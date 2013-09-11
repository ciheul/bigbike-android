package com.ciheul.bigbike;

import java.util.ArrayList;
import java.util.List;

import com.ciheul.bigbike.data.BigBikeContentProvider;
import com.ciheul.bigbike.data.BigBikeDatabaseHelper;
import com.ciheul.bigbike.loader.ShelterModel;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class BigBikeApplication extends Application {
    
    List<ShelterModel> shelters;
    
    @Override
    public void onCreate() {
        super.onCreate();
        insertSampleData();
        shelters = queryShelters();        
    }

    private List<ShelterModel> queryShelters() {
        List<ShelterModel> shelters = new ArrayList<ShelterModel>();

        String[] projection = { BigBikeDatabaseHelper.COL_SHELTER_NAME, BigBikeDatabaseHelper.COL_CAPACITY,
                BigBikeDatabaseHelper.COL_LON, BigBikeDatabaseHelper.COL_LAT };
        Cursor cursor = getContentResolver().query(BigBikeContentProvider.SHELTER_CONTENT_URI, projection, null, null,
                null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                ShelterModel shelter = new ShelterModel();
                shelter.setName(cursor.getString(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_SHELTER_NAME)));
                shelter.setCapacity(cursor.getInt(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_CAPACITY)));
                shelter.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_LON)));
                shelter.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(BigBikeDatabaseHelper.COL_LAT)));

                shelters.add(shelter);
            }
        }

        return shelters;
    }

    public List<ShelterModel> getShelters() {
        return shelters;
    }    
    
    /********************/
    /** DEBUGGING ONLY **/
    /********************/

    private void insertSampleData() {
        List<ContentValues> listValues = new ArrayList<ContentValues>();

        Log.d("BigBike", "insertSampleDat");
        ContentValues values = new ContentValues();
        values.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Kafe Halaman");
        values.put(BigBikeDatabaseHelper.COL_CAPACITY, 10);
        values.put(BigBikeDatabaseHelper.COL_LON, 107.61117);
        values.put(BigBikeDatabaseHelper.COL_LAT, -6.88510);
        values.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values);

        ContentValues values2 = new ContentValues();
        values2.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Fly Over Pasupati");
        values2.put(BigBikeDatabaseHelper.COL_CAPACITY, 40);
        values2.put(BigBikeDatabaseHelper.COL_LON, 107.61246);
        values2.put(BigBikeDatabaseHelper.COL_LAT, -6.89848);
        values2.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values2);

        ContentValues values3 = new ContentValues();
        values3.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Kebun Binatang Tamansari");
        values3.put(BigBikeDatabaseHelper.COL_CAPACITY, 30);
        values3.put(BigBikeDatabaseHelper.COL_LON, 107.60812);
        values3.put(BigBikeDatabaseHelper.COL_LAT, -6.89341);
        values3.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values3);

        ContentValues values4 = new ContentValues();
        values4.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Teuku Umar");
        values4.put(BigBikeDatabaseHelper.COL_CAPACITY, 40);
        values4.put(BigBikeDatabaseHelper.COL_LON, 107.61366);
        values4.put(BigBikeDatabaseHelper.COL_LAT, -6.89124);
        values4.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values4);

        ContentValues values5 = new ContentValues();
        values5.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Monumen Pahlawan");
        values5.put(BigBikeDatabaseHelper.COL_CAPACITY, 30);
        values5.put(BigBikeDatabaseHelper.COL_LON, 107.61829);
        values5.put(BigBikeDatabaseHelper.COL_LAT, -6.89286);
        values5.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values5);

        ContentValues values6 = new ContentValues();
        values6.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Gasibu");
        values6.put(BigBikeDatabaseHelper.COL_CAPACITY, 40);
        values6.put(BigBikeDatabaseHelper.COL_LON, 107.61859);
        values6.put(BigBikeDatabaseHelper.COL_LAT, -6.90010);
        values6.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values6);

        ContentValues values7 = new ContentValues();
        values7.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Taman Flexi Dago");
        values7.put(BigBikeDatabaseHelper.COL_CAPACITY, 40);
        values7.put(BigBikeDatabaseHelper.COL_LON, 107.61072);
        values7.put(BigBikeDatabaseHelper.COL_LAT, -6.90391);
        values7.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values7);

        ContentValues values8 = new ContentValues();
        values8.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Cibeunying Park");
        values8.put(BigBikeDatabaseHelper.COL_CAPACITY, 40);
        values8.put(BigBikeDatabaseHelper.COL_LON, 107.62437);
        values8.put(BigBikeDatabaseHelper.COL_LAT, -6.90523);
        values8.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values8);
        
        ContentValues values9 = new ContentValues();
        values9.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Taman Pramuka");
        values9.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values9.put(BigBikeDatabaseHelper.COL_LON, 107.62688);
        values9.put(BigBikeDatabaseHelper.COL_LAT, -6.91028);
        values9.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values9);
                
        ContentValues values10 = new ContentValues();
        values10.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Kantor Pos Indonesia");
        values10.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values10.put(BigBikeDatabaseHelper.COL_LON, 107.61739);
        values10.put(BigBikeDatabaseHelper.COL_LAT, -6.90694);
        values10.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values10);

        ContentValues values11 = new ContentValues();
        values11.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Grapari Dago");
        values11.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values11.put(BigBikeDatabaseHelper.COL_LON, 107.61542);
        values11.put(BigBikeDatabaseHelper.COL_LAT, -6.88273);
        values11.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values11);
        
        ContentValues values12 = new ContentValues();
        values12.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Indomaret Tubagus Ismail Raya");
        values12.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values12.put(BigBikeDatabaseHelper.COL_LON, 107.61872);
        values12.put(BigBikeDatabaseHelper.COL_LAT, -6.88516);
        values12.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values12);
        
        ContentValues values13 = new ContentValues();
        values13.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "RM. Ampera Tubagus Ismail Raya");
        values13.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values13.put(BigBikeDatabaseHelper.COL_LON, 107.62261);
        values13.put(BigBikeDatabaseHelper.COL_LAT, -6.88591);
        values13.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values13);
        
        
        ContentValues values14 = new ContentValues();
        values14.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Elok Market Cigadung");
        values14.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values14.put(BigBikeDatabaseHelper.COL_LON, 107.62490);
        values14.put(BigBikeDatabaseHelper.COL_LAT, -6.88036);
        values14.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values14);
        
        ContentValues values15 = new ContentValues();
        values15.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Lapangan Tenis Cigadung");
        values15.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values15.put(BigBikeDatabaseHelper.COL_LON, 107.62225);
        values15.put(BigBikeDatabaseHelper.COL_LAT, -6.87211);
        values15.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values15);
        
        ContentValues values16 = new ContentValues();
        values16.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Borma Dago");
        values16.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values16.put(BigBikeDatabaseHelper.COL_LON, 107.61792);
        values16.put(BigBikeDatabaseHelper.COL_LAT, -6.87695);
        values16.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values16);
        
        ContentValues values17 = new ContentValues();
        values17.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Dago Pojok");
        values17.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values17.put(BigBikeDatabaseHelper.COL_LON, 107.61899);
        values17.put(BigBikeDatabaseHelper.COL_LAT, -6.87419);
        values17.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values17);
        
        ContentValues values18 = new ContentValues();
        values18.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Taman Budaya Jawa Barat");
        values18.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values18.put(BigBikeDatabaseHelper.COL_LON, 107.62009);
        values18.put(BigBikeDatabaseHelper.COL_LAT, -6.87018);
        values18.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values18);
        
        ContentValues values19 = new ContentValues();
        values19.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Terminal Dago");
        values19.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values19.put(BigBikeDatabaseHelper.COL_LON, 107.62114);
        values19.put(BigBikeDatabaseHelper.COL_LAT, -6.86689);
        values19.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values19);
        
        ContentValues values20 = new ContentValues();
        values20.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Dago Atas 1");
        values20.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values20.put(BigBikeDatabaseHelper.COL_LON, 107.62549);
        values20.put(BigBikeDatabaseHelper.COL_LAT, -6.86316);
        values20.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values20);
                
        ContentValues values21 = new ContentValues();
        values21.put(BigBikeDatabaseHelper.COL_SHELTER_NAME, "Cibeunying Kolot");
        values21.put(BigBikeDatabaseHelper.COL_CAPACITY, 20);
        values21.put(BigBikeDatabaseHelper.COL_LON, 107.62647);
        values21.put(BigBikeDatabaseHelper.COL_LAT, -6.88660);
        values21.put(BigBikeDatabaseHelper.COL_UPDATED_AT, "2013-09-10T15:03:31.511158");
        listValues.add(values21);
        
        
        for (ContentValues value : listValues) {
            getContentResolver().insert(BigBikeContentProvider.SHELTER_CONTENT_URI, value);
        }
    }
}
