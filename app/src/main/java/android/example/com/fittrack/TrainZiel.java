package android.example.com.fittrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelTrain_ziel;
import android.example.com.fittrack.Formulare.form_target;
import android.example.com.fittrack.ListAdapter.TrainZielListAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Activity für fukntionen rum um die ziele des benutzers
 */

public class TrainZiel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String LOG = TrainZiel.class.getSimpleName();
    private DatabaseHelper db = new DatabaseHelper( this );
    private ListView lv;
    private Spinner spinnerUser;

    /**
     * die wichtigstens komponente des navigationdrawer werden hier erstellt
     * überwiegend automatisch generiert durch android studio(toolbar, floatingactionbutton,layout,
     * navigationview);
     * ein onClickListener für den floating button um form_target Activity zu öffnen;
     * befüllen mit setSpinner() methode unseren Spinner für die ListView;
     * die Listview ist dann spezifisch für den einen Benutzer;
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_target );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTargetForm();
            }
        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        setSpinner();
        erzeugeListe();

        spinnerUser.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                erzeugeListe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d( LOG, "adater ausgeführt" );
            }
        } );
    }

    /**
     * wir bekommen über den ausgewählten Inhalt des Spinners die jeweilige BenutzerID
     * und bekommen mit getAllTrain_ziel(int BenutzerID) alle ziele des zugehörigen Benutzer
     * anschließende verknüpfung zwischen Arraylist der ziele mit ListView;
     * OnclickListener für die option des löschen in der Listview
     */

    private void erzeugeListe() {

        final String username = spinnerUser.getSelectedItem().toString();
        ModelBenutzer mb = db.getBenutzer( username );
        final ArrayList<ModelTrain_ziel> trainZielList = db.getAllTrain_ziel( mb.getBenutzer_id() );
        lv = findViewById( R.id.lV_ziele );
        TrainZielListAdapter tla = new TrainZielListAdapter( getApplicationContext(), trainZielList );
        lv.setAdapter( tla );
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder( TrainZiel.this );
                alert.setTitle( "Dieses Ziel wirklich LÖSCHEN !?" );
                alert.setCancelable( true );
                alert.setPositiveButton( "Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteTrain_ziel( trainZielList.get( position ) );
                        Toast.makeText( TrainZiel.this, "You are one ugly motherf****r " +
                                username +
                                ", Arnie-Predator", Toast.LENGTH_LONG
                        ).show();
                        erzeugeListe();
                    }
                } );
                alert.setNegativeButton( "Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                } );
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        } );
    }

    /**
     * befüllen den Spinner mit allen benutzer namen in der datenbank
     */

    private void setSpinner() {
        spinnerUser = findViewById( R.id.spinner_traning_ziel );
        String[] spinnerBenutzerArray = db.getAllBenutzerNamen();
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<>
                ( this, android.R.layout.simple_spinner_item, spinnerBenutzerArray );
        spinnerArrayAdapter1.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinnerUser.setAdapter( spinnerArrayAdapter1 );
    }

    /**
     * öffnen form_taget activity
     */

    private void openTargetForm() {
        Intent i = new Intent( this, form_target.class );
        startActivity( i );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

    /**
     * navigation UI
     * @param item
     * @return true
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent( this, MainActivity.class );
            startActivity( i );
        } else if (id == R.id.nav_user) {
            Intent i = new Intent( this, Benutzer.class );
            startActivity( i );
        } else if (id == R.id.nav_station) {
            Intent i = new Intent( this, Station.class );
            startActivity( i );
        } else if (id == R.id.nav_training) {
            Intent i = new Intent( this, Training.class );
            startActivity( i );
        } else if (id == R.id.nav_target) {
            Intent i = new Intent( this, TrainZiel.class );
            startActivity( i );
        } else if (id == R.id.nav_statistics) {
            Intent i = new Intent( this, Statistik.class );
            startActivity( i );

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
