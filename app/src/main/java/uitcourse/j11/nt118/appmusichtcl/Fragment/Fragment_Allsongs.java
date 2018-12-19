package uitcourse.j11.nt118.appmusichtcl.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import uitcourse.j11.nt118.appmusichtcl.Activity.AddPlayListActivity;
import uitcourse.j11.nt118.appmusichtcl.Activity.PlayMusicActivity;
import uitcourse.j11.nt118.appmusichtcl.Adapter.AllsongsAdapter;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;
import uitcourse.j11.nt118.appmusichtcl.R;

public class Fragment_Allsongs extends Fragment {

    View view;
    RecyclerView recyclerViewbaihatoffline;
    AllsongsAdapter allsongsAdapter;
    ArrayList<AudioModel> danhsach;
    Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_allsongs, container, false);
        recyclerViewbaihatoffline = view.findViewById(R.id.recyclerviewallsongsoffline);
        danhsach = getAllAudioFromDevice(getContext());
        allsongsAdapter = new AllsongsAdapter(getActivity(), danhsach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewbaihatoffline.setLayoutManager(linearLayoutManager);
        recyclerViewbaihatoffline.setAdapter(allsongsAdapter);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public ArrayList<AudioModel> getAllAudioFromDevice(final Context context) {

        final ArrayList<AudioModel> tempAudioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA,
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.ArtistColumns.ARTIST,};
        //Cursor c = context.getContentResolver().query(uri, projection, MediaStore.Audio.Media.DATA + " like ? ", new String[]{"%yourFolderName%"}, null);
        Cursor c = context.getContentResolver().query(uri,
                projection,
                null,
                null,
                null);
        if (c != null) {
            while (c.moveToNext()) {

                AudioModel audioModel = new AudioModel();
                String path = c.getString(0);
                String album = c.getString(1);
                String artist = c.getString(2);

                String name = path.substring(path.lastIndexOf("/") + 1);

                audioModel.setName(name);
                audioModel.setAlbum(album);
                audioModel.setArtist(artist);
                audioModel.setPath(path);
                String[] s = path.split("/");
                String n = s[s.length - 1].trim();
                if (s[s.length - 1].trim().contains(".")) {
                    String[] sp = n.split("\\.");
                    audioModel.setLast(sp[0]);
                }

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Artist :" + artist);

                tempAudioList.add(audioModel);
            }
            c.close();
        }

        return tempAudioList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.textviewphathettatcaoffline)
    public void onViewClicked() {
        Intent intent1 = new Intent(getContext(), PlayMusicActivity.class);
        intent1.putExtra("cacbaihatoffline", danhsach);
        startActivity(intent1);
    }
}
