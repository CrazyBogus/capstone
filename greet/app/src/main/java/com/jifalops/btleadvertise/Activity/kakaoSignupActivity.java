package com.jifalops.btleadvertise.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.jifalops.btleadvertise.Activity.Login;
import com.jifalops.btleadvertise.Activity.MainActivity;
import com.jifalops.btleadvertise.Database.DbOpenHelper;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by client on 2016. 4. 24..
 */
public class kakaoSignupActivity extends Activity {
    private Context mContext;
    private DbOpenHelper mDbOpenHelper;
    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestMe();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
            } // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                Logger.d("UserProfile : " + userProfile);
                String kakaoId = String.valueOf(userProfile.getId());
                String kakaoNickname = String.valueOf(userProfile.getNickname());
                Log.d("카카오 로그인 아이디 : ", kakaoId + " " +kakaoNickname);

                redirectMainActivity(kakaoId, kakaoNickname); // 로그인 성공시 MainActivity로
            }
        });
    }

    private void redirectMainActivity(String kakaoID, String kakaoNickname) {
        serverConnect(kakaoID,kakaoNickname);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("kakaoID", kakaoID);
        intent.putExtra("kakaoNICKNAME", kakaoNickname);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);


        startActivity(intent);
        finish();
    }

    public void serverConnect(String id, String nickname)
    {


        String url = "http://52.69.46.152:8000/api/sign_up";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams param = new RequestParams();
        client.addHeader("Accept", "*/*");
        client.setMaxRetriesAndTimeout(3, 30000);
        client.setUserAgent("android-async-http-1.4.9");
        if(id==null)
        {  //카카오 아이디가 없으면 로그인화면으로 넘어감
            int flag = 1;
            // 화면전환하는 객체 선언
            Intent intent = new Intent().setClass(this, Login.class);
            // 데이터 넘김
            intent.putExtra("flag", flag);
            // 화면 전환 메소드
            startActivity(intent);
            Log.d("In SignUp : ", "Kakao Id가 없습니다");
        }

        try {
            param.put("id", id);
            param.put("nickname", nickname);
        Log.d("id is : ", id+" nickname is : "+nickname);

        } catch(Exception e) {e.printStackTrace();}

        client.post(mContext, url, param, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    String connect_result = response.getString("result");
                    Log.d("result : ", connect_result);
                } catch (JSONException e) {}

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("In SignUp : ", "Connection Failed");
            }

        });


    }

}
