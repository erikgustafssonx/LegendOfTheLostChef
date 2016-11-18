package se.erikgustafsson.ekir.lostchef;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

/**
 * This is the game engine class. It extends SurfaceView and hold the game thread
 * This class is extended in the PackmanHunt class, which is the actuall game
 */
public abstract class GameView extends SurfaceView implements Runnable {
    SurfaceHolder surface;
    volatile boolean game_paused = false;
    volatile boolean game_running = true;
    Context context;
    SoundPool soundPool=null;
    Thread gameloop = null;

    public GameView(Context tcontext) {
        super(tcontext);
        surface = getHolder();
        soundPool=new SoundPool(4, AudioManager.STREAM_MUSIC,0);
        gameloop = new Thread(this);
        gameloop.start();
        context=tcontext;
    }

    Bitmap load_bitmap(String filename) {
        Bitmap result = null;
        try {
            AssetManager assets = context.getAssets();
            InputStream istream = assets.open(filename);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            result = BitmapFactory.decodeStream(istream,null,options);
            istream.close();
            return result;
        } catch (IOException e) {
            return null;
        }
    }

    public void resume() {
        game_paused = false;
    }

    public void pause() {
        game_paused = true;
    }

    public void destroy() {
        game_running = false;
    }

    public void run() {
        while(game_running) {
            if(game_paused) {
                continue;
            }
            if (!surface.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = surface.lockCanvas();

            // Call the game loop method
            gameLoop(canvas);
            surface.unlockCanvasAndPost(canvas);
            try {
                gameloop.sleep(50);
            }
            catch (Exception e){

            }
        }
    }

    // This function should hold the game loop
    public abstract void gameLoop(Canvas canvas);

    public int load_sound(int resId) {
        return soundPool.load(context,resId,1);
    }

    public void play_sound(int sound_id) {
        soundPool.play(sound_id,1,1,1,0,1f);
    }
}
