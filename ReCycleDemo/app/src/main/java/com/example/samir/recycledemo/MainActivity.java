package com.example.samir.recycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        ArrayList<Album> albumArrayList = getData();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);

        recycler_view.setLayoutManager(mLayoutManager);


        AlbumsAdapter adapter = new AlbumsAdapter(MainActivity.this, albumArrayList);

        recycler_view.setAdapter(adapter);
    }

    public ArrayList<Album> getData() {
        ArrayList<Album> albumArrayList = new ArrayList();

        albumArrayList.add(new Album("Rhutwa", 1,R.mipmap.ic_launcher,"Surat",12345));
        albumArrayList.add(new Album("Heli", 2, R.mipmap.ic_launcher,"Delhi",45623));
        albumArrayList.add(new Album("Pooja", 3, R.mipmap.ic_launcher,"Pune",45218));
        albumArrayList.add(new Album("Komal", 4, R.mipmap.ic_launcher,"Bihar",7845));
        albumArrayList.add(new Album("Vishwa", 5, R.mipmap.ic_launcher,"goa",96442));
        albumArrayList.add(new Album("Urvil", 6, R.mipmap.ic_launcher,"patna",41256));
        albumArrayList.add(new Album("Darshit", 7, R.mipmap.ic_launcher,"Shimla",95674));
        albumArrayList.add(new Album("Yashasvi", 8,R.mipmap.ic_launcher,"Manali",41526));
        albumArrayList.add(new Album("Hardik", 9, R.mipmap.ic_launcher,"Vadodara",74562));
        return albumArrayList;
    }


}
