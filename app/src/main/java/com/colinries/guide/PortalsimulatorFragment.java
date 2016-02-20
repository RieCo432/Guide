package com.colinries.guide;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;


public class PortalsimulatorFragment extends Fragment {

    public static int[] ResonatorLevels = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static String[] ModsAndRarity = new String[5];
    public static int resonatorSlotId;
    public static ImageView[] modSlot;
    public static SpinnerDialog selectResonatorLevelSpinner;
    public static SpinnerDialog selectModSpinner;
    public static ImageView[] resonatorSlot;
    public static int[] resonatorImages;
    public static int[] modImages;
    public static int modSlotId;
    public static String[] mods = new String[16];

    public static TextView portal_level;
    public static TextView portal_energy;
    public static TextView portal_range;
    public static TextView portal_hackspeed;
    public static TextView portal_burnoutinsulation;
    public static TextView portal_outgoinglinks;
    public static TextView portal_attackfreq;
    public static TextView portal_force;
    public static TextView portal_mitigation;
    public static EditText linkAmountInput;

    public static float[] decreasefactor1488 = {0f, 1f, 4f, 8f, 8f};
    public static float[] decreasefactor1222 = {0f, 1f, 2f, 2f, 2f};
    public static int[] resonatorEnergyLevel = {0,1000,1500,2000,2500,3000,4000,5000,6000};
    public static float[] linkAmpFactor = {0f, 7f, 5f, 2f};
    public static float[] heatSinkFactor = {0f, 0.7f, 0.5f, 0.2f};
    public static int[] multiHackInsulation = {0, 12, 8, 4};




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

        resonatorImages = new int[]{0,
                R.mipmap.item_resonator1,
                R.mipmap.item_resonator2,
                R.mipmap.item_resonator3,
                R.mipmap.item_resonator4,
                R.mipmap.item_resonator5,
                R.mipmap.item_resonator6,
                R.mipmap.item_resonator7,
                R.mipmap.item_resonator8};

        modImages = new int[] {0,
                R.mipmap.item_shieldc,
                R.mipmap.item_shieldr,
                R.mipmap.item_shieldvr,
                R.mipmap.item_shieldaxa,
                R.mipmap.item_linkampr,
                R.mipmap.item_linkampvr,
                R.mipmap.item_softbankultralinkvr,
                R.mipmap.item_heatsinkc,
                R.mipmap.item_heatsinkr,
                R.mipmap.item_heatsinkvr,
                R.mipmap.item_multihackc,
                R.mipmap.item_multihackr,
                R.mipmap.item_multihackvr,
                R.mipmap.item_forceampr,
                R.mipmap.item_turretr
        };

        portal_level = (TextView) layout.findViewById(R.id.portalLevelValue);
        portal_energy = (TextView) layout.findViewById(R.id.portalEnergyValue);
        portal_range = (TextView) layout.findViewById(R.id.portalRangeValue);
        portal_hackspeed = (TextView) layout.findViewById(R.id.portalHackSpeedValue);
        portal_burnoutinsulation = (TextView) layout.findViewById(R.id.portalBurnoutInsulationValue);
        portal_outgoinglinks = (TextView) layout.findViewById(R.id.portalOutgoingLinksValue);
        portal_attackfreq = (TextView) layout.findViewById(R.id.portalAttackFrequencyValue);
        portal_force = (TextView) layout.findViewById(R.id.portalForceValue);
        portal_mitigation = (TextView) layout.findViewById(R.id.portalMitigationValue);

        linkAmountInput = (EditText) layout.findViewById(R.id.linkAmmountInput);
        linkAmountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                updateProperties();
            }
        });

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

                mods = new String[]{null,
                        "csh",
                        "rsh",
                        "vrsh",
                        "axash",
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

        for (int i=1; i<=4; i++) {
            if (ModsAndRarity[i] != null) {
                modSlot[i].setImageResource(modImages[getIndex(mods, ModsAndRarity[i])]);
            } else {
                modSlot[i].setImageDrawable(null);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void updateProperties() {
        //Portal level
        int sum = 0;
        for (int i = 1; i <=8; i++) {
            sum = sum + ResonatorLevels[i];
        }
        float fLevel = ((float)sum)/8f;
        int level = (int) fLevel;
        if (level==0) level = 1;
        portal_level.setText(Integer.toString(level));

        //link range
        float portal_range_value = fLevel*fLevel*fLevel*fLevel*160f;
        String unit = "m";
        if (portal_range_value >= 1000f) {
            portal_range_value = portal_range_value/1000f;
            unit = "km";
        }


        float[] rangeFactorValues = {0f, 0f, 0f, 0f, 0f};
        int rangeFactorValuesIndex = 1;
        float rangeMultiplyFactor = 1;

        for (int i=1;i<=4;i++) {
            if (ModsAndRarity[i] != null) {
                if (ModsAndRarity[i].endsWith("la")) {
                    if (ModsAndRarity[i].startsWith("r")) {
                        rangeFactorValues[rangeFactorValuesIndex] = linkAmpFactor[3];
                        rangeFactorValuesIndex++;
                    } else if (ModsAndRarity[i].startsWith("vr")) {
                        rangeFactorValues[rangeFactorValuesIndex] = linkAmpFactor[1];
                        rangeFactorValuesIndex++;
                        rangeMultiplyFactor = 0f;
                    }
                }
                if (ModsAndRarity[i].equals("sbula")) {
                    rangeFactorValues[rangeFactorValuesIndex] = linkAmpFactor[2];
                    rangeFactorValuesIndex++;
                    rangeMultiplyFactor = 0f;
                }
            }
        }

        Arrays.sort(rangeFactorValues);
        int decreasefactorIndex = 1;


        for (int i=4;i>=1;i--) {
            rangeMultiplyFactor = rangeMultiplyFactor + (rangeFactorValues[i]/decreasefactor1488[decreasefactorIndex]);
            decreasefactorIndex++;
        }

        portal_range_value = portal_range_value * rangeMultiplyFactor;

        PortalsimulatorFragment.portal_range.setText(Float.toString(portal_range_value) + unit);

        //Mitigation

        int portal_mitigation_value = 0;

        for (int i=1;i<=4;i++) {
            if (ModsAndRarity[i] != null) {
                if (ModsAndRarity[i].endsWith("sh")) {
                    if (ModsAndRarity[i].startsWith("c"))
                        portal_mitigation_value = portal_mitigation_value + 30;
                    else if (ModsAndRarity[i].startsWith("r"))
                        portal_mitigation_value = portal_mitigation_value + 40;
                    else if (ModsAndRarity[i].startsWith("vr"))
                        portal_mitigation_value = portal_mitigation_value + 60;
                    else if (ModsAndRarity[i].startsWith("axa"))
                        portal_mitigation_value = portal_mitigation_value + 70;
                }
            }
        }

        //Outbound Links

        int portal_outgoinglinks_value = 8;

        for (int i=1;i<=4;i++) {
            if (ModsAndRarity[i] != null) {
                if (ModsAndRarity[i].equals("sbula")) portal_outgoinglinks_value = portal_outgoinglinks_value + 8;
            }
        }

        if (!linkAmountInput.getText().toString().equals("")) {
            float links = Float.valueOf(linkAmountInput.getText().toString());

            if (portal_outgoinglinks_value > 8) {
                portal_mitigation_value = portal_mitigation_value + (int) (Math.floor(400f / 9f * Math.atan(links / Math.E)) * 1.5f);
            } else {
                portal_mitigation_value = portal_mitigation_value + (int) (Math.floor(400f / 9f * Math.atan(links / Math.E)));
            }
        }


        portal_mitigation.setText(Integer.toString(portal_mitigation_value));

        portal_outgoinglinks.setText(Integer.toString(portal_outgoinglinks_value));

        //Energy

        int portal_energy_value = 0;

        for (int i=1;i<=8;i++) {
            if (ResonatorLevels[i] != 0) {
                portal_energy_value = portal_energy_value + resonatorEnergyLevel[ResonatorLevels[i]];
            }
        }

        portal_energy.setText(Integer.toString(portal_energy_value));

        //Force

        boolean[] forceamps = {false, false, false, false, false};
        int forceampsindex = 1;
        float portal_force_value = 1;

        for (int i=1;i<=4;i++) {
            if (ModsAndRarity[i] != null) {
                if (ModsAndRarity[i].endsWith("fa")) {
                    forceamps[forceampsindex] = true;
                    forceampsindex++;
                    portal_force_value = 0f;
                }
            }
        }

        for (int i=1; i<=4;i++) {
            if (forceamps[i]) {
                portal_force_value = portal_force_value + (2f/decreasefactor1488[i]);
            }
        }

        portal_force.setText(Float.toString(portal_force_value) + "x");


        //AttackFreq

        boolean[] turrets = {false, false, false, false, false};
        int turretsindex = 1;
        float portal_attackfreq_value = 1f;

        for (int i=1;i<=4;i++) {
            if (ModsAndRarity[i] != null) {
                if (ModsAndRarity[i].endsWith("t")) {
                    turrets[turretsindex] = true;
                    turretsindex++;
                    portal_attackfreq_value = 0f;
                }
            }
        }

        for (int i=1; i<=4;i++) {
            if (turrets[i]) {
                portal_attackfreq_value = portal_attackfreq_value + (2f/decreasefactor1488[i]);
            }
        }

        portal_attackfreq.setText(Float.toString(portal_attackfreq_value) + "x");

        //Hack Speed

        float[] hackSpeedFactors = {0f, 0f, 0f, 0f, 0f};
        int hackSpeedFactorIndex = 1;

        for (int i=1;i<=4;i++) {
            if (ModsAndRarity[i] != null) {
                if (ModsAndRarity[i].endsWith("hs")) {
                    if (ModsAndRarity[i].startsWith("c")) {
                        hackSpeedFactors[hackSpeedFactorIndex] = heatSinkFactor[3];
                        hackSpeedFactorIndex++;
                    } else if (ModsAndRarity[i].startsWith("r")) {
                        hackSpeedFactors[hackSpeedFactorIndex] = heatSinkFactor[2];
                        hackSpeedFactorIndex++;
                    } else if (ModsAndRarity[i].startsWith("vr")) {
                        hackSpeedFactors[hackSpeedFactorIndex] = heatSinkFactor[1];
                        hackSpeedFactorIndex++;
                    }
                }
            }
        }

        Arrays.sort(hackSpeedFactors);
        decreasefactorIndex = 1;
        float portal_hackspeed_value = 300f;

        for (int i=4;i>=1;i--) {
            if (hackSpeedFactors[i] != 0f) {
                portal_hackspeed_value = portal_hackspeed_value *(1f-((hackSpeedFactors[i] )/ decreasefactor1222[decreasefactorIndex]));
                decreasefactorIndex++;
            }
        }

        portal_hackspeed.setText(Integer.toString(Math.round(portal_hackspeed_value)) + "s");

        //Burnout insulation

        float[] burnoutInsulationValue = {0f, 0f, 0f, 0f, 0f};
        int burnoutInsulationValueIndex = 1;

        for (int i=1;i<=4;i++) {
            if (ModsAndRarity[i] != null) {
                if (ModsAndRarity[i].endsWith("mh")) {
                    if (ModsAndRarity[i].startsWith("c")) {
                        burnoutInsulationValue[burnoutInsulationValueIndex] = multiHackInsulation[3];
                        burnoutInsulationValueIndex++;
                    } else if (ModsAndRarity[i].startsWith("r")) {
                        burnoutInsulationValue[burnoutInsulationValueIndex] = multiHackInsulation[2];
                        burnoutInsulationValueIndex++;
                    } else if (ModsAndRarity[i].startsWith("vr")) {
                        burnoutInsulationValue[burnoutInsulationValueIndex] = multiHackInsulation[1];
                        burnoutInsulationValueIndex++;
                    }
                }
            }
        }

        Arrays.sort(burnoutInsulationValue);
        decreasefactorIndex = 1;
        int portal_burnoutinsulation_value = 4;

        for (int i=4;i>=1;i--) {
            portal_burnoutinsulation_value =  portal_burnoutinsulation_value + (int) ((burnoutInsulationValue[i] )/ decreasefactor1222[decreasefactorIndex]);
            decreasefactorIndex++;
        }

        portal_burnoutinsulation.setText(Integer.toString(portal_burnoutinsulation_value));

    }

    public int getIndex(String[] array, String value) {
        if (value != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    if (array[i].equals(value)) return i;
                }
            }
        }
        return 0;
    }
}
