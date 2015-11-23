package samples.exoguru.materialtabss;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
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
public class SeatPostFragment extends Fragment implements NumberPicker.OnValueChangeListener {
    private Calendar calendar;
    private static EditText DateView;
    private static Date date;
    private EditText seatView;
    private EditText priceView;
    private AutoCompleteTextView toView;
    private AutoCompleteTextView fromView;
    private  boolean smoking;
    private boolean food;
    private boolean pets;
    private boolean music;


    ArrayList<String> cities;

    @Override
    public  void onCreate(Bundle saved){
        super.onCreate(saved);



        calendar = Calendar.getInstance();
        cities = new ArrayList<>();

        smoking = false;
        food= false;
        pets= false;
        music=true;

        loadJSONFromAsset();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.post_seats_offer, container,false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.select_dialog_item,cities);
        toView = (AutoCompleteTextView)  v.findViewById(R.id.autoCompleteTextView1);
        toView.setThreshold(2);
        toView.setAdapter(adapter);

        fromView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView2);
        fromView.setThreshold(2);
        fromView.setAdapter(adapter);

        DateView = (EditText)v.findViewById(R.id.depart);
        DateView.setKeyListener(null);

        seatView = (EditText) v.findViewById(R.id.seats);
        seatView.setKeyListener(null);

        priceView = (EditText) v.findViewById(R.id.pricePost);
        priceView.setKeyListener(null);


        //set listener for the  depart EditTxt
        DateView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }

        });
        //set listener for the seats EditTxt
        seatView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showNumberPicker();
            }

        });

        //set listener for the seats EditTxt
        priceView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showNumberPickerPrice();
            }


        });

        ((CheckBox) v.findViewById(R.id.smoking)).setOnClickListener(new CheckBoxListener());
        ((CheckBox) v.findViewById(R.id.pets)).setOnClickListener( new CheckBoxListener());

        Button buttonSearch = (Button) v.findViewById(R.id.buttonPostSeats);
        buttonSearch.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                    postTrip();
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
                        Log.w("Check","Checked");
                    }
                    else
                        smoking= false;
                        break;
                case R.id.pets:
                    if (checked) {
                        pets=true;
                    }
                    else
                        smoking=false;
                        break;
                case R.id.food:
                    if (checked) {
                       food=true;
                    }
                    else
                       food=false;
                        break;
                case R.id.music:
                    if (checked) {
                        music=true;
                    }
                    else
                        music=false;
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
            DateView.setText(new StringBuilder().append(day).append("/")
                    .append(month+1).append("/").append(year));
            //create data object
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


    private void showNumberPickerPrice() {
        final Dialog d = new Dialog(getActivity());
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.numberdialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(10000);
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                priceView.setText(String.valueOf(np.getValue()));
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
    private void  postTrip(){

//        private static EditText DateView;
//        private int year, month, day;
//        private EditText seatView;
        String from = fromView.getText().toString();
        String to = toView.getText().toString();
        String price2= priceView.getText().toString();
        String seats2 = seatView.getText().toString();

        if (!from.equals("") && !to.equals("") && !price2.equals("") && !seats2.equals("")) {
            int seats = Integer.parseInt(seats2);
            int price = Integer.parseInt(price2);

            // SET GROUP
            String group = "Facebook";

            TripStorage storage = TripStorage.getInstance();
            storage.addTrip(new Trip("FromPost", from, to, date, seats, smoking, food, pets, music, "none", price, group));
            Toast.makeText(getActivity(), "Success!", Toast.LENGTH_LONG).show();

            Profile profile = Profile.getInstance();
            profile.setTrips(storage.getTrips());



            Log.i("POST A TRIP", "Profile: " + profile.getTrips().size());

            fromView.setText("");
            toView.setText("");
            DateView.setText("");
            priceView.setText("");
            seatView.setText("");
            calendar.clear();
        }else{
            Toast.makeText(getActivity(), "Please fill all fields!", Toast.LENGTH_LONG).show();
        }


    }
}
