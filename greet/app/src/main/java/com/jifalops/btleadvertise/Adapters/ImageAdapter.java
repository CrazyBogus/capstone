package com.jifalops.btleadvertise.Adapters;

import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jifalops.btleadvertise.R;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 12..
 */
public class ImageAdapter extends PagerAdapter {

    private ArrayList<Bitmap> mData;
    private LayoutInflater inflater;

    public ImageAdapter(LayoutInflater inflater, ArrayList<Bitmap> data) {
        this.mData = data;
        this.inflater = inflater;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.d("테스트", "position : " + position + " size : " + mData.size());

        View view = null;

        view = inflater.inflate(R.layout.viewpager_myprofile_pics, null);

        ImageView img = (ImageView) view.findViewById(R.id.image);
        img.setImageBitmap(mData.get(position));

        container.addView(view);

        return view;
    }



    @Override
    public int getCount() {
        Log.d("이미지 카운트", "size : " + mData.size());
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}
