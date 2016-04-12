package com.axel.renotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by Александр on 11.04.2016.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME ="notesbase.db";
    private static final int DB_VERSION = 1;

    public MyDataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTES (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME TEXT, " +
                "DESCRIPTION TEXT, " +
                "IMAGE_RESOURCE_ID INTEGER);");
        Log.d("MyLogs", "it is readOnly?" + db.isReadOnly());
        insertNote(db, "Tested", "i simple tested my db", R.drawable.placeholder);
        insertNote(db, "Tested2", "i want do add 2 writes", R.drawable.placeholder);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public static void insertNote(SQLiteDatabase db, String name, String desc, int res_id)
    {

        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        Log.d("MyLogs", "we have in cv.name is " + cv.get("NAME") + " string");
        cv.put("DESCRIPTION", desc);
        Log.d("MyLogs", "we have in cv.desc is " + cv.get("DESCRIPTION") + " string");
        cv.put("IMAGE_RESOURCE_ID", res_id);
        Log.d("MyLogs", "we have in cv.image_resources is " + cv.get("IMAGE_RESOURCE_ID") + " string");
        try
        {
            long rowID = db. insertOrThrow("NOTES", null, cv);
            Log.d("MyLogs","we added in db "+rowID +" row");
        }
        catch (SQLiteException e)
        {
            Log.d("MyLogs", e.getMessage());
        }


    }
}
