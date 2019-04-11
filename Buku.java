package com.perpus;

public class Buku{
    protected String isbn, judul;
    protected int jumlah;

    public Buku(String isbn, String jdl, int jml){
        this.isbn = isbn;
        this.judul = jdl;
        this.jumlah = jml;
        
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }
    public String getIsbn(){
        return this.isbn;
    }
    public void setJudul(String jdl){
        this.judul = jdl;
    }
    public String getJudul(){
        return this.judul;
    }
    public void setJumlah(int jml){
        this.jumlah = jml;
    }
    
}