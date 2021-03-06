package android.example.com.fittrack.ListAdapter;


import android.content.Context;
import android.example.com.fittrack.FitDB.ModelBenutzer;
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

public class BenutzerListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ModelBenutzer> benutzerArrayList;
    private LayoutInflater inf;

    /**
     * wir übergeben eine activity und ein arraylist im constructor des adapters
     * anschließen können wir ein layout mit inhalt aus der arraylist befüllen
     * mit hilfe eines LayoutInflater
     * @param context
     * @param benutzer
     */
    public BenutzerListAdapter(Context context, ArrayList<ModelBenutzer> benutzer) {
        this.context = context;
        this.benutzerArrayList = benutzer;
        inf = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    @Override
    public int getCount() {
        return benutzerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return benutzerArrayList.get( position );
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * befüllen die layout datei mit den inhalt aus dem arraylist
     * @param position der daten im arraylist
     * @param convertView
     * @param parent
     * @return befüllte layout
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = inf.inflate( R.layout.list_benutzer_item, parent, false );
        TextView txID = (TextView) row.findViewById( R.id.tV_list_benutzer_ID );
        TextView txName = (TextView) row.findViewById( R.id.tV_list_benutzer_name );
        TextView txAlter = (TextView) row.findViewById( R.id.tV_list_benutzer_alter );
        TextView txGewicht = (TextView) row.findViewById( R.id.tV_list_benutzer_gewicht );

        txID.setText( "Nr.: " + Integer.toString( position ) );
        txName.setText( "Name: " + benutzerArrayList.get( position ).getBenutzer_name() );
        txAlter.setText( benutzerArrayList.get( position ).getBenutzer_alter() + " Jahre" );
        txGewicht.setText( benutzerArrayList.get( position ).getBenutzer_gewicht() + " Kg" );


        return row;
    }
}
