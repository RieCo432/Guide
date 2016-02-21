package com.colinries.guide;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DownloadXmlTask extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... urls) {
        try {
            return loadXmlFromNetwork(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return new String[]{"An error occured!", "Network Error!"};
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return new String[]{"An error occured!", "XML Error!"};
        }
    }

    @Override
    protected void onPostExecute(String[] result) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        new FAQFragment();
        ListView faqList = FAQFragment.faqList;

        int j = 0;
        String[][] listData = new String[result.length / 2][2];

        for (int i = 0; i < result.length; i++) {
            listData[j][0] = result[i];
            i++;
            listData[j][1] = result[i];
            j++;
        }

        HashMap<String, String> item;
        for (String[] aListData : listData) {
            item = new HashMap<>();
            item.put("line1", aListData[0]);
            item.put("line2", aListData[1]);
            list.add(item);
        }

        new FAQFragment();
        ListAdapter myListAdapter = new SimpleAdapter(FAQFragment.context, list,
                R.layout.two_line_list_item,
                new String[]{"line1", "line2"},
                new int[]{R.id.text1, R.id.text2});
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
            //ConvertAnInputStreamToFile(stream, urlString);
            entries = faqParser.parse(stream);
            result = new String[entries.size() * 2];

        } finally {
            if (stream != null) {
                stream.close();
            }
        }

        int i = 0;

        for (FaqParser.Entry entry : entries) {
            result[i] = entry.question;
            i++;
            result[i] = entry.answer;
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

    /*private void ConvertAnInputStreamToFile(InputStream in, String url) throws IOException {
        byte[] buffer = new byte[in.available()];
        in.read(buffer);
        Context ctx = FAQFragment.context;
        File file = getTempFile(FAQFragment.context, url);
        FileOutputStream outputStream;

        FileOutputStream fos = ctx.openFileOutput

    }

    public File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = Uri.parse(url).getLastPathSegment();
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }*/
}
