package com.company;


import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Billetautomat {
    private int pris;    // Prisen for én billet.
    private int balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private static int antalBilletterSolgt; // Antal billetter automaten i alt har solgt
    private static File file = new File("AutomatData");

    public Billetautomat() {
        pris = 40;
        balance = 0;
    }

    public Billetautomat(int billetpris, int startbalance) {
        int pris = billetpris;
        balance = startbalance;
    }


    public void skriveTilFil(int voksenbilletPris){

        int total = 0;
        try{
            Scanner scan = new Scanner(file);
            total = scan.nextInt() + voksenbilletPris;

            PrintWriter pw = new PrintWriter(file);
            pw.println( total + " kr.");
            pw.close();
        }catch (Exception u){
            System.out.println("File is not working");
        }

    }

    public int getBilletpris() {
        return pris;
    }


    public void indsætPenge(int beløb) {
        balance = balance + beløb;
    }


    public int getBalance() {
        return balance;
    }

    /** Udskriv en billet. */
    public void udskrivBillet() {
        antalBilletterSolgt = antalBilletterSolgt + 1;
        balance -= pris;             // Nulstil balance

        System.out.println("##########B##T##########");
        System.out.println("# Borgen Trafikselskab #");
        System.out.println("#                      #");
        System.out.println("#        Billet        #");
        System.out.println("#        " + pris + " kr.        #");
        System.out.println("#                      #");
        System.out.println("# Du har " + balance + " kr til gode #");
        System.out.println("##########B##T##########");
        System.out.println();
    }

    public void setBilletpris(String montørkode, int nyPris) {
        if (montørkode.equals("1234")) pris = nyPris;
        else System.err.println("Kunne ikke sætte pris - forkert kode");
    }

    public int getSamletSalgsbeløb(String montørkode) {
        if (montørkode.equals("1234")); return pris * antalBilletterSolgt;
    }
}