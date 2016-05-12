package com.jifalops.btleadvertise.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jifalops.btleadvertise.FirstFragment;
import com.jifalops.btleadvertise.FourthFragment;
import com.jifalops.btleadvertise.SecondFragment;
import com.jifalops.btleadvertise.ThirdFragment;

/**
 * Created by client on 2016. 5. 12..
 */
public class MainAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private String kakaoID;

    public MainAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setKakaoID(String kakaoID) {
        this.kakaoID = kakaoID;
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

                return FirstFragment.newInstance(0, kakaoID);

            case 1: // Fragment # 0 - This will show FirstFragment different title
                return SecondFragment.newInstance(1, null);
            case 2: // Fragment # 1 - This will show SecondFragment
                return ThirdFragment.newInstance(2, null);
            case 3: // Fragment # 1 - This will show SecondFragment
                return FourthFragment.newInstance(3, null);
            default:
                return null;
        }
    }
}
