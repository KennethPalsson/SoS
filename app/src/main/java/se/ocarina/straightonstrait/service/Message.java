
package se.ocarina.straightonstrait.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("Header")
    @Expose
    private Header header;
    @SerializedName("Links")
    @Expose
    private Links links;
    @SerializedName("Text")
    @Expose
    private Text text;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

}
