package com.example.hp.menuinflater;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.text.Selection.selectAll;
import static com.example.hp.menuinflater.R.id.text;
public class MainActivity extends AppCompatActivity
{
    private Button Bt1;
    private EditText et1,edt2;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bt1=(Button)findViewById(R.id.Bt1);
        edt2=(EditText)findViewById(R.id.edt2);
        Bt1.setOnCreateContextMenuListener(this);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        menu.add(1,101,10,"Menu 1");        //groupId, itemId, order, title   
        menu.add(1,102,20,"Menu 2");
        menu.add(1,103,30,"Menu 3");
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public boolean onContextItemSelected(MenuItem item)
    {
        if (item.getItemId() == 101)
        {
            Toast.makeText(MainActivity.this, "Menu 1", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == 102)
        {
            Toast.makeText(MainActivity.this, "Menu 2", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == 103)
        {
            Toast.makeText(MainActivity.this, "Menu 3", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu1)
        {
            et1=(EditText)findViewById(R.id.et1);
            et1.selectAll();
        }
        else {
            if (item.getItemId() == R.id.menu2)
            {
                et1 = (EditText) findViewById(R.id.et1);
                Selection.removeSelection(et1.getText());
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
