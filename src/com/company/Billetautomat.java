package com.company;

import java.io.*;
import java.util.Scanner;

public class Billetautomat {
    private int balance; // Hvor mange penge kunden p.t. har puttet i automaten
    private int antalBilletterSolgt; // Antal billetter automaten i alt har solgt
    private int totaltBeløbSolgt;
    private String brugernavn;
    private String kodeord;


    public Billetautomat() {
    }

    public void loginOplysninger(){
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




    public void setBalance(int nyBalance)
    {
        balance = nyBalance;
    }

    public int getBalance() {
        return balance;
    }

    public int getAntalBilletterSolgt()
    {
        return antalBilletterSolgt;
    }

    public int getTotaltBeløbSolgt()
    {
        return totaltBeløbSolgt;
    }

    public void udskrivBillet(int voksenAntal, int børneAntal, int cykelAntal, int totalPris) {
        antalBilletterSolgt = antalBilletterSolgt + (voksenAntal+ børneAntal+ cykelAntal);
        totaltBeløbSolgt +=totalPris;

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

    public boolean checkLogin(String brugerGæt,String kodegæt)
    {
        if (brugerGæt.equals(brugernavn) && kodegæt.equals(kodeord)) {
            return true;
        }
        return false;
    }

    public void indsætPenge(int beløb) {

        if(beløb > 0){
            balance += beløb;
            System.out.println("##############################################");
            System.out.println("Du har nu " + balance + " KR. i maskinen");
            System.out.println("##############################################");
        }else {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Du skal indsætte et beløb som er større end 0");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

}