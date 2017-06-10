package com.example.samir.gpslocation;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.EventObject;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by samir on 18-03-2017.
 */
public class MyService extends Service
{
    GPSTracker gps;
    static int i=0;
    private static final String TAG = "MyService";
    ProgressDialog pd;
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate()
    {
        Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyService.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key", 0);
        editor.commit();
    }
    @Override
    public int onStartCommand(Intent intent, int flags,int startId) {
        //Toast.makeText(this, "My Service Started", Toast.LENGTH_SHORT).show();
        gps = new GPSTracker(MyService.this);
        i=0;
        Handler h = new Handler();
        h.postDelayed(new Runnable()
        {
            public void run()
            {
                if(getI()==0)
                {
                    latlong();
                    startService(new Intent(MyService.this,MyService.class));
                }
            }
        }, 5000);
        Log.d(TAG, "onStart");
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        i=1;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyService.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key", 1);
        editor.commit();
        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");
    }
    public int getI()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getInt("key",0);
    }
    public void latlong()
    {
        if (gps.canGetLocation())
        {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_SHORT).show();
            Log.e("longitude:",""+longitude);
            Log.e("latitude:",""+latitude);
            startJSON(latitude,longitude);
        }
    }
    private void startJSON(double lat,double longi)
    {
        String serverURL = "http://hardikdesaii.in.net/bucketlist/gps.php";
        String longitude =String.valueOf(longi);
        String latitude=String.valueOf(lat);
        RequestBody requestBody = new FormBody.Builder()
                .add(LONGITUDE, longitude)
                .add(LATITUDE, latitude)
                .build();
        try
        {


            if(isNetworkAvailable())
            {


//                pd = new ProgressDialog(MyService.this);
//                pd.setTitle("Loading");
//                pd.setMessage("Synchronizing Data , Please Wait");
//                pd.show();
                Log.e("MyService","after progress Dailog");
                post(serverURL,requestBody, new Callback()
                {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e)
                    {
                        Log.e("onFailure", " " + e.getMessage());
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run()
                            {
                                Toast.makeText(MyService.this,"On failure of post method called",Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        });
                    } // onFailure ends here
                    private void runOnUiThread(Runnable runnable)
                    {

                    }
                    @Override
                    public void onResponse(okhttp3.Call call, Response response) throws IOException
                    {
                        String responseFromServer = response.body().string();
                        Log.e("responseFromServer", " " + responseFromServer);
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                pd.dismiss();
                                Toast.makeText(MyService.this, "successfull", Toast.LENGTH_LONG).show();
                                //Toast.makeText(MainActivity.this," "+responseFromServer,Toast.LENGTH_LONG).show();
                                //Intent intent = new Intent(MyService.this, LogIn.class);
                                //startActivity(intent);
                            }
                        });
                    } // onResonse ends here
                }); // post method ends here
            }//if ends here
            else
            {
                Toast.makeText(MyService.this,"Network Connection Unavailable",Toast.LENGTH_SHORT).show();
            }
        } // try ends here
        catch (Exception ex)
        {
            Log.e("In main body",""+ex);
            Toast.makeText(MyService.this," In main try ",Toast.LENGTH_LONG).show();
        }// catch ends here
    } // start json ends here
    private final OkHttpClient client = new OkHttpClient();
    okhttp3.Call post(String url, RequestBody formBody, Callback callback) throws IOException
    {
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}




