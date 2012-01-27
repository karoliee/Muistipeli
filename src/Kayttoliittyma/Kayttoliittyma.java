/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author karoliee
 */
public class Kayttoliittyma extends JPanel {

    /**
     * Taulukko, jonka alkiot ovat muistipelin kortit
     */
    public JButton[] kortit;
    /**
     * Kuuntelija, joka kuuntelee milloin korttia painetaan
     */
    KortinKuuntelija kortinkuuntelija;
    /**
     * Pelilauta, missä peliä pelataan
     */
    JFrame pelilauta;
    /**
     * Käyttöliittymä, joka asettaa pelin osat pelilaudalle
     */
    Container kayttoliittyma;
    /**
     * Lista korttien "kuvista", eli numeroista. Jokaista numeroa on siis kaksi
     * kappaletta
     */
    ArrayList<Integer> korttienNumerot;

    /**
     * Konstruktori, jossa luodaan luokan oliot
     */
    public Kayttoliittyma() {
        // korttien määrä myöhemmin riippuvaksi jostain muusta
        kortit = new JButton[4];
        kortinkuuntelija = new KortinKuuntelija();
        korttienNumerot = new ArrayList<Integer>();
    }

    /**
     * Metodi, jossa luodaan pelilauta, jossa peliä pelataan
     */
    public void teePelilauta() {
        pelilauta = new JFrame();
        kayttoliittyma = pelilauta.getContentPane();
        pelilauta.setSize(500, 500);
        asetaKortitPelilaudalle();
        pelilauta.setTitle("Muistipeli");
        pelilauta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pelilauta.setVisible(true);
    }

    /**
     * Metodi luo muistipelin kortit taulukkoon
     */
    public void teeKortit() {
        for (int i = 0; i < kortit.length; i++) {
            kortit[i] = new JButton();
            kortit[i].addActionListener(kortinkuuntelija);
        }
    }

    /**
     * Metodi luo napit, joilla pelistä pystyy poistumaan ja aloittamaan uuden
     * pelin
     */
    public void teeMuutNapit() {
//        tee aloitus- ja lopetusnapit, JButton kai?
    }

    /**
     * Metodi asettaa kortit pelilaudalle
     */
    public void asetaKortitPelilaudalle() {
        //aseta täällä myös ehkä pelin aloittamis- ja lopettamisnapit? 
        //tai tee niille oma metodi
        for (int i = 0; i < kortit.length; i++) {
            kayttoliittyma.add(kortit[i]);
        }
    }

    /**
     * Metodi luo listan, jonka alkiot ovat pelikorttejen "kuvat" eli numerot.
     * Kaikkia numeroita on kaksi kappaletta taulukossa. Tämän jälkeen metodi
     * sekoittaa listan
     */
    public void teeNumerotKorttejaVartenJaSekoitaNe() {
        for (int i = 1; i <= kortit.length / 2; i++) {
            korttienNumerot.add(i);
            korttienNumerot.add(i);
        }
        Collections.shuffle(korttienNumerot);

    }
}
