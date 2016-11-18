package se.erikgustafsson.ekir.lostchef;

import android.app.Application;
import android.media.MediaPlayer;

import com.erikgustafsson.ekir.project.R;

/**
 * This is the data shared between activities
 * Only the music player is shared so that different activities can start and stop it
 * Actually in the current implementation this is only used by the MainActivity
 * but more activities might use it later
 */
public class DungeonCrawlerApplication extends Application {
    public boolean fastStart = false;
    public boolean gameStarted = false;
    private MediaPlayer MediaSpelaren = null;
    private boolean music=false;

    public void init_music() {
        if(MediaSpelaren!=null) {
            return;
        }
        music=true;
        MediaSpelaren = MediaPlayer.create(this, R.raw.music);
        MediaSpelaren.setLooping(true);
        MediaSpelaren.setVolume(0.1f, 0.1f);
        MediaSpelaren.start();
    }

    public void setMusic(boolean tmusic) {
        music = tmusic;
        if(music) {
            MediaSpelaren.start();
        } else {
            MediaSpelaren.pause();
        }
    }

    public boolean getMusic() {
        return music;
    }
}
