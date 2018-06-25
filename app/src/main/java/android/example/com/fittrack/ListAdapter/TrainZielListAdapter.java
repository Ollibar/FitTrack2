package android.example.com.fittrack.ListAdapter;

import android.content.Context;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTrain_ziel;
import android.example.com.fittrack.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TrainZielListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelTrain_ziel> train_zielArrayList;
    private DatabaseHelper db;

    public TrainZielListAdapter(Context context, ArrayList<ModelTrain_ziel> train_ziel) {
        this.context=context;
        this.train_zielArrayList=train_ziel;
    }

    @Override
    public int getCount() {
        return train_zielArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return train_zielArrayList.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


     public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inf =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View row =inf.inflate( R.layout.list_train_ziel_item,parent,false);

        TextView zielBenutzer= (TextView)row.findViewById(R.id.tV_zielBenutzerName);
        TextView zielStation= (TextView)row.findViewById(R.id.tV_zielStationName);
        TextView zielGewicht= (TextView)row.findViewById(R.id.tV_list_ziel_Gewicht);
        TextView zielPos1= (TextView)row.findViewById(R.id.tV_zielPositionEins);
        TextView zielPos2= (TextView)row.findViewById(R.id.tV_zielPositionZwei);
        TextView zielPos3= (TextView)row.findViewById(R.id.tV_zielTrainPosDrei);
        TextView zielWunschgewicht= (TextView)row.findViewById(R.id.tV_ziel_body_weight);
        TextView zielDauer= (TextView)row.findViewById(R.id.tV_list_ziel_Dauer);
        TextView zielGeschw= (TextView)row.findViewById(R.id.tV_list_ziel_Gesw);

        long id = train_zielArrayList.get(position).getTrain_ziel_benutzer_id();
        ModelBenutzer m =db.getBenutzer(id);
        zielBenutzer.setText("Benutzer: "+m.getBenutzer_name());

        long idstation = train_zielArrayList.get(position).getTrain_ziel_station_id();
        ModelStation s = db.getStation(idstation);
        zielStation.setText("Gerät: "+s.getStation_name());

        zielGewicht.setText("Sollgewicht: "+train_zielArrayList.get(position).getTrain_ziel_soll_gewicht());
        zielPos1.setText("Position 1: "+train_zielArrayList.get(position).getTrain_ziel_pos1());
        zielPos2.setText("Position 2: "+train_zielArrayList.get(position).getTrain_ziel_pos2());
        zielPos3.setText("Position 3: "+train_zielArrayList.get(position).getTrain_ziel_pos3());
        zielWunschgewicht.setText("Wunschgewicht: "+train_zielArrayList.get(position).getTrain_ziel_korper_gewicht());
        zielDauer.setText("Dauer: "+train_zielArrayList.get(position).getTrain_ziel_soll_dauer());
        zielGeschw.setText("Geschwindigkeit: "+train_zielArrayList.get(position).getTrain_ziel_soll_geschwindigkeit());


         return row;
    }
}
