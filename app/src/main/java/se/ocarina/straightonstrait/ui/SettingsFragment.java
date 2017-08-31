package se.ocarina.straightonstrait.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import javax.inject.Inject;

import retrofit2.Retrofit;
import se.ocarina.straightonstrait.R;
import se.ocarina.straightonstrait.SosApplication;

public class SettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Inject
    Retrofit retrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        Activity activity = getActivity();
        ((SosApplication) activity.getApplication()).getNetComponent().inject(this);

        final AutoCompletePreference originPreference =
                (AutoCompletePreference) findPreference(getString(R.string.pref_key_origin));
        originPreference.setRetrofit(retrofit);

        final AutoCompletePreference destinationPreference =
                (AutoCompletePreference) findPreference(getString(R.string.pref_key_destination));
        destinationPreference.setRetrofit(retrofit);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        getActivity().setResult(Activity.RESULT_OK, null);
    }

}
