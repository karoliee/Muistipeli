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
    public void konstruktoriAntaaOikeanNimen() {
        assertEquals(pelaaja.getPelaajanNimi(), "matti");
    }

    @Test
    public void pelaajanNimenVaihto() {
        pelaaja.nimenVaihto("maija");
        assertEquals(pelaaja.getPelaajanNimi(), "maija");
    }

    @Test
    public void konstruktorissaEiOleLoydettyjaKorttipareja() {
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void parinLoytaminenLisaaLoydettyjenKorttiparienMaaraa() {
        pelaaja.loydettyjenKorttiparienMaaranKasvu();
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 1, vertailuTarkkuus);
    }

    @Test
    public void loydettyjenParienNollausKunEiOleLoydettyjaPareja() {
        pelaaja.loydettyjenKorttiparienMaaranNollaus();
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void loydettyjenParienNollausKunOnLoydettyjaPareja() {
        pelaaja.loydettyjenKorttiparienMaaranKasvu();
        pelaaja.loydettyjenKorttiparienMaaranNollaus();
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void konstruktorissaEiOleYrityksia() {
        assertEquals(pelaaja.getYritystenMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void yritysLisaaLoydettyjenYritystenMaaraa() {
        pelaaja.yritystenMaaranKasvu();
        assertEquals(pelaaja.getYritystenMaara(), 1, vertailuTarkkuus);
    }

    @Test
    public void yritystenNollausKunEiOleYrityksia() {
        pelaaja.yritystenMaaranNollaus();
        assertEquals(pelaaja.getYritystenMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void yritystenNollausKunOnYrityksia() {
        pelaaja.yritystenMaaranKasvu();
        pelaaja.yritystenMaaranNollaus();
        assertEquals(pelaaja.getYritystenMaara(), 0, vertailuTarkkuus);
    }
}
