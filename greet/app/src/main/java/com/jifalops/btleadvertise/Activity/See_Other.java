package com.jifalops.btleadvertise.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jifalops.btleadvertise.R;

/**
 * Created by client on 2016. 4. 4..
 */
public class See_Other extends Activity{
    TextView User_ID;
    TextView User_Password;
    TextView realtest;
    ImageView PassingImage;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.see_other);

       // User_Info uobj= getIntent().getParcelableExtra("userTag");

//        PassingImage = (ImageView)findViewById(R.id.ivImage);
//        User_ID = (TextView)findViewById(R.id.User_Id);
//        User_Password = (TextView)findViewById(R.id.User_Password);
//        realtest = (TextView)findViewById(R.id.TTTTest);

        Intent intent = getIntent();




        byte[] arr = getIntent().getByteArrayExtra("image");
        Bitmap image = BitmapFactory.decodeByteArray(arr, 0, arr.length);

        PassingImage.setImageBitmap(image);

//        if(getIntent().hasExtra("byteArray"))
//        { ImageView PassingImage = new ImageView(this);
//            Bitmap b = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
//            PassingImage.setImageBitmap(b); }

//        Bitmap bitmap = (Bitmap)intent.getExtras().get("1111");
//        PassingImage.setImageBitmap(bitmap);
        //PassingImage.setImageResource(image_link);
        //startActivityForResult(intent, 1); // 값을 전달 다시 받아오기위해 forresult를 사용했다.
        //realtest.setText(str);                      //출력내용 지정
        //Log.d("str 값은: ", "" + str);
        //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

       // hehe.execute(str);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        //super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String ret = data.getStringExtra("retVal"); //다시 받온 값을 여기서 처리한다.
                    //TextView tv1 = (TextView)this.findViewById(R.id.textview);
                    //tv1.setText(ret);
                }else if(resultCode == RESULT_CANCELED){

                }
                break;
        }

    }
}
