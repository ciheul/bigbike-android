package com.ciheul.bigbike;

import java.util.ArrayList;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
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
import com.ciheul.bigbike.loader.ShelterModel;

public class MapFragment extends SherlockFragment {

    private BigBikeApplication app;
    private ResourceProxy resourceProxy;
    private MapView myOpenMapView;
    private MapController myMapController;

    private ItemizedOverlay<OverlayItem> itemizedOverlay;

    private final static double lonCenter = 107.61302;
    private final static double latCenter = -6.89388;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (BigBikeApplication) getActivity().getApplication();
    }

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
        myMapController.setZoom(15);

        myMapController.setCenter(new GeoPoint(latCenter, lonCenter));

        ArrayList<OverlayItem> listMarker = getListShelterMarker();

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

    private ArrayList<OverlayItem> getListShelterMarker() {
        ArrayList<ShelterModel> listShelter = (ArrayList<ShelterModel>) app.getShelters();

        ArrayList<OverlayItem> listMarker = new ArrayList<OverlayItem>();
        for (ShelterModel shelter : listShelter) {
            listMarker.add(new OverlayItem("Bandung", "Description", new GeoPoint(shelter.getLatitude(), shelter
                    .getLongitude())));
        }

        return listMarker;
    }

}
