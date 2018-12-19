package uitcourse.j11.nt118.appmusichtcl.Offline;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AudioModel implements Serializable {

    int id;
    String path;
    String name;
    String album;
    String artist;
    String last;
    int relate_id;

    public AudioModel() {
    }

    public AudioModel(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public AudioModel(int id, String path, String name, String artist) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.artist = artist;
    }

    public AudioModel(int id, String name, String path, String artist, String last) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.artist = artist;
        this.last = last;
    }

    public AudioModel(int id, String name, String path, String artist, String last, int relate_id) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.artist = artist;
        this.last = last;
        this.relate_id = relate_id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "AudioModel{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
