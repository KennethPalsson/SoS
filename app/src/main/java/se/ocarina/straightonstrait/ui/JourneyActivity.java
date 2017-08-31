package se.ocarina.straightonstrait.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class JourneyActivity extends FragmentActivity {

    private static final String EXTRA_JOURNEY = "se.ocarina.straightonstrait.journey";

    @Override
    protected Fragment createFragment() {
        Journey journey = getIntent().getParcelableExtra(EXTRA_JOURNEY);
        return JourneyFragment.newInstance(journey);
    }

    public static Intent newIntent(Context packageContext, Journey journey) {
        Intent intent = new Intent(packageContext, JourneyActivity.class);
        intent.putExtra(EXTRA_JOURNEY, journey);
        return intent;
    }
}
