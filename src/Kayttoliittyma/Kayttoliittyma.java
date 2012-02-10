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
     * Pelin ilmentymä eli muistipeli-luokka
     */
    Peli muistipeli;
    /**
     * Taulukko, jonka alkiot ovat muistipelin kortit
     */
    public JButton[] kortit;
//    /**
//     * Pelilauta, missä peliä pelataan
//     */
//    JFrame pelilauta;
    /**
     * Nappi, jota painamalla pelin voi lopettaa
     */
    JButton lopetusNappi;
    /**
     * Nappi, jota painamalla voi aloittaa uuden pelin
     */
    JButton uudenPelinAloitusNappi;
    /**
     * Säiliö, joka asettaa kortit pelilaudalle
     */
    Container korttienSailio;
    /**
     * Merkkijono kertoo, mitä korttia käännettäessä tapahtui
     */
    String tapahtuma;
    /**
     * Olio, joka hallitsee ajan kulumista
     */
    Ajastin ajastin;

    /**
     * Konstruktori, jossa luodaan luokan oliot
     */
    public Kayttoliittyma() {
        // korttien määrä myöhemmin riippuvaksi jostain muusta
        kortit = new JButton[8];
        muistipeli = new Peli();
        ajastin = new Ajastin(this, 500);
    }

    /**
     * Metodi, jossa luodaan pelilauta, jossa peliä pelataan
     */
    public void teePelilauta() {
        JFrame pelilauta = new JFrame();
        korttienSailio = pelilauta.getContentPane();
        pelilauta.setSize(500, 400);
        pelilauta.getContentPane().setLayout(new GridLayout(2, (kortit.length - 1) / 2));
        teeMuutNapit();
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
            kortit[i] = new JButton("Muistipeli");
            kortit[i].addActionListener(this);
        }
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(kortit.length);
    }

    /**
     * Metodi palauttaa Peli-olion. Tämä on lähinnä testausta varten
     *
     * @return muistipeli-peli
     */
    public Peli getMuistipeli() {
        return muistipeli;
    }

    /**
     * Metodi luo napit, joilla pelistä pystyy poistumaan ja aloittamaan uuden
     * pelin
     */
    public void teeMuutNapit() {
        lopetusNappi = new JButton("Lopeta");
        lopetusNappi.addActionListener(this);
        uudenPelinAloitusNappi = new JButton("Uusi peli");
        uudenPelinAloitusNappi.addActionListener(this);

    }

    /**
     * Metodi asettaa kortit pelilaudalle
     */
    public void asetaKortitPelilaudalle() {
        //aseta täällä myös ehkä pelin aloittamis- ja lopettamisnapit laudalle? 
        //tai tee se metodissa teePelilauta() ?
        for (int i = 0; i < kortit.length; i++) {
            korttienSailio.add(kortit[i]);
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
                tapahtuma = muistipeli.kaannaKortti(i);
                ajastin.start();
            }
        }
        if (e.getSource() == lopetusNappi) {
            System.exit(0);
        }
        if (e.getSource() == uudenPelinAloitusNappi) {
            muistipeli = new Peli();
            teeKortit();
            asetaKortitPelilaudalle();
        }
    }

    /**
     * Metodi kertoo, mitä korteille tapahtuu kun aika on on kulunut ajastimesta
     */
    public void aikaOnKulunut() {
        if (tapahtuma.equals("Kortit olivat samoja")) {
            piilotaKortit(muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                    muistipeli.getToisenKortinJarjestysNumero());
        } else if (tapahtuma.equals("Kortit eivät olleet samoja")) {
            kaannaKortitTakaisinAlaspain(
                    muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                    muistipeli.getToisenKortinJarjestysNumero());
        }
    }

    /**
     * Metodi piilottaa näkyvistä kortit, jotka ovat pari
     *
     * @param ensimmaisenKortinJarjestysNumero ensimmäiseksi käännetyn kortin
     * järjestysnumero
     * @param toisenKortinJarjestysNumero toiseksi käännetyn kortin
     * järjestysnumero
     */
    public void piilotaKortit(int ensimmaisenKortinJarjestysNumero,
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