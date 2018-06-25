package android.example.com.fittrack.Formulare;

import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class form_target extends AppCompatActivity {


    private Spinner spinnerUser,spinnerTrain;
    private ArrayAdapter<String> adapterUser, adapterTrain;
    private TextView tvSollGewicht,tvSollGeschw,tvSollDauer,tvPos1,tvPos2,tvPos3;


    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_target);

        getUserSpinner();
        getStationSpinner();


    }


    public void insertTarget(View view) {




    }

    public void uodateTarget(View view) {

    }




    public void getUserSpinner(){
        ArrayList<ModelBenutzer> userList = db.getAllBenutzer();
        ArrayList<String> list = new ArrayList<>();

        String s;

        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {

                s = userList.get(i).getBenutzer_name();
                list.add(s);

            }

            spinnerUser = (Spinner) findViewById(R.id.spinner_User);
            adapterUser = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
            spinnerUser.setAdapter(adapterUser);

        }

    }

    public void getStationSpinner(){

        String [] array = db.getAllStationNamen();
            spinnerTrain=(Spinner)findViewById(R.id.spinner_Station);
            adapterTrain=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,array);
            spinnerTrain.setAdapter(adapterTrain);
        }

    }
