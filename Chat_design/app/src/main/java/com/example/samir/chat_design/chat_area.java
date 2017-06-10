package com.example.samir.chat_design;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class chat_area extends AppCompatActivity
{
    private ChatArrayAdapter adt;
    private ListView list;
    private EditText chatText;
    private Button send;
    Intent in;
    private Boolean side=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_area);
        Intent i=getIntent();
        send=(Button)findViewById(R.id.btn);
        list=(ListView)findViewById(R.id.listview);
        chatText = (EditText) findViewById(R.id.chat_text);
        adt = new ChatArrayAdapter(getApplicationContext(), R.layout.chat);
        chatText.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if((event.getAction()==KeyEvent.ACTION_DOWN)&&(keyCode==KeyEvent.KEYCODE_ENTER))
                {
                    return sendChatMessage();
                }
                return false;
            }
        });
        list.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        list.setAdapter(adt);
        adt.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                list.setSelection(adt.getCount() -1);
            }
        });
        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendChatMessage();
            }
        });
    }
    private boolean sendChatMessage()
    {

        adt.add(new ChatMessage(side,chatText.getText().toString()));
        chatText.setText("");
        side=!side;
        return true;
    }

}
