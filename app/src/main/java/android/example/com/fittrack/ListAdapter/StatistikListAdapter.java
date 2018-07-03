package android.example.com.fittrack.ListAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.example.com.fittrack.FitDB.DatabaseHelper;
import android.example.com.fittrack.FitDB.ModelStation;
import android.example.com.fittrack.FitDB.ModelTraining;
import android.example.com.fittrack.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StatistikListAdapter extends BaseAdapter {

    private Context context;
    private Cursor c;
    public StatistikListAdapter(Context context, Cursor cursor) {
        this.context=context;
        this.c =cursor;

    }

    @Override
    public int getCount() {
        return c.getCount();
    }

 @Override
    public Object getItem(int position) {
        Object o =null;
        return o;

 }
    @Override
    public long getItemId(int position) {
            long test =1;
         return test;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DatabaseHelper db =new DatabaseHelper( context );


        LayoutInflater inf =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        @SuppressLint("ViewHolder") View row =inf.inflate( R.layout.list_stat_item,parent,false);



        TextView txDatum =(TextView) row.findViewById( R.id.stat_datum );
        TextView txPumperPower =(TextView) row.findViewById( R.id.stat_pump);
        TextView txKardio =(TextView) row.findViewById( R.id.stat_kardio );
        c.moveToPosition( position );

        String s = c.getString(c.getColumnIndex( "training_datum" ));
        txDatum.setText( s);
        txKardio.setText(String.valueOf( c.getInt( c.getColumnIndex( "kcal" ) ) )+" kcal");
        txPumperPower.setText( String.valueOf( c.getInt( c.getColumnIndex( "pumpPower"  ) ) ) + " PumpPower");


        return row;
    }

}
