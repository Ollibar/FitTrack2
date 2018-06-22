package android.example.com.fittrack.Formulare;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_form_station );
    }

    public void speicherStation(View view) {

        EditText edTName =(EditText) findViewById( R.id.edT_station_name ) ;
        String statName =edTName.getText().toString();

        Spinner spTyp =(Spinner) findViewById( R.id.stationspinner );

        int spinner_pos = spTyp.getSelectedItemPosition();
        int[] values = getResources().getIntArray( R.array.station_typ_value );
        int typvalue = values[spinner_pos];

        ModelStation station =new ModelStation( statName,typvalue );
        db.createStation( station );

    }
}
