package d3ifcool.org.Models;

public class Obat {

    private String id_obat, nama_obat, keterangan_obat, user_email, user_id;

    public Obat(String id_obat, String nama_obat, String keterangan_obat, String user_email, String user_id) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.keterangan_obat = keterangan_obat;
        this.user_email = user_email;
        this.user_id = user_id;
    }

    public Obat(String nama_obat, String user_email) {
        this.nama_obat = nama_obat;
        this.user_email = user_email;
    }

    public Obat() {
    }

    public Obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public String getId_obat() {
        return id_obat;
    }

    public void setId_obat(String id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public String getKeterangan_obat() {
        return keterangan_obat;
    }

    public void setKeterangan_obat(String keterangan_obat) {
        this.keterangan_obat = keterangan_obat;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
