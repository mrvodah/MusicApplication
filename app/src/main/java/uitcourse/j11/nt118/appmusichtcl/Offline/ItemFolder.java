package uitcourse.j11.nt118.appmusichtcl.Offline;

public class ItemFolder {

    int hinh;
    String ten;
    int soluong;

    public ItemFolder(int hinh, String ten, int soluong) {
        this.hinh = hinh;
        this.ten = ten;
        this.soluong = soluong;
    }

    public int getHinh() {
        return hinh;
    }

    public String getTen() {
        return ten;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
