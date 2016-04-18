package com.axel.renotes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.RadialGradient;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Александр on 15.04.2016.
 */
public class Fragment_with_RecyclerView extends Fragment {

    private String TAG = "MyLogs";
    private RecyclerView mRecyclerView;
    public   MyRecyclerAdapter adapter;
    private  MyDataBaseHelper helper;
    private Cursor cursor;
    private  SQLiteDatabase db;
    private FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycle, null);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view_inFr);
        fab = (FloatingActionButton)v.findViewById(R.id.fab);
        return v;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(itemAnimator);

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
                Fragment1 tempFrg = new Fragment1().newInstance(position);
                tempFrg.show(getFragmentManager(), null);
            }
        });
        mRecyclerView.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),add_activity.class);
                startActivity(intent);
            }
        });
    }

//    public void removeItemFromRV(int item_id)
//    {
//
//    }

    @Override
    public void onResume() {
        super.onResume();
        onActivityCreated(null);//не выход, костыльное решение
        //adapter.notifyDataSetChanged(); не работает
    }

    @Override
    public void onPause() {
        super.onPause();
        onActivityCreated(null); //для удаления не работает
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        onActivityCreated(null);
        //adapter.notifyDataSetChanged();
    }
}
