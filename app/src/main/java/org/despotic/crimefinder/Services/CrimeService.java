package org.despotic.crimefinder.Services;
import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.app.Activity.RESULT_OK;


public class CrimeService extends IntentService {
    //private AsyncHttpClient aClient = new SyncHttpClient();
    private AsyncHttpClient aClient = new SyncHttpClient();
    // Must create a default constructor
    URL url;

    public CrimeService() {
        // Used to name the worker thread, important only for debugging.
        super("test-service");
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
        Log.i("SERVICE", ">> LAUNCHED");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // This describes what will happen when service is triggered
        // Extract the receiver passed into the service
        ResultReceiver rec = intent.getParcelableExtra("receiver");


        // Extract additional values from the bundle
        String val = intent.getStringExtra("foo");
        // To send a message to the Activity, create a pass a Bundle

        RequestParams params = new RequestParams();
        params.put("Accept", "application/json");


        // Send synchronous request
        try {
            getCrimeData();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Bundle bundle = new Bundle();
        bundle.putString("resultValue", "My Result Value. Passed in: " + val);


        // Here we call send passing a resultCode and the bundle of extras
        //rec.send(Activity.RESULT_OK, bundle);
        rec.send(RESULT_OK,bundle);

    }


    private void getCrimeData() throws JSONException{
        SeattleOpenDataClient.get("",null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //if array


                Log.i("JSON","CONNECTION SUCCESS");

                JSONObject firstEntry = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.i("JSON",String.valueOf(response.getString(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //if object
            }
        });

    }
}