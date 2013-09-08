package com.ciheul.bigbike;

import java.util.ArrayList;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockActivity;

public class MainActivity extends SherlockActivity implements ActionBar.TabListener {

    private TextView tabSelected;

    private MapView myOpenMapView;
    private MapController myMapController;

    private ItemizedOverlay<OverlayItem> itemizedOverlay;

    private ResourceProxy resourceProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_navigation);

        tabSelected = (TextView) findViewById(R.id.text);

        setUpTabs();
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        switch (tab.getPosition()) {
        case 0:
            showMap();
            break;
        case 1:
            myOpenMapView.setVisibility(View.INVISIBLE);
            tabSelected.setVisibility(View.VISIBLE);
            
            Toast.makeText(this, tab.getText(), Toast.LENGTH_SHORT).show();
            tabSelected.setText("Selected: " + tab.getText());
            break;
        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

    private void setUpTabs() {
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // create map tab
        ActionBar.Tab tabMap = getSupportActionBar().newTab();
        tabMap.setText("Map");
        tabMap.setTabListener(this);
        getSupportActionBar().addTab(tabMap);

        // create map tab
        ActionBar.Tab tabList = getSupportActionBar().newTab();
        tabList.setText("List");
        tabList.setTabListener(this);
        getSupportActionBar().addTab(tabList);
    }

    private void showMap() {
        resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());

        myOpenMapView = (MapView) findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setTileSource(TileSourceFactory.MAPQUESTOSM);

        myMapController = myOpenMapView.getController();
        myMapController.setZoom(14);

        double lon = 107.62207;
        double lat = -6.91552;

        IGeoPoint point = new GeoPoint(lat, lon); // lat lon and not inverse
        myMapController.setCenter(point);

        ArrayList<OverlayItem> listMarker = new ArrayList<OverlayItem>();
        listMarker.add(new OverlayItem("Bandung", "Description", new GeoPoint(lat, lon)));

        itemizedOverlay = new ItemizedIconOverlay<OverlayItem>(listMarker,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                    @Override
                    public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                        Toast.makeText(getApplicationContext(), "single tap", Toast.LENGTH_LONG).show();
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Toast.makeText(getApplicationContext(), "long press", Toast.LENGTH_LONG).show();
                        return true;
                    }
                }, resourceProxy);

        myOpenMapView.getOverlays().add(itemizedOverlay);
        myOpenMapView.invalidate();
        
        myOpenMapView.setVisibility(View.VISIBLE);
        tabSelected.setVisibility(View.INVISIBLE);
    }
}
