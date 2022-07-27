package com.example.pengaduanmasyakarat.model;

public class laporanuser {
    private String  uid,id, nama,tgl,jam,laporan,solusi;

    public laporanuser(){
    }

    public laporanuser(String nama,String tgl,String jam,String laporan,String solusi){
        this.nama=nama;
        this.tgl=tgl;
        this.jam=jam;
        this.laporan=laporan;
        this.solusi=solusi;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getLaporan() {
        return laporan;
    }

    public void setLaporan(String laporan) {
        this.laporan = laporan;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }
}


