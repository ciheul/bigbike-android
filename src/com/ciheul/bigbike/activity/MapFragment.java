package com.ciheul.bigbike.activity;

import java.util.ArrayList;

import org.osmdroid.bonuspack.overlays.ExtendedOverlayItem;
import org.osmdroid.bonuspack.overlays.ItemizedOverlayWithBubble;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.ciheul.bigbike.BigBikeApplication;
import com.ciheul.bigbike.R;
import com.ciheul.bigbike.data.ShelterModel;
import com.ciheul.bigbike.extend.ViaPointInfoWindow;

public class MapFragment extends SherlockFragment {

    private BigBikeApplication app;
    // private ResourceProxy resourceProxy;
    private MapView myOpenMapView;
    private MapController myMapController;

    // private ItemizedOverlay<OverlayItem> itemizedOverlay;
    private ItemizedOverlayWithBubble<ExtendedOverlayItem> itemizedOverlayBubble;

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

        // resourceProxy = new DefaultResourceProxyImpl(getActivity().getApplicationContext());
        myOpenMapView = (MapView) getActivity().findViewById(R.id.openmapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setTileSource(TileSourceFactory.MAPQUESTOSM);

        myMapController = myOpenMapView.getController();
        myMapController.setZoom(15);
        myMapController.setCenter(new GeoPoint(latCenter, lonCenter));

        // ArrayList<OverlayItem> listMarker = getListShelterMarker();
        //
        // itemizedOverlay = new ItemizedIconOverlay<OverlayItem>(listMarker,
        // new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
        // @Override
        // public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
        // Toast.makeText(getActivity(), "single tap", Toast.LENGTH_LONG).show();
        // return true;
        // }
        //
        // @Override
        // public boolean onItemLongPress(final int index, final OverlayItem item) {
        // Toast.makeText(getActivity(), "long press", Toast.LENGTH_LONG).show();
        // return true;
        // }
        // }, resourceProxy);
        //
        // myOpenMapView.getOverlays().add(itemizedOverlay);

        ArrayList<ExtendedOverlayItem> listMarker = getListShelterMarker();
        itemizedOverlayBubble = new ItemizedOverlayWithBubble<ExtendedOverlayItem>(getActivity(), listMarker,
                myOpenMapView, new ViaPointInfoWindow(getActivity(), R.layout.bonuspack_bubble, myOpenMapView));
        myOpenMapView.getOverlays().add(itemizedOverlayBubble);

        myOpenMapView.invalidate();

        myOpenMapView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }

    private ArrayList<ExtendedOverlayItem> getListShelterMarker() {
        ArrayList<ShelterModel> listShelter = (ArrayList<ShelterModel>) app.getShelters();

        ArrayList<ExtendedOverlayItem> listMarker = new ArrayList<ExtendedOverlayItem>();
        for (ShelterModel shelter : listShelter) {
            listMarker.add(new ExtendedOverlayItem(shelter.getName(), "Kapasitas: " + shelter.getCapacity(),
                    new GeoPoint(shelter.getLatitude(), shelter.getLongitude()), null));            
        }

        return listMarker;
    }

}
