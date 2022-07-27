package com.example.pengaduanmasyakarat;

public class Users {

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    private String Uid;
    private String email;
    private String nikktp;
    private String nikcard;
    private String name;
    private String tgl;
    private String phone;
    private String tmpt;
    private int usertype;

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }

//CONSTRUKTOR


    public Users() {

    }

    public Users(String uid, String email, String nikktp, String nikcard, String name, String tgl, String phone, String tmpt, int usertype) {
        this.Uid =uid;
        this.email = email;
        this.nikktp = nikktp;
        this.nikcard = nikcard;
        this.name = name;
        this.tgl = tgl;
        this.phone = phone;
        this.tmpt = tmpt;
        this.usertype=usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNikktp() {
        return nikktp;
    }

    public void setNikktp(String nikktp) {
        this.nikktp = nikktp;
    }

    public String getNikcard() {
        return nikcard;
    }

    public void setNikcard(String nikcard) {
        this.nikcard = nikcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTmpt() {
        return tmpt;
    }

    public void setTmpt(String tmpt) {
        this.tmpt = tmpt;
    }


}
