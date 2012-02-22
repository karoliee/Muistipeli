/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Logiikka.*;
import java.util.ArrayList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author karoliee
 */
public class PeliTest {

    Peli muistipeli;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        muistipeli = new Peli();
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
    public void kaikillaKorteillaOnArvo() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(6);
        assertEquals(6, muistipeli.getArvojenMaara(), 0.001);

    }

    @Test
    public void kortitOvatSamojaKunKortitOvatSamoja() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(2);
        assertTrue(muistipeli.testaaOvatkoKortitSamoja(0, 1));
    }

    @Test
    public void kortitEivatOleSamojaKunKortitEivatOleSamoja() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        assertFalse(muistipeli.testaaOvatkoKortitSamoja(0, 1));
    }

    @Test
    public void ensimmainenKorttiOnEnsimmainenKaannetty() {
        assertEquals(muistipeli.kaannaKortti(6, false), "Oli ensimmäinen kortti");

    }

    @Test
    public void korttiaPainettiinKaksiKertaa() {
        muistipeli.kaannaKortti(2, false);
        assertEquals(muistipeli.kaannaKortti(2, false), "Painoit samaa korttia!");

    }

    @Test
    public void kortillaOnOikeaArvo() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        assertEquals(muistipeli.getKortinArvo(1), 2);
    }

    @Test
    public void kortillaOnOikeaArvoMerkkijonona() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for (int i = 1; i <= 8; i++) {
            korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        assertEquals(muistipeli.getKortinArvoMerkkiJonona(1), "2");
    }

    @Test
    public void ensimmaiseksiKaannetyllaKortillaKortillaOnOikeaJarjestysNumero() {
        muistipeli.kaannaKortti(2, false);
        assertEquals(muistipeli.getEnsimmaisenKortinJarjestysNumero(), 2);

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
        assertEquals(muistipeli.getToisenKortinJarjestysNumero(), 2);

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
}