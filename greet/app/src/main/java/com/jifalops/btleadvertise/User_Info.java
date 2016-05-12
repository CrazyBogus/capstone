package com.jifalops.btleadvertise;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.ViewDebug;
import android.widget.ImageView;

import com.jifalops.btleadvertise.Adapters.UserInfoAdapter;

import java.util.ArrayList;

/**
 * Created by client on 2016. 4. 4..
 * 현재의 목표를 이루기 위해서 리스트뷰에 가져와야 할 정보들은 사용자아이콘, 이름 그리고 전화번호가 있습니다.

 이를 저장할 하나의 클래스를 생성해주면 됩니다. 이 클래스는 정보를 저장(Set)할 생성자와 정보를 가져올 Getter를 만들어 주셔야 합니다.

 */
public class User_Info {




    private Drawable mUserIcon;
    private String mUserName;
    private String mUserFakeName;
    private String mUserPhoneNumber;
    private int use_bit = 0;
    private Bitmap UserIcon;
    private ImageView realUserIcon;
    private UserInfoAdapter A;
    User_Info(Drawable userIcon, String userName, String userPhoneNumber) {
        mUserIcon = userIcon;
        mUserName = userName;
        mUserPhoneNumber = userPhoneNumber;
    }

    User_Info(){

    }
    public void SetImage(Bitmap image) {

        this.UserIcon = image;
       // realUserIcon.setImageBitmap(UserIcon);
    }
    public Bitmap GetImage()
    {
        return UserIcon;
    }
    public void SetId (String id)
    {
        this.mUserName = id;
    }
    public String GetId()
    {

        return mUserName;
    }

    public void SetFakeId (String id)
    {
        this.mUserFakeName = id;
    }
    public String GetFakeId()
    {

        return mUserFakeName;
    }

    public void Setuse_bit(int bit)
    {
        this.use_bit = bit;
    }
    public int Getuse_bit()
    {
        return use_bit;
    }


    public Drawable getUserIcon(){
        return mUserIcon;
    }

    public String getUserName() {
        return mUserName;
    }

    public String  getUserPhoneNumber() {
        return mUserPhoneNumber;
    }



}



//
//
//String UserName;
//String  Password;
//int Action;
//
//
//    public User_Info(String name,String pass,int ac){
//        UserName=name;
//        Password=pass;
//        Action=ac;
//    }
//
//    //parcel part
//    public User_Info(Parcel in){
//        String[] data= new String[3];
//
//        in.readStringArray(data);
//        this.UserName= data[0];
//        this.Password= data[1];
//        this.Action= Integer.parseInt(data[2]);
//    }
//    @Override
//    public int describeContents() {
//// TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//// TODO Auto-generated method stub
//
//        dest.writeStringArray(new String[]{this.UserName,this.Password,String.valueOf(this.Action)});
//    }
//
//public static final Parcelable.Creator<User_Info> CREATOR= new Parcelable.Creator<User_Info>() {
//
//    @Override
//    public User_Info createFromParcel(Parcel source) {
//// TODO Auto-generated method stub
//        return new User_Info(source);  //using parcelable constructor
//    }
//
//    @Override
//    public User_Info[] newArray(int size) {
//// TODO Auto-generated method stub
//        return new User_Info[size];
//    }
//};