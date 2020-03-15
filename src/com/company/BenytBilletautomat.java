package com.company;

import java.util.Scanner;

public class BenytBilletautomat {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Billetautomat automat = new Billetautomat();
        IndkøbsKurv kurv = new IndkøbsKurv();
        Translog translog = new Translog();

        translog.læsFraFil();

        System.out.println("============================================");
        System.out.println("| Hej velkommen til denne billet automat    |");
        System.out.println("|                                           |");

        int option = 5;
        while(option != 0) {
            System.out.println("============================================");
            System.out.println("Du har følgende muligheder:                 ");
            System.out.println("============================================");
            System.out.println("Tryk 1 for: Køb billetter                   ");
            System.out.println("********************************************");
            System.out.println("Tryk 2 for: Se din kurv                     ");
            System.out.println("********************************************");
            System.out.println("Tryk 3 for: Indsæt penge                    ");
            System.out.println("********************************************");
            System.out.println("Tryk 4 for: Tjek balancen                   ");
            System.out.println("********************************************");
            System.out.println("Tryk 5 for: Tjek ud                         ");
            System.out.println("********************************************");
            System.out.println("Tryk 6 for: Returpenge                      ");
            System.out.println("********************************************");
            System.out.println("Tryk 7 for: Læs regler for at rejse med os  ");
            System.out.println("********************************************");
            System.out.println("Tryk 8 for: Admin adgang                    ");
            System.out.println("********************************************");
            System.out.println("Tryk 0 for: Afslut                          ");
            System.out.println("********************************************");
            while (!scan.hasNextInt()){
                scan.next();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Du skal indtaste et tal");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            option = scan.nextInt();
            switch (option) {
                case 1:
                    KøbBillet(automat, kurv, translog);
                    break;
                case 2:
                    if(kurv.getKurvTotalPris()==0)
                    {
                        System.out.println("Din kurv er tom.");
                    }
                    else
                    {
                        kurv.printKurv();

                        System.out.println("============================================");
                        System.out.println("Tryk 1 for: Tøm kurv                        ");
                        System.out.println("============================================");
                        System.out.println("Tryk 0 for: Gå retur                        ");
                        System.out.println("============================================");
                        while (!scan.hasNextInt()){
                            scan.next();
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("Du skal indtaste et tal");
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        int tømKurvJaTak = scan.nextInt();
                        if(tømKurvJaTak == 1)
                        {
                            kurv.tømKurv();
                        }
                    }
                    break;
                case 3:
                    System.out.println("################################");
                    System.out.println("Hvor mange penge vil du indsætte?");
                    System.out.println("################################");
                    while (!scan.hasNextInt()){
                        scan.next();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Du skal indtaste et tal");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    int indsatPenge = scan.nextInt();
                    automat.indsætPenge(indsatPenge);
                    if (indsatPenge > 0){
                        translog.tilføjHandling(1, indsatPenge);
                    }
                    break;
                case 4:
                    System.out.println("##########################################");
                    System.out.println("Du har " + automat.getBalance() + " KR. i automatten");
                    System.out.println("##########################################");

                    break;
                case 5:
                    int balance = automat.getBalance();
                    int totalPris = kurv.getKurvTotalPris();
                    int diff = totalPris-balance;

                    if(totalPris == 0){
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Du har ikke lagt noget i din kurv endnu");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }else if(balance >= totalPris){
                        automat.udskrivBillet(kurv.getBilletAntal('v'), kurv.getBilletAntal('b'), kurv.getBilletAntal('c'),kurv.getKurvTotalPris());
                        kurv.tømKurv();
                        translog.tilføjHandling(2, totalPris);
                        if (automat.getBalance()>0)
                        {
                            System.out.println("============================================");
                            System.out.println("Du har +"+automat.getBalance()+" DKK tilbage");
                            System.out.println("============================================");
                            System.out.println("Tryk 1 for: returpenge");
                            System.out.println("============================================");
                            System.out.println("Tryk 0 for: fortsætte med dit køb");
                            System.out.println("============================================");
                            while (!scan.hasNextInt()){
                                scan.next();
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                System.out.println("Du skal indtaste et tal");
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            }
                            int returJaTak = scan.nextInt();
                            if (returJaTak==1)
                            {
                                automat.udskrivReturPenge();

                                System.out.println("Tryk 0 for at gå retur");
                                while (!scan.hasNextInt()){
                                    scan.next();
                                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                    System.out.println("Du skal indtaste et tal");
                                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                }
                                returJaTak = scan.nextInt();
                            }
                        }
                    }else {

                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Maskinen mangler " + diff + " KR. før du kan få dine billetter");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                    }
                    break;
                case 6:
                    if (automat.getBalance()>0)
                    {
                        System.out.println("============================================");
                        System.out.println("Tryk 1 for: returpenge");
                        System.out.println("============================================");
                        System.out.println("Tryk 0 for: fortsætte med dit køb");
                        System.out.println("============================================");
                        while (!scan.hasNextInt()){
                            scan.next();
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("Du skal indtaste et tal");
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        int returJaTak = scan.nextInt();
                        if (returJaTak==1)
                        {
                            automat.udskrivReturPenge();

                            System.out.println("Tryk 0 for at gå retur");
                            while (!scan.hasNextInt()){
                                scan.next();
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                System.out.println("Du skal indtaste et tal");
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            }
                            returJaTak = scan.nextInt();
                        }
                    }
                    else
                    {
                        System.out.println("Der er ingen penge i automaten");
                    }
                    break;
                case 7:
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
                                "\nHvis du er i tvivl om du er\nsmittet så undgå at rejse med os");
                        System.out.println("============================================\n");
                        System.out.println("Tryk 0 for at gå tilbage\n");
                        while (!scan.hasNextInt()){
                            scan.next();
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("Du skal indtaste et tal");
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        }
                        tilbage = scan.nextInt();
                    }
                    break;
                case 8:
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
            System.out.println("Tryk for at tilføje til kurv:               ");
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
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("Du skal indtaste et tal");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            option = scan.nextInt();
            switch (option) {
                case 1:
                    System.out.println("============================");
                    System.out.println("Hvor mange Børnebilletter?");
                    System.out.println("============================");
                    while (!scan.hasNextInt()){
                        scan.next();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Du skal indtaste et tal");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    int antalBørnebilletter = scan.nextInt();
                    kurv.addBillet('b', antalBørnebilletter);
                    break;
                case 2:
                    System.out.println("============================");
                    System.out.println("Hvor mange voksenbilletter?");
                    System.out.println("============================");
                    while (!scan.hasNextInt()){
                        scan.next();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Du skal indtaste et tal");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    int antalVoksenbilletter = scan.nextInt();
                    kurv.addBillet('v', antalVoksenbilletter);
                    break;
                case 3:
                    System.out.println("============================");
                    System.out.println("Hvor mange cykelbilletter?");
                    System.out.println("============================");
                    while (!scan.hasNextInt()){
                        scan.next();
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("Du skal indtaste et tal");
                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                    int antalCykelbilletter = scan.nextInt();
                    kurv.addBillet('c', antalCykelbilletter);
                    break;
            }
        }
    }

}
