package com.dismas.imaya.gatekeeper;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dismas.imaya.gatekeeper.Database.DBhelper;
import com.squareup.picasso.Picasso;

public class AccessDeniedActivity extends AppCompatActivity {
    private DBhelper dbHelper;
    Context context = this;
    //Declaring our ImageView
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_denied);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String label = intent.getStringExtra("label");
        final String Card_Serial = intent.getStringExtra("card_serial");

        //Verification unsuccessful
        // send the tone to the "alarm" stream (classic beeps go there) with 100% volume
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 300 milliseconds
        v.vibrate(1000);

        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ANSWER, 20000);// 200 is duration in ms

        //End of verification unsuccessful

        // Showing error message that phone is not nfc enabled
        Toast.makeText(this, "Verification is successful",
                Toast.LENGTH_LONG).show();

        TextView name = (TextView) findViewById(R.id.name);
        TextView designation= (TextView) findViewById(R.id.designation);
        TextView department = (TextView) findViewById(R.id.department);
        TextView dob = (TextView) findViewById(R.id.dob);
        TextView height = (TextView) findViewById(R.id.height);
        TextView weight = (TextView) findViewById(R.id.weight);
        TextView allowed_areas = (TextView) findViewById(R.id.allowed_areas);
        TextView card_serial = (TextView) findViewById(R.id.card_serial);
        //Initializing the ImageView
        imageView = (ImageView) findViewById(R.id.imageView5);


        String nametv = getName(Card_Serial);
        String designationtv = getDesignation(Card_Serial);
        String departmenttv = getDepartment(Card_Serial);
        String dobtv = getDOB(Card_Serial);
        String heighttv = getHeight(Card_Serial);
        String weighttv = getWeight(Card_Serial);
        String allowed_areastv = getAllowedAreas(Card_Serial);
        String card_serialtv = getCardSerial(Card_Serial);
        String imageUri = getImgUrl(Card_Serial);

        name.setText(nametv);
        designation.setText(designationtv);
        department.setText(departmenttv);
        dob.setText(dobtv);
        height.setText(heighttv);
        weight.setText(weighttv);
        allowed_areas.setText(allowed_areastv);
        card_serial.setText(card_serialtv);

        Picasso.with(context).load(imageUri)
                .placeholder(R.drawable.small)
                .error(R.drawable.error_load)
                .into(imageView);

    }

    public String getName(String Card_Serial) {

        Cursor cursor = null;
        String nametv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT name FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        nametv = cursor.getString(cursor.getColumnIndex("name"));

        return nametv;

    }
    public String getDesignation(String Card_Serial) {

        Cursor cursor = null;
        String designationtv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT designation FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        designationtv = cursor.getString(cursor.getColumnIndex("designation"));

        return designationtv;

    }
    public String getDepartment(String Card_Serial) {

        Cursor cursor = null;
        String designationtv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT department FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        designationtv = cursor.getString(cursor.getColumnIndex("department"));

        return designationtv;

    }
    public String getDOB(String Card_Serial) {

        Cursor cursor = null;
        String dobtv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT dob FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        dobtv = cursor.getString(cursor.getColumnIndex("dob"));

        return dobtv;

    }
    public String getHeight(String Card_Serial) {

        Cursor cursor = null;
        String heighttv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT height FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        heighttv = cursor.getString(cursor.getColumnIndex("height"));

        return heighttv;

    }
    public String getWeight(String Card_Serial) {

        Cursor cursor = null;
        String weighttv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT weight FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        weighttv = cursor.getString(cursor.getColumnIndex("weight"));

        return weighttv;

    }
    public String getAllowedAreas(String Card_Serial) {

        Cursor cursor = null;
        String allowed_areastv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT allowed_areas FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        allowed_areastv = cursor.getString(cursor.getColumnIndex("allowed_areas"));

        return allowed_areastv;

    }
    public String getCardSerial(String Card_Serial) {

        Cursor cursor = null;
        String card_serialtv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT card_serial FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        card_serialtv = cursor.getString(cursor.getColumnIndex("card_serial"));

        return card_serialtv;

    }
    public String getImgUrl(String Card_Serial) {

        Cursor cursor = null;
        String imgurltv = "";
        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT img_url FROM Users WHERE card_serial=?", new String[] {Card_Serial + ""});
        cursor.moveToFirst();
        imgurltv = cursor.getString(cursor.getColumnIndex("img_url"));

        return imgurltv;

    }

}