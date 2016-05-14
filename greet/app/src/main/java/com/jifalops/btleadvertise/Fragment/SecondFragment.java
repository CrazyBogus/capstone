package com.jifalops.btleadvertise.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jifalops.btleadvertise.Activity.MainActivity;
import com.jifalops.btleadvertise.Adapters.MyCardAdapter;
import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 5. 8..
 */
public class SecondFragment extends Fragment {
    private ListView listview ;
    private MyCardAdapter adapter;
    private Bitmap test;
    // Store instance variables

    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance(int page, Bitmap bm) {
        SecondFragment secondFragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);

        if (bm != null) {
            args.putParcelable("bm", bm);
        }

        secondFragment.setArguments(args);
        return secondFragment;
    }

    public void setImage(Bitmap bm) {
        adapter.addItem(bm);
//        adapter.notifyDataSetChanged();
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        test = getArguments().getParcelable("bm");

        // Adapter 생성
        adapter = new MyCardAdapter() ;

//        adapter.addItem(test);
//        // 첫 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.test1),
//                "Box", "Account Box Black 36dp") ;
//        // 두 번째 아이템 추가.
//        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.my_profile_preview),
//                "Circle", "Account Circle Black 36dp") ;


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.my_card_list);
        listview.setAdapter(adapter);

        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
                Intent newActivity = new Intent(getActivity(), MainActivity.class);

                startActivity(newActivity);
            }
        }) ;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

//        if (getArguments().containsKey("bm")) {
//            Log.d("bm", "sencond has bm");
//        } else {
//            Log.d("bm", "sencond not has bm");
//        }
//
//        if (adapter == null) {
//            Log.d("bm", "adapter is null");
//        }
//
//        if (getArguments().containsKey("bm") && adapter != null) {
//            Log.d("bm", "before addItem");
//            adapter.addItem(((Bitmap)getArguments().getParcelable("bm")));
//            adapter.notifyDataSetChanged();
//            Log.d("bm", "after addItem");
//        }
    }
}
