package com.example.hp.fcmdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Hp on 13-05-2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService
{
    String token;
    final String Myprefrences="Prefrences";
    public static final String TOKEN_NAME="token";
    @Override
    public void onTokenRefresh()
    {
        super.onTokenRefresh();
        String token= FirebaseInstanceId.getInstance().getToken();
        Log.d("rhutwa", "Refreshed token: " + token);
        SharedPreferences sharedpreferences = getSharedPreferences(Myprefrences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("token", token);
        editor.commit();
        //Toast.makeText(this,"Thanks",Toast.LENGTH_LONG).show();
        registerToken(token);
    }
    private void registerToken(String token)
    {
        /*OkHttpClient client= new OkHttpClient();
        RequestBody body= new FormBody.Builder()
                .add("Token", token)

                .build();
        Request request= new Request.Builder()
                .url("http://localhost/fcm/register.php")
                .post(body)
                .build();
        try
        {
            client.newCall(request).execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
        SharedPreferences sharedpreferences = getSharedPreferences(Myprefrences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }
}
