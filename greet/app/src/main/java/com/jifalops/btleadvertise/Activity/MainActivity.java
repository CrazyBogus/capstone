package com.jifalops.btleadvertise.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jifalops.btleadvertise.Adapters.MainAdapter;
import com.jifalops.btleadvertise.Card;
import com.jifalops.btleadvertise.Database.DbOpenHelper;
import com.jifalops.btleadvertise.Functional.Splash;
import com.jifalops.btleadvertise.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DbOpenHelper mDbOpenHelper;

    private MainAdapter adapterViewPager;
    private ViewPager vpPager;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;
    private ImageView add_new_card;
    private ImageView add_interests;
    private ImageView logo;
    private static String kakaoID;
    private static String kakaoNICKNAME;
    private static boolean start_flag = true;
    private Bitmap bm;


    ArrayList<String> keyword = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (start_flag) {
            startActivity(new Intent(this, Splash.class));
            start_flag = false;
            finish();
        }
        setLayout();



        Intent intent = getIntent();

        if(intent.hasExtra("kakaoID")) {
            kakaoID = intent.getStringExtra("kakaoID");
            kakaoNICKNAME = intent.getStringExtra("kakaoNICKNAME");
            Log.d("MainActivity: ", "카카오 정보 가져오기 성공!");
        }
        else
        {
            Log.d("MainActivity : ","intent가 kakaoID를 가지고 있지 않습니다.");
        }

        // Attach the page change listener inside the activity
        adapterViewPager = new MainAdapter(getSupportFragmentManager(), kakaoID, kakaoNICKNAME);
        adapterViewPager.setKakaoID(kakaoID);

        vpPager.setAdapter(adapterViewPager);

        if(getIntent().hasExtra("bm")) {
            Log.d("MainActivity : ", "has extra bm");
            vpPager.setCurrentItem(1);
        }
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {}

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
                if (position == 0) {
                    btn_one.setBackgroundResource(R.drawable.actionbar_selected_peoplelist);
                    btn_two.setBackgroundResource(R.drawable.actionbar_unselected_editcard);
                    btn_three.setBackgroundResource(R.drawable.actionbar_unselected_interest);
                    btn_four.setBackgroundResource(R.drawable.actionbar_unselected_option);
                    add_new_card.setVisibility(View.INVISIBLE);
                    add_interests.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    btn_one.setBackgroundResource(R.drawable.actionbar_unselected_peoplelist);
                    btn_two.setBackgroundResource(R.drawable.actionbar_selected_editcard);
                    btn_three.setBackgroundResource(R.drawable.actionbar_unselected_interest);
                    btn_four.setBackgroundResource(R.drawable.actionbar_unselected_option);
                    add_new_card.setVisibility(View.VISIBLE);
                    add_interests.setVisibility(View.INVISIBLE);
                } else if (position == 2) {
                    btn_one.setBackgroundResource(R.drawable.actionbar_unselected_peoplelist);
                    btn_two.setBackgroundResource(R.drawable.actionbar_unselected_editcard);
                    btn_three.setBackgroundResource(R.drawable.actionbar_selected_interest);
                    btn_four.setBackgroundResource(R.drawable.actionbar_unselected_option);
                    add_new_card.setVisibility(View.INVISIBLE);
                    add_interests.setVisibility(View.INVISIBLE);

                } else {
                    btn_one.setBackgroundResource(R.drawable.actionbar_unselected_peoplelist);
                    btn_two.setBackgroundResource(R.drawable.actionbar_unselected_editcard);
                    btn_three.setBackgroundResource(R.drawable.actionbar_unselected_interest);
                    btn_four.setBackgroundResource(R.drawable.actionbar_selected_option);
                    add_new_card.setVisibility(View.INVISIBLE);
                    add_interests.setVisibility(View.INVISIBLE);
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
                Intent newActivity = new Intent(MainActivity.this, Add_Profile.class);
                newActivity.putExtra("kakaoID", kakaoID);
                newActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newActivity);

            }
        });

        add_interests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MainActivity.this, Pick_Interests.class);
                startActivity(newActivity);

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();


        //      // DB Create and Open
//        mDbOpenHelper = new DbOpenHelper(this);
//
//        mDbOpenHelper.open();
//        if(kakaoID != null && kakaoNICKNAME != null)
//        mDbOpenHelper.my_info_insert(kakaoID,kakaoNICKNAME);
//
//        else
//            Log.d("MainActivity : ", "kakaoInfos are null");

    }
    /**
     * Layout
     */
    private void setLayout() {
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_four = (Button) findViewById(R.id.btn_four);
        btn_one.setBackgroundResource(R.drawable.actionbar_selected_peoplelist);
        btn_two.setBackgroundResource(R.drawable.actionbar_unselected_editcard);
        btn_three.setBackgroundResource(R.drawable.actionbar_unselected_interest);
        btn_four.setBackgroundResource(R.drawable.actionbar_unselected_option);
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);

        add_new_card = (ImageView) findViewById(R.id.add_new_card);
        add_interests = (ImageView) findViewById(R.id.add_interests);
        logo = (ImageView) findViewById(R.id.logo);
        add_new_card.setImageResource(R.drawable.my_profile_add_new_card);
        add_interests.setImageResource(R.drawable.my_profile_keyword_selection);
        logo.setImageResource(R.drawable.text_logo);
        vpPager = (ViewPager) findViewById(R.id.vpPager);
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

    private void setCurrentInflateItem(int type) {
        if (type == 0) {
            vpPager.setCurrentItem(0);
            add_new_card.setVisibility(View.INVISIBLE);
            add_interests.setVisibility(View.VISIBLE);
        } else if (type == 1) {
            vpPager.setCurrentItem(1);
            btn_two.setBackgroundResource(R.drawable.actionbar_selected_editcard);
            add_new_card.setVisibility(View.VISIBLE);
            add_interests.setVisibility(View.INVISIBLE);
        } else if (type == 2) {
            vpPager.setCurrentItem(2);
            btn_three.setBackgroundResource(R.drawable.actionbar_selected_interest);
            add_new_card.setVisibility(View.INVISIBLE);
            add_interests.setVisibility(View.INVISIBLE);
        } else {
            vpPager.setCurrentItem(3);
            btn_four.setBackgroundResource(R.drawable.actionbar_selected_option);
            add_new_card.setVisibility(View.INVISIBLE);
            add_interests.setVisibility(View.INVISIBLE);
        }
    }

    //인텐트 받아왔을 때.. 근데 이거 있으면 onStart 호출 불가
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

//        if (intent.hasExtra("bm")) {
//            Log.d("MainActivity : ", "bm is not null");
//
//            bm = intent.getParcelableExtra("bm");
//            adapterViewPager.setImageView(bm);
//
//        } else {
//            Log.d("MainActivity : ", "bm is null");
//        }

        if (intent.hasExtra("card")) {
            adapterViewPager.setCardItem((Card) intent.getParcelableExtra("card"));
        }

        if(intent.hasExtra("keyword"))
        {
            keyword = intent.getStringArrayListExtra("keyword");
            Log.d("In mainActivity : " , keyword.get(0));
            Log.d("In mainActivity : " , keyword.get(1));
            Log.d("In mainActivity : " , keyword.get(2));
            Log.d("In mainActivity : " , keyword.get(3));
            Log.d("In mainActivity : " , keyword.get(4));

//            FirstFragment fragment = new FirstFragment();
//            Bundle bundle = new Bundle();
//            bundle.putStringArrayList("keyword", keyword);
//            fragment.setArguments(bundle);

            adapterViewPager.getFirstFragment().getkeyword(keyword);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        logo.setImageDrawable(null);
        add_new_card.setImageDrawable(null);
        add_interests.setImageDrawable(null);
    }

}