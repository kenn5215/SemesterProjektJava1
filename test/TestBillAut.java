import kapitel_04.Billetautomat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBillAut
{
    @Test
    public void prøve() {
        Billetautomat ba = new Billetautomat(10);
        assertEquals(10, ba.getBilletpris());
    }



    @Test
    public void prøve2() {
        Billetautomat ba = new Billetautomat(10);
        assertEquals(12, ba.getBilletpris());
    }
}
