package uitcourse.j11.nt118.appmusichtcl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uitcourse.j11.nt118.appmusichtcl.Activity.DanhsachbaihatActivity;
import uitcourse.j11.nt118.appmusichtcl.Activity.OfflineActivity;
import uitcourse.j11.nt118.appmusichtcl.Offline.ItemFolder;
import uitcourse.j11.nt118.appmusichtcl.R;

public class ListviewthumucofflineAdapter extends BaseAdapter{


    Context context;
    ArrayList<ItemFolder> arrayListtenthumuc ;

    public ListviewthumucofflineAdapter(Context context, ArrayList<ItemFolder> arrayListtenthumuc) {
        this.context = context;
        this.arrayListtenthumuc = arrayListtenthumuc;
    }

    private class ViewHolder
    {
        ImageView imgviewoffline;
        TextView txtitemlistviewoffline;
        TextView txtsoluong;
    }


    @Override
    public int getCount() {
        return arrayListtenthumuc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder ;

        // Nếu chưa được tạo
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_offline,null);
            holder = new ViewHolder();
            // Ánh xạ
            holder.imgviewoffline = convertView.findViewById(R.id.imageoffline);
            holder.txtitemlistviewoffline = convertView.findViewById(R.id.textviewitemlistviewoffline);
            holder.txtsoluong= convertView.findViewById(R.id.textviewsoluongoffline);
            convertView.setTag(holder);// Giữ trạng thái ánh xạ
        }
        else
        {
            // lấy lại trạng thái đã setTag;
            holder = (ViewHolder) convertView.getTag();
        }


        // Gán dữ liệu
        holder.txtitemlistviewoffline.setText(arrayListtenthumuc.get(position).getTen());
        holder.txtsoluong.setText(String.valueOf(arrayListtenthumuc.get(position).getSoluong()));
        holder.imgviewoffline.setImageResource(arrayListtenthumuc.get(position).getHinh());

        return convertView;
    }

}
