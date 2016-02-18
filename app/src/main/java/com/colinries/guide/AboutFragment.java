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
                {getString(R.string.builddate), versionDate},
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
                android.R.layout.simple_list_item_2,
                new String[] {"line1", "line2"},
                new int[] {android.R.id.text1, android.R.id.text2});

        aboutView.setAdapter(myListAdapter);

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
}