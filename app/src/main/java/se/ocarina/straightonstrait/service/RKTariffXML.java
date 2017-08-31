
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RKTariffXML {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("generic")
    @Expose
    private Generic generic;
    @SerializedName("pricingSet")
    @Expose
    private List<PricingSet> pricingSet;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Generic getGeneric() {
        return generic;
    }

    public void setGeneric(Generic generic) {
        this.generic = generic;
    }

    public List<PricingSet> getPricingSet() {
        return pricingSet;
    }

    public void setPricingSet(List<PricingSet> pricingSet) {
        this.pricingSet = pricingSet;
    }

}
