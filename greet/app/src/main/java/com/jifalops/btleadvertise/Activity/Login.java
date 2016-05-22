package com.jifalops.btleadvertise.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.jifalops.btleadvertise.R;
import com.kakao.auth.*;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.security.MessageDigest;

/**
 * Created by client on 2016. 4. 10..
 */
public class Login extends Activity {
    SessionCallback callback;

    ImageButton btn_login;
    EditText editText_login_name;
    RelativeLayout mainLayout;
    ImageView img_bg;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        img_bg = (ImageView) findViewById(R.id.img_bg);
        img_bg.setImageResource(R.drawable.login_bg);
        /**카카오톡 로그아웃 요청**/
        //한번 로그인이 성공하면 세션 정보가 남아있어서 로그인창이 뜨지 않고 바로 onSuccess()메서드를 호출합니다.
        //테스트 하시기 편하라고 매번 로그아웃 요청을 수행하도록 코드를 넣었습니다 ^^
        UserManagement.requestLogout(new LogoutResponseCallback() {
            @Override
            public void onCompleteLogout() {

                //로그아웃 성공 후 하고싶은 내용 코딩 ~
            }
        });


//        callback = new SessionCallback();
//        Session.getCurrentSession().addCallback(callback);


        btn_login = (ImageButton) findViewById(R.id.login_imageButton);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(Login.this, MainActivity.class);

                startActivity(newActivity);
            }
        });


        editText_login_name = (EditText) findViewById(R.id.login_name);
        editText_login_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText_login_name.setHint("");
            }
        });


        mainLayout = (RelativeLayout) findViewById(R.id.login);

        mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText_login_name.getWindowToken(), 0);
                editText_login_name.setHint("이를을 입력해주세요");
                editText_login_name.getHint();
            }
        });

    }
        @Override
        protected void onDestroy() {
            super.onDestroy();
         // img_bg.setImageDrawable(null);
       }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
//            return;
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Session.getCurrentSession().removeCallback(callback);
//    }
//
//    private class SessionCallback implements ISessionCallback {
//
//        @Override
//        public void onSessionOpened() {
//            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
//        }
//
//        @Override
//        public void onSessionOpenFailed(KakaoException exception) {
//            if(exception != null) {
//                Logger.e(exception);
//            }
//            setContentView(R.layout.login); // 세션 연결이 실패했을때
//        }                                            // 로그인화면을 다시 불러옴
//    }
//
//    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
//        final Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(intent);
//        finish();
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //간편로그인시 호출 ,없으면 간편로그인시 로그인 성공화면으로 넘어가지 않음
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            redirectSignupActivity();  // 세션 연결성공 시 redirectSignupActivity() 호출
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            setContentView(R.layout.login); // 세션 연결이 실패했을때
        }                                            // 로그인화면을 다시 불러옴

        }

    protected void redirectSignupActivity() {       //세션 연결 성공 시 SignupActivity로 넘김
        final Intent intent = new Intent(this, kakaoSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
    private void getAppKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }
}

