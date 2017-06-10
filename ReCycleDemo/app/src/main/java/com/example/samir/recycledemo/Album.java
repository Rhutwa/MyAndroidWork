package com.example.samir.recycledemo;

/**
 * Created by samir on 16-01-2017.
 */
/**
 * Created by samir on 16-01-2017.
 */

public class Album {
    private String name;
    private int numOfSongs;
    private int thumbnail;
    private String address;
    private int mobile;


    public Album(String name, int numOfSongs, int thumbnail,String address,int mobile) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
        this.address = address;
        this.mobile = mobile;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs()
    {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs)
    {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail()
    {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getAddress()
    {
    return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public int getmobile(){
        return mobile;
    }
   public void setMobile(int mobile)
   {
       this.mobile=mobile;
   }
}

