// good tutorial: http://tausiq.wordpress.com/2012/12/12/android-custom-adapter-listview-with-listfragment-and-loadermanager-inside-fragmentactivity/

package com.ciheul.bigbike.activity;

import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.ciheul.bigbike.data.BigBikeContentProvider;
import com.ciheul.bigbike.data.ShelterListLoader;
import com.ciheul.bigbike.data.ShelterModel;

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
        super.onListItemClick(l, v, position, id);
        
        Intent shelterInfoIntent = new Intent(getActivity(), ShelterInfoActivity.class);
        Uri shelterUri = Uri.parse(BigBikeContentProvider.SHELTER_CONTENT_URI + "/" + (id+1));
        shelterInfoIntent.putExtra(BigBikeContentProvider.SHELTER_CONTENT_ITEM_TYPE, shelterUri);
        startActivity(shelterInfoIntent);
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