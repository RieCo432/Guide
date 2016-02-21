package com.colinries.guide;

import android.app.Instrumentation;
import android.app.Instrumentation.ActivityResult;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static String mPortalSimUnlockPrice;
    public static String mDonateSPrice;
    public static String mDonateMPrice;
    public static String mDonateLPrice;

    public final String mPortalSimUnlockId = "portalsimunlock";
    public String mIdToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

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

        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);

        if (!sharedPreferences.getString("APP_ID_TOKEN", "NULL").equals("NULL")) {
            mIdToken = sharedPreferences.getString("APP_ID_TOKEN", "NULL");
        } else {
            mIdToken = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("APP_ID_TOKEN", mIdToken).apply();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> skuList = new ArrayList<>();
                skuList.add(mPortalSimUnlockId);
                skuList.add("donateS");
                skuList.add("donateM");
                skuList.add("donateL");
                Bundle querySkus = new Bundle();
                querySkus.putStringArrayList("ITEM_ID_LIST", skuList);

                try {
                    Bundle skuDetails = mService.getSkuDetails(3, getPackageName(), "inapp", querySkus);

                    int response = skuDetails.getInt("RESPONSE_CODE");
                    if (response == 0) {
                        ArrayList<String> responseList = skuDetails.getStringArrayList("DETAILS_LIST");

                        assert responseList != null;
                        for (String thisResponse : responseList) {
                            JSONObject object = new JSONObject(thisResponse);
                            String sku = object.getString("productId");
                            String price = object.getString("price");
                            switch (sku) {
                                case mPortalSimUnlockId:
                                    mPortalSimUnlockPrice = price;
                                    break;
                                case "donateS":
                                    mDonateSPrice = price;
                                    break;
                                case "donateM":
                                    mDonateMPrice = price;
                                    break;
                                case "donateL":
                                    mDonateLPrice = price;
                                    break;
                            }

                        }
                    }

                } catch (RemoteException | JSONException e) {
                    e.printStackTrace();
                }


            }
        });

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
        } else if (id == R.id.action_unlockPortalSim) {
            try {
                Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(), mPortalSimUnlockId, "inapp", mIdToken);
                PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
                assert pendingIntent != null;
                startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), Integer.valueOf("0"), Integer.valueOf("0"), Integer.valueOf("0"));
                Toast.makeText(this, "worked", Toast.LENGTH_LONG).show();
            } catch (RemoteException | IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
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


        if (id == R.id.nav_main) {
            fragmentTransaction.replace(R.id.fragment_container, new HowToFragment()).commit();
            setTitle(getString(R.string.how_tos));

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
            fragmentTransaction.replace(R.id.fragment_container, new PortalsimulatorFragment()).commit();
            setTitle(getString(R.string.portalsimulator));
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

    IInAppBillingService mService;

    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }

}
