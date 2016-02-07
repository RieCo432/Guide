package com.colinries.guide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Colin on 07/02/2016.
 */
public class customListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public customListAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.select_item_list, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.select_item_list, null,true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.itemIcon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.itemName);

        imageView.setImageResource(imgid[position]);
        extratxt.setText(itemname[position]);
        return rowView;

    };
}