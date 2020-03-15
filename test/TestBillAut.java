import com.company.Billetautomat;
import com.company.IndkøbsKurv;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBillAut
{
    @Test
    public void indsætPenge20() {
        Billetautomat automat = new Billetautomat();
        automat.indsætPenge(20);
        assertEquals(20 , automat.getBalance());
    }

    @Test
    public void indsætPengeNEGATIV() {
        Billetautomat automat = new Billetautomat();
        automat.indsætPenge(-1);
        assertEquals(0 , automat.getBalance());
    }

    @Test
    public void KøbBørneBilletter() {
        IndkøbsKurv kurv = new IndkøbsKurv();
        kurv.addBillet('b', 5);
        assertEquals(kurv.getBørneBilletPris() * 5 , kurv.getKurvTotalPris());
    }

    @Test
    public void KøbVoksenBilletter() {
        IndkøbsKurv kurv = new IndkøbsKurv();
        kurv.addBillet('v', 5);
        assertEquals(kurv.getVoksenBilletPris() * 5 , kurv.getKurvTotalPris());
    }

    @Test
    public void KøbCykelBilletterNEGATIV() {
        IndkøbsKurv kurv = new IndkøbsKurv();
        kurv.addBillet('c', -2);
        assertEquals(0 , kurv.getKurvTotalPris());
    }

    @Test
    public void Retur() {
        //Tester om jeg får den rigtige mængde penge retur
        Billetautomat automat = new Billetautomat();
        IndkøbsKurv kurv = new IndkøbsKurv();

        automat.indsætPenge(100);
        assertEquals(100, automat.getBalance());
        kurv.addBillet('v', 4);
        automat.udskrivBillet(4, 0, 0, kurv.getKurvTotalPris());
        int retur = automat.getBalance();

        assertEquals(100 - kurv.getKurvTotalPris(), retur);
    }

    @Test
    public void TømKurv() {
        IndkøbsKurv kurv = new IndkøbsKurv();
        kurv.addBillet('c', 4);
        kurv.addBillet('v', 10);
        kurv.addBillet('b', 2);
        kurv.tømKurv();
        assertEquals(0 , kurv.getKurvTotalPris());
    }

    @Test
    public void test() {
        IndkøbsKurv kurv = new IndkøbsKurv();
        kurv.tømKurv();
        assertEquals(0 , kurv.getKurvTotalPris());
    }

}
