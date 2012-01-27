/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.*;
import java.awt.*;
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
     * Luku, joka kertoo montako paria peliin tulee, eli korttejen määrä on siis
     * kaksinkertainen tähän verrattuna
     */
    public Kayttoliittyma() {
        // korttiparien määrä myöhemmin riippuvaksi jostain muusta
        teeKortit(3);
        kortinkuuntelija = new KortinKuuntelija();
        pelilauta = new JFrame();
        kayttoliittyma = pelilauta.getContentPane();
        pelilauta.setSize(500, 500);
//        asetaKortitPelilaudalle();
        pelilauta.setTitle("Muistipeli");
        pelilauta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pelilauta.setVisible(true);

    }

    /**
     * Metodi tekee taulukon, ja luo sinne alkiot, jotka ovat muistipelin kortit
     *
     * @param korttiparienMaara Kertoo, montako paria kortteja on
     */
    public void teeKortit(int korttiparienMaara) {
        kortit = new JButton[2 * korttiparienMaara];
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
     * Metodi luo taulukon, jonka alkiot ovat pelikorttejen "kuvat" eli numerot.
     * Kaikkia numeroita on kaksi kappaletta taulukossa
     */
    public void teeNumerotKorttejaVarten() {
//        uusi taulukko johon tulee numeroita?
    }

    /**
     * Metodi sekoittaa taulukon, jossa on pelikorttejen numerot
     */
    public void liitaKortteihinNumerotSatunnaisesti() {
//        eli sekoita numerot täällä. vai edellisessä (onko tämä turha metodi?)
    }
}
