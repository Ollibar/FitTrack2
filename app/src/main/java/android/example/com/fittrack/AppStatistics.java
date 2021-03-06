package android.example.com.fittrack;

import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Gesamtstatistik
 */
public class AppStatistics extends AppCompatActivity {

    TextView user, station, training, ziel;
    private DatabaseHelper db = new DatabaseHelper( this );

    /**
     * zeig die anzahl der user/ station/ training/ ziel in der ganzen app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.app_statistics );

        user = (TextView) findViewById( R.id.tV_anzahlUser );
        station = (TextView) findViewById( R.id.tV_anzahlStation );
        training = (TextView) findViewById( R.id.tV_anzahlTraining );
        ziel = (TextView) findViewById( R.id.tV_anzahlZiele );

        if (db != null) {
            user.setText( "Anzahl der User in deine App: " + db.getBenutzerCount() );
            station.setText( "Anzahl der Geräte in deine App: " + db.getStationCount() );
            training.setText( "Anzahl der Trainings in deine App: " + db.getTrainingCount() );
            ziel.setText( "Anzahl der Trainingsziele in deine App: " + db.getTrain_zielCount() );
        }
    }

    /**
     * zurück zum MainActivity
     * @param view
     */
    public void onClickback(View view) {
        Intent in = new Intent( this, MainActivity.class );
        startActivity( in );
        db.closeDB();
    }
}
