package se.ocarina.straightonstrait.ui;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

class Journey implements Parcelable {
    private final int mIndex;
    private final Date mDepartureTime;
    private final Date mArrivalTime;
    private final long mDuration;
    private final int mTransferCount;
    private final long mWalkDuration;
    private final ArrayList<Transportation> mTransportTypes;
    private final ArrayList<Link> mLinks;

    Journey(int index,
            Date departureTime,
            Date arrivalTime,
            long duration,
            int transferCount,
            long walkDuration,
            ArrayList<Transportation> transportTypes,
            ArrayList<Link> links) {
        mIndex = index;
        mDepartureTime = departureTime;
        mArrivalTime = arrivalTime;
        mDuration = duration;
        mTransferCount = transferCount;
        mWalkDuration = walkDuration;
        mTransportTypes = transportTypes;
        mLinks = links;
    }

    private Journey(Parcel in) {
        mIndex = in.readInt();
        mDepartureTime = new Date(in.readLong());
        mArrivalTime = new Date(in.readLong());
        mDuration = in.readLong();
        mTransferCount = in.readInt();
        mWalkDuration = in.readLong();
        ArrayList<String> ttStrings = new ArrayList<>();
        in.readStringList(ttStrings);
        mTransportTypes = asEnumList(ttStrings);
        mLinks = in.createTypedArrayList(Link.CREATOR);
    }

    public static final Creator<Journey> CREATOR = new Creator<Journey>() {
        @Override
        public Journey createFromParcel(Parcel in) {
            return new Journey(in);
        }

        @Override
        public Journey[] newArray(int size) {
            return new Journey[size];
        }
    };

    int getIndex() { return mIndex; }

    Date getDepartureTime() {
        return mDepartureTime;
    }

    Date getArrivalTime() {
        return mArrivalTime;
    }

    long getDuration() {
        return mDuration;
    }

    int getTransferCount() {
        return mTransferCount;
    }

    long getWalkDuration() {
        return mWalkDuration;
    }

    ArrayList<Transportation> getTransportTypes() { return mTransportTypes; }

    ArrayList<Link> getLinks() { return mLinks; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mIndex);
        dest.writeLong(mDepartureTime.getTime());
        dest.writeLong(mArrivalTime.getTime());
        dest.writeLong(mDuration);
        dest.writeInt(mTransferCount);
        dest.writeLong(mWalkDuration);
        dest.writeStringList(asStringList(mTransportTypes));
        dest.writeTypedList(mLinks);
    }

    private static ArrayList<Transportation> asEnumList(ArrayList<String> transportTypes) {
        ArrayList<Transportation> result = new ArrayList<>();
        for (String t : transportTypes) {
            result.add(Transportation.valueOf(t));
        }
        return result;
    }

    private static ArrayList<String> asStringList(ArrayList<Transportation> transportTypes) {
        ArrayList<String> result = new ArrayList<>();
        for (Transportation t : transportTypes) {
            result.add(t.toString());
        }
        return result;
    }
}
