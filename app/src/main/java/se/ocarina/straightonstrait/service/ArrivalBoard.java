
package se.ocarina.straightonstrait.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrivalBoard {

    @SerializedName("noNamespaceSchemaLocation")
    @Expose
    private String noNamespaceSchemaLocation;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("Arrival")
    @Expose
    private List<Arrival> arrival;

    public String getNoNamespaceSchemaLocation() {
        return noNamespaceSchemaLocation;
    }

    public void setNoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
        this.noNamespaceSchemaLocation = noNamespaceSchemaLocation;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Arrival> getArrival() {
        return arrival;
    }

    public void setArrival(List<Arrival> arrival) {
        this.arrival = arrival;
    }
}
