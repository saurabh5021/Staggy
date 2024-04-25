package com.example.staggy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    Handler handler;
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView title, tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.textViewheading);
        tag = findViewById(R.id.tagline);

        logo.setAnimation(topAnim);
        title.setAnimation(bottomAnim);
        tag.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);


//        try
//        {
//            this.getSupportActionBar().hide();
//        }
//        catch (NullPointerException e){}
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_launcher);
//        handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(LauncherActivity.this,MenuActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },3000);

    }
}