package com.example.samir.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText ed1,ed2,ed3;
    Button button;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";
    public static final String Email = "emailKey";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        ed3=(EditText)findViewById(R.id.ed3);
        button=(Button) findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                String n  = ed1.getText().toString();
                String ph  = ed2.getText().toString();
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, n);
                editor.putString(Phone, ph);
                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(MainActivity.this,n,Toast.LENGTH_LONG).show();
            }
        });


    }
}
