package com.example.neozhang.circle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public long insertData(double latitude, double longitude, float time, float duration,
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
            double latitude  = cursor.getFloat(cursor.getColumnIndex(columns[1]));
            double longitude  = cursor.getFloat(cursor.getColumnIndex(columns[2]));
            float time  = cursor.getFloat(cursor.getColumnIndex(columns[3]));
            float duration  = cursor.getFloat(cursor.getColumnIndex(columns[4]));
            String title  = cursor.getString(cursor.getColumnIndex(columns[5]));
            String description = cursor.getString(cursor.getColumnIndex(columns[6]));
            float cost  = cursor.getFloat(cursor.getColumnIndex(columns[7]));
            String host = cursor.getString(cursor.getColumnIndex(columns[8]));
            Log.v("EventData", uid +" "+ latitude +" "+  longitude +" "+  time +" "+  duration +" "+  title +" "+  description +" "+  cost +" "+  host);
        }
    }
    public float getLat(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, latitude FROM " + DBhelper.EVENT_TABLE_NAME, null);
        float x = -1;
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex(columns[0]));
            if(uid == id) {
                x = cursor.getFloat(1);
                Log.v("latitude","found" + x + " -- ");
            }
        }
        return x;
    }
    public float getLong(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, longitude FROM " + DBhelper.EVENT_TABLE_NAME, null);
        float x = -1;
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getFloat(1);
                Log.v("longitude","found" + x + " -- ");
            }
        }
        return x;
    }
    public float getTime(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, time FROM " + DBhelper.EVENT_TABLE_NAME, null);
        float x = -1;
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getFloat(1);
                Log.v("time","found" + x + " -- ");
            }
        }
        return x;
    }
    public float getDuration(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, duration FROM " + DBhelper.EVENT_TABLE_NAME, null);
        float x = -1;
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getFloat(1);
                Log.v("duration","found" + x + " -- ");
            }
        }
        return x;
    }
    public String getTitle(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, title FROM " + DBhelper.EVENT_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("title","found" + x + " -- ");
            }
        }
        return x;
    }
    public String getDescription(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, description FROM " + DBhelper.EVENT_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("description","found" + x + " -- ");
            }
        }
        return x;
    }
    public float getCost(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, cost FROM " + DBhelper.EVENT_TABLE_NAME, null);
        float x = -1;
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getFloat(1);
                Log.v("cost","found" + x + " -- ");
            }
        }
        return x;
    }
    public String getHost(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, host FROM " + DBhelper.EVENT_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("host","found" + x + " -- ");
            }
        }
        return x;
    }
    public int numberOfRows() {
        SQLiteDatabase db = helper.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DBhelper.EVENT_TABLE_NAME);
        return numRows;
    }

    static class DBhelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "someDB3";
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


    }
}