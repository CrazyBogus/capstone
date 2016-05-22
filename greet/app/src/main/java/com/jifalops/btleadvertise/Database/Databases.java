package com.jifalops.btleadvertise.Database;

import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 18..
 */
public class Databases {
    public static final class CreateDB_MYINFO implements BaseColumns {
        public static final String USERID = "userid";
        public static final String _ID = "id";
        public static final String NICKNAME = "nickname";
        public static final String _TABLENAME = "my_info";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("+_ID+" integer primary key autoincrement, "+USERID+" text not null, "+NICKNAME+" text not null );";
       // "create table if not exists my info(_ID integer primary key autoincrement, userid text not null, nickname text not null);"

        }

    public static final class CreateDB_CARD implements BaseColumns {
        public static final String _ID = "id";
        public static final ArrayList<String> KEYWORD = new ArrayList<String>() {
            {
                add("keyword1");
                add("keyword2");
                add("keyword3");
                add("keyword4");
                add("keyword5");
            }
        };
        public static final String IMAGE = "image";
        public static final String NICKNAME = "nickname";
        public static final String ONOFF = "onoff";
        public static final String PHONENUMBER = "phonenumber";
        public static final String SNS = "sns";
        public static final String STATUS = "status";
        public static final String _TABLENAME = "card";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +KEYWORD.get(0)+" text not null, "
                        +IMAGE+" blob not null, "
                        +NICKNAME+" text not null, "
                        +ONOFF+" text not null, "
                        +PHONENUMBER+" text not null, "
                        +SNS+" text not null, "
                        +STATUS+" text not null );";
    }
}
