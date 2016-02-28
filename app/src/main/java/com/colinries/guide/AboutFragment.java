package com.colinries.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.HashMap;

public class AboutFragment extends Fragment {

    public AboutFragment() {
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
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_about, container, false);

        String versionName = BuildConfig.VERSION_NAME;
        String versionCode = Integer.toString(BuildConfig.VERSION_CODE);
        String versionDate = BuildConfig.versionDate;

        String[][] listData = {
                {getString(R.string.app_name), getString(R.string.subheader)},
                {getString(R.string.version), versionName},
                {getString(R.string.build), versionCode},
                {getString(R.string.build_date), versionDate},
                {getString(R.string.developers), getString(R.string.developers_names)},
                {getString(R.string.copyright), getString(R.string.copyright_label)}
        };

        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        final ListView aboutView = (ListView) layout.findViewById(R.id.aboutListView);

        HashMap<String, String> item;
        for (String[] aListData: listData) {
            item = new HashMap<>();
            item.put("line1", aListData[0]);
            item.put("line2", aListData[1]);
            list.add(item);
        }

        ListAdapter myListAdapter = new SimpleAdapter(getActivity(), list,
                R.layout.two_line_list_item,
                new String[] {"line1", "line2"},
                new int[] {R.id.text1, R.id.text2});

        aboutView.setAdapter(myListAdapter);

        /*TextView line1 = (TextView) aboutView.findViewById(android.R.id.text1);
        TextView line2 = (TextView) aboutView.findViewById(android.R.id.text2);

        line1.setTextColor(getResources().getColor(R.color.colorPrimary));
        line2.setTextColor(getResources().getColor(R.color.colorPrimary));*/

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