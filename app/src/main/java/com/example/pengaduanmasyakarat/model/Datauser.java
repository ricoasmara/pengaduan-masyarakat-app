package com.example.pengaduanmasyakarat.model;

public class Datauser {
    private String inputemail,inputnik,inputnikkk,inputnama,inputagama,inputtgl,inputnomer, inputalamat;

    public Datauser(){

    }

    public Datauser(String inputemail, String inputnik, String inputnikkk, String inputnama, String inputagama, String inputtgl, String inputnomer, String inputalamat) {
        this.inputemail = inputemail;
        this.inputnik = inputnik;
        this.inputnikkk = inputnikkk;
        this.inputnama = inputnama;
        this.inputagama = inputagama;
        this.inputtgl = inputtgl;
        this.inputnomer = inputnomer;
        this.inputalamat = inputalamat;
    }

    public String getInputemail() {
        return inputemail;
    }

    public void setInputemail(String inputemail) {
        this.inputemail = inputemail;
    }

    public String getInputnik() {
        return inputnik;
    }

    public void setInputnik(String inputnik) {
        this.inputnik = inputnik;
    }

    public String getInputnikkk() {
        return inputnikkk;
    }

    public void setInputnikkk(String inputnikkk) {
        this.inputnikkk = inputnikkk;
    }

    public String getInputnama() {
        return inputnama;
    }

    public void setInputnama(String inputnama) {
        this.inputnama = inputnama;
    }

    public String getInputagama() {
        return inputagama;
    }

    public void setInputagama(String inputagama) {
        this.inputagama = inputagama;
    }

    public String getInputtgl() {
        return inputtgl;
    }

    public void setInputtgl(String inputtgl) {
        this.inputtgl = inputtgl;
    }

    public String getInputnomer() {
        return inputnomer;
    }

    public void setInputnomer(String inputnomer) {
        this.inputnomer = inputnomer;
    }

    public String getInputalamat() {
        return inputalamat;
    }

    public void setInputalamat(String inputalamat) {
        this.inputalamat = inputalamat;
    }
}
