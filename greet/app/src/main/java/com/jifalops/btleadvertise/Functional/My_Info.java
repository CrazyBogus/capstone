package com.jifalops.btleadvertise.Functional;

/**
 * Created by client on 2016. 4. 7..
 */
public class My_Info {

    public String my_id;
    public String my_nickname;
    public String my_phoneNum;
    public String my_status_message;
    public String my_other_sns;
    public String my_interests;

    private void setMy_id(String id) {
        this.my_id = id;
    }

    public String getMy_id(){
        return my_id;
    }
    private void setMy_nickname(String nickname) {
        this.my_nickname = nickname;
    }

    public String getMy_nickname(){
        return my_nickname;
    }
    private void setMy_phoneNum(String phoneNum) {
        this.my_phoneNum = phoneNum;
    }

    public String getMy_phoneNum(){
        return my_phoneNum;
    }
    private void setMy_status_message(String status_message) {
        this.my_status_message = status_message;
    }

    public String getMy_status_message(){
        return my_status_message;
    }
    private void setMy_other_sns(String other_sns) {
        this.my_other_sns = other_sns;
    }

    public String getMy_other_sns(){
        return my_other_sns;
    }
    private void setMy_interests(String my_interests) {
        this.my_interests = my_interests;
    }

    public String getMy_interests(){
        return my_interests;
    }

}
