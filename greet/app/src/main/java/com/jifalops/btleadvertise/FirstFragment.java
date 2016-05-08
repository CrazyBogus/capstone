package com.jifalops.btleadvertise;

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
import android.os.ParcelUuid;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

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
 * Created by client on 2016. 5. 8..
 */
public class FirstFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
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

    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<User_Info> User_Data;
    User_Info User1 = new User_Info();


    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();


        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");


        btManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);

        // Adapter 생성
        adapter = new User_Info_Adapter(getActivity());
        // 리스트뷰 참조 및 Adapter달기


    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        userList = (ListView) view.findViewById(R.id.user_list);

        userList.setAdapter(adapter);
        userList.setDivider(null);
        // TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);

//        btn_login = (ImageButton) view.findViewById(R.id.login_imageButton);
//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent newActivity = new Intent(getActivity(), Login.class);
//
//                startActivity(newActivity);
//            }
//        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Appwith Bluetooth disabled.", Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity().isFinishing()) {
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
                .setReportDelay(500)
                .build();


        ScanFilter.Builder filter = new ScanFilter.Builder();
        filter.setServiceUuid(App_UUID);
        filters = new ArrayList<ScanFilter>();
        filters.add(filter.build());


        btAdapter.getBluetoothLeScanner().startScan(filters, setting, scanCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (btAdapter != null && btAdapter.isEnabled()) {
            btAdapter.getBluetoothLeAdvertiser().stopAdvertising(adCallback);
            btAdapter.getBluetoothLeScanner().stopScan(scanCallback);
            btAdapter.getBluetoothLeScanner().flushPendingScanResults(scanCallback);

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
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
//


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                            Log.d("온페일류어 안에서 : ", "123");
                        }

                    });


                }
                // Data가 변경 되있음을 알려준다.
                adapter.notifyDataSetChanged();

            }


        }

        ;

        @Override
        public void onScanFailed(int errorCode) {
            Log.d("onScanFailed", "");
        }

    };
}
