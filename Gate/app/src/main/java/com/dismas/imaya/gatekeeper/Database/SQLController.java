package com.dismas.imaya.gatekeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by imaya on 10/4/16.
 */

public class SQLController {
    private DBhelper dbHelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        ourcontext = c;
    }

    public SQLController open() throws SQLException {
        dbHelper = new DBhelper(ourcontext);
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String designation, String department, String img_url,
                       String dob, String height, String weight, String allowed_areas, String card_serial) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBhelper.NAME, name);
        contentValue.put(DBhelper.DESIGNATION, designation);
        contentValue.put(DBhelper.DEPARTMENT, department);
        contentValue.put(DBhelper.IMG_URL, img_url);
        contentValue.put(DBhelper.DOB, dob);
        contentValue.put(DBhelper.HEIGHT, height);
        contentValue.put(DBhelper.WEIGHT, weight);
        contentValue.put(DBhelper.ALLOWED_AREAS, allowed_areas);
        contentValue.put(DBhelper.CARD_SERIAL, card_serial);
        database.insert(DBhelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DBhelper._ID, DBhelper.NAME,
                DBhelper.DESIGNATION, DBhelper.DEPARTMENT, DBhelper.IMG_URL,
                DBhelper.DOB, DBhelper.HEIGHT, DBhelper.WEIGHT, DBhelper.ALLOWED_AREAS, DBhelper.CARD_SERIAL};
        Cursor cursor = database.query(DBhelper.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String designation, String department, String img_url,
                      String dob, String height, String weight, String allowed_areas, String card_serial) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.NAME, name);
        contentValues.put(DBhelper.DESIGNATION, designation);
        contentValues.put(DBhelper.DEPARTMENT, department);
        contentValues.put(DBhelper.IMG_URL, img_url);
        contentValues.put(DBhelper.DOB, dob);
        contentValues.put(DBhelper.HEIGHT, height);
        contentValues.put(DBhelper.WEIGHT, weight);
        contentValues.put(DBhelper.ALLOWED_AREAS, allowed_areas);
        contentValues.put(DBhelper.CARD_SERIAL, card_serial);
        int i = database.update(DBhelper.TABLE_NAME, contentValues,
                DBhelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DBhelper.TABLE_NAME, DBhelper._ID + "=" + _id, null);
    }

}
