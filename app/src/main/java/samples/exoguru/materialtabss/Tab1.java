package samples.exoguru.materialtabss;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;


public class Tab1 extends Fragment implements  CallBackEditPostPostion {

    private static final String TAG = "Profile Tab";

    private ArrayList<String> posts;
    private ArrayList<Trip> trips;

    private MainActivity myActivity;
    private ViewGroup container;
    private  FragmentManager fm;
    private ListView mPostList;

    @Override
    public void onAttach(Activity myActivity){
        super.onAttach(myActivity);
        this.myActivity = (MainActivity) myActivity;
        fm =  ((MainActivity) myActivity).getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1,container,false);
        this.container = container;
        String[] history = {"Glasgow -> London", "Mezdra -> Sofia"};

        posts = new ArrayList<>();
//        posts.add("Glasgow -> London");
//        posts.add("Mezdra -> Sofia");
//        posts.add("Varna -> Bourgas");
//        posts.add("Vratza -> ASDAsdaxaad12w3sadas213asdsadsadasdasdadasdasdasdasdasdad");


        Profile profile = myActivity.getProfile();
        trips = profile.getTrips();


        Log.i(TAG, "Profile: " + profile.toString() + " num of trips: " + profile.getTrips().size());

        for(Trip trip : trips){
            posts.add(trip.toString());
        }

       // Log.i("TAG", trips.)


        // adapter for posts
        ListAdapter adapter = new CustomListViewAdapter(v.getContext(), posts,this);

        mPostList = (ListView) v.findViewById(R.id.mPostList);
        mPostList.setAdapter(adapter);
        // adapter for history
        ListAdapter historyAdapter = new CustomHistoryListAdapter(v.getContext(), history);
        ListView mHistoryList = (ListView) v.findViewById(R.id.mHistoryPostList);
        mHistoryList.setAdapter(historyAdapter);

        mPostList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));

                        Toast.makeText(getActivity(), food, Toast.LENGTH_LONG).show();
                    }
                }
        );

        // rating bar
        RatingBar rb = (RatingBar) v.findViewById(R.id.mRating);
        rb.setRating(Float.parseFloat("3.4"));

                rb.setOnRatingBarChangeListener(
                        new RatingBar.OnRatingBarChangeListener() {
                            @Override
                            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                                String numStars = String.valueOf(rating);
                                Toast.makeText(Tab1.super.getActivity(), "The new rating is " + numStars, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
        
        return v;
    }


    @Override
    public void passPosition(int position, String action) {
        if (action.equals("delete")){
            posts.remove(position);
            ((BaseAdapter) mPostList.getAdapter()).notifyDataSetChanged();
            Toast.makeText(getActivity(), "Post Deleted!", Toast.LENGTH_LONG).show();
        }else{ //edit
            Log.i("Passed", String.valueOf(posts.get(position)));
            final FragmentTransaction ft = fm.beginTransaction();
            ft.replace((R.id.mainTab1), new SeatPostFragment());
            ft.addToBackStack(null);
            ft.commit();
        }

    }
}
