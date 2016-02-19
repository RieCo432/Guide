package com.colinries.guide;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DownloadXmlTask extends AsyncTask<String, Void, String[]> {

    //TODO: remove all log.i calls and other debugging stuff
    @Override
    protected String[] doInBackground(String... urls) {
        try {
            Log.i("DXT", "function called on " + urls[0]);
            return loadXmlFromNetwork(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(new FAQFragment().getActivity(), new FAQFragment().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            //Toast.makeText(new FAQFragment().getActivity(), new FAQFragment().getString(R.string.xml_error), Toast.LENGTH_SHORT).show();
            return new String[]{"Q1", "A1", "Q2", "A2"};
        }
    }

    @Override
    protected void onPostExecute(String[] result) {
        ArrayList<HashMap<String,String>> list = new ArrayList<>();

        new FAQFragment();
        ListView faqList = FAQFragment.faqList;

        int j = 0;
        String[][] listData = new String[result.length/2][2];

        for (int i=0;i<result.length;i++) {
            listData[j][0] = result[i];
            i++;
            listData[j][1] = result[i];
            j++;
        }

        HashMap<String,String> item;
        for (String[] aListData : listData) {
            item = new HashMap<>();
            item.put("line1", aListData[0]);
            item.put("line2", aListData[1]);
            list.add(item);
        }

        new FAQFragment();
        ListAdapter myListAdapter = new SimpleAdapter(FAQFragment.context, list,
                android.R.layout.simple_list_item_2,
                new String[] { "line1","line2" },
                new int[] {android.R.id.text1, android.R.id.text2});
        faqList.setAdapter(myListAdapter);
    }

    private String[] loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
        InputStream stream = null;
        FaqParser faqParser = new FaqParser();
        List<FaqParser.Entry> entries = null;
        String question = null;
        String answer = null;
        String[] result;

        try {
            stream = downloadUrl(urlString);
            entries = faqParser.parse(stream);
            result = new String[entries.size()*2];
        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        int i = 0;

        for (FaqParser.Entry entry: entries) {
            Log.i("DXT", "result setter called");
            result[i] = entry.question;
            //Log.i("DXT", entry.question);
            i++;
            result[i] = entry.answer;
            //Log.i("DXT", entry.answer);
            i++;

        }

        return result;
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();

        return conn.getInputStream();
    }
}
