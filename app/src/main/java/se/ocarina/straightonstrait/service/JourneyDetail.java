
package se.ocarina.straightonstrait.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JourneyDetail {

    @SerializedName("noNamespaceSchemaLocation")
    @Expose
    private String noNamespaceSchemaLocation;
    @SerializedName("Stop")
    @Expose
    private List<Stop> stop;
    @SerializedName("JourneyName")
    @Expose
    private List<JourneyName> journeyName;
    @SerializedName("JourneyType")
    @Expose
    private List<JourneyType> journeyType;
    @SerializedName("Note")
    @Expose
    private List<Note> note;

    public String getNoNamespaceSchemaLocation() {
        return noNamespaceSchemaLocation;
    }

    public void setNoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
        this.noNamespaceSchemaLocation = noNamespaceSchemaLocation;
    }

    public List<Stop> getStop() {
        return stop;
    }

    public void setStop(List<Stop> stop) {
        this.stop = stop;
    }

    public List<JourneyName> getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(List<JourneyName> journeyName) {
        this.journeyName = journeyName;
    }

    public List<JourneyType> getJourneyType() {
        return journeyType;
    }

    public void setJourneyType(List<JourneyType> journeyType) {
        this.journeyType = journeyType;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

}
