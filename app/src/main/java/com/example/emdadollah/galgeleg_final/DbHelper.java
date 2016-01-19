package com.example.emdadollah.galgeleg_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mourhaf on 11-01-2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "spiller.db";
    public static final String TABLE_NAME = "spiller_table";

    public static final String COL_0 = "Id";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "SCORE";


    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + COL_0 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_1 +" STRING, "
        +COL_2 + " STRING" + ")";
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }

    public Integer deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"NAME = ?",new String[]{name});
    }

    public boolean insertData(String name, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, score);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean updateData(String name, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, score);
        db.update(TABLE_NAME, contentValues, "NAME = ?", new String[] {name});

        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * from " + TABLE_NAME+ " ORDER BY SCORE DESC;", null);
        return res;
    }
}
