package com.colinries.guide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class PortalsimulatorFragment extends Fragment {

    public static int[] ResonatorLevels = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static String[] ModsAndRarity = new String[5];
    public static int[] PortalProperties = new int[9]; //level, energy, mitigation, range, outbound links, hack speed, burnout limit, force, attack freq
    public static int resonatorSlotId;
    public static ImageView[] modSlot;
    public static SpinnerDialog selectResonatorLevelSpinner;
    public static SpinnerDialog selectModSpinner;
    public static ImageView[] resonatorSlot;
    public static int[] resonatorImages;
    public static int modSlotId;

    public static TextView portal_level;
    public static TextView portal_energy;
    public static TextView portal_range;
    public static TextView portal_hackspeed;
    public static TextView portal_burnoutinsulation;
    public static TextView portal_outgoinglinks;
    public static TextView portal_attackfreq;
    public static TextView portal_force;




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

        resonatorSlot = new ImageView[]{null,
                (ImageView) layout.findViewById(R.id.resonatorSlot1),
                (ImageView) layout.findViewById(R.id.resonatorSlot2),
                (ImageView) layout.findViewById(R.id.resonatorSlot3),
                (ImageView) layout.findViewById(R.id.resonatorSlot4),
                (ImageView) layout.findViewById(R.id.resonatorSlot5),
                (ImageView) layout.findViewById(R.id.resonatorSlot6),
                (ImageView) layout.findViewById(R.id.resonatorSlot7),
                (ImageView) layout.findViewById(R.id.resonatorSlot8)};

        modSlot = new ImageView[]{null,
                (ImageView) layout.findViewById(R.id.modSlot1),
                (ImageView) layout.findViewById(R.id.modSlot2),
                (ImageView) layout.findViewById(R.id.modSlot3),
                (ImageView) layout.findViewById(R.id.modSlot4)};

        resonatorImages = new int[]{0, R.mipmap.item_resonator8,
                R.mipmap.item_resonator8,
                R.mipmap.item_resonator8,
                R.mipmap.item_resonator8,
                R.mipmap.item_resonator8,
                R.mipmap.item_resonator8,
                R.mipmap.item_resonator8,
                R.mipmap.item_resonator8};

        portal_level = (TextView) layout.findViewById(R.id.portalLevelValue);
        portal_energy = (TextView) layout.findViewById(R.id.portalEnergyValue);
        portal_range = (TextView) layout.findViewById(R.id.portalRangeValue);
        portal_hackspeed = (TextView) layout.findViewById(R.id.portalHackSpeedValue);
        portal_burnoutinsulation = (TextView) layout.findViewById(R.id.portalBurnoutInsulationValue);
        portal_outgoinglinks = (TextView) layout.findViewById(R.id.portalOutgoingLinksValue);
        portal_attackfreq = (TextView) layout.findViewById(R.id.portalAttackFrequencyValue);
        portal_force = (TextView) layout.findViewById(R.id.portalForceValue);

        //TODO: Add all onClickListeners and item select handlers
        View.OnClickListener ResonatorSelectorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resonatorSlotId = v.getId();

                String[] levels = {"Select Level", "1","2","3","4","5","6","7","8"};

                selectResonatorLevelSpinner = new SpinnerDialog(getContext(), levels, new SpinnerDialog.DialogListener() {
                    public void cancelled() {
                        Log.i("GUIDE:", "cancelled");

                    }
                    public void ready(int position) {

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
                        updateProperties();

                    }
                });

                selectResonatorLevelSpinner.show();

                }

            };

        View.OnClickListener ModSelectorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modSlotId = v.getId();

                final String[] mods_friendly = {"Select Mod",
                        "Common Shield",
                        "Rare Shield",
                        "Very Rare Shield",
                        "AXA Shield",
                        "Rare Link Amp",
                        "Very Rare Link Amp",
                        "Softbank Ultra Link",
                        "Common Heatsink",
                        "Rare Heatsink",
                        "Very Rare Heatsink",
                        "Common Multi Hack",
                        "Rare Multi Hack",
                        "Very Rare Multi Hack",
                        "Force Amp",
                        "Turret"
                };

                final String[] mods = {null,
                        "cs",
                        "rs",
                        "vrs",
                        "axas",
                        "rla",
                        "vrla",
                        "sbula",
                        "chs",
                        "rhs",
                        "vrhs",
                        "cmh",
                        "rmh",
                        "vrmh",
                        "rfa",
                        "rt"
                };

                selectModSpinner = new SpinnerDialog(getContext(), mods_friendly, new SpinnerDialog.DialogListener() {

                    public void cancelled() {
                    }


                    public void ready(int n) {
                        switch (modSlotId) {
                            case R.id.modSlot1:
                                ModsAndRarity[1]=mods[n];
                                break;
                            case R.id.modSlot2:
                                ModsAndRarity[2]=mods[n];
                                break;
                            case R.id.modSlot3:
                                ModsAndRarity[3]=mods[n];
                                break;
                            case R.id.modSlot4:
                                ModsAndRarity[4]=mods[n];
                                break;
                        }
                        updateImages();
                        updateProperties();
                    }

                });

                selectModSpinner.show();

            }
        };

        for (int i=1;i<=8;i++) {
            resonatorSlot[i].setOnClickListener(ResonatorSelectorListener);
        }

        for (int i=1; i<=4; i++) {
            modSlot[i].setOnClickListener(ModSelectorListener);
        }

        //TODO: Add Formulas

        //TODO: Put results into TextViews

        return layout;
    }

    public void updateImages() {
        for (int i = 1; i<=8; i++) {
            if (resonatorImages[ResonatorLevels[i]] != 0) {
                resonatorSlot[i].setImageResource(resonatorImages[ResonatorLevels[i]]);
            } else {
                resonatorSlot[i].setImageDrawable(null);
            }
        }
    }

    public void updateProperties() {
        //Portal level
        int sum = 0;
        for (int i = 1; i <=8; i++) {
            sum = sum + ResonatorLevels[i];
        }
        float fLevel = sum/8;
        int level = (int) fLevel;
        if (level==0) level = 1;
        portal_level.setText(Integer.toString(level));

        //link range
        float range = fLevel*fLevel*fLevel*fLevel*160;
        String unit = "m";
        if (range >= 1000f) {
            range = range/1000f;
            unit = "km";
        }
        portal_range.setText(Float.toString(range) + unit);
    }
}
