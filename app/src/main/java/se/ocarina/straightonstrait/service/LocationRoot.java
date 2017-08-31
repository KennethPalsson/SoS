
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationRoot {

    @SerializedName("CoordLocation")
    @Expose
    private CoordLocation coordLocation;
    @SerializedName("LocationList")
    @Expose
    private LocationList locationList;
    @SerializedName("StopLocation")
    @Expose
    private StopLocation stopLocation;

    public CoordLocation getCoordLocation() {
        return coordLocation;
    }

    public void setCoordLocation(CoordLocation coordLocation) {
        this.coordLocation = coordLocation;
    }

    public LocationList getLocationList() {
        return locationList;
    }

    public void setLocationList(LocationList locationList) {
        this.locationList = locationList;
    }

    public StopLocation getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(StopLocation stopLocation) {
        this.stopLocation = stopLocation;
    }

}
