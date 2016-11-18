package se.erikgustafsson.ekir.lostchef;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.erikgustafsson.ekir.project.R;

/**
 * This activity handles the splash screen
 */
public class SplashActivity extends Activity{
        DungeonCrawlerApplication app;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            app = (DungeonCrawlerApplication)getApplication();
            int wait_time = 3000;
            if(app.fastStart) {
                wait_time=0;
            }
            setContentView(R.layout.activity_splash);

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                public void run() {

                    // make sure we close the splash screen so the user won't come back when it presses back key

                    finish();
                    Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                    SplashActivity.this.startActivity(intent);
                }

            }, wait_time);
        }
    }