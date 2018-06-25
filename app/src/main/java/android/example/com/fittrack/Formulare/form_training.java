package android.example.com.fittrack.Formulare;

import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.example.com.fittrack.MainActivity;
import android.example.com.fittrack.R;
import android.example.com.fittrack.Training;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class form_training extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper( this );
    Spinner spinnerBenutzer, spinnerStation;
    TextView geschwindigkeit, dauer, datum, beschreibung, kcal;







    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_form_training );
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        dauer = findViewById( R.id.editText_dauer );
        geschwindigkeit = findViewById( R.id.editText_speed );
        datum=findViewById( R.id.editText_Datum );
        Date strDate = Calendar.getInstance().getTime();
        datum.setText( dateFormat.format(strDate));
        kcal=findViewById( R.id.editText_kcal  );
        setSpinner();
        spinnerStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(getStationtyp()==1){
                    dauer.setVisibility(View.GONE);
                    kcal.setVisibility( View.GONE );
                    geschwindigkeit.setVisibility(View.GONE);
                }
                else if(getStationtyp()==2){

                    dauer.setVisibility(View.VISIBLE);
                    geschwindigkeit.setVisibility(View.VISIBLE);
                    kcal.setVisibility( View.VISIBLE );



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setSpinner() {
        // erzeugt Listen für die Spinner
        spinnerBenutzer = (Spinner)findViewById( R.id.spinner_user );

        DatabaseHelper db = new DatabaseHelper( this );
         String[] spinnerBenutzerArray=db.getAllBenutzerNamen();
         ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, spinnerBenutzerArray);
        spinnerArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBenutzer.setAdapter(spinnerArrayAdapter1);

        spinnerStation = (Spinner)findViewById( R.id.spinner_station );

        String[] spinnerStationArray=db.getAllStationNamen();
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,spinnerStationArray);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStation.setAdapter(spinnerArrayAdapter2);

    }

    public int getStationtyp() {
        String name = spinnerStation.getSelectedItem().toString();
        ModelStation station =(ModelStation)db.getStation( name );
        int stationtyp =(int)station.getStation_typ();

        return stationtyp;
    }

    public int getStationID() {
        String name = spinnerStation.getSelectedItem().toString();
        ModelStation station =(ModelStation)db.getStation( name );
        int stationID =(int)station.getStation_id();

        return stationID;
    }


    public void speicherTraining(View view) {


        ModelTraining training = new ModelTraining();
        ModelBenutzer benutzer;

        beschreibung=findViewById( R.id.editText_beschreibung );
        training.setTraining_beschreibung( beschreibung.getText().toString() );
        training.setTraining_datum(datum.getText().toString());
        training.setTraining_station_id( getStationID() );

        benutzer = db.getBenutzer(  spinnerBenutzer.getSelectedItem().toString() );

        training.setTraining_benutzer_id(benutzer.getBenutzer_id() );

        if(getStationtyp()==2){

            training.setTraining_geschwindigkeit( Integer.valueOf( geschwindigkeit.getText().toString() ) );
            training.setTraining_dauer(Integer.valueOf(dauer.getText().toString()));
            training.setTraining_kcal(Integer.valueOf(kcal.getText().toString() ) );
        }


        db.createTraining(training);
        Intent i = new Intent(this,Training.class);
        startActivity( i );
    }
}
