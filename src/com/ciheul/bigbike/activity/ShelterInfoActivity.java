package com.ciheul.bigbike.activity;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ciheul.bigbike.R;
import com.ciheul.bigbike.data.BigBikeContentProvider;
import com.ciheul.bigbike.data.BigBikeDatabaseHelper;

public class ShelterInfoActivity extends Activity {

    private TextView shelterName;
    private TextView capacity;
    private TextView coordinates;
    
    private Uri shelterUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelter_info);

        shelterName = (TextView) findViewById(R.id.shelter_info_tv_name);
        capacity = (TextView) findViewById(R.id.shelter_info_tv_capacity);
        coordinates = (TextView) findViewById(R.id.shelter_info_tv_coordinates);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            shelterUri = extras.getParcelable(BigBikeContentProvider.SHELTER_CONTENT_ITEM_TYPE);
            populateShelterInfo(shelterUri);
        }                
    }

    private void populateShelterInfo(Uri shelterUri) {
        String[] projection = { BigBikeDatabaseHelper.COL_SHELTER_NAME, BigBikeDatabaseHelper.COL_CAPACITY,
                BigBikeDatabaseHelper.COL_LON, BigBikeDatabaseHelper.COL_LAT };
        Cursor cursor = getContentResolver().query(shelterUri, projection, null, null, null);
        Log.d("BigBike", shelterUri.toString());
        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            shelterName.setText(cursor.getString(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_SHELTER_NAME)));
            capacity.setText(cursor.getString(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_CAPACITY)));

            double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_LON));
            double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_LAT));
            coordinates.setText("(" + String.valueOf(longitude) + ", " + String.valueOf(latitude) + ")");
        }
        cursor.close();
    }

}
