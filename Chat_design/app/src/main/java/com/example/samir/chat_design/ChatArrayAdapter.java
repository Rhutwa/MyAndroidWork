package com.example.samir.chat_design;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by samir on 13-03-2017.
 */

public class ChatArrayAdapter extends ArrayAdapter<ChatMessage>
{
    private TextView chatText;
    private List<ChatMessage> MessageList = new ArrayList<ChatMessage>();
    private LinearLayout layout;

    public ChatArrayAdapter(Context applicationContext, int chat)
    {
        super(applicationContext, chat);
    }

    public void add(ChatMessage object)
    {
        MessageList.add(object);
        super.add(object);
    }

    public int getCount()
    {
        return this.MessageList.size();
    }

    public ChatMessage getItem(int index)
    {
        return this.MessageList.get(index);
    }

    public View getView(int position, View ConvertView, ViewGroup parent)
    {
        View v = ConvertView;
        if (v == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.chat, parent, false);
        }
        layout = (LinearLayout)v.findViewById(R.id.message1);
        ChatMessage messageobj = getItem(position);
        chatText = (TextView) v.findViewById(R.id.singleMessage);
        chatText.setText(messageobj.message);
        //chatText.setBackgroundResource(messageobj.left ? R.drawable.rounded_corner1 :R.drawable.rounded_corner);
        //layout.setGravity(messageobj.right?Gravity.RIGHT:Gravity.LEFT);
        return v;
    }
    public Bitmap decodeToBitmap(byte[] decodeByte)
    {
        return BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.length);
    }


}
