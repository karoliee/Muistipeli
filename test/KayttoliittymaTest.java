/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import Kayttoliittyma.*;
import Logiikka.*;

/**
 *
 * @author karoliee
 */
public class KayttoliittymaTest {
    Kayttoliittyma kayttis;
    Peli muistipeli;
    
    @Before
    public void konstruktoritLuovatOliot() {
        kayttis = new Kayttoliittyma();
        muistipeli = new Peli();
    }
    
     @Test
     public void KaikillaKorteillaOnArvo() {
         kayttis.teeKortit();
         assertEquals( 8, kayttis.getMuistipeli().getArvojenMaara(), 0.001 );
     }
}
