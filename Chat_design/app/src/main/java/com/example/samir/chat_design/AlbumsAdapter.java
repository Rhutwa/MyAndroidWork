package com.example.samir.chat_design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by samir on 06-03-2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<Album> albumArrayList;
    Album album;

    public AlbumsAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public AlbumsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AlbumsAdapter.MyViewHolder holder, int position) {
        album = albumArrayList.get(position);
        holder.title.setText(album.getName());
       
        Glide.with(context).load(album.getThumbnail()).into(holder.thumbnail);


        holder.card_view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent=new Intent(v.getContext(),chat_area.class);
                                                    //intent.putExtra(Constants.name,holder.title.getText().toString());
                                                    //intent.putExtra(Constants.no_of_song,holder.count.getText().toString());
                                                    //intent.putExtra(Constants.address,holder.add.getText().toString());
                                                    //intent.putExtra("img",album.getThumbnail());
                                                    context.startActivities(new Intent[]{intent});
                                                }
                                            }
        );
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    @Override
    public void onClick(View v) {

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;
        public TextView title, count;
        public Button btn1;
        public CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            //count = (TextView) itemView.findViewById(R.id.count);
            //btn1 = (Button) itemView.findViewById(R.id.btn1);
            //add = (TextView) itemView.findViewById(R.id.add);

            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
