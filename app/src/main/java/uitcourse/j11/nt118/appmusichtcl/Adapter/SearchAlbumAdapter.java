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

import uitcourse.j11.nt118.appmusichtcl.Activity.DanhsachbaihatActivity;
import uitcourse.j11.nt118.appmusichtcl.Activity.PlayNhacActivity;
import uitcourse.j11.nt118.appmusichtcl.Model.Album;
import uitcourse.j11.nt118.appmusichtcl.Model.Baihat;
import uitcourse.j11.nt118.appmusichtcl.R;

public class SearchAlbumAdapter extends RecyclerView.Adapter<SearchAlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> mangalbum;

    public SearchAlbumAdapter(Context context, ArrayList<Album> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTenalbum,txtCasi;
        ImageView imgalbum,imgluotthich;
        public ViewHolder(View itemView) {
            super(itemView);

            txtTenalbum = itemView.findViewById(R.id.textviewsearchtenalbum);
            txtCasi = itemView.findViewById(R.id.textviewsearchtencasi);
            imgalbum = itemView.findViewById(R.id.imagviewSearchalbum);
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
                    Intent intent = new Intent(context,DanhsachbaihatActivity.class);
                    intent.putExtra("album",mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }





    @NonNull
    @Override
    public SearchAlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_search_album,parent,false);

        return new SearchAlbumAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAlbumAdapter.ViewHolder holder, int position) {

        Album album = mangalbum.get(position);
        holder.txtTenalbum.setText(album.getTenAblum());
        holder.txtCasi.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgalbum);
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }
}
