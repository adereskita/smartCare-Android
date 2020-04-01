package d3ifcool.org.Models;

public class Obat {

    private String id_obat, nama_obat, keterangan_obat, dosis_obat;

    public Obat(String id_obat, String nama_obat, String keterangan_obat, String dosis_obat) {
        this.id_obat = id_obat;
        this.nama_obat = nama_obat;
        this.keterangan_obat = keterangan_obat;
        this.dosis_obat = dosis_obat;
    }

    public Obat(String nama_obat, String keterangan_obat) {
        this.nama_obat = nama_obat;
        this.keterangan_obat = keterangan_obat;
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

    public String getDosis_obat() {
        return dosis_obat;
    }

    public void setDosis_obat(String dosis_obat) {
        this.dosis_obat = dosis_obat;
    }
}
