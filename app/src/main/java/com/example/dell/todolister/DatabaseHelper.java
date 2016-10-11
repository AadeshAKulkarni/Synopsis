package com.example.dell.todolister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String db="toDo";
    private static final String table="list";

    public DatabaseHelper(Context context) {
        super(context,db,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,DATA TEXT)");
    }
    public void onUpdated(String input1,String input2) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("DATA",input1);
        db.update(table, cv, "ID='"+input2+"'", null);
    }

    public Cursor onView(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+table+" order by ID desc",null);
        return res;
    }
    public void onDelete(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res1=db.rawQuery("delete from "+table+" where ID='"+id+"'",null);
        db.execSQL("delete from "+table+" where ID='"+id+"'");
    }
    public boolean onInsert(String title,String data){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentvalue=new ContentValues();
        contentvalue.put("TITLE",title);
        contentvalue.put("DATA",data);
        long result=db.insert(table,null,contentvalue);
        if(result==-1)
            return false;
        else
            return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+table);
        onCreate(db);
    }
}
