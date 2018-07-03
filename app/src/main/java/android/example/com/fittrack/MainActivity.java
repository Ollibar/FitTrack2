package android.example.com.fittrack;

import android.content.Intent;

import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTrain_ziel;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.os.Bundle;
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
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int activeUserID;
    private static final String TAG = "UserActivity";
    TextView nav_Arnisays;


    private static final String LOG = MainActivity.class.getSimpleName();
    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        nav_Arnisays =findViewById( R.id.textView4 );



    }

    private void getArnisquote() {
        String[] quotearray = {
                "Bodybuilding is much like any other sport. To be successful, you must dedicate yourself 100% to your training, diet and mental approach.",
                "Failure is not an option. Everybody has to succeed.",
                "There are no shortcuts - Everything is reps, reps, reps.",
                "The mind is the limit. As long as the mind can envision the fact that you can do something, you can do it, as long as you really believe 100 percent.",
                "The worst thing i can be is the same as everybody else.",
                "The Resistance that you fight physically in the gym and the resistance that you fight in life can only build a strong character.",
                "Stop Whining",
                "Trust yourself",
                "Work Your Butt Off",
                "When you go through hardships and decide not to surrender, that is strength",
                "For me life is continuously being hungry. The meaning of life is not simply to exist, to survive, but to move ahead, to go up, to achieve, to conquer.",
                "tart wide, expand further, and never look back.",
                "Just remember, you can't climb the ladder of success with your hands in your pockets.",
                "Nothing is impossible, the word itself says 'I'm possible'!",
                "What is the point of being on this Earth if you are going to be like everyone else?",
                "The mind is the limit. As long as the mind can envision the fact that you can do something, you can do it, as long as you really believe 100 percent.",
                "If you don’t find the time, if you don’t do the work, you don’t get the results.",
                "Life’s six rules for success. 1. Trust yourself. 2. Break some rules. 3. Don’t be afraid to fail. 4. Ignore the naysayers. 5. Work like hell. 6. Give something back.",
                "Life may be full of pain but that’s not an excuse to give up.",
                "Money doesn’t make you happy. I now have $50 million but I was just as happy when I had $48 million."};

        int random = new Random().nextInt(quotearray.length);
        String quote =quotearray[random];


        nav_Arnisays.setText(quote);

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

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.appStatistics){
            Intent in = new Intent(this,AppStatistics.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    // Navigation Drawer Menü Listner
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

    // Methode zum einfügen von Testdaten in die Datenbank
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
        ModelTraining  training = new ModelTraining("20/06/2018",2,3,"Beschreibung 1",123,159,147,0,0);
        db.createTraining( training );
        training = new ModelTraining("17/06/2018",2,4,"Beschreibung 1",123,159,147,0,0);
        db.createTraining( training );
        training = new ModelTraining("01/06/2018",1,1,"1",0,0,0,11,67);
        db.createTraining( training );
        training = new ModelTraining("03/06/2018",1,1,"2",0,0,0,13,42);
        db.createTraining( training );
        training = new ModelTraining("05/06/2018",1,1,"3",0,0,0,15,26);
        db.createTraining( training );
        training = new ModelTraining("07/06/2018",1,1,"4",0,0,0,17,30);
        db.createTraining( training );
        training = new ModelTraining("09/06/2018",1,1,"5",0,0,0,23,20);
        db.createTraining( training );
        training = new ModelTraining("11/06/2018",1,1,"6",0,0,0,11,11);
        db.createTraining( training );
        training = new ModelTraining("09/06/2018",1,2,"5",0,0,0,22,20);
        db.createTraining( training );
        training = new ModelTraining("11/06/2018",1,2,"6",0,0,0,33,12);
        db.createTraining( training );
        training = new ModelTraining("13/06/2018",3,2,"7",0,0,0,450,1);
        db.createTraining( training );
        training = new ModelTraining("09/06/2018",1,3,"8",60,12,600,0,0);
        db.createTraining( training );
        training = new ModelTraining("11/06/2018",1,3,"9",60,12,600,0,0);
        db.createTraining( training );

        // Trainingsziel
        ModelTrain_ziel train_ziel = new ModelTrain_ziel(1,1,0,0,0,70,"3 ","4 "," ");
        db.createTrain_ziel(train_ziel);
        ModelTrain_ziel train_ziele = new ModelTrain_ziel(1,2,90,60,123,70," ","   "," ");
        db.createTrain_ziel(train_ziele);

    }

    public void getArnisquote(View view) {
        getArnisquote();
    }

    }

