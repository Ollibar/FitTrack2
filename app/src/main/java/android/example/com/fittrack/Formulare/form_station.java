package android.example.com.fittrack.Formulare;

import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class form_station extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper( this );
    EditText edTName;
    String statName;
    Spinner spTyp;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_form_station );
        edTName =(EditText) findViewById( R.id.edT_station_name ) ;
        statName =edTName.getText().toString();
        spTyp =(Spinner) findViewById( R.id.stationspinner );
        i = getIntent();

        if(i!=null){
            ModelStation station = new ModelStation(  );
            station = db.getStation( i.getLongExtra( "stationID",0 ) );
            edTName.setText( station.getStation_name() );
            spTyp.setSelection( station.getStation_typ()-1 );
        }
    }

    public void speicherStation(View view) {

        int spinner_pos = spTyp.getSelectedItemPosition();
        int[] values = getResources().getIntArray( R.array.station_typ_value );
        int typvalue = values[spinner_pos];

        ModelStation station =new ModelStation( statName,typvalue );
        db.createStation( station );

    }

    public void abbrechen(View view) {
        super.onBackPressed();
    }
}
