package com.colinries.guide;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton gotoFacebook = (ImageButton) layout.findViewById(R.id.button1);
        gotoFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facebookIntent;
                try {
                    getActivity().getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/463638860350452"));
                } catch (Exception e) {
                    facebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/463638860350452"));
                }
                startActivity(facebookIntent);
            }
        });

        ImageButton gotoTwitter = (ImageButton) layout.findViewById(R.id.button2);
        gotoTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent twitterIntent;
                try {
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=ingress"));
                } catch (PackageManager.NameNotFoundException e) {
                    twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("htpps://www.twitter.com/ingress"));
                }
                startActivity(twitterIntent);
            }
        });

        ImageButton gotoGPlus = (ImageButton) layout.findViewById(R.id.button3);
        gotoGPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gplusIntent;

                try {
                    gplusIntent = new Intent(Intent.ACTION_VIEW);
                    gplusIntent.setData(Uri.parse("https://plus.google.com/+Ingress"));
                    gplusIntent.setPackage("com.google.android.apps.plus");
                } catch (ActivityNotFoundException e) {
                    gplusIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+Ingress"));
                }
                startActivity(gplusIntent);
            }
        });

        ImageButton gotoYT = (ImageButton) layout.findViewById(R.id.button4);
        gotoYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ytIntent;

                /*try {
                    ytIntent = new Intent(Intent.ACTION_VIEW);
                    ytIntent.setData(Uri.parse("https://www.youtube.com/user/ingress"));
                    //ytIntent.setPackage("com.google.android.youtube");
                }catch (ActivityNotFoundException e) {*/
                    ytIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/ingress"));
                //}
                startActivity(ytIntent);
            }
        });




        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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