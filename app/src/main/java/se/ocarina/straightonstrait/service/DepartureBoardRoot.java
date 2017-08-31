
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartureBoardRoot {

    @SerializedName("DepartureBoard")
    @Expose
    private DepartureBoard departureBoard;

    public DepartureBoard getDepartureBoard() {
        return departureBoard;
    }

    public void setDepartureBoard(DepartureBoard departureBoard) {
        this.departureBoard = departureBoard;
    }

}
