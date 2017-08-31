
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PricingSet {

    @SerializedName("passengerTypeAmount")
    @Expose
    private String passengerTypeAmount;
    @SerializedName("passengerTypeId")
    @Expose
    private String passengerTypeId;
    @SerializedName("passengerTypeInfo")
    @Expose
    private String passengerTypeInfo;
    @SerializedName("pricing")
    @Expose
    private Pricing pricing;

    public String getPassengerTypeAmount() {
        return passengerTypeAmount;
    }

    public void setPassengerTypeAmount(String passengerTypeAmount) {
        this.passengerTypeAmount = passengerTypeAmount;
    }

    public String getPassengerTypeId() {
        return passengerTypeId;
    }

    public void setPassengerTypeId(String passengerTypeId) {
        this.passengerTypeId = passengerTypeId;
    }

    public String getPassengerTypeInfo() {
        return passengerTypeInfo;
    }

    public void setPassengerTypeInfo(String passengerTypeInfo) {
        this.passengerTypeInfo = passengerTypeInfo;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

}
