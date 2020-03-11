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
        StartFilTjek();
    }

    public Billetautomat(int billetpris, int startbalance) {
        voksenPris = billetpris;
        balance = startbalance;
    }

    private void GetIndstillinger(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Skriv prisen for en børne billet:");
        børnePris = scan.nextInt();
        System.out.println("Skriv prisen for en voksen billet:");
        voksenPris = scan.nextInt();
        System.out.println("Skriv prisen for en voksen billet:");
        cykelPris = scan.nextInt();

    }

    private void StartFilTjek(){
        //Opretter vores filobjekt kaldet startfil
        //Denne fil bruges til at indeholde alle startindstillingerne
        //Så når vi starter programmet vil vi gerne vide om der er lavet indstillinger til maskinen
        File startFil = new File("startFil");

        //Tjekker om filen er tom
        boolean erTom;
        erTom = startFil.length() == 0;

        if (erTom) {
            try {
                PrintWriter pw = new PrintWriter(startFil);
                GetIndstillinger();
                pw.println("Børne billet = " + børnePris + " KR.");
                pw.println("voksen billet = " + voksenPris + " KR.");
                pw.println("voksen billet = " + cykelPris + " KR.");
                pw.close();
            } catch (Exception u) {
                System.out.println("Kunne ikke skrive til startFilen ");
            }
        }else {
            System.out.println("Denne maskine er indstillet!");
        }
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