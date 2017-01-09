package com.dismas.imaya.gatekeeper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Context context = this;

        //Create PackageManager instance
        PackageManager packageManager = context.getPackageManager();

        //check whether the phone is nfc enabled
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)) {

            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);



        }else{
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 300 milliseconds
            v.vibrate(1000);
            // Showing error message that phone is not nfc enabled
            Toast.makeText(context, "Your Device is not NFC enabled",
                    Toast.LENGTH_LONG).show();

            //remove this once the logic is working
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
            //end of what to remove
        }
    }
}
