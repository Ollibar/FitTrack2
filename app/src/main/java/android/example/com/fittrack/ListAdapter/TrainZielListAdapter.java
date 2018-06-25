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
        TextView txname =(TextView) row.findViewById( R.id.tV_list_ziel_Dauer );
        TextView txGewGes =(TextView) row.findViewById( R.id.tV_list_ziel_Gewicht );
        TextView txDauer =(TextView) row.findViewById( R.id.tV_list_ziel_Dauer );




        txname.setText( train_zielArrayList.get( position ).getTrain_ziel_station_id() );

        if(train_zielArrayList.get( position ).getTrain_ziel_soll_gewicht()>0){
            txGewGes.setText( train_zielArrayList.get( position ).getTrain_ziel_soll_gewicht()+" Kg" );
            txDauer.setText( "" );
        }
        else{
            txGewGes.setText( train_zielArrayList.get( position ).getTrain_ziel_soll_geschwindigkeit()+" km/h" );
            txDauer.setText( train_zielArrayList.get(position).getTrain_ziel_soll_dauer() +" Min" );
        }









        return row;
    }
}
