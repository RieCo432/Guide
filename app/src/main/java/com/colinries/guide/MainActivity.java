package com.colinries.guide;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.colinries.guide.util.IabHelper;
import com.colinries.guide.util.IabResult;
import com.colinries.guide.util.Inventory;
import com.colinries.guide.util.Purchase;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "guide.inappbilling";
    IabHelper mHelper;
    static String ITEM_SKU;
    public static SharedPreferences sharedPreferences;

    public String mIdToken;

    @Override
    protected void onStart() {
        super.onStart();

        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs/Z+0f1U2B2Jvk1vsEoa+rwZuVZBisvb1Tt0bqELhClou97rnWtobvkf13Cjbnr7qGKfENcuQAikRcZbY6FkLyWEceC5iScew4OK7FFllpbLlZ32dOIXyzgaKZI8zGEIWNbNrRMnGRyTABml63+PAZLW09vPAxJTFO991lhvjnMcWFEq32dtgoHGkzNUrgWJeRV8jiPSakcAqvcStWreZ1uvIJwQLhf4BZdyQ4c8sBEx5QPG/S5sNt1nfF9gZl2ShLEResiugXDJWYgOcmDdUw0BBNvu/411IKOujxR/XXIRHHEAkZA6D6qiJHAPLYbG/6V7GA1Nu8lrrRIbiJqnswIDAQAB";

        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.enableDebugLogging(false, TAG);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.i(TAG, "In-app billing setup failed: " + result);
                } else {
                    Log.i(TAG, "In-app billing is set up OK");
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new MainFragment()).commit();
        setTitle(getString(R.string.main));

        /*Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);*/

        if (!sharedPreferences.getString("APP_ID_TOKEN", "NULL").equals("NULL")) {
            mIdToken = sharedPreferences.getString("APP_ID_TOKEN", "NULL");
        } else {
            mIdToken = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("APP_ID_TOKEN", mIdToken).apply();
        }
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
        } else if (id == R.id.action_donateS) {
            ITEM_SKU = "donates";
            mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, mIdToken);
        } else if (id == R.id.action_donateM) {
            ITEM_SKU = "donatem";
            mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, mIdToken);
        } else if (id == R.id.action_donateL) {
            ITEM_SKU = "donatel";
            mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, mIdToken);
        } else if (id == R.id.action_unlockPortalSim) {
            ITEM_SKU = "premiumunlock";
            mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001, mPurchaseFinishedListener, mIdToken);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        if (id == R.id.nav_main) {
            fragmentTransaction.replace(R.id.fragment_container, new MainFragment()).commit();
            setTitle(getString(R.string.main));

        } else if (id == R.id.nav_howto) {
            fragmentTransaction.replace(R.id.fragment_container, new HowToFragment()).commit();
            setTitle(getString(R.string.how_tos));

        } else if (id == R.id.nav_items) {
            Intent intent = new Intent(this, ItemActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_badges) {
            Intent intent = new Intent(this, BadgesActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_portalsimulator) {
            if(sharedPreferences.getBoolean("PREMIUM_UNLOCKED", false)) {
                fragmentTransaction.replace(R.id.fragment_container, new PortalsimulatorFragment()).commit();
                setTitle(getString(R.string.portalsimulator));
            } else {
                final Tracker t = ((Guide) this.getApplication()).getTracker(
                        Guide.TrackerName.APP_TRACKER);
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.premiumunlockneeded))
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton(getString(R.string.buy), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ITEM_SKU = "premiumunlock";
                                mHelper.launchPurchaseFlow(MainActivity.this, ITEM_SKU, 10001, mPurchaseFinishedListener, mIdToken);
                                t.send(new HitBuilders.EventBuilder()
                                        .setCategory("Premium Unlock")
                                        .setAction("Accepted")
                                        .build());
                            }
                        })
                        .setMessage(R.string.dialogpremiuminfo)
                        .setCancelable(false)
                        .setNegativeButton(getString(R.string.nothanks), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fragmentTransaction.replace(R.id.fragment_container, new MainFragment()).commit();
                                setTitle(getString(R.string.main));
                                t.send(new HitBuilders.EventBuilder()
                                        .setCategory("Premium Unlock")
                                        .setAction("Declined")
                                        .build());
                            }
                        }).show();
            }
        }else if (id == R.id.nav_faq) {
            fragmentTransaction.replace(R.id.fragment_container, new FAQFragment()).commit();
            setTitle(getString(R.string.faq));
        } else if (id == R.id.nav_about) {
            fragmentTransaction.replace(R.id.fragment_container, new AboutFragment()).commit();
            setTitle(getString(R.string.about));
        } else if (id == R.id.nav_support) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getString(R.string.support_email), null));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.guide_support));
            Intent.createChooser(intent, getString(R.string.choose_email_app));
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", getString(R.string.feedback_email), null));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.guide_feedback));
            Intent.createChooser(intent, getString(R.string.choose_email_app));
            startActivity(intent);
        } else if (id == R.id.nav_settings) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            Tracker t = ((Guide) getApplication()).getTracker(
                    Guide.TrackerName.APP_TRACKER);
            if (result.isFailure()) {
                Toast.makeText(MainActivity.this, getString(R.string.purchasefailed), Toast.LENGTH_SHORT).show();
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("Purchase")
                        .setAction("Failed " + ITEM_SKU)
                        .build());
            } else if (info.getSku().equals(ITEM_SKU) && !(info.getSku().equals("premiumunlock"))) {
                consumeItem();
                Toast.makeText(MainActivity.this, getString(R.string.thankyou), Toast.LENGTH_SHORT).show();
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("Purchase")
                        .setAction("Successful " + info.getSku())
                        .build());
            } else if (info.getSku().equals(("premiumunlock"))) {
                Toast.makeText(MainActivity.this, getString(R.string.premiumunlocked), Toast.LENGTH_SHORT).show();
                sharedPreferences.edit().putBoolean("PREMIUM_UNLOCKED", true).apply();
                t.send(new HitBuilders.EventBuilder()
                        .setCategory("Purchase")
                        .setAction("Successful " + info.getSku())
                        .build());
            }
        }
    };

    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        @Override
        public void onQueryInventoryFinished(IabResult result, Inventory inv) {
            if (result.isFailure()) {
                Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            } else {
                mHelper.consumeAsync(inv.getPurchase(ITEM_SKU), mConsumeFinishedListener);
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            if (result.isSuccess()) {
                Toast.makeText(MainActivity.this, "Donation consumed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.errorconsumingitem), Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
}
