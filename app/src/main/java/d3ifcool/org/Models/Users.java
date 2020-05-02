package d3ifcool.org.Models;

public class Users {
    private String user_id, nama, email, gender, berat, umur, tinggi, sistol, diastol;

    public Users(String user_id, String nama, String email) {
        this.user_id = user_id;
        this.nama = nama;
        this.email = email;
    }

    public Users(String user_id, String nama, String email, String gender, String berat, String umur, String tinggi) {
        this.user_id = user_id;
        this.nama = nama;
        this.email = email;
        this.gender = gender;
        this.berat = berat;
        this.umur = umur;
        this.tinggi = tinggi;
    }

    public Users(String user_id, String nama, String email, String gender, String berat, String umur, String tinggi, String sistol, String diastol) {
        this.user_id = user_id;
        this.nama = nama;
        this.email = email;
        this.gender = gender;
        this.berat = berat;
        this.umur = umur;
        this.tinggi = tinggi;
        this.sistol = sistol;
        this.diastol = diastol;
    }

    public Users() {

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

    public String getGender() {
        return gender;
    }

    public String getTinggi() {
        return tinggi;
    }

    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
