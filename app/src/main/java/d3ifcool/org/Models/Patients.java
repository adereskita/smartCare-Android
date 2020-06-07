package d3ifcool.org.Models;

public class Patients {
    private String id_check, email, nama, nama_dokter, disease, tipe_darah, image_id,
            deskripsi, tanggal, sistol, diastol;
    private int tinggi, berat, umur;

    public Patients(String id_check, String email, String nama, String nama_dokter, String disease,
                    String tipe_darah, String image_id, String deskripsi, String tanggal,
                    String sistol, String diastol, int tinggi, int berat, int umur) {
        this.id_check = id_check;
        this.email = email;
        this.nama = nama;
        this.nama_dokter = nama_dokter;
        this.disease = disease;
        this.tipe_darah = tipe_darah;
        this.image_id = image_id;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.sistol = sistol;
        this.diastol = diastol;
        this.tinggi = tinggi;
        this.berat = berat;
        this.umur = umur;
    }

    public Patients(String id_check, String email, String disease, String deskripsi, String tanggal,
                    String nama_dokter) {
        this.id_check = id_check;
        this.email = email;
        this.disease = disease;
        this.deskripsi = deskripsi;
        this.tanggal = tanggal;
        this.nama_dokter = nama_dokter;
    }

    public Patients() {
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getSistol() {
        return sistol;
    }

    public void setSistol(String sistol) {
        this.sistol = sistol;
    }

    public String getDiastol() {
        return diastol;
    }

    public void setDiastol(String diastol) {
        this.diastol = diastol;
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
