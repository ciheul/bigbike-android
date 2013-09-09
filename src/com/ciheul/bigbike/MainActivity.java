package com.ciheul.bigbike;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity implements ActionBar.TabListener {

    // private TextView tabSelected;
    private NonSwipeableViewPager pager;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // remove title bar
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // set up navigation tab
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        pager = (NonSwipeableViewPager) findViewById(R.id.pager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        pager.setAdapter(tabAdapter);

        // tab should be written after ViewPager instantiation
        // create map tab
        ActionBar.Tab tabMap = getSupportActionBar().newTab();
        tabMap.setText("Map").setTabListener(this);
        getSupportActionBar().addTab(tabMap);

        // create list tab
        ActionBar.Tab tabList = getSupportActionBar().newTab();
        tabList.setText("List").setTabListener(this);
        getSupportActionBar().addTab(tabList);
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    
    private class TabAdapter extends FragmentPagerAdapter {

        private static final int TAB_COUNT = 2;

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            switch (arg0) {
            case 0:
                MapFragmentTab mapFragmentTab = new MapFragmentTab();
                return mapFragmentTab;
            case 1:
                ListFragmentTab listFragmentTab = new ListFragmentTab();
                return listFragmentTab;
            }
            return null;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }
    }
}
