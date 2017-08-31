package se.ocarina.straightonstrait.ui;

import android.os.Parcel;
import android.os.Parcelable;

class Node implements Parcelable {
    private final String mName;
    private final String mTrack;
    private final String mTime;
    private final Integer mRouteIdx;

    Node(String name, String track, String time, Integer routeIdx) {
        mName = name;
        mTrack = track;
        mTime = time;
        mRouteIdx = routeIdx;
    }

    Node(Node node) {
        mName = node.getName();
        mTrack = node.getTrack();
        mTime = node.getTime();
        mRouteIdx = node.getRouteIdx();
    }

    private Node(Parcel in) {
        mName = in.readString();
        mTrack = in.readString();
        mTime = in.readString();
        mRouteIdx = in.readInt();
    }

    public static final Creator<Node> CREATOR = new Creator<Node>() {
        @Override
        public Node createFromParcel(Parcel in) {
            return new Node(in);
        }

        @Override
        public Node[] newArray(int size) {
            return new Node[size];
        }
    };

    public String getName() {
        return mName;
    }

    public String getTrack() {
        return mTrack;
    }

    public String getTime() {
        return mTime;
    }

    public Integer getRouteIdx() {
        return mRouteIdx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mTrack);
        dest.writeString(mTime);
        dest.writeInt(mRouteIdx);
    }
}
