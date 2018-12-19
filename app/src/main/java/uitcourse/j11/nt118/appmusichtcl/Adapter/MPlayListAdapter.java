package uitcourse.j11.nt118.appmusichtcl.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uitcourse.j11.nt118.appmusichtcl.Activity.AddPlayListActivity;
import uitcourse.j11.nt118.appmusichtcl.Database.models.MPlayList;
import uitcourse.j11.nt118.appmusichtcl.R;

/**
 * Created by VietVan on 12/2/2018.
 */
public class MPlayListAdapter extends RecyclerView.Adapter<MPlayListAdapter.ViewHolder> {
    private Context mContext;
    private List<MPlayList> items;

    public MPlayListAdapter(Context context, List<MPlayList> items) {
        this.items = items;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dong_playlist, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MPlayList item = items.get(position);
        //TODO Fill in your logic for binding the view.

        holder.mName.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
        holder.mName.setText(item.getName());

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddPlayListActivity.class);
                intent.putExtra("playlist", item);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }

        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textviewtenplaylist)
        TextView mName;
        @BindView(R.id.imageviewbackgroundplaylist)
        ImageView mImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}