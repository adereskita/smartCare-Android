package d3ifcool.org.Models;

public class Patients {
    private String patient_id, nama, symptomp_id, symptomp, tipe_darah, image_id,
            keterangan_doc, tanggal;
    private int tinggi, berat, umur;

    public Patients(String patient_id, String nama, String symptomp_id, String symptomp,
                    String tipe_darah, String image_id, String keterangan_doc,
                    String tanggal, int tinggi, int berat, int umur) {
        this.patient_id = patient_id;
        this.nama = nama;
        this.symptomp_id = symptomp_id;
        this.symptomp = symptomp;
        this.tipe_darah = tipe_darah;
        this.image_id = image_id;
        this.keterangan_doc = keterangan_doc;
        this.tanggal = tanggal;
        this.tinggi = tinggi;
        this.berat = berat;
        this.umur = umur;
    }

    public Patients(String nama, String symptomp, String image_id, String keterangan_doc, String tanggal) {
        this.nama = nama;
        this.symptomp = symptomp;
        this.image_id = image_id;
        this.keterangan_doc = keterangan_doc;
        this.tanggal = tanggal;
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

    public String getKeterangan_doc() {
        return keterangan_doc;
    }

    public void setKeterangan_doc(String keterangan_doc) {
        this.keterangan_doc = keterangan_doc;
    }

    public String getSymptomp_id() {
        return symptomp_id;
    }

    public void setSymptomp_id(String symptomp_id) {
        this.symptomp_id = symptomp_id;
    }

    public String getSymptomp() {
        return symptomp;
    }

    public void setSymptomp(String symptomp) {
        this.symptomp = symptomp;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
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
