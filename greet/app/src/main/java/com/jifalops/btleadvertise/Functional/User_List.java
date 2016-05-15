package com.jifalops.btleadvertise.Functional;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by client on 2016. 4. 7..
 */
public class User_List {

    boolean flag = true;
    private ArrayList UserList = null;
    private int user_num;


    public User_List()
    {
        UserList = new ArrayList();
        user_num = 0;

    }

    public void add_user(String id)
    {

        if(!UserList.contains(id)) {
            UserList.add(id);
            this.user_num++;
            flag = false;
            Log.d("추가 된 놈 : " , id);
        }


        Log.d("현재 유저 수 : ", String.valueOf(user_num));
//        client_num = Integer.getInteger(String.valueOf(client_num));
    }

    public void  remove_user(String id)
    {
        Log.d("remove에 들어왔다!!!","");
        UserList.remove(id);
    }


    public int check_exist(String id)
    {

        String temp = null;

            if (user_num > 0 && UserList.contains(id) && id!=null)
            {

                Log.d("유저 리스트에 존재한다", id);

               // flag = true;

                return 1;
            }
            else if(id != null && !(UserList.contains(id)))
            {
                Log.d("go adduser유저리스트에 추가된다 :", id);
                add_user(id);
                return 2;
            }

            else
            {
                Log.d("유저 이름으로 null이 오는 경우",id);
                return 3;
            }



    }

    public boolean check_usebit(String id)
    {
        if (user_num > 0 && UserList.contains(id) && id!=null)
        {

           // Log.d("usebit_유저 리스트에 존재한다", id);

            return true;
        }
        else
        {
         //   Log.d("userbit_ else문","");
            return false;
        }
    }

    public int getUser_num()
    {
        return user_num;
    }
}
