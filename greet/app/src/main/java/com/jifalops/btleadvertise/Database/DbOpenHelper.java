package com.jifalops.btleadvertise.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 18..
 */
public class DbOpenHelper {
    private static final String DATABASE_NAME = "intercard.db";
    private static final int DATABASE_VERSION = 7;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;


    private class DatabaseHelper extends SQLiteOpenHelper {

        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Databases.CreateDB_MYINFO._CREATE);

            db.execSQL(Databases.CreateDB_CARD._CREATE);


        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+Databases.CreateDB_MYINFO._TABLENAME);
            db.execSQL("DROP TABLE IF EXISTS "+Databases.CreateDB_CARD._TABLENAME);
            Log.d("DB Upgrade", "Good Job");
            onCreate(db);
        }
    }

    public DbOpenHelper(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void my_info_insert(String id, String nickname)
    {
        mDB = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d("In my_info_insert : ", "id : "+id+" nickname : "+nickname);
        values.put("nickname", nickname);
        values.put("userid", id);


        mDB.insert("my_info",null,values);
    }


    public void my_profile_insert(ArrayList<String> keyword, byte[] image, String nickname, ArrayList<String> onoff, String phonenumber, ArrayList<String> sns, String status)
    {
        mDB = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("keyword1", keyword.get(1));
        values.put("image", image);
        values.put("nickname", nickname);
        //values.put("onoff", onoff);
        values.put("phonenumber", phonenumber);
        values.put("sns", sns.get(0));
        values.put("status", status);

        Log.d("In my_profile_insert : ", "keyword1 : "+keyword+
                " image : "+image+
                " nickname : " + nickname+
                " onoff : " + onoff+
                " phonenumber : " + phonenumber+
                " sns : " + sns+
                " status : " + status);
        mDB.insert("card",null,values);
    }

    public void select()
    {
        String sql = "select * from card where id = 2;";
        Cursor result = mDB.rawQuery(sql, null);

        // result(Cursor 객체)가 비어 있으면 false 리턴
        if(result.moveToFirst()){
            String voca = result.getString(1);
            String vocal = result.getString(4);
            Log.d("In select : ", voca + " " +vocal);
        }
        else
        Log.d("여기에 오는거였다.","ㅋㅋㅋ");

        result.close();
    }


    public void close(){
        mDB.close();
    }
}
