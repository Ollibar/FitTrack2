package android.example.com.fittrack;

import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.example.com.fittrack.Formulare.form_training;
import android.example.com.fittrack.ListAdapter.TrainingListAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Training extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper db = new DatabaseHelper( this );
    ListView lv;
    Spinner spinnerBenutzer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_training );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertNewTraining();
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
        erzeugeTrainingListe();
        spinnerBenutzer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                erzeugeTrainingListe();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                erzeugeTrainingListe();
            }
            });

    }

    private void setSpinner() {
        spinnerBenutzer = (Spinner)findViewById( R.id.training_spinner_);

        DatabaseHelper db = new DatabaseHelper( this );
        String[] spinnerBenutzerArray=db.getAllBenutzerNamen();
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, spinnerBenutzerArray);
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBenutzer.setAdapter(spinnerArrayAdapter1);
    }

    private void erzeugeTrainingListe() {
        String username = spinnerBenutzer.getSelectedItem().toString();

        ModelBenutzer benutzer = (ModelBenutzer) db.getBenutzer( username );
        ArrayList<ModelTraining> trainingList=db.getTrainingByUserID(benutzer.getBenutzer_id());
        lv=findViewById( R.id.listView_trainig );
        TrainingListAdapter tla = new TrainingListAdapter(getApplicationContext(),trainingList);

        lv.setAdapter(tla);
        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TrainingOnClickDialog tcd = new TrainingOnClickDialog(Training.this, id);
                tcd.show();
            }
        });


    }

    private void insertNewTraining() {
        Intent i = new Intent(this,form_training.class);
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
            Intent i = new Intent(this,Statistik.class);
            startActivity( i );

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
