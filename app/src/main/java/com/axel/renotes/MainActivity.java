package com.axel.renotes;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

    private String TAG = "MyLogs";
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private MyDataBaseHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    private Fragment1 fragment;
    private android.support.v4.app.FragmentManager manager;
    private android.support.v4.app.FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(itemAnimator);
        manager = getSupportFragmentManager();
        fragment = new Fragment1();


        try {
            helper = new MyDataBaseHelper(this);
            db = helper.getReadableDatabase();
            cursor = db.query("NOTES",
                    new String[] {"_id","NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},
                    null,
                    null,null,null,null);
        }
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database in unvailable", Toast.LENGTH_LONG);
            toast.show();
        }

        adapter = new MyRecyclerAdapter(MainActivity.this, cursor);
        adapter.SetOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position) {
                transaction = manager.beginTransaction();
                if (manager.findFragmentById(R.id.v1)==null)
                {
                    cursor = db.query("NOTES",
                            new String[] {"_id","NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},
                            "_id = ?",
                            new String[] {Integer.toString(position)},
                            null,null,null);
                    Fragment1 fr = new Fragment1();
                    Bundle bundle = new Bundle();

                    fr.setArguments(bundle);
                    Log.d(TAG, "clicked on " + position + " item");
//                    TextView title = (TextView)findViewById(R.id.titleNote);
//                    title.setText("clicked "+position); такой подход не рабоает
                    transaction.add(view.getId(),fragment);

                }
                else
                {
                    transaction.remove(fragment);
                }
                transaction.commit();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}
