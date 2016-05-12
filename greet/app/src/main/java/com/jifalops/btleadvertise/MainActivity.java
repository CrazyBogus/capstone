package com.jifalops.btleadvertise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jifalops.btleadvertise.Adapters.MainAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MainAdapter adapterViewPager;
    private ViewPager vpPager;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    private LinearLayout layout_actionbar;
    private ImageView add_new_card;
    private static String kakaoID;

    private static boolean start_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(start_flag) {
            startActivity(new Intent(this, Splash.class));
            start_flag = false;
            finish();
        }
        Bundle b = getIntent().getExtras();
        if(b != null)
            kakaoID = b.getString("kakaoID");

        setLayout();
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MainAdapter(getSupportFragmentManager());
        adapterViewPager.setKakaoID(kakaoID);

        vpPager.setAdapter(adapterViewPager);

        // Attach the page change listener inside the activity
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this,
                          position+"번째 페이지입니다", Toast.LENGTH_SHORT).show();
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
                if(position==0) {
                    layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected1);
                    add_new_card.setVisibility(View.INVISIBLE);
                }else if(position==1){
                    layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected2);
                    add_new_card.setVisibility(View.VISIBLE);
                }else if(position==2){
                    layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected3);
                    add_new_card.setVisibility(View.INVISIBLE);
                }else{
                    layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected4);
                }
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });

        add_new_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MainActivity.this, My_Profile.class);

                startActivity(newActivity);
            }
        });
    }






    /**
     * Layout
     */
    private void setLayout(){
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);

        layout_actionbar = (LinearLayout) findViewById(R.id.layout_actionbar);
        layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected1);
        add_new_card = (ImageView) findViewById(R.id.add_new_card);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                setCurrentInflateItem(0);
                break;
            case R.id.btn_two:
                setCurrentInflateItem(1);
                break;
            case R.id.btn_three:
                setCurrentInflateItem(2);
                break;
            case R.id.btn_four:
                setCurrentInflateItem(3);
                break;
        }
    }

    private void setCurrentInflateItem(int type){
        if(type==0){
            layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected1);
            add_new_card.setVisibility(View.INVISIBLE);
            vpPager.setCurrentItem(0);
        }else if(type==1){
            layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected2);
            add_new_card.setVisibility(View.VISIBLE);
            vpPager.setCurrentItem(1);
        }else if(type==2){
            layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected3);
            add_new_card.setVisibility(View.INVISIBLE);
            vpPager.setCurrentItem(2);
        }else{
            layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected4);
            vpPager.setCurrentItem(3);
        }
    }

    private View.OnClickListener mPagerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_one:
                    Toast.makeText(getApplicationContext(), "btn_0", Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    };



}