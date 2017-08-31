
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JourneyType {

    @SerializedName("routeIdxFrom")
    @Expose
    private Integer routeIdxFrom;
    @SerializedName("routeIdxTo")
    @Expose
    private Integer routeIdxTo;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getRouteIdxFrom() {
        return routeIdxFrom;
    }

    public void setRouteIdxFrom(Integer routeIdxFrom) {
        this.routeIdxFrom = routeIdxFrom;
    }

    public Integer getRouteIdxTo() {
        return routeIdxTo;
    }

    public void setRouteIdxTo(Integer routeIdxTo) {
        this.routeIdxTo = routeIdxTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
