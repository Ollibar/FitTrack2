package android.example.com.fittrack.ListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.example.com.fittrack.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//push  

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


        LayoutInflater inf =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        @SuppressLint("ViewHolder") View row =inf.inflate( R.layout.list_station_item,parent,false);
        TextView txDatum =(TextView) row.findViewById( R.id.list_train_item_Datum );
        TextView txStationName =(TextView) row.findViewById( R.id.list_train_item_station_name);
        TextView txDauer =(TextView) row.findViewById( R.id.list_train_item_dauer );
        TextView txKcal =(TextView) row.findViewById( R.id.list_train_item_kcal );



        txDatum.setText(trainingArrayList.get( position ).getTraining_datum() );
        int stationName = trainingArrayList.get( position ).getTraining_station_id();

        txStationName.setText( stationName );
        txDauer.setText( trainingArrayList.get( position ).getTraining_dauer() );
        txKcal.setText( trainingArrayList.get( position ).getTraining_kcal() );











        return row;
    }
}
