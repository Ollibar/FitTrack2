package android.example.com.fittrack.Formulare;


import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.example.com.fittrack.Benutzer;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * speichern und update von user
 */

public class form_user extends AppCompatActivity {

    private static final String LOG = form_user.class.getSimpleName();
    private DatabaseHelper dbHelper = new DatabaseHelper( this );
    private EditText txtfield1, txtfield2, txtfield3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_form_user );

        txtfield1 = (EditText) findViewById( R.id.form_user_name );
        txtfield2 = (EditText) findViewById( R.id.form_user_age );
        txtfield3 = (EditText) findViewById( R.id.form_user_weight );
    }

    /**
     * Methode für das clicken auf den user speichern button;
     * wir hollen uns die eingetragenen daten von user aus den EditText
     * und schreiben sie in die datenbank mit hilfe eines ModelBenutzer Objekt
     * das wir der methode createBenutzer(ModelBenutzer) übergeben;
     * anschließen gehen wir in die Benutzer activity zurück
     * @param view
     */

    public void insertUserIntoDB(View view) {

        String name = txtfield1.getText().toString();
        String alter = txtfield2.getText().toString();
        String gewicht = txtfield3.getText().toString();

        if (TextUtils.isEmpty( name )) {
            txtfield1.setError( "Zeile ist Leer !" );
            return;
        } else if (TextUtils.isEmpty( alter )) {
            txtfield2.setError( "Zeile ist Leer" );
            return;
        } else if (TextUtils.isEmpty( gewicht )) {
            txtfield3.setError( "Zeile ist Leer !" );
            return;
        }

        int alt = Integer.parseInt( alter );
        int gew = Integer.parseInt( gewicht );

        ModelBenutzer user = new ModelBenutzer( name, alt, gew );

        long index = dbHelper.createBenutzer( user );
        Log.d( LOG, "index von benutzer: " + index );
        if (index != -1) {
            Intent i = new Intent( this, Benutzer.class );
            startActivity( i );
            Toast.makeText( this, "Neuer Pumper erstellt", Toast.LENGTH_LONG ).show();
            dbHelper.closeDB();
        }
    }

    /**
     * updateUser Button
     * erzwingen die eingabe des namens mit der try/catch;
     * da wir dann in der der update methode durch where bedingung die datensätze mit dem
     * name vergleichen;
     * anschließen gehen wir in die Benutzer activity zurück
     * @param view
     */

    public void updateUser(View view) {

        String name = txtfield1.getText().toString();
        String alter = txtfield2.getText().toString();
        String gewicht = txtfield3.getText().toString();

        try {

            ModelBenutzer user = dbHelper.getBenutzer( name );

            if (TextUtils.isEmpty( alter )) {
                user.setBenutzer_alter( user.getBenutzer_alter() );
            } else user.setBenutzer_alter( Integer.parseInt( alter ) );

            if (TextUtils.isEmpty( gewicht )) {
                user.setBenutzer_alter( user.getBenutzer_gewicht() );
            } else user.setBenutzer_gewicht( Integer.parseInt( gewicht ) );


            int i = dbHelper.updateBenutzer( user );

            Intent intent = new Intent( this, Benutzer.class );
            if (i <= 0) {
                startActivity( intent );
                Toast.makeText( this, "Keine Pumper gefunden :(", Toast.LENGTH_LONG ).show();
                dbHelper.closeDB();
            } else startActivity( intent );
            Toast.makeText( this, name + " Updated :) ", Toast.LENGTH_LONG ).show();
            dbHelper.closeDB();
        } catch (CursorIndexOutOfBoundsException exception) {
            txtfield1.setError( "Zeile ist Leer !" );
            Toast.makeText( this, "Bitte einen Namen eingeben", Toast.LENGTH_LONG ).show();
        }
    }
}


