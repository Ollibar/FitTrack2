// angelehnt an http://www.vogella.com/tutorials/AndroidListView/article.html#adapterown

package android.example.com.fittrack.ListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adabpterklasse für die GeräteListe
 */
public class StationListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelStation> stationArrayList;

    public StationListAdapter(Context context, ArrayList<ModelStation> stations) {
        this.context = context;
        this.stationArrayList = stations;
    }

    /**
     *
     * @return gibt die größe des ListArrays zurück
     */
    @Override
    public int getCount() {
        return stationArrayList.size();
    }

    /**
     *
     * @param position
     * @return gibt item an der übergebenen Position zurück
     */
    @Override
    public Object getItem(int position) {
        return stationArrayList.get( position );
    }

    /**
     *
     * @param position
     * @return gibt die station ID zurück
     */
    @Override
    public long getItemId(int position) {

        return stationArrayList.get( position ).getStation_id();
    }

    /**
     *  übergibt die daten an die Felder des ListView Items
     * @param position
     * @param convertView
     * @param parent
     * @return befülltes layout
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inf = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        @SuppressLint("ViewHolder") View row = inf.inflate( R.layout.list_station_item, parent, false );

        TextView txName = (TextView) row.findViewById( R.id.tV_station_name );
        TextView txTyp = (TextView) row.findViewById( R.id.tV_station_typ );
        String x = Integer.toString( position );

        txName.setText( stationArrayList.get( position ).getStation_name() );
        String stationTyp = "k.A.";
        if (stationArrayList.get( position ).getStation_typ() == 1) {
            stationTyp = "Kraft";
        } else if (stationArrayList.get( position ).getStation_typ() == 2) {
            stationTyp = "Kardio";
        }
        txTyp.setText( stationTyp );


        return row;
    }
}
