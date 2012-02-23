/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Logiikka.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Luokassa testataan pelin toimintaa
 * @author karoliee
 */
public class PeliTest {

    Peli muistipeli;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        muistipeli = new Peli();
        muistipeli.setPelaaja2("maija");
    }

    @Test
    public void kaksiKorttiaOnKaannettynaSaadaanVaihdettua() {
        muistipeli.setKaksiKorttiaOnKaannettyna(true);
        assertTrue(muistipeli.getKaksiKorttiaOnKaannettyna());
    }

    @Test
    public void yhdenKortinKaannonJalkeenKaksiKorttiaEiOleKaannettyna() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(4);
        muistipeli.kaannaKortti(1, false);
        assertFalse(muistipeli.getKaksiKorttiaOnKaannettyna());
    }

    @Test
    public void kahdenKortinKaannonJalkeenKaksiKorttiaOnKaannettyna() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(4);
        muistipeli.kaannaKortti(1, false);
        muistipeli.kaannaKortti(2, false);
        assertTrue(muistipeli.getKaksiKorttiaOnKaannettyna());
    }

    @Test
    public void listaSekoittaaItsensa() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(11);
    }

    @Test
    public void listaSekoittaaMyosYhdenKortin() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(1);
    }

    @Test
    public void kaikillaKorteillaOnTasanYksiArvo() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(6);
        assertEquals(6, muistipeli.getArvojenMaara(), 0.001);

    }

    @Test
    public void kortitOvatSamojaKunNiidenPitaisikinOlla() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(2);
        assertTrue(muistipeli.testaaOvatkoKortitSamoja(0, 1));
    }

    @Test
    public void kortitEivatOleSamojaKunNiidenEiPitaisiOlla() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        assertFalse(muistipeli.testaaOvatkoKortitSamoja(0, 1));
    }

    @Test
    public void ensimmaisenaKaannettyKorttiOnEnsimmainenKaannetty() {
        assertEquals(muistipeli.kaannaKortti(6, false), "Oli ensimmäinen kortti");

    }

    @Test
    public void huomataanJosKorttiaPainetaanKaksiKertaa() {
        muistipeli.kaannaKortti(2, false);
        assertEquals(muistipeli.kaannaKortti(2, false), "Painoit samaa korttia!");

    }

    @Test
    public void kortinArvoPalautetaanOikeanaLukuna() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 3; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        assertEquals(muistipeli.getKortinArvo(1), 2);
    }

    @Test
    public void kortinArvoPalautetaanOikeanaArvoMerkkijonona() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 3; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        assertEquals(muistipeli.getKortinArvoMerkkiJonona(1), "2");
    }

    @Test
    public void ensimmaiseksiKaannetyllaKortillaKortillaOnOikeaJarjestysNumero() {
        muistipeli.kaannaKortti(2, false);
        assertEquals(muistipeli.getEnsimmaisenKortinJarjestysnumero(), 2);

    }

    @Test
    public void toiseksiKaannetyllaKortillaKortillaOnOikeaJarjestysNumero() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        muistipeli.kaannaKortti(1, false);
        muistipeli.kaannaKortti(2, false);
        assertEquals(muistipeli.getToisenKortinJarjestysnumero(), 2);

    }

    @Test
    public void kortitTunnistetaanSamoiksiKunKortitOvatSamoja() {
        ArrayList<Integer> korteillaSamatArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            korteillaSamatArvot.add(6);
        }
        muistipeli.setKorttienArvot(korteillaSamatArvot);
        muistipeli.kaannaKortti(3, false);
        assertEquals(muistipeli.kaannaKortti(5, false), "Kortit olivat samoja");

    }

    @Test
    public void kortitTunnistetaanEriKorteiksiKunKortitEivatOleSamoja() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        muistipeli.kaannaKortti(3, false);
        assertEquals(muistipeli.kaannaKortti(5, false), "Kortit eivät olleet samoja");

    }

    @Test
    public void nimellinenVastapelaajaSaadaanTehtya() {
        assertEquals(muistipeli.getPelaaja2().getPelaajanNimi(), "maija");

    }
    @Test
    public void pelinAlussaOnEnsimmaisenPelaajanVuoro() {
        assertTrue(muistipeli.getOnkoEnsimmaisenPelaajanVuoro());

    }
    @Test
    public void ensimmainenPelaajaPelaaEnsinKaksinpelissa() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(4);
        muistipeli.kaannaKortti(1, true);
        muistipeli.kaannaKortti(2, true);
        assertEquals(muistipeli.getPelaaja1().getYritystenMaara(), 1, 0.001);

    }
    @Test
    public void toinenPelaajaEiPelaaEnsinKaksinpelissa() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(4);
        muistipeli.kaannaKortti(1, true);
        muistipeli.kaannaKortti(2, true);
        assertEquals(muistipeli.getPelaaja2().getYritystenMaara(), 0, 0.001);

    }
    @Test
    public void ensimmaisenPelaajanJalkeenOnToisenPelaajanVuoroKaksinpelissa() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(6);
        muistipeli.kaannaKortti(1, true);
        muistipeli.kaannaKortti(2, true);
        muistipeli.kaannaKortti(3, true);
        muistipeli.kaannaKortti(4, true);
        assertEquals(muistipeli.getPelaaja2().getYritystenMaara(), 1, 0.001);

    }
    @Test
    public void ensimmaisenPelaajanJalkeenEiOleEnaaEnsimmaisenPelaajanVuoroKaksinpelissa() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(6);
        muistipeli.kaannaKortti(1, true);
        muistipeli.kaannaKortti(2, true);
        muistipeli.kaannaKortti(3, true);
        muistipeli.kaannaKortti(4, true);
        assertEquals(muistipeli.getPelaaja1().getYritystenMaara(), 1, 0.001);

    }
}