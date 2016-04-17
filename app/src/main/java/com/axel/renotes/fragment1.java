package com.axel.renotes;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Александр on 13.04.2016.
 */
public class Fragment1 extends DialogFragment implements View.OnClickListener {

    int postedID;
    private MyDataBaseHelper helper;
    private Cursor cursor;
    private SQLiteDatabase db;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postedID = getArguments().getInt("index", 0)+1;
        try {
            helper = new MyDataBaseHelper(getContext());
            db = helper.getReadableDatabase();
            cursor = db.query("NOTES",
                    new String[]{"_id", "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(postedID)},
                    null, null, null);
            Log.d("MyLogs","what have in cursor "+cursor.getCount()+" items");
        }
        catch (SQLiteException e) {
            Toast toast = Toast.makeText(getContext(),"Problem with connect to database",Toast.LENGTH_LONG);
            toast.show();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_row, null);

        ImageView tempImg = (ImageView)v.findViewById(R.id.thumbnail);
        TextView tempTitle = (TextView)v.findViewById(R.id.titleNote);
        TextView tempDisc = (TextView)v.findViewById(R.id.disc);
        Button btnEdit = (Button)v.findViewById(R.id.btn_edit);
        Button btnDelete = (Button)v.findViewById(R.id.btn_delete);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        cursor.moveToFirst();
        tempTitle.setText(cursor.getString(1));
        tempDisc.setText(cursor.getString(2));
        tempImg.setImageResource(R.drawable.placeholder); //пока что это так, затем переделаю

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
        String str_id = String.valueOf(postedID);
        Log.d("MyLogs", "we in editClick handler");
        Intent intent = new Intent(getActivity(),activity_change.class); // в случае вызова интента из фрагмента юзаем не this, a getActivity()
        intent.putExtra(activity_change.extra_data,str_id);
        startActivity(intent);                                          //потому что фрагмент не знает заранее из какого активити он будет вызван
    }

    public void btn_deleteClick (View view)
    {
        Log.d("MyLogs","we in deleteClick handler");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_edit:
            {btn_editClick(v);
                break;}
            case R.id.btn_delete:
            {btn_deleteClick(v);
                break;}
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        //незнаю пока как тут изменить, обновить
    }
}
