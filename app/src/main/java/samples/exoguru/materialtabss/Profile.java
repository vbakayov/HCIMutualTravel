package samples.exoguru.materialtabss;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nikolay on 23/11/2015.
 */
public class Profile implements Serializable{

    private String name;
    private String message;
    private int rating;
    private ArrayList<Trip> trips;
    private ArrayList<Trip> historyTrips;

    private static Profile profile  = new Profile();

    // Constructor
    public Profile(){
         this("Nikolay");
    }

    // constructor
    public Profile(String name){
        this.name = name;
    }

    public static Profile getInstance(){
        return profile;
    }


    // GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public void setTrips(ArrayList<Trip> trips) {
        this.trips = trips;
    }

    public ArrayList<Trip> getHistoryTrips() {
        return historyTrips;
    }

    public void setHistoryTrips(ArrayList<Trip> historyTrips) {
        this.historyTrips = historyTrips;
    }

    public String toString(){
        return this.name;
    }
}
