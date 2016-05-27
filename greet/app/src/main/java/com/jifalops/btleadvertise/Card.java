package com.jifalops.btleadvertise;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by client on 2016. 5. 26..
 */
public class Card implements Parcelable {
    public int cardNumber;
    public ArrayList<String> keyword;
    public String image;
    public String name;
    public ArrayList<String> onOff;
    public String phoneNumber;
    public ArrayList<String> sns;
    public String status;

    public Card() {
        keyword = new ArrayList<>();
        onOff = new ArrayList<>();
        sns = new ArrayList<>();
    }

    protected Card(Parcel in) {
        cardNumber = in.readInt();
        keyword = in.createStringArrayList();
        image = in.readString();
        name = in.readString();
        onOff = in.createStringArrayList();
        phoneNumber = in.readString();
        sns = in.createStringArrayList();
        status = in.readString();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(cardNumber);
        dest.writeStringList(keyword);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeStringList(onOff);
        dest.writeString(phoneNumber);
        dest.writeStringList(sns);
        dest.writeString(status);
    }
}
