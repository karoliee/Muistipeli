/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author karoliee
 */
public class Kayttoliittyma extends JPanel implements ActionListener {

    /**
     * Pelin logiikka-luokka
     */
    Logiikka logiikka;
    /**
     * Taulukko, jonka alkiot ovat muistipelin kortit
     */
    public JButton[] kortit;
    /**
     * Pelilauta, missä peliä pelataan
     */
    JFrame pelilauta;
    /**
     * Käyttöliittymä, joka asettaa pelin osat pelilaudalle
     */
    Container kayttoliittyma;

    /**
     * Konstruktori, jossa luodaan luokan oliot
     */
    public Kayttoliittyma() {
        // korttien määrä myöhemmin riippuvaksi jostain muusta
        kortit = new JButton[4];

        logiikka = new Logiikka();
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
     * Metodi luo muistipelin kortit ja laittaa ne taulukkoon
     */
    public void teeKortit() {
        for (int i = 0; i < kortit.length; i++) {
            kortit[i] = new JButton();
            kortit[i].addActionListener(this);
        }
        logiikka.teeNumerotKorttejaVartenJaSekoitaNe(kortit.length);
    }

    /**
     * Metodi luo napit, joilla pelistä pystyy poistumaan ja aloittamaan uuden
     * pelin
     */
    public void teeMuutNapit() {
//        tee aloitus- ja lopetusnapit, JButton kai kanssa?
    }

    /**
     * Metodi asettaa kortit pelilaudalle
     */
    public void asetaKortitPelilaudalle() {
        //aseta täällä myös ehkä pelin aloittamis- ja lopettamisnapit laudalle? 
        //tai tee se metodissa teePelilauta() + 
        //mieti miten saat kortit asetettua laudalle vierekkäin vielä
        for (int i = 0; i < kortit.length; i++) {
            kayttoliittyma.add(kortit[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        //tässä pitäisi saada tietää kortin järjestysnumero jotenkin
//        Object kaannettyKortti = e.getSource();
//        System.out.println(kaannettyKortti);
    }
}