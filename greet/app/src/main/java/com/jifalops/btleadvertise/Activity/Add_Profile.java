package com.jifalops.btleadvertise.Activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jifalops.btleadvertise.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by client on 2016. 5. 14..
 */
public class Add_Profile extends FragmentActivity {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private ImageView my_profile_image;
    private ImageView btn_back;
    private ImageView btn_edit_complete;
    private ImageView btn_edit_profile_image;
    private static final String TEMP_FILE_NAME = "tempFile.jpg";

    private Uri mTempImageUri;

    ///사진
    private static final int PICK_FROM_CAMERA = 100;
    private static final int PICK_FROM_ALBUM = 200;
    private static final int REQUEST_CODE_PROFILE_IMAGE_CROP = 300;
    private Uri fileUri;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Bitmap photo;
    //사진

    private TextView Tv_name;
    private TextView Tv_phonenumber;
    private TextView Tv_selfintro;
    private TextView Tv_sns;
    private ArrayList<Bitmap> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_profile);
        init();





        my_profile_image.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(Add_Profile.this, My_Profile_Image.class);

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

                Intent newActivity = new Intent(Add_Profile.this, MainActivity.class);
                //여기서 프로필을 추가해줘야 한다.
               // Log.d("photo : ", photo.toString());
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

        Tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tv_name.setHint("");
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(Tv_name.getWindowToken(), 0);
            }
        });
        Tv_phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tv_phonenumber.setHint("");
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(Tv_phonenumber.getWindowToken(), 0);
            }
        });
        Tv_selfintro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tv_selfintro.setHint("");
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(Tv_selfintro.getWindowToken(), 0);
            }
        });
        Tv_sns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tv_sns.setHint("");
                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(Tv_sns.getWindowToken(), 0);
            }
        });
//        Iv_name.setOnClickListener(new ImageView.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Edit_Infomation(0);
//
//            }
//        });
//        Iv_phonenumber.setOnClickListener(new ImageView.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Edit_Infomation(1);
//
//            }
//        });
//        Iv_selfintro.setOnClickListener(new ImageView.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Edit_Infomation(2);
//
//            }
//        });
//        Iv_sns.setOnClickListener(new ImageView.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Edit_Infomation(3);
//
//            }
//        });


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

            startActivityForResult(intent, PICK_FROM_CAMERA);
        }
        else if ("앨범" == item.getTitle()) {
            Toast.makeText(this, "앨범 구현 예정입니다.", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent();
            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
            // Gallery 호출
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            // 잘라내기 셋팅
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 73);
            intent.putExtra("aspectY", 50);
            intent.putExtra("outputX", 365);
            intent.putExtra("outputY", 250);
            intent.putExtra("noFaceDetection",true);

            try {
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent,
                        "Complete action using"), PICK_FROM_ALBUM);
            } catch (ActivityNotFoundException e) {
                // Do nothing for now
            }
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
        Toast.makeText(Add_Profile.this, "OptionMenu 강제 종료",
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

        if (requestCode == PICK_FROM_CAMERA) {

            if (resultCode == RESULT_OK) {

                Log.e("카메라로 사진 찍기", "result_ok");

                Bundle extras = data.getExtras();

                // Image captured and saved to fileUri specified in the Intent

                if(data != null){

//                    photo = extras.getParcelable("data");
//                    my_profile_image.setImageBitmap(photo);
//                    mData.add(0,photo);
                    doCrop();
                    //사진넘기
                    //Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();

                }else{

                    Log.e("onActivityResult",fileUri.getPath());

                }



            }

            else if (resultCode == RESULT_CANCELED) {

                Log.e("com","result_canceled");

                // User cancelled the image capture

            } else {


                // Image capture failed, advise user

            }

        }



        else if (requestCode == PICK_FROM_ALBUM) {

            if(resultCode==RESULT_OK)
            {

                    Bundle extras2 = data.getExtras();
                    if (extras2 != null) {
                        photo = extras2.getParcelable("data");
                        my_profile_image.setImageBitmap(photo);
                        mData.add(0,photo);

                        Log.d("앨범에서 가져오기 : ", " mData 이후입니다" );
                    }
                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();
            }

        }
        else if(requestCode == REQUEST_CODE_PROFILE_IMAGE_CROP)
        {
            /// 이미지 불러올때 사용할 옵션을 만든다.
            BitmapFactory.Options options2 =  new BitmapFactory.Options( );
            /// 이미지 가져올때 샘플링할 옵션
            /// 값이 클수록 작게 가져온다.
            options2.inSampleSize = 1 ;

            File tempFile2 = getTempFile();
            if ( tempFile2.exists() )

                /// 이미지 불러올때 사용할 옵션을 만든다.

            photo = BitmapFactory.decodeFile(tempFile2.toString(), options2);
                my_profile_image.setImageBitmap(photo);
                mData.add(0,photo);

        }
    }

    private File getTempFile(){
        File file = new File( Environment.getExternalStorageDirectory(), TEMP_FILE_NAME );
        try{
            file.createNewFile();
        }
        catch( Exception e ){
            Log.e("cklee", "fileCreation fail" );
        }
        return file;
    }

    private Uri getJustTakenPictureUri(){
        Cursor cursor = getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{ MediaStore.Images.ImageColumns.DATA }, null, null, null );
        if ( cursor == null ) return null;

        String fileName = null;
        if ( cursor.moveToLast() )
            fileName = cursor.getString( 0 );
        cursor.close();

        if ( TextUtils.isEmpty( fileName ) ) return null;
        return Uri.fromFile( new File( fileName ) );
    }

    private void doCrop(){
        Uri justTakenPictureUri = getJustTakenPictureUri();

        mTempImageUri = Uri.fromFile( getTempFile() );
        Intent intent = new Intent( "com.android.camera.action.CROP" );
        intent.setDataAndType( justTakenPictureUri, "image/*" );

        // 잘라내기 셋팅
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 77);
        intent.putExtra("aspectY", 40);
        intent.putExtra("outputX", 385);
        intent.putExtra("outputY", 200);
        intent.putExtra("noFaceDetection",true);


        intent.putExtra( MediaStore.EXTRA_OUTPUT, mTempImageUri );
        startActivityForResult( intent,  REQUEST_CODE_PROFILE_IMAGE_CROP );
    }

//    public void Edit_Infomation(int id)
//    {
//        AlertDialog.Builder alert = new AlertDialog.Builder(this);
//
//        alert.setTitle("개인 정보 변경");
//        switch (id) {
//            case 0 :
//                alert.setMessage("이름 바꿔");
//                break;
//            case 1:
//                alert.setMessage("전화번호 바꿔");
//                break;
//            case 2:
//                alert.setMessage("자기소개 바꿔");
//                break;
//            case 3:
//                alert.setMessage("SNS주소 바꿔");
//                break;
//        }
//
//
//        final EditText name = new EditText(this);
//        alert.setView(name);
//
//        switch (id) {
//            case 0 :
//                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        String username = name.getText().toString();
//                        Tv_name.setText(username);
//
//                    }
//                });
//                break;
//            case 1:
//                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        String phonenumber = name.getText().toString();
//                        Tv_name.setText(phonenumber);
//
//                    }
//                });
//                break;
//            case 2:
//                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        String selfintro = name.getText().toString();
//                        Tv_name.setText(selfintro);
//
//                    }
//                });
//                break;
//            case 3:
//                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        String sns = name.getText().toString();
//                        Tv_name.setText(sns);
//
//                    }
//                });
//                break;
//        }
//
//
//
//        alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//
//            }
//        });
//
//        alert.show();
//    }

}



