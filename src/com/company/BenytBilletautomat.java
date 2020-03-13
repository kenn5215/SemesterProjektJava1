package com.company;

import java.util.Scanner;

public class BenytBilletautomat {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Billetautomat automat = new Billetautomat();
        IndkøbsKurv kurv = new IndkøbsKurv();
        Translog translog = new Translog();

        System.out.println("============================================");
        System.out.println("| Hej velkommen til denne billet automat    |");
        System.out.println("|                                           |");

        int option = 1;
        while(option != 0) {
            System.out.println("============================================");
            System.out.println("Du har følgende muligheder:                 ");
            System.out.println("============================================");
            System.out.println("Tryk 1 for: Køb billetter                   ");
            System.out.println("********************************************");
            System.out.println("Tryk 2 for: Tjek din kurv                   ");
            System.out.println("********************************************");
            System.out.println("Tryk 3 for: Indsæt penge                    ");
            System.out.println("********************************************");
            System.out.println("Tryk 4 for: Tjek balancen                   ");
            System.out.println("********************************************");
            System.out.println("Tryk 5 for: Tjek ud                         ");
            System.out.println("********************************************");
            System.out.println("Tryk 6 for: Læs regler for at rejse med os  ");
            System.out.println("********************************************");
            System.out.println("Tryk 7 for: Admin adgang                    ");
            System.out.println("********************************************");
            System.out.println("Tryk 0 for: Afslut                          ");
            System.out.println("********************************************");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    KøbBillet(automat, kurv, translog);
                    break;

                case 2:
                    System.out.println("================================");
                    System.out.println("Du har " + kurv.getBørneBilletAntal() + " børnebilletter " + kurv.getBørneBilletPris()*kurv.getBørneBilletAntal() + " kr.");
                    System.out.println("Du har " + kurv.getVoksenBilletAntal() + " voksenbilletter " + kurv.getVoksenBilletPris()*kurv.getVoksenBilletAntal() + " kr.");
                    System.out.println("Du har " + kurv.getCykelBilletAntal() + " cykelbilletter " + kurv.getCykelBilletPris()*kurv.getCykelBilletAntal() + " kr.");
                    System.out.println("================================");
                    break;
                case 3:
                    System.out.println("Hvor mange penge vil du indsætte?");
                    int indsatPenge = scan.nextInt();
                    if(indsatPenge > 0){
                        automat.indsætPenge(indsatPenge);
                        translog.tilføjHandling(1, indsatPenge);
                        System.out.println("##############################################");
                        System.out.println("Du har indsat " + indsatPenge + " KR. i maskinen");
                        System.out.println("##############################################");

                    }else {
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Du skal indsætte et beløb som er større end 0");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    break;
                case 4:
                    System.out.println("##########################################");
                    System.out.println("Du har indsat i alt " + automat.getBalance() + " KR. i automatten");
                    System.out.println("##########################################");

                    break;
                case 5:
                    int balance = automat.getBalance();
                    int totalPris = kurv.getKurvTotalPris();

                    if(totalPris == 0){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Du har ikke købt noget endnu");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }else if(balance >= totalPris){
                        automat.udskrivBillet();
                        translog.tilføjHandling(2, totalPris);
                    }else {
                        int diff = totalPris-balance;
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Maskinen mangler " + diff + " KR. før du kan få dine billetter");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                    }
                    break;
                case 6:
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
                case 7:
                    automat.adminMenu(kurv, translog);
                    break;
                case 0:
                    System.out.println("============================================");
                    System.out.println("Tak og farvel!");
                    System.out.println("============================================");
                    break;

            }
        }
        kurv.skrivTilFIl();
    }

    public static void KøbBillet(Billetautomat automat, IndkøbsKurv kurv, Translog translog){
        Scanner scan = new Scanner(System.in);
        int option = 1;
        while (option != 0) {
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

            while (!scan.hasNextInt()){
                scan.next();
                System.out.println("Du skal indtaste et tal");
            }
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
