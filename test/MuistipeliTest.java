/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Kayttoliittyma.*;
import Logiikka.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author karoliee
 */
public class MuistipeliTest {

    Logiikka logiikka;
    Pelaaja pelaaja;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        logiikka = new Logiikka();
        pelaaja = logiikka.getPelaaja();
    }

    @Test
    public void nimenTarkistus() {
        assertEquals(pelaaja.pelaajanNimi(), "matti");
    }
    @Test
    public void eiYhtaanKorttipariaLoydetty() {
        assertEquals(pelaaja.arvattujenKorttiparienMaara(), 0, vertailuTarkkuus);
    }
    @Test
    public void yksiKorttipariLoydetty() {
        pelaaja.arvattujenKorttienMaaranKasvu();
        assertEquals(pelaaja.arvattujenKorttiparienMaara(), 1, vertailuTarkkuus);
    }
}
