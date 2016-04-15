package com.axel.renotes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Александр on 13.04.2016.
 */
public class Fragment1 extends DialogFragment {

    int postedID;
    private MyDataBaseHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyLogs", "we in onCreate fragment");
        postedID = getArguments().getInt("index", 0);
        Log.d("MyLogs","we can add args? "+postedID);
        try {
            helper = new MyDataBaseHelper(getContext());
            db = helper.getReadableDatabase();
            cursor = db.query("NOTES",
                    new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(postedID)},
                    null, null, null);
            Log.d("MyLogs","what have in cursor"+cursor.getColumnName(1));
        }
        catch (SQLiteException e) {
            Log.d("MyLogs", e.getMessage());
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_row, null);
        Log.d("MyLogs","we in onCreateView");
        ImageView tempImg = (ImageView)v.findViewById(R.id.thumbnail);
        TextView tempTitle = (TextView)v.findViewById(R.id.titleNote);
        TextView tempDisc = (TextView)v.findViewById(R.id.disc);
        cursor.moveToFirst();
        Log.d("MyLogs", "we finding all our items");
        String x = cursor.getString(1);
        Log.d("MyLogs", "we have in x: "+x);
        tempTitle.setText(x); //здесь ошибка
        Log.d("MyLogs", "we can set Name");
        tempDisc.setText(cursor.getString(2));
        tempImg.setImageResource(R.drawable.placeholder); //пока что это так, затем переделаю
        Log.d("MyLogs", "we set our params");
        return v;
    }

    public static Fragment1 newInstance(int index)
    {
        Fragment1 f = new Fragment1();
        Bundle args = new Bundle();
        args.putInt("index",index);
        f.setArguments(args);
        return f;
    }

    public void btn_editClick (View view)
    {
        Log.d("MyLogs","we in editClick handler");
    }

    public void btn_saveClick (View view)
    {
        Log.d("MyLogs","we in saveClick handler");
    }

    public void btn_deleteClick (View view)
    {
        Log.d("MyLogs","we in deleteClick handler");
    }


}
