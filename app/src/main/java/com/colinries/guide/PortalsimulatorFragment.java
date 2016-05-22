package com.colinries.guide;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Arrays;

public class PortalsimulatorFragment extends Fragment {

    public static int[] ResonatorLevels = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static String[] ModsAndRarity = {null, null, null, null, null};
    public static ImageView[] modSlot;
    public static ImageView[] resonatorSlot;
    public static int[] resonatorImages;
    public static int[] modImages;
    public static String[] mods = {null, "csh","rsh","vrsh","axash","rla","vrla","sbula","chs","rhs","vrhs","cmh","rmh","vrmh","rfa","rt"};

    public static View.OnClickListener resonatorClickListener;
    public static View.OnClickListener modClickListener;
    public static View.OnClickListener linkClickListener;

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

    public static View slotView;

    public static View resonatorSlotView1;
    public static View resonatorSlotView2;
    public static View resonatorSlotView3;
    public static View resonatorSlotView4;
    public static View resonatorSlotView5;
    public static View resonatorSlotView6;
    public static View resonatorSlotView7;
    public static View resonatorSlotView8;

    public static View modSlotView1;
    public static View modSlotView2;
    public static View modSlotView3;
    public static View modSlotView4;

    public Tracker t;

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

        t = ((Guide)getActivity().getApplication()).getTracker(Guide.TrackerName.APP_TRACKER);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(sharedPreferences.getBoolean("show_simulator_hint", true)) {

            new AlertDialog.Builder(getActivity())
                    .setTitle(getString(R.string.sim_hint_title))
                    .setMessage(getString(R.string.sim_hint))
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.okay), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(R.mipmap.ic_launcher)
                    .show();

            sharedPreferences.edit().putBoolean("show_simulator_hint", false).apply();
        }

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

        resonatorSlotView1 = layout.findViewById(R.id.resonatorSlot1);
        resonatorSlotView2 = layout.findViewById(R.id.resonatorSlot2);
        resonatorSlotView3 = layout.findViewById(R.id.resonatorSlot3);
        resonatorSlotView4 = layout.findViewById(R.id.resonatorSlot4);
        resonatorSlotView5 = layout.findViewById(R.id.resonatorSlot5);
        resonatorSlotView6 = layout.findViewById(R.id.resonatorSlot6);
        resonatorSlotView7 = layout.findViewById(R.id.resonatorSlot7);
        resonatorSlotView8 = layout.findViewById(R.id.resonatorSlot8);

        modSlotView1 = layout.findViewById(R.id.modSlot1);
        modSlotView2 = layout.findViewById(R.id.modSlot2);
        modSlotView3 = layout.findViewById(R.id.modSlot3);
        modSlotView4 = layout.findViewById(R.id.modSlot4);

        registerForContextMenu(resonatorSlotView1);
        registerForContextMenu(resonatorSlotView2);
        registerForContextMenu(resonatorSlotView3);
        registerForContextMenu(resonatorSlotView4);
        registerForContextMenu(resonatorSlotView5);
        registerForContextMenu(resonatorSlotView6);
        registerForContextMenu(resonatorSlotView7);
        registerForContextMenu(resonatorSlotView8);

        registerForContextMenu(modSlotView1);
        registerForContextMenu(modSlotView2);
        registerForContextMenu(modSlotView3);
        registerForContextMenu(modSlotView4);

        resonatorClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.resonatorSlot1:
                        if(ResonatorLevels[1]<8) ResonatorLevels[1]++; else ResonatorLevels[1] = 0;
                        break;
                    case R.id.resonatorSlot2:
                        if(ResonatorLevels[2]<8) ResonatorLevels[2]++; else ResonatorLevels[2] = 0;
                        break;
                    case R.id.resonatorSlot3:
                        if(ResonatorLevels[3]<8) ResonatorLevels[3]++; else ResonatorLevels[3] = 0;
                        break;
                    case R.id.resonatorSlot4:
                        if(ResonatorLevels[4]<8) ResonatorLevels[4]++; else ResonatorLevels[4] = 0;
                        break;
                    case R.id.resonatorSlot5:
                        if(ResonatorLevels[5]<8) ResonatorLevels[5]++; else ResonatorLevels[5] = 0;
                        break;
                    case R.id.resonatorSlot6:
                        if(ResonatorLevels[6]<8) ResonatorLevels[6]++; else ResonatorLevels[6] = 0;
                        break;
                    case R.id.resonatorSlot7:
                        if(ResonatorLevels[7]<8) ResonatorLevels[7]++; else ResonatorLevels[7] = 0;
                        break;
                    case R.id.resonatorSlot8:
                        if(ResonatorLevels[8]<8) ResonatorLevels[8]++; else ResonatorLevels[8] = 0;
                        break;
                }
                updateImages();
                updateProperties();
            }
        };

        modClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.modSlot1:
                        if(getIndex(mods, ModsAndRarity[1]) < 15) ModsAndRarity[1] = mods[getIndex(mods, ModsAndRarity[1]) + 1]; else ModsAndRarity[1] = mods[0];
                        break;
                    case R.id.modSlot2:
                        if(getIndex(mods, ModsAndRarity[2]) < 15) ModsAndRarity[2] = mods[getIndex(mods, ModsAndRarity[2]) + 1]; else ModsAndRarity[2] = mods[0];
                        break;
                    case R.id.modSlot3:
                        if(getIndex(mods, ModsAndRarity[3]) < 15) ModsAndRarity[3] = mods[getIndex(mods, ModsAndRarity[3]) + 1]; else ModsAndRarity[3] = mods[0];
                        break;
                    case R.id.modSlot4:
                        if(getIndex(mods, ModsAndRarity[4]) < 15) ModsAndRarity[4] = mods[getIndex(mods, ModsAndRarity[4]) + 1]; else ModsAndRarity[4] = mods[0];
                        break;
                }
                updateImages();
                updateProperties();
            }
        };

        linkClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkAmountInput.setText(Integer.toString(Integer.valueOf(String.valueOf(linkAmountInput.getText()))+1));
                updateProperties();
            }
        };

        resonatorSlotView1.setOnClickListener(resonatorClickListener);
        resonatorSlotView2.setOnClickListener(resonatorClickListener);
        resonatorSlotView3.setOnClickListener(resonatorClickListener);
        resonatorSlotView4.setOnClickListener(resonatorClickListener);
        resonatorSlotView5.setOnClickListener(resonatorClickListener);
        resonatorSlotView6.setOnClickListener(resonatorClickListener);
        resonatorSlotView7.setOnClickListener(resonatorClickListener);
        resonatorSlotView8.setOnClickListener(resonatorClickListener);

        modSlotView1.setOnClickListener(modClickListener);
        modSlotView2.setOnClickListener(modClickListener);
        modSlotView3.setOnClickListener(modClickListener);
        modSlotView4.setOnClickListener(modClickListener);

        linkAmountInput.setOnClickListener(linkClickListener);

        return layout;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();

        if(v.getId()==resonatorSlotView1.getId() || v.getId()==resonatorSlotView2.getId()
                ||v.getId()==resonatorSlotView3.getId() || v.getId()==resonatorSlotView4.getId()
                || v.getId()==resonatorSlotView5.getId() || v.getId()==resonatorSlotView6.getId()
                || v.getId()==resonatorSlotView7.getId() || v.getId()==resonatorSlotView8.getId() ) {
            inflater.inflate(R.menu.select_resonator_context_menu, menu);
        } else if(v.getId()==modSlotView1.getId() || v.getId()==modSlotView2.getId()
            ||v.getId()==modSlotView3.getId() || v.getId()==modSlotView4.getId()) {
            inflater.inflate(R.menu.select_mod_context_menu, menu);
        }

        slotView = v;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(slotView.getId() == resonatorSlotView1.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[1] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[1] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[1] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[1] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[1] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[1] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[1] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[1] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[1] = 8;
                    break;
            }
        } else if(slotView.getId() == resonatorSlotView2.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[2] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[2] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[2] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[2] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[2] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[2] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[2] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[2] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[2] = 8;
                    break;
            }
        } else if(slotView.getId() == resonatorSlotView3.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[3] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[3] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[3] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[3] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[3] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[3] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[3] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[3] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[3] = 8;
                    break;
            }
        } else if(slotView.getId() == resonatorSlotView4.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[4] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[4] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[4] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[4] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[4] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[4] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[4] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[4] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[4] = 8;
                    break;
            }
        } else if(slotView.getId() == resonatorSlotView5.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[5] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[5] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[5] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[5] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[5] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[5] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[5] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[5] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[5] = 8;
                    break;
            }
        } else if(slotView.getId() == resonatorSlotView6.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[6] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[6] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[6] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[6] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[6] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[6] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[6] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[6] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[6] = 8;
                    break;
            }
        } else if(slotView.getId() == resonatorSlotView7.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[7] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[7] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[7] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[7] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[7] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[7] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[7] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[7] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[7] = 8;
                    break;
            }
        } else if(slotView.getId() == resonatorSlotView8.getId()) {
            switch(item.getItemId()) {
                case R.id.select_none:
                    ResonatorLevels[8] = 0;
                    break;
                case R.id.select_l1:
                    ResonatorLevels[8] = 1;
                    break;
                case R.id.select_l2:
                    ResonatorLevels[8] = 2;
                    break;
                case R.id.select_l3:
                    ResonatorLevels[8] = 3;
                    break;
                case R.id.select_l4:
                    ResonatorLevels[8] = 4;
                    break;
                case R.id.select_l5:
                    ResonatorLevels[8] = 5;
                    break;
                case R.id.select_l6:
                    ResonatorLevels[8] = 6;
                    break;
                case R.id.select_l7:
                    ResonatorLevels[8] = 7;
                    break;
                case R.id.select_l8:
                    ResonatorLevels[8] = 8;
                    break;
            }
        }  else if(slotView.getId() == modSlotView1.getId()) {
            switch (item.getItemId()) {
                case R.id.select_none:
                    ModsAndRarity[1]=mods[0];
                    break;
                case R.id.select_csh:
                    ModsAndRarity[1]=mods[1];
                    break;
                case R.id.select_rsh:
                    ModsAndRarity[1]=mods[2];
                    break;
                case R.id.select_vrsh:
                    ModsAndRarity[1]=mods[3];
                    break;
                case R.id.select_axash:
                    ModsAndRarity[1]=mods[4];
                    break;
                case R.id.select_rla:
                    ModsAndRarity[1]=mods[5];
                    break;
                case R.id.select_vrla:
                    ModsAndRarity[1]=mods[6];
                    break;
                case R.id.select_sbula:
                    ModsAndRarity[1]=mods[7];
                    break;
                case R.id.select_chs:
                    ModsAndRarity[1]=mods[8];
                    break;
                case R.id.select_rhs:
                    ModsAndRarity[1]=mods[9];
                    break;
                case R.id.select_vrhs:
                    ModsAndRarity[1]=mods[10];
                    break;
                case R.id.select_cmh:
                    ModsAndRarity[1]=mods[11];
                    break;
                case R.id.select_rmh:
                    ModsAndRarity[1]=mods[12];
                    break;
                case R.id.select_vrmh:
                    ModsAndRarity[1]=mods[13];
                    break;
                case R.id.select_rfa:
                    ModsAndRarity[1]=mods[14];
                    break;
                case R.id.select_rt:
                    ModsAndRarity[1]=mods[15];
                    break;
            }
        }  else if(slotView.getId() == modSlotView2.getId()) {
            switch (item.getItemId()) {
                case R.id.select_none:
                    ModsAndRarity[2]=mods[0];
                    break;
                case R.id.select_csh:
                    ModsAndRarity[2]=mods[1];
                    break;
                case R.id.select_rsh:
                    ModsAndRarity[2]=mods[2];
                    break;
                case R.id.select_vrsh:
                    ModsAndRarity[2]=mods[3];
                    break;
                case R.id.select_axash:
                    ModsAndRarity[2]=mods[4];
                    break;
                case R.id.select_rla:
                    ModsAndRarity[2]=mods[5];
                    break;
                case R.id.select_vrla:
                    ModsAndRarity[2]=mods[6];
                    break;
                case R.id.select_sbula:
                    ModsAndRarity[2]=mods[7];
                    break;
                case R.id.select_chs:
                    ModsAndRarity[2]=mods[8];
                    break;
                case R.id.select_rhs:
                    ModsAndRarity[2]=mods[9];
                    break;
                case R.id.select_vrhs:
                    ModsAndRarity[2]=mods[10];
                    break;
                case R.id.select_cmh:
                    ModsAndRarity[2]=mods[11];
                    break;
                case R.id.select_rmh:
                    ModsAndRarity[2]=mods[12];
                    break;
                case R.id.select_vrmh:
                    ModsAndRarity[2]=mods[13];
                    break;
                case R.id.select_rfa:
                    ModsAndRarity[2]=mods[14];
                    break;
                case R.id.select_rt:
                    ModsAndRarity[2]=mods[15];
                    break;
            }
        }  else if(slotView.getId() == modSlotView3.getId()) {
            switch (item.getItemId()) {
                case R.id.select_none:
                    ModsAndRarity[3]=mods[0];
                    break;
                case R.id.select_csh:
                    ModsAndRarity[3]=mods[1];
                    break;
                case R.id.select_rsh:
                    ModsAndRarity[3]=mods[2];
                    break;
                case R.id.select_vrsh:
                    ModsAndRarity[3]=mods[3];
                    break;
                case R.id.select_axash:
                    ModsAndRarity[3]=mods[4];
                    break;
                case R.id.select_rla:
                    ModsAndRarity[3]=mods[5];
                    break;
                case R.id.select_vrla:
                    ModsAndRarity[3]=mods[6];
                    break;
                case R.id.select_sbula:
                    ModsAndRarity[3]=mods[7];
                    break;
                case R.id.select_chs:
                    ModsAndRarity[3]=mods[8];
                    break;
                case R.id.select_rhs:
                    ModsAndRarity[3]=mods[9];
                    break;
                case R.id.select_vrhs:
                    ModsAndRarity[3]=mods[10];
                    break;
                case R.id.select_cmh:
                    ModsAndRarity[3]=mods[11];
                    break;
                case R.id.select_rmh:
                    ModsAndRarity[3]=mods[12];
                    break;
                case R.id.select_vrmh:
                    ModsAndRarity[3]=mods[13];
                    break;
                case R.id.select_rfa:
                    ModsAndRarity[3]=mods[14];
                    break;
                case R.id.select_rt:
                    ModsAndRarity[3]=mods[15];
                    break;
            }
        }  else if(slotView.getId() == modSlotView4.getId()) {
            switch (item.getItemId()) {
                case R.id.select_none:
                    ModsAndRarity[4]=mods[0];
                    break;
                case R.id.select_csh:
                    ModsAndRarity[4]=mods[1];
                    break;
                case R.id.select_rsh:
                    ModsAndRarity[4]=mods[2];
                    break;
                case R.id.select_vrsh:
                    ModsAndRarity[4]=mods[3];
                    break;
                case R.id.select_axash:
                    ModsAndRarity[4]=mods[4];
                    break;
                case R.id.select_rla:
                    ModsAndRarity[4]=mods[5];
                    break;
                case R.id.select_vrla:
                    ModsAndRarity[4]=mods[6];
                    break;
                case R.id.select_sbula:
                    ModsAndRarity[4]=mods[7];
                    break;
                case R.id.select_chs:
                    ModsAndRarity[4]=mods[8];
                    break;
                case R.id.select_rhs:
                    ModsAndRarity[4]=mods[9];
                    break;
                case R.id.select_vrhs:
                    ModsAndRarity[4]=mods[10];
                    break;
                case R.id.select_cmh:
                    ModsAndRarity[4]=mods[11];
                    break;
                case R.id.select_rmh:
                    ModsAndRarity[4]=mods[12];
                    break;
                case R.id.select_vrmh:
                    ModsAndRarity[4]=mods[13];
                    break;
                case R.id.select_rfa:
                    ModsAndRarity[4]=mods[14];
                    break;
                case R.id.select_rt:
                    ModsAndRarity[4]=mods[15];
                    break;
            }
        }
        updateImages();
        updateProperties();
        return true;
    }

    public void updateImages() {
        for (int i = 1; i<=8; i++) {
            if (resonatorImages[ResonatorLevels[i]] != 0) {
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("PortalSim")
                        .setAction("R"+Integer.toString(ResonatorLevels[i]))
                        .build());
                resonatorSlot[i].setImageResource(resonatorImages[ResonatorLevels[i]]);
            } else {
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("PortalSim")
                        .setAction("R0")
                        .build());
                resonatorSlot[i].setImageDrawable(null);
            }
        }

        for (int i=1; i<=4; i++) {
            if (ModsAndRarity[i] != null) {
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("PortalSim")
                        .setAction(mods[i])
                        .build());
                modSlot[i].setImageResource(modImages[getIndex(mods, ModsAndRarity[i])]);
            } else {
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("PortalSim")
                        .setAction("none")
                        .build());
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
