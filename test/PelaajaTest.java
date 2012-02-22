/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Logiikka.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Luokassa testataan pelaajan toimintaa
 * @author karoliee
 */
public class PelaajaTest {

    Peli muistipeli;
    Pelaaja pelaaja;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        muistipeli = new Peli();
        pelaaja = muistipeli.getPelaaja1();
    }

    @Test
    public void pelinPelaajallaEiOleAluksiNimea() {
        assertEquals(pelaaja.getPelaajanNimi(), "");
    }

    @Test
    public void pelaajanNimenVaihtoOnnistuu() {
        pelaaja.setPelaajanNimi("maija");
        assertEquals(pelaaja.getPelaajanNimi(), "maija");
    }

    @Test
    public void pelinAlussaPelaajallaEiOleLoydettyjaKorttipareja() {
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void parinLoytaminenLisaaLoydettyjenKorttiparienMaaraa() {
        pelaaja.loydettyjenKorttiparienMaaranKasvu();
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 1, vertailuTarkkuus);
    }

    @Test
    public void loydettyjenParienNollausKunEiOleLoydettyjaParejaOnnistuu() {
        pelaaja.loydettyjenKorttiparienMaaranNollaus();
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void loydettyjenParienNollausKunOnLoydettyjaParejaOnnistuu() {
        pelaaja.loydettyjenKorttiparienMaaranKasvu();
        pelaaja.loydettyjenKorttiparienMaaranNollaus();
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void pelinAlussaPelaajallaEiOleYrityksia() {
        assertEquals(pelaaja.getYritystenMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void yritysLisaaLoydettyjenYritystenMaaraa() {
        pelaaja.yritystenMaaranKasvu();
        assertEquals(pelaaja.getYritystenMaara(), 1, vertailuTarkkuus);
    }

    @Test
    public void yritystenNollausKunEiOleYrityksiaOnnistuu() {
        pelaaja.yritystenMaaranNollaus();
        assertEquals(pelaaja.getYritystenMaara(), 0, vertailuTarkkuus);
    }

    @Test
    public void yritystenNollausKunOnYrityksiaOnnistuu() {
        pelaaja.yritystenMaaranKasvu();
        pelaaja.yritystenMaaranNollaus();
        assertEquals(pelaaja.getYritystenMaara(), 0, vertailuTarkkuus);
    }
    @Test
    public void kaikkienTulostenNollausNollaaYritykset() {
        pelaaja.yritystenMaaranKasvu();
        pelaaja.loydettyjenKorttiparienMaaranKasvu();
        pelaaja.tulostenNollaus();
        assertEquals(pelaaja.getYritystenMaara(), 0, vertailuTarkkuus);
    }
    @Test
    public void kaikkienTulostenNollausNollaaLoydetytKorttiparit() {
        pelaaja.yritystenMaaranKasvu();
        pelaaja.loydettyjenKorttiparienMaaranKasvu();
        pelaaja.tulostenNollaus();
        assertEquals(pelaaja.getLoydettyjenKorttiparienMaara(), 0, vertailuTarkkuus);
    }
}