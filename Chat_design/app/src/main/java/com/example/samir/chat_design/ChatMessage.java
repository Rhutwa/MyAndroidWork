package com.example.samir.chat_design;

/**
 * Created by samir on 13-03-2017.
 */

public class ChatMessage
{
    public boolean right;
    public String message;
    public ChatMessage(Boolean right,Object message)
    {
        super();
        this.right=right;
        this.message= (String) message;
    }


}
