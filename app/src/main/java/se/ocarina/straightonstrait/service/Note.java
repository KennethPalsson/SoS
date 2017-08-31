
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("routeIdxFrom")
    @Expose
    private Integer routeIdxFrom;
    @SerializedName("routeIdxTo")
    @Expose
    private Integer routeIdxTo;
    @SerializedName("text")
    @Expose
    private String text;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
