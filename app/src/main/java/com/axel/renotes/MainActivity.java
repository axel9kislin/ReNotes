package com.axel.renotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        adapter = new MyRecyclerAdapter(MainActivity.this, noteList);
        mRecyclerView.setAdapter(adapter);

        for (int i = 0; i < noteList.size(); i++) {
            //JSONObject post = posts.optJSONObject(i);
            //здесь надо сделать загрузку данных из SQLite базы, заполнение элемента NoteItem
            NoteItem item = new NoteItem();
            //item.setTitle(post.optString("title"));
            //item.getDisc(post.optString("thumbnail"));

            noteList.add(item);
        }


    }
}
