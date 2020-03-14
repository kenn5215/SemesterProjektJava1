package com.company;

import java.io.*;
import java.util.Scanner;

public class Billetautomat {
    private int børnePris;    // Børne Prisen for én billet.
    private int voksenPris;    // Voksen Prisen for én billet.
    private int cykelPris;    // Voksen Prisen for én billet.
    private int balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private int antalBilletterSolgt; // Antal billetter automaten i alt har solgt
    private String brugernavn;
    private String kodeord;


    public Billetautomat() {
    }

    private void loginOplysninger(){
        Scanner scan = new Scanner(System.in);
        File login = new File("LoginOplysninger");

        if(login.length() == 0){
            System.out.println("Denne maskine er helt ny og der ikke oprettet en bruger, venlist opret en bruger");
            System.out.println("Vælg dit brugernavn:");
            brugernavn = scan.next();
            System.out.println("vælg dit kodeord:");
            kodeord = scan.next();

            try {
                PrintWriter pw = new PrintWriter(login);
                pw.println(brugernavn);
                pw.println(kodeord);
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Kunne ikke skrive til filen!");
            }

        }else{
            try{
                BufferedReader reader = new BufferedReader(new FileReader(login));

                brugernavn = reader.readLine();
                kodeord = reader.readLine();
            }catch (Exception u){
                System.out.println("Kunne ikke læse fra filen");
            }
        }


    }


    public void adminMenu(IndkøbsKurv kurv, Translog translog){
        Scanner scan = new Scanner(System.in);

        loginOplysninger();

        System.out.println("Der er kun adgang for instalatører");
        //Brugernavn
        System.out.println("Skriv dit brugernavn:");
        String intastetBrugerNavn = scan.next();
        //Kodeord
        System.out.println("Skriv dit kodeord");
        String intastetKodeord = scan.next();

        //Tjekker om login oplysninger stemmer overens.
        if (intastetBrugerNavn.equals(brugernavn) && intastetKodeord.equals(kodeord)) {
            int option = 1;
            while (option != 0) {
                System.out.println("============================================");
                System.out.println("Du har følgende muligheder:                 ");
                System.out.println("============================================");
                System.out.println("Tryk 1 for: Ændre billetprisen              ");
                System.out.println("********************************************");
                System.out.println("Tryk 2 for: Print log                       ");
                System.out.println("********************************************");
                System.out.println("Tryk 0 for: Afslut                          ");
                System.out.println("********************************************");
                option = scan.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("============================================");
                        System.out.println("Ny børnebillet pris:");
                        int nyBørnePris = scan.nextInt();
                        System.out.println("Ny voksenbillet pris:");
                        int nyVoksenPris = scan.nextInt();
                        System.out.println("Ny cykelbillet pris:");
                        int nyCykelPris = scan.nextInt();
                        System.out.println("============================================");
                        kurv.setBilletPris(nyVoksenPris, nyBørnePris, nyCykelPris);
                        break;
                    case 2:
                        translog.printAlleLog();
                        break;
                }
            }
        }else{
                System.out.println("Forkert brugernavn eller adgangskode....");
                System.out.println("Skriv et eller andet for at gå tilbage");
                String ignorer = scan.next();
            }
    }

    public void indsætPenge(int beløb) {
        if(beløb < 0){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Indtast venligst et beløb over 0");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            balance = 0;
        }else {
            balance = balance + beløb;
        }
    }


    public int getBalance() {
        return balance;
    }


    public void udskrivBillet(int totalPris) {
        antalBilletterSolgt = antalBilletterSolgt + 1;
        balance -= totalPris;

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