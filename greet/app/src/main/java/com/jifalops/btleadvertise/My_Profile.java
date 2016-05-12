package com.jifalops.btleadvertise;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.extras.Base64;


/**
 * Created by client on 2016. 5. 2..
 */

/* 주변 리스트를 띄워주는 액티비티 */

public class My_Profile extends FragmentActivity {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private ImageView my_profile_image;
    private ImageView btn_back;
    private ImageView btn_edit_complete;
    private ImageView btn_edit_profile_image;
    ///사진
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Bitmap photo;
    //사진

    private ArrayList<Bitmap> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);
        init();

        mData = new ArrayList<Bitmap>();


        my_profile_image.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(My_Profile.this, My_Profile_Image.class);

//                Log.d("photo : ", photo.toString());
                newActivity.putExtra("bm", photo);
                newActivity.putExtra("BitmapList", mData);
                newActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                startActivity(newActivity);
            }
        });

        btn_back.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        btn_edit_complete.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                //여기서 프로필을 추가해줘야 한다.
                finish();
            }
        });
        btn_edit_profile_image.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 카메라와 앨범 중 택하기
                openOptionsMenu();
                // 사진폴더이동
                // 카메라 호출

            }
        });


    }
    //레이아웃 설정
    private void init() {
        my_profile_image = (ImageView) findViewById(R.id.my_profile_image);
        btn_back = (ImageView) findViewById(R.id.back);
        btn_edit_complete = (ImageView) findViewById(R.id.edit_complete);
        btn_edit_profile_image = (ImageView) findViewById(R.id.edit_profile_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("카메라" );
        menu.add("앨범");

        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if ("카메라" == item.getTitle()) {
            Toast.makeText(this, "카메라 구현 예정입니다.", Toast.LENGTH_SHORT).show();
            // 사진 촬영할때.


            Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);



            fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image

          //  intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
/*위코드에서 특이사항은  사진을 찍으려고 카메라를 호출했을때

onActivityResult에서 data 값이 null이 리턴되는것이었다.



 몇시간 동안 뻘짓과 웹서핑 결과

위 코드를 사용(EXTRA_OUTPUT 설정)하면 해당 경로에 이미지가 저장되므로

굳이 리턴할 필요가 없다고 받아 드리는 수 밖에 없는듯하다.

 그래서 ' intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);' 부분을

 주석처리하면 data 값이 정상적으로 넘어온다.

*/


            // start the image capture Intent

            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);



        }
        else if ("설정" == item.getTitle()) {
            Toast.makeText(this, "앨범 구현 예정입니다.", Toast.LENGTH_SHORT).show();
        }

        return true;
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
        Toast.makeText(My_Profile.this, "OptionMenu 강제 종료",
                Toast.LENGTH_SHORT).show();
        super.closeOptionsMenu();

    }


    /** Create a file Uri for saving an image or video */

    private static Uri getOutputMediaFileUri(int type){

        return Uri.fromFile(getOutputMediaFile(type));

    }

    /** Create a File for saving an image or video */

    private static File getOutputMediaFile(int type){

        // To be safe, you should check that the SDCard is mounted

        // using Environment.getExternalStorageState() before doing this.



        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(

                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        // This location works best if you want the created images to be shared

        // between applications and persist after your app has been uninstalled.



        // Create the storage directory if it does not exist

        if (! mediaStorageDir.exists()){

            if (! mediaStorageDir.mkdirs()){

                Log.d("MyCameraApp", "failed to create directory");

                return null;

            }

        }



        // Create a media file name

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE){

            mediaFile = new File(mediaStorageDir.getPath() + File.separator +

                    "IMG_"+ timeStamp + ".jpg");

        }  else {

            return null;

        }



        return mediaFile;

    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

            if (resultCode == RESULT_OK) {

                Log.e("com", "result_ok");

                Bundle extras = data.getExtras();

                // Image captured and saved to fileUri specified in the Intent

                if(data != null){
                    photo = extras.getParcelable("data");
                    my_profile_image.setImageBitmap(photo);

                    mData.add(0,photo);
                    //사진넘기
                    //Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();

                }else{

                    Log.e("onActivityResult",fileUri.getPath());

                }



            } else if (resultCode == RESULT_CANCELED) {

                Log.e("com","result_canceled");

                // User cancelled the image capture

            } else {

                // Image capture failed, advise user

            }

        }





        }

}



