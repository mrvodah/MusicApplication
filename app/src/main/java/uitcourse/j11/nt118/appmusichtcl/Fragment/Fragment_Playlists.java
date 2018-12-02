package uitcourse.j11.nt118.appmusichtcl.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import uitcourse.j11.nt118.appmusichtcl.Adapter.MPlayListAdapter;
import uitcourse.j11.nt118.appmusichtcl.Database.DBUtil;
import uitcourse.j11.nt118.appmusichtcl.Database.DatabaseHelper;
import uitcourse.j11.nt118.appmusichtcl.Database.models.MPlayList;
import uitcourse.j11.nt118.appmusichtcl.R;

public class Fragment_Playlists extends Fragment {

    View view;

    List<MPlayList> mPlayLists;
    MPlayListAdapter mPlayListAdapter;
    @BindView(R.id.recyclerviewplaylist)
    RecyclerView mPlayListRecyclerView;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlists, container, false);
        unbinder = ButterKnife.bind(this, view);

        mPlayLists = new ArrayList<>();
        mPlayLists = DBUtil.getPlayList(getContext());
        mPlayListAdapter = new MPlayListAdapter(getContext(), mPlayLists);
        mPlayListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlayListRecyclerView.setAdapter(mPlayListAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        // bắt click button
        createAddDialog();
    }

    private void createAddDialog() {
        final EditText edittext = new EditText(getContext());
        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Tạo PlayList");
        alert.setMessage("Nhập tên PlayList: ");

        alert.setView(edittext);

        alert.setPositiveButton("Tạo", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String name = edittext.getText().toString();

                DBUtil.addPlayList(getContext(), name);
                mPlayLists.clear();
                mPlayLists.addAll(DBUtil.getPlayList(getContext()));
                mPlayListAdapter.notifyDataSetChanged();

            }
        });

        alert.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
                dialog.dismiss();
            }
        });

        alert.show();
    }
}
