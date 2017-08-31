
package se.ocarina.straightonstrait.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripList {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("Trip")
    @Expose
    private List<Trip> trip;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Trip> getTrip() {
        return trip;
    }

    public void setTrip(List<Trip> trip) {
        this.trip = trip;
    }

}
