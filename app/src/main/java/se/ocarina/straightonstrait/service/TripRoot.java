
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripRoot {

    @SerializedName("TripList")
    @Expose
    private TripList tripList;

    public TripList getTripList() {
        return tripList;
    }

    public void setTripList(TripList tripList) {
        this.tripList = tripList;
    }

}
