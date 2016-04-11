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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<NoteItem> noteList;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;

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

        try
        {
            //создали хелпер, получили базу
            SQLiteOpenHelper helper = new MyDataBaseHelper(this);
            SQLiteDatabase db = helper.getReadableDatabase();

            //получаем данные в курсор, всё из базы, для того чтоб потом это вывести в наш recyclerView
            Cursor cursor = db.query("NOTES", new String[] {"NAME","DESCRIPTION","IMAGE_RESOURCE_ID"},null,null,null,null,null);

            if (cursor.moveToFirst())
                if (cursor.moveToNext()) {
                    {
                        String nameText = cursor.getString(0);
                        String descText = cursor.getString(1);
                        int img_res = cursor.getInt(2);

                        TextView textTitle = (TextView) findViewById(R.id.titleNote);
                        textTitle.setText(nameText);

                        TextView textDesc = (TextView) findViewById(R.id.disc);
                        textDesc.setText(descText);

                        ImageView img = (ImageView) findViewById(R.id.thumbnail);
                        img.setImageResource(img_res);
                        img.setContentDescription("this is image of you notes");

                        //получили в переменные, теперь нам бы закинуть это в адаптер для ресайкла, или как-то передать в общем на
                        //отрисовку интерфейсу
                    }
                }
            cursor.close();
            db.close();
        }catch (SQLiteException e)
        {
            Toast toast = Toast.makeText(this, "Database in unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}
