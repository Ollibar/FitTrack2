package android.example.com.fittrack;

import android.content.Intent;
import android.database.Cursor;
import android.example.com.fittrack.Datenbank.BenutzerData;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class User extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BenutzerData benutzerData;
    private ListView list;

    DatabaseHelper db = new DatabaseHelper( this );


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

        benutzerData = new BenutzerData( this );

        createList();

        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                String name = benutzerData.getUserNamebyID( position+1 );
                int age = benutzerData.getUserAgebyID( position+1 );
                int weight = benutzerData.getUserWeightbyID( position+1 );
                //openUserForm(zahl);

                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position+ "  name: "+ name +" alter " +age +"gewicht "+weight, Toast.LENGTH_LONG)
                        .show();





            }
        } );

    }

    private void openUserForm(long id){

        Toast.makeText( getApplicationContext(),"Test",Toast.LENGTH_LONG );



    }

    private void openUserForm() {
        Intent i = new Intent(this,form_user.class);
        startActivity( i );
    }

    private void createList() {

        //DB Curser
        Cursor data = benutzerData.getTableData( "Benutzer" );
        ArrayList<String> listData_name = new ArrayList<>(  );
        //hole daten
        while(data.moveToNext()){
            listData_name.add( data.getString(1)+ "   "+ data.getInt(3)+" Jahre" +"   " + data.getInt(2) + " Kg");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_user_item_3,listData_name);

        ArrayList<ModelBenutzer> listData = new ArrayList<>(  );
        listData = db.getAllBenutzer();

        ArrayAdapter<ModelBenutzer> arrayAdapter = new ArrayAdapter<>( this, R.layout.list_user_item, listData );

        //Configure the list view
        list = (ListView) findViewById(R.id._userlist);
        list.setAdapter( adapter );
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
        getMenuInflater().inflate( R.menu.user, menu );
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
            Intent i = new Intent(this,User.class);
            startActivity( i );
        } else if (id == R.id.nav_station) {
            Intent i = new Intent(this,Station.class);
            startActivity( i );
        } else if (id == R.id.nav_training) {
            Intent i = new Intent(this,Training.class);
            startActivity( i );
        } else if (id == R.id.nav_target) {
            Intent i = new Intent(this,Target.class);
            startActivity( i );
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
