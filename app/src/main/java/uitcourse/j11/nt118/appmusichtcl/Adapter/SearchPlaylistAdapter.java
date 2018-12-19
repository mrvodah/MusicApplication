package uitcourse.j11.nt118.appmusichtcl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uitcourse.j11.nt118.appmusichtcl.Activity.AddPlayListActivity;
import uitcourse.j11.nt118.appmusichtcl.Activity.DanhsachbaihatActivity;
import uitcourse.j11.nt118.appmusichtcl.Activity.PlayNhacActivity;
import uitcourse.j11.nt118.appmusichtcl.Database.models.MPlayList;
import uitcourse.j11.nt118.appmusichtcl.Model.Album;
import uitcourse.j11.nt118.appmusichtcl.Model.Baihat;
import uitcourse.j11.nt118.appmusichtcl.Model.Playlist;
import uitcourse.j11.nt118.appmusichtcl.R;

public class SearchPlaylistAdapter extends RecyclerView.Adapter<SearchPlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<Playlist> mangplaylist;
    boolean isOffline;

    public SearchPlaylistAdapter(Context context, ArrayList<Playlist> mangplayplist, boolean isOffline) {
        this.context = context;
        this.mangplaylist = mangplayplist;
        this.isOffline = isOffline;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txttenplaylistsearch;
        ImageView imgplaylistsearch,imgplaylistsearchbackground;
        public ViewHolder(View itemView) {
            super(itemView);
            txttenplaylistsearch = itemView.findViewById(R.id.textviewtenplaylistsearch);
            imgplaylistsearch = itemView.findViewById(R.id.imageviewplaylistsearch);
            imgplaylistsearchbackground = itemView.findViewById(R.id.imageviewbackgroundplaylistsearch);
            //imgluotthich = itemView.findViewById(R.id.imageviewSearchluotthich);

            // Bắt sự kiện mỗi item
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PlayNhacActivity.class);
                    intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isOffline){

                        Playlist playlist = mangplaylist.get(getAdapterPosition());
                        Intent intent = new Intent(context, AddPlayListActivity.class);
                        intent.putExtra("playlist", new MPlayList(Integer.valueOf(playlist.getIdplaylist()), playlist.getTen()));
                        context.startActivity(intent);
                    }
                    else{

                        Intent intent = new Intent(context,DanhsachbaihatActivity.class);
                        intent.putExtra("itemplaylist",mangplaylist.get(getAdapterPosition()));
                        context.startActivity(intent);
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public SearchPlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_playlist,parent,false);

        return new SearchPlaylistAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchPlaylistAdapter.ViewHolder holder, int position) {

        Playlist playlist = mangplaylist.get(position);

        holder.txttenplaylistsearch.setText(playlist.getTen());
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imgplaylistsearchbackground);
        Picasso.with(context).load(playlist.getIcon()).into(holder.imgplaylistsearch);
    }
    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }
}
