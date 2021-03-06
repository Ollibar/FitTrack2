package android.example.com.fittrack;

import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.Formulare.form_station;
import android.example.com.fittrack.ListAdapter.StationListAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 *Activity Klasse für Stationen
 */
public class Station extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper db = new DatabaseHelper( this );
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_station );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStationForm();
            }
        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        erzeugeStationListe();
    }

    /**
     * Ruft alle Geräte aus der Datenbank ab und übergibt SIe mit Hilfe des ListAdapters an den ListView.
     */
    private void erzeugeStationListe() {
        ArrayList<ModelStation> stationList = db.getAllStation();
        lv = findViewById( R.id.stationlist );
        StationListAdapter sla = new StationListAdapter( getApplicationContext(), stationList );

        lv.setAdapter( sla );
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openStationForm( id );
            }
        } );
    }

    /**
     * Ruft die Klasse FormStation auf, um ein neues GErät anzulegen
     */
    private void openStationForm() {
        Intent i = new Intent( this, form_station.class );
        startActivity( i );
    }
    /**
     * Ruft die Klasse FormStation auf, um ein neues GErät anzulegen
     * hinterlegt im intent die Geräte ID
     */
    private void openStationForm(long id) {
        Intent i = new Intent( this, form_station.class );
        i.putExtra( "stationID", id );
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
