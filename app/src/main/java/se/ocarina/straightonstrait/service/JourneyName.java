
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JourneyName {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("routeIdxFrom")
    @Expose
    private Integer routeIdxFrom;
    @SerializedName("routeIdxTo")
    @Expose
    private Integer routeIdxTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

}
