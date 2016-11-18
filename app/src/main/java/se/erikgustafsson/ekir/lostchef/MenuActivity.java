package se.erikgustafsson.ekir.lostchef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.erikgustafsson.ekir.project.R;


public class MenuActivity extends Activity{
    Button btn_resume;
    Button btn_play;
    Button btn_about;
    CheckBox chk_music;
    DungeonCrawlerApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (DungeonCrawlerApplication)getApplication();
        app.init_music();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_resume=(Button) findViewById(R.id.btn_resume);
        btn_play= (Button) findViewById(R.id.btn_play);
        btn_about= (Button) findViewById(R.id.btn_about);
        chk_music = (CheckBox) findViewById(R.id.chk_music);
        chk_music.setChecked(app.getMusic());
        if(app.gameStarted) {
            btn_resume.setVisibility(View.VISIBLE);
            btn_play.setVisibility(View.GONE);
        } else {
            btn_resume.setVisibility(View.GONE);
            btn_play.setVisibility(View.VISIBLE);
        }
        btn_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                MenuActivity.this.startActivity(intent);
            }
        });
        chk_music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                app.setMusic(isChecked);
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        if(!app.gameStarted && app.fastStart) {
            startGame();
        }
    }
    public void startGame() {
        Intent intent = new Intent(MenuActivity.this, PlayActivity.class);
        MenuActivity.this.startActivity(intent);
    }

}
