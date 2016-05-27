package com.jifalops.btleadvertise.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jifalops.btleadvertise.R;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 17..
 */
public class Add_Keyword extends ActionBarActivity{

    private TextView keyword_1;
    private TextView keyword_2;
    private TextView keyword_3;
    private TextView keyword_4;
    private TextView keyword_5;

    private TextView social_1;
    private TextView social_2;
    private TextView social_3;
    private TextView social_4;
    private TextView social_5;
    private TextView social_6;
    private TextView social_7;
    private TextView social_8;
    private TextView social_9;
    private TextView social_10;
    private TextView social_11;

    private TextView living_1;
    private TextView living_2;
    private TextView living_3;
    private TextView living_4;
    private TextView living_5;
    private TextView living_6;
    private TextView living_7;
    private TextView living_8;
    private TextView living_9;
    private TextView living_10;
    private TextView living_11;

    private TextView culture_1;
    private TextView culture_2;
    private TextView culture_3;
    private TextView culture_4;
    private TextView culture_5;
    private TextView culture_6;
    private TextView culture_7;
    private TextView culture_8;
    private TextView culture_9;
    private TextView culture_10;
    private TextView culture_11;

    private TextView sports_1;
    private TextView sports_2;
    private TextView sports_3;
    private TextView sports_4;
    private TextView sports_5;
    private TextView sports_6;
    private TextView sports_7;
    private TextView sports_8;

    private TextView hobby_1;
    private TextView hobby_2;
    private TextView hobby_3;
    private TextView hobby_4;
    private TextView hobby_5;
    private Button btn_delete_keywords;

    private RelativeLayout category_1;
    private RelativeLayout category_2;
    private RelativeLayout category_3;
    private RelativeLayout category_4;
    private RelativeLayout category_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_keyword);

        init();
        clickevent();
        // ActionBar의 배경색 변경
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF01afff));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void init()
    {

        keyword_1 = (TextView) findViewById(R.id.keyword1);
        keyword_2 = (TextView) findViewById(R.id.keyword2);
        keyword_3 = (TextView) findViewById(R.id.keyword3);
        keyword_4 = (TextView) findViewById(R.id.keyword4);
        keyword_5 = (TextView) findViewById(R.id.keyword5);

        social_1 = (TextView) findViewById(R.id.social_1);
        social_2 = (TextView) findViewById(R.id.social_2);
        social_3 = (TextView) findViewById(R.id.social_3);
        social_4 = (TextView) findViewById(R.id.social_4);
        social_5 = (TextView) findViewById(R.id.social_5);
        social_6 = (TextView) findViewById(R.id.social_6);
        social_7 = (TextView) findViewById(R.id.social_7);
        social_8 = (TextView) findViewById(R.id.social_8);
        social_9 = (TextView) findViewById(R.id.social_9);
        social_10 = (TextView) findViewById(R.id.social_10);
        social_11 = (TextView) findViewById(R.id.social_11);

        living_1 = (TextView) findViewById(R.id.living_1);
        living_2 = (TextView) findViewById(R.id.living_2);
        living_3 = (TextView) findViewById(R.id.living_3);
        living_4 = (TextView) findViewById(R.id.living_4);
        living_5 = (TextView) findViewById(R.id.living_5);
        living_6 = (TextView) findViewById(R.id.living_6);
        living_7 = (TextView) findViewById(R.id.living_7);
        living_8 = (TextView) findViewById(R.id.living_8);
        living_9 = (TextView) findViewById(R.id.living_9);
        living_10 = (TextView) findViewById(R.id.living_10);
        living_11 = (TextView) findViewById(R.id.living_11);

        culture_1 = (TextView) findViewById(R.id.culture_1);
        culture_2 = (TextView) findViewById(R.id.culture_2);
        culture_3 = (TextView) findViewById(R.id.culture_3);
        culture_4 = (TextView) findViewById(R.id.culture_4);
        culture_5 = (TextView) findViewById(R.id.culture_5);
        culture_6 = (TextView) findViewById(R.id.culture_6);
        culture_7 = (TextView) findViewById(R.id.culture_7);
        culture_8 = (TextView) findViewById(R.id.culture_8);
        culture_9 = (TextView) findViewById(R.id.culture_9);
        culture_10 = (TextView) findViewById(R.id.culture_10);
        culture_11 = (TextView) findViewById(R.id.culture_11);

        sports_1 = (TextView) findViewById(R.id.sports_1);
        sports_2 = (TextView) findViewById(R.id.sports_2);
        sports_3 = (TextView) findViewById(R.id.sports_3);
        sports_4 = (TextView) findViewById(R.id.sports_4);
        sports_5 = (TextView) findViewById(R.id.sports_5);
        sports_6 = (TextView) findViewById(R.id.sports_6);
        sports_7 = (TextView) findViewById(R.id.sports_7);
        sports_8 = (TextView) findViewById(R.id.sports_8);

        hobby_1 = (TextView) findViewById(R.id.hobby_1);
        hobby_2 = (TextView) findViewById(R.id.hobby_2);
        hobby_3 = (TextView) findViewById(R.id.hobby_3);
        hobby_4 = (TextView) findViewById(R.id.hobby_4);
        hobby_5 = (TextView) findViewById(R.id.hobby_5);

        btn_delete_keywords = (Button) findViewById(R.id.delete_keywords);
//
        category_1 = (RelativeLayout) findViewById(R.id.category_1);
        category_2 = (RelativeLayout) findViewById(R.id.category_2);
        category_3 = (RelativeLayout) findViewById(R.id.category_3);
        category_4 = (RelativeLayout) findViewById(R.id.category_4);
        category_5 = (RelativeLayout) findViewById(R.id.category_5);

        keyword_1.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_2.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_3.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_4.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_5.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        social_1.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_2.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_3.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_4.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_5.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_6.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_7.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_8.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_9.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        social_11.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_1.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_2.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_3.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_4.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_5.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_6.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_7.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_8.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_9.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_10.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        living_11.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_1.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_2.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_3.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_4.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_5.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_6.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_7.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_8.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_9.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_10.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        culture_11.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_1.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_2.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_3.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_4.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_5.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_6.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_7.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        sports_8.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        hobby_1.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        hobby_2.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        hobby_3.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        hobby_4.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        hobby_5.setBackgroundResource(R.drawable.add_keyword_btn_un_keyword);
        category_1.setBackgroundResource(R.drawable.add_keyword_category_social);
        category_2.setBackgroundResource(R.drawable.add_keyword_category_living);
        category_3.setBackgroundResource(R.drawable.add_keyword_category_culture);
        category_4.setBackgroundResource(R.drawable.add_keyword_category_sports);
        category_5.setBackgroundResource(R.drawable.add_keyword_category_hobby);

    }

    public void clickevent()
    {
        btn_delete_keywords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword_1.setText("");
                keyword_2.setText("");
                keyword_3.setText("");
                keyword_4.setText("");
                keyword_5.setText("");
            }
        });

        keyword_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               keyword_1.setText("");
            }
        });
        keyword_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword_2.setText("");
            }
        });
        keyword_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword_3.setText("");
            }
        });
        keyword_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword_4.setText("");
            }
        });
        keyword_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyword_5.setText("");
            }
        });
        social_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_1.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_1.getText()) keyword_2.setText(social_1.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_1.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_1.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_1.getText());
            }
        });
        social_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_2.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_2.getText()) keyword_2.setText(social_2.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_2.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_2.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_2.getText());
            }
        });
        social_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_3.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_3.getText()) keyword_2.setText(social_3.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_3.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_3.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_3.getText());
            }
        });
        social_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_4.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_4.getText()) keyword_2.setText(social_4.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_4.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_4.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_4.getText());
            }
        });
        social_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_5.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_5.getText()) keyword_2.setText(social_5.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_5.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_5.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_5.getText());
            }
        });
        social_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_6.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_6.getText()) keyword_2.setText(social_6.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_6.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_6.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_6.getText());
            }
        });
        social_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_7.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_7.getText()) keyword_2.setText(social_7.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_7.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_7.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_7.getText());
            }
        });
        social_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_8.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_8.getText()) keyword_2.setText(social_8.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_8.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_8.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_8.getText());
            }
        });
        social_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_9.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_9.getText()) keyword_2.setText(social_9.getText());
                else if(keyword_3.getText()=="") keyword_3.setText(social_9.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_9.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_9.getText());
            }
        });
        social_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     if(keyword_1.getText()=="") keyword_1.setText(social_11.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_11.getText()) keyword_2.setText(social_11.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(social_11.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_11.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_11.getText());
            }
        });
        living_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_1.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_1.getText()) keyword_2.setText(living_1.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_1.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_1.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_1.getText());
            }
        });
        living_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_2.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_2.getText()) keyword_2.setText(living_2.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_2.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_2.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_2.getText());
            }
        });
        living_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_3.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_3.getText()) keyword_2.setText(living_3.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_3.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_3.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_3.getText());
            }
        });
        living_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_4.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_4.getText()) keyword_2.setText(living_4.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_4.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_4.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_4.getText());
            }
        });
        living_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_5.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_5.getText()) keyword_2.setText(living_5.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_5.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_5.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_5.getText());
            }
        });
        living_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_6.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_6.getText()) keyword_2.setText(living_6.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_6.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_6.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_6.getText());
            }
        });
        living_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_7.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_7.getText()) keyword_2.setText(living_7.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_7.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_7.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_7.getText());
            }
        });
        living_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_8.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_8.getText()) keyword_2.setText(living_8.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_8.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_8.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_8.getText());
            }
        });
        living_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_9.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_9.getText()) keyword_2.setText(living_9.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_9.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_9.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_9.getText());
            }
        });
        living_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_10.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_10.getText()) keyword_2.setText(living_10.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_10.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_10.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_10.getText());
            }
        });
        living_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(living_11.getText());
                else if(keyword_2.getText()=="" && keyword_1!=living_11.getText()) keyword_2.setText(living_11.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(living_11.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(living_11.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(living_11.getText());
            }
        });
        culture_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_1.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_1.getText()) keyword_2.setText(culture_1.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_1.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_1.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_1.getText());
            }
        });
        culture_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_2.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_2.getText()) keyword_2.setText(culture_2.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_2.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_2.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_2.getText());
            }
        });
        culture_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_3.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_3.getText()) keyword_2.setText(culture_3.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_3.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_3.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_3.getText());
            }
        });
        culture_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_4.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_4.getText()) keyword_2.setText(culture_4.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_4.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_4.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_4.getText());
            }
        });
        culture_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_5.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_5.getText()) keyword_2.setText(culture_5.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_5.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_5.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_5.getText());
            }
        });
        culture_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_6.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_6.getText()) keyword_2.setText(culture_6.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_6.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_6.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_6.getText());
            }
        });
        culture_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_7.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_7.getText()) keyword_2.setText(culture_7.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_7.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_7.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_7.getText());
            }
        });
        culture_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_8.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_8.getText()) keyword_2.setText(culture_8.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_8.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_8.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_8.getText());
            }
        });
        culture_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_9.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_9.getText()) keyword_2.setText(culture_9.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_9.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_9.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_9.getText());
            }
        });
        culture_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_10.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_10.getText()) keyword_2.setText(culture_10.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_10.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_10.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_10.getText());
            }
        });
        culture_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(culture_11.getText());
                else if(keyword_2.getText()=="" && keyword_1!=culture_11.getText()) keyword_2.setText(culture_11.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(culture_11.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(culture_11.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(culture_11.getText());
            }
        });
        sports_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_1.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_1.getText()) keyword_2.setText(sports_1.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_1.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_1.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_1.getText());
            }
        });
        sports_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_2.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_2.getText()) keyword_2.setText(sports_2.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_2.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_2.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_2.getText());
            }
        });
        sports_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_3.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_3.getText()) keyword_2.setText(sports_3.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_3.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_3.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_3.getText());
            }
        });
        sports_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_4.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_4.getText()) keyword_2.setText(sports_4.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_4.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_4.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_4.getText());
            }
        });
        sports_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_5.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_5.getText()) keyword_2.setText(sports_5.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_5.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_5.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_5.getText());
            }
        });
        sports_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_6.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_6.getText()) keyword_2.setText(sports_6.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_6.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_6.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_6.getText());
            }
        });
        sports_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_7.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_7.getText()) keyword_2.setText(sports_7.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_7.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_7.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_7.getText());
            }
        });
        sports_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(sports_8.getText());
                else if(keyword_2.getText()=="" && keyword_1!=sports_8.getText()) keyword_2.setText(sports_8.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(sports_8.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(sports_8.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(sports_8.getText());
            }
        });
        hobby_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(hobby_1.getText());
                else if(keyword_2.getText()=="" && keyword_1!=hobby_1.getText()) keyword_2.setText(hobby_1.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(hobby_1.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(hobby_1.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(hobby_1.getText());
            }
        });
        hobby_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(hobby_2.getText());
                else if(keyword_2.getText()=="" && keyword_1!=hobby_2.getText()) keyword_2.setText(hobby_2.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(hobby_2.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(hobby_2.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(hobby_2.getText());
            }
        });
        hobby_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(hobby_3.getText());
                else if(keyword_2.getText()=="" && keyword_1!=hobby_3.getText()) keyword_2.setText(hobby_3.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(hobby_3.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(hobby_3.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(hobby_3.getText());
            }
        });
        hobby_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(hobby_4.getText());
                else if(keyword_2.getText()=="" && keyword_1!=hobby_4.getText()) keyword_2.setText(hobby_4.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(hobby_4.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(hobby_4.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(hobby_4.getText());
            }
        });
        hobby_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(hobby_5.getText());
                else if(keyword_2.getText()=="" && keyword_1!=hobby_5.getText()) keyword_2.setText(hobby_5.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(hobby_5.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(hobby_5.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(hobby_5.getText());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_keword, menu);

        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    // back 버튼 눌렀을 때 이벤트 처리
    @Override
    public boolean onSupportNavigateUp()
    {
        //여기서 키워드 정보 넘겨주기
        ArrayList<String> keyword = new ArrayList<String>();
        keyword.add(0,keyword_1.getText().toString());
        keyword.add(1,keyword_2.getText().toString());
        keyword.add(2,keyword_3.getText().toString());
        keyword.add(3,keyword_4.getText().toString());
        keyword.add(4,keyword_5.getText().toString());

        Intent intent = new Intent(this, Add_Profile.class);

        intent.putStringArrayListExtra("keyword", keyword);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Log.d("In add_keyword : ", "intent 넘기기");
//        Bundle b = new Bundle();
//        b.putString("kakaoID", kakaoID); //Your id
//        b.putString("kakaoNICKNAME", kakaoNickname); //Your id
//        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);

        finish();
        keyword_1.setBackground(null);
        keyword_2.setBackground(null);
        keyword_3.setBackground(null);
        keyword_4.setBackground(null);
        keyword_5.setBackground(null);
        social_1.setBackground(null);
        social_2.setBackground(null);
        social_3.setBackground(null);
        social_4.setBackground(null);
        social_5.setBackground(null);
        social_6.setBackground(null);
        social_7.setBackground(null);
        social_8.setBackground(null);
        social_9.setBackground(null);
        social_10.setBackground(null);
        social_11.setBackground(null);
        living_1.setBackground(null);
        living_2.setBackground(null);
        living_3.setBackground(null);
        living_4.setBackground(null);
        living_5.setBackground(null);
        living_6.setBackground(null);
        living_7.setBackground(null);
        living_8.setBackground(null);
        living_9.setBackground(null);
        living_10.setBackground(null);
        living_11.setBackground(null);
        culture_1.setBackground(null);
        culture_2.setBackground(null);
        culture_3.setBackground(null);
        culture_4.setBackground(null);
        culture_5.setBackground(null);
        culture_6.setBackground(null);
        culture_7.setBackground(null);
        culture_8.setBackground(null);
        culture_9.setBackground(null);
        culture_10.setBackground(null);
        culture_11.setBackground(null);
        sports_1.setBackground(null);
        sports_2.setBackground(null);
        sports_3.setBackground(null);
        sports_4.setBackground(null);
        sports_5.setBackground(null);
        sports_6.setBackground(null);
        sports_7.setBackground(null);
        sports_8.setBackground(null);
        hobby_1.setBackground(null);
        hobby_2.setBackground(null);
        hobby_3.setBackground(null);
        hobby_4.setBackground(null);
        hobby_5.setBackground(null);
        category_1.setBackground(null);
        category_2.setBackground(null);
        category_3.setBackground(null);
        category_4.setBackground(null);
        category_5.setBackground(null);
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        keyword_1.setBackground(null);
        keyword_2.setBackground(null);
        keyword_3.setBackground(null);
        keyword_4.setBackground(null);
        keyword_5.setBackground(null);
        social_1.setBackground(null);
        social_2.setBackground(null);
        social_3.setBackground(null);
        social_4.setBackground(null);
        social_5.setBackground(null);
        social_6.setBackground(null);
        social_7.setBackground(null);
        social_8.setBackground(null);
        social_9.setBackground(null);
        social_10.setBackground(null);
        social_11.setBackground(null);
        living_1.setBackground(null);
        living_2.setBackground(null);
        living_3.setBackground(null);
        living_4.setBackground(null);
        living_5.setBackground(null);
        living_6.setBackground(null);
        living_7.setBackground(null);
        living_8.setBackground(null);
        living_9.setBackground(null);
        living_10.setBackground(null);
        living_11.setBackground(null);
        culture_1.setBackground(null);
        culture_2.setBackground(null);
        culture_3.setBackground(null);
        culture_4.setBackground(null);
        culture_5.setBackground(null);
        culture_6.setBackground(null);
        culture_7.setBackground(null);
        culture_8.setBackground(null);
        culture_9.setBackground(null);
        culture_10.setBackground(null);
        culture_11.setBackground(null);
        sports_1.setBackground(null);
        sports_2.setBackground(null);
        sports_3.setBackground(null);
        sports_4.setBackground(null);
        sports_5.setBackground(null);
        sports_6.setBackground(null);
        sports_7.setBackground(null);
        sports_8.setBackground(null);
        hobby_1.setBackground(null);
        hobby_2.setBackground(null);
        hobby_3.setBackground(null);
        hobby_4.setBackground(null);
        hobby_5.setBackground(null);
        category_1.setBackground(null);
        category_2.setBackground(null);
        category_3.setBackground(null);
        category_4.setBackground(null);
        category_5.setBackground(null);

    }

}
