package se.ocarina.straightonstrait.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import se.ocarina.straightonstrait.R;
import se.ocarina.straightonstrait.SosApplication;

public class JourneyFragment extends Fragment {

    private static final String ARG_JOURNEY = "journey";

    @Inject
    Retrofit retrofit;

    private ViewManager mViewManager;
    private RecyclerView mRecyclerView;
    private Journey mJourney;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SosApplication) getActivity().getApplication()).getNetComponent().inject(this);
        mJourney = getArguments().getParcelable(ARG_JOURNEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journey, container, false);

        mViewManager = new ViewManager();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.link_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(new LinkAdapter(mJourney.getLinks()));

        return view;
    }

    public static JourneyFragment newInstance(Journey journey) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_JOURNEY, journey);

        JourneyFragment fragment = new JourneyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class LinkHolder extends RecyclerView.ViewHolder {
        private final ImageView mTransportTypeImageView;
        private final TextView mTypeTextView;
        private final TextView mNameTextView;
        private final TextView mDirectionTextView;
        private final TextView mOriginNameTextView;
        private final TextView mOriginTrackTextView;
        private final TextView mOriginTimeTextView;
        private final TextView mOriginRtTrackTextView;
        private final TextView mOriginRtTimeTextView;
        private final TextView mDestinationNameTextView;
        private final TextView mDestinationTrackTextView;
        private final TextView mDestinationTimeTextView;
        private final TextView mDestinationRtTrackTextView;
        private final TextView mDestinationRtTimeTextView;

        LinkHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_link, parent, false));

            mTransportTypeImageView = (ImageView) itemView.findViewById(R.id.transport_type_image_view);
            mTypeTextView = (TextView) itemView.findViewById(R.id.type_text_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            mDirectionTextView = (TextView) itemView.findViewById(R.id.direction_text_view);
            mOriginNameTextView = (TextView) itemView.findViewById(R.id.origin_name_text_view);
            mOriginTrackTextView = (TextView) itemView.findViewById(R.id.origin_track_text_view);
            mOriginTimeTextView = (TextView) itemView.findViewById(R.id.origin_time_text_view);
            mOriginRtTrackTextView = (TextView) itemView.findViewById(R.id.origin_rt_track_text_view);
            mOriginRtTimeTextView = (TextView) itemView.findViewById(R.id.origin_rt_time_text_view);
            mDestinationNameTextView = (TextView) itemView.findViewById(R.id.destination_name_text_view);
            mDestinationTrackTextView = (TextView) itemView.findViewById(R.id.destination_track_text_view);
            mDestinationTimeTextView = (TextView) itemView.findViewById(R.id.destination_time_text_view);
            mDestinationRtTrackTextView = (TextView) itemView.findViewById(R.id.destination_rt_track_text_view);
            mDestinationRtTimeTextView = (TextView) itemView.findViewById(R.id.destination_rt_time_text_view);
        }

        void bind(Link link) {
            int resId = mViewManager.getImageResId(link.getTransportType());
            mTransportTypeImageView.setImageResource(resId);
            mTypeTextView.setText(link.getType());
            mNameTextView.setText(link.getName());
            mDirectionTextView.setText(link.getDirection());
            mOriginNameTextView.setText(link.getOriginName());
            mOriginTrackTextView.setText(link.getDepartureTrack());
            mOriginTimeTextView.setText(link.getDepartureTime());
            mOriginRtTrackTextView.setText(link.getDepartureRtTrack());
            mOriginRtTimeTextView.setText(link.getDepartureRtTime());
            mDestinationNameTextView.setText(link.getDestinationName());
            mDestinationTrackTextView.setText(link.getArrivalTrack());
            mDestinationTimeTextView.setText(link.getArrivalTime());
            mDestinationRtTrackTextView.setText(link.getArrivalRtTrack());
            mDestinationRtTimeTextView.setText(link.getArrivalRtTime());
        }
    }

    private class LinkAdapter extends RecyclerView.Adapter<JourneyFragment.LinkHolder> {
        private final List<Link> mLinks;

        LinkAdapter(List<Link> links) {
            mLinks = links;
        }

        @Override
        public JourneyFragment.LinkHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new JourneyFragment.LinkHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(JourneyFragment.LinkHolder holder, int position) {
            Link link = mLinks.get(position);
            holder.bind(link);
        }

        @Override
        public int getItemCount() {
            return mLinks.size();
        }
    }

}
