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

public class StationListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelStation> stationArrayList;

    public StationListAdapter(Context context, ArrayList<ModelStation> stations) {
        this.context = context;
        this.stationArrayList = stations;
    }

    @Override
    public int getCount() {
        return stationArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return stationArrayList.get( position );
    }

    @Override
    public long getItemId(int position) {

        return stationArrayList.get( position ).getStation_id();
    }

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
