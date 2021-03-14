package com.gdtidm.convert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    private ImageView image_splash;
    private TextView tv1_splash, tv2_splash;
    private View line_splash;

    private static int SPLASH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_splash);

        image_splash        =   findViewById(R.id.image_splash);
        tv1_splash          =   findViewById(R.id.teks1_splash);
        tv2_splash          =   findViewById(R.id.teks2_splash);
        line_splash         =   findViewById(R.id.view_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, SPLASH);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.myanimation);
        image_splash.startAnimation(anim);
        tv1_splash.startAnimation(anim);
        tv2_splash.startAnimation(anim);
        line_splash.startAnimation(anim);
    }
}