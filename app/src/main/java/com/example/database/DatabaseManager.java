package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context){
        super(context, "MoviesDB", null, 1);
    }

    public void onCreate(SQLiteDatabase db){
        String sql = "create table MovieTable(";
        sql += "id integer primary key autoincrement, ";
        sql += "title text, director text, year text)";
        db.execSQL(sql);
    }

    public void insert(String movieName, String directorName, String yearName){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into MovieTable values(";
        sql += "null, '"+movieName+"', '"+directorName+"', '"+yearName+"')";
        db.execSQL(sql);
        db.close();
    }

    public void updateByTitle(String title, String director, String year){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update MovieTable set director = '"+director+"'";
        sql += "where title = '"+title+"'";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<String> getTitles(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from MovieTable";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            String title = cursor.getString(1);
            list.add(title);
        }
        db.close();
        return list;
    }

    public String[] get(String movieTitle){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from MovieTable where title = '"+movieTitle+"'";
        Cursor cursor = db.rawQuery(sql, null);
        String[] entry = new String[3];
        if (cursor.moveToFirst()){
            String title = cursor.getString(1);
            String director = cursor.getString(2);
            String year = cursor.getString(3);
            entry[0] = title;
            entry[1] = director;
            entry[2] = year;
        }
        db.close();
        return entry;
    }

    public ArrayList<String> getDirector (String directorName){
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> list = new ArrayList<String>();
        String sql = "select * from MovieTable where director = '"+directorName+"'";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()) {
            String director = cursor.getString(1);
            list.add(director);
        }
        db.close();
        return list;
    }

    public void delete (String deleteMovie){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from MovieTable where title = '"+deleteMovie+"'";
        db.execSQL(sql);
        db.close();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
