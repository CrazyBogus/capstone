package com.jifalops.btleadvertise.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.jifalops.btleadvertise.Card;
import com.jifalops.btleadvertise.Database.DbOpenHelper;
import com.jifalops.btleadvertise.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

//        adapter.addItem(bm);
        Log.d("SecondFragment : ", "setImage add 완료");
//        adapter.notifyDataSetChanged();
    }

    public void setItem(Card card) {
        adapter.addItem(card);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Adapter 생성
        adapter = new MyCardAdapter() ;

        SharedPreferences pref = getActivity().getSharedPreferences("greet", getActivity().MODE_PRIVATE);

        Set<String> cardList = pref.getStringSet("cardList", new HashSet<String>());

        for (String temp : cardList.toArray(new String[cardList.size()])) {
            Log.d("click", temp);
            try {
                JSONObject jsonObject = new JSONObject(temp);

                Card card = new Card();
                card.name = jsonObject.getString("name");
                card.image = jsonObject.getString("image");
                card.cardNumber = jsonObject.getInt("cardNumber");
                card.phoneNumber = jsonObject.getString("phoneNumber");

                if (jsonObject.has("keyword1")) {
                    card.keyword.add(jsonObject.getString("keyword1"));
                }

                if (jsonObject.has("keyword2")) {
                    card.keyword.add(jsonObject.getString("keyword2"));
                }

                if (jsonObject.has("keyword3")) {
                    card.keyword.add(jsonObject.getString("keyword3"));
                }

                if (jsonObject.has("keyword4")) {
                    card.keyword.add(jsonObject.getString("keyword4"));
                }

                if (jsonObject.has("keyword5")) {
                    card.keyword.add(jsonObject.getString("keyword5"));
                }

                adapter.addItem(card);

            } catch (JSONException e) {

            }
        }

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
//                Intent newActivity = new Intent(getActivity(), My_Profile.class);
//
//                startActivity(newActivity);
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
                               // mDbOpenHelper = new DbOpenHelper(getActivity());
                               // mDbOpenHelper.open();
                               // mDbOpenHelper.my_profile_remove(position);
                                Log.d("In LongClickListener : ", "Position 값 : " + Integer.toString(position));

                                removeItem(position);
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

    private void removeItem(int position) {
        adapter.removeItem(position);

        Card card = (Card) adapter.getItem(position);

        SharedPreferences pref = getActivity().getSharedPreferences("greet", getActivity().MODE_PRIVATE);
        Set<String> cardList = pref.getStringSet("cardList", new HashSet<String>());

        for (String temp : cardList.toArray(new String[cardList.size()])) {
            Log.d("click", card.cardNumber + "");
            Log.d("click", temp);
            try {
                JSONObject jsonObject = new JSONObject(temp);

                if (card.cardNumber == jsonObject.getInt("cardNumber")) {
                    cardList.remove(temp);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet("cardList", cardList);
        editor.commit();

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
