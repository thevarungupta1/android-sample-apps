    package com.thevarungupta.selfieapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

    public class SplashActivity extends AppCompatActivity {

        public static final int DELAY_TIME = 1500;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Make it fullscreen
            // IMPORTANT : Call this before the call to setContentView
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.activity_splash);

            // Use a handler to execute code with a delay
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    loadActivity();
                }
            }, DELAY_TIME);
        }

        private void loadActivity() {
            // Start the MainActivity using intent
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
}
