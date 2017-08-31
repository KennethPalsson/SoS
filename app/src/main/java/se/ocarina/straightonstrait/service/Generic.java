
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Generic {

    @SerializedName("infotext")
    @Expose
    private List<Infotext> infotext;

    public List<Infotext> getInfotext() {
        return infotext;
    }

    public void setInfotext(List<Infotext> infotext) {
        this.infotext = infotext;
    }

}
