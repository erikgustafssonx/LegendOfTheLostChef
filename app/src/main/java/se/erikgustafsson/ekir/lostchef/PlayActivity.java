package se.erikgustafsson.ekir.lostchef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * This activity handles the game itself
 */
public class PlayActivity extends Activity{
    DungeonCrawler dungeonCrawler;
    DungeonCrawlerApplication app;
    public void LaunchAbout() {
        Intent intent = new Intent(PlayActivity.this, AboutActivity.class);
        PlayActivity.this.startActivity(intent);
    }

    public void LaunchMenu() {
        Intent intent = new Intent(PlayActivity.this, MenuActivity.class);
        PlayActivity.this.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app=(DungeonCrawlerApplication)getApplication();
        app.gameStarted=true;
        dungeonCrawler = new DungeonCrawler(this);
        setContentView(dungeonCrawler);
        // http://stackoverflow.com/questions/12388771/how-to-set-activity-to-fullscreen-mode-in-android
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            dungeonCrawler.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
        /* immersive mode
        * https://developer.android.com/training/system-ui/immersive.html
        */
    }

    @Override public void onResume() {
        super.onResume();
        dungeonCrawler.resume();
    }

    @Override public void onPause() {
        super.onPause();
        dungeonCrawler.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dungeonCrawler.destroy();
    }




}