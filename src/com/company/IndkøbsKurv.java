package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class IndkøbsKurv {
    private int voksenBilletAntal = 0;
    private int børneBilletAntal = 0;
    private int cykelBilletAntal = 0;
    private int voksenBilletPris = 0;
    private int børneBilletPris = 0;
    private int cykelBilletPris = 0;



    public IndkøbsKurv()
    {
        voksenBilletAntal = 0;
        børneBilletAntal = 0;
        cykelBilletAntal = 0;
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

                for (int i = 0; i < 3; i++) {
                    prisData[i] = reader.readLine();
                }

                for (int i = 0; i < prisData[0].length(); i++) {
                    while (Character.isDigit(prisData[0].charAt(i))){
                        børneBilletPris *= 10;
                        børneBilletPris += (prisData[0].charAt(i)) -'0';

                        i++;
                    }
                }

                for (int i = 0; i < prisData[1].length(); i++) {
                    while (Character.isDigit(prisData[1].charAt(i))){
                        voksenBilletPris *= 10;
                        voksenBilletPris += (prisData[1].charAt(i)) -'0';

                        i++;
                    }
                }

                for (int i = 0; i < prisData[2].length(); i++) {
                    while (Character.isDigit(prisData[2].charAt(i))){
                        cykelBilletPris *= 10;
                        cykelBilletPris += (prisData[2].charAt(i)) -'0';

                        i++;
                    }
                }

            }catch (Exception u){
                System.out.println("Kunne ikke læse fra filen!");
                u.printStackTrace();
            }
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
            System.out.println("Antal billetter ikke accepteret.");
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

    public void printKurv()
    {
        if ((voksenBilletAntal+børneBilletAntal+cykelBilletAntal)==0)
        {
            System.out.println("Kurven er tom.");
        }
        else
        {
            System.out.print("Kurv: ");
            if(voksenBilletAntal>0)
            {
                System.out.print(voksenBilletAntal+"x voksenbillet. ");
            }
            if (børneBilletAntal>0)
            {
                System.out.print(børneBilletAntal+"x børnebillet. ");
            }
            if (cykelBilletAntal>0){
                System.out.print(cykelBilletAntal+"x cykelbillet.");
            }
            System.out.println("");
            System.out.println("Totalpris: "+getKurvTotalPris()+" DKK");
        }
    }

    public void setBilletPris(int voPris, int bøPris, int cyPris)
    {
        voksenBilletPris = voPris;
        børneBilletPris = bøPris;
        cykelBilletPris = cyPris;
    }


}
