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
    private static final int DATABASE_VERSION = 17;
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

    public void my_info_insert(String id, String nickname, ArrayList<String> keyword)
    {
        mDB = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d("In my_info_insert : ", "id : "+id+" nickname : "+nickname);
        values.put("nickname", nickname);
        values.put("userid", id);
        values.put("keyword1", keyword.get(0));
        values.put("keyword2", keyword.get(1));
        values.put("keyword3", keyword.get(2));
        values.put("keyword4", keyword.get(3));
        values.put("keyword5", keyword.get(4));


        mDB.insert("my_info",null,values);
    }


    public void my_profile_insert(int card_number, ArrayList<String> keyword, byte[] image, String nickname, ArrayList<String> onoff, String phonenumber, ArrayList<String> sns, String status)
    {
        mDB = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", card_number);
        values.put("keyword1", keyword.get(0));
        values.put("keyword2", keyword.get(1));
        values.put("keyword3", keyword.get(2));
        values.put("keyword4", keyword.get(3));
        values.put("keyword5", keyword.get(4));
        values.put("image", image);
        values.put("nickname", nickname);
        values.put("onoff1", onoff.get(0));
        values.put("onoff2", onoff.get(1));
        values.put("onoff3", onoff.get(2));
        values.put("onoff4", onoff.get(3));
        values.put("onoff5", onoff.get(4));
        values.put("phonenumber", phonenumber);
        values.put("sns1", sns.get(0));
        values.put("sns2", sns.get(1));
        values.put("sns3", sns.get(2));
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

    // 모든 Data 읽기
    public void my_info_selectAll(String tableName){
        String sql = "select * from " + tableName + ";";
        Cursor results = mDB.rawQuery(sql, null);

        results.moveToFirst();

        while(!results.isAfterLast()){
            int id = results.getInt(0);
            String voca = results.getString(1);
            String v2 = results.getString(2);
            String v3 = results.getString(3);
            String v4 = results.getString(4);
            String v5 = results.getString(5);

            String v7 = results.getString(7);
            Log.d("test","index= "+id+" voca="+voca+" voca="+v2+" voca="+v3+" voca="+v4);
            results.moveToNext();
        }
        results.close();
    }

    // 모든 Data 읽기
    public void my_profile_selectAll(String tableName){
        String sql = "select * from " + tableName + ";";
        Cursor results = mDB.rawQuery(sql, null);

        results.moveToFirst();

        while(!results.isAfterLast()){
            int id = results.getInt(0);
            String voca = results.getString(1);
            String v2 = results.getString(2);
            String v3 = results.getString(3);
            String v4 = results.getString(4);
            String v5 = results.getString(5);
            String v6 = results.getString(6);
            String v7 = results.getString(7);
            Log.d("test","index= "+id+" voca="+voca+" voca="+v2+" voca="+v3+" voca="+v4);
            results.moveToNext();
        }
        results.close();
    }

    // Data 삭제
    public void my_profile_remove(int index){
        String tableName = "card";
        String sql = "delete from " + tableName + " where id = "+index+";";
        mDB.execSQL(sql);
    }


    // Table 삭제
    public void removeTable(String tableName){
        String sql = "drop table " + tableName;
        mDB.execSQL(sql);
    }

    public void close(){
        mDB.close();
    }
}
