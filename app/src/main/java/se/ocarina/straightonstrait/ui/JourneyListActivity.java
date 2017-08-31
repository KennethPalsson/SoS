package se.ocarina.straightonstrait.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;

import se.ocarina.straightonstrait.R;

public class JourneyListActivity extends FragmentActivity
    implements JourneyListFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new JourneyListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onJourneySelected(Journey journey, boolean multiplePanes) {
        if (multiplePanes) {
            Fragment detailFragment = JourneyFragment.newInstance(journey);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, detailFragment)
                    .commit();
        } else {
            Intent intent = JourneyActivity.newIntent(this, journey);
            startActivity(intent);
        }
    }

    @Override
    public boolean isMultiplePanes() {
        return findViewById(R.id.detail_fragment_container) != null;
    }
}
