// good tutorial: http://tausiq.wordpress.com/2012/12/12/android-custom-adapter-listview-with-listfragment-and-loadermanager-inside-fragmentactivity/

package com.ciheul.bigbike;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.ciheul.bigbike.loader.ShelterListLoader;
import com.ciheul.bigbike.loader.ShelterModel;

public class ShelterListFragment extends SherlockListFragment implements
        LoaderManager.LoaderCallbacks<List<ShelterModel>> {
    
    private ShelterListAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // this is really important in order to save the state across screen
        // configuration changes for example
        setRetainInstance(true);

        // you only need to instantiate these the first time your fragment is
        // created; then, the method above will do the rest
        if (adapter == null) {
            adapter = new ShelterListAdapter(getActivity(), android.R.layout.simple_list_item_1);
        }
        
        setListAdapter(adapter);
        setEmptyText("No shelter information");

        // initiate the loader to do the background work
        getLoaderManager().initLoader(0, null, this);

        registerForContextMenu(getListView());
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        setUserVisibleHint(true);
//    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Insert desired behavior here.
        Log.d("BigBike", "Item clicked: " + id);
    }

    /**********************/
    /** LOADER CALLBACKS **/
    /**********************/

    @Override
    public Loader<List<ShelterModel>> onCreateLoader(int loaderId, Bundle bundle) {
        return new ShelterListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<ShelterModel>> loader, List<ShelterModel> data) {
        adapter.setData(data);

        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ShelterModel>> loader) {
        adapter.setData(null);
    }

}