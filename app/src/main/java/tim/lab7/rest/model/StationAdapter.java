package tim.lab7.rest.model;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tim.lab7.R;

public class StationAdapter extends ArrayAdapter<SubstanceAll> {

    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    SubstanceAll tempValues = null;
    int i = 0;

    public StationAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public StationAdapter(Context context, int resource, List<SubstanceAll> substanceAllList) {
        super(context, resource, substanceAllList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.station_row, null);
        }
        SubstanceAll substanceAll = getItem(position);
        if(substanceAll!=null){
            TextView substanceId = (TextView) v.findViewById(R.id.substanceId);
            TextView substanceName= (TextView) v.findViewById(R.id.substanceName);
            TextView unit= (TextView) v.findViewById(R.id.unit);
            TextView treshold= (TextView) v.findViewById(R.id.treshold);
            TextView value= (TextView) v.findViewById(R.id.value);
            if(substanceId != null){
                substanceId.setText(substanceAll.getSubstanceId());
            }
            if(substanceName != null){
                substanceName.setText(substanceAll.getSubstanceName());
            }
            if(value != null){
                value.setText(Double.toString(substanceAll.getValue()));
            }
            if(treshold != null){
                treshold.setText(Double.toString(substanceAll.getTreshold()));
            }
            if(unit != null){
                unit.setText(substanceAll.getUnit());
            }
            if(substanceAll.getValue()>substanceAll.getTreshold()){
                value.setTextColor(Color.RED);
            }
            if(substanceAll.getValue()==substanceAll.getTreshold()){
                value.setTextColor(Color.BLUE);
            }
            if(substanceAll.getValue()<substanceAll.getTreshold()){
                value.setTextColor(Color.GREEN);
            }
        }
        return v;
    }
}



