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

public class DBadapter {

    DBhelper helper;
    String[] columns ={
            DBhelper.CONTACTS_COLUMN_ID,
            DBhelper.CONTACTS_COLUMN_NAME,
            DBhelper.CONTACTS_COLUMN_PASSWORD,
            DBhelper.CONTACTS_COLUMN_EMAIL,
            DBhelper.CONTACTS_COLUMN_EVENTCOUNT,
            DBhelper.CONTACTS_COLUMN_EVENTS,
            DBhelper.CONTACTS_COLUMN_PHONE};

    public DBadapter(Context context){
        // TODO auto-generated constructor stub
        helper = new DBhelper(context);
    }

    public long insertData(String name, String password, String email, int eventcount, String events, String phone){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.CONTACTS_COLUMN_NAME,name);
        contentValues.put(DBhelper.CONTACTS_COLUMN_PASSWORD,password);
        contentValues.put(DBhelper.CONTACTS_COLUMN_EMAIL,email);
        contentValues.put(DBhelper.CONTACTS_COLUMN_EVENTCOUNT,eventcount);
        contentValues.put(DBhelper.CONTACTS_COLUMN_EVENTS,events);
        contentValues.put(DBhelper.CONTACTS_COLUMN_PHONE,phone);
        long id = db.insert(DBhelper.CONTACTS_TABLE_NAME, null, contentValues);
        return id;
    }
    public void getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query(DBhelper.CONTACTS_TABLE_NAME, columns, null, null, null, null, null);
        while(cursor.moveToNext()){
            int uid = cursor.getInt(cursor.getColumnIndex(columns[0]));
            String name  = cursor.getString(cursor.getColumnIndex(columns[1]));
            String password  = cursor.getString(cursor.getColumnIndex(columns[2]));
            String email  = cursor.getString(cursor.getColumnIndex(columns[3]));
            int eventcount  = cursor.getInt(cursor.getColumnIndex(columns[4]));
            String events  = cursor.getString(cursor.getColumnIndex(columns[5]));
            String phone = cursor.getString(cursor.getColumnIndex(columns[6]));
            Log.v("EventData", uid +" "+ name +" "+  password +" "+  email +" "+  eventcount +" "+  events +" "+  phone);
        }
    }
    public String getName(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name FROM " + DBhelper.CONTACTS_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex(columns[0]));
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("Name","found" + x + " -- ");
            }
        }
        return x;
    }
    public String getPassword(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, password FROM " + DBhelper.CONTACTS_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("longitude","found" + x + " -- ");
            }
        }
        return x;
    }
    public String getEmail(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, email FROM " + DBhelper.CONTACTS_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("Email","found" + x + " -- ");
            }
        }
        return x;
    }
    public int getEventCount(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, eventcount FROM " + DBhelper.CONTACTS_TABLE_NAME, null);
        int x = 0;
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getInt(1);
                Log.v("event count ","found" + x + " -- ");
            }
        }
        return x;
    }
    //the events are given in a list of event ids with "," in between them
    public String getEvents(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, events FROM " + DBhelper.CONTACTS_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("events","found" + x + " -- ");
            }
        }
        return x;
    }
    public String getPhone(int id){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, phone FROM " + DBhelper.CONTACTS_TABLE_NAME, null);
        String x = "";
        while(cursor.moveToNext()) {
            int uid = cursor.getInt(0);
            if(uid == id) {
                x = cursor.getString(1);
                Log.v("phone","found" + x + " -- ");
            }
        }
        return x;
    }

    public int numberOfRows() {
        SQLiteDatabase db = helper.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, DBhelper.CONTACTS_TABLE_NAME);
        return numRows;
    }


    static class DBhelper extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "someDB4";
        public static final String CONTACTS_TABLE_NAME = "contacts";
        public static final String CONTACTS_COLUMN_ID = "id";
        public static final String CONTACTS_COLUMN_NAME = "name";
        public static final String CONTACTS_COLUMN_PASSWORD= "password";
        public static final String CONTACTS_COLUMN_EMAIL = "email";
        public static final String CONTACTS_COLUMN_EVENTCOUNT = "eventcount";
        public static final String CONTACTS_COLUMN_EVENTS = "events";
        public static final String CONTACTS_COLUMN_PHONE = "phone";
        public static final int CONTACTS_TABLE_VERSION = 3;

        public DBhelper(Context context) {
            super(context, DATABASE_NAME, null, CONTACTS_TABLE_VERSION);
            Log.v("SQL", "construtor was called");
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(
                    "CREATE TABLE " + CONTACTS_TABLE_NAME +
                            " (" + CONTACTS_COLUMN_ID + " integer primary key, " +
                            CONTACTS_COLUMN_NAME + " text," +
                            CONTACTS_COLUMN_PASSWORD  + " text," +
                            CONTACTS_COLUMN_EMAIL + " text, " +
                            CONTACTS_COLUMN_EVENTCOUNT + " integar, " +
                            CONTACTS_COLUMN_EVENTS + " text, " +
                            CONTACTS_COLUMN_PHONE + " text)"
            );
            Log.v("SQL", "onCreate was called");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME);
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
            int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
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
        //-----------------------------------------------------
    }
}