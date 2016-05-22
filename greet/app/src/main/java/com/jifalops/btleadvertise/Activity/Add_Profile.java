package com.jifalops.btleadvertise.Activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.mime.HttpMultipartMode;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.extras.Base64;

/**
 * Created by client on 2016. 5. 14..
 */
public class Add_Profile extends ActionBarActivity implements View.OnClickListener {

    private ImageView my_profile_image;
    private ImageView btn_edit_profile_image;
    private ImageView btn_keyword;
    private ImageView img_video;
    private TextView keyword_1;
    private TextView keyword_2;
    private TextView keyword_3;
    private TextView keyword_4;
    private TextView keyword_5;
    private Uri mTempImageUri;
    private Uri getImagePath;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile);
        init();

        //Editview 외에 다른 곳을 터치하면 키보드가 내려가도록 구현.
        setupUI(Sv);

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
    }
    //레이아웃 설정
    private void init() {
        my_profile_image = (ImageView) findViewById(R.id.my_profile_image);
        btn_keyword = (ImageView) findViewById(R.id.setting_interests);
        btn_edit_profile_image = (ImageView) findViewById(R.id.edit_profile_image);
        btn_edit_profile_image.setOnClickListener(this);
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
//

        if (id == R.id.item_edit_complete) {

            Intent newActivity = new Intent(Add_Profile.this, MainActivity.class);
            //여기서 프로필을 추가해준다.


            //이름과 사진은 필수로
            if ( Tv_name.getText().toString().length() != 0 || photo != null) {
                //비트맵 바이트로 변환
                // 출력 스트림 생성, 압축, 바이트 어래이로 변환
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, stream);

                int card_number = 1;
                byte[] imageInByte = stream.toByteArray();
                temp = imageInByte;
                String nickname = Tv_name.getText().toString();
                ArrayList<String> onoff = new ArrayList<String>();
                onoff.add(0, "0");
                onoff.add(1, "1");
                onoff.add(2, "0");
                onoff.add(3, "1");
                onoff.add(4, "0");
                String phonenumber = Tv_phonenumber.getText().toString();
                if(Tv_phonenumber.getText().toString().length() == 0) phonenumber = "000-0000-0000";
                ArrayList<String> sns = new ArrayList<String>();
                sns.add(0,Tv_sns1.getText().toString());
                sns.add(1,Tv_sns2.getText().toString());
                sns.add(2,Tv_sns3.getText().toString());
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
                String status = Tv_selfintro.getText().toString();
                if(Tv_selfintro.getText().toString().length() == 0) status = "자기소개가 없습니다";
                // DB 열기
                mDbOpenHelper = new DbOpenHelper(this);
                mDbOpenHelper.open();

//                mDbOpenHelper.my_profile_insert(keyword,imageInByte, nickname, onoff, phonenumber, sns,status);
  //              Log.d("In Add_profile : ", "성공적으로 DB input");
    //            mDbOpenHelper.select();
                serverConnect(5, nickname, phonenumber,keyword,sns,onoff,status,2);
                //card_number++;
                //json :{	id,
//                card_number
//            nickname
//                    phone_number
//            keyword (배열, 각키워드앞에 #이 붙음)
//            sns_list  (배열)
//            on_off (size 5개배열 0: card 1 :phone_number 2: status_message 3: sns 4: video )
//            status_message
//            image_size (유저가 5개 올릴 경우 main까지 6을 보내주셔야되요)
//        }
//

                // ArrayList<String> keyword, String image, String nickname, String onoff, String phonenumber, ArrayList<String> sns, String status)

            }
            if(photo != null) {
                newActivity.putExtra("bm", photo);
                newActivity.putStringArrayListExtra("keyword", keyword);
                newActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

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


            my_profile_image.setImageDrawable(null);
            btn_keyword.setImageDrawable(null);
        }

        return super.onOptionsItemSelected(item);
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
                    my_profile_image.setImageBitmap(photo);
                    Log.d("Photo 값 : ", photo.toString());
                    mData.add(photo);


                }

                getImagePath = mTempImageUri;

                // 임시 파일 삭제

                File f = new File(mTempImageUri.getPath());

                if(f.exists())
                {
                    f.delete();
                }

                break;
            }

            case PICK_FROM_ALBUM:
            {
                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

                mTempImageUri = data.getData();

            }

            case PICK_FROM_CAMERA:
            {
                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mTempImageUri, "image/*");
              //  intent.setDataAndType(getImagePath, "image/*");
                intent.putExtra("aspectX", 73);
                intent.putExtra("aspectY", 50);
                intent.putExtra("outputX", 365);
                intent.putExtra("outputY", 250);
                intent.putExtra("noFaceDetection",true);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
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
    /*
     * 참고 해볼곳
     * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
     * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
     * http://www.damonkohler.com/2009/02/android-recipes.html
     * http://www.firstclown.us/tag/android/
     */

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 임시로 사용할 파일의 경로를 생성
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
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
    {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }
    @Override
    public void onClick(View v)
    {
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


//    //인텐트 받아왔을 때.. 근데 이거 있으면 onStart 호출 불가
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//
//
//
//
//    }
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

    }

    public void serverConnect(int card_number, String nickname, String phonenumber, ArrayList<String> keyword, ArrayList<String> sns, ArrayList<String> onoff, String status, int image_size)
    {


        Log.d("In serverconnect : ", "here");
        String url = "http://52.69.46.152:8001/api/upload_card";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams param2 = new RequestParams();
        final String contentType = RequestParams.APPLICATION_OCTET_STREAM;

        param2.setForceMultipartEntityContentType(true);


        File file1 = new File(getPath(getImagePath));

        file1.renameTo(file1);


        Log.d("file name : ", file1.getName());
        //File file2 = new File(getPath(getImagePath),"1.jpg");
        //file1.renameTo(file2);
        client.addHeader("Accept", "*/*");
       // client.addHeader("FileName",      "android"+System.currentTimeMillis()+".jpg");
       // client.addHeader("Content-Type", "multipart/form-data; boundary=hihi");



        client.setMaxRetriesAndTimeout(3, 30000);
        client.setUserAgent("android-async-http-1.4.9");
//        MultipartEntityBuilder entity = MultipartEntityBuilder.create();
//        for (BasicNameValuePair nameValuePair : nameValuePairs) {
//            entity.addTextBody(nameValuePair.getName(), nameValuePair.getValue());
//        }
//        Log.d("FilePath : ", getImagePath.getPath());


//        Log.d("File Info : ", file.getName());
           Log.d("File Info : ", file1.getPath());

        if(kakaoID==null) kakaoID="123455";



        Log.d("Temp Info : ", temp.toString() + " " +Integer.toString(temp.length));
        try {


            param2.put("id", kakaoID);
            param2.put("card_number",card_number);
            param2.put("nickname",nickname);
            param2.put("phone_number",phonenumber);
            param2.put("pic2", file1.getName());
            param2.put("keyword",keyword);
            param2.put("sns_list",sns);
            param2.put("on_off",onoff);
            param2.put("status_message",status);
            param2.put("image_size",image_size);
            param2.put("test", temp.length);
            param2.put("test", temp);
            param2.put("files",photo);

           // param2.put("pic", file1.getPath());
            param2.put("pic", file1.getPath());
            param2.put("pic", file1);

          //  param2.put("files", file2);
      //      param2.put("picture", file);

        } catch(Exception e) {e.printStackTrace();}
    //    param2.setHttpEntityIsRepeatable(true);
    //    param2.setUseJsonStreamer(false);
//           param2.put("picture", temp);


     //   Log.d("In Param2 info : ", "file name is : " +file.getName());
//        json :{	id,
//                card_number
//            nickname
//                    phone_number
//            keyword (배열, 각키워드앞에 #이 붙음)
//            sns_list  (배열)
//            on_off (size 5개배열 0: card 1 :phone_number 2: status_message 3: sns 4: video )
//            status_message
//            image_size (유저가 5개 올릴 경우 main까지 6을 보내주셔야되요)
//        }
//


//        files (filename : picture , filename : 0.jpg
//        1.jpg
//        main.jpg)   //사진 인덱스는 사진 이름에 넣어서 보내주시고, 사람이 다섯개를 올리면  5개  0.jpg~4.jpg 보내주시고 그중  프로필	     //사진을 main.jpg로 다시 보내주셔야되요

        Log.d("In serverconnect : ", "here11");
        client.post(mContext, url, param2, new JsonHttpResponseHandler() {

            //

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
                Log.d("In Add_Profile : ", response.toString());
                try {

                    final boolean connect_result = response.getBoolean("take");





                } catch (JSONException e) {

                }
//


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("In Add_Profile : ", "Connection Failed");
            }

        });


    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }
}




