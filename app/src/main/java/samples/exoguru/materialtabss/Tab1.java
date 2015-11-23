package samples.exoguru.materialtabss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;


public class Tab1 extends Fragment {

    private static final String TAG = "Profile Tab";

    private ArrayList<String> posts;
    private ArrayList<Trip> trips;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_1,container,false);

       // String[] posts = {"Glasgow -> London", "Mezdra -> Sofia", "Varna -> Bourgas", "Vratza -> ASDAsdaxaad12w3sadas213asdsadsadasdasdadasdasdasdasdasdad"};
        String[] history = {"Glasgow -> London", "Mezdra -> Sofia"};

        posts = new ArrayList<>();
        posts.add("Glasgow -> London");
        posts.add("Mezdra -> Sofia");
        posts.add("Varna -> Bourgas");
        posts.add("Vratza -> ASDAsdaxaad12w3sadas213asdsadsadasdasdadasdasdasdasdasdad");




        //Log.i(TAG, "Profile: " + profile.toString());


//        for(Trip trip : trips){
//            posts.add(trip.toString());
//        }

       // Log.i("TAG", trips.)


        // adapter for posts
        ListAdapter adapter = new CustomListViewAdapter(v.getContext(), posts);
        ListView mPostList = (ListView) v.findViewById(R.id.mPostList);
        mPostList.setAdapter(adapter);

        // adapter for history
        ListAdapter historyAdapter = new CustomHistoryListAdapter(v.getContext(), history);
        ListView mHistoryList = (ListView) v.findViewById(R.id.mHistoryPostList);
        mHistoryList.setAdapter(historyAdapter);

//        myListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String food = String.valueOf(parent.getItemAtPosition(position));
//                        Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
//                    }
//                }
//        );

        // rating bar
        RatingBar rb = (RatingBar) v.findViewById(R.id.mRating);


        rb.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                        String numStars = String.valueOf(rating);
                        Toast.makeText(Tab1.super.getActivity(), "The new rating is " + numStars,Toast.LENGTH_SHORT).show();
                    }
                }
        );
        
        return v;
    }


}
