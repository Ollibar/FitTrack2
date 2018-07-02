package android.example.com.fittrack;

import android.content.Intent;
import android.database.Cursor;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelTrain_ziel;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.example.com.fittrack.Formulare.form_training;
import android.example.com.fittrack.ListAdapter.StatistikListAdapter;
import android.example.com.fittrack.ListAdapter.TrainingListAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Statistik extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        Spinner spinnerBenutzer;
        ListView lv;
    private DatabaseHelper db ;

    private ArrayAdapter<String> userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_statistics );
        db = new DatabaseHelper(this);
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        getUserSpinner();


        spinnerBenutzer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  //createTestgraph();
                erzeugeTrainingListe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void getUserSpinner() {
        db = new DatabaseHelper( this );
        spinnerBenutzer = (Spinner)findViewById( R.id.spinnerStatisticUser);

        String[] spinnerBenutzerArray=db.getAllBenutzerNamen();
        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, spinnerBenutzerArray);
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBenutzer.setAdapter(spinnerArrayAdapter1);
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

    private void erzeugeTrainingListe() {
        String username = spinnerBenutzer.getSelectedItem().toString();
        ModelBenutzer benutzer = db.getBenutzer( username );


        lv = findViewById( R.id.statistikList );

        Cursor c = db.getPumperStats( benutzer.getBenutzer_id() );
        StatistikListAdapter sla = new StatistikListAdapter( getApplicationContext(), c );

        lv.setAdapter( sla );

        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        } );
    }
    /*
    private void createTestgraph(){
        String s = userSpinner.getSelectedItem().toString();
        GraphView graph = (GraphView) findViewById(R.id.graphViewKcal);
        DataPoint[]data = db.getData(db.getBenutzer(s).getBenutzer_id());
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(data);
        series.setValuesOnTopColor(Color.RED);
        series.setDrawValuesOnTop(true);
        graph.addSeries(series);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Datum");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Kcal");



    }
*/

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
