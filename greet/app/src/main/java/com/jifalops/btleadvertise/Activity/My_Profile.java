package com.jifalops.btleadvertise.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

    private ImageView Iv_name;
    private ImageView Iv_phonenumber;
    private ImageView Iv_selfintro;
    private ImageView Iv_sns;

    private TextView Tv_name;
    private TextView Tv_phonenumber;
    private TextView Tv_selfintro;
    private TextView Tv_sns;
    private ArrayList<Bitmap> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);
        init();




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

                Intent newActivity = new Intent(My_Profile.this, MainActivity.class);
                //여기서 프로필을 추가해줘야 한다.
                //Log.d("photo : ", photo.toString());
                newActivity.putExtra("bm", photo);

                newActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(newActivity);

                //finish();
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

        Iv_name.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Edit_Infomation(0);

            }
        });
        Iv_phonenumber.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Edit_Infomation(1);

            }
        });
        Iv_selfintro.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Edit_Infomation(2);

            }
        });
        Iv_sns.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Edit_Infomation(3);

            }
        });


    }
    //레이아웃 설정
    private void init() {
        my_profile_image = (ImageView) findViewById(R.id.my_profile_image);
        btn_back = (ImageView) findViewById(R.id.back);
        btn_edit_complete = (ImageView) findViewById(R.id.edit_complete);
        btn_edit_profile_image = (ImageView) findViewById(R.id.edit_profile_image);
        mData = new ArrayList<Bitmap>();
        Tv_name = (TextView) findViewById(R.id.textview_name);
        Tv_phonenumber = (TextView) findViewById(R.id.textview_phonenumber);
        Tv_selfintro = (TextView) findViewById(R.id.textview_selfintro);
        Tv_sns = (TextView) findViewById(R.id.textview_sns);
        Iv_name = (ImageView) findViewById(R.id.btn_edit_name);
        Iv_phonenumber = (ImageView) findViewById(R.id.btn_edit_phonenumber);
        Iv_selfintro = (ImageView) findViewById(R.id.btn_edit_selfintro);
        Iv_sns = (ImageView) findViewById(R.id.btn_edit_sns);

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
        else
        {
            Log.d("이미지 넘기기 작업중","");
        }





        }

    public void Edit_Infomation(int id)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("개인 정보 변경");
        switch (id) {
            case 0 :
                alert.setMessage("이름 바꿔");
                break;
            case 1:
                alert.setMessage("전화번호 바꿔");
                break;
            case 2:
                alert.setMessage("자기소개 바꿔");
                break;
            case 3:
                alert.setMessage("SNS주소 바꿔");
                break;
        }


        final EditText name = new EditText(this);
        alert.setView(name);

        switch (id) {
            case 0 :
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String username = name.getText().toString();
                        Tv_name.setText(username);

                    }
                });
                break;
            case 1:
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String phonenumber = name.getText().toString();
                        Tv_name.setText(phonenumber);

                    }
                });
                break;
            case 2:
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String selfintro = name.getText().toString();
                        Tv_name.setText(selfintro);

                    }
                });
                break;
            case 3:
                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String sns = name.getText().toString();
                        Tv_name.setText(sns);

                    }
                });
                break;
        }



        alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        alert.show();
    }

}



