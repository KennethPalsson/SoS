
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Destination {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("routeIdx")
    @Expose
    private Integer routeIdx;
    @SerializedName("rtDate")
    @Expose
    private String rtDate;
    @SerializedName("rtTime")
    @Expose
    private String rtTime;
    @SerializedName("rtTrack")
    @Expose
    private String rtTrack;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("track")
    @Expose
    private String track;
    @SerializedName("type")
    @Expose
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRouteIdx() {
        return routeIdx;
    }

    public void setRouteIdx(Integer routeIdx) {
        this.routeIdx = routeIdx;
    }

    public String getRtDate() {
        return rtDate;
    }

    public void setRtDate(String rtDate) {
        this.rtDate = rtDate;
    }

    public String getRtTime() {
        return rtTime;
    }

    public void setRtTime(String rtTime) {
        this.rtTime = rtTime;
    }

    public String getRtTrack() {
        return rtTrack;
    }

    public void setRtTrack(String rtTrack) {
        this.rtTrack = rtTrack;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
