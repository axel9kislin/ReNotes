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
    private List<NoteItem> noteList;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    MyDataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        mRecyclerView.setItemAnimator(itemAnimator);

        adapter = new MyRecyclerAdapter(MainActivity.this, noteList);
        mRecyclerView.setAdapter(adapter);

        helper = new MyDataBaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        try
        {
            Log.d(TAG, "we in try, after acsess");
            //получаем данные в курсор, всё из базы, для того чтоб потом это вывести в наш recyclerView
            Cursor cursor = db.query("NOTES",
                    null, //здесь я вставлю норм условие
                    null,null,null,null,null);
            int tempIndex = cursor.getColumnIndex("NAME");
            Log.d(TAG,"we have index " + tempIndex);
            Log.d(TAG,"we have in cursor "+cursor.getCount());
            if (cursor.moveToFirst()) {
                Log.d(TAG,"we can do MoveToFirst!");
                do {
                    {
                        String nameText = cursor.getString(1);
                        String descText = cursor.getString(2);
                        int img_res = cursor.getInt(3);
                        Log.d(TAG, "we have name" + nameText);
                        Log.d(TAG, "we have desc" + descText);
                        Log.d(TAG, "we have img" + img_res);

                        TextView textTitle = (TextView) findViewById(R.id.titleNote);
                        textTitle.setText(nameText);
                        Log.d(TAG, "we have text" + textTitle.getText() + " in textTitle");

                        TextView textDesc = (TextView) findViewById(R.id.disc);
                        textDesc.setText(descText);

                        ImageView img = (ImageView) findViewById(R.id.thumbnail);
                        img.setImageResource(img_res);
                        img.setContentDescription("this is image of you notes");

                        //получили в переменные, теперь нам бы закинуть это в адаптер для ресайкла, или как-то передать в общем на
                        //отрисовку интерфейсу
                    }
                }  while (cursor.moveToNext());
            }else
            {
            Log.d(TAG, "We cant moveToFirst()");
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e)
        {
            Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }


    }
}
