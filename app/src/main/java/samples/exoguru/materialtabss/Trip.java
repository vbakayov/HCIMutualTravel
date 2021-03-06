package samples.exoguru.materialtabss;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Viktor on 11/19/2015.
 */
public class Trip implements Serializable {
    private String ownerName;
    private String fromTown;
    private String toTown;
    private Date time;
    private Integer seatsAvailable;
    private boolean smoking;
    private boolean food;
    private boolean pets;
    private boolean music;
    private String shortText;
    private int price;
    private String group;

    public Trip(String ownerName, String fromTown, String toTown, Date time, Integer seatsAvailable, boolean smoking, boolean food, boolean pets, boolean music, String shortText, int price, String group) {

        this.ownerName = ownerName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.time = time;
        this.seatsAvailable = seatsAvailable;
        this.smoking = smoking;
        this.food = food;
        this.pets = pets;
        this.music = music;
        this.shortText = shortText;
        this.price= price;
        this.group = group;

    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getFromTown() {
        return fromTown;
    }

    public String getToTown() {
        return toTown;
    }

    public Date getTime() {
        return time;
    }

    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public boolean isFood() {
        return food;
    }

    public boolean isPets() {
        return pets;
    }

    public boolean isMusic() {
        return music;
    }

    public String isShortText() {
        return shortText;
    }


    public int getPrice() {
        return price;
    }

    public String getGroup() {
        return group;
    }

    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");


        return this.fromTown + " to " + this.toTown + " at " + sdf.format(this.time) + " with " + this.seatsAvailable + " seats available";
    }
}
