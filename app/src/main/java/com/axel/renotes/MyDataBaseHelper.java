package com.axel.renotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


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
                "IMAGE_RESOURCE_ID TEXT);");
        insertNote(db, "Тестим лист на анимацию ", "Тест вставки русского шрифта", "тратата");
        insertNote(db, "Тестовая запись 2 ", "надо вставить что-то", "какой-то ресурс");
        insertNote(db, "запись 3 ", "начальная запись", "ресурс");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public static void insertNote(SQLiteDatabase db, String name, String desc, String res_id)
    {
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("DESCRIPTION", desc);
        cv.put("IMAGE_RESOURCE_ID", res_id);
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
