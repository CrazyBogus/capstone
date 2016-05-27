package com.jifalops.btleadvertise.Activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jifalops.btleadvertise.Card;
import com.jifalops.btleadvertise.Database.DbOpenHelper;
import com.jifalops.btleadvertise.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.mime.HttpMultipartMode;
import cz.msebera.android.httpclient.extras.Base64;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;

/**
 * Created by client on 2016. 5. 14..
 */
public class Add_Profile extends ActionBarActivity implements View.OnClickListener {
    private File f;
    private File e;
    private File copy_file;
    private File coffee_file;
    private File original_file;
    private ImageView my_profile_image;
    private ImageView btn_edit_profile_image;
    private ImageView btn_keyword;
    private ImageView img_video;
    private ImageView onoff_2;
    private ImageView onoff_3;
    private ImageView onoff_4;
    private ImageView onoff_5;
    private TextView keyword_1;
    private TextView keyword_2;
    private TextView keyword_3;
    private TextView keyword_4;
    private TextView keyword_5;
    private Uri mTempImageUri;
    private Uri getImagePath;
    private String filePath;
    private static final int PICK_FROM_CAMERA = 100;
    private static final int PICK_FROM_ALBUM = 200;
    private static final int CROP_FROM_CAMERA = 300;
    private Bitmap photo;
    private EditText Tv_name;
    private EditText Tv_phonenumber;
    private EditText Tv_selfintro;
    private EditText Tv_sns1;
    private EditText Tv_sns2;
    private EditText Tv_sns3;
    private ArrayList<String> keyword;
    private ArrayList<Bitmap> mData;
    private ScrollView Sv;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private Context mContext;
    private DbOpenHelper mDbOpenHelper;
    private static String kakaoID;
    private byte[] temp;
    private boolean onoff1=true;
    private boolean onoff2=true;
    private boolean onoff3=true;
    private boolean onoff4=true;
    private boolean onoff5=true;
    private boolean flag_photo = true;
    private String full_path;
    private String second_path;
    private ArrayList<String> onoff = new ArrayList<String>();
    private int card_number = 0;
    private String ttemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile);
        init();

        //Editview 외에 다른 곳을 터치하면 키보드가 내려가도록 구현.
        setupUI(Sv);

//        onoff.set(0,"1");
        // ActionBar의 배경색 변경
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF01afff));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        my_profile_image.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newActivity = new Intent(Add_Profile.this, My_Profile_Image.class);
                if(photo != null) {
                    Log.d("photo : ", photo.toString());
                    newActivity.putExtra("bm", photo);
                    newActivity.putExtra("BitmapList", mData);
                    newActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    startActivity(newActivity);
                }
            }
        });
        btn_keyword.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(Add_Profile.this, Add_Keyword.class);
                startActivity(newActivity);
                }

        });

        Tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Tv_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Tv_selfintro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Tv_sns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
            }
        });
        Tv_sns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
            }
        });
        Tv_sns3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
            }
        });
        img_video.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Add_Profile.this)
                        .setTitle("서비스 준비 중")
                        .setMessage("서비스 준비 중입니다")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        });
        //키워드 받기
        if(getIntent().hasExtra("keyword")) {
            keyword = getIntent().getStringArrayListExtra("keyword");
            keyword_1.setText(keyword.get(0));
            keyword_2.setText(keyword.get(1));
            keyword_3.setText(keyword.get(2));
            keyword_4.setText(keyword.get(3));
            keyword_5.setText(keyword.get(4));
        }
        //카카오 아이디받기
        if(getIntent().hasExtra("kakaoID")) {
            kakaoID = getIntent().getStringExtra("kakaoID");
            Log.d("MainActivity: ", "카카오 정보 가져오기 성공!");
        }

        getImagePath = createSaveCropFile();

        SharedPreferences pref = getSharedPreferences("greet", MODE_PRIVATE);
        card_number = pref.getInt("card_number", 0);
    }
    //레이아웃 설정
    private void init() {
        my_profile_image = (ImageView) findViewById(R.id.my_profile_image);
        btn_keyword = (ImageView) findViewById(R.id.setting_interests);
        btn_edit_profile_image = (ImageView) findViewById(R.id.edit_profile_image);
        btn_edit_profile_image.setOnClickListener(this);
        onoff_2 = (ImageView) findViewById(R.id.onoff_phonenumber);
        onoff_3 = (ImageView) findViewById(R.id.onoff_status);
        onoff_4 = (ImageView) findViewById(R.id.onoff_sns);
        onoff_5 = (ImageView) findViewById(R.id.onoff_video);
        onoff_2.setOnClickListener(this);
        onoff_3.setOnClickListener(this);
        onoff_4.setOnClickListener(this);
        onoff_5.setOnClickListener(this);
        img_video = (ImageView) findViewById(R.id.image_video);
        mData = new ArrayList<Bitmap>();
        Tv_name = (EditText) findViewById(R.id.textview_name);
        Tv_phonenumber = (EditText) findViewById(R.id.textview_phonenumber);
        Tv_selfintro = (EditText) findViewById(R.id.textview_selfintro);
        Tv_sns1 = (EditText) findViewById(R.id.textview_sns1);
        Tv_sns2 = (EditText) findViewById(R.id.textview_sns2);
        Tv_sns3 = (EditText) findViewById(R.id.textview_sns3);

        Sv = (ScrollView) findViewById(R.id.add_profile);

        keyword_1 = (TextView) findViewById(R.id.keyword1);
        keyword_2 = (TextView) findViewById(R.id.keyword2);
        keyword_3 = (TextView) findViewById(R.id.keyword3);
        keyword_4 = (TextView) findViewById(R.id.keyword4);
        keyword_5 = (TextView) findViewById(R.id.keyword5);



        keyword_1.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_2.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_3.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_4.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        keyword_5.setBackgroundResource(R.drawable.add_keyword_btn_keyword);
        btn_keyword.setImageResource(R.drawable.my_profile_keyword_selected);
        my_profile_image.setImageResource(R.drawable.my_profile_image_default);
        btn_edit_profile_image.setImageResource(R.drawable.my_profile_edit_image);
        img_video.setImageResource(R.drawable.my_profile_video_default);
        onoff_2.setImageResource(R.drawable.my_profile_toggle_on);
        onoff_3.setImageResource(R.drawable.my_profile_toggle_on);
        onoff_4.setImageResource(R.drawable.my_profile_toggle_on);
        onoff_5.setImageResource(R.drawable.my_profile_toggle_off);
       if(onoff.size()==0) {
           onoff.add(0,"1");
           onoff.add(1,"1");
           onoff.add(2,"1");
           onoff.add(3,"1");
           onoff.add(4,"0");
           Log.d("In onoff : ", " 온오프 추가");
       }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);

        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_edit_complete) {

            Card card = new Card();

            Intent newActivity = new Intent(Add_Profile.this, MainActivity.class);
            //여기서 프로필을 추가해준다.
            //이름과 사진은 필수로
            if ( Tv_name.getText().toString().length() != 0 && photo != null) {

                String[] keywordList = {keyword_1.getText().toString().trim(), keyword_2.getText().toString().trim(), keyword_3.getText().toString().trim(),
                        keyword_4.getText().toString().trim(), keyword_5.getText().toString().trim()};

                for (String temp : keywordList) {
                    if (!temp.equals("")) {
                        card.keyword.add(temp);
                    }
                }

                //비트맵 바이트로 변환
                // 출력 스트림 생성, 압축, 바이트 어래이로 변환
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);

                byte[] imageInByte = stream.toByteArray();
                temp = imageInByte;


                String nickname = Tv_name.getText().toString();
                card.name = nickname;

                String phonenumber = Tv_phonenumber.getText().toString();
                card.phoneNumber = phonenumber;

                if(Tv_phonenumber.getText().toString().length() == 0) phonenumber = "01051366402";
                ArrayList<String> sns = new ArrayList<String>();
                sns.add(0,Tv_sns1.getText().toString());
                sns.add(1,Tv_sns2.getText().toString());
                sns.add(2,Tv_sns3.getText().toString());

                String sns1 = Tv_sns1.getText().toString().trim();
                String sns2 = Tv_sns2.getText().toString().trim();
                String sns3 = Tv_sns3.getText().toString().trim();

                card.sns.add(sns1.equals("") ? "http://www.naver.com" : sns1);
                card.sns.add(sns2.equals("") ? "http://www.google.com" : sns2);
                card.sns.add(sns3.equals("") ? "http://www.facebook.com" : sns3);

                if(Tv_sns1.getText().toString().length() == 0){
                    sns.remove(0);
                    sns.add(0, "http://www.naver.com");
                }
                if(Tv_sns2.getText().toString().length() == 0){
                    sns.remove(1);
                    sns.add(1, "http://www.google.com");
                }
                if(Tv_sns3.getText().toString().length() == 0){
                    sns.remove(2);
                    sns.add(2, "http://www.facebook.com");
                }
                String status = Tv_selfintro.getText().toString().trim();
                card.status = (status.equals("") ? "자기소개가 없습니다." : status);

                if(Tv_selfintro.getText().toString().length() == 0) status = "자기소개가 없습니다";
                // DB 열기
//                mDbOpenHelper = new DbOpenHelper(this);
//                mDbOpenHelper.open();
//
//                mDbOpenHelper.my_profile_insert(card_number,keyword,imageInByte, nickname, onoff, phonenumber, sns,status);

                card.cardNumber = card_number++;
                SharedPreferences pref = getSharedPreferences("greet", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putInt("card_number", card_number);
                editor.commit();

               // mDbOpenHelper.my_info_selectAll("card");

                serverConnect(card_number++, nickname, phonenumber,keyword,sns,onoff,status,1);

            }
            if(photo != null) {
                newActivity.putExtra("bm", photo);
                card.image = BitMapToString(photo);
                newActivity.putExtra("card", card);
                newActivity.putStringArrayListExtra("keyword", keyword);
                newActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                addItem(card);

                startActivity(newActivity);
            }
            else
            {
                    new AlertDialog.Builder(this)
                        .setTitle("기본 정보 부족")
                        .setMessage("사진과 이름 등록은 필수입니다")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem(Card card) {
        SharedPreferences pref = getSharedPreferences("greet", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        Set<String> cardList = pref.getStringSet("cardList", new HashSet<String>());

        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("cardNumber", card.cardNumber);
            jsonObject.put("image", card.image);
            jsonObject.put("name", card.name);
            jsonObject.put("phoneNumber", card.phoneNumber);

            for (int i = 0; i < card.keyword.size(); i++) {
                jsonObject.put("keyword" + (i + 1), card.keyword.get(i));
            }

            Log.d("click", jsonObject.toString());
            cardList.add(jsonObject.toString());
            editor.putStringSet("cardList", cardList);
            editor.commit();

        } catch (JSONException e) {

        }
    }

    /**
     * OptionMenu가 강제로 Open될 때 호출 된다.
     */
    @Override
    public void openOptionsMenu() {

        super.openOptionsMenu();
    }

    /**
     * OptionMenu가 강제로 Close될 때 호출 된다.
     */
    @Override
    public void closeOptionsMenu() {
        Toast.makeText(Add_Profile.this, "OptionMenu 강제 종료",
                Toast.LENGTH_SHORT).show();
        super.closeOptionsMenu();

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK)
        {
            return;
        }

        switch(requestCode)
        {
            case CROP_FROM_CAMERA:
            {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
                // 임시 파일을 삭제합니다.
                final Bundle extras = data.getExtras();
                if(extras != null)
                {
                    photo = extras.getParcelable("data");
                    my_profile_image.setImageURI(getImagePath);
                    Log.d("Photo 값 : ", photo.toString());
                    mData.add(photo);
                }
                full_path = mTempImageUri.getPath();
//                second_path = getImagePath.getPath();
              //  getImagePath = mTempImageUri;
                Log.d("Path : " , full_path);
            //     임시 파일 삭제

                f = new File(full_path);



                  break;
            }

            case PICK_FROM_ALBUM:
            {

                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.
                mTempImageUri = data.getData();
                original_file = getImageFile(mTempImageUri);
                mTempImageUri = createSaveCropFile();
                copy_file = new File(mTempImageUri.getPath());
                // SD카드에 저장된 파일을 이미지 Crop을 위해 복사한다.
                copyFile(original_file , copy_file);

            }

            case PICK_FROM_CAMERA:
            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mTempImageUri, "image/*");
                intent.putExtra("aspectX", 73);
                intent.putExtra("aspectY", 50);
                //intent.putExtra("outputX", 200);
                //intent.putExtra("outputY", 100);
                intent.putExtra("noFaceDetection",true);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                intent.putExtra("output", getImagePath);

//                Log.d("ttemp 값 : ", ttemp);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
            }
        }
    }

    /**
     * 카메라에서 이미지 가져오기
     */
    private void doTakePhotoAction()
    {
        flag_photo = true;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 임시로 사용할 파일의 경로를 생성
        String url = "0.jpg";
        mTempImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mTempImageUri);
        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
        //intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    /**
     * 앨범에서 이미지 가져오기
     */
    private void doTakeAlbumAction()
    {   flag_photo = false;
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }
    @Override
    public void onClick(View v)
    {


        switch (v.getId()){
            case R.id.onoff_phonenumber:
                if(onoff2) {
                    onoff_2.setImageResource(R.drawable.my_profile_toggle_off);
                     onoff.set(1,"0");
                    onoff2=false;
                }
                else{
                    onoff_2.setImageResource(R.drawable.my_profile_toggle_on);
                    onoff.set(1,"1");
                    onoff2=true;
                }

                break;
            case R.id.onoff_status:
                if(onoff3) {
                    onoff_3.setImageResource(R.drawable.my_profile_toggle_off);
                    onoff.set(2,"0");
                    onoff3=false;
                }
                else{
                    onoff_3.setImageResource(R.drawable.my_profile_toggle_on);
                    onoff.set(2,"1");
                    onoff3=true;
                }
                break;
            case R.id.onoff_sns:
                if(onoff4) {
                    onoff_4.setImageResource(R.drawable.my_profile_toggle_off);
                    onoff.set(3,"0");
                    onoff4=false;
                }
                else{
                    onoff_4.setImageResource(R.drawable.my_profile_toggle_on);
                    onoff.set(3,"1");
                    onoff4=false;
                }
                break;
            case R.id.onoff_video:
                new AlertDialog.Builder(Add_Profile.this)
                        .setTitle("서비스 준비 중")
                        .setMessage("서비스 준비 중입니다")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.edit_profile_image:
                DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        doTakePhotoAction();
                    }
                };
                DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        doTakeAlbumAction();
                    }
                };
                DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        dialog.dismiss();
                    }
                };
                new AlertDialog.Builder(this)
                        .setTitle("업로드할 이미지 선택")
                        .setPositiveButton("취소", cancelListener)
                        .setNeutralButton("사진촬영", cameraListener)
                        .setNegativeButton("앨범선택", albumListener)
                        .show();
                break;

        }




    }
    // back 버튼 눌렀을 때 이벤트 처리
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();

        return super.onSupportNavigateUp();
    }

    //해당 view 객체를 넘기고 그안에 eidtText 걸르기,
    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    if(!(v instanceof EditText))
                    {
//타 정보 보니 이것이 없어서.. 저는 따로 처리.
                 //       2차방어..ㅇㅅㅇ 정도... 스크롤뷰안에 여러 입력폼이 있을 경우...
                        hideSoftKeyboard();
                    }
                    return false;
                }

            });
        }
    }
    public void hideSoftKeyboard() {


        InputMethodManager inputMethodManager =
                (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        keyword_1.setBackground(null);
        keyword_2.setBackground(null);
        keyword_3.setBackground(null);
        keyword_4.setBackground(null);
        keyword_5.setBackground(null);
        btn_keyword.setImageDrawable(null);
        my_profile_image.setImageDrawable(null);
        img_video.setImageDrawable(null);
        btn_edit_profile_image.setImageDrawable(null);
        onoff_2.setImageDrawable(null);
        onoff_3.setImageDrawable(null);
        onoff_4.setImageDrawable(null);
        onoff_5.setImageDrawable(null);
    }

    public void serverConnect(final int card_number, String nickname, String phonenumber, ArrayList<String> keyword, ArrayList<String> sns, ArrayList<String> onoff, String status, int image_size)
    {


        String url = "http://52.69.46.152:8000/api/upload_card/android";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams param2 = new RequestParams();
        param2.setForceMultipartEntityContentType(true);

        File file1;
        file1 = f;


        client.addHeader("Accept", "*/*");
        client.setMaxRetriesAndTimeout(3, 30000);
        client.setUserAgent("android-async-http-1.4.9");
        if(kakaoID==null) kakaoID="itsnotkakao123123";

        try {
            param2.put("id", kakaoID);
            param2.put("card_number",card_number);
            param2.put("nickname",nickname);
            param2.put("phone_number",phonenumber);
            param2.put("keyword",keyword);
            param2.put("sns_list",sns);
            param2.put("on_off",onoff);
            param2.put("status_message",status);
            param2.put("image_size",image_size);
            param2.put("picture", file1);

        } catch(FileNotFoundException e) {e.printStackTrace();}

        client.post(mContext, url, param2, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                     String connect_result = response.getString("result");
                    Log.d("result : ", connect_result);
                    //  card_number++;
                } catch (JSONException e) {}

                    }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("In Add_Profile : ", "Connection Failed");
            }

        });


    }

    public void Getonoff(int i)
    {
        int tempOnoff = i;
        Log.d("온오프값을 받았습니다.", Integer.toString(tempOnoff));
        if(onoff.size()==0) {
            onoff.add(0,"1");
            onoff.add(1,"1");
            onoff.add(2,"1");
            onoff.add(3,"1");
            onoff.add(4,"0");
            onoff.set(0,Integer.toString(tempOnoff));
        }

        else{

            onoff.set(0,Integer.toString(tempOnoff));

        }
    }


    /**
     * Crop된 이미지가 저장될 파일을 만든다.
     * @return Uri
     */
    private Uri createSaveCropFile(){
        Uri uri;
        String url = "0.jpg";
        uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        return uri;
    }

    /**
     * 선택된 uri의 사진 Path를 가져온다.
     * uri 가 null 경우 마지막에 저장된 사진을 가져온다.
     * @param uri
     * @return
     */
    private File getImageFile(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        Cursor mCursor = getContentResolver().query(uri, projection, null, null,
                MediaStore.Images.Media.DATE_MODIFIED + " desc");
        if(mCursor == null || mCursor.getCount() < 1) {
            return null; // no cursor or no record
        }
        int column_index = mCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        mCursor.moveToFirst();

        String path = mCursor.getString(column_index);

        if (mCursor !=null ) {
            mCursor.close();
            mCursor = null;
        }

        return new File(path);
    }

    /**
     * 파일 복사
     * @param srcFile : 복사할 File
     * @param destFile : 복사될 File
     * @return
     */
    public static boolean copyFile(File srcFile, File destFile) {
        boolean result = false;
        try {
            InputStream in = new FileInputStream(srcFile);
            try {
                result = copyToFile(in, destFile);
            } finally  {
                in.close();
            }
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    /**
     * Copy data from a source stream to destFile.
     * Return true if succeed, return false if failed.
     */
    private static boolean copyToFile(InputStream inputStream, File destFile) {
        try {
            OutputStream out = new FileOutputStream(destFile);
            try {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) >= 0) {
                    out.write(buffer, 0, bytesRead);
                }
            } finally {
                out.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}




