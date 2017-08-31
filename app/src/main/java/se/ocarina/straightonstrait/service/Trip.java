
package se.ocarina.straightonstrait.service;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("alternative")
    @Expose
    private Boolean alternative;
    @SerializedName("cancelled")
    @Expose
    private Boolean cancelled;
    @SerializedName("valid")
    @Expose
    private Boolean valid;
    @SerializedName("Leg")
    @Expose
    private List<Leg> leg;
    @SerializedName("RKTariffXML")
    @Expose
    private RKTariffXML rKTariffXML;

    public Boolean getAlternative() {
        return alternative;
    }

    public void setAlternative(Boolean alternative) {
        this.alternative = alternative;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public List<Leg> getLeg() {
        return leg;
    }

    public void setLeg(List<Leg> leg) {
        this.leg = leg;
    }

    public RKTariffXML getRKTariffXML() {
        return rKTariffXML;
    }

    public void setRKTariffXML(RKTariffXML rKTariffXML) {
        this.rKTariffXML = rKTariffXML;
    }

}
