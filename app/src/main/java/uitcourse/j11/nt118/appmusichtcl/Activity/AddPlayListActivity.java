package uitcourse.j11.nt118.appmusichtcl.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uitcourse.j11.nt118.appmusichtcl.Adapter.AllsongsAdapter;
import uitcourse.j11.nt118.appmusichtcl.Database.Constant;
import uitcourse.j11.nt118.appmusichtcl.Database.DBUtil;
import uitcourse.j11.nt118.appmusichtcl.Database.models.MPlayList;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;
import uitcourse.j11.nt118.appmusichtcl.R;

// Màn hình Thêm nhạc của từng Playlist
public class AddPlayListActivity extends AppCompatActivity {

    private static final String TAG = "AddPlayListActivity";
    private static final int REQUEST_QUEST = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    AllsongsAdapter mAdapter;
    ArrayList<AudioModel> mList;
    @BindView(R.id.rcv_playlist)
    RecyclerView mRecyclerView;

    MPlayList item;
    String nameArtist;
    boolean isArtist;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_play_list);
        ButterKnife.bind(this);

        item = (MPlayList) getIntent().getSerializableExtra("playlist");
        if (item == null) {
            nameArtist = getIntent().getStringExtra("nameArtist");
            isArtist = getIntent().getBooleanExtra("isArtist", false);
        }

        setSupportActionBar(toolbar);
        try {
            if (item != null)
                getSupportActionBar().setTitle(item.getName());
            else
                getSupportActionBar().setTitle(nameArtist);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } catch (Exception ex) {
        }

        mList = new ArrayList<>();
        if (item != null)
            mList = DBUtil.get(this, item.getId());
        else {
            fab.setVisibility(View.GONE);
            if (isArtist) {
                for (AudioModel audio : Constant.audios) {
                    if (audio.getArtist().equals(nameArtist))
                        mList.add(audio);
                }
            } else {
                for (AudioModel audio : Constant.audios) {
                    if (audio.getAlbum().equals(nameArtist))
                        mList.add(audio);
                }
            }
        }
        mAdapter = new AllsongsAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_QUEST) {
            if (resultCode == RESULT_OK) {

                List<Integer> list = data.getIntegerArrayListExtra("pos");
                int id = data.getIntExtra("id", -1);
                if (list == null || list.size() == 0)
                    return;
                Log.d(TAG, "onActivityResult: " + list.size() + "/" + id);
                DBUtil.add(AddPlayListActivity.this, Constant.audios, list, id);

                mList.clear();
                mList = DBUtil.get(AddPlayListActivity.this, id);
                Log.d(TAG, "onActivityResult: " + mList);
                mAdapter = new AllsongsAdapter(this, mList);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mRecyclerView.setAdapter(mAdapter);

            }
        }
    }

    @OnClick({R.id.textviewphathettatcaoffline, R.id.fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.textviewphathettatcaoffline:
                Intent intent1 = new Intent(AddPlayListActivity.this, PlayMusicActivity.class);
                intent1.putExtra("cacbaihatoffline", mList);
                startActivity(intent1);
                break;
            case R.id.fab:
                Intent intent = new Intent(AddPlayListActivity.this, ChoosePlayListActivity.class);
                intent.putExtra("id", item.getId());
                startActivityForResult(intent, REQUEST_QUEST);
                break;
        }
    }
}
