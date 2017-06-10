package com.example.samir.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogIn extends AppCompatActivity
{
    EditText uname,pwd;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        uname=(EditText)findViewById(R.id.username);
        pwd=(EditText)findViewById(R.id.password);
        button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean flag = validate();
                if (flag == true)
                {
                    //startJSON();
                    //Intent intent = new Intent(LogIn.this,First_Demo.class);
                    //startActivity(intent);
                }
            }
            private boolean validate()
            {
                boolean valid = true;

                String user = uname.getText().toString();
                String password = pwd.getText().toString();


                // if (!password.equals(confirm))
                if (user.isEmpty() || user.length() < 3)
                {
                    uname.setError("Enter username");
                    uname.requestFocus();
                    return false;
                }
                if(password.length()<=0)
                {
                    pwd.setError("Enter Password");
                    pwd.requestFocus();
                    return false;
                }
                return valid;
            }

        });
    }
}
