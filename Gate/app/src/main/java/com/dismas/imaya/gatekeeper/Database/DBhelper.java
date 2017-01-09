package com.dismas.imaya.gatekeeper.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imaya on 10/4/16.
 */

public class DBhelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "Users";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String DESIGNATION = "designation";
    public static final String DEPARTMENT = "department";
    public static final String DOB = "dob";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String CARD_SERIAL = "card_serial";
    public static final String IMG_URL = "img_url";
    public static final String ALLOWED_AREAS = "allowed_areas";
    // Database Information
    static final String DB_NAME = "GateKeeper.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, "+ DESIGNATION + " TEXT NOT NULL,"+ DEPARTMENT + " TEXT NOT NULL, "+ IMG_URL + " TEXT NOT NULL, " + DOB + " TEXT NOT NULL, "
            + HEIGHT + " TEXT NOT NULL, "+ WEIGHT + " TEXT NOT NULL," + ALLOWED_AREAS + " TEXT NOT NULL,"+ CARD_SERIAL + " TEXT NOT NULL);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllAreas(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(8));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
}