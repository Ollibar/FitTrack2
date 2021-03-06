package android.example.com.fittrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.Formulare.form_user;
import android.example.com.fittrack.ListAdapter.BenutzerListAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Activity für Funktionen rum um den Benutzer
 */

public class Benutzer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DatabaseHelper db = new DatabaseHelper( this );
    private ListView listView;

    /**
     * die wichtigstens komponente des navigationdrawer werden hier erstellt
     * sowie die methode für die erzeugung der benutzer listview
     * überwiegend automatisch generiert durch android studio(toolbar, floatingactionbutton,layout,
     * navigationview)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserForm();
            }
        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        showAllListBenutzer();


    }
    /**
     * toogle button wird gedrückt und die userform activity wird gestartet
     * für die eingabe der daten
     */
    private void openUserForm() {
        Intent i = new Intent( this, form_user.class );
        startActivity( i );
    }
    /**
     * methode für die erzeugung der benutzer listview
     * aus der datenbank bekommen wir eine Arraylist von allen Benutzer;
     * durch ein OnClickItemListener mit integrierten AlertDialog können wir dann ein Benutzer
     * aus der Datenbank löschen
     */

    public void showAllListBenutzer() {
        final ArrayList<ModelBenutzer> benutzerList = db.getAllBenutzer();
        listView = findViewById( R.id._userlist );
        BenutzerListAdapter bla = new BenutzerListAdapter( getApplicationContext(), benutzerList );

        listView.setAdapter( bla );
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder( Benutzer.this );
                builder.setTitle( "Wollen sie diesen Benutzer Löschen ?" );
                builder.setCancelable( true );
                builder.setPositiveButton( "Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteBenutzer( benutzerList.get( position ) );
                        Toast.makeText( Benutzer.this, "Benutzer " + benutzerList.get( position ).getBenutzer_name() +
                                        " wurde Erfolgreich gelöscht. Hasta la vista, baby!",
                                Toast.LENGTH_LONG ).show();
                        // Liste aktualisieren
                        showAllListBenutzer();
                    }
                } );
                builder.setNegativeButton( "Nein", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                } );
                AlertDialog alert = builder.create();
                alert.show();
            }
        } );
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }
    /**
     * navigation durch die die UI
     * @param item acttivities
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

