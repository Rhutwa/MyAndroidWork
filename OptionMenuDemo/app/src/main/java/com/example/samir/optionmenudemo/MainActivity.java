package com.example.samir.optionmenudemo;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //menu.add("Menu4");
        MenuInflater menudemo=new MenuInflater(MainActivity.this);
        menudemo.inflate(R.menu.menudemo,menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.menu1)
        {
            Toast.makeText(this,"Menu 1 selected",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.menu2)
        {
            Toast.makeText(this,"Menu 2 selected",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.menu3)
        {
            Toast.makeText(this,"Menu 3 selected",Toast.LENGTH_LONG).show();
        }

        return true;
    }
}