package com.example.paul.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Paul on 11/27/2015.
 */

public class EventDB {

    DBhelper helper;
    String[] columns ={
            DBhelper.EVENT_COLUMN_ID,
            DBhelper.EVENT_COLUMN_LATITUDE,
            DBhelper.EVENT_COLUMN_LONGITUDE,
            DBhelper.EVENT_COLUMN_TIME,
            DBhelper.EVENT_COLUMN_DURATION,
            DBhelper.EVENT_COLUMN_TITLE,
            DBhelper.EVENT_COLUMN_DESCRIPTION,
            DBhelper.EVENT_COLUMN_COST,
            DBhelper.EVENT_COLUMN_HOST};

    public EventDB(Context context){
        // TODO auto-generated constructor stub
        helper = new DBhelper(context);
    }

    public long insertData(float latitude, float longitude, float time, float duration,
                           String title, String description, float cost, String host){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.EVENT_COLUMN_LATITUDE,latitude);
        contentValues.put(DBhelper.EVENT_COLUMN_LONGITUDE,longitude);
        contentValues.put(DBhelper.EVENT_COLUMN_TIME,time);
        contentValues.put(DBhelper.EVENT_COLUMN_DURATION,duration);
        contentValues.put(DBhelper.EVENT_COLUMN_TITLE,title);
        contentValues.put(DBhelper.EVENT_COLUMN_DESCRIPTION,description);
        contentValues.put(DBhelper.EVENT_COLUMN_COST,cost);
        contentValues.put(DBhelper.EVENT_COLUMN_HOST,host);
        long id = db.insert(DBhelper.EVENT_TABLE_NAME, null, contentValues);
        return id;
    }
    public void getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DBhelper.EVENT_TABLE_NAME, columns, null, null, null, null, null);
        while(cursor.moveToNext()){
            int uid = cursor.getInt(cursor.getColumnIndex(columns[0]));
            float latitude  = cursor.getFloat(cursor.getColumnIndex(columns[1]));
            float longitude  = cursor.getFloat(cursor.getColumnIndex(columns[2]));
            float time  = cursor.getFloat(cursor.getColumnIndex(columns[3]));
            float duration  = cursor.getFloat(cursor.getColumnIndex(columns[4]));
            String title  = cursor.getString(cursor.getColumnIndex(columns[5]));
            String description = cursor.getString(cursor.getColumnIndex(columns[6]));
            float cost  = cursor.getFloat(cursor.getColumnIndex(columns[7]));
            String host = cursor.getString(cursor.getColumnIndex(columns[8]));
            Log.v("EventData", uid +" "+ latitude +" "+  longitude +" "+  time +" "+  duration +" "+  title +" "+  description +" "+  cost +" "+  host);
        }
    }
    public void getData(int position){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DBhelper.EVENT_TABLE_NAME, columns, null, null, null, null, null);
        int uid = cursor.getInt(cursor.getColumnIndex(columns[0]));
        float latitude  = cursor.getFloat(cursor.getColumnIndex(columns[1]));
        float longitude  = cursor.getFloat(cursor.getColumnIndex(columns[2]));
        float time  = cursor.getFloat(cursor.getColumnIndex(columns[3]));
        float duration  = cursor.getFloat(cursor.getColumnIndex(columns[4]));
        String title  = cursor.getString(cursor.getColumnIndex(columns[5]));
        String description = cursor.getString(cursor.getColumnIndex(columns[6]));
        float cost  = cursor.getFloat(cursor.getColumnIndex(columns[7]));
        String host = cursor.getString(cursor.getColumnIndex(columns[8]));


    }


    static class DBhelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "someDB2";
        public static final String EVENT_TABLE_NAME = "events";
        public static final String EVENT_COLUMN_ID = "id";
        public static final String EVENT_COLUMN_LATITUDE = "latitude";
        public static final String EVENT_COLUMN_LONGITUDE = "longitude";
        public static final String EVENT_COLUMN_TIME= "time";
        public static final String EVENT_COLUMN_DURATION = "duration";
        public static final String EVENT_COLUMN_TITLE = "title";
        public static final String EVENT_COLUMN_DESCRIPTION = "description";
        public static final String EVENT_COLUMN_COST = "cost";
        public static final String EVENT_COLUMN_HOST = "host";

        public static final int EVENT_TABLE_VERSION = 2;

        public DBhelper(Context context) {
            super(context, DATABASE_NAME, null, EVENT_TABLE_VERSION);
            Log.v("SQL", "construtor was called");
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(
                    "CREATE TABLE " +   EVENT_TABLE_NAME + " (" +
                            EVENT_COLUMN_ID + " integer primary key, " +
                            EVENT_COLUMN_LATITUDE + " text," +
                            EVENT_COLUMN_LONGITUDE  + " text," +
                            EVENT_COLUMN_TIME + " text, " +
                            EVENT_COLUMN_DURATION + " text, " +
                            EVENT_COLUMN_TITLE + " text, " +
                            EVENT_COLUMN_DESCRIPTION + " text, " +
                            EVENT_COLUMN_COST + " text, " +
                            EVENT_COLUMN_HOST + " text)"
            );
            Log.v("SQL", "onCreate was called");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME);
            onCreate(db);
            Log.v("SQL", "onUpgrade was called");
        }

        public boolean insertContact(String name, String phone, String email, String street, String pass) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("phone", phone);
            contentValues.put("email", email);
            contentValues.put("street", street);
            contentValues.put("password", pass);
            db.insert("contacts", null, contentValues);
            return true;
        }

        public Cursor getData(int id) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from contacts where id=" + id + "", null);
            return res;
        }

        public int numberOfRows() {
            SQLiteDatabase db = this.getReadableDatabase();
            int numRows = (int) DatabaseUtils.queryNumEntries(db, EVENT_TABLE_NAME);
            return numRows;
        }

        public boolean updateContact(Integer id, String name, String phone, String email, String street, String password) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("phone", phone);
            contentValues.put("email", email);
            contentValues.put("street", street);
            contentValues.put("password", password);
            db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
            return true;
        }

        public Integer deleteContact(Integer id) {
            SQLiteDatabase db = this.getWritableDatabase();
            return db.delete("contacts",
                    "id = ? ",
                    new String[]{Integer.toString(id)});
        }

        public ArrayList<String> getAllCotacts() {
            ArrayList<String> array_list = new ArrayList<String>();

            //hp = new HashMap();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res = db.rawQuery("select * from contacts", null);
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(res.getString(res.getColumnIndex(EVENT_COLUMN_TITLE)));
                res.moveToNext();
            }
            return array_list;
        }

    }
}