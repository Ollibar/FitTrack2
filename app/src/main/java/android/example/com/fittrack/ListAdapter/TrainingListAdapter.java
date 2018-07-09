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

import java.util.ArrayList;
/**
 * Adapterhilfsklasse für die Verbindung zum Benutzer ListView
 */
public class TrainingListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelTraining> trainingArrayList;

    public TrainingListAdapter(Context context, ArrayList<ModelTraining> stations) {
        this.context = context;
        this.trainingArrayList = stations;
    }

    /**
     *
     * @return gibt die größe der Liste zurück
     */
    @Override
    public int getCount() {
        return trainingArrayList.size();
    }

    /**
     *
     * @param position
     * @return gibt das Objekt an der Position zurück
     */
    @Override
    public Object getItem(int position) {
        return trainingArrayList.get( position );
    }

    /**
     *
     * @param position
     * @return gibt die Trainings ID an der Position zurück
     */
    @Override
    public long getItemId(int position) {
        return trainingArrayList.get( position ).getTraining_id();
    }

    /**
     * befüllen die layout datei mit den inhalt aus dem arraylist
     * @param position
     * @param convertView
     * @param parent
     * @return befülltes layout
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DatabaseHelper db = new DatabaseHelper( context );


        LayoutInflater inf = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        @SuppressLint("ViewHolder") View row = inf.inflate( R.layout.list_train_item, parent, false );
        TextView txDatum = (TextView) row.findViewById( R.id.list_train_item_Datum );
        TextView txStationName = (TextView) row.findViewById( R.id.list_train_item_station_name );
        TextView txDauer = (TextView) row.findViewById( R.id.list_train_item_dauer );
        TextView txKcal = (TextView) row.findViewById( R.id.list_train_item_kcal );
        TextView txGeschwindigkeit = (TextView) row.findViewById( R.id.list_train_item_geschwindigkeit );


        txDatum.setText( trainingArrayList.get( position ).getTraining_datum() );
        ModelStation station = db.getStation( trainingArrayList.get( position ).getTraining_station_id() );


        txStationName.setText( station.getStation_name() );
        // wenn Kraft gerät dann zeige Gewicht + Wiederholung
        if (station.getStation_typ() == 1) {

            txGeschwindigkeit.setText( trainingArrayList.get( position ).getTraining_wiederholung() + " x" );
            txKcal.setText( trainingArrayList.get( position ).getTraining_gewicht() + " Kg" );

        }
        // wenn Kardio Gerät dann zeige Geschwindigkeit + Dauer + Kcal
        else if (station.getStation_typ() == 2) {
            txDauer.setText( trainingArrayList.get( position ).getTraining_dauer() + " Min." );
            txGeschwindigkeit.setText( trainingArrayList.get( position ).getTraining_geschwindigkeit() + " Km/h" );
            txKcal.setText( trainingArrayList.get( position ).getTraining_kcal() + " Kcal" );
        }

        return row;
    }

}
