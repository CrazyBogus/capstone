package com.jifalops.btleadvertise.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jifalops.btleadvertise.R;

import java.io.ByteArrayOutputStream;

import cz.msebera.android.httpclient.extras.Base64;

/**
 * Created by client on 2016. 5. 9..
 */
public class PageFragment_ForProfileImg extends Fragment {

    private int mPageNumber;
    private ImageView mImageView;
    private ImageView my_profile_image;
    private ImageView main;
    private Bitmap photo;
    private int test;
    private Bundle mTagObjectId;

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

//        photo = getArguments().getParcelable("img");

      //  Log.d("test : ", BitMapToString(photo));
//        Bundle extra = getArguments();
//      photo = extra.getParcelable("img");
//
//
//        Log.d("photo11 : ", photo.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.viewpager_myprofile_pics, container, false);
//        ((TextView) rootView.findViewById(R.id.number)).setText(mPageNumber + "");
        Bundle mTagObjectId = this.getArguments();
        main = (ImageView) getActivity().findViewById(R.id.main);
        main.setImageResource(R.drawable.main_unselected);
        Log.d("여기까지 옵니다","asd");

        if (getActivity().getIntent().hasExtra("photo")) {
//            Log.d("포토 이미지", getActivity().getIntent().getParcelableExtra("photo"))
        }

//        //Log.d("test : ", mTagObjectId.toString());
//        if (mTagObjectId != null) {
//            photo = mTagObjectId.getParcelable("img");
//
//        }


//
//  Log.d("objectid : ", mTagObjectId.toString());
//        switch(mPageNumber){
//            case 0:
////                Log.d("objectid : ", mTagObjectId.toString());
//
//               // ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.test1);
//                break;
//            case 1:
////                ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.my_profile_image_default);
//                break;
//            case 2:
////                ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.test1);
//                break;
//            case 3:
////                ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.my_profile_image_default);
//                break;
//            case 4:
////            ((ImageView) rootView.findViewById(R.id.image)).setBackgroundResource(R.drawable.test1);
//                break;
//        }

        return rootView;
    }
    /**
     * @param bitmap
     * @return converting bitmap and return a string
     */
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}


