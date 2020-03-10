import com.company.Billetautomat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBillAut
{
    @Test
    public void prøve() {
        Billetautomat ba = new Billetautomat();
        assertEquals(40, ba.getBilletpris());
    }



    @Test
    public void prøve2() {
        Billetautomat ba = new Billetautomat();
        assertEquals(40, ba.getBilletpris());
    }
}
