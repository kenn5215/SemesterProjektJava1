package com.company;

import java.io.*;
import java.util.Scanner;

public class Billetautomat {
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
            System.out.println("Denne maskine er helt ny og der ikke oprettet en bruger. Opret venligst en bruger");
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

        System.out.println("Der er kun adgang for installatører");
        //Brugernavn
        System.out.println("Skriv dit brugernavn:");
        String indtastetBrugernavn = scan.next();
        //Kodeord
        System.out.println("Skriv dit kodeord");
        String indtastetKodeord = scan.next();

        //Tjekker om login oplysninger stemmer overens.
        if (indtastetBrugernavn.equals(brugernavn) && indtastetKodeord.equals(kodeord)) {
            int option = 1;
            while (option != 0) {
                System.out.println("=========================================================");
                System.out.println("Du har følgende muligheder:                 ");
                System.out.println("=========================================================");
                System.out.println("Tryk 1 for: Ændre billetprisen              ");
                System.out.println("*********************************************************");
                System.out.println("Tryk 2 for: For manuelt at sætte maskinens balance.      ");
                System.out.println("*********************************************************");
                System.out.println("Tryk 3 for: Print log                       ");
                System.out.println("*********************************************************");
                System.out.println("Tryk 0 for: Afslut                          ");
                System.out.println("*********************************************************");
                while (!scan.hasNextInt()){
                    scan.next();
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("Du skal indtaste et tal");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                }
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
                        System.out.println("=================================================");
                        System.out.println("Indtast ny balance: (husk at reset'e efter endt test)");
                        System.out.println("=================================================");
                        while (!scan.hasNextInt()){
                            scan.next();
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("Du skal indtaste et tal");
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        balance = scan.nextInt();
                        break;
                    case 3:
                        translog.printAlleLog();
                        break;
                }
            }
        }else{
                System.out.println("Forkert brugernavn eller adgangskode....");
                System.out.println("Tryk 0 for at gå retur");
                String ignorer = scan.next();
            }
    }







    public void indsætPenge(int beløb) {
        balance = balance + beløb;
    }

    public void setBalance(int nyBalance)
    {
        balance = nyBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void udskrivBillet(int voksenAntal, int børneAntal, int cykelAntal, int totalPris) {
        antalBilletterSolgt = antalBilletterSolgt + (voksenAntal+ børneAntal+ cykelAntal);
        balance -= totalPris;             // Nulstil balance

        System.out.println("##########B##T##########");
        System.out.println("# Borgen Trafikselskab #");
        System.out.println("#                      #");
        System.out.println("#      Billet          #");
        if (voksenAntal>0)
        {
            System.out.println("#      "+voksenAntal+" x Voksen      #");
        }
        if (børneAntal>0)
        {
            System.out.println("#      "+børneAntal+" x Barn        #");
        }
        if (cykelAntal>0)
        {
            System.out.println("#      "+cykelAntal+" x Cykel       #");
        }
        System.out.println("#                      #");
        System.out.println("##########B##T##########");
        System.out.println("");
    }
    public void udskrivReturPenge()
    {
        System.out.println("########################");
        System.out.println("# "+ balance+" DKK               #");
        System.out.println("#                      #");
        System.out.println("#   Legitime           #");
        System.out.println("#     danske dollaz    #");
        System.out.println("#                      #");
        System.out.println("########################");
        System.out.println("");

        balance = 0;
    }

}