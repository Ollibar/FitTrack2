package ListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.example.com.fittrack.FitDB.ModelSatz;
import android.example.com.fittrack.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SatzListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelSatz> satzArrayList;

    public SatzListAdapter(Context context, ArrayList<ModelSatz> satz) {
        this.context=context;
        this.satzArrayList=satz;
    }

    @Override
    public int getCount() {
        return satzArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return satzArrayList.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inf =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        @SuppressLint("ViewHolder") View row =inf.inflate( R.layout.list_satz_item,parent,false);
        TextView txNummer =(TextView) row.findViewById( R.id.tv_list_satz_nummer );
        TextView txGewichte =(TextView) row.findViewById( R.id.tv_list_satz_gewicht );
        TextView txWiederholung =(TextView) row.findViewById( R.id.tv_list_satz_wiederholung );


        txNummer.setText( satzArrayList.get( position ).getSatz_nr() );
        txGewichte.setText( satzArrayList.get( position ).getSatz_gewicht()+ " Kg" );
        txWiederholung.setText( satzArrayList.get(position).getSatz_wiederholung() +" X" );






        return row;
    }
}

