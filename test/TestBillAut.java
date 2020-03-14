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
}
