package com.jifalops.btleadvertise.Functional;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jifalops.btleadvertise.Activity.Login;
import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 4. 10..
 */
public class Splash  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 1000);



    }

}

