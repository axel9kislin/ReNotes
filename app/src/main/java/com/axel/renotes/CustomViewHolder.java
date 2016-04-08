package com.axel.renotes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Александр on 08.04.2016.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    protected ImageView imageView;
    protected TextView textTitle;
    protected TextView textDisc;

    public CustomViewHolder(View view) {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
        this.textTitle = (TextView) view.findViewById(R.id.titleNote);
        this.textDisc = (TextView) view.findViewById(R.id.disc);
    }
}
