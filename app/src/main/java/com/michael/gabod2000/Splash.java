package com.michael.gabod2000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    TextView hobbyText;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        hobbyText=findViewById(R.id.hobby);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mEditor = sharedpreferences.edit();
        Animation bottomUp = AnimationUtils.loadAnimation(Splash.this,
                R.anim.bottom_up);
        hobbyText.setAnimation(bottomUp);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                if (sharedpreferences!=null){
//                    if (sharedpreferences.getString("login","").equals("done")){
                        Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();
//                    }else {
//                        Intent mainIntent = new Intent(Splash.this, Authentication.class);
//                        Splash.this.startActivity(mainIntent);
//                        Splash.this.finish();
//                    }
//                }



            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}