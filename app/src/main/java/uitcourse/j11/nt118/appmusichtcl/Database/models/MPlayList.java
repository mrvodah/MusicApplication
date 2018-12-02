package uitcourse.j11.nt118.appmusichtcl.Database.models;

import java.io.Serializable;

/**
 * Created by VietVan on 12/2/2018.
 */

public class MPlayList implements Serializable{

    private int id;
    private String name;

    public MPlayList() {
    }

    public MPlayList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
