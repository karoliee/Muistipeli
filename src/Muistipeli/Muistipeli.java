/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Muistipeli;

import Kayttoliittyma.Kayttoliittyma;

/**
 *
 * @author karoliee
 */
public class Muistipeli {

    /**
     * Luokka ohjaa muistipelin käyttämistä
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kayttoliittyma kayttis = new Kayttoliittyma();
        kayttis.teeKortit();
        kayttis.teePelilauta();


    }
}
