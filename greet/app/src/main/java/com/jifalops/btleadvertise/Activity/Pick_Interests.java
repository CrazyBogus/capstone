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

import com.jifalops.btleadvertise.Fragment.FirstFragment;
import com.jifalops.btleadvertise.R;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 17..
 */
public class Pick_Interests extends ActionBarActivity {

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

    private Button btn_delete_keywords;

    private RelativeLayout category_1;
    private LinearLayout category_2;
    private LinearLayout category_3;
    private LinearLayout category_4;
    private LinearLayout category_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_interests);

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
        btn_delete_keywords = (Button) findViewById(R.id.delete_keywords);
//
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
//        category_1 = (RelativeLayout) findViewById(R.id.category_1);
//        category_2 = (LinearLayout) findViewById(R.id.category_2);
//        category_3 = (LinearLayout) findViewById(R.id.category_3);
//        category_4 = (LinearLayout) findViewById(R.id.category_4);
//        category_5 = (LinearLayout) findViewById(R.id.category_5);
//
////        Drawable drawable1 = getResources().getDrawable(R.drawable.add_keyword_category_hobby);
//
//        category_1.setBackground(drawable1);
//        category_2.setBackground(drawable1);
//        category_3.setBackground(drawable1);
//        category_4.setBackground(drawable1);
//        category_5.setBackground(drawable1);

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
        social_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(keyword_1.getText()=="") keyword_1.setText(social_10.getText());
                else if(keyword_2.getText()=="" && keyword_1!=social_10.getText()) keyword_2.setText(social_10.getText());
                else if(keyword_3.getText()=="" ) keyword_3.setText(social_10.getText());
                else if(keyword_4.getText()=="") keyword_4.setText(social_10.getText());
                else if(keyword_5.getText()=="") keyword_5.setText(social_10.getText());
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

        Intent intent = new Intent(Pick_Interests.this, MainActivity.class);
        intent.putExtra("keyword", keyword);

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
//        FirstFragment frament = new FirstFragment();
//        Bundle bundle = new Bundle();
//        bundle.putStringArrayList("keyword", keyword);
//
//        frament.setArguments(bundle);

//        Intent intent = new Intent(this, FirstFragment.class);
//
//        intent.putStringArrayListExtra("keyword", keyword);
//
    //        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        Log.d("In add_keyword : ", "intent 넘기기");
////        Bundle b = new Bundle();
////        b.putString("kakaoID", kakaoID); //Your id
////        b.putString("kakaoNICKNAME", kakaoNickname); //Your id
////        intent.putExtras(b); //Put your id to your next Intent
            //startActivity(intent);

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
//        category_1.setBackground(null);
//        category_2.setBackground(null);
//        category_3.setBackground(null);
//        category_4.setBackground(null);
//        category_5.setBackground(null);
        return super.onSupportNavigateUp();
    }
}
