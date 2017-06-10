package com.example.samir.chat_design;

/**
 * Created by samir on 06-03-2017.
 */

public class Album {

    private String name;
    private int numOfSongs; // camel case
    private int thumbnail;
    private int address;

    public Album(String name, int numOfSongs, int thumbnail, int address) {
        this.name = name;
        this.numOfSongs = numOfSongs;
        this.thumbnail = thumbnail;
        this.address = address;
    }

    public Album(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfSongs() {
        return numOfSongs;
    }

    public void setNumOfSongs(int numOfSongs) {
        this.numOfSongs = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }
}
