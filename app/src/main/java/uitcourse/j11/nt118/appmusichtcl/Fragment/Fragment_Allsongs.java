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

import uitcourse.j11.nt118.appmusichtcl.Adapter.AllsongsAdapter;
import uitcourse.j11.nt118.appmusichtcl.Adapter.PlaynhacAdapter;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;
import uitcourse.j11.nt118.appmusichtcl.R;

public class Fragment_Allsongs extends Fragment {

    View view;
    RecyclerView recyclerViewbaihatoffline;
    AllsongsAdapter allsongsAdapter ;
    ArrayList<AudioModel> danhsach;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_allsongs,container,false);
        recyclerViewbaihatoffline = view.findViewById(R.id.recyclerviewallsongsoffline);
        danhsach = getAllAudioFromDevice(getContext());
        for(int i = 0 ; i< danhsach.size();i++)
        {
            Log.d("Testtuong",danhsach.get(i).getName());
        }
        allsongsAdapter = new AllsongsAdapter(getActivity(),danhsach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewbaihatoffline.setLayoutManager(linearLayoutManager);
        recyclerViewbaihatoffline.setAdapter(allsongsAdapter);

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

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Artist :" + artist);

                tempAudioList.add(audioModel);
            }
            c.close();
        }

        return tempAudioList;
    }

}
