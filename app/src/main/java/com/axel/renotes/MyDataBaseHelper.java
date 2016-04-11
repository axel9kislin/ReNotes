package com.axel.renotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Александр on 11.04.2016.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME ="notesbase";
    private static final int DB_VERSION = 1;

    public MyDataBaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTES (_id INTEGER PRIMATY KEY AUTOINCREMENT, " +
                "NAME TEXT" +
                "DESCRIPTION TEXT" +
                "IMAGE_RESOURCE_ID INTEGER);");
        insertNote(db, "Tested note","i simple tested my db", R.drawable.placeholder);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertNote(SQLiteDatabase db, String name, String desc, int res_id)
    {
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("DESCRIPTION", desc);
        cv.put("IMAGE_RESOURCE_ID", res_id);
        db.insert("NOTES",null,cv);
    }
}
