package com.axel.renotes;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Александр on 08.04.2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Cursor c;
    private Context mContext;

    public MyRecyclerAdapter(Context context, Cursor cursor) {
        c = cursor;
        mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int i) {

        //ОнБиндВьюХолдер - это заполнение наших вьюшек элементами из класса, в котором хранится информация о заметках.
        holder.textTitle.setText(c.getString(0));
        holder.textDisc.setText(c.getString(1));
        holder.imageView.setImageResource(c.getInt(2));

        //Download image using picasso library
//        Picasso.with(mContext).load(feedItem.getThumbnail())
//                .error(R.drawable.placeholder)
//                .placeholder(R.drawable.placeholder)
//                .into(customViewHolder.imageView);

        //Setting text view title
        //CustomViewHolder.TextView.setText(Html.fromHtml(nItem.getTitle()));

        holder.textTitle.setOnClickListener(clickListener);
        holder.imageView.setOnClickListener(clickListener);

        holder.textTitle.setTag(holder);
        holder.imageView.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return c.getCount();
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //фигня полная, перепишу

        }
    };
}
