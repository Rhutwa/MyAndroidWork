/**
 * Created by samir on 16-01-2017.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> implements View.OnClickListener{
    private Context context;
    private ArrayList<Album> albumArrayList;

    public AlbumsAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.title.setText(album.getName());
        holder.count.setText(String.valueOf(album.getNumOfSongs()));
        Glide.with(context).load(album.getThumbnail()).into(holder.thumbnail);
        holder.thumbnail.setTag(R.string.app_name, position);
        holder.thumbnail.setOnClickListener(AlbumsAdapter.this);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
        //return 3;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.thumbnail) {
            ImageView im = (ImageView) v;
            int position = (int) im.getTag(R.string.app_name);
            Album album = albumArrayList.get(position);
            Toast.makeText(context, album.getAddress(), Toast.LENGTH_SHORT).show();

        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, count;
        public ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);


        }
    }
}
