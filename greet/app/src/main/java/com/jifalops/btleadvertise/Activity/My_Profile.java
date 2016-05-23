package com.jifalops.btleadvertise.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jifalops.btleadvertise.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by client on 2016. 5. 2..
 */

/* 주변 리스트를 띄워주는 액티비티 */

public class My_Profile extends ActionBarActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private ImageView my_profile_image;
    private ImageView btn_back;
    private ImageView btn_edit_complete;
    private ImageView btn_edit_profile_image;

    ///사진
    private static final String TEMP_FILE_NAME = "tempFile.jpg";

    private Uri mTempImageUri;
    private Uri getImagePath;
    ///사진
    private static final int PICK_FROM_CAMERA = 100;
    private static final int PICK_FROM_ALBUM = 200;
    private static final int CROP_FROM_CAMERA = 300;
    private Bitmap photo;
    //사진

    private ImageView Iv_name;
    private ImageView Iv_phonenumber;
    private ImageView Iv_selfintro;
    private ImageView Iv_sns;

    private TextView Tv_name;
    private TextView Tv_phonenumber;
    private TextView Tv_selfintro;
    private EditText Tv_sns1;
    private EditText Tv_sns2;
    private EditText Tv_sns3;
    private ArrayList<Bitmap> mData;
    private ScrollView Sv;
    private ImageView btn_keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);
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

                Intent newActivity = new Intent(My_Profile.this, My_Profile_Image.class);
                if (photo != null) {
                    Log.d("photo : ", photo.toString());
                    newActivity.putExtra("bm", photo);
                    newActivity.putExtra("BitmapList", mData);
                    newActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    startActivity(newActivity);
                }
            }
        });
        btn_keyword.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(My_Profile.this, Add_Keyword.class);
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

    }

    //레이아웃 설정
    private void init() {
        my_profile_image = (ImageView) findViewById(R.id.myprofile_my_profile_image);
        btn_edit_profile_image = (ImageView) findViewById(R.id.myprofile_edit_profile_image);
        btn_edit_profile_image.setOnClickListener(this);
        mData = new ArrayList<Bitmap>();
        Tv_name = (EditText) findViewById(R.id.myprofile_textview_name);
        Tv_phonenumber = (EditText) findViewById(R.id.myprofile_textview_phonenumber);
        Tv_selfintro = (EditText) findViewById(R.id.myprofile_textview_selfintro);
        Tv_sns1 = (EditText) findViewById(R.id.myprofile_textview_sns1);
        Tv_sns2 = (EditText) findViewById(R.id.myprofile_textview_sns2);
        Tv_sns3 = (EditText) findViewById(R.id.myprofile_textview_sns3);

        btn_keyword = (ImageView) findViewById(R.id.myprofile_setting_interests);

        Sv = (ScrollView) findViewById(R.id.my_profile_Sv);

//        keyword_1 = (TextView) findViewById(R.id.keyword1);
//        keyword_2 = (TextView) findViewById(R.id.keyword2);
//        keyword_3 = (TextView) findViewById(R.id.keyword3);
//        keyword_4 = (TextView) findViewById(R.id.keyword4);
//        keyword_5 = (TextView) findViewById(R.id.keyword5);
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
                    mData.add(0,photo);

                }

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
}



