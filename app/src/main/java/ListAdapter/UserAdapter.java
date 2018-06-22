package ListAdapter;

import android.content.Context;
import android.example.com.fittrack.FitDB.ModelBenutzer;
import android.example.com.fittrack.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelBenutzer> userArrayList;


    public UserAdapter(Context context, ArrayList<ModelBenutzer> userArrayList) {
        this.context=context;
        this.userArrayList=userArrayList;

    }
    @Override
    public int getCount() {
        return userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userArrayList.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = mInflater.inflate(R.layout.list_user_item,parent,false);
        TextView userID = (TextView)v.findViewById(R.id.tV_userID);
        TextView name = (TextView)v.findViewById(R.id.tV_Name);
        TextView alter = (TextView)v.findViewById(R.id.tV_Alter);
        TextView gewicht =(TextView)v.findViewById(R.id.tV_Gewicht);

        String x = Integer.toString(position);
        userID.setText(x);
        name.setText(userArrayList.get(position).getBenutzer_name());
        alter.setText(userArrayList.get(position).getBenutzer_alter());
        gewicht.setText(userArrayList.get(position).getBenutzer_gewicht());


        return v;
    }
}
