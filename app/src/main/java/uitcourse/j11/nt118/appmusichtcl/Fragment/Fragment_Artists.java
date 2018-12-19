package uitcourse.j11.nt118.appmusichtcl.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uitcourse.j11.nt118.appmusichtcl.Adapter.ArtistAdapter;
import uitcourse.j11.nt118.appmusichtcl.Database.Constant;
import uitcourse.j11.nt118.appmusichtcl.R;

public class Fragment_Artists extends Fragment {

    List<String> mArtists;
    ArtistAdapter mArtistAdapter;
    @BindView(R.id.rcv_artist)
    RecyclerView mArtistRecyclerView;

    View view;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artists, container, false);
        unbinder = ButterKnife.bind(this, view);

        mArtists = new ArrayList<>();
        mArtists.addAll(Constant.mNameArtists);
        mArtistAdapter = new ArtistAdapter(getContext(), mArtists, true);
        mArtistRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mArtistRecyclerView.setAdapter(mArtistAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
