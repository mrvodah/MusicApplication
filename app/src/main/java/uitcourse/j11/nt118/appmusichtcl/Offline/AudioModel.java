package uitcourse.j11.nt118.appmusichtcl.Offline;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AudioModel implements Serializable {

    String path;
    String name;
    String album;
    String artist;

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
