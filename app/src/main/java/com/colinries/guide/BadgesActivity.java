package com.colinries.guide;

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
import android.widget.TextView;

public class BadgesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static TextView badge_name;
    public static ImageView badge_image;
    public static TextView badge_description;
    public static TextView badge_acquisition;
    public static TextView badge_variations;
    public static TextView badge_notes;
    public static LinearLayout badge_intro_block;
    public static LinearLayout badge_iq_block;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        badge_name = (TextView) findViewById(R.id.badge_name);
        badge_image = (ImageView) findViewById(R.id.badge_image);
        badge_description = (TextView) findViewById(R.id.badge_description);
        badge_acquisition = (TextView) findViewById(R.id.badge_acquisition);
        badge_variations = (TextView) findViewById(R.id.badge_variations);
        badge_notes = (TextView) findViewById(R.id.badge_notes);

        badge_intro_block = (LinearLayout) findViewById(R.id.badge_intro_block);
        badge_iq_block = (LinearLayout) findViewById(R.id.badge_iq_block);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //TODO: Add all navigation entries and their respective fragments

        if (id == R.id.nav_abaddon) {

            badge_name.setText(getString(R.string.abaddon));
            badge_image.setImageResource(R.mipmap.badge_abaddon);
            badge_description.setText(getString(R.string.abaddon_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.abaddon_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_ada) {

            badge_name.setText(getString(R.string.aided_ada));
            badge_image.setImageResource(R.mipmap.badge_ada);
            badge_description.setText(getString(R.string.aided_ada_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.aided_ada_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_builder) {

            badge_name.setText(getString(R.string.builder));
            badge_image.setImageResource(R.mipmap.badge_builder);
            badge_description.setText(getString(R.string.builder_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.builder_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_connector) {

            badge_name.setText(getString(R.string.connector));
            badge_image.setImageResource(R.mipmap.badge_connector);
            badge_description.setText(getString(R.string.connector_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.connector_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_darsana) {

            badge_name.setText(getString(R.string.darsana));
            badge_image.setImageResource(R.mipmap.badge_darsana);
            badge_description.setText(getString(R.string.darsana_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.darsana_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_engineer) {

            badge_name.setText(getString(R.string.engineer));
            badge_image.setImageResource(R.mipmap.badge_engineer);
            badge_description.setText(getString(R.string.engineer_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.engineer_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_eve) {

            badge_name.setText(getString(R.string.eve));
            badge_image.setImageResource(R.mipmap.badge_eve);
            badge_description.setText(getString(R.string.eve_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.eve_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_explorer) {

            badge_name.setText(getString(R.string.explorer));
            badge_image.setImageResource(R.mipmap.badge_explorer);
            badge_description.setText(getString(R.string.explorer_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.explorer_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_founder) {

            badge_name.setText(getString(R.string.founder));
            badge_image.setImageResource(R.mipmap.badge_founder);
            badge_description.setText(getString(R.string.founder_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.founder_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_hacker) {

            badge_name.setText(getString(R.string.hacker));
            badge_image.setImageResource(R.mipmap.badge_hacker);
            badge_description.setText(getString(R.string.hacker_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.hacker_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_hankjohnson) {

            badge_name.setText(getString(R.string.hankjohnson));
            badge_image.setImageResource(R.mipmap.badge_hankjohnson);
            badge_description.setText(getString(R.string.hankjohnson_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.hankjohnson_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_helios) {

            badge_name.setText(getString(R.string.helios));
            badge_image.setImageResource(R.mipmap.badge_helios);
            badge_description.setText(getString(R.string.helios_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.helios_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_illuminator) {

            badge_name.setText(getString(R.string.illuminator));
            badge_image.setImageResource(R.mipmap.badge_illuminator);
            badge_description.setText(getString(R.string.illuminator_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.illuminator_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_initio) {

            badge_name.setText(getString(R.string.initio));
            badge_image.setImageResource(R.mipmap.badge_initio);
            badge_description.setText(getString(R.string.initio_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.initio_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_innovator) {

            badge_name.setText(getString(R.string.innovator));
            badge_image.setImageResource(R.mipmap.badge_innovator);
            badge_description.setText(getString(R.string.innovator_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.innovator_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_interitus) {

            badge_name.setText(getString(R.string.interitus));
            badge_image.setImageResource(R.mipmap.badge_interitus);
            badge_description.setText(getString(R.string.interitus_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.interitus_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_liberator) {

            badge_name.setText(getString(R.string.liberator));
            badge_image.setImageResource(R.mipmap.badge_liberator);
            badge_description.setText(getString(R.string.liberator_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.liberator_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_mindcontroller) {

            badge_name.setText(getString(R.string.mindcontroller));
            badge_image.setImageResource(R.mipmap.badge_mindcontroller);
            badge_description.setText(getString(R.string.mindcontroller_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.mindcontroller_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_missionday) {

            badge_name.setText(getString(R.string.missionday));
            badge_image.setImageResource(R.mipmap.badge_missionday);
            badge_description.setText(getString(R.string.missionday_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.missionday_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_nl1331) {

            badge_name.setText(getString(R.string.nl1331));
            badge_image.setImageResource(R.mipmap.badge_nl1331);
            badge_description.setText(getString(R.string.nl1331_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.nl1331_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_persepolis) {

            badge_name.setText(getString(R.string.persepolis));
            badge_image.setImageResource(R.mipmap.badge_persepolis);
            badge_description.setText(getString(R.string.persepolis_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.persepolis_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_purifier) {

            badge_name.setText(getString(R.string.purifier));
            badge_image.setImageResource(R.mipmap.badge_purifier);
            badge_description.setText(getString(R.string.purifier_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.purifier_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_recharger) {

            badge_name.setText(getString(R.string.recharger));
            badge_image.setImageResource(R.mipmap.badge_recharger);
            badge_description.setText(getString(R.string.recharger_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.recharger_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_recursion) {

            badge_name.setText(getString(R.string.purifier));
            badge_image.setImageResource(R.mipmap.badge_purifier);
            badge_description.setText(getString(R.string.purifier_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.purifier_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_shonin) {

            badge_name.setText(getString(R.string.shonin));
            badge_image.setImageResource(R.mipmap.badge_shonin);
            badge_description.setText(getString(R.string.shonin_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.shonin_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_sojourner) {

            badge_name.setText(getString(R.string.sojourner));
            badge_image.setImageResource(R.mipmap.badge_sojourner);
            badge_description.setText(getString(R.string.sojourner_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.sojourner_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_specops) {

            badge_name.setText(getString(R.string.specops));
            badge_image.setImageResource(R.mipmap.badge_specops);
            badge_description.setText(getString(R.string.specops_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.specops_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_stellavyctory) {

            badge_name.setText(getString(R.string.stellavyctory));
            badge_image.setImageResource(R.mipmap.badge_stellavyctory);
            badge_description.setText(getString(R.string.stellavyctory_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.stellavyctory_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_susannamoyer) {

            badge_name.setText(getString(R.string.susannamoyer));
            badge_image.setImageResource(R.mipmap.badge_susannamoyer);
            badge_description.setText(getString(R.string.susannamoyer_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_passcodes_only));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.susannamoyer_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_translator) {

            badge_name.setText(getString(R.string.translator));
            badge_image.setImageResource(R.mipmap.badge_translator);
            badge_description.setText(getString(R.string.translator_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.translator_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_trekker) {

            badge_name.setText(getString(R.string.trekker));
            badge_image.setImageResource(R.mipmap.badge_trekker);
            badge_description.setText(getString(R.string.trekker_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.trekker_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_vanguard) {

            badge_name.setText(getString(R.string.vanguard));
            badge_image.setImageResource(R.mipmap.badge_vanguard);
            badge_description.setText(getString(R.string.vanguard_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_full));
            badge_notes.setText(getString(R.string.vanguard_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_verified) {

            badge_name.setText(getString(R.string.verified));
            badge_image.setImageResource(R.mipmap.badge_verified);
            badge_description.setText(getString(R.string.verified_description));
            badge_acquisition.setText(getString(R.string.item_acquisition_playing));
            badge_variations.setText(getString(R.string.badge_variations_bronze));
            badge_notes.setText(getString(R.string.verified_notes));

            badge_intro_block.setVisibility(View.GONE);
            badge_iq_block.setVisibility(View.VISIBLE);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
