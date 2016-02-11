package com.colinries.guide;

import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView item_name;
    public static ImageView item_image;
    public static TextView item_description;
    public static TextView item_acquisition;
    public static TextView item_variations;
    public static TextView item_notes;
    public static LinearLayout item_intro_block;
    public static LinearLayout item_iq_block;

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
        item_iq_block = (LinearLayout) findViewById(R.id.item_iq_block);

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
        getMenuInflater().inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.item_intro) {

            item_iq_block.setVisibility(View.GONE);
            item_intro_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_resonators) {

            item_name.setText(getString(R.string.resonator));
            item_image.setImageResource(R.mipmap.item_resonator8);
            item_description.setText(getString(R.string.resonator_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_level));
            item_notes.setText(getString(R.string.resonator_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_xmps) {

            item_name.setText(getString(R.string.xmp));
            item_image.setImageResource(R.mipmap.item_xmp8);
            item_description.setText(getString(R.string.adarefactor));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_level));
            item_notes.setText(getString(R.string.ada_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_ultrastrikes) {


            item_name.setText(getString(R.string.adarefactor));
            item_image.setImageResource(R.mipmap.item_ultrastrike8);
            item_description.setText(getString(R.string.ada_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_level));
            item_notes.setText(getString(R.string.ada_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);


        } else if (id == R.id.nav_adarefactor) {

            item_name.setText(getString(R.string.adarefactor));
            item_image.setImageResource(R.mipmap.item_resonator8);
            item_description.setText(getString(R.string.ada_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.ada_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_jarvisvirus) {

            item_name.setText(getString(R.string.adarefactor));
            item_image.setImageResource(R.mipmap.item_resonator8);
            item_description.setText(getString(R.string.ada_description));
            item_acquisition.setText(getString(R.string.item_acquisition_full));
            item_variations.setText(getString(R.string.item_variations_rarity_veryrare));
            item_notes.setText(getString(R.string.ada_notes));

            item_intro_block.setVisibility(View.GONE);
            item_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_powercubes) {
        } else if (id == R.id.nav_portalkey) {
        } else if (id == R.id.nav_shields) {
        } else if (id == R.id.nav_axashields) {
        } else if (id == R.id.nav_heatsinks) {
        } else if (id == R.id.nav_multihacks) {
        } else if (id == R.id.nav_forceamps) {
        } else if (id == R.id.nav_turrets) {
        } else if (id == R.id.nav_linkamps) {
        } else if (id == R.id.nav_softbankultralinkamps) {
        } else if (id == R.id.nav_capsules) {
        } else if (id == R.id.nav_mufgcapsules) {
        } else if (id == R.id.nav_fracker) {
        } else if (id == R.id.nav_keylocker) {
        } else if (id == R.id.nav_beacons) {
        }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
