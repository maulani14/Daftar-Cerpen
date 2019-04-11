package com.maulani14.utsdaftarcerpen;

public class Note {
    private String judul;
    private String isi;
    private String sumber;

    public Note(String judul, String isi, String sumber) {
        this.judul = judul;
        this.isi = isi;
        this.sumber = sumber;
    }

    public String getJudul() {

        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getSumber() {
        return sumber;
    }

    public void setSumber(String sumber) {
        this.sumber = sumber;
    }
}
