package samples.exoguru.materialtabss;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by Viktor on 11/5/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter implements PagerSlidingTabStrip.IconTabProvider {
    private int tabIcons[] = {R.drawable.icon_profile, R.drawable.seat_belt1, R.drawable.luggage, R.drawable.post};


    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    private  Tab2 tab2;
    private  Context mContext;


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(Context ctx, FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        mContext = ctx;
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: Tab1 tab1 = new Tab1();
                    return tab1;
            case 1: tab2 = new Tab2();
                    return tab2;
            case 2:  Tab3 tab3 = new Tab3();
                    return tab3;
            case 3:  Tab4 tab4 = new Tab4();
                    return tab4;
        }
        return null;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }

//    @Override
//    public View getCustomTabView(ViewGroup parent, int position) {
//        LinearLayout customLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.custom_tab, parent, false);
//        ImageView imageView = (ImageView) customLayout.findViewById(R.id.image);
//        imageView.setImageResource(ICONS[position]);
//        return customLayout;
//    }
}
