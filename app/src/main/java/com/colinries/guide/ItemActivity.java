package com.colinries.guide;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class ItemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (id == R.id.nav_resonators) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.ResonatorFragment()).commit();
            setTitle(getString(R.string.resonators));
            // Handle the camera action
        } else if (id == R.id.nav_xmps) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.XmpFragment()).commit();
            setTitle(getString(R.string.xmps));
            // Handle the camera action
        } else if (id == R.id.nav_ultrastrikes) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.UltrastrikeFragment()).commit();
            setTitle(getString(R.string.ultrastrikes));
            // Handle the camera action
        } else if (id == R.id.nav_adarefactor) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.AdarefactorFragment()).commit();
            setTitle(getString(R.string.adarefactor));
            // Handle the camera action
        } else if (id == R.id.nav_jarvisvirus) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.JarvisvirusFragment()).commit();
            setTitle(getString(R.string.jarvisvirus));
            // Handle the camera action
        } else if (id == R.id.nav_powercubes) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.PowercubeFragment()).commit();
            setTitle(getString(R.string.powercubes));
            // Handle the camera action
        } else if (id == R.id.nav_portalkey) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.PortalkeyFragment()).commit();
            setTitle(getString(R.string.portalkey));
            // Handle the camera action
        } else if (id == R.id.nav_shields) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.ShieldFragment()).commit();
            setTitle(getString(R.string.shields));
            // Handle the camera action
        } else if (id == R.id.nav_axashields) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.AxashieldFragment()).commit();
            setTitle(getString(R.string.axashields));
            // Handle the camera action
        } else if (id == R.id.nav_heatsinks) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.HeatsinkFragment()).commit();
            setTitle(getString(R.string.heatsinks));
            // Handle the camera action
        } else if (id == R.id.nav_multihacks) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.MultihackFragment()).commit();
            setTitle(getString(R.string.multihacks));
            // Handle the camera action
        } else if (id == R.id.nav_forceamps) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.ForceampFragment()).commit();
            setTitle(getString(R.string.forceamps));
            // Handle the camera action
        } else if (id == R.id.nav_turrets) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.TurretFragment()).commit();
            setTitle(getString(R.string.turrets));
            // Handle the camera action
        } else if (id == R.id.nav_linkamps) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.LinkampFragment()).commit();
            setTitle(getString(R.string.linkamps));
            // Handle the camera action
        } else if (id == R.id.nav_softbankultralinkamps) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.SoftbankultralinkampFragment()).commit();
            setTitle(getString(R.string.softbankultralinkamps));
            // Handle the camera action
        } else if (id == R.id.nav_capsules) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.CapsuleFragment()).commit();
            setTitle(getString(R.string.capsules));
            // Handle the camera action
        } else if (id == R.id.nav_mufgcapsules) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.MufgcapsuleFragment()).commit();
            setTitle(getString(R.string.mufgcapsules));
            // Handle the camera action
        } else if (id == R.id.nav_fracker) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.FrackerFragment()).commit();
            setTitle(getString(R.string.fracker));
            // Handle the camera action
        } else if (id == R.id.nav_keylocker) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.KeylockerFragment()).commit();
            setTitle(getString(R.string.keylocker));
            // Handle the camera action
        } else if (id == R.id.nav_beacons) {
            fragmentTransaction.replace(R.id.item_fragment_container, new ItemsFragment.BeaconFragment()).commit();
            setTitle(getString(R.string.beacons));
            // Handle the camera action
        }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
