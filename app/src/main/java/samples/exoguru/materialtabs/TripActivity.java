package samples.exoguru.materialtabs;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import samples.exoguru.materialtabss.R;
import samples.exoguru.materialtabss.Route;
import samples.exoguru.materialtabss.Trip;

public class TripActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static Trip clickedTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_trip);
        Intent intent = getIntent();
        clickedTrip = (Trip) intent.getSerializableExtra("trip"); //if it's a string you stored.
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        if (Geocoder.isPresent()) {
            try {
                String locationFrom = clickedTrip.getFromTown();
                String locationTo = clickedTrip.getToTown();
                Geocoder gc = new Geocoder(this);
                List<Address> addressesFrom = gc.getFromLocationName(locationFrom, 5); // get the found Address Objects
                List<Address> addressesTo = gc.getFromLocationName(locationTo, 5);
                ArrayList<LatLng>liPoints = new ArrayList<LatLng>(addressesFrom.size()); // A list to save the coordinates if they are available

                for (Address a : addressesFrom)
                    if (a.hasLatitude() && a.hasLongitude()) {liPoints.add(new LatLng(a.getLatitude(), a.getLongitude()));}
                for (Address a : addressesTo)
                    if (a.hasLatitude() && a.hasLongitude()) {liPoints.add(new LatLng(a.getLatitude(), a.getLongitude()));}


                LatLng fromCoordinates = new LatLng(liPoints.get(0).latitude, liPoints.get(0).longitude);
                LatLng toCoordinates = new LatLng(liPoints.get(1).latitude, liPoints.get(1).longitude);

                mMap.addMarker(new MarkerOptions().position(fromCoordinates).title(locationFrom));
                mMap.addMarker(new MarkerOptions().position(toCoordinates).title(locationTo));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(fromCoordinates));
                Route drawRoute  = new Route();
                drawRoute.drawRoute(mMap,this,liPoints,"",true);
            } catch (IOException e) {
                // handle the exception
            }

            // Add a marker in Sydney and move the camera

        }
    }
}
