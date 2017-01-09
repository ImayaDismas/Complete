package com.dismas.imaya.gatekeeper;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dismas.imaya.gatekeeper.Database.DBhelper;

import java.util.ArrayList;
import java.util.List;

import static com.dismas.imaya.gatekeeper.Database.DBhelper.TABLE_NAME;

public class VerifyActivity extends AppCompatActivity {
    private DBhelper dbHelper;
    private SQLiteDatabase database;
    private static int SPLASH_TIME_OUT = 3000;
    Button button;

    // list of NFC technologies detected:
    private final String[][] techList = new String[][] {
            new String[] {
                    NfcA.class.getName(),
                    NfcB.class.getName(),
                    NfcF.class.getName(),
                    NfcV.class.getName(),
                    IsoDep.class.getName(),
                    MifareClassic.class.getName(),
                    MifareUltralight.class.getName(), Ndef.class.getName()
            }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        addListenerOnButtonCancel();

        final String Card_Serial =  "E14351D5";

         Intent intent = getIntent();
        final String label = intent.getStringExtra("label");

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        dbHelper = new DBhelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        List<String> labels = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            while(cursor.moveToNext()){
                labels.add(cursor.getString(9));
            }
        }

        // closing connection
        cursor.close();
        db.close();

        final String allowed_areastv = getAllowedAreas(Card_Serial);

        if (labels.contains(Card_Serial)) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    if(label.equals(allowed_areastv))
                    {
                        Intent intent = new Intent(VerifyActivity.this, ResultsActivity.class);
                        intent.putExtra("card_serial", Card_Serial);
                        intent.putExtra("label", label);
                        startActivity(intent);

                        // close this activity
                        finish();
                    }
                    else
                    {
                        Intent intent = new Intent(VerifyActivity.this, AccessDeniedActivity.class);
                        intent.putExtra("card_serial", Card_Serial);
                        intent.putExtra("label", label);
                        startActivity(intent);
                        finish();

                        // close this activity
                        finish();
                    }

                }
            }, SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    Intent intent = new Intent(VerifyActivity.this, ErrorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

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
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // creating pending intent:
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
//        // creating intent receiver for NFC events:
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
//        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
//        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
//        // enabling foreground dispatch for getting intent from NFC event:
//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // disabling foreground dispatch:
//        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
//        nfcAdapter.disableForegroundDispatch(this);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
//            final String Card_Serial =  ByteArrayToHexString(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
//
//            intent = getIntent();
//            final String label = intent.getStringExtra("label");
//
//            // Select All Query
//            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
//
//            dbHelper = new DBhelper(getApplicationContext());
//
//            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            // looping through all rows and adding to list
//            List<String> labels = new ArrayList<String>();
//            if (cursor.moveToFirst()) {
//                while(cursor.moveToNext()){
//                    labels.add(cursor.getString(9));
//                }
//            }
//
//            // closing connection
//            cursor.close();
//            db.close();
//
//            final String allowed_areastv = getAllowedAreas(Card_Serial);
//
//            if (labels.contains(Card_Serial)) {
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        // Start your app main activity
//                        if(label.equals(allowed_areastv))
//                        {
//                            Intent intent = new Intent(VerifyActivity.this, ResultsActivity.class);
//                            intent.putExtra("card_serial", Card_Serial);
//                            intent.putExtra("label", label);
//                            startActivity(intent);
//
//                            // close this activity
//                            finish();
//                        }
//                        else
//                        {
//                            Intent intent = new Intent(VerifyActivity.this, AccessDeniedActivity.class);
//                            intent.putExtra("card_serial", Card_Serial);
//                            intent.putExtra("label", label);
//                            startActivity(intent);
//                            finish();
//
//                            // close this activity
//                            finish();
//                        }
//
//                    }
//                }, SPLASH_TIME_OUT);
//            } else {
//                new Handler().postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        // This method will be executed once the timer is over
//                        Intent intent = new Intent(VerifyActivity.this, ErrorActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                }, SPLASH_TIME_OUT);
//            }
//        }
//    }
//
//    private String ByteArrayToHexString(byte [] inarray) {
//        int i, j, in;
//        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
//        String out= "";
//
//        for(j = 0 ; j < inarray.length ; ++j)
//        {
//            in = (int) inarray[j] & 0xff;
//            i = (in >> 4) & 0x0f;
//            out += hex[i];
//            i = in & 0x0f;
//            out += hex[i];
//        }
//        return out;
//    }

    public void addListenerOnButtonCancel() {

        final Context context = this;

        button = (Button) findViewById(R.id.cancel);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, VerifyActivity.class);
                startActivity(intent);
                finish();

            }

        });

    }
}
