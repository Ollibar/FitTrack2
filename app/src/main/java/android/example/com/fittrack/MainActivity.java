package android.example.com.fittrack;

import android.content.Intent;

import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelSatz;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int activeUserID;
    private static final String TAG = "UserActivity";


    private static final String LOG = MainActivity.class.getSimpleName();
    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );





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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity( i );
        } else if (id == R.id.nav_user) {
            Intent i = new Intent(this,Benutzer.class);
            startActivity( i );
        } else if (id == R.id.nav_station) {
            Intent i = new Intent(this,Station.class);
            startActivity( i );
        } else if (id == R.id.nav_training) {
            Intent i = new Intent(this,Training.class);
            startActivity( i );
        } else if (id == R.id.nav_target) {
            Intent i = new Intent(this,TrainZiel.class);
            startActivity( i );
        } else if (id == R.id.nav_statistics) {
            Intent i = new Intent(this,Statistics.class);
            startActivity( i );

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }




    public void testdaten(MenuItem item) {
        // Benutzer
        ModelBenutzer user =new ModelBenutzer( "Betty Kane",25,65 );
        db.createBenutzer( user );
        user =new ModelBenutzer( "Arthur Curry",39,90 );
        db.createBenutzer( user );
        user =new ModelBenutzer( "Scott Lang",31,70 );
        db.createBenutzer( user );

        //Stationen
        ModelStation station = new ModelStation("Brustpresse",1  );
        db.createStation( station );
        station = new ModelStation("Klimmzugmaschine",1  );
        db.createStation( station );
        station = new ModelStation("Laufband",2  );
        db.createStation( station );
        station = new ModelStation("Crosstrainer",2  );
        db.createStation( station );

        //Trainings
        ModelTraining  training = new ModelTraining("25/06/2018",2,3,"Beschreibung 1",123,159,147);
        db.createTraining( training );
        training = new ModelTraining("24/06/2018",2,4,"Beschreibung 1",123,159,147);
        db.createTraining( training );
        training = new ModelTraining("27/06/2018",1,1,"Beschreibung 1",0,0,0);
        db.createTraining( training );
        training = new ModelTraining("24/06/2018",3,2,"Beschreibung 1",0,0,0);
        db.createTraining( training );

        //Satz
        ModelSatz satz = new ModelSatz( 3,1,25,25 );
        db.createSatz( satz );
        satz = new ModelSatz( 3,2,25,25 );
        db.createSatz( satz );
        satz = new ModelSatz( 3,3,25,25 );
        db.createSatz( satz );

    }


    public void test(View view) {
        String[] array = db.getAllBenutzerNamen();
        for(int p =0; p<array.length;p++){
            Log.v(TAG, "Name=" + array[p]);
            Log.v(TAG, "ok" );

        }
    }
}
