package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Translog {

    /*Vi har i vores transaktionslog valgt at indføre 3 handlinger.
     *1: Indsæt penge. 2: Udskriv billet. 3: Udbetal returbeløb.
     */
    class transAktion {
        //variable
        int handling;
        int beløb;

        Date dato;
    }




    ArrayList<transAktion> log = new ArrayList<transAktion>();

    public void tilføjHandling(int handling, int beløb) {
        transAktion temp = new transAktion();

        temp.handling = handling;
        temp.beløb = beløb;
        temp.dato = new Date();
        log.add(temp);
    }



    public StringBuilder læsFraFil(){
        //Opretter stringbuilder til at læse alt dataten fra filen translog
        StringBuilder logFile = new StringBuilder();
        //Opretter en fil translog hvis denne ikke er oprettet før
        File translog = new File("translog");

        //tjekker om filen existere ellers vil jeg ikke læse fra denne fil
        if(translog.exists()){
            try {
                String currentLine;
                BufferedReader reader = new BufferedReader(new FileReader("translog"));

                while ((currentLine = reader.readLine()) != null){
                    logFile.append(currentLine);
                    logFile.append("\n");
                }

            }catch (Exception u){
                System.out.println("Kunne ikke læse filen");
                u.printStackTrace();
            }
        }



        return logFile;
    }


    public void skrivTilLogFil(){
        //Opretter en stringbuilder til at indeholde alt vores data for tidligere logs som er skrevet til translog
        StringBuilder logFile = new StringBuilder();
        //Opretter en translog fil hvis ikke allerede denne er oprettet.
        File translog = new File("translog");

        //Kalder min læsfrafil som retunere en stringbuilder som indeholder data fra translog
        logFile = læsFraFil();

        //foreach løkke til at skrive fra vores arraylist af transaktioner til logfile, her bliver der skrevet afhængigt af hvilken handling
        for (transAktion elem_ : log) {
            logFile.append(elem_.dato.toString()).append(": ");
            if (elem_.handling == 1) {
                logFile.append("Blev der indsat: ").append(elem_.beløb).append(" DKK.\n");
            } else if (elem_.handling == 2) {
                logFile.append("Blev der udskrevet billetter til en værdi af ").append(elem_.beløb).append(" DKK.\n");
            } else if (elem_.handling == 3) {
                logFile.append("Blev der returneret ").append(elem_.beløb).append(" DKK.\n");
            }
        }

        //Alt data samlet i logfile skal nu skrives til vores fil og her bruger jeg try catch, hvis der sker en fejl får vi advide hvad der skete og programmet kan fortsætte
        try {
            PrintWriter pw = new PrintWriter(translog);
            pw.println(logFile);
            pw.close();

        } catch (Exception u) {
            u.printStackTrace();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Kunne ikke skrive til logfilen");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        //Når jeg har skrevet til filen sletter jeg indholdet i arraylisten log, for ikke har skrive det samme flere gange hvis jeg kalder funktionen igen
        log.clear();

    }

    public void printAlleLog() {
        Date currentDate = new Date();
        System.out.println("=====Transaktioner pr. " + currentDate.toString());
        for (transAktion elem_ : log) {
            System.out.print(elem_.dato.toString() + ": ");
            if (elem_.handling == 1) {
                System.out.println("Blev der indsat: " + elem_.beløb + " DKK.");
            } else if (elem_.handling == 2) {
                System.out.println("Blev der udskrevet billetter til en værdi af " + elem_.beløb + " DKK. ");
            } else if (elem_.handling == 3) {
                System.out.println("Blev der returneret " + elem_.beløb + " DKK. ");
            }
        }
        System.out.println("=====");
    }

    public void printPerHandling(int handling) {
        Date currentDate = new Date();
        System.out.println("=====Transaktioner pr. " + currentDate.toString());
        for (transAktion elem_ : log) {
            if (elem_.handling == handling) {
                System.out.print(elem_.dato.toString() + ": ");
                if (elem_.handling == 1) {
                    System.out.println("Blev der indsat: " + elem_.beløb + " DKK.");
                } else if (elem_.handling == 2) {
                    System.out.println("Blev der udskrevet billetter til en værdi af " + elem_.beløb + " DKK. ");
                } else if (elem_.handling == 3) {
                    System.out.println("Blev der returneret " + elem_.beløb + " DKK. ");
                }
            }
        }
        System.out.println("=====");


    }

    public void printBeløbOver(int beløb) {
        Date currentDate = new Date();
        System.out.println("=====Transaktioner pr. " + currentDate.toString());
        for (transAktion elem_ : log) {
            if (elem_.beløb > beløb) {
                System.out.print(elem_.dato.toString() + ": ");
                if (elem_.handling == 1) {
                    System.out.println("Blev der indsat: " + elem_.beløb + " DKK.");
                } else if (elem_.handling == 2) {
                    System.out.println("Blev der udskrevet billetter til en værdi af " + elem_.beløb + " DKK. ");
                } else if (elem_.handling == 3) {
                    System.out.println("Blev der returneret " + elem_.beløb + " DKK. ");
                }
            }
        }
        System.out.println("=====");
    }
}
