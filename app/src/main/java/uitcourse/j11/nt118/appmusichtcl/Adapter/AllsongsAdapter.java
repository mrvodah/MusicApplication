package uitcourse.j11.nt118.appmusichtcl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uitcourse.j11.nt118.appmusichtcl.Activity.PlayNhacActivity;
import uitcourse.j11.nt118.appmusichtcl.Model.Album;
import uitcourse.j11.nt118.appmusichtcl.Model.Baihat;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;
import uitcourse.j11.nt118.appmusichtcl.R;
import uitcourse.j11.nt118.appmusichtcl.Service.APIService;
import uitcourse.j11.nt118.appmusichtcl.Service.Dataservice;

public class AllsongsAdapter extends RecyclerView.Adapter<AllsongsAdapter.ViewHolder> {

    Context context;
    ArrayList<AudioModel> mangbaihat;

    public AllsongsAdapter(Context context, ArrayList<AudioModel> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView txtindex, txtcasi,txttenbaihat;


        public ViewHolder(View itemView) {
            super(itemView);
            txtcasi = itemView.findViewById(R.id.textviewtencasioffline);
            txttenbaihat = itemView.findViewById(R.id.textviewtenbaihatoffline);
            txtindex = itemView.findViewById(R.id.textviewdanhsachindexoffline);


            // Chuyen du lieu bai hat sang man hinh playnhacActivity
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuclocal", mangbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });*/

        }
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.dong_bai_hat_offline,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllsongsAdapter.ViewHolder holder, int position) {

        AudioModel baihat = mangbaihat.get(position);
        Log.d("TTTTTT",baihat.getName());
        holder.txtindex.setText(position+1+"");
        holder.txttenbaihat.setText(baihat.getName());
        holder.txtcasi.setText(baihat.getArtist());

    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }
}
