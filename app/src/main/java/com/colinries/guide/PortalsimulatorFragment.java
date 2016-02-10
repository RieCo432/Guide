package com.colinries.guide;


import android.database.DataSetObserver;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;


public class PortalsimulatorFragment extends Fragment {

    public static int[] ResonatorLevels = new int[9];
    public static String[] ModsAndRarity = new String[4]; //f.ex. shieldrare, shieldveryrare, shieldaxa, linkamprare, forceamprare
    public static int[] PortalProperties = new int[9]; //level, energy, mitigation, range, outbound links, hack speed, burnout limit, force, attack freq
    public static int resonatorSlotId;
    public static SpinnerDialog selectResonatorLevelSpinner;
    public static ImageButton[] resonatorSlot;
    public static int[] resonatorImages;


    public PortalsimulatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_portalsimulator, container, false);

        resonatorSlot = new ImageButton[]{null, (ImageButton) layout.findViewById(R.id.resonatorSlot1),
                (ImageButton) layout.findViewById(R.id.resonatorSlot2),
                (ImageButton) layout.findViewById(R.id.resonatorSlot3),
                (ImageButton) layout.findViewById(R.id.resonatorSlot4),
                (ImageButton) layout.findViewById(R.id.resonatorSlot5),
                (ImageButton) layout.findViewById(R.id.resonatorSlot6),
                (ImageButton) layout.findViewById(R.id.resonatorSlot7),
                (ImageButton) layout.findViewById(R.id.resonatorSlot8)};

        resonatorImages = new int[]{0, R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified,
                R.mipmap.verified};

        //TODO: Add all onClickListeners and item select handlers
        View.OnClickListener ResonatorSelectorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.i("GUIDE:", "onClick");
                resonatorSlotId = v.getId();
                /*Log.i("GUIDE:", "getId");
                selectResonatorLevelSpinner.setVisibility(View.VISIBLE);
                Log.i("GUIDE:", "setVisibility");*/

                String[] levels = {"Select Level", "1","2","3","4","5","6","7","8"};
                //int[] resonator_images = {R.mipmap.verified,R.mipmap.verified,R.mipmap.verified,R.mipmap.verified,R.mipmap.verified,R.mipmap.verified,R.mipmap.verified,R.mipmap.verified};

                selectResonatorLevelSpinner = new SpinnerDialog(getContext(), levels, new SpinnerDialog.DialogListener() {
                    public void cancelled() {
                        Log.i("GUIDE:", "cancelled");

                    }
                    public void ready(int position) {
                        Log.i("GUIDE:", "ready " + Integer.toString(position));

                        Log.i("GUIDE", Integer.toString(resonatorSlotId) + "    " + Integer.toString(R.id.resonatorSlot1));

                        switch (resonatorSlotId) {
                            case R.id.resonatorSlot1:
                                ResonatorLevels[1] = position;
                                Log.i("GUIDE", "True");
                                break;
                            case R.id.resonatorSlot2:
                                ResonatorLevels[2] = position;
                                break;
                            case R.id.resonatorSlot3:
                                ResonatorLevels[3] = position;
                                break;
                            case R.id.resonatorSlot4:
                                ResonatorLevels[4] = position;
                                break;
                            case R.id.resonatorSlot5:
                                ResonatorLevels[5] = position;
                                break;
                            case R.id.resonatorSlot6:
                                ResonatorLevels[6] = position;
                                break;
                            case R.id.resonatorSlot7:
                                ResonatorLevels[7] = position;
                                break;
                            case R.id.resonatorSlot8:
                                ResonatorLevels[8] = position;
                                break;
                        }
                        Log.i("GUIDE", "switch complete");

                        updateImages();
                        //updateProperties();

                    }
                });

                selectResonatorLevelSpinner.show();

                }

            };

        for (int i=1;i<=8;i++) {
            resonatorSlot[i].setOnClickListener(ResonatorSelectorListener);
        }

        /*layout.findViewById(R.id.resonatorSlot1).setOnClickListener(ResonatorSelectorListener);
        layout.findViewById(R.id.resonatorSlot2).setOnClickListener(ResonatorSelectorListener);
        layout.findViewById(R.id.resonatorSlot3).setOnClickListener(ResonatorSelectorListener);
        layout.findViewById(R.id.resonatorSlot4).setOnClickListener(ResonatorSelectorListener);
        layout.findViewById(R.id.resonatorSlot5).setOnClickListener(ResonatorSelectorListener);
        layout.findViewById(R.id.resonatorSlot6).setOnClickListener(ResonatorSelectorListener);
        layout.findViewById(R.id.resonatorSlot7).setOnClickListener(ResonatorSelectorListener);
        layout.findViewById(R.id.resonatorSlot8).setOnClickListener(ResonatorSelectorListener);*/

        //TODO: Add Formulas

        //TODO: Put results into TextViews

        return layout;
    }

    public void updateImages() {
        for (int i = 1; i<=8;i++) {
            resonatorSlot[i].setImageResource(resonatorImages[ResonatorLevels[i]]);
            Log.i("GUIDE", Integer.toString(i) + Integer.toString(ResonatorLevels[i]));
        }
    }
}
