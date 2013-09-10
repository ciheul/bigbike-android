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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class MapFragment extends SherlockFragment {

    private ResourceProxy resourceProxy;
    private MapView myOpenMapView;
    private MapController myMapController;

    private ItemizedOverlay<OverlayItem> itemizedOverlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        // get OSM MapView object
        myOpenMapView = (MapView) view.findViewById(R.id.openmapview);
        return view;
    }

    /**
     * Set up the map
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        resourceProxy = new DefaultResourceProxyImpl(getActivity().getApplicationContext());
        myOpenMapView = (MapView) getActivity().findViewById(R.id.openmapview);
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
                        Toast.makeText(getActivity(), "single tap", Toast.LENGTH_LONG).show();
                        return true;
                    }

                    @Override
                    public boolean onItemLongPress(final int index, final OverlayItem item) {
                        Toast.makeText(getActivity(), "long press", Toast.LENGTH_LONG).show();
                        return true;
                    }
                }, resourceProxy);

        myOpenMapView.getOverlays().add(itemizedOverlay);
        myOpenMapView.invalidate();

        myOpenMapView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

}
