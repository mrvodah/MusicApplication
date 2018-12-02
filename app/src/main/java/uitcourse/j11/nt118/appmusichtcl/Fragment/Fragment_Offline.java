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
import java.util.List;

import uitcourse.j11.nt118.appmusichtcl.Activity.OfflineActivity;
import uitcourse.j11.nt118.appmusichtcl.Adapter.ListviewthumucofflineAdapter;
import uitcourse.j11.nt118.appmusichtcl.Database.DBUtil;
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
        //arrayListtenthumuc.add(new ItemFolder(R.drawable.icondownloadforder,"Downloads",0));
        arrayListtenthumuc.add(new ItemFolder(R.drawable.iconartistoffline,"Artists",listWithoutDuplicateArtist.size()));
        arrayListtenthumuc.add(new ItemFolder(R.drawable.iconalbumoffline,"Albums",listWithoutDuplicateElements.size()));
        listviewthumucofflineAdapter = new ListviewthumucofflineAdapter(getActivity(),arrayListtenthumuc);
        listView.setAdapter(listviewthumucofflineAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("Test Intent",String.valueOf(position));

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
        arrayListtenthumuc.remove(1);
        arrayListtenthumuc.add(1, new ItemFolder(R.drawable.iconplaylist,"Playlists", DBUtil.getCountPlayList(getContext())));
        listviewthumucofflineAdapter.notifyDataSetChanged();
        Log.d(TAG, "onResume: " + DBUtil.getCountPlayList(getContext()));
    }

    /**
     * Function to read all mp3 files from sdcard
     * and store the details in ArrayList
     * */

    public ArrayList<AudioModel> getAllAudioFromDevice(final Context context) {

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

                Log.e("Name :" + name, " Album :" + album);
                Log.e("Path :" + path, " Artist :" + artist);

                tempAudioList.add(audioModel);
            }
            c.close();
        }

        return tempAudioList;
    }





}
