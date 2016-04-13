package com.axel.renotes;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


/**
 * Created by Александр on 08.04.2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    private Context mContext;
    public CursorAdapter mCursorAdapter;
    OnItemClickListener mItemClickListener;

    public MyRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;

        mCursorAdapter = new CursorAdapter(mContext, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.view_list_item, null);
                return view;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView text = (TextView)view.findViewById(R.id.textViewListItem);
                text.setText(cursor.getString(1));
            }
        };
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), viewGroup);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int i) {

        mCursorAdapter.getCursor().moveToPosition(i);
        mCursorAdapter.bindView(holder.itemView, mContext, mCursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return mCursorAdapter.getCount();
    }

    //реализация холдера
    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View v1;
        public CustomViewHolder(View view) {
            super(view);
            v1 = view.findViewById(R.id.v1);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
