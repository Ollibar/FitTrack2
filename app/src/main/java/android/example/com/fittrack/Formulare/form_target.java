package android.example.com.fittrack.Formulare;

import android.content.Intent;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTrain_ziel;
import android.example.com.fittrack.R;
import android.example.com.fittrack.TrainZiel;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class form_target extends AppCompatActivity {


    private Spinner spinnerUser,spinnerStation;
    private ArrayAdapter<String> adapterUser, adapterTrain;
    private TextView tvSollGewicht,tvSollGeschw,tvSollDauer,tvGewichtwunsch,tvPos1,tvPos2,tvPos3;


    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_target);

        getUserSpinner();
        getStationSpinner();

        spinnerStation=(Spinner)findViewById(R.id.spinner_Station);
        spinnerUser=(Spinner)findViewById(R.id.spinner_Station);
        tvSollGewicht =(TextView)findViewById(R.id.edT_soll_gewicht);
        tvSollDauer =(TextView)findViewById(R.id.edT_soll_dauer);
        tvSollGeschw=(TextView)findViewById(R.id.edT_soll_geschwindigkeit);
        tvGewichtwunsch=(TextView)findViewById(R.id.edT_gewichtwusch_);
        tvPos1=(TextView)findViewById(R.id.edT_pos_1);
        tvPos2=(TextView)findViewById(R.id.edT_pos_2);
        tvPos3=(TextView)findViewById(R.id.edT_pos_3);



        spinnerStation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = parent.getItemAtPosition(position).toString();
                ModelStation station = db.getStation(s);
                if(station.getStation_typ()==1){
                    tvSollGewicht.setVisibility(View.VISIBLE);
                    tvSollDauer.setVisibility(View.GONE);
                    tvSollGeschw.setVisibility(View.GONE);
                    tvGewichtwunsch.setVisibility(View.GONE);
                    tvPos1.setVisibility(View.VISIBLE);
                    tvPos2.setVisibility(View.VISIBLE);
                    tvPos3.setVisibility(View.VISIBLE);
                }else if(station.getStation_typ()==2){
                    tvSollGewicht.setVisibility(View.GONE);
                    tvSollDauer.setVisibility(View.VISIBLE);
                    tvSollGeschw.setVisibility(View.VISIBLE);
                    tvGewichtwunsch.setVisibility(View.VISIBLE);
                    tvPos1.setVisibility(View.GONE);
                    tvPos2.setVisibility(View.GONE);
                    tvPos3.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(form_target.this,"Do something !",Toast.LENGTH_LONG).show();
            }
        });


    }


    public void insertTarget(View view) {

        String gewicht = tvSollGewicht.getText().toString();
        String dauer = tvSollDauer.getText().toString();
        String geschw=tvSollGeschw.getText().toString();
        String gewichtwunsch=tvGewichtwunsch.getText().toString();
        String pos1=tvPos1.getText().toString();
        String pos2=tvPos2.getText().toString();
        String pos3=tvPos3.getText().toString();


        ModelTrain_ziel ziele = new ModelTrain_ziel();
        ziele.setTrain_ziel_benutzer_id(getBenutzerID());
        ziele.setTrain_ziel_station_id(getStationID());
        ziele.setTrain_ziel_korper_gewicht(Integer.parseInt(gewichtwunsch));
        ziele.setTrain_ziel_pos1(pos1);
        ziele.setTrain_ziel_pos2(pos2);
        ziele.setTrain_ziel_pos3(pos3);
        ziele.setTrain_ziel_soll_dauer(Integer.parseInt(dauer));
        ziele.setTrain_ziel_soll_geschwindigkeit(Integer.parseInt(geschw));
        ziele.setTrain_ziel_soll_gewicht(Integer.parseInt(gewicht));
        long id = db.createTrain_ziel(ziele);
        if(id > -1) {
            Intent i = new Intent(this, TrainZiel.class);
            startActivity(i);
            Toast.makeText(this,"Neue Pumper-Ziele erstellt !",Toast.LENGTH_LONG).show();
            db.closeDB();
        }

    }

    public void updateTarget(View view) {

        String gewicht = tvSollGewicht.getText().toString();
        String dauer = tvSollDauer.getText().toString();
        String geschw=tvSollGeschw.getText().toString();
        String gewichtwunsch=tvGewichtwunsch.getText().toString();
        String pos1=tvPos1.getText().toString();
        String pos2=tvPos2.getText().toString();
        String pos3=tvPos3.getText().toString();

        long id =db.getTrainZielID(getStationID());

        ModelTrain_ziel ziel =(ModelTrain_ziel) db.getTrain_ziel(id);

        ziel.setTrain_ziel_benutzer_id(getBenutzerID());
        ziel.setTrain_ziel_station_id(getStationID());

        if(TextUtils.isEmpty(gewichtwunsch)){
            ziel.setTrain_ziel_korper_gewicht(ziel.getTrain_ziel_korper_gewicht());
        }else ziel.setTrain_ziel_korper_gewicht(Integer.parseInt(gewichtwunsch));

        if (TextUtils.isEmpty(gewicht)){
            ziel.setTrain_ziel_soll_gewicht(ziel.getTrain_ziel_soll_gewicht());
        }else ziel.setTrain_ziel_soll_gewicht(Integer.parseInt(gewicht));

        if(TextUtils.isEmpty(geschw)){
            ziel.setTrain_ziel_soll_geschwindigkeit(ziel.getTrain_ziel_soll_geschwindigkeit());
        }else ziel.setTrain_ziel_soll_geschwindigkeit(Integer.parseInt(geschw));

        if(TextUtils.isEmpty(pos1)){
            ziel.setTrain_ziel_pos1(ziel.getTrain_ziel_pos1());
        }else ziel.setTrain_ziel_pos1(pos1);

        if (TextUtils.isEmpty(pos2)){
            ziel.setTrain_ziel_pos2(ziel.getTrain_ziel_pos2());
        }else ziel.setTrain_ziel_pos2(pos2);

        if(TextUtils.isEmpty(pos3)){
            ziel.setTrain_ziel_pos3(ziel.getTrain_ziel_pos3());
        }else ziel.setTrain_ziel_pos3(pos3);

        if(TextUtils.isEmpty(dauer)){
            ziel.setTrain_ziel_soll_dauer(ziel.getTrain_ziel_soll_dauer());
        }else
        ziel.setTrain_ziel_soll_dauer(Integer.parseInt(dauer));


        int count = db.updateTrain_ziel(ziel);
        Intent i = new Intent(this, TrainZiel.class);
        if(count <= 0) {
            startActivity(i);
            Toast.makeText(this,"Lappen haben halt keine Ziele :)",Toast.LENGTH_LONG).show();
            db.closeDB();
        }else startActivity(i);
        Toast.makeText(this,"Ziele Aktualisiert.",Toast.LENGTH_LONG).show();
            db.closeDB();
    }


    private int getStationID(){
        String station =spinnerStation.getSelectedItem().toString();
        ModelStation s = (ModelStation) db.getStation(station);
        int id =s.getStation_id();
        return id;
    }
    private int getBenutzerID(){
        String s = spinnerUser.getSelectedItem().toString();
        ModelBenutzer m = (ModelBenutzer) db.getBenutzer(s);
        int id = m.getBenutzer_id();
        return id;
    }


    private void getUserSpinner(){
        String [] array = db.getAllBenutzerNamen();
        spinnerUser =(Spinner)findViewById(R.id.spinner_User);
        adapterUser=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,array);
        spinnerUser.setAdapter(adapterUser);
    }

    private void getStationSpinner(){
        String [] array = db.getAllStationNamen();
            spinnerStation=(Spinner)findViewById(R.id.spinner_Station);
            adapterTrain=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,array);
            spinnerStation.setAdapter(adapterTrain);
        }



    }
