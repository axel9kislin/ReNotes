package com.axel.renotes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Александр on 08.04.2016.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    View v1;
    public CustomViewHolder(View view) {
        super(view);
       v1 = view.findViewById(R.id.v1);
    }
}
