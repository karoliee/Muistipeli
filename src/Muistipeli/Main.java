/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Muistipeli;

import Kayttoliittyma.Kayttoliittyma;

/**
 *Luokka ohjaa muistipelin käyttämistä
 * 
 * @author karoliee
 */
public class Main {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kayttoliittyma kayttis = new Kayttoliittyma();
        kayttis.teeKortit();
        kayttis.teePelilauta();


    }
}
