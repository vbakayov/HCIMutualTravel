package samples.exoguru.materialtabss;

import java.util.Date;

/**
 * Created by Viktor on 11/19/2015.
 */
public class Trip {
    private String ownerName;
    private String fromTown;
    private String toTown;
    private Date time;
    private Integer seatsAvailable;
    private boolean smooking;
    private boolean food;
    private boolean pets;
    private boolean music;
    private String shortText;
    private int price;


    public Trip(String ownerName, String fromTown, String toTown, Date time, Integer seatsAvailable, boolean smooking, boolean food, boolean pets, boolean music, String shortText, int price) {

        this.ownerName = ownerName;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.time = time;
        this.seatsAvailable = seatsAvailable;
        this.smooking = smooking;
        this.food = food;
        this.pets = pets;
        this.music = music;
        this.shortText = shortText;
        this.price= price;
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

    public boolean isSmooking() {
        return smooking;
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
}
