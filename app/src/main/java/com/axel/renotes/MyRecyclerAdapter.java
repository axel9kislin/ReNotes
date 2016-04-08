package com.axel.renotes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Александр on 08.04.2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private List<NoteItem> noteItemList;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<NoteItem> noteItemList) {
        this.noteItemList = noteItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int i) {

        NoteItem nItem = noteItemList.get(i);

        //здесь видимо задать заполнение полей вьюхолдера?
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

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getPosition();

            NoteItem feedItem = noteItemList.get(position);
            Toast.makeText(mContext, feedItem.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getItemCount() {
        return (null != noteItemList ? noteItemList.size() : 0);
    }

}
