package com.colinries.guide;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ItemFragment extends Fragment {

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String[] itemNames = {
                getString(R.string.resonators),
                getString(R.string.xmps),
                getString(R.string.ultrastrikes),
                getString(R.string.adarefactor),
                getString(R.string.jarvisvirus),
                getString(R.string.powercubes),
                getString(R.string.shields),
                getString(R.string.axashields),
                getString(R.string.heatsinks),
                getString(R.string.multihacks),
                getString(R.string.forceamps),
                getString(R.string.turrets),
                getString(R.string.linkamps),
                getString(R.string.softbankultralinkamps),
                getString(R.string.capsules),
                getString(R.string.mufgcapsules),
                getString(R.string.fracker),
                getString(R.string.keylocker),
                getString(R.string.beacons),
                getString(R.string.cmus)
        };

        Integer[] itemIcons = {
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified
        };

        // Inflate the layout for this fragment
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_item, container, false);

        ListView selectItemList = (ListView) layout.findViewById(R.id.selectItemList);

        //selectItemList.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.select_item_list, R.id.itemName, itemNames, itemIcons));
        selectItemList.setAdapter(new customListAdapter(getActivity(), itemNames,itemIcons));
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        selectItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                if(position == 0) {
                    fragmentTransaction.replace(R.id.fragment_container, new SampleItemPage()).commit();
                }
            }
        });

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