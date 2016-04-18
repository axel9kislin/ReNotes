package com.axel.renotes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class detail extends AppCompatActivity implements View.OnClickListener {

    private String postedName;
    private MyDataBaseHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        postedName = intent.getStringExtra("NAME");
        try {
            helper = new MyDataBaseHelper(this);
            db = helper.getReadableDatabase();
            cursor = db.query("NOTES",
                    new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "NAME = ?",
                    new String[]{postedName},
                    null, null, null);
            Log.d("MyLogs", "what have in cursor " + cursor.getCount() + " items");
        }
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Problem with connect to database", Toast.LENGTH_LONG);
            toast.show();
        }
        ImageView tempImg = (ImageView)findViewById(R.id.image_detail);
        TextView tempTitle = (TextView)findViewById(R.id.title_detail);
        TextView tempDisc = (TextView)findViewById(R.id.desc_detail);
        Button btnEdit = (Button)findViewById(R.id.btn_editDetail);
        Button btnDelete = (Button)findViewById(R.id.btn_deleteDetail);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        cursor.moveToFirst();
        tempTitle.setText(cursor.getString(1));
        tempDisc.setText(cursor.getString(2));
        tempImg.setImageResource(R.drawable.placeholder); //пока что это так, затем переделаю
    }

    public void btn_editClick (View view)
    {
        cursor.moveToFirst();
        String str_id = String.valueOf(cursor.getInt(0));
        Intent intent = new Intent(this,activity_change.class); // в случае вызова интента из фрагмента юзаем не this, a getActivity()
        intent.putExtra(activity_change.extra_data, str_id);
        startActivity(intent);                                          //потому что фрагмент не знает заранее из какого активити он будет вызван
        finish();
    }

    public void btn_deleteClick (View view)
    {
        //Fragment_with_RecyclerView.removeItemFromRV(postedID);
        helper = new MyDataBaseHelper(this);
        db = helper.getWritableDatabase();
        db.delete("NOTES", "_id=" + cursor.getInt(0), null);
        finish();
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_editDetail:
            {btn_editClick(v);
                break;}
            case R.id.btn_deleteDetail:
            {btn_deleteClick(v);
                break;}
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MyLogs", "We onResume in detail activity");
//        Log.d("MyLogs", "What we have in postedName there? "+postedName);
//        try {
//            helper = new MyDataBaseHelper(this);
//            db = helper.getReadableDatabase();
//            cursor = db.query("NOTES",
//                    new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
//                    "NAME = ?",
//                    new String[]{postedName},
//                    null, null, null);
//            Log.d("MyLogs", "what have in cursor " + cursor.getCount() + " items");
//        }
//        catch (SQLiteException e) {
//            Toast toast = Toast.makeText(this, "Problem with connect to database", Toast.LENGTH_LONG);
//            toast.show();
//        }
//        cursor.moveToFirst();
//        TextView tempTitle = (TextView)findViewById(R.id.title_detail);
//        TextView tempDisc = (TextView)findViewById(R.id.desc_detail);
//        tempTitle.setText(cursor.getString(1));
//        tempDisc.setText(cursor.getString(2));
    }
}
