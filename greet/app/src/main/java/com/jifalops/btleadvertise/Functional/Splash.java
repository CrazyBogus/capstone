package com.jifalops.btleadvertise.Functional;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.jifalops.btleadvertise.Activity.Login;
import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 4. 10..
 */
public class Splash  extends Activity {

    private ImageView splash_img;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        splash_img = (ImageView) findViewById(R.id.splash_image);
        splash_img.setImageResource(R.drawable.splash);
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 2000);



    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        splash_img.setImageDrawable(null);
    }

}

