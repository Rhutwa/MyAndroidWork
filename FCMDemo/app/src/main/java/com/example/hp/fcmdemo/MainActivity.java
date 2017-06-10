package com.example.hp.fcmdemo;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{


    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private EditText User_ID;
    private Button register;
    String token,User_id;
    ProgressDialog pd;
    final String Myprefrences="Prefrences";
    public static final String TOKEN_NAME="token";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedpreferences = getSharedPreferences(Myprefrences, Context.MODE_PRIVATE);
        User_ID=(EditText)findViewById(R.id.User_ID);
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean flag=validate();
                if(flag==true)
                {
                    String token= FirebaseInstanceId.getInstance().getToken();
                    Log.d("rhutwa", "token: " + token);
                    Toast.makeText(MainActivity.this,token,Toast.LENGTH_LONG).show();
                    FirebaseMessaging.getInstance().subscribeToTopic("test");
                    FirebaseInstanceId.getInstance().getToken();

                    //startJSON();
                }
            }
        });
    }
    public boolean validate()
    {
        if(User_ID.getText().length()<5)
        {
            User_ID.setError("Must be of minimum 5 characters");
            User_ID.requestFocus();
            return false;
        }

        return true;
    }
    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void startJSON()
    {
        String User_id=User_ID.getText().toString();
        String serverURL = "http://192.168.0.127/fcm/register.php";
        RequestBody requestBody = new FormBody.Builder()
                .add("User_id",User_id)
                .build();
        try
        {
            if(isNetworkAvailable())
            {
                pd = new ProgressDialog(MainActivity.this);
                pd.setTitle("Loading");
                pd.setMessage("Synchronizing Data , Please Wait");
                pd.show();
                post(serverURL,requestBody, new Callback()
                {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e)
                    {
                        Log.e("onFailure", " " + e.getMessage());
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(MainActivity.this," Error in Registration , Please try again later.",Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        });
                    } // onFailure ends here
                    @Override
                    public void onResponse(okhttp3.Call call, Response response) throws IOException
                    {
                        SharedPreferences sharedpreferences = getSharedPreferences(Myprefrences, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(TOKEN_NAME, token);
                        editor.commit();
                        final String responseFromServer = response.body().string();
                        Log.e("responseFromServer", " " + responseFromServer);
                        //parse the json
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this,"Registratioin successfull",Toast.LENGTH_LONG).show();
                                //Toast.makeText(MainActivity.this," "+responseFromServer,Toast.LENGTH_LONG).show();
                            }
                        });
                    } // onResonse ends here
                }); // post method ends here
            }
            else
            {
                Toast.makeText(MainActivity.this," Enable data connectioin/wifi",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this,"Exception in main try block",Toast.LENGTH_LONG).show();
        }
    } // startJSON ends here
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
}
