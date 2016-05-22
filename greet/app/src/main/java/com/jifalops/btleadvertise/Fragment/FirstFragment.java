package com.jifalops.btleadvertise.Fragment;

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
import android.os.CountDownTimer;
import android.os.ParcelUuid;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jifalops.btleadvertise.Activity.See_Other;
import com.jifalops.btleadvertise.Adapters.UserInfoAdapter;
import com.jifalops.btleadvertise.Functional.My_Info;
import com.jifalops.btleadvertise.Functional.User_Info;
import com.jifalops.btleadvertise.Functional.User_List;
import com.jifalops.btleadvertise.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.extras.Base64;

/**
 * Created by client on 2016. 5. 8..
 */
public class FirstFragment extends Fragment {
    // Store instance variables


    private BluetoothManager btManager;
    private BluetoothAdapter btAdapter;
    private TextView textView;
    private List<ScanFilter> filters;
    Context mContext;
    ParcelUuid App_UUID = new ParcelUuid(UUID.fromString("20112018-0000-1000-8000-00805f9b34fb"));
    /* 유저리스트뷰 설정 */

    private ListView userList;
    private UserInfoAdapter adapter;
    private User_List user_list = new User_List();
    private User_Info User1 = new User_Info();
    // 리스트 아이템 데이터를 저장할 배열
    private ArrayList<User_Info> User_Data;


    private int use_bit = 30;
    private int value=0;
    private static final int REQUEST_ENABLE_BT = 1;
    private static String kakaoid=null;
    private String copy_device_name;
    private ArrayList<String> keyword;
    private boolean flag_Timer;

    private CountDownTimer timer1;

    private ImageView Iv_random;
    private ImageView Iv_interest;
    private boolean flag_search = true;


    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        //고유값을 위해 kakaoId 가져오기
        if(title != null)
        kakaoid = title;

        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        // Adapter 생성
        adapter = new UserInfoAdapter(getActivity());
        flag_Timer = true;
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        userList = (ListView) view.findViewById(R.id.user_list);
        userList.setAdapter(adapter);
        userList.setDivider(null);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // 보낼 데이터 생성
                String name = "asdasd";
                // 화면전환하는 객체 선언
                Intent intent = new Intent().setClass(getActivity(), See_Other.class);
                // 데이터 넘김
                intent.putExtra("name", name);
                // 화면 전환 메소드
                startActivity(intent);
            }
        });


        Iv_interest = (ImageView) view.findViewById(R.id.Iv_interest);
        Iv_random = (ImageView) view.findViewById(R.id.Iv_random);
        Iv_interest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Clicked Interest", Toast.LENGTH_SHORT).show();
                Iv_random.setImageResource(R.drawable.near_random2);
                Iv_interest.setImageResource(R.drawable.near_interests2);


                Bundle extra = getArguments();
                if(extra != null) {
                    keyword = extra.getStringArrayList("keyword");
                    Log.d("keyword : ", "1 : " + keyword.get(0) + " 2 : " + keyword.get(1));
                }
                flag_search = true;
            }
        });
        Iv_random.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Clicked Random", Toast.LENGTH_SHORT).show();
                Iv_random.setImageResource(R.drawable.near_random);
                Iv_interest.setImageResource(R.drawable.near_interests);
                flag_search = false;
            }
        });


//        Bundle extra = getArguments();



//
//        keyword = extra.getStringArrayList("keyword");
//        Log.d("Keyword Information : ", keyword.get(0)+" "+ keyword.get(1) + " " + keyword.get(2)+ " " + keyword.get(3)+ " " + keyword.get(4));

      //  mText=(TextView) view.findViewById(R.id.text);
      //  mText1=(TextView) view.findViewById(R.id.text1);
      //  mText.setText("asdasdasdasd");
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
        //유저 객체 생성
        User1 = new User_Info();


        btAdapter = btManager.getAdapter();
        if (btAdapter == null || !btAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
            return;
        }

        if(kakaoid != null)
        btAdapter.setName(kakaoid);
        else{btAdapter.setName("hi");}

        Log.d("On resume 내에서 : ", "Adapter이름"+btAdapter.getName());


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

        if(flag_Timer) {
            //use_bit 깎는 타이머 및 리스트에서 제거
            timer1 = new CountDownTimer(200 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) { // 총 시간과 주기
                    value++;

                    try {
                        if(User1.Getuse_bit()!=0)
                        use_bit = User1.Getuse_bit();

                        use_bit--;
                        User1.Setuse_bit(use_bit);
                        User1.Getuse_bit();

                        if (User1.Getuse_bit() == 0 || User1.Getuse_bit() == -1) {
                            user_list.remove_user(copy_device_name);
                            adapter.remove(User1);
                            adapter.notifyDataSetChanged();
                        }

                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFinish() {
                    Log.d("timer 끝났습니다 : ", "ㅅㄱ");
                }
            }.start();  // 타이머 시작
            flag_Timer = false;
        }

    }


    @Override
    public void onPause() {
        super.onPause();

            btAdapter.getBluetoothLeAdvertiser().stopAdvertising(adCallback);
            btAdapter.getBluetoothLeScanner().stopScan(scanCallback);
            btAdapter.getBluetoothLeScanner().flushPendingScanResults(scanCallback);

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
        }

        @Override
        public void onStartFailure(int errorCode) {
        }
    };

    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {


//
        }
        //배치(무더기데이터가 올때
        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            ScanResult result = null;
            String device_name = null;

           //    Log.d("scan results 결과 : ", results.toString());
            for (int i = 0; i < results.size(); i++) {
                result = results.get(i);
                device_name = result.getScanRecord().getDeviceName();
                if(flag_search) {
                    Log.d("관심사 별 검색 시작", "시작!");
                    serverConnect_interest(device_name, keyword);
                }
                else if(!flag_search) {
                    Log.d("무작위 검색 시작", "시작!");
                    serverConnect_random(device_name);
                }
            }



        }

        ;

        @Override
        public void onScanFailed(int errorCode) {
            Log.d("onScanFailed", "");
        }

    };

    public void serverConnect_random(String device_name)
    {

        Log.d("In SC_Random : ", "Success");
        //검색 된 기기가 유저리스트에 없어야지만 http 통신을 한다. 이때 체크리스트 함수를 부를 때 만약 없으면 add해주기 때문에 결국엔 check가 됨.
        if (user_list.check_exist(device_name)==2) {
            Log.d("user name : ", device_name);
            Log.d("adapter 갯수 : ", Integer.toString(adapter.getCount()));

            //HTTP 통신
            String url = "http://52.69.46.152:8001/api/find_members/random";
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams param2 = new RequestParams();

            client.addHeader("Accept", "text/json");
            client.setMaxRetriesAndTimeout(3, 30000);
            client.setUserAgent("android-async-http-1.4.9");


            param2.put("id", device_name);
            copy_device_name = device_name;

            client.post(mContext, url, param2, new JsonHttpResponseHandler() {

                //

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                    // Handle resulting parsed JSON response here
                    Log.d("온석세스 안에서 : ", response.toString());
                    try {

                        final String name = response.getString("nickname");
                        final String image_str = response.getString("profile_picture");
                        byte[] bytePlainOrg = Base64.decode(image_str, 0);
                        //byte[] 데이터  stream 데이터로 변환 후 bitmapFactory로 이미지 생성
                        ByteArrayInputStream inStream = new ByteArrayInputStream(bytePlainOrg);
                        final Bitmap bm = BitmapFactory.decodeStream(inStream);


                        //어뎁터에 유저 객체 넣기
                        adapter.add(User1);
                        //객체에 이미지 넣기
                        User1.SetImage(bm);
                        User1.GetImage();
                        //객체에 아이디 넣기
                        User1.SetId(name);
                        User1.GetId();
                        User1.Setuse_bit(40);
                        User1.Getuse_bit();


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


        } //데이터 추가 끝

        //데이터 존재하면
        else if((user_list.check_exist(device_name)) == 1){
            Log.d("여기 들어오나","aaaaa");
            User1.Setuse_bit(50);
            User1.Getuse_bit();
            Log.d("GetUse_bit : ", Integer.toString(User1.Getuse_bit()));
            //Log.d("여기 들어오나","bbbbb");
        }
        //데이터가 아예 없어
        else if((user_list.check_exist(device_name)) == 3)
        {
            Log.d("아무것도 안해","aaaaa");
        }
        else
        {
            Log.d("미쳤네미쳤어","aaaa");
        }

        adapter.notifyDataSetChanged();
    }

    public void serverConnect_interest(String device_name, ArrayList<String> keyword)
    {
        Log.d("In SC_Random : ", "Success");
        //검색 된 기기가 유저리스트에 없어야지만 http 통신을 한다. 이때 체크리스트 함수를 부를 때 만약 없으면 add해주기 때문에 결국엔 check가 됨.
        if (user_list.check_exist(device_name)==2) {
            Log.d("user name : ", device_name);
            Log.d("adapter 갯수 : ", Integer.toString(adapter.getCount()));

            //HTTP 통신
            String url = "http://52.69.46.152:8001/api/find_members/random";
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams param2 = new RequestParams();

            client.addHeader("Accept", "text/json");
            client.setMaxRetriesAndTimeout(3, 30000);
            client.setUserAgent("android-async-http-1.4.9");


            param2.put("keyword", keyword);
            param2.put("id", device_name);
            copy_device_name = device_name;

            client.post(mContext, url, param2, new JsonHttpResponseHandler() {

                //

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {

                       String a = response.getString("nickname");


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


        } //데이터 추가 끝

        //데이터 존재하면
        else if((user_list.check_exist(device_name)) == 1){
            Log.d("여기 들어오나","aaaaa");
            User1.Setuse_bit(50);
            User1.Getuse_bit();
            Log.d("GetUse_bit : ", Integer.toString(User1.Getuse_bit()));
            //Log.d("여기 들어오나","bbbbb");
        }
        //데이터가 아예 없어
        else if((user_list.check_exist(device_name)) == 3)
        {
            Log.d("아무것도 안해","aaaaa");
        }
        else
        {
            Log.d("미쳤네미쳤어","aaaa");
        }

        adapter.notifyDataSetChanged();
    }




}

