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
                                translog.tilføjHandling(3, automat.getBalance());
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
                            translog.tilføjHandling(3, automat.getBalance());
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
                    adminmenu(automat, kurv, translog);
                    break;
                case 0:
                    System.out.println("============================================");
                    System.out.println("Tak og farvel!");
                    System.out.println("============================================");
                    break;

            }
        }
        translog.skrivTilLogFil();
        kurv.skrivTilStartFil();
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

    public static void adminmenu (Billetautomat automat,IndkøbsKurv kurv, Translog translog)
    {
        Scanner scan = new Scanner(System.in);
        automat.loginOplysninger();

        System.out.println("Der er kun adgang for installatører");
        //Brugernavn
        System.out.println("Skriv dit brugernavn: (Brugernavn og kodeord er i \"LoginOplysninger\" ovre til venstre på skærmen");
        String indtastetBrugernavn =scan.next();
        //Kodeord
        System.out.println("Skriv dit kodeord");
        String indtastetKodeord = scan.next();

        //Tjekker om login oplysninger stemmer overens.
        if (automat.checkLogin(indtastetBrugernavn,indtastetKodeord)) {
            int option = 1;
            while (option != 0) {
                System.out.println("=========================================================");
                System.out.println("Du har følgende muligheder:                 ");
                System.out.println("=========================================================");
                System.out.println("Tryk 1 for: Ændre billetprisen              ");
                System.out.println("*********************************************************");
                System.out.println("Tryk 2 for: For manuelt at sætte maskinens balance.      ");
                System.out.println("*********************************************************");
                System.out.println("Tryk 3 for: Check totalt antal solgt                       ");
                System.out.println("*********************************************************");
                System.out.println("Tryk 4 for: Print log                       ");
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
                        int nyBalance = scan.nextInt();
                        automat.setBalance(nyBalance);
                        break;
                    case 3:
                        System.out.println("Der er blevet solgt "+automat.getAntalBilletterSolgt()+ " billetter til en totalpris af " +automat.getTotaltBeløbSolgt()+" DKK.");
                        break;
                    case 4:
                        while (option !=0) {
                            System.out.println("=================================================");
                            System.out.println("Du har nu følgende muligheder");
                            System.out.println("=================================================");
                            System.out.println("Tryk 1 for at printe alle handlinger siden sidst");
                            System.out.println("*************************************************");
                            System.out.println("Tryk 2 for at printe alle handlinger der håndterede beløb over x DKK");
                            System.out.println("*************************************************");
                            System.out.println("Tryk 3 for at printe en bestemt type handlinger   ");
                            System.out.println("*************************************************");
                            System.out.println("Tryk 4 for at printe alle handlinger maskinen har foretaget siden opsætning");
                            System.out.println("*************************************************");
                            System.out.println("Tryk 0 for at gå retur");
                            System.out.println("*************************************************");
                            while (!scan.hasNextInt()) {
                                scan.next();
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                System.out.println("Du skal indtaste et tal");
                                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                            }
                            option = scan.nextInt();
                            switch (option) {
                                case 1:
                                    translog.printAlleLog();
                                    break;
                                case 2:
                                    System.out.println("======================================================");
                                    System.out.println("Indtast den værdi du vil have printet handlinger over");
                                    System.out.println("=======================================================");
                                    while (!scan.hasNextInt()) {
                                        scan.next();
                                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                        System.out.println("Du skal indtaste et tal");
                                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                    }
                                    //ingenting
                                    int maxBeløb = scan.nextInt();
                                    translog.printBeløbOver(maxBeløb);
                                    break;
                                case 3:
                                    System.out.println("======================================================");
                                    System.out.println("Indtast hvilken type handlinger du vil have printet");
                                    System.out.println("======================================================");
                                    System.out.println("Tryk 1 for indbetalinger                              ");
                                    System.out.println("******************************************************");
                                    System.out.println("Tryk 2 for billeter udskrevet                         ");
                                    System.out.println("******************************************************");
                                    System.out.println("Tryk 3 for udbetalinger                                 ");
                                    System.out.println("******************************************************");
                                    System.out.println("Tryk 0 for at gå retur");
                                    System.out.println("*************************************************");

                                    while (!scan.hasNextInt()) {
                                        scan.next();
                                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                        System.out.println("Du skal indtaste et tal");
                                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
                                    }
                                    int typeHandling = scan.nextInt();
                                    if (typeHandling <= 3 && typeHandling >= 1) {
                                        translog.printPerHandling(typeHandling);
                                    }
                                    break;
                                case 4:
                                    translog.skrivTilLogFil();


                                    System.out.println(translog.læsFraFil());
                                    break;
                            }

                        }
                        option = 1;
                        break;
                }
            }
        }
        else{
            System.out.println("Forkert brugernavn eller adgangskode....");
            System.out.println("Tryk 0 for at gå retur");
            String ignorer = scan.next();
        }
    }
}
