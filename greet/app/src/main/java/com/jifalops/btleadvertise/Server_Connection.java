package com.jifalops.btleadvertise;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.*;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import cz.msebera.android.httpclient.Header;

/**
 * Created by client on 2016. 4. 7..
 */
public class Server_Connection extends AsyncTask<String, Void, Void> {
    Context mContext;
    String TAG = "Tag";

    @Override
    protected Void doInBackground(String... params) {

        try {
//            Looper.prepare();
//            String url = "http://52.69.46.152:8000/api/find_members/random";
//            AsyncHttpClient client = new AsyncHttpClient();
//            RequestParams param1 = new RequestParams();
//            JSONObject jsonParams = new JSONObject();
//            client.addHeader("Accept", "text/json");
//
//            client.removeHeader("'content-type': 'application/x-www-form-urlencoded; charset=UTF-8'");
//            //client.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//           // client.addHeader("Content-Type", "application/json");
//            client.setMaxRetriesAndTimeout(3, 30000);
//            client.setUserAgent("android-async-http-1.4.9");


             //param1.put("This is my data", params);


            //    param1.put("id", "2");

//            StringEntity entity = null;
//          //  entity = new StringEntity(jsonParams.toString());
//           // entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//
//            client.post(mContext, url, param1, new JsonHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                    // Root JSON in response is an dictionary i.e { "data : [ ... ] }
//                    // Handle resulting parsed JSON response here
//                    JSONObject A = new JSONObject();
//                    A = response;
//
//                    Log.d("온석세스 안에서 : ", response.toString());
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
//                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                }
//            });


        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return null;

    }


    AsyncTask Task2 = new AsyncTask<Object, Void, Void>() {



        @Override
        protected Void doInBackground(Object... params) {
            try {
                Looper.prepare();
                String url = "http://52.69.46.152:8000/api/find_members/random";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams param2 = new RequestParams();
                JSONObject jsonParams = new JSONObject();
                client.addHeader("Accept", "text/json");
                client.setMaxRetriesAndTimeout(3, 30000);
                client.setUserAgent("android-async-http-1.4.9");

                param2.put("2", params);

                client.post(mContext, url, param2, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                        // Handle resulting parsed JSON response here
                        JSONObject A = new JSONObject();
                        A = response;
                        Intent retIntent = new Intent();
                        //retIntent.putExtra("retVal", A.);
                        Log.d("온석세스 안에서 : ", response.toString());

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)

                    }
                });


            }
            catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    };

}



//        postData(params[0]);
//        return null;
//        String JsonResponse = null;
//        String JsonDATA = params[0];
//
//        try {
//            Looper.prepare();
//            Log.d("params0값은 : ", params[0]);
//
//            String url = "http://52.69.46.152:8000/api/find_members/random";
//            AsyncHttpClient client = new AsyncHttpClient();
//            client.setMaxRetriesAndTimeout(3, 30000);
//            client.setUserAgent("android-async-http-1.4.9");
//            RequestParams login = new RequestParams();
//            JSONObject jlogin = new JSONObject();
//
//
//
//            try {
//                jlogin.put("id", params);
//
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            login.put("login", jlogin);
//
//            jlogin.put("testkey","testvalue");
//            StringEntity entity = new StringEntity(jlogin.toString());
//            client.post(mContext,"http://google.com", entity, "application/json",
//                    new AsyncHttpResponseHandler() {
//                        @Override
//                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                            Log.d(TAG, "Http Post Success");
//                            Log.d(TAG, "Received Msg:" + responseBody.toString());
//                        }
//
//                        @Override
//                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                            Log.d(TAG, "Http Post Fail");
//                            Log.d(TAG,"Status Code:"+statusCode);
//                        }
//                    });
//            entity = new StringEntity(jlogin.toString());
//
//            JSONObject json = new JSONObject();
//
//            json.put("id=", params[0]);
//            //  StringEntity entity = new StringEntity(json.toString());
//            RequestParams param = new RequestParams();
//
//            //param.put("id=", params[0]);
//
//            param.put("id", params[0]);
//
//
//            client.post(url, login, new JsonHttpResponseHandler() {
//
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                    // Root JSON in response is an dictionary i.e { "data : [ ... ] }
//                    // Handle resulting parsed JSON response here
//                    Log.d("테스트 ", "성공");
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
//                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
//                    Log.d("테스트 ", "실패");
//                }
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//    public void postData(String valueIWantToSend) {
//        HttpClient httpclient = new DefaultHttpClient();
//        // specify the URL you want to post to
//        HttpPost httppost = new HttpPost("http://52.69.46.152:8000/api/find_members/random");
//        try {
//            // create a list to store HTTP variables and their values
//            List nameValuePairs = new ArrayList();
//            // add an HTTP variable and value pair
//            nameValuePairs.add(new BasicNameValuePair("123123", ""));
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//            // send the variable and value, in other words post, to the URL
//            HttpResponse response = httpclient.execute(httppost);
//        } catch (ClientProtocolException e) {
//            // process execption
//        } catch (IOException e) {
//            // process execption
//        }
//    }



























//String url = "http://52.69.46.152:8000/api/find_members/random";
//            URL obj = new URL(url);
//            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
//
//            conn.setReadTimeout(10000);
//            conn.setConnectTimeout(15000);
//            conn.setRequestMethod("POST");
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//            conn.setRequestProperty("Content-Type", "application/json");
//
//            JSONObject job = new JSONObject();
//            job.put("ID","2");
//
//            byte[] outputInBytes = params[0].getBytes("UTF-8");
//            OutputStream os = conn.getOutputStream();
//            os.write( outputInBytes );
//            os.close();
//
//            int retCode = conn.getResponseCode();
//
//            InputStream is = conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String line;
//            StringBuffer response = new StringBuffer();
//            while((line = br.readLine()) != null) {
//                response.append(line);
//                response.append('\r');
//            }
//            br.close();
//
//            String res = response.toString();