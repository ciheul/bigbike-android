package com.ciheul.bigbike.activity;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ciheul.bigbike.R;
import com.ciheul.bigbike.data.ShelterModel;

public class ShelterListAdapter extends ArrayAdapter<ShelterModel> {

    LayoutInflater inflater;

    public ShelterListAdapter(Context context, int resource) {
        super(context, resource);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ListViewHolder holder;

        if (view == null) {            
            view = inflater.inflate(R.layout.shelter_row, parent, false);

            holder = new ListViewHolder();
            holder.shelterName = (TextView) view.findViewById(R.id.shelter_row_name);
            holder.capacity = (TextView) view.findViewById(R.id.shelter_row_capacity);
            holder.longitude = (TextView) view.findViewById(R.id.shelter_row_longitude);
            holder.latitude = (TextView) view.findViewById(R.id.shelter_row_latitude);
            view.setTag(holder);
        } else {
            holder = (ListViewHolder) view.getTag();
        }

        String shelterName = getItem(position).getName();
        if (shelterName != null) {
            holder.shelterName.setText(shelterName);
        }
        
        String capacity = String.valueOf(getItem(position).getCapacity());
        if (capacity != null) {
            holder.capacity.setText(capacity);
        }

        String longitude = String.valueOf(getItem(position).getLongitude());
        if (longitude != null) {
            holder.longitude.setText(longitude);
        }
        
        String latitude = String.valueOf(getItem(position).getLatitude());
        if (latitude != null) {
            holder.latitude.setText(latitude);
        }
        
        return view;
    }

    public void setData(List<ShelterModel> data) {
        clear();
        if (data != null) {
            for (ShelterModel appEntry : data) {
                add(appEntry);
            }
        }
    }
    
    public static class ListViewHolder {
        public TextView shelterName;
        public TextView capacity;
        public TextView longitude;
        public TextView latitude;
    }
}
