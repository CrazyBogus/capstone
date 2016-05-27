package com.jifalops.btleadvertise.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jifalops.btleadvertise.Activity.Login;
import com.jifalops.btleadvertise.Activity.See_Other;
import com.jifalops.btleadvertise.Database.DbOpenHelper;
import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 5. 8..
 */
public class FourthFragment extends Fragment {
    // Store instance variables
    private ImageView Iv_background;
    private ImageView Iv_logout;
    // newInstance constructor for creating fragment with arguments
    public static FourthFragment newInstance(int page, String title) {
        FourthFragment fragmentFour = new FourthFragment();
        Bundle args = new Bundle();
        fragmentFour.setArguments(args);
        return fragmentFour;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        Iv_logout = (ImageView) view.findViewById(R.id.Iv_logout);
        Iv_background = (ImageView) view.findViewById(R.id.Iv_background);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.option_background, options);

        BitmapFactory.Options option1 = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.option_logout, options);
        Iv_background.setImageBitmap(bitmap);
        Iv_logout.setImageBitmap(bitmap2);

        Iv_logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getActivity())
                        .setTitle("로그아웃")
                        .setMessage("정말 로그아웃 하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int flag = 1;
                                // 화면전환하는 객체 선언
                                Intent intent = new Intent().setClass(getActivity(), Login.class);
                                // 데이터 넘김
                                intent.putExtra("flag", flag);
                                // 화면 전환 메소드
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        })
                        .show();



            }
        });



        return view;
    }
}
