package uitcourse.j11.nt118.appmusichtcl.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import uitcourse.j11.nt118.appmusichtcl.Database.models.MPlayList;
import uitcourse.j11.nt118.appmusichtcl.Offline.AudioModel;

/**
 * Created by VietVan on 12/2/2018.
 */

public class DBUtil {

    // PlayList

    public static void addPlayList(Context context, String name){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PLAYLIST_NAME, name);

        database.insert(DatabaseHelper.PLAYLIST_TABLE, null, values);
        database.close();
    }

    public static void delPlayList(Context context, int id){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        database.delete(DatabaseHelper.DATABASE_NAME, DatabaseHelper.PLAYLIST_ID + " = " + id, null);

        database.close();
    }

    public static int getCountPlayList(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        int count = 0;
        Cursor cursor = database.query(DatabaseHelper.PLAYLIST_TABLE, null,null,null,null,null,null);
        if(cursor == null)
            count = 0;
        else
            count = cursor.getCount();

        cursor.close();
        database.close();
        return count;
    }

    public static List<MPlayList> getPlayList(Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        List<MPlayList> list = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.PLAYLIST_TABLE, null, null,null,null,null,null);

        while (cursor.moveToNext()){
            list.add(new MPlayList(cursor.getInt(0),
                    cursor.getString(1)));
        }

        cursor.close();
        database.close();
        return list;
    }

    // AddPlayList

    public static boolean isExistsFavou(Context context, String path){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        boolean isFav;
        Cursor cursor = database.query(DatabaseHelper.FAVOU_TABLE, null,
                DatabaseHelper.FAVOU_PATH + " = " + path, null, null, null, null);
        if(cursor.getCount() == 1)
            isFav = true;
        else
            isFav = false;

        cursor.close();
        database.close();
        return isFav;
    }

    public static void add(Context context, List<AudioModel> listMusic, List<Integer> positions, int id){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        for (Integer position : positions) {
            if(!isExistsFavou(context, listMusic.get(position).getPath())){
                AudioModel audioModel = listMusic.get(position);
                values.put(DatabaseHelper.FAVOU_NAME, audioModel.getName());
                values.put(DatabaseHelper.FAVOU_PATH, audioModel.getPath());
                values.put(DatabaseHelper.FAVOU_ARTIST, audioModel.getArtist());
                values.put(DatabaseHelper.FAVOU_RELATE_ID, id);

                database.insert(DatabaseHelper.FAVOU_TABLE, null, values);
            }
        }

        database.close();
    }

}
