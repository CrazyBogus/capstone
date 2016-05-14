package com.jifalops.btleadvertise.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 5. 8..
 */
public class FourthFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    ImageButton btn_login;
    TextView tvLabel4;

    // newInstance constructor for creating fragment with arguments
    public static FourthFragment newInstance(int page, String title) {
        FourthFragment fragmentFour = new FourthFragment();
        Bundle args = new Bundle();


        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFour.setArguments(args);
        return fragmentFour;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");




    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        tvLabel4 = (TextView) view.findViewById(R.id.tvLabel4);



        return view;
    }
}
