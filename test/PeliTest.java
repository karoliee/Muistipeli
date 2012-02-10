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
        assertEquals( 6, muistipeli.getArvojenMaara(), 0.001 );
        
    }
    @Test
    public void kortitOvatSamoja() {
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(2);
        assertTrue(muistipeli.testaaOvatkoKortitSamoja(0,1));
    }
    @Test
    public void kortitEivatOleSamoja() {
        ArrayList<Integer> korteillaEriArvot = new ArrayList<Integer>();
        for(int i = 1; i<=8; i++) {
        korteillaEriArvot.add(i);
        }
        muistipeli.setKorttienArvot(korteillaEriArvot);
        assertFalse(muistipeli.testaaOvatkoKortitSamoja(0,1));
    }
    @Test
    public void korttiOnEnsimmainenKaannetty() {
        assertEquals( muistipeli.kaannaKortti(6), "Oli ensimmäinen kortti" );
        
    }
    @Test
    public void korttiaPainettiinKaksiKertaa() {
        muistipeli.kaannaKortti(2);
        assertEquals( muistipeli.kaannaKortti(2), "Painoit samaa korttia!" );
        
    }
}
