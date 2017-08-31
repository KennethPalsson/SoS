
package se.ocarina.straightonstrait.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationList {

    @SerializedName("StopLocation")
    @Expose
    private List<StopLocation> stopLocation;
    @SerializedName("CoordLocation")
    @Expose
    private List<CoordLocation> coordLocation;

    public List<StopLocation> getStopLocation() {
        return stopLocation;
    }

    public void setStopLocation(List<StopLocation> stopLocation) {
        this.stopLocation = stopLocation;
    }

    public List<CoordLocation> getCoordLocation() {
        return coordLocation;
    }

    public void setCoordLocation(List<CoordLocation> coordLocation) {
        this.coordLocation = coordLocation;
    }

}
