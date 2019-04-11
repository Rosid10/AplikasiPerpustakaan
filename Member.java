package com.perpus;

public class Member{
    protected String kodeMember, nama;


    //data construktor
    public Member(String nama, String kd){
        this.kodeMember = kd;
        this.nama = nama;
    }

    public void setKodeMember(String kd){
        this.kodeMember = kd;
    }
    public String getKodeMember(){
        return this.kodeMember;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getNama(){
        return this.nama;
    }
    
}