/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Logiikka.*;
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
        muistipeli.teeNumerotKorttejaVartenJaSekoitaNe(11);
    }
    @Test
    public void listaSekoittaaMyosYhdenKortin() {
        muistipeli.teeNumerotKorttejaVartenJaSekoitaNe(1);
    }
    @Test
    public void kortitOvatSamoja() {
        muistipeli.teeNumerotKorttejaVartenJaSekoitaNe(1);
    }
    @Test
    public void kortitEivatOleSamoja() {
        muistipeli.teeNumerotKorttejaVartenJaSekoitaNe(1);
    }
}