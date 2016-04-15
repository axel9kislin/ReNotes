package com.axel.renotes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Александр on 15.04.2016.
 */
public class Fragment_with_RecyclerView extends Fragment {

    private String TAG = "MyLogs";
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private MyDataBaseHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    private DialogFragment more_info;
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycle, null);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view_inFr);
        return v;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(itemAnimator);
        manager = getFragmentManager();

        try {
            helper = new MyDataBaseHelper(getContext());
            db = helper.getReadableDatabase();
            cursor = db.query("NOTES",
                    new String[] {"_id","NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},
                    null,
                    null,null,null,null);
        }
        catch (SQLiteException e) {
            Log.d(TAG,e.getMessage());
        }

        adapter = new MyRecyclerAdapter(getContext(),cursor);
        adapter.SetOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Log.d(TAG, "we in OnItemClick");
//                Bundle arg = new Bundle();
//                Log.d(TAG, "we declarated bundle arg");
//                arg.putInt("id", position);
//                Log.d(TAG, "we add in budle our id");
                //more_info = new Fragment1();
                Fragment1 tempFrg = new Fragment1().newInstance(position);
                Log.d(TAG, "we create our dialogFragment");
                tempFrg.show(getFragmentManager(), null);
                Log.d(TAG, "we do show our fragment");
                Log.d(TAG, "clicked on " + position + " item");
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
