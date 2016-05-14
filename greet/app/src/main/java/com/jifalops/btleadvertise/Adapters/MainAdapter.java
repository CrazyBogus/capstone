package com.jifalops.btleadvertise.Adapters;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jifalops.btleadvertise.Fragment.FirstFragment;
import com.jifalops.btleadvertise.Fragment.FourthFragment;
import com.jifalops.btleadvertise.Fragment.SecondFragment;
import com.jifalops.btleadvertise.Fragment.ThirdFragment;

/**
 * Created by client on 2016. 5. 12..
 */
public class MainAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private String kakaoID;
    private Bitmap bm;

    private FirstFragment firstFragment;
    private SecondFragment secondFragment;

    public MainAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);

        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
    }

    public void setKakaoID(String kakaoID) {
        this.kakaoID = kakaoID;
    }

    public void setImageView(Bitmap bm) {
//        this.bm = bm;
//        SecondFragment.newInstance(1, bm);
        secondFragment.setImage(bm);
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
//                return FirstFragment.newInstance(0, kakaoID);
                return FirstFragment.newInstance(0, kakaoID);
            case 1: // Fragment # 0 - This will show FirstFragment different title
//                return SecondFragment.newInstance(1, bm);
                return secondFragment;
            case 2: // Fragment # 1 - This will show SecondFragment
                return ThirdFragment.newInstance(2, null);
            case 3: // Fragment # 1 - This will show SecondFragment
                return FourthFragment.newInstance(3, null);
            default:
                return null;
        }
    }
}
