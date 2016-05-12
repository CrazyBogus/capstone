package com.jifalops.btleadvertise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jifalops.btleadvertise.Adapters.ImageAdapter;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 9..
 */
public class My_Profile_Image extends FragmentActivity{
    private ViewPager mViewPager;
    private ImageAdapter mPagerAdapter;
    private Bitmap bm;
    private ImageView mImageView;

    private ImageView test_img;

    private ArrayList<Bitmap> mData = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagefragment_forprofile);

        //My_Profile로 부터 비트맵을 가져온다.(카메라 사진)
        Intent intent = getIntent();
        bm = intent.getParcelableExtra("bm");

        mData = intent.getParcelableArrayListExtra("BitmapList");

        Log.d("사진", bm.toString());

        test_img = (ImageView) findViewById(R.id.text_img);
        test_img.setImageBitmap(bm);

//        mData = new ArrayList<Bitmap>();


        mViewPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ImageAdapter(getLayoutInflater(), mData);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(0);
//        mImageView = (ImageView) findViewById(R.id.image);

//        mData.add(bm);
        mPagerAdapter.notifyDataSetChanged();

        //Log.d("image로 잘 넘어옴 : ", bm.toString());
//
//        Bundle objectId = new Bundle();
//        objectId.putParcelable("img", bm);
//      //  Log.d("objectID : ", objectId.toString());
//        PageFragment_ForProfileImg passId = new PageFragment_ForProfileImg();
//        passId.setArguments(objectId);

        // Attach the page change listener inside the activity
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            // This method will be invoked when a new page becomes selected.
//            @Override
//            public void onPageSelected(int position) {
//                Toast.makeText(My_Profile_Image.this,
//                        position + "번째 사진입니다", Toast.LENGTH_SHORT).show();
//            }
//
//            // This method will be invoked when the current page is scrolled
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                // Code goes here
//                if (position == 0) {
//
//                  //  mImageView.setImageBitmap(bm);
//
//                } else if (position == 1) {
//                    //  mImageView.setImageBitmap(bm);
//
//                } else if (position == 2) {
//                    //  mImageView.setImageBitmap(bm);
//                } else {
//                    //  mImageView.setImageBitmap(bm);
//                }
//            }
//
//            // Called when the scroll state changes:
//            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                // Code goes here
//            }
//        });

//        PageFragment_ForProfileImg frament = new PageFragment_ForProfileImg();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("img", bm);
////        bundle.putInt("test",256);
//        frament.setArguments(bundle);
    }

//    @Override
//    public void onClick(View view) {
//
//        Bundle objectId = new Bundle();
//        objectId.putParcelable("img", bm);
//        Log.d("objectID : ", objectId.toString());
//        PageFragment_ForProfileImg passId = new PageFragment_ForProfileImg();
//        passId.setArguments(objectId);
//        finish();
//    }


    @Override
    protected void onDestroy() {
        Log.d("OOMTEST", "onDestroy");

//        mImageView.setImageBitmap(null);
        super.onDestroy();
    }

}
