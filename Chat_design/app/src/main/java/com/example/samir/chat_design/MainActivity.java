package com.example.samir.chat_design;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Album> albumArrayList = getData(); //this vl create dummy data
        AlbumsAdapter adapter = new AlbumsAdapter(MainActivity.this, albumArrayList);

        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Album> getData() {
        ArrayList<Album> albumArrayList = new ArrayList();

        Album album1 = new Album();
        album1.setName("Heli");
        album1.setThumbnail(R.mipmap.ic_launcher);
        albumArrayList.add(album1);

        Album album2 = new Album();
        album2.setName("Rhutwa");
        album2.setThumbnail(R.mipmap.ic_launcher);
        albumArrayList.add(album2);

        Album album3 = new Album();
        album3.setName("Pooja");
        album3.setThumbnail(R.mipmap.ic_launcher);
        albumArrayList.add(album3);

        Album album4 = new Album();
        album4.setName("Komal");
        album4.setThumbnail(R.mipmap.ic_launcher);
        albumArrayList.add(album4);

        Album album5 = new Album();
        album5.setName("Yashvi");
        album5.setThumbnail(R.mipmap.ic_launcher);
        albumArrayList.add(album5);

        Album album6 = new Album();
        album6.setName("hetvi");
        album6.setThumbnail(R.mipmap.ic_launcher);
        albumArrayList.add(album6);

        return albumArrayList;

    }
}
