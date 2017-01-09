package com.dismas.imaya.gatekeeper;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        //Verification unsuccessful
        // send the tone to the "alarm" stream (classic beeps go there) with 100% volume
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Vibrate for 300 milliseconds
        v.vibrate(1000);

        ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        toneG.startTone(ToneGenerator.TONE_CDMA_ANSWER, 20000);// 200 is duration in ms

        //End of verification unsuccessful
    }
}
