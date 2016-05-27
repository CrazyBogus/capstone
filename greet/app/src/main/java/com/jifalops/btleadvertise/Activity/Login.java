package com.jifalops.btleadvertise.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jifalops.btleadvertise.R;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

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
    private boolean falg_logout = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        img_bg = (ImageView) findViewById(R.id.img_bg);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);


        Intent intent = getIntent();
        if(intent.hasExtra("flag"))
        {
            falg_logout = false;
        }

        if(!falg_logout) {
            /**카카오톡 로그아웃 요청**/
            //한번 로그인이 성공하면 세션 정보가 남아있어서 로그인창이 뜨지 않고 바로 onSuccess()메서드를 호출합니다.
            //테스트 하시기 편하라고 매번 로그아웃 요청을 수행하도록 코드를 넣었습니다 ^^
            UserManagement.requestLogout(new LogoutResponseCallback() {
                @Override
                public void onCompleteLogout() {
                    Log.d("로그아웃 성공!","!");
                }
            });
        }
        else
        {
            Log.d("로그아웃 안해~ 어 에러","123");
        }


    }
        @Override
        protected void onDestroy() {
            super.onDestroy();

       }
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
}

