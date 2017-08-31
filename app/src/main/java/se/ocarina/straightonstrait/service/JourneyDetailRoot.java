
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JourneyDetailRoot {

    @SerializedName("JourneyDetail")
    @Expose
    private JourneyDetail journeyDetail;

    public JourneyDetail getJourneyDetail() {
        return journeyDetail;
    }

    public void setJourneyDetail(JourneyDetail journeyDetail) {
        this.journeyDetail = journeyDetail;
    }

}
