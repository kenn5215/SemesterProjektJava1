package com.company;

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

        //String til at printe
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
