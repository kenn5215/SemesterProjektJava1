package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
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

    public void læsFraFil(){

        try {
            transAktion temp = new transAktion();
            BufferedReader reader = new BufferedReader(new FileReader("translog"));
            String currentLine;
            String[] filelog = new String[100];
            int i = 0;

            while ((currentLine = reader.readLine()) != null){
                filelog[i] = currentLine;
                i++;
            }

        }catch (Exception u){
            System.out.println("Kunne ikke læse filen");
            u.printStackTrace();
        }


    }

    public void skrivTilFil(){

        File translog = new File("translog");
        try {
            PrintWriter pw = new PrintWriter(translog);
            for (transAktion elem_ : log) {
                pw.println(elem_.dato.toString() + ": ");
                if (elem_.handling == 1) {
                    pw.println("Blev der indsat: " + elem_.beløb + " DKK.");
                } else if (elem_.handling == 2) {
                    pw.println("Blev der udskrevet billetter til en værdi af " + elem_.beløb + " DKK. ");
                } else if (elem_.handling == 3) {
                    pw.println("Blev der returneret " + elem_.beløb + " DKK. ");
                }
            }
            pw.close();

        }catch (Exception u){
            System.out.println("Kunne ikke skrive til filen");
        }
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
