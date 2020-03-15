package com.company;

import java.io.*;
import java.util.Scanner;

public class IndkøbsKurv {
    private int voksenBilletAntal;
    private int børneBilletAntal;
    private int cykelBilletAntal;
    private int voksenBilletPris;
    private int børneBilletPris;
    private int cykelBilletPris;



    public IndkøbsKurv()
    {
        voksenBilletAntal = 0;
        børneBilletAntal = 0;
        cykelBilletAntal = 0;
        voksenBilletPris = 0;
        børneBilletPris = 0;
        cykelBilletPris = 0;
        StartFilTjek();
    }

    private void GetIndstillinger(Boolean erTom, File startFil){
        Scanner scan = new Scanner(System.in);
        //Hvis Filen startFil er tom skal der indstilles priser på billetterne
        if(erTom){
            System.out.println("Skriv prisen for en børnebillet:");
            børneBilletPris = scan.nextInt();
            System.out.println("Skriv prisen for en voksenbillet:");
            voksenBilletPris = scan.nextInt();
            System.out.println("Skriv prisen for en cykelbillet:");
            cykelBilletPris = scan.nextInt();
        }else{
            //Hvis filen ikke er tom skal vi nu læse fra den fil
            try{
                BufferedReader reader = new BufferedReader(new FileReader(startFil));

                String[] prisData = new String[3];

                //Læser de tre linjer fra startfilen og ligger dem i et string array
                for (int i = 0; i < 3; i++) {
                    prisData[i] = reader.readLine();
                }
                //kalder min funktion for at læse de dataer der står i filen
                for (int i = 0; i < 3; i++) {
                    LæsFraFil(i, prisData);
                }


            }catch (Exception u){
                System.out.println("Kunne ikke læse fra filen!");
                u.printStackTrace();
            }
        }

    }

    private void LæsFraFil(int linje, String[] prisData){
        int i = 0;
        //Her læser jeg dataen fra filen startfil og jeg ved at på linje 0 er børneprisen på linje 1 er voksenprisen osv.
        //Så derfor tjekker jeg hver plads på hver linje om jeg finder et tal og hvis jeg gør bliver det prisen
        while (prisData[linje].length() > i){
            while (Character.isDigit(prisData[linje].charAt(i))){

                switch (linje){
                    case 0:
                        børneBilletPris *= 10;
                        børneBilletPris += (prisData[linje].charAt(i)) -'0';
                        break;
                    case 1:
                        voksenBilletPris *= 10;
                        voksenBilletPris += (prisData[linje].charAt(i)) -'0';
                        break;
                    case 2:
                        cykelBilletPris *= 10;
                        cykelBilletPris += (prisData[linje].charAt(i)) -'0';
                        break;
                }
                i++;
            }
            i++;
        }
    }

    private void StartFilTjek(){
        //Opretter vores filobjekt kaldet startfil
        //Denne fil bruges til at indeholde alle startindstillingerne
        //Så når vi starter programmet vil vi gerne vide om der er lavet indstillinger til maskinen
        File startFil = new File("startFil");

        //Tjekker om filen er tom
        boolean erTom;
        erTom = startFil.length() == 0;

        if (erTom) {
            try {
                //Hvis filen er tom vil vi gerne indstille maskinen. Her kalder vi GetIndstillinger og skriver priserne til filen.
                //Når man arbejder med filer skal man bruge en try - catch, hvis noget går galt, som filen ikke kan skrives til.
                PrintWriter pw = new PrintWriter(startFil);
                GetIndstillinger(true , startFil);
                pw.println("Børnebillet = " + børneBilletPris + " KR.");
                pw.println("voksenbillet = " + voksenBilletPris + " KR.");
                pw.println("Cykelbillet = " + cykelBilletPris + " KR.");
                pw.close();
            } catch (Exception u) {
                System.out.println("Kunne ikke skrive til startFilen");
                u.printStackTrace();
            }
        }else {
            GetIndstillinger(false, startFil);
        }
    }

    public void addBillet(char x, int antal)
    {
        if(antal<1 || antal>30)
        {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Antal billetter ikke accepteret.");
            System.out.println("Du kan kun købe imellem 1 og 30.");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            return;
        }
        if(x=='v')
        {
            voksenBilletAntal += antal;
        }
        else if(x=='b')
        {
            børneBilletAntal += antal;
        }
        else if(x=='c')
        {
            cykelBilletAntal += antal;
        }
    }

    public int getBilletAntal(char type)
    {
        if(type=='v')
        {
            return voksenBilletAntal;
        }
        else if(type=='b')
        {
            return børneBilletAntal;
        }
        else if(type=='c')
        {
            return cykelBilletAntal;
        }
        return 0;
    }

    public void printKurv(){
        System.out.println("##################################");
        System.out.println("Du har " + børneBilletAntal + " børnebilletter " + børneBilletPris * børneBilletAntal + " kr.");
        System.out.println("Du har " + voksenBilletAntal + " voksenbilletter " + voksenBilletPris * voksenBilletAntal + " kr.");
        System.out.println("Du har " + cykelBilletAntal + " cykelbilletter " + cykelBilletPris * cykelBilletAntal + " kr.\n");
        System.out.println("I alt    " + getKurvTotalPris() + " KR.");
        System.out.println("##################################");
    }

    public int getKurvTotalPris()
    {
        return (voksenBilletAntal*voksenBilletPris)+(børneBilletAntal*børneBilletPris) +
                (cykelBilletAntal*cykelBilletPris);
    }

    public int getVoksenBilletPris()
    {
        return voksenBilletPris;
    }
    public int getBørneBilletPris()
    {
        return børneBilletPris;
    }
    public int getCykelBilletPris()
    {
        return cykelBilletPris;
    }

    public void tømKurv()
    {
        if ((voksenBilletAntal+børneBilletAntal+cykelBilletAntal)==0)
        {
            System.out.println("Kurven er allerede tom.");
        }
        else
        {
            voksenBilletAntal = 0;
            børneBilletAntal = 0;
            cykelBilletAntal = 0;
        }
    }

    public void setBilletPris(int voPris, int bøPris, int cyPris)
    {
        voksenBilletPris = voPris;
        børneBilletPris = bøPris;
        cykelBilletPris = cyPris;
    }

    public void skrivTilFIl(){
        try (PrintWriter pw = new PrintWriter("startFil")) {
            pw.println("Børnebillet = " + børneBilletPris + " KR.");
            pw.println("voksenbillet = " + voksenBilletPris + " KR.");
            pw.println("Cykelbillet = " + cykelBilletPris + " KR.");
        }catch (Exception u){
            System.out.println("Kunne ikke skrive til startFilen");
            u.printStackTrace();
        }
    }

}
