package se.ocarina.straightonstrait.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import se.ocarina.straightonstrait.R;
import se.ocarina.straightonstrait.SosApplication;
import se.ocarina.straightonstrait.service.Arrival;
import se.ocarina.straightonstrait.service.ArrivalBoardRoot;
import se.ocarina.straightonstrait.service.CollectionHelper;
import se.ocarina.straightonstrait.service.Departure;
import se.ocarina.straightonstrait.service.DepartureBoardRoot;
import se.ocarina.straightonstrait.service.Destination;
import se.ocarina.straightonstrait.service.JourneyDetail;
import se.ocarina.straightonstrait.service.JourneyDetailRoot;
import se.ocarina.straightonstrait.service.Leg;
import se.ocarina.straightonstrait.service.LocationRoot;
import se.ocarina.straightonstrait.service.Origin;
import se.ocarina.straightonstrait.service.RestApi;
import se.ocarina.straightonstrait.service.Stop;
import se.ocarina.straightonstrait.service.Trip;
import se.ocarina.straightonstrait.service.TripRoot;

public class JourneyListFragment extends Fragment {
    private static final String TAG = "JourneyListFragment";
    private static final String KEY_JOURNEYS = "journeys";
    private static final int REQUEST_CODE_SETTINGS = 0;

    @Inject
    Retrofit retrofit;

    private final SosFactory mFactory = new SosFactoryImpl();
    private ViewManager mViewManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mJourneyRecyclerView;
    private SwipeRefreshLayout.OnRefreshListener mListener;
    private Ref<ArrayList<Journey>> mJourneys = new Ref<>();
    private Callbacks mCallbacks;

    interface Callbacks {
        boolean isMultiplePanes();
        void onJourneySelected(Journey journey, boolean multiplePanes);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mJourneys.value = savedInstanceState.getParcelableArrayList(KEY_JOURNEYS);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journey_list, container, false);
        Activity activity = getActivity();
        ((SosApplication) activity.getApplication()).getNetComponent().inject(this);

        mViewManager = new ViewManager();

        mJourneyRecyclerView = (RecyclerView) view.findViewById(R.id.journey_recycler_view);
        mJourneyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateUI();
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(mListener);

        if (mJourneys.value == null) {
            RefreshList();
        } else {
            mJourneyRecyclerView.setAdapter(new JourneyAdapter(mJourneys.value));
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_JOURNEYS, mJourneys.value);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_SETTINGS) {
            RefreshList();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_journey_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = SettingsActivity.newIntent(getActivity());
                startActivityForResult(intent, REQUEST_CODE_SETTINGS);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void RefreshList() {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
                mListener.onRefresh();
            }
        });
    }

    private void updateUI() {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String originName = preferences.getString(getString(R.string.pref_key_origin), null);
        Call<LocationRoot> originRoot = retrofit.create(RestApi.class).getLocation(originName);
        originRoot.enqueue(new Callback<LocationRoot>() {
            @Override
            public void onResponse(Call<LocationRoot> call, Response<LocationRoot> response) {
                final String originId =
                        response.body().getLocationList().getStopLocation().get(0).getId();
                final String destName = preferences.getString(getString(R.string.pref_key_destination), null);
                Call<LocationRoot> destRoot = retrofit.create(RestApi.class).getLocation(destName);
                destRoot.enqueue(new Callback<LocationRoot>() {
                    @Override
                    public void onResponse(Call<LocationRoot> call, Response<LocationRoot> response) {
                        final String destId =
                                response.body().getLocationList().getStopLocation().get(0).getId();
                        final String time = Util.formatTime(new Date());
                        final Call<TripRoot> tripRoot =
                                retrofit.create(RestApi.class).getTrip(originId, destId, time);
                        tripRoot.enqueue(new TripCallback(mJourneys));
                    }

                    @Override
                    public void onFailure(Call<LocationRoot> call, Throwable t) {
                        Log.w(TAG, "Failed to get destination from web service");
                    }
                });
            }

            @Override
            public void onFailure(Call<LocationRoot> call, Throwable t) {
                Log.w(TAG, "Failed to get origin from web service");
            }
        });
    }

    private class JourneyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int mIndex;
        private final TextView mDepartureTimeTextView;
        private final TextView mArrivalTimeTextView;
        private final TextView mDurationTextView;
        private final TextView mTransferCountTextView;
        private final TextView mWalkingDistanceTextView;
        private final LinearLayout mTransportTypesContainer;

        JourneyHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_journey, parent, false));
            itemView.setOnClickListener(this);

            mDepartureTimeTextView = (TextView) itemView.findViewById(R.id.departure_time_text_view);
            mArrivalTimeTextView = (TextView) itemView.findViewById(R.id.arrival_time_text_view);
            mDurationTextView = (TextView) itemView.findViewById(R.id.duration_text_view);
            mTransferCountTextView = (TextView) itemView.findViewById(R.id.transfer_count_text_view);
            mWalkingDistanceTextView = (TextView) itemView.findViewById(R.id.walking_distance_text_view);
            mTransportTypesContainer = (LinearLayout) itemView.findViewById(R.id.transport_types_container);
        }

        void bind(Journey journey) {
            mIndex = journey.getIndex();
            mDepartureTimeTextView.setText(Util.formatTime(journey.getDepartureTime()));
            mArrivalTimeTextView.setText(Util.formatTime(journey.getArrivalTime()));
            mDurationTextView.setText(Util.formatDuration(journey.getDuration()));
            mTransferCountTextView.setText(String.valueOf(journey.getTransferCount()));
            mWalkingDistanceTextView.setText(Util.formatDuration(journey.getWalkDuration()));
            for (Transportation t : journey.getTransportTypes()) {
                View view = mViewManager.createImageView(getActivity(), t);
                mTransportTypesContainer.addView(view);
            }
        }

        @Override
        public void onClick(View view) {
            mCallbacks.onJourneySelected(mJourneys.value.get(mIndex), mCallbacks.isMultiplePanes());
        }
    }

    private class JourneyAdapter extends RecyclerView.Adapter<JourneyHolder> {
        private final List<Journey> mJourneys;

        JourneyAdapter(List<Journey> journeys) {
            mJourneys = journeys;
        }

        @Override
        public JourneyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new JourneyHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(JourneyHolder holder, int position) {
            Journey journey = mJourneys.get(position);
            holder.bind(journey);
        }

        @Override
        public int getItemCount() {
            return mJourneys.size();
        }
    }

    private class TripCallback implements Callback<TripRoot> {
        private final Ref<ArrayList<Journey>> mJourneys;

        TripCallback(Ref<ArrayList<Journey>> journeys) {
            mJourneys = journeys;
        }

        @Override
        public void onResponse(Call<TripRoot> call, Response<TripRoot> response) {
            final List<Trip> trips = response.body().getTripList().getTrip();
            ArrayList<Journey> journeys = new ArrayList<>();
            int i = 0;
            for (Trip trip : trips) {
                final List<Leg> legs = trip.getLeg();
                long walkDuration = 0;
                ArrayList<Transportation> transportTypes = new ArrayList<>();
                ArrayList<Link> links = new ArrayList<>();
                for (Leg leg : legs) {
                    Origin orig = leg.getOrigin();
                    Destination dest = leg.getDestination();
                    String journeyDetailRef = null;
                    if (leg.getJourneyDetailRef() != null) {
                        journeyDetailRef = Util.getUriQueryParam(leg.getJourneyDetailRef().getRef());
                    }
                    String type = leg.getType();
                    String name = leg.getName();
                    if (type.equals("WALK")) {
                        walkDuration += Util.toDuration(orig.getTime(), dest.getTime());
                    }
                    Transportation transportType = mFactory.getTransportation(type);
                    if (!transportTypes.contains(transportType)) {
                        transportTypes.add(transportType);
                    }

                    Integer origRouteIdx = orig.getRouteIdx() == null ? -1 : orig.getRouteIdx();
                    Integer destRouteIdx = dest.getRouteIdx() == null ? -1 : dest.getRouteIdx();
                    Link link = new Link(
                            transportType,
                            type,
                            name,
                            new Node(orig.getName(), orig.getTrack(), orig.getTime(), origRouteIdx),
                            new Node(dest.getName(), dest.getTrack(), dest.getTime(), destRouteIdx),
                            journeyDetailRef);
                    links.add(link);
                }
                final Origin firstOrig = legs.get(0).getOrigin();
                final Destination lastDest = legs.get(legs.size() - 1).getDestination();
                final Journey journey = new Journey(i,
                        Util.toDate(firstOrig.getTime()),
                        Util.toDate(lastDest.getTime()),
                        Util.toDuration(firstOrig.getTime(), lastDest.getTime()),
                        legs.size(),
                        walkDuration,
                        transportTypes,
                        links);
                journeys.add(journey);
                i++;
            }
            mJourneys.value = journeys;
            mJourneyRecyclerView.setAdapter(new JourneyAdapter(mJourneys.value));
            if (mCallbacks.isMultiplePanes() && !mJourneys.value.isEmpty()) {
                mCallbacks.onJourneySelected(mJourneys.value.get(0), true);
            }
            updateLinks();
            mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onFailure(Call<TripRoot> call, Throwable t) {
            Log.w(TAG, "Failed to get trips from web service");
            mSwipeRefreshLayout.setRefreshing(false);
        }

        private void updateLinks() {
            for (Journey journey : mJourneys.value) {
                for (final Link link : journey.getLinks()) {
                    if (link.getType().equals("WALK")) {
                        continue; // No departure for walk
                    }
                    final String origName = link.getOriginName();
                    Call<LocationRoot> locationCall = retrofit.create(RestApi.class).getLocation(origName);
                    locationCall.enqueue(new Callback<LocationRoot>() {
                        @Override
                        public void onResponse(Call<LocationRoot> call, Response<LocationRoot> response) {
                            final String id =
                                    response.body().getLocationList().getStopLocation().get(0).getId();
                            final String departureTime = link.getDepartureTime();
                            final Call<DepartureBoardRoot> departureBoardCall =
                                    retrofit.create(RestApi.class).getDepartureBoard(id, departureTime);
                            departureBoardCall.enqueue(new DepartureBoardCallback(link));
                        }

                        @Override
                        public void onFailure(Call<LocationRoot> call, Throwable t) {
                            Log.w(TAG, "Failed to get location from web service");
                        }
                    });
                    Call<JourneyDetailRoot> journeyDetailCall =
                            retrofit.create(RestApi.class).getJourneyDetail(link.getJourneyDetailRef());
                    journeyDetailCall.enqueue(new JourneyDetailCallback(link));
                }
            }
        }
    }

    private class JourneyDetailCallback implements Callback<JourneyDetailRoot> {
        private final Link mLink;

        JourneyDetailCallback(Link link) {
            mLink = link;
        }

        @Override
        public void onResponse(Call<JourneyDetailRoot> call, Response<JourneyDetailRoot> response) {
            JourneyDetail journeyDetail = response.body().getJourneyDetail();
            List<Stop> stops = journeyDetail.getStop();
            Integer origRouteIdx = mLink.getOriginRouteIdx();
            if (origRouteIdx >= 0) {
                Stop origStop = stops.get(origRouteIdx);
                if (origStop.getRtTrack() != null) {
                    mLink.setDepartureRtTrack(origStop.getRtTrack());
                }
                if (origStop.getRtDepTime() != null) {
                    mLink.setDepartureRtTime(origStop.getRtDepTime());
                }
            }
            Integer destRouteIdx = mLink.getDestinationRouteIdx();
            if (destRouteIdx >= 0) {
                Stop destStop = stops.get(destRouteIdx);
                if (destStop.getRtTrack() != null) {
                    mLink.setArrivalRtTrack(destStop.getRtTrack());
                }
                if (destStop.getRtArrTime() != null) {
                    mLink.setArrivalRtTime(destStop.getRtArrTime());
                }
            }
        }

        @Override
        public void onFailure(Call<JourneyDetailRoot> call, Throwable t) {
            Log.w(TAG, "Failed to get journey detail from web service");
        }
    }

    private class DepartureBoardCallback implements Callback<DepartureBoardRoot> {
        private final Link mLink;

        DepartureBoardCallback(Link link) {
            mLink = link;
        }

        @Override
        public void onResponse(Call<DepartureBoardRoot> call, Response<DepartureBoardRoot> response) {
            if (response.body().getDepartureBoard().getError() != null) {
                return;
            }
            List<Departure> departures = response.body().getDepartureBoard().getDeparture();
            Departure departure = CollectionHelper.getDeparture(departures, mLink.getType(), mLink.getName());
            if (departure == null) {
                return;
            }
            mLink.setDirection(departure.getDirection());
            if (departure.getRtTrack() != null) {
                mLink.setDepartureRtTrack(departure.getRtTrack());
            }
            if (departure.getRtTime() != null) {
                mLink.setDepartureRtTime(departure.getRtTime());
            }
            final String destName = mLink.getDestinationName();
            Call<LocationRoot> locationCall = retrofit.create(RestApi.class).getLocation(destName);
            locationCall.enqueue(new Callback<LocationRoot>() {
                @Override
                public void onResponse(Call<LocationRoot> call, Response<LocationRoot> response) {
                    final String id =
                            response.body().getLocationList().getStopLocation().get(0).getId();
                    final String arrivalTime = mLink.getArrivalTime();
                    final Call<ArrivalBoardRoot> arrivalBoardCall =
                            retrofit.create(RestApi.class).getArrivalBoard(id, arrivalTime);
                    arrivalBoardCall.enqueue(new ArrivalBoardCallback(mLink));
                }

                @Override
                public void onFailure(Call<LocationRoot> call, Throwable t) {
                    Log.w(TAG, "Failed to get location from web service");
                }
            });
        }

        @Override
        public void onFailure(Call<DepartureBoardRoot> call, Throwable t) {
            Log.w(TAG, "Failed to get departure board from web service");
        }
    }

    private class ArrivalBoardCallback implements Callback<ArrivalBoardRoot> {
        private final Link mLink;

        ArrivalBoardCallback(Link link) {
            mLink = link;
        }

        @Override
        public void onResponse(Call<ArrivalBoardRoot> call, Response<ArrivalBoardRoot> response) {
            if (response.body().getArrivalBoard().getError() != null) {
                return;
            }
            List<Arrival> arrivals = response.body().getArrivalBoard().getArrival();
            Arrival arrival = CollectionHelper.getArrival(arrivals, mLink.getType(), mLink.getName());
            if (arrival == null) {
                return;
            }
            if (arrival.getRtTrack() != null) {
                mLink.setArrivalRtTrack(arrival.getRtTrack());
            }
            if (arrival.getRtTime() != null) {
                mLink.setArrivalRtTime(arrival.getRtTime());
            }
        }

        @Override
        public void onFailure(Call<ArrivalBoardRoot> call, Throwable t) {
            Log.w(TAG, "Failed to get arrival board from web service");
        }
    }
}
