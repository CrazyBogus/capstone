package com.jifalops.btleadvertise.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 4. 4..
 */
public class See_Other extends Activity{
    private TextView keyword_1;
    private TextView keyword_2;
    private TextView keyword_3;
    private TextView keyword_4;
    private TextView keyword_5;
    private ImageView Iv_profile_image;
    private ImageView Iv_add_number;
    private ImageView Iv_sns;
    private ImageView Iv_add_card;
    private ImageView Iv_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.see_other);
        init();


        Iv_video.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(See_Other.this)
                        .setTitle("서비스 준비 중")
                        .setMessage("서비스 준비 중입니다")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });
        Iv_sns.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(See_Other.this)
                        .setTitle("서비스 준비 중")
                        .setMessage("서비스 준비 중입니다")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });
        Iv_add_number.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(See_Other.this)
                        .setTitle("서비스 준비 중")
                        .setMessage("서비스 준비 중입니다")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });
    }

    public void init()
    {
        keyword_1 = (TextView) findViewById(R.id.keyword1);
        keyword_2 = (TextView) findViewById(R.id.keyword2);
        keyword_3 = (TextView) findViewById(R.id.keyword3);
        keyword_4 = (TextView) findViewById(R.id.keyword4);
        keyword_5 = (TextView) findViewById(R.id.keyword5);
        keyword_1.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_2.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_3.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_4.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_5.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        Iv_add_number = (ImageView) findViewById(R.id.Iv_add_number);
        Iv_add_number.setImageResource(R.drawable.see_other_save_number);
        Iv_sns = (ImageView) findViewById(R.id.Iv_sns);
        Iv_sns.setImageResource(R.drawable.see_other_sns);
        Iv_add_card = (ImageView) findViewById(R.id.Iv_add_card);
        Iv_add_card.setImageResource(R.drawable.see_other_add);
        Iv_profile_image = (ImageView) findViewById(R.id.Iv_profile_image);
        Iv_video = (ImageView) findViewById(R.id.Iv_video);
        Iv_video.setImageResource(R.drawable.my_profile_video_default);

    }

    }
