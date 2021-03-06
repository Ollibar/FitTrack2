package android.example.com.fittrack;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.Formulare.form_training;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Klasse für den Listendialog ( Training)
 */
public class TrainingOnClickDialog extends Dialog implements View.OnClickListener {
    public Activity c;
    public Dialog d;
    public Button abbrechen, bearbeiten, loeschen;
    public long trainingID;
    DatabaseHelper db = new DatabaseHelper( getContext() );

    public TrainingOnClickDialog(Activity a, long id) {
        super( a );
        this.c = a;
        this.trainingID = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        setContentView( R.layout.trainingonclickdialog );
        bearbeiten = (Button) findViewById( R.id.bt_dialog_Bearbeiten );
        bearbeiten.setOnClickListener( this );
        loeschen = (Button) findViewById( R.id.bt_dialog_delete );
        loeschen.setOnClickListener( this );
        abbrechen = (Button) findViewById( R.id.bt_dialog_abbrechen );
        abbrechen.setOnClickListener( this );
    }

    /**
     * OnClick handler für den Trainings Dialog, der sich beim klick auf ein Listitem öffnet
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.bt_dialog_Bearbeiten) {
            Intent i = new Intent( getContext(), form_training.class );
            i.putExtra( "ID", trainingID );
            c.startActivity( i );
        } else if (v.getId() == R.id.bt_dialog_delete) {

            db.deleteTrainingbyID( trainingID );


        } else if (v.getId() == R.id.bt_dialog_abbrechen) {
            super.onBackPressed();
        }

    }
}
