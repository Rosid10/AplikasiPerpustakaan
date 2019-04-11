package com.perpus;

import java.util.Date;
import java.util.HashMap;

import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;

public class Main{
    static HashMap<String, Buku> books = new HashMap<String, Buku>();
    static HashMap<String, Member> members = new HashMap<String, Member>();
    static HashMap<String, Petugas> petugas = new HashMap<String, Petugas>();
    static HashMap<String, Peminjaman> peminjamans = new HashMap<String, Peminjaman>();
    
    static BufferedReader input = new BufferedReader(new InputStreamReader( System.in));
    static int menu;

    public static Boolean hasMember(String id){
        if(members.containsKey(id)){
           return true;
        }
        return false;

    }

    public static Boolean hasBuku(String id){
        if(books.containsKey(id)){
           return true;
        }
        return false;

    }
    
    public static Boolean hasTransaksi(String id){
        if(peminjamans.containsKey(id)){
           return true;
        }
        return false;

    }
    public static void menu() throws IOException{
        /* Pengulangan menu ketika tidak pilih tombol keluar */
        boolean run =true;
        while(run == true){
            System.out.println("================================================");
            System.out.println("           Perpustakaan Karya Bangsa            ");
            System.out.println("================================================");
            System.out.println("                      MENU                      ");
            System.out.println("------------------------------------------------");
            System.out.println("    Kode        Aksi                            ");
            System.out.println("    1.      Pinjam Buku                         ");
            System.out.println("    2.      Kembalikan Buku                     ");
            System.out.println("    3.      Daftar Pinjam                       ");
            System.out.println("    4.      Daftar Pengembalian                 ");
            System.out.println("    0.      Keluar                              ");
            System.out.println("________________________________________________");
            System.out.print("    Silakan pilih menu  : ");
            String pilihan = input.readLine();
            
            /* Membandingkan string */
            if(pilihan.equalsIgnoreCase("x")){
                run = false;
                System.out.println("================================================");
            }
            else{
                run = true;
                menu =Integer.parseInt(pilihan);
                System.out.println("================================================");
                if(menu == 4){
                    daftarpinjam();
                }
                else if(menu == 1){
                    pinjam();
                }
                else if(menu == 2){
                    pengembalian();
                }
                else if(menu == 3){
                    daftarpinjam();
                }
                else if(menu == 4){
                    daftarpengembalian();
                }
                else{
                    System.exit(0);
                }
            }
            
        }
    }
    public static void daftarpinjam(){
        for(String i : peminjamans.keySet()){
            String stat = "";
            if(peminjamans.get(i).status_pengembalian == true){
                 stat = "Sudah dikembalikan";
                 continue;
            }
            else{
                stat = "Belum dikembalikan";
            }
            System.out.println("No. Transaksi  : " + peminjamans.get(i).noTransaksi);
            System.out.println("Tanggal Pinjam : " + peminjamans.get(i).tanggal);
            String kd = peminjamans.get(i).kodeMember;
            System.out.println("Kode Member    : " + peminjamans.get(i).kodeMember);
            System.out.println("Nama           : " + members.get(kd).nama);
            String is = peminjamans.get(i).isbn;
            System.out.println("ISBN           : " + peminjamans.get(i).isbn);
            System.out.println("Judul          : " + books.get(is).judul);
            System.out.println("Jumlah         : " + peminjamans.get(i).jumlahPinjam);

            System.out.println("Status         : "+ stat+"\n");

            
        }
    }

    public static void daftarpengembalian(){
        for(String i : peminjamans.keySet()){
            String stat = "";
            if(peminjamans.get(i).status_pengembalian == true){
                 stat = "Sudah dikembalikan";
                 
            }
            else{
                stat = "Belum dikembalikan";
                continue;
            }
            System.out.println("No. Transaksi  : " + peminjamans.get(i).noTransaksi);
            System.out.println("Tanggal Pinjam : " + peminjamans.get(i).tanggal);
            String kd = peminjamans.get(i).kodeMember;
            System.out.println("Kode Member    : " + peminjamans.get(i).kodeMember);
            System.out.println("Nama           : " + members.get(kd).nama);
            String is = peminjamans.get(i).isbn;
            System.out.println("ISBN           : " + peminjamans.get(i).isbn);
            System.out.println("Judul          : " + books.get(is).judul);
            System.out.println("Jumlah         : " + peminjamans.get(i).jumlahPinjam);

            System.out.println("Status         : "+ stat+"\n");

            
        }
    }

    public static void pinjam() throws IOException{
        for(String i : books.keySet()){
            System.out.println("ISBN    : " + books.get(i).isbn);
            System.out.println("Judul   : " + books.get(i).judul);
            System.out.println("Jumlah  : " + books.get(i).jumlah + "\n");

            
        }
        System.out.print("Silakan masukkan kode Member : ");
        String kode = input.readLine().toUpperCase();
        if(hasMember(kode) == false){
           System.exit(1);
        }

        System.out.print("Silakan masukkan ISBN buku yang hendak dipinjam : ");
        String isbn = input.readLine().toUpperCase();
        if(hasBuku(isbn) == false){
            System.out.println("Buku tidak ada !");
        }
        System.out.print("Jumlah pinjam : ");
        int pinjam = Integer.parseInt(input.readLine().toUpperCase());

        Peminjaman trx = new Peminjaman(kode+isbn,kode,isbn,"01/03/2019",pinjam);
        String tanggal = trx.getCurrentDate();
        int stok = books.get(isbn).jumlah;
        books.get(isbn).jumlah = stok - pinjam;
        peminjamans.put(trx.noTransaksi, new Peminjaman(kode+isbn,kode,isbn,tanggal,pinjam));

        /* Print Struk */

        System.out.println("================================================");
        System.out.println("+               Bukti Peminjaman               +");
        System.out.println("================================================");
        System.out.println("+ Tanggal             : "+tanggal);
        System.out.println("+ No. Transaksi       : "+trx.noTransaksi);
        System.out.println("+ Member              : "+members.get(kode).kodeMember+" - "+members.get(kode).nama);
        System.out.println("+ Buku                : "+books.get(isbn).isbn+" - "+books.get(isbn).judul);
        System.out.println("+ Jumlah Pinjam       : "+trx.jumlahPinjam+" buah");
        System.out.println("================================================");
    
    }

    public static void pengembalian() throws IOException{
        daftarpinjam();

        System.out.print("Silakan masukkan No Transaksi : ");
        String no = input.readLine().toUpperCase();
        if(hasTransaksi(no) == false){
            System.exit(1);
         }

         Peminjaman trx = new Peminjaman();
         
         peminjamans.get(no).status_pengembalian = true;
         String isbn = peminjamans.get(no).isbn;
         int jmlPinjam = peminjamans.get(no).jumlahPinjam;
         int stok = books.get(isbn).jumlah;
         books.get(isbn).jumlah = stok + jmlPinjam;
         System.out.print("Masukkan tanggal pengembalian");
         String tglkembali = input.readLine();
         //String tglKembali = trx.getCurrentDate();
         String tglPinjam = peminjamans.get(no).tanggal;
         long selisih = trx.selisihTanggal(tglPinjam, tglkembali);
         
         if(selisih > 7){
            System.out.println("Anda terkena denda !\n");
            long harikenadenda = selisih-7;
            long denda = harikenadenda * 3000;
            peminjamans.get(no).totaldenda = denda;
            System.out.println("Denda sebesar Rp. "+denda);
         }
    }

    
    
    public static void main(String[] args) throws IOException{
        //input buku
        books.put("1",new Buku("1","Critical Eleven", 20));
        books.put("2",new Buku("2","Walking Out", 15));
        books.put("3",new Buku("3","Every Thing", 10));

        //input member
        members.put("M001", new Member("M001","rosyid"));
        members.put("M002", new Member("M002","wawan"));

        menu();
    }
} 

class Peminjaman{
    String noTransaksi,kodeMember, isbn, tanggal, tanggal_kembali;
    int jumlahPinjam;
    long totaldenda;
    public boolean status_pengembalian;
   
    public Peminjaman(){

    }
    public Peminjaman(String a, String b, String c, String d, int e){
        noTransaksi = a;
        kodeMember = b;
        isbn = c;
        tanggal = d;
        jumlahPinjam = e;
        status_pengembalian = false;

    }

    public String getCurrentDate(){
        Date tanggal = new Date();
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String tgl = f.format(tanggal);
        return tgl;
    }
    
    public long selisihTanggal(String tglpinjam, String tglkembali){
        DateFormat dateAwal = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateAkhir = new SimpleDateFormat("dd/MM/yyyy");
        long hasil = 0;
        try{
            Date tglAwal = dateAwal.parse(tglpinjam);
            Date tglAkhir = dateAkhir.parse(tglkembali);
             
            Date TGLAwal = tglAwal;
            Date TGLAkhir = tglAkhir;
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(TGLAwal);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(TGLAkhir);
             
            hasil = daysBetween(cal1, cal2);
        }
        catch(ParseException e){

        }
       return hasil;
    }

    private static long daysBetween(Calendar tanggalAwal, Calendar tanggalAkhir) {
        long lama = 0;
        Calendar tanggal = (Calendar) tanggalAwal.clone();
        while (tanggal.before(tanggalAkhir)) {
            tanggal.add(Calendar.DAY_OF_MONTH, 1);
            lama++;
        }
        return lama;
    }
}