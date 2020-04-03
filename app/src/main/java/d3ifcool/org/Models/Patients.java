package d3ifcool.org.Models;

public class Patients {
    private String id_check, email, nama, disease, tipe_darah, image_id,
            deskripsi, tanggal;
    private int tinggi, berat, umur;

    public Patients(String id_check, String email, String nama, String disease,
                    String tipe_darah, String image_id, String deskripsi,
                    String tanggal, int tinggi, int berat, int umur) {
        this.id_check = id_check;
        this.email = email;
        this.nama = nama;
        this.disease = disease;
        this.tipe_darah = tipe_darah;
        this.image_id = image_id;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.tinggi = tinggi;
        this.berat = berat;
        this.umur = umur;
    }

    public Patients(String id_check, String email, String disease, String deskripsi, String tanggal) {
        this.id_check = id_check;
        this.email = email;
        this.disease = disease;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
    }

    public Patients() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getId_check() {
        return id_check;
    }

    public void setId_check(String id_check) {
        this.id_check = id_check;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe_darah() {
        return tipe_darah;
    }

    public void setTipe_darah(String tipe_darah) {
        this.tipe_darah = tipe_darah;
    }

    public int getTinggi() {
        return tinggi;
    }

    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }
}
