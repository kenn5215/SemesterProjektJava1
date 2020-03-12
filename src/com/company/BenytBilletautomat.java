package com.company;

import javax.swing.*;
import java.util.Scanner;

public class BenytBilletautomat {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Billetautomat automat = new Billetautomat();
        IndkøbsKurv kurv = new IndkøbsKurv();

        System.out.println("============================================");
        System.out.println("| Hej velkommen til denne billet automat    |");
        System.out.println("============================================\n");

        int option = 1;
        while(option != 0) {
            System.out.println("============================================");
            System.out.println("Du har følgende muligheder:                 ");
            System.out.println("============================================");
            System.out.println("Tryk 1 for: Køb billetter                   ");
            System.out.println("********************************************");
            System.out.println("Tryk 2 for: Tjek din kurv                   ");
            System.out.println("********************************************");
            System.out.println("Tryk 3 for: Læs regler for at rejse med os  ");
            System.out.println("********************************************");
            System.out.println("Tryk 0 for: Afslut                          ");
            System.out.println("********************************************");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    KøbBillet(automat, kurv);
                    break;

                case 2:
                    kurv.getKurvTotalPris();
                    break;
                case 3:
                    int tilbage = 1;
                    while (tilbage != 0) {
                        System.out.println("============================================");
                        System.out.println("Børnebillet gælder for børn mellem 4-12 år");
                        System.out.println("============================================");
                        System.out.println("Hvis du køber en voksen billet kan du\ntage et barn under 4 år med gratis");
                        System.out.println("============================================");
                        System.out.println("Maks én cykel pr. person.");
                        System.out.println("============================================");
                        System.out.println("Grundet Covid19 vil vi bede alle om\nikke at sidde for tæt. " +
                                "\nHvis du er i tvivl om du er\nsmittet så ungå at rejse med os");
                        System.out.println("============================================\n");
                        System.out.println("Tryk 0 for at gå tilbage\n");
                        tilbage = scan.nextInt();
                    }
                    break;
                case 0:
                    System.out.println("============================================");
                    System.out.println("Tak og farvel!");
                    System.out.println("============================================");
                    break;

            }
        }
    }

    public static void KøbBillet(Billetautomat automat, IndkøbsKurv kurv){
        Scanner scan = new Scanner(System.in);
        int option = 1;
        while (option != 0) {
            System.out.println("\n");
            System.out.println("============================================");
            System.out.println("Du kan vælge imellem følgende billetter:    ");
            System.out.println("============================================");
            System.out.println("Tryk 1 for: BørneBillet " + kurv.getBørneBilletPris() + "KR.");
            System.out.println("********************************************");
            System.out.println("Tryk 2 for: VoksenBillet " + kurv.getVoksenBilletPris() + "KR.");
            System.out.println("********************************************");
            System.out.println("Tryk 3 for: Cykelbillet " + kurv.getCykelBilletPris() + "KR.");
            System.out.println("********************************************");
            System.out.println("Tryk 0 for: Tilbage                         ");
            System.out.println("********************************************");

            option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Hvor mange Børnebilletter?");
                    int antalBørnebilletter = scan.nextInt();
                    kurv.addBillet('b', antalBørnebilletter);

                    break;
                case 2:
                    System.out.println("Hvor mange voksenbilletter?");
                    int antalVoksenbilletter = scan.nextInt();
                    kurv.addBillet('v', antalVoksenbilletter);


                    break;
                case 3:
                    System.out.println("Hvor mange cykelbilletter?");
                    int antalCykelbilletter = scan.nextInt();
                    kurv.addBillet('c', antalCykelbilletter);

                    break;
            }
        }
    }

}
