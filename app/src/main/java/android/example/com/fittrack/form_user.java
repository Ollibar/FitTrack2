package android.example.com.fittrack;


import android.content.Intent;
import android.example.com.fittrack.Datenbank.BenutzerData;
import android.example.com.fittrack.TabellenObjekte.Benutzer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class form_user extends AppCompatActivity {
    private BenutzerData benutzerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_form_user );
    }

    public void insertUserIntoDB(View view) {

        benutzerData = new BenutzerData(this);
        benutzerData.open();
        EditText txtfield1 =(EditText)  findViewById(R.id.form_user_name);
        String name = txtfield1.getText().toString();

        EditText txtfield2 =(EditText)  findViewById(R.id.form_user_age);
        Integer alter = Integer.parseInt( txtfield2.getText().toString() );

        EditText txtfield3 =(EditText)  findViewById(R.id.form_user_weight);
        Integer gewicht = Integer.parseInt( txtfield3.getText().toString() );

        Benutzer benutzer = benutzerData.createBenutzer(name,gewicht,alter);
        benutzerData.close();

        Intent i = new Intent(this,User.class);
        startActivity( i );

    }

}
