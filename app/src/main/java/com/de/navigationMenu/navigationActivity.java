package com.de.navigationMenu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.de.R;
import com.de.baseClass.baseActivity;
import com.de.bookCar.bookCarSearchFragment;
import com.de.bookedCar.bookedCarFragment;
import com.de.carDriverMapping.carDriverMappingFragment;
import com.de.utils.constantString;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Bhadresh on 10/6/17.
 */

public class navigationActivity extends baseActivity implements NavigationView.OnNavigationItemSelectedListener, navigationContract.View {

    private ArrayList<String> HeaderItem;
    private ExpandableListView mDrawerList;
    public static Toolbar mToolbar;
    public static TextView tv_toolbar;
    DrawerLayout drawer;
    private String[] mNavigationDrawerItemTitles;
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    void init() {


        // TODO: 10/6/17 Define the Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        tv_toolbar = (TextView) findViewById(R.id.tv_home);
        tv_toolbar.setText(constantString.home);
        setSupportActionBar(mToolbar);

        // TODO: 10/6/17 Define the Navigation drawer
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        // TODO: 10/6/17 Define the toggle
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_navi_icon, getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        // TODO: 10/6/17 set up Expandablelist in navigaton drawer
        mDrawerList = (ExpandableListView) findViewById(R.id.left_drawer);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.layout__navigation_header, mDrawerList, false);
        mDrawerList.addHeaderView(header, null, false);
        addItemNavigationDrawer();
    }

    // TODO: 10/6/17 Add the value in hashmap and set in expadable list
    private void addItemNavigationDrawer() {

        final LinkedHashMap<String, ArrayList<String>> NaviItem = new LinkedHashMap<>();
        ArrayList<String> NaviChildItem = new ArrayList<>();

        NaviChildItem.add(constantString.bookCar);
        NaviChildItem.add(constantString.listOfBookCar);
        NaviItem.put(constantString.bookingHistory, NaviChildItem);
        NaviChildItem = new ArrayList<>();

        NaviChildItem.add(constantString.preBookingCar);
        NaviChildItem.add(constantString.listOfPreBookingCar);
        NaviItem.put(constantString.preBooking, NaviChildItem);
        NaviChildItem = new ArrayList<>();

        NaviItem.put(constantString.carDriverMapping, NaviChildItem);
        NaviChildItem = new ArrayList<>();

        NaviItem.put(constantString.expense, NaviChildItem);
        NaviChildItem = new ArrayList<>();

        NaviChildItem.add(constantString.paymentManagement);
        NaviItem.put(constantString.account, NaviChildItem);
        NaviChildItem = new ArrayList<>();

        NaviChildItem.add(constantString.driver);
        NaviChildItem.add(constantString.customer);
        NaviItem.put(constantString.cancelRequest, NaviChildItem);
        NaviChildItem = new ArrayList<>();

        NaviItem.put(constantString.logout, NaviChildItem);

        HeaderItem = new ArrayList<String>(NaviItem.keySet());

        NavigationAdapter adapter = new NavigationAdapter(this, NaviItem, getSupportFragmentManager());
        mDrawerList.setAdapter(adapter);

        mToolbar.setVisibility(View.VISIBLE);

        callFragment(new carDriverMappingFragment(), R.id.content_frame, "");

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // TODO: 10/6/17 set the Click of each item of navigation drawer Call fragment from here
        mDrawerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (NaviItem.get(HeaderItem.get(groupPosition)).size() == 0) {
                    if (drawer.isDrawerVisible(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else {
                        drawer.openDrawer(GravityCompat.START);
                    }

                    showToast(HeaderItem.get(groupPosition));
                    String str_header = HeaderItem.get(groupPosition);
                    if (str_header.equals(constantString.carDriverMapping)) {
                        callFragment(new carDriverMappingFragment(), R.id.content_frame, "");
                        tv_toolbar.setText(constantString.carDriverMapping);
                    } else if (str_header.equals(constantString.expense)) {
                        tv_toolbar.setText(constantString.expense);
                    } else if (str_header.equals(constantString.logout)) {
                        showToast(constantString.logout);
                    }
                }
                return false;
            }
        });

        mDrawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    mDrawerList.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        mDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (drawer.isDrawerVisible(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }

                String str_child = NaviItem.get(HeaderItem.get(groupPosition)).get(childPosition);
                if (str_child.equals(constantString.bookCar)) {

                    callFragment(new bookCarSearchFragment(), R.id.content_frame);
                    tv_toolbar.setText(constantString.bookCar);

                } else if (str_child.equals(constantString.listOfBookCar)) {
                    callFragment(new bookedCarFragment(), R.id.content_frame, "");
//                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new BookedCarListFragment()).commit();
                    tv_toolbar.setText(constantString.listOfBookCar);

                } else if (str_child.equals(constantString.preBookingCar)) {

//                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new BookCarInvoiceFragment()).commit();
                    tv_toolbar.setText(constantString.preBookingCar);

                } else if (str_child.equals(constantString.listOfPreBookingCar)) {

//                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new BookedCarListFragment()).commit();
                    tv_toolbar.setText(constantString.bookCar);

                } else if (str_child.equals(constantString.driver)) {

//                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DriverCancelRequestFragment()).commit();
                    tv_toolbar.setText(constantString.driverCancelRequest);

                } else if (str_child.equals(constantString.customer)) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new CustomerCancelRequestFragment()).commit();
                    tv_toolbar.setText(constantString.customerCancelRequest);

                } else if (str_child.equals(constantString.paymentManagement)) {

                }

                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void showToast(String message) {

        displayToast(message);
    }

    @Override
    public void navigationItemClick(Fragment secondFragment) {

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectItem(i);


        }
    }

    private void selectItem(int i) {
        Fragment fragment = null;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
