package se.ocarina.straightonstrait.ui;

import android.content.Context;
import android.widget.ImageView;

import java.util.HashMap;

import se.ocarina.straightonstrait.R;

class ViewManager {
    private final HashMap<Transportation,Integer> mTransportResources;

    ViewManager() {
        mTransportResources = new HashMap<>();
        mTransportResources.put(Transportation.Bus, R.drawable.ic_directions_bus_black_18dp);
        mTransportResources.put(Transportation.Ferry, R.drawable.ic_directions_boat_black_18dp);
        mTransportResources.put(Transportation.Rail, R.drawable.ic_train_black_18dp);
        mTransportResources.put(Transportation.Subway, R.drawable.ic_subway_black_18dp);
        mTransportResources.put(Transportation.Tram, R.drawable.ic_tram_black_18dp);
        mTransportResources.put(Transportation.Walk, R.drawable.ic_directions_walk_black_18dp);
    }

    ImageView createImageView(Context context, Transportation transportation) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(getImageResId(transportation));
        return imageView;
    }

    Integer getImageResId(Transportation transportation) {
        return mTransportResources.get(transportation);
    }
}
