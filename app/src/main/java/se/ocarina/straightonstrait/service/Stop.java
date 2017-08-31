
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stop {

    @SerializedName("arrDate")
    @Expose
    private String arrDate;
    @SerializedName("arrTime")
    @Expose
    private String arrTime;
    @SerializedName("depDate")
    @Expose
    private String depDate;
    @SerializedName("depTime")
    @Expose
    private String depTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("routeIdx")
    @Expose
    private Integer routeIdx;
    @SerializedName("rtArrDate")
    @Expose
    private String rtArrDate;
    @SerializedName("rtArrTime")
    @Expose
    private String rtArrTime;
    @SerializedName("rtDepDate")
    @Expose
    private String rtDepDate;
    @SerializedName("rtDepTime")
    @Expose
    private String rtDepTime;
    @SerializedName("rtTrack")
    @Expose
    private String rtTrack;
    @SerializedName("track")
    @Expose
    private String track;
    @SerializedName("x")
    @Expose
    private Integer x;
    @SerializedName("y")
    @Expose
    private Integer y;

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
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

    public String getRtArrDate() {
        return rtArrDate;
    }

    public void setRtArrDate(String rtArrDate) {
        this.rtArrDate = rtArrDate;
    }

    public String getRtArrTime() {
        return rtArrTime;
    }

    public void setRtArrTime(String rtArrTime) {
        this.rtArrTime = rtArrTime;
    }

    public String getRtDepDate() {
        return rtDepDate;
    }

    public void setRtDepDate(String rtDepDate) {
        this.rtDepDate = rtDepDate;
    }

    public String getRtDepTime() {
        return rtDepTime;
    }

    public void setRtDepTime(String rtDepTime) {
        this.rtDepTime = rtDepTime;
    }

    public String getRtTrack() {
        return rtTrack;
    }

    public void setRtTrack(String rtTrack) {
        this.rtTrack = rtTrack;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

}
