package com.colinries.guide;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class FAQFragment extends Fragment {

    private static final String URL = "http://colinries.com/guide/faq.xml";
    public static RelativeLayout layout;
    public static ListView faqList;
    public static Context context;


    public FAQFragment() {
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
        layout = (RelativeLayout) inflater.inflate(R.layout.fragment_faq, container, false);

        context = getActivity();
        new DownloadXmlTask().execute(URL);

        faqList = (ListView) layout.findViewById(R.id.faqList);



        return layout;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();}
}
