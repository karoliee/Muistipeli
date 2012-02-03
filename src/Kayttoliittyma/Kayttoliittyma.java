/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Luokka luo pelilaudan ja sen muut sen komponentit ja asettaa ne paikoilleen
 * pelaamista varten
 *
 * @author karoliee
 */
public class Kayttoliittyma extends JPanel implements ActionListener {

    /**
     * Pelin muistipeli-luokka
     */
    Peli muistipeli;
    /**
     * Taulukko, jonka alkiot ovat muistipelin kortit
     */
    public JButton[] kortit;
    /**
     * Pelilauta, missä peliä pelataan
     */
    JFrame pelilauta;
    /**
     * Nappi, jota painamalla pelin voi lopettaa
     */
    JButton lopetusNappi;
    /**
     * Nappi, jota painamalla voi aloittaa uuden pelin
     */
    JButton uudenPelinAloitusNappi;
    /**
     * Käyttöliittymä, joka asettaa pelin osat pelilaudalle
     */
    Container kayttoliittyma;

    /**
     * Konstruktori, jossa luodaan luokan oliot
     */
    public Kayttoliittyma() {
        // korttien määrä myöhemmin riippuvaksi jostain muusta
        kortit = new JButton[6];
        muistipeli = new Peli();
    }

    /**
     * Metodi, jossa luodaan pelilauta, jossa peliä pelataan
     */
    public void teePelilauta() {
        pelilauta = new JFrame();
        kayttoliittyma = pelilauta.getContentPane();
        pelilauta.setSize(500, 400);
        pelilauta.getContentPane().setLayout(new GridLayout(2, (kortit.length - 1) / 2));
        asetaKortitPelilaudalle();
        teeMuutNapit();
        pelilauta.setTitle("Muistipeli");
        pelilauta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pelilauta.setVisible(true);
    }

    /**
     * Metodi luo muistipelin kortit ja laittaa ne taulukkoon
     */
    public void teeKortit() {
        for (int i = 0; i < kortit.length; i++) {
            kortit[i] = new JButton("Muistipeli");
            kortit[i].addActionListener(this);
        }
        muistipeli.teeNumerotKorttejaVartenJaSekoitaNe(kortit.length);
    }

    /**
     * Metodi luo napit, joilla pelistä pystyy poistumaan ja aloittamaan uuden
     * pelin
     */
    public void teeMuutNapit() {
        lopetusNappi = new JButton("Lopeta");
        uudenPelinAloitusNappi = new JButton("Uusi peli");
    }

    /**
     * Metodi asettaa kortit pelilaudalle
     */
    public void asetaKortitPelilaudalle() {
        //aseta täällä myös ehkä pelin aloittamis- ja lopettamisnapit laudalle? 
        //tai tee se metodissa teePelilauta() ?
        for (int i = 0; i < kortit.length; i++) {
            kayttoliittyma.add(kortit[i]);
        }
    }

    /**
     * Metodi kuulee tapahtuman, ja kertoo peli-luokalle, mitä nappia painettiin
     * ja kääntää kortin, tai lopettaa pelin tai aloittaa uuden pelin riippuen
     * painetusta napista
     *
     * @param e tapahtuma, joka tapahtuu
     */
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < kortit.length; i++) {
            if (kortit[i] == e.getSource()) {
                kortit[i].setText(muistipeli.kortinArvoMerkkiJonona(i));
                String tapahtuma = muistipeli.kaannaKortti(i);
                
                System.out.println(tapahtuma);
                if (tapahtuma.equals("Kortit olivat samoja")) {
                    poistaKortit(muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                            muistipeli.getToisenKortinJarjestysNumero());
                    System.out.println("poistin kortit");
                } else if (tapahtuma.equals("Kortit eivät olleet samoja")) {
                    kaannaKortitTakaisinAlaspain(
                            muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                            muistipeli.getToisenKortinJarjestysNumero());
                    System.out.println("käänsin kortit takaisin");
                } else {
                }
            }
        }
        if (e.getSource() == lopetusNappi) {
            System.exit(0);
        }
        if (e.getSource() == uudenPelinAloitusNappi) {
            muistipeli = new Peli();
            teeKortit();
            teePelilauta();
        }
    }

    /**
     * Metodi poistaa kortit, jotka ovat pari
     *
     * @param ensimmaisenKortinJarjestysNumero ensimmäiseksi käännetyn kortin
     * järjestysnumero
     * @param toisenKortinJarjestysNumero toiseksi käännetyn kortin
     * järjestysnumero
     */
    public void poistaKortit(int ensimmaisenKortinJarjestysNumero,
            int toisenKortinJarjestysNumero) {
        kortit[ensimmaisenKortinJarjestysNumero].setVisible(false);
        kortit[toisenKortinJarjestysNumero].setVisible(false);

    }

    /**
     * Metodi kääntää kortit takaisin alaspäin, koska ne eivät olleet samoja
     *
     * @param ensimmaisenKortinJarjestysNumero ensimmäiseksi käännetyn kortin
     * järjestysnumero
     * @param toisenKortinJarjestysNumero toiseksi käännetyn kortin
     * järjestysnumero
     */
    public void kaannaKortitTakaisinAlaspain(int ensimmaisenKortinJarjestysNumero,
            int toisenKortinJarjestysNumero) {
        kortit[ensimmaisenKortinJarjestysNumero].setText("Muistipeli");
        kortit[toisenKortinJarjestysNumero].setText("Muistipeli");

    }
}