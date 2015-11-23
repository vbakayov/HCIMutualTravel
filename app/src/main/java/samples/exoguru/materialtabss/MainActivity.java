package samples.exoguru.materialtabss;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends ActionBarActivity {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Me","Seats", "Luggage","Post"};
    int Numboftabs =4;

    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


        populateTripView();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
             Button button;
            button = (Button) findViewById(R.id.buttonSeatsPost);
                    button.setVisibility(View.VISIBLE);
            button = (Button) findViewById(R.id.buttonLuggagePost);
                    button.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }

    private void populateTripView() {
        TripStorage trips = TripStorage.getInstance();
        trips.addTrip(new Trip("Viktor", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 10));
        trips.addTrip(new Trip("Viktor2", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 20));
        trips.addTrip(new Trip("Viktor3", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 30));
        trips.addTrip(new Trip("Viktor4", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 40));
        trips.addTrip(new Trip("Viktor5", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 50));
        trips.addTrip(new Trip("Viktor6", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 60));
        trips.addTrip(new Trip("Viktor7", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 70));
        trips.addTrip(new Trip("Viktor8", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 80));
        trips.addTrip(new Trip("Viktor9", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg", 90));

        trips.addTrip(new Trip("Viktor", "London", "Paris", new Date(), 3, true, true, true, true, "msg", 10));
        trips.addTrip(new Trip("Viktor2", "London", "Manchester", new Date(), 5, true, true, true, true, "msg", 20));
        trips.addTrip(new Trip("Viktor3", "Gabrovo", "London", new Date(), 6, true, true, true, true, "msg", 30));
        trips.addTrip(new Trip("Viktor4", "maikati", "bashtati", new Date(), 3, true, true, true, true, "msg", 40));

        profile = Profile.getInstance();
        profile.setTrips(trips.getTrips());

    }

    public Profile getProfile(){
        return profile;
    }


}