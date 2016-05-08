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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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

public class My_Profile extends Activity {
    //private static final String SERVICE_UUID = "CDB7950D-73F1-4D4D-8E47-C090502DBD63";
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothManager btManager;
    private BluetoothAdapter btAdapter;
    private TextView textView;
    private List<ScanFilter> filters;
    Context mContext;
    ParcelUuid App_UUID = new ParcelUuid(UUID.fromString("20112017-0000-1000-8000-00805f9b34fb"));
    /* 유저리스트뷰 설정 */
    private ListView userList;
    private User_Info_Adapter adapter;
    private User_List user_list = new User_List();
    private My_Info my_info;
    private boolean flag_clock = true;
    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<User_Info> User_Data;
    User_Info User1 = null;
    private static boolean start_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(start_flag) {
            startActivity(new Intent(this, Splash.class));
            start_flag = false;
            finish();
        }




        ActionBar actionBar = getActionBar();


        btManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        // Adapter 생성
        adapter = new User_Info_Adapter(getApplicationContext());
        // 리스트뷰 참조 및 Adapter달기
        userList = (ListView) findViewById(R.id.user_list);


        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("여기들어오나 ", "테스트");
                Intent newActivity = new Intent(My_Profile.this, MyPage.class);
                // newActivity.putExtra("2222", "Test");


                Bitmap b = adapter.getItem(position).GetImage(); // your bitmap
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 50, bs);
                byte[] byteArray = bs.toByteArray();
                newActivity.putExtra("image", byteArray);

                startActivity(newActivity);


            }
        });
        userList.setAdapter(adapter);
        userList.setDivider(null);







    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "App cannot work with Bluetooth disabled.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFinishing()) {
            return;
        }
        btAdapter = btManager.getAdapter();
        if (btAdapter == null || !btAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
            return;
        }

        btAdapter.setName("hi");

        AdvertiseSettings.Builder settings = new AdvertiseSettings.Builder();
        settings.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED);
        settings.setConnectable(true); // We are not handling connections.
        settings.setTimeout(0); // No time limit;
        settings.setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH); // Long range.



        AdvertiseData.Builder data = new AdvertiseData.Builder();


        data.addServiceUuid(App_UUID).setIncludeDeviceName(true).setIncludeTxPowerLevel(false);
        btAdapter.getBluetoothLeAdvertiser().startAdvertising(settings.build(), data.build(), adCallback);



        ScanSettings setting = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .setReportDelay(2000)
                .build();


        ScanFilter.Builder filter = new ScanFilter.Builder();
        filter.setServiceUuid(App_UUID);
        filters = new ArrayList<ScanFilter>();
        filters.add(filter.build());


        btAdapter.getBluetoothLeScanner().startScan(filters,setting,scanCallback);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (btAdapter != null && btAdapter.isEnabled()) {
            btAdapter.getBluetoothLeAdvertiser().stopAdvertising(adCallback);
            btAdapter.getBluetoothLeScanner().stopScan(scanCallback);
            btAdapter.getBluetoothLeScanner().flushPendingScanResults(scanCallback);

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    String text = null;



    return true;
}


    private final AdvertiseCallback adCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
//            textView.append("Started advertising at " + settingsInEffect.getTxPowerLevel() + "dBm.\n");
        }

        @Override
        public void onStartFailure(int errorCode) {
            switch (errorCode) {
//                case AdvertiseCallback.ADVERTISE_FAILED_ALREADY_STARTED:
//                    textView.append("Advertise failed: already started.\n");
//                    break;
//                case AdvertiseCallback.ADVERTISE_FAILED_DATA_TOO_LARGE:
//                    textView.append("Advertise failed: data too large.\n");
//                    break;
//                case AdvertiseCallback.ADVERTISE_FAILED_FEATURE_UNSUPPORTED:
//                    textView.append("Advertise failed: feature unsupported.\n");
//                    break;
//                case AdvertiseCallback.ADVERTISE_FAILED_INTERNAL_ERROR:
//                    textView.append("Advertise failed: internal error.\n");
//                    break;
//                case AdvertiseCallback.ADVERTISE_FAILED_TOO_MANY_ADVERTISERS:
//                    textView.append("Advertise failed: too many advertisers.\n");
//                    break;
            }
        }
    };

    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {


//            User1.SetId(result.getScanRecord().getDeviceName());
//            User1.GetId();
//            adapter.add(User1);
//            // Data가 변경 되있음을 알려준다.
//            adapter.notifyDataSetChanged();

          //  printScanResult(result);
//
        }

        //배치(무더기데이터가 올때
        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            ScanResult result = null;
            String device_name = null;

            Log.d("scan results 결과 : ", results.toString());
            for (int i = 0; i < results.size(); i++) {

                result = results.get(i);
                device_name = result.getScanRecord().getDeviceName();

                // if(user_list.check_exist(result.getScanRecord().getDeviceName())){User1.Setuse_bit(10);}

                //검색 된 기기가 유저리스트에 없어야지만 http 통신을 한다. 이때 체크리스트 함수를 부를 때 만약 없으면 add해주기 때문에 결국엔 check가 됨.
                if (user_list.check_exist(device_name)) {
                    Log.d("user name : ", device_name);
                    Log.d("adapter 갯수 : ", Integer.toString(adapter.getCount()));

                //HTTP 통신
                String url = "http://52.69.46.152:8000/api/find_members/random";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams param2 = new RequestParams();

                client.addHeader("Accept", "text/json");
                client.setMaxRetriesAndTimeout(3, 30000);
                client.setUserAgent("android-async-http-1.4.9");


                       param2.put("id", device_name);


                client.post(mContext, url, param2, new JsonHttpResponseHandler() {

                    //

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                        // Handle resulting parsed JSON response here
                        Log.d("온석세스 안에서 : ", response.toString());
                        try {

                            //ImageView iv = (ImageView)findViewById(R.id.user_icon);
                            final String name = response.getString("nickname");
                            final String image_str = response.getString("profile_picture");
                            byte[] bytePlainOrg = Base64.decode(image_str, 0);
                            //byte[] 데이터  stream 데이터로 변환 후 bitmapFactory로 이미지 생성
                            ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
                            final Bitmap bm = BitmapFactory.decodeStream(inStream);
                            //유저 객체 생성
                            User1 = new User_Info();
                            //어뎁터에 유저 객체 넣기
                            adapter.add(User1);
                            //객체에 이미지 넣기
                            User1.SetImage(bm);
                            User1.GetImage();
                            //객체에 아이디 넣기
                            User1.SetId(name);
                            User1.GetId();


//                                if (!(user_list.check_exist(User1.GetId()))) {
//                                    adapter.add(User1);
//                                    // adapter.add(User1);
//
//                                    User1.SetId(name);
//                                    final String image_str = response.getString("profile_picture");
//
//                                    byte[] bytePlainOrg = Base64.decode(image_str, 0);
//                                    //byte[] 데이터  stream 데이터로 변환 후 bitmapFactory로 이미지 생성
//                                    ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
//                                    final Bitmap bm = BitmapFactory.decodeStream(inStream);
//                                    User1.SetImage(bm);
//                                    User1.GetImage();
//                                    Log.d("여기는 한번만 들어와야 되는데.", "");
//
//                                    adapter.notifyDataSetChanged();

//                                }


                        } catch (JSONException e) {

                        }
//                        Intent retIntent = new Intent();


                    }


                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.d("온페일류어 안에서 : ", "123");
                    }

                });

            }
                //만약 리스트에 존재하면 countable use_bit를 부여해서 사라지면 삭제할 수 있게 한다. 이때 User1이 null이 아닌지 체크(통신 성공 후여야만 !null)
                if(user_list.check_usebit(device_name) && User1 != null)
                {
                    Log.d("use_bit 초기화", "");
                    User1.Setuse_bit(20);

                   initialize(device_name);
                    //Clock_usebit clock = new Clock_usebit(user_list, device_name);
                    //clock.setDaemon(true);
//                    if(flag_clock)
//                    {
//                        clock.start();
//                        flag_clock = false;
//                    }
////                    // User1.Getuse_bit();
////                    if(!(clock.isAlive())) {
////                        clock.start();
////                    }

                }

            }

            Log.d("adapter notifyrk 되나..","");
            // Data가 변경 되있음을 알려준다.
            adapter.notifyDataSetChanged();
        }
            @Override
            public void onScanFailed ( int errorCode){

            }

//        private void printScanResult(ScanResult result) {
//            String id = result.getDevice() != null ? result.getDevice().getAddress() : "unknown";
//            int tx = result.getScanRecord() != null ? result.getScanRecord().getTxPowerLevel() : 0;
//          //  textView.append(result.getDevice().getName() +" "+ id+ " " + result.getScanRecord()+".\n");
//           // textView.append("Device Name : " + result.getScanRecord().getDeviceName()+".\n");
//        }
    };

    class Clock_usebit extends Thread
    {
        int cnt = User1.Getuse_bit();
        private User_List User_List = new User_List();
        private String device_name = null;
        int TicTok = cnt;
        public Clock_usebit(User_List User_List, String device_name)
        {
            this.User_List = User_List;
            this.device_name = device_name;
        }

    public void run()
        {

            while(cnt!=0)
            {
                cnt--;
                TicTok = cnt;
                try
                {

                    Thread.sleep(1000);
                   // Log.d("Thread 돌아가는 소리 : ", Integer.toString(TicTok));
                }
                catch (InterruptedException e)
                {
                    Log.e("Msg : ",  e.toString());
                }
            }
            Log.d("in clock : ", "");
            User_List.remove_user(device_name);

        }
    }

    private void initialize(final String device_name)
    {

        Handler handler =    new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {

                user_list.remove_user(device_name);
            }
        };

        handler.sendEmptyMessageDelayed(0, 5000);    // ms, 3초후 종료시킴
    }



}
