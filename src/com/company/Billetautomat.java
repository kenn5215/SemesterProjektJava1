package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Billetautomat {
    private int børnePris;    // Børne Prisen for én billet.
    private int voksenPris;    // Voksen Prisen for én billet.
    private int cykelPris;    // Voksen Prisen for én billet.
    private int balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private int antalBilletterSolgt; // Antal billetter automaten i alt har solgt

    public Billetautomat() {
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
        balance -= voksenPris;             // Nulstil balance

        System.out.println("##########B##T##########");
        System.out.println("# Borgen Trafikselskab #");
        System.out.println("#                      #");
        System.out.println("#        Billet        #");
        System.out.println("#        " + voksenPris + " kr.        #");
        System.out.println("#                      #");
        System.out.println("# Du har " + balance + " kr til gode #");
        System.out.println("##########B##T##########");
        System.out.println();
    }



}