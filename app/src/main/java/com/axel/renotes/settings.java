package com.axel.renotes;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {

    private Button btn_ClearAll;
    private SQLiteDatabase db;
    private MyDataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btn_ClearAll = (Button)findViewById(R.id.btn_clearAll);
    }
    public void OnClearAll(View v)
    {
        helper = new MyDataBaseHelper(this);
        db = helper.getWritableDatabase();
        int clearCount = db.delete("NOTES", null, null);
        Log.d("MyLogs", "deleted rows count = " + clearCount);
        finish();
    }
}
