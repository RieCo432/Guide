package com.colinries.guide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class ItemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView item_name;
    public static ImageView item_image;
    public static TextView item_description;
    public static TextView item_acquisition;
    public static TextView item_variations;
    public static TextView item_notes;
    public static LinearLayout item_intro_block;
    public static ScrollView item_iq_block;

    public static Tracker t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        item_name = (TextView) findViewById(R.id.item_name);
        item_image = (ImageView) findViewById(R.id.item_image);
        item_description = (TextView) findViewById(R.id.item_description);
        item_acquisition = (TextView) findViewById(R.id.item_acquisition);
        item_variations = (TextView) findViewById(R.id.item_variations);
        item_notes = (TextView) findViewById(R.id.item_notes);

        item_intro_block = (LinearLayout) findViewById(R.id.item_intro_block);
        item_iq_block = (ScrollView) findViewById(R.id.item_iq_block);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_submit_faq) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getString(R.string.question_submission_email), null));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.submit_a_question));
            Intent.createChooser(intent, getString(R.string.choose_email_app));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Tracker t = ((Guide) this.getApplication()).getTracker(
                Guide.TrackerName.APP_TRACKER);

        if (id == R.id.nav_intro) {

            item_iq_block.setVisibility(View.GONE);
            item_intro_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_resonators) {

            item_name.setText(getString(R.string.resonators));
            item_image.setImageResource(R.mipmap.item_resonator8);
            item_description.setText(getString(R.string.resonator_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_level));
            item_notes.setText(getString(R.string.resonator_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_xmps) {

            item_name.setText(getString(R.string.xmp));
            item_image.setImageResource(R.mipmap.item_xmp8);
            item_description.setText(getString(R.string.xmp_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_level));
            item_notes.setText(getString(R.string.xmp_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_ultrastrikes) {


            item_name.setText(getString(R.string.ultrastrikes));
            item_image.setImageResource(R.mipmap.item_ultrastrike8);
            item_description.setText(getString(R.string.ultrastrike_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_level));
            item_notes.setText(getString(R.string.ultrastrike_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_adarefactor) {

            item_name.setText(getString(R.string.adarefactor));
            item_image.setImageResource(R.mipmap.item_adarefactor);
            item_description.setText(getString(R.string.ada_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.ada_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_jarvisvirus) {

            item_name.setText(getString(R.string.jarvisvirus));
            item_image.setImageResource(R.mipmap.item_jarvisvirus);
            item_description.setText(getString(R.string.jarvis_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.jarvis_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_powercubes) {

            item_name.setText(getString(R.string.powercubes));
            item_image.setImageResource(R.mipmap.item_powercube8);
            item_description.setText(getString(R.string.powercubes_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_level));
            item_notes.setText(getString(R.string.powercubes_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_lawsonpowercubes) {

            item_name.setText(getString(R.string.lawsonpowercube));
            item_image.setImageResource(R.mipmap.item_lawson);
            item_description.setText(getString(R.string.lawsonpowercubes_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.lawsonpowercubes_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_portalkey) {

            item_name.setText(getString(R.string.portalkey));
            item_image.setImageResource(R.mipmap.item_portalkey);
            item_description.setText(getString(R.string.portalkey_description));
            item_acquisition.setText(getString(R.string.item_acquisition_hacking_only));
            item_variations.setText(getString(R.string.item_variations_rarity_common));
            item_notes.setText(getString(R.string.portalkey_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_shields) {

            item_name.setText(getString(R.string.shields));
            item_image.setImageResource(R.mipmap.item_shieldvr);
            item_description.setText(getString(R.string.shields_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_all));
            item_notes.setText(getString(R.string.shield_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_axashields) {
            item_name.setText(getString(R.string.axashields));
            item_image.setImageResource(R.mipmap.item_shieldaxa);
            item_description.setText(getString(R.string.axashield_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.axashield_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_heatsinks) {

            item_name.setText(getString(R.string.heatsinks));
            item_image.setImageResource(R.mipmap.item_heatsinkvr);
            item_description.setText(getString(R.string.heatsink_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_all));
            item_notes.setText(getString(R.string.heatsink_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_multihacks) {

            item_name.setText(getString(R.string.multihacks));
            item_image.setImageResource(R.mipmap.item_multihackvr);
            item_description.setText(getString(R.string.multihack_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_all));
            item_notes.setText(getString(R.string.multihack_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_forceamps) {

            item_name.setText(getString(R.string.forceamps));
            item_image.setImageResource(R.mipmap.item_forceampr);
            item_description.setText(getString(R.string.forceamp_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_rare));
            item_notes.setText(getString(R.string.forceamp_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_turrets) {

            item_name.setText(getString(R.string.turrets));
            item_image.setImageResource(R.mipmap.item_turretr);
            item_description.setText(getString(R.string.turret_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_rare));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_linkamps) {

            item_name.setText(getString(R.string.linkamps));
            item_image.setImageResource(R.mipmap.item_linkampvr);
            item_description.setText(getString(R.string.linkamp_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full) + "(Rare)\n"+ getString(R.string.item_acquisition_passcodes_only) + "(Very Rare)");
            item_variations.setText(getString(R.string.item_variations_rarity_rare_veryrare));
            item_notes.setText(getString(R.string.linkamp_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_softbankultralinkamps) {

            item_name.setText(getString(R.string.softbankultralinkamps));
            item_image.setImageResource(R.mipmap.item_softbankultralinkvr);
            item_description.setText(getString(R.string.softbankultralinkamp_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.softbankultralinkamp_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_capsules) {

            item_name.setText(getString(R.string.capsules));
            item_image.setImageResource(R.mipmap.item_capsuler);
            item_description.setText(getString(R.string.capsule_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_rare));
            item_notes.setText(getString(R.string.capsule_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_mufgcapsules) {

            item_name.setText(getString(R.string.mufgcapsules));
            item_image.setImageResource(R.mipmap.item_mufgcapsulevr);
            item_description.setText(getString(R.string.mufgcapsules_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.mufgcapsule_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_fracker) {

            item_name.setText(getString(R.string.fracker));
            item_image.setImageResource(R.mipmap.item_fracker);
            item_description.setText(getString(R.string.fracker_description));
            item_acquisition.setText(getString(R.string.item_acquisition_store_only));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.fracker_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_keylocker) {

            item_name.setText(getString(R.string.keylocker));
            item_image.setImageResource(R.mipmap.item_keylockervr);
            item_description.setText(getString(R.string.keylocker_description));
            item_acquisition.setText(getString(R.string.item_acquisition_store_only));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.keylocker_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());

        } else if (id == R.id.nav_beacons) {

            item_name.setText(getString(R.string.beacons));
            item_image.setImageResource(R.mipmap.item_beaconres);
            item_description.setText(getString(R.string.beacon_description));
            item_acquisition.setText(getString(R.string.item_acquisition_store_only));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.beacon_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

            t.send(new HitBuilders.EventBuilder()
                    .setCategory("ItemIQ")
                    .setAction("Selected " + getResources().getResourceName(id))
                    .build());
        }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        final Tracker tracker = ((Guide) getApplication()).getTracker(Guide.TrackerName.APP_TRACKER);
        if (tracker != null) {
            tracker.setScreenName(getClass().getSimpleName());
            tracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        final Tracker tracker = ((Guide) getApplication()).getTracker(Guide.TrackerName.APP_TRACKER);
        if (tracker != null) {
            tracker.setScreenName(getClass().getSimpleName());
            tracker.send(new HitBuilders.ScreenViewBuilder().build());
        }
    }
}
