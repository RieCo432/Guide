package com.colinries.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class HowToFragment extends Fragment {

    public HowToFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_how_to, container, false);

        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();

        final Tracker tracker = ((Guide) getActivity().getApplication()).getTracker(Guide.TrackerName.APP_TRACKER);
        if (tracker != null) {
            tracker.setScreenName(getClass().getSimpleName());
            tracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        final Tracker tracker = ((Guide) getActivity().getApplication()).getTracker(Guide.TrackerName.APP_TRACKER);
        if (tracker != null) {
            tracker.setScreenName(getClass().getSimpleName());
            tracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }
}