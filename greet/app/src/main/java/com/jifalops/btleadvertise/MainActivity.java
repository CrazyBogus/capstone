package com.jifalops.btleadvertise;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentPagerAdapter adapterViewPager;
    private ViewPager vpPager;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    private LinearLayout layout_actionbar;
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

        setLayout();
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
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
                }else if(position==1){
                    layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected2);
                }else if(position==2){
                    layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected3);
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
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment

                    return FirstFragment.newInstance(0, null);


                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return SecondFragment.newInstance(1, null);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return ThirdFragment.newInstance(2, null);
                case 3: // Fragment # 1 - This will show SecondFragment
                    return FourthFragment.newInstance(3,null);
                default:
                    return null;
            }
        }



//        // Returns the page title for the top indicator
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return "Page " + position;
//        }





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
            vpPager.setCurrentItem(0);
        }else if(type==1){
            layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected2);
            vpPager.setCurrentItem(1);
        }else if(type==2){
            layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected3);
            vpPager.setCurrentItem(2);
        }else{
            layout_actionbar.setBackgroundResource(R.drawable.actionbar_selected4);
            vpPager.setCurrentItem(3);
        }
    }

    private View.OnClickListener mPagerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btn_one:
                    Toast.makeText(getApplicationContext(), "btn_0", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };



}