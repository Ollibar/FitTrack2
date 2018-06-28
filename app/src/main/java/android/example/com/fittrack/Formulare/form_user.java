package android.example.com.fittrack.Formulare;


import android.content.Intent;

import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.R;
import android.example.com.fittrack.Benutzer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class form_user extends AppCompatActivity {

    private DatabaseHelper dbHelper = new DatabaseHelper(this);

    private static final String LOG = form_user.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_form_user );
    }

    public void insertUserIntoDB(View view) {

        EditText txtfield1 =(EditText)  findViewById(R.id.form_user_name);
        String name = txtfield1.getText().toString();

        EditText txtfield2 =(EditText)  findViewById(R.id.form_user_age);
        String alter = txtfield2.getText().toString();

        EditText txtfield3 =(EditText)  findViewById(R.id.form_user_weight);
        String gewicht = txtfield3.getText().toString();

        if(TextUtils.isEmpty(name)){
            txtfield1.setError("Zeile ist Leer !");
            return;
        }
        else if(TextUtils.isEmpty(alter)){
            txtfield2.setError("Zeile ist Leer");
            return;
        }
        else if(TextUtils.isEmpty(gewicht)){
            txtfield3.setError("Zeile ist Leer !");
            return;
        }

        int alt = Integer.parseInt(alter);
        int gew = Integer.parseInt(gewicht);

        ModelBenutzer user = new ModelBenutzer(name,alt,gew);

        long index = dbHelper.createBenutzer(user);
        Log.d(LOG,"index von benutzer: "+index);
        if(index != -1) {
            Intent i = new Intent(this, Benutzer.class);
            startActivity(i);
            Toast.makeText(this,"Neuer Pumper erstellt",Toast.LENGTH_LONG).show();
            dbHelper.closeDB();
        }
    }

    public void updateUser(View view){

        EditText txtfield1 =(EditText)  findViewById(R.id.form_user_name);
        String name = txtfield1.getText().toString();

        EditText txtfield2 =(EditText)  findViewById(R.id.form_user_age);
        String alter = txtfield2.getText().toString();

        EditText txtfield3 =(EditText)  findViewById(R.id.form_user_weight);
        String gewicht = txtfield3.getText().toString();

        if(TextUtils.isEmpty(name)){
            txtfield1.setError("Zeile ist Leer !");
            return;
        }
        if(TextUtils.isEmpty(alter)){
            txtfield2.setError("Zeile ist Leer");
            return;
        }
        if(TextUtils.isEmpty(gewicht)){
            txtfield3.setError("Zeile ist Leer !");
            return;
        }

        int alt = Integer.parseInt(alter);
        int gew = Integer.parseInt(gewicht);

        ModelBenutzer user = new ModelBenutzer(name,alt,gew);
        int i = dbHelper.updateBenutzer(user);

        Intent intent = new Intent(this, Benutzer.class);
        if(i <= 0){
            startActivity(intent);
            Toast.makeText(this,"Keine Pumper gefunden :(",Toast.LENGTH_LONG).show();
            dbHelper.closeDB();
        }else startActivity(intent);
            Toast.makeText(this,name+" Updated :) ",Toast.LENGTH_LONG).show();
            dbHelper.closeDB();
        }
    }

