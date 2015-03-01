package com.example.springflower.buybuygo.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MyDB {
    public SQLiteDatabase db = null;
    private final static String DATABASE_NAME = "db1.db";
    private final static String TABLE_NAME = "table03";
    private final static String _ID = "_id";
    private final static String TITLE = "title";
    private final static String CONTENT = "content";
    private final static String LAT = "lat";
    private final static String LON = "lon";
    private final static String Snum = "snum";
    private final static String S1 = "s1";
    private final static String S2 = "s2";
    private final static String CREATBY = "creatby";
    private final static String Dis = "distance";
    private final static String CREATORPhone = "creatorphone";
    //////////////////////////////
    private final static String TABLE_NAME2 = "table02";
    private final static String _ID2 = "_id";
    private final static String USER = "username";
    private final static String UPHONE = "userphone";
    private final static String CREATE_TABLE2 =
            "CREATE TABLE " + TABLE_NAME2 + " ("+
                    _ID2     + " INTEGER PRIMARY KEY," +
                    USER   + " TEXT," +
                    UPHONE	+ " TEXT)";

    public Cursor getPHONE(long rowID ){
        Cursor mCursor = db.query(TABLE_NAME2,
                new String[] {_ID2, USER, UPHONE },
                _ID2 + "=" + rowID, null, null, null, null, null);
        return mCursor;
    }
    public long append2(String user, String uphone){
        ContentValues args = new ContentValues();
        args.put(USER, user);
        args.put(UPHONE, uphone);
        return db.insert(TABLE_NAME2, null, args);
    }





    //創建資料表欄位
    private final static String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ("+
                    _ID     + " INTEGER PRIMARY KEY," +
                    TITLE   + " TEXT," +
                    CONTENT	+ " TEXT," +
                    LAT     + " REAL," +
                    LON     + " REAL,"  +
                    Snum    + " INTEGER,"+
                    S1      + " TEXT," +
                    S2      + " TEXT,"+
                    CREATBY + " TEXT,"+
                    Dis     + " REAL,"+
                    CREATORPhone+ " TEXT)";

    private Context mCtx= null;
    public MyDB(Context ctx){
        this.mCtx = ctx;
    }
    public void open() throws SQLException {
        db = mCtx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        try{
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_TABLE2);
        }catch(Exception e){
        }
    }
    public void close(){
        db.close();
    }
    public Cursor getALL(){
        return db.query(TABLE_NAME,
                new String[] {_ID, TITLE, CONTENT, LAT, LON, Snum, S1, S2, CREATBY, Dis, CREATORPhone},
                null, null, null, null, null, null);
    }
    public Cursor getByPosition(int pos){
        Cursor mCursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME + " WHERE " + _ID + "=" + pos, null);
        return mCursor;
    }
    public Cursor get(long rowId) throws SQLException {
        Cursor mCursor = db.query(TABLE_NAME,
                new String[] {_ID, TITLE, CONTENT, LAT, LON, Snum, S1, S2, CREATBY, Dis, CREATORPhone },
                _ID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public long append(String title, String content,
                       double lat, double lon, int snum, String s1,
                       String s2, String creatby, double dis, String cretpho){
        ContentValues args = new ContentValues();
        args.put(TITLE, title);
        args.put(CONTENT, content);
        args.put(LAT, lat);
        args.put(LON, lon);
        args.put(Snum, snum);
        args.put(S1, s1);
        args.put(S2, s2);
        args.put(CREATBY, creatby);
        args.put(Dis, dis);
        args.put(CREATORPhone, cretpho);
        return db.insert(TABLE_NAME, null, args);
    }
    public boolean delete(long rowId){
        return db.delete(TABLE_NAME, _ID + "=" + rowId, null) >0;

    }
    public boolean update(long rowId, String title, String content,
                          double lat, double lon, int snum, String s1,
                          String s2, String creatby, double dis, String cretpho){
        ContentValues args = new ContentValues();
        args.put(TITLE, title);
        args.put(CONTENT, content);
        args.put(LAT, lat);
        args.put(LON, lon);
        args.put(Snum, snum);
        args.put(S1, s1);
        args.put(S2, s2);
        args.put(CREATBY, creatby);
        args.put(Dis, dis);
        args.put(CREATORPhone, cretpho);
        return db.update(TABLE_NAME, args, _ID + "=" + rowId, null) > 0;
    }



}
