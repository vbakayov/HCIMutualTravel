package samples.exoguru.materialtabss;

import java.util.ArrayList;

/**
 * Created by Viktor on 11/19/2015.
 * Singleton class stroing all the trips in the system
 */
public class TripStorage {

    private static TripStorage  tripStorage = new TripStorage();
    private static ArrayList<Trip> trips= new ArrayList<Trip>();


    //prevent any other class from instantiating
    private TripStorage(){}

    public static TripStorage getInstance(){
        return tripStorage;
    }

    protected static  void addTrip( Trip item){
        trips.add(item);
    }

    protected ArrayList<Trip>  getTrips(){
        return trips;
    }
}
