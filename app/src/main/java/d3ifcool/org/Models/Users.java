package d3ifcool.org.Models;

public class Users {
    private String user_id, nama, email;

    public Users(String user_id, String nama, String email) {
        this.user_id = user_id;
        this.nama = nama;
        this.email = email;
    }

    public Users() {

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
