package com.jifalops.btleadvertise.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.jifalops.btleadvertise.Activity.Add_Profile;
import com.jifalops.btleadvertise.Activity.MainActivity;
import com.jifalops.btleadvertise.Activity.My_Profile;
import com.jifalops.btleadvertise.Adapters.MyCardAdapter;
import com.jifalops.btleadvertise.Database.DbOpenHelper;
import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 5. 8..
 */
public class SecondFragment extends Fragment {
    private ListView listview ;
    private MyCardAdapter adapter;

    private DbOpenHelper mDbOpenHelper;
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
        Log.d("SecondFragment : ", "setImage add 완료");
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Adapter 생성
        adapter = new MyCardAdapter() ;

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_second, container, false);
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.my_card_list);
        listview.setAdapter(adapter);


        // 위에서 생성한 listview에 클릭 이벤트 핸들러 정의.
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
                Intent newActivity = new Intent(getActivity(), My_Profile.class);

                startActivity(newActivity);
            }
        }) ;
        listview.setLongClickable(true);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {
                Log.d("CLICK", "OnLongClickListener");
                new AlertDialog.Builder(getActivity())
                        .setTitle("명함 삭제")
                        .setMessage("정말 삭제하시겠습니까")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //여기서 명함 삭제 구현 예정 몇번째 명함인지를 알아야 지울 수 있음..!!
                                mDbOpenHelper = new DbOpenHelper(getActivity());
                                mDbOpenHelper.open();
                               // mDbOpenHelper.my_profile_remove(position);
                                Log.d("In LongClickListener : ", "Position 값 : " + Integer.toString(position));


                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        })
                        .show();
                adapter.notifyDataSetChanged();
                return true;
            }
        });

//

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
