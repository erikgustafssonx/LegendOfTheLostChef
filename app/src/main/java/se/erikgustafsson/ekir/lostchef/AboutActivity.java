package se.erikgustafsson.ekir.lostchef;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.erikgustafsson.ekir.project.R;


public class AboutActivity extends Activity {
    Button btn_credits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        btn_credits=(Button)findViewById(R.id.btn_credits);
        btn_credits.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, CreditsActivity.class);
                AboutActivity.this.startActivity(intent);
            }
        });
    }
}
