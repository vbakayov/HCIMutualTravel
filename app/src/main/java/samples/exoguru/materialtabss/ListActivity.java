package samples.exoguru.materialtabss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Viktor on 11/18/2015.
 */
public class ListActivity extends AppCompatActivity{
    private static Trip clickedTrip;
    int onStartCount = 0;
    private TripStorage trips;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        trips= TripStorage.getInstance();

        //for animation the activity
        onStartCount = 1;
        if (savedInstanceState == null) // 1st time
        {
            this.overridePendingTransition(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);
        } else // already created so reverse animation
        {
            onStartCount = 2;
        }

        final PullRefreshLayout layout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        layout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);

    // listen refresh event
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 3000);

            }
        });

        // refresh complete
        layout.setRefreshing(false);

        populateListView();
        populateTripView();
        registerClickCallback();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (onStartCount > 1) {
            this.overridePendingTransition(R.anim.anim_slide_in_right,
                    R.anim.anim_slide_out_right);

        } else if (onStartCount == 1) {
            onStartCount++;
        }

    }


    private void populateListView() {
        //Build Adapter //Context for the activity, layout to use ,Items to be displayed
        ArrayAdapter<Trip> adapter = new MyListAdapter();

        //Configurate the list view
        ListView list = (ListView) findViewById(R.id.listMain);
        list.setBackgroundResource(R.drawable.gradient_bg);;
        list.setAdapter(adapter);
    }


    private class MyListAdapter extends ArrayAdapter<Trip>{
        public 	MyListAdapter(){
            super(ListActivity.this,R.layout.list_row, trips.getTrips());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Make sure we have a view to work with(may have been given null)
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.list_row, parent,false);
            }

            //Find trip to work with
            Trip currentTrip = trips.getTrips().get(position);
            //Fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.list_image);
            imageView.setImageResource(getResources().getIdentifier("myphoto", "drawable", getPackageName()));


            TextView ownerName = (TextView) itemView.findViewById(R.id.title);
            ownerName.setText(currentTrip.getOwnerName());

            TextView toTown = (TextView) itemView.findViewById(R.id.fromList);
            toTown.setText(currentTrip.getFromTown());

            TextView fromTown = (TextView) itemView.findViewById(R.id.toList);
            fromTown.setText(currentTrip.getToTown());

            TextView date = (TextView) itemView.findViewById(R.id.dateList);
            date.setText(currentTrip.getTime().toString());

            TextView price = (TextView) itemView.findViewById(R.id.priceList);
            price.setText(Integer.toString(currentTrip.getPrice()));


            return itemView;
        }
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listMain);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long ID) {
                //which trips we are currently
               clickedTrip = trips.getTrips().get(position);

                //build and center the toast
                Toast toast =Toast.makeText(ListActivity.this, clickedTrip.getOwnerName(), Toast.LENGTH_LONG);
                LinearLayout layout = (LinearLayout) toast.getView();
                if (layout.getChildCount() > 0) {
                    TextView tv = (TextView) layout.getChildAt(0);
                    tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                }
                toast.show();

//                Intent intent = new Intent(MapLocation.this, TabLayoutActivity.class);
//                startActivity(intent);

            }
        });

    }

    private void populateTripView() {
        trips.addTrip(new Trip("Viktor", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",10));
        trips.addTrip(new Trip("Viktor2", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",20));
        trips.addTrip(new Trip("Viktor3", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",30));
        trips.addTrip(new Trip("Viktor4", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",40));
        trips.addTrip(new Trip("Viktor5", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",50));
        trips.addTrip(new Trip("Viktor6", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",60));
        trips.addTrip(new Trip("Viktor7", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",70));
        trips.addTrip(new Trip("Viktor8", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",80));
        trips.addTrip(new Trip("Viktor9", "Sevlievo", "London", new Date(), 3, true, true, true, true, "msg",90));




    }
}


