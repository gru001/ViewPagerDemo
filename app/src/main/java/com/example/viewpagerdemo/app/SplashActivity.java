package com.example.viewpagerdemo.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
/**
 * provides ui for {@link SplashActivity} which launcher of our app
 *
 * @author pranit(gru001)
 */
import com.example.viewpagerdemo.app.profile.ProfilePhotosActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // handler for 3 sec delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // start ProfilePhotosActivity and finish current
                startActivity(ProfilePhotosActivity.getStartIntent(SplashActivity.this));
                finish();
            }
        },3*1000);
    }
}
