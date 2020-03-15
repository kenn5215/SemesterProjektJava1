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
        loginOplysninger();
    }

    public void loginOplysninger(){
        Scanner scan = new Scanner(System.in);
        File login = new File("LoginOplysninger");

        //Her tjekker jeg længden på min fil og hvis filen er 0 må det være første gang maskinen sættes i brug
        //og der burde oprettes en bruger
        if(login.length() == 0){
            System.out.println("Denne maskine er helt ny og der ikke oprettet en bruger. Opret venligst en bruger");
            System.out.println("Vælg dit brugernavn:");
            brugernavn = scan.next();
            System.out.println("vælg dit kodeord:");
            kodeord = scan.next();

            //Bruger try catch for at fange hvis der skulle komme fejl og kører videre med programmet så det ikke crasher
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
            //Hvis filen ikke er tom skal der bare læses brugernavn og kodeord fra filen og sættes  lig med variablerne så de kan bruges
            //til at tjekke login adgang
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

        if(beløb > 0 && beløb <= 1000){
            balance += beløb;
            System.out.println("##############################################");
            System.out.println("Du har nu " + balance + " KR. i maskinen");
            System.out.println("##############################################");
        }else {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("vi kan kun tage imod positive beløb og vi tager højest imod 1000kr af gangen");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

}