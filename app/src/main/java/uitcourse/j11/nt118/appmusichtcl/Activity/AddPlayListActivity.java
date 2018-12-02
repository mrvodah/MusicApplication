package uitcourse.j11.nt118.appmusichtcl.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uitcourse.j11.nt118.appmusichtcl.Adapter.AllsongsAdapter;
import uitcourse.j11.nt118.appmusichtcl.Database.models.MPlayList;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;
import uitcourse.j11.nt118.appmusichtcl.R;

public class AddPlayListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    AllsongsAdapter mAdapter ;
    ArrayList<AudioModel> mList;
    @BindView(R.id.rcv_playlist)
    RecyclerView mRecyclerView;

    MPlayList item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_play_list);
        ButterKnife.bind(this);

        item = (MPlayList) getIntent().getSerializableExtra("playlist");

        setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setTitle(item.getName());
        } catch(Exception ex){
        }

        mList = new ArrayList<>();
        mAdapter = new AllsongsAdapter(this, mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @OnClick(R.id.fab)
    public void onViewClicked() {

    }
}
