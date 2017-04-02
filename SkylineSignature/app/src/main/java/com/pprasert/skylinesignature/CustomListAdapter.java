package com.pprasert.skylinesignature;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] itemdesc;

    public CustomListAdapter(Activity context, String[] itemname, String[] itemdesc) {
        super(context, R.layout.playtimelist, itemname);

        this.context=context;
        this.itemname=itemname;
        this.itemdesc = itemdesc;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.playtimelist, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(R.drawable.skylinelogo);
        extratxt.setText(itemdesc[position]);
        return rowView;

    };
}
