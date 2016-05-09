package com.jifalops.btleadvertise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by client on 2016. 5. 9..
 */
public class PageFragment_ForProfileImg extends Fragment {

    private int mPageNumber;
    private ImageView mImageView;

    public static PageFragment_ForProfileImg create(int pageNumber) {
        PageFragment_ForProfileImg fragment = new PageFragment_ForProfileImg();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("page");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.viewpager_myprofile_pics, container, false);
//        ((TextView) rootView.findViewById(R.id.number)).setText(mPageNumber + "");
        switch(mPageNumber){
            case 0:
                ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.test1);
                break;
            case 1:
                ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.my_profile_image_default);
                break;
            case 2:
                ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.test1);
                break;
            case 3:
                ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.my_profile_image_default);
                break;
            case 4:
            ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.test1);
                break;
        }

        return rootView;
    }
}


