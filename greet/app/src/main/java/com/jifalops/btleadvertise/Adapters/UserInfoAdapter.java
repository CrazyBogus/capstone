package com.jifalops.btleadvertise.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jifalops.btleadvertise.Activity.See_Other;
import com.jifalops.btleadvertise.R;
import com.jifalops.btleadvertise.Functional.User_Info;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 12..
 */
public class UserInfoAdapter extends BaseAdapter implements View.OnClickListener {

    private boolean Flag_start_changer;
    // Activity에서 가져온 객체정보를 저장할 변수
    private User_Info mUser;
    private Context mContext;

    // ListView 내부 View들을 가르킬 변수들
    private ImageView imgUserIcon;
    private TextView tvUserName;
    private TextView tvUserPhoneNumber;
    private ImageButton IsAdded;


    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<User_Info> mUserData;

    public UserInfoAdapter(Context context) {
        super();
        mContext = context;
        mUserData = new ArrayList<User_Info>();
    }

    @Override
    /**
     * @return 아이템의 총 개수를 반환
     */
    public int getCount() {
        // TODO Auto-generated method stub
        return mUserData.size();
    }

    @Override
    /**
     * @return 선택된 아이템을 반환
     */
    public User_Info getItem(int position) {
        // TODO Auto-generated method stub
        // Log.d("포지션값 : ", Integer.toString(position));

        return mUserData.get(position);

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    /**
     * getView
     *
     * @param position - 현재 몇 번째로 아이템이 추가되고 있는지 정보를 갖고 있다.
     * @param convertView - 현재 사용되고 있는 어떤 레이아웃을 가지고 있는지 정보를 갖고 있다.
     * @param parent - 현재 뷰의 부모를 지칭하지만 특별히 사용되지는 않는다.
     * @return 리스트 아이템이 저장된 convertView
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;

        // 리스트 아이템이 새로 추가될 경우에는 v가 null값이다.
        // view는 어느 정도 생성된 뒤에는 재사용이 일어나기 때문에 효율을 위해서 해준다.
        if (v == null) {
            // inflater를 이용하여 사용할 레이아웃을 가져옵니다.
            v = ((LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.user_list, null);

            //명함 크기 변경하는 곳
            v.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT, 270));

            // 레이아웃이 메모리에 올라왔기 때문에 이를 이용하여 포함된 뷰들을 참조할 수 있습니다.
            imgUserIcon = (ImageView) v.findViewById(R.id.user_icon);
            tvUserName = (TextView) v.findViewById(R.id.user_name);
            // tvUserPhoneNumber = (TextView) v.findViewById(R.id.user_phone_number);
          //  IsAdded = (ImageButton) v.findViewById(R.id.isSelected_Star);


        }

        // 받아온 position 값을 이용하여 배열에서 아이템을 가져온다.
        mUser = getItem(position);

        // 데이터의 실존 여부를 판별합니다.
        if (mUser != null) {
            // 데이터가 있다면 갖고 있는 정보를 뷰에 알맞게 배치시킵니다.
            //@@사진 띄우려고 여기 바꿨음 0411 형일@
            if (mUser.GetImage() != null) {
                imgUserIcon.setImageBitmap(mUser.GetImage());
                // imgUserIcon.setImageDrawable(mUser.getUserIcon());
            }
            tvUserName.setText(mUser.getUserName());
            //tvUserPhoneNumber.setText(mUser.getUserPhoneNumber());


        }

        return v;
    }

    // 데이터를 추가하는 것을 위해서 만들어 준다.
    public void add(User_Info user) {

        mUserData.add(user);
        Log.d("지금 인덱스는 : ", Integer.toString(getCount()));
    }
    public void remove(User_Info user)
    {
        mUserData.remove(user);
    }

    public boolean contains(User_Info user)
    {
        if(mUserData.contains(user)) return true;
        else return false;
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        // Tag를 이용하여 Data를 가져옵니다.
        User_Info clickItem = (User_Info) v.getTag();

        Toast.makeText(mContext,"UserList Click", Toast.LENGTH_SHORT).show();




    }



}
