package android.example.com.fittrack.ListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelSatz;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.example.com.fittrack.R;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//push

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TrainingListAdapter extends BaseAdapter {




    private Context context;
    private ArrayList<ModelTraining> trainingArrayList;

    public TrainingListAdapter(Context context, ArrayList<ModelTraining> stations) {
        this.context=context;
        this.trainingArrayList=stations;

    }

    @Override
    public int getCount() {
        return trainingArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return trainingArrayList.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DatabaseHelper db =new DatabaseHelper( context );


        LayoutInflater inf =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        @SuppressLint("ViewHolder") View row =inf.inflate( R.layout.list_train_item,parent,false);
        TextView txDatum =(TextView) row.findViewById( R.id.list_train_item_Datum );
        TextView txStationName =(TextView) row.findViewById( R.id.list_train_item_station_name);
        TextView txDauer =(TextView) row.findViewById( R.id.list_train_item_dauer );
        TextView txKcal =(TextView) row.findViewById( R.id.list_train_item_kcal );
        TextView txGeschwindigkeit =(TextView) row.findViewById( R.id.list_train_item_geschwindigkeit);



        txDatum.setText(trainingArrayList.get( position ).getTraining_datum() );
        ModelStation station= db.getStation(trainingArrayList.get( position ).getTraining_station_id() );

        Toast.makeText(context,station.getStation_name(), Toast.LENGTH_LONG).show();


     txStationName.setText(station.getStation_name());


       if(trainingArrayList.get( position ).getTraining_dauer()>0){
           txDauer.setText( trainingArrayList.get( position ).getTraining_dauer()+" Min." );
       }
        if(trainingArrayList.get( position ).getTraining_geschwindigkeit()>0){
            txGeschwindigkeit.setText( trainingArrayList.get( position ).getTraining_geschwindigkeit()+" Km/h" );
        }
        if(trainingArrayList.get( position ).getTraining_kcal()>0){
            txKcal.setText( trainingArrayList.get( position ).getTraining_kcal()+" Kcal" );
        }
        /* Funktioniert leider nicht
        ListView lv =new ListView( context );
        ArrayList<ModelSatz> satzList = db.getAllSatzByTrainindID(trainingArrayList.get( position ).getTraining_id());
        lv = lv.findViewById(R.id.list_train_item_ListView);
        SatzListAdapter sla =new SatzListAdapter( context,satzList );

        lv.setAdapter( sla );
        */
        return row;
    }

    private void erzeugeSatzliste() {


    }
}
