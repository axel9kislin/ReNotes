package com.axel.renotes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class add_activity extends AppCompatActivity {

    private SQLiteDatabase db;
    private MyDataBaseHelper helper;
    private EditText title;
    private EditText disc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);
        title = (EditText)findViewById(R.id.userTitle_Add);
        disc = (EditText)findViewById(R.id.userDesc_Add);
    }

    public void OnAddNote(View v)
    {
        helper = new MyDataBaseHelper(this);
        db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", title.getText().toString());
        cv.put("DESCRIPTION", disc.getText().toString());
        cv.put("IMAGE_RESOURCE_ID", R.drawable.placeholder);
        try
        {
            long rowID = db. insertOrThrow("NOTES", null, cv);
            Log.d("MyLogs", "we added in db " + rowID + " row");
        }
        catch (SQLiteException e)
        {
            Log.d("MyLogs", e.getMessage());
        }
        finish();
    }
}
