package com.example.samir.signup;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.view.View.Z;

public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    EditText fn, ln, em, pwd, cp, data, un;
    CheckBox cd;

    Button button, btn_date,act;
    ProgressDialog pd;
    SharedPreferences prefrences;
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String BDATE = "bdate";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fn = (EditText) findViewById(R.id.firstname);
        ln = (EditText) findViewById(R.id.lastname);
        em = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.password);
        cp = (EditText) findViewById(R.id.cpwd);
        cd = (CheckBox) findViewById(R.id.cd);
        un = (EditText) findViewById(R.id.username);
        button = (Button) findViewById(R.id.button);

        data = (EditText) findViewById(R.id.data);
        btn_date = (Button) findViewById(R.id.btn_date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        act = (Button) findViewById(R.id.act);

        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);

            }
        });
        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cd.isChecked())
                {
                   boolean flag=validate();
                   if(flag==true)
                   {
                       //startJSON();
                   }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please accept terms and conditions ", Toast.LENGTH_LONG).show();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    } // oncreate ends here
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,myDateListener, year, month, day);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    showDate(arg1, arg2 + 1, arg3);
                }
            };
    private void showDate(int year, int month, int day) {
        btn_date.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    public boolean validate()
    {
        String name = fn.getText().toString();
        String email = em.getText().toString();
        String password = pwd.getText().toString();
        String lname = ln.getText().toString();
        String username = un.getText().toString();
        String confirm = cp.getText().toString();

        if (name.length()==0)
        {
            fn.setError("Enter valid firstname");
            fn.requestFocus();
            return false;
        }
        if (lname.length()==0)
        {
            ln.setError("Enter lastname");
            ln.requestFocus();
            return false;
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            em.setError("enter a valid email address");
            em.requestFocus();
            return false;
        }
        if (username.length()== 0)
        {
            un.setError("Enter valid username");
            un.requestFocus();
            return false;
        }
        if (password.length() < 4 || password.length() > 20)
        {
            pwd.setError("password is invalid");
            pwd.requestFocus();
            return false;
        }
       if (!password.equals(confirm))
        {
            cp.setError("Enter valid password");
            cp.requestFocus();
            return false;
        }
        return true;
    } // validate method ends here


    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworKAvailable ends here

    private void startJSON()
    {
        final String firstname = fn.getText().toString();
        String lastname = ln.getText().toString();
        String email = em.getText().toString();
        String password = pwd.getText().toString();
        String username = un.getText().toString();

        String serverURL = "http://hardikdesaii.in.net/bucketlist/insert.php";
        RequestBody requestBody = new FormBody.Builder()
                .add(FNAME, firstname)
                .add(LNAME, lastname)
                .add(EMAIL, email)
                .add(BDATE, btn_date.getText().toString())
                .add(USERNAME, username)
                .add(PASSWORD, password)
                .build();
        try {
            if (isNetworkAvailable()) {
                pd = new ProgressDialog(MainActivity.this);
                pd.setTitle("Loading");
                pd.setMessage("Synchronizing Data , Please Wait");
                pd.show();
                post(serverURL, requestBody, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("onFailure", " " + e.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, " Error in Registration , Please try again later.", Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        });
                    } // onFailure ends here
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseFromServer = response.body().string();
                        Log.e("responseFromServer", " " + responseFromServer);
                        //parse the json
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();
                                Toast.makeText(MainActivity.this, "Registratioin successfull", Toast.LENGTH_LONG).show();
                                //Toast.makeText(MainActivity.this," "+responseFromServer,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, LogIn.class);
                                startActivity(intent);
                            }
                        });
                    } // onResonse ends here
               }); // post method ends here
            } else {
                Toast.makeText(MainActivity.this, " Enable data connectioin/wifi", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this, "Exception in main try block", Toast.LENGTH_LONG).show();
        }
    } // startJSON ends here
    private final OkHttpClient client = new OkHttpClient();
    Call post(String url, RequestBody formBody, Callback callback) throws IOException
    {
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction()
    {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
    @Override
    public void onStart()
    {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }
    @Override
    public void onStop()
    {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }
}
