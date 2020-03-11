package com.company;

import java.io.File;
import java.util.Date;

public class indkøbsKurv {
    private int voksenBilletAntal;
    private int børneBilletAntal;
    private int cykelBilletAntal;
    private int voksenBilletPris;
    private int børneBilletPris;
    private int cykelBilletPris;

    public indkøbsKurv (int voPris, int bøPris, int cyPris)
    {
        voksenBilletPris = voPris;
        børneBilletPris = bøPris;
        cykelBilletPris = cyPris;
        voksenBilletAntal = 0;
        børneBilletAntal = 0;
        cykelBilletAntal = 0;
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

    public int getVoksenPris()
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
