package uitcourse.j11.nt118.appmusichtcl.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VietVan on 12/2/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "music";
    public static final int VERSION = 1;

    public static final String PLAYLIST_TABLE = "playlist_table";
    public static final String FAVOU_TABLE = "favou_table";
    public static final String PLAYLIST_ID = "_id";
    public static final String PLAYLIST_NAME = "_name";
    public static final String FAVOU_ID = "favou_id";
    public static final String FAVOU_NAME = "favou_name";
    public static final String FAVOU_PATH = "favou_path";
    public static final String FAVOU_ARTIST = "favou_artist";
    public static final String FAVOU_RELATE_ID = "favou_relate_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + PLAYLIST_TABLE + "( "
                + PLAYLIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLAYLIST_NAME + " TEXT )";

        String query2 = "CREATE TABLE " + FAVOU_TABLE + "( "
                + FAVOU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FAVOU_NAME + " TEXT, "
                + FAVOU_PATH + " TEXT, "
                + FAVOU_ARTIST + " TEXT, "
                + FAVOU_RELATE_ID + " INTEGER )";

        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP DATABASE IF EXISTS " + DATABASE_NAME;
        db.execSQL(query);

        onCreate(db);
    }
}
