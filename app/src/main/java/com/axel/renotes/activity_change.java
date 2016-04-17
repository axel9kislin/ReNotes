package com.axel.renotes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_change extends AppCompatActivity {

    private EditText titleChanged;
    private EditText descChanged;
    private SQLiteDatabase db;
    private String id_changing;
    private MyDataBaseHelper helper;
    private Cursor cursor;
    public static final String extra_data = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_change);
        titleChanged = (EditText)findViewById(R.id.userTitle_changed);
        descChanged = (EditText)findViewById(R.id.userDesc_changed);
        Intent intent = getIntent();
        id_changing = intent.getStringExtra(extra_data);
        try {
            helper = new MyDataBaseHelper(this);
            db = helper.getReadableDatabase();
            cursor = db.query("NOTES",
                    new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{id_changing},
                    null, null, null);
            Log.d("MyLogs","what have in cursor "+cursor.getCount()+" items");
        }
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this,"Problem with connect to database",Toast.LENGTH_LONG);
            toast.show();
        }
        cursor.moveToFirst();
        titleChanged.setText(cursor.getString(1));
        descChanged.setText(cursor.getString(2));
    }

    public void OnSaveChanges(View v)
    {
        helper = new MyDataBaseHelper(this);
        db = helper.getWritableDatabase();
        String dataFromTitleEdit = titleChanged.getText().toString();
        String dataFromDescEdit = descChanged.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put("NAME", dataFromTitleEdit);
        cv.put("DESCRIPTION", dataFromDescEdit);
        int updCount = db.update("NOTES", cv, "_id = ?",
                new String[]{id_changing});
        Log.d("MyLogs", "updated rows count = " + updCount);

        finish();
    }

}
