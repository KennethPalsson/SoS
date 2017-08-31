package se.ocarina.straightonstrait.ui;

import android.content.Context;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import se.ocarina.straightonstrait.R;
import se.ocarina.straightonstrait.service.CollectionHelper;
import se.ocarina.straightonstrait.service.LocationRoot;
import se.ocarina.straightonstrait.service.RestApi;
import se.ocarina.straightonstrait.service.StopLocation;

public class AutoCompletePreference extends EditTextPreference {
    private static final int MIN_THRESHOLD = 3;
    private final String TAG = "AutoCompletePreference";
    private final AutoCompleteTextView mTextView;
    private final ArrayAdapter<String> mAdapter;
    private List<StopLocation> mEntries;
    private Retrofit mRetrofit;

    public AutoCompletePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTextView = new AutoCompleteTextView(context, attrs);
        mTextView.setThreshold(MIN_THRESHOLD);
        mTextView.setSelectAllOnFocus(true);
        mAdapter = new ArrayAdapter<>(getContext(), R.layout.drop_down_text_item);
        mTextView.setAdapter(mAdapter);
        mTextView.addTextChangedListener(new TextWatcher() {
            static final int MAX_ENTRIES = 50;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {
                if (count < before) {
                    mTextView.setThreshold(count > MIN_THRESHOLD ? count : MIN_THRESHOLD);
                }
                if (!mTextView.enoughToFilter()) {
                    mEntries = null;
                    mAdapter.clear();
                } else if (mEntries == null || mEntries.size() >= MAX_ENTRIES) {
                    if (count > before && mEntries != null) {
                        mTextView.setThreshold(count > MIN_THRESHOLD ? count : MIN_THRESHOLD);
                    }
                    final String input = TextUtils.substring(s, 0, mTextView.getThreshold());
                    Call<LocationRoot> location = mRetrofit.create(RestApi.class).getLocation(input);
                    location.enqueue(new Callback<LocationRoot>() {
                        @Override
                        public void onResponse(Call<LocationRoot> call, Response<LocationRoot> response) {
                            mEntries = response.body().getLocationList().getStopLocation();
                            mAdapter.clear();
                            List<String> names = CollectionHelper.getNames(mEntries);
                            mAdapter.addAll(names);

                            //Force the adapter to filter itself, necessary to show new data.
                            //Filter based on the current text because api call is asynchronous.
                            mAdapter.getFilter().filter(input, null);                        }

                        @Override
                        public void onFailure(Call<LocationRoot> call, Throwable t) {
                            Log.w(TAG, "Failed to get locations from REST service");
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onBindDialogView(View view) {
        AutoCompleteTextView textView = mTextView;
        textView.setText(getText());
        ViewParent oldParent = textView.getParent();
        if (oldParent != view) {
            if (oldParent != null) {
                ((ViewGroup)oldParent).removeView(textView);
            }
            onAddEditTextToDialogView(view, textView);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            String value = mTextView.getText().toString();
            if (callChangeListener(value)) {
                setText(value);
            }
        }
    }

    public void setRetrofit(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

}
