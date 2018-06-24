package android.example.com.fittrack.Formulare;

import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.example.com.fittrack.MainActivity;
import android.example.com.fittrack.R;
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
    TextView geschwindigkeit, dauer, satz, datum, beschreibung;
    ListView listView;
    Button addSatz;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_form_training );
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        dauer = findViewById( R.id.editText_dauer );
        geschwindigkeit = findViewById( R.id.editText_speed );
        satz=findViewById( R.id.tV_Satz );
        listView=findViewById( R.id.list_satz );
        addSatz=findViewById( R.id.but_satz );
        datum=findViewById( R.id.editText_Datum );
        Date strDate = Calendar.getInstance().getTime();
        datum.setText( dateFormat.format(strDate));
        setSpinner();
        spinnerStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(getStationtyp()==1){
                    satz.setVisibility( View.VISIBLE );
                    listView.setVisibility( View.VISIBLE );
                    addSatz.setVisibility( View.VISIBLE );

                    dauer.setVisibility(View.GONE);

                    geschwindigkeit.setVisibility(View.GONE);
                }
                else if(getStationtyp()==2){

                    dauer.setVisibility(View.VISIBLE);
                    geschwindigkeit.setVisibility(View.VISIBLE);

                    satz.setVisibility( View.GONE );
                    listView.setVisibility( View.GONE );
                    addSatz.setVisibility( View.GONE );
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
        ModelBenutzer benutzer = new ModelBenutzer();

        beschreibung=findViewById( R.id.editText_beschreibung );
        training.setTraining_beschreibung( beschreibung.getText().toString() );
        training.setTraining_datum(datum.getText().toString());
        training.setTraining_station_id( getStationID() );

        benutzer = db.getBenutzer(  spinnerBenutzer.getSelectedItem().toString() );

        training.setTraining_benutzer_id(benutzer.getBenutzer_id() );

        if(getStationtyp()==2){
            training.setTraining_geschwindigkeit( R.id.editText_dauer );
            training.setTraining_dauer(R.id.editText_dauer);
        }


        db.createTraining(training);
    }
}
