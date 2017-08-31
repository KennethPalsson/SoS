
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArrivalBoardRoot {

    @SerializedName("ArrivalBoard")
    @Expose
    private ArrivalBoard arrivalBoard;

    public ArrivalBoard getArrivalBoard() {
        return arrivalBoard;
    }

    public void setArrivalBoard(ArrivalBoard arrivalBoard) {
        this.arrivalBoard = arrivalBoard;
    }

}
