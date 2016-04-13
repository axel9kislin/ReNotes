package com.axel.renotes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MyLogs";
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private MyDataBaseHelper helper;
    private Cursor cursor;
    private  SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(itemAnimator);

        try {
            helper = new MyDataBaseHelper(this);
            db = helper.getReadableDatabase();
            Log.d(TAG, "we in try, after acsess");
            //получаем данные в курсор, всё из базы, для того чтоб потом это вывести в наш recyclerView
            cursor = db.query("NOTES",
                    null,
                    null,null,null,null,null);
        }
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database in unvailable", Toast.LENGTH_LONG);
            toast.show();
        }

        adapter = new MyRecyclerAdapter(MainActivity.this, cursor);
        mRecyclerView.setAdapter(adapter);
    }
}
