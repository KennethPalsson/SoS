package se.ocarina.straightonstrait.ui;

import android.os.Parcel;
import android.os.Parcelable;

class Link implements Parcelable {

    private final Transportation mTransportType;
    private final String mType;
    private final String mName;
    private String mDirection;
    private final Node mOrigin;
    private final Node mDestination;
    private final String mJourneyDetailRef;
    private String mDepartureRtTrack;
    private String mDepartureRtTime;
    private String mArrivalRtTrack;
    private String mArrivalRtTime;

    Link(Transportation transportType,
         String type,
         String name,
         Node origin,
         Node destination,
         String journeyDetailRef) {
        mTransportType = transportType;
        mType = type;
        mName = name;
        mDirection = "";
        mOrigin = new Node(origin);
        mDestination = new Node(destination);
        mJourneyDetailRef = journeyDetailRef;
    }

    private Link(Parcel in) {
        mTransportType = Transportation.valueOf(in.readString());
        mType = in.readString();
        mName = in.readString();
        mDirection = in.readString();
        mOrigin = in.readParcelable(Node.class.getClassLoader());
        mDestination = in.readParcelable(Node.class.getClassLoader());
        mJourneyDetailRef = in.readString();
        mDepartureRtTrack = in.readString();
        mDepartureRtTime = in.readString();
        mArrivalRtTrack = in.readString();
        mArrivalRtTime = in.readString();
    }

    public static final Creator<Link> CREATOR = new Creator<Link>() {
        @Override
        public Link createFromParcel(Parcel in) {
            return new Link(in);
        }

        @Override
        public Link[] newArray(int size) {
            return new Link[size];
        }
    };

    Transportation getTransportType() {
        return mTransportType;
    }

    public String getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

    String getDirection() {
        return mDirection;
    }

    void setDirection(String direction) {
        mDirection = direction;
    }

    String getOriginName() {
        return mOrigin.getName();
    }

    Integer getOriginRouteIdx() {
        return mOrigin.getRouteIdx();
    }

    String getDepartureTrack() {
        return mOrigin.getTrack();
    }

    String getDepartureTime() {
        return mOrigin.getTime();
    }

    String getDestinationName() {
        return mDestination.getName();
    }

    Integer getDestinationRouteIdx() {
        return mDestination.getRouteIdx();
    }

    String getArrivalTrack() {
        return mDestination.getTrack();
    }

    String getArrivalTime() {
        return mDestination.getTime();
    }

    String getJourneyDetailRef() {
        return mJourneyDetailRef;
    }

    String getDepartureRtTrack() {
        return mDepartureRtTrack;
    }

    void setDepartureRtTrack(String departureRtTrack) {
        mDepartureRtTrack = departureRtTrack;
    }

    String getDepartureRtTime() {
        return mDepartureRtTime;
    }

    void setDepartureRtTime(String departureRtTime) {
        mDepartureRtTime = departureRtTime;
    }

    String getArrivalRtTrack() {
        return mArrivalRtTrack;
    }

    void setArrivalRtTrack(String arrivalRtTrack) {
        mArrivalRtTrack = arrivalRtTrack;
    }

    String getArrivalRtTime() {
        return mArrivalRtTime;
    }

    void setArrivalRtTime(String arrivalRtTime) {
        mArrivalRtTime = arrivalRtTime;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTransportType.toString());
        dest.writeString(mType);
        dest.writeString(mName);
        dest.writeString(mDirection);
        dest.writeParcelable(mOrigin, flags);
        dest.writeParcelable(mDestination, flags);
        dest.writeString(mJourneyDetailRef);
        dest.writeString(mDepartureRtTrack);
        dest.writeString(mDepartureRtTime);
        dest.writeString(mArrivalRtTrack);
        dest.writeString(mArrivalRtTime);
    }
}
