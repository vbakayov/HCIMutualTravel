package samples.exoguru.materialtabss;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.andexert.expandablelayout.library.ExpandableLayout;

/**
 * Created by Viktor on 11/5/2015.
 */
public class Tab2 extends Fragment implements NumberPicker.OnValueChangeListener {
    private DatePicker datePicker;
    private Calendar calendar;
    private   ArrayList<String> cities;
    private static EditText dateView;
    private EditText seatView;
    private AutoCompleteTextView toView;
    private AutoCompleteTextView fromView;
    private  boolean smoking;
    private boolean pets;
    private boolean food;
    private boolean music;
    private  static Date date;


    @Override
    public  void onCreate(Bundle saved){
        super.onCreate(saved);

        calendar = Calendar.getInstance();
        cities = new ArrayList<>();

        loadJSONFromAsset();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.tab_2, container,false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item,cities);
        toView = (AutoCompleteTextView)
                v.findViewById(R.id.autoCompleteTextView1);
        toView.setThreshold(2);
        toView.setAdapter(adapter);

        fromView = (AutoCompleteTextView)
                v.findViewById(R.id.autoCompleteTextView2);
        fromView.setThreshold(2);
        fromView.setAdapter(adapter);

        dateView= (EditText)v.findViewById(R.id.depart);
        dateView.setKeyListener(null);

        seatView = (EditText) v.findViewById(R.id.seats);
        seatView.setKeyListener(null);
        //set listenr for the  depart EditTxt
        dateView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }

        });
        //set listenr for the seats EditTxt
        seatView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showNumberPicker();
            }

        });

        ((CheckBox) v.findViewById(R.id.smoking)).setOnClickListener(new CheckBoxListener());
        ((CheckBox) v.findViewById(R.id.pets)).setOnClickListener( new CheckBoxListener());

        Button buttonSearch = (Button) v.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
               //  ArrayList list = filterSearch();
                TripStorage storage = TripStorage.getInstance();
                ArrayList<Trip> list=   storage.getTrips();
                if(list != null) {
                    Intent myIntent = new Intent(getActivity(), ListActivity.class);
                    myIntent.putExtra("filtered", list); //pass the filted array with the trips
                    getActivity().startActivity(myIntent);
                }
            }
        });
        final ExpandableLayout expandableLayoutView = (ExpandableLayout) v.findViewById(R.id.advancedOptions);
        return v;
    }




    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = getActivity().getAssets().open("countriesToCities.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
            Log.d("Citieeees",json);
            JSONObject jsonObj = new JSONObject(json);
            // Getting JSON Array node
            JSONArray jArray = jsonObj.getJSONArray("United Kingdom");
            for (int i=0;i<jArray.length();i++){
                cities.add(jArray.get(i).toString());
            }


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {

    }

    public  class CheckBoxListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // Is the view now checked?
            boolean checked = ((CheckBox) view).isChecked();

            // Check which checkbox was clicked
            switch(view.getId()) {
                case R.id.smoking:
                    if (checked) {
                        smoking= true;

                    }
                    else
                        smoking=false;
                        break;
                case R.id.pets:
                    if (checked) {
                        pets = true;
                    }
                    else
                        pets= false;
                        break;
                case R.id.food:
                    if (checked) {
                        food = true;
                    }
                    else
                        food= false;
                        break;
                case R.id.music:
                    if (checked) {
                        music= true;
                    }
                    else
                        music= false;
                        break;

            }


        }
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            dateView.setText(new StringBuilder().append(day).append("/")
                    .append(month+1).append("/").append(year));

            String date_s= Integer.toString(day)+Integer.toString(month)+Integer.toString(year);
            SimpleDateFormat dt = new SimpleDateFormat("ddMMyyyy");
            try {
                date= dt.parse(date_s);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

    }

    public void showNumberPicker()
    {

        final Dialog d = new Dialog(getActivity());
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.numberdialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(100);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                seatView.setText(String.valueOf(np.getValue()));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }


    private ArrayList<Trip> filterSearch() {

        String from = fromView.getText().toString();
        String to = toView.getText().toString();
        String seats2 = seatView.getText().toString();


        if (!from.equals("") && !to.equals("") &&   !seats2.equals("")) {

            int seats = Integer.parseInt(seats2);
            ArrayList<Trip> filtered = new ArrayList<Trip>();

            TripStorage storage = TripStorage.getInstance();
             ArrayList<Trip> trips =   storage.getTrips();
            Log.w("date1", date.toString());
            for ( int i = 0 ; i< trips.size(); i++){
                    Trip trip = trips.get(i);
                    Log.w("date2",trip.getTime().toString());
                   if (from.equals(trip.getFromTown()) && to.equals(trip.getToTown()) && seats < trip.getSeatsAvailable() && date.compareTo(trip.getTime())==0){
                       filtered.add(trip);
                   }
            }
            if (filtered.size() == 0){
                Toast.makeText(getActivity(), "No Results Found!", Toast.LENGTH_LONG).show();
                return  null;
            }
            return  filtered;
        }
        Toast.makeText(getActivity(), "Please fill all the fields!", Toast.LENGTH_LONG).show();
        return null;
    }

}
