package uitcourse.j11.nt118.appmusichtcl.Fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uitcourse.j11.nt118.appmusichtcl.Activity.OfflineActivity;
import uitcourse.j11.nt118.appmusichtcl.Adapter.ListviewthumucofflineAdapter;
import uitcourse.j11.nt118.appmusichtcl.Database.Constant;
import uitcourse.j11.nt118.appmusichtcl.Database.DBUtil;
import uitcourse.j11.nt118.appmusichtcl.Model.Album;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;
import uitcourse.j11.nt118.appmusichtcl.Offline.ItemFolder;
import uitcourse.j11.nt118.appmusichtcl.R;

public class Fragment_Offline extends Fragment {

    private static final String TAG = "Fragment_Offline";
    Context context;
    View view;
    ListView listView;
    ListviewthumucofflineAdapter listviewthumucofflineAdapter;
    ArrayList<ItemFolder> arrayListtenthumuc;
    ArrayList<AudioModel> listaudiofromdevice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_offline,container,false);
        listView = view.findViewById(R.id.listviewthumucoffline);
        arrayListtenthumuc = new ArrayList<>();
        listaudiofromdevice = getAllAudioFromDevice(getContext());
        ArrayList<String> listalbum  = new ArrayList<>();
        ArrayList<String> listartist  = new ArrayList<>();
        for (AudioModel item :listaudiofromdevice) {
            listalbum.add(item.getAlbum());
            listartist.add(item.getArtist());
            //Log.d("TUONGTESTALBUM",item.getAlbum());
        }

        ArrayList<String> listWithoutDuplicateElements = new ArrayList<>();
        for (String item2:listalbum) {
            if (!listWithoutDuplicateElements.contains(item2)) {
                listWithoutDuplicateElements.add(item2);
            }
        }

        ArrayList<String> listWithoutDuplicateArtist = new ArrayList<>();
        for (String item3:listartist) {
            if (!listWithoutDuplicateArtist.contains(item3)) {
                listWithoutDuplicateArtist.add(item3);
            }
        }

        arrayListtenthumuc.add(new ItemFolder(R.drawable.iconallsong,"All songs",listaudiofromdevice.size()));
        arrayListtenthumuc.add(new ItemFolder(R.drawable.iconplaylist,"Playlists", DBUtil.getCountPlayList(getContext())));
        arrayListtenthumuc.add(new ItemFolder(R.drawable.iconartistoffline,"Artists",listWithoutDuplicateArtist.size()));
        arrayListtenthumuc.add(new ItemFolder(R.drawable.iconalbumoffline,"Albums",listWithoutDuplicateElements.size()));
        listviewthumucofflineAdapter = new ListviewthumucofflineAdapter(getActivity(),arrayListtenthumuc);
        listView.setAdapter(listviewthumucofflineAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), OfflineActivity.class);
                intent.putExtra("vitri",position);
                startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + DBUtil.getCountPlayList(getContext()));
        arrayListtenthumuc.remove(1);
        arrayListtenthumuc.add(1, new ItemFolder(R.drawable.iconplaylist,"Playlists", DBUtil.getCountPlayList(getContext())));
        listviewthumucofflineAdapter.notifyDataSetChanged();

        listaudiofromdevice.clear();
        listaudiofromdevice.addAll(getAllAudioFromDevice(getContext()));
        arrayListtenthumuc.remove(0);
        arrayListtenthumuc.add(0, new ItemFolder(R.drawable.iconallsong,"All songs", listaudiofromdevice.size()));

        listviewthumucofflineAdapter.notifyDataSetChanged();
    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */

    public ArrayList<AudioModel> getAllAudioFromDevice(final Context context) {
        if(Constant.audios != null || Constant.audios.size() > 0){
            Constant.audios = new ArrayList<>();
            Constant.audios.clear();
        }
        if(Constant.mNameArtists != null || Constant.mNameArtists.size() > 0){
            Constant.mNameArtists = new ArrayList<>();
            Constant.mNameArtists.clear();
        }
        if(Constant.mNameAlbums != null || Constant.mNameAlbums.size() > 0){
            Constant.mNameAlbums = new ArrayList<>();
            Constant.mNameAlbums.clear();
        }
        final ArrayList<AudioModel> tempAudioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST,};
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
                String[] names = name.split("\\.");
                audioModel.setLast(names[0]);

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Artist :" + artist);
                Constant.mNameAlbums.add(album);
                Constant.mNameArtists.add(artist);

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Artist :" + artist);

                Constant.audios.add(audioModel);
                tempAudioList.add(audioModel);
            }

            prepareAlbums();
            prepareArtist();
            c.close();
        }

        return tempAudioList;
    }

    private void prepareArtist() {
        Set<String> hs = new HashSet<>();
        hs.addAll(Constant.mNameArtists);
        Constant.mNameArtists.clear();
        Constant.mNameArtists.addAll(hs);
    }

    private void prepareAlbums() {
        Set<String> hs = new HashSet<>();
        hs.addAll(Constant.mNameAlbums);
        Constant.mNameAlbums.clear();
        Constant.mNameAlbums.addAll(hs);
        for (String mNameAlbum : Constant.mNameAlbums) {
            Constant.albums.add(new Album(mNameAlbum));
        }

    }



}
