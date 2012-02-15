/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Kayttoliittyma;

import Logiikka.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
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
     * Nappi, jota painamalla voi aloittaa uuden pelin, johon tulee uusi määrä
     * kortteja
     */
    JButton uudenTasonValitsemisNappi;
    /**
     * Lappu, josta näkee löydettyjen korttiparien määrän
     */
    JLabel loydetytTulos;
    /**
     * Lappu, josta näkee vuorojen määrän
     */
    JLabel yrityksetTulos;
    /**
     * Säiliö korteille, joka asetetaan pelilaudalle
     */
    Panel korttiPaneeli;
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
        muistipeli = new Peli();
        ajastin = new Ajastin(this, 400);

    }

    /**
     * Metodi, jossa kysytään pelaajalta, kuinka monta korttia peliin halutaan
     */
    public void kysyKorttiParienMaara() {
        int korttiParienMaara = -1;
        korttiParienMaara = PonnahdusIkkuna.kysyLuku("Kuinka monta paria?");
        while (korttiParienMaara <= 0) {
            korttiParienMaara = PonnahdusIkkuna.kysyLuku("Kuinka monta paria?" + "\n" + "Ainakin 1");
        }
        setKorttienMaara(korttiParienMaara);
    }

    /**
     * Metodi, jossa päätetään, kuinka monta korttia pelissä on
     *
     * @param korttiParienMaara korttiparien määrä
     */
    public void setKorttienMaara(int korttiParienMaara) {
        kortit = new JButton[2 * korttiParienMaara];
    }

    /**
     * Metodi, jossa luodaan pelilauta, jossa peliä pelataan
     */
    public void teePelilauta() {
        kysyKorttiParienMaara();
        pelilauta = new JFrame();
        pelilauta.setSize(900, 500);
        pelilauta.setTitle("Muistipeli");
        pelilauta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        teeKortit();
        teeMuutNapit();
        asetaKortitPelilaudalle();
        asetaNapitPelilaudalle();
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
     * Metodi luo napit, joilla pelistä pystyy poistumaan ja aloittamaan uuden
     * pelin, ja joista näkee tulokset
     */
    public void teeMuutNapit() {
        lopetusNappi = new JButton("Lopeta");
        lopetusNappi.addActionListener(this);
        uudenPelinAloitusNappi = new JButton("Uusi peli");
        uudenPelinAloitusNappi.addActionListener(this);
        uudenTasonValitsemisNappi = new JButton("Uusi taso");
        uudenTasonValitsemisNappi.addActionListener(this);
        loydetytTulos = new JLabel("Löydetyt: ");
        yrityksetTulos = new JLabel("Yritykset: ");


    }

    /**
     * Metodi asettaa kortit pelilaudalle
     */
    public void asetaKortitPelilaudalle() {
        korttiPaneeli = new Panel();
        if (kortit.length <= 10) {
            korttiPaneeli.setLayout(new GridLayout(2, (kortit.length) / 2));
        } else if (kortit.length <= 40) {
            korttiPaneeli.setLayout(new GridLayout(4, (kortit.length) / 4));
        } else {
            korttiPaneeli.setLayout(new GridLayout(6, (kortit.length) / 6));
        }

        for (int i = 0; i < kortit.length; i++) {
            korttiPaneeli.add(kortit[i]);
        }
        pelilauta.add(korttiPaneeli);

    }

    /**
     * Metodi asettaa napit pelilaudalle
     */
    public void asetaNapitPelilaudalle() {
        Panel nappiPaneeli = new Panel();
        nappiPaneeli.setLayout(new GridLayout(5, 1));
        nappiPaneeli.add(lopetusNappi);
        nappiPaneeli.add(uudenPelinAloitusNappi);
        nappiPaneeli.add(uudenTasonValitsemisNappi);
        nappiPaneeli.add(loydetytTulos);
        nappiPaneeli.add(yrityksetTulos);
        pelilauta.add(nappiPaneeli, BorderLayout.WEST);
    }

    /**
     * Metodi päivittää tulokset pelilaudalle
     */
    public void tarkastaTulokset() {
        loydetytTulos.setText("Löydetyt: "
                + muistipeli.getPelaaja().getLoydettyjenKorttiparienMaara());
        yrityksetTulos.setText("Yritykset: " + muistipeli.getPelaaja().getYritystenMaara());

    }

    /**
     * Metodi nollaa pelaajan tulokset pelilaudalla
     */
    public void nollaaTulokset() {
        muistipeli.getPelaaja().loydettyjenKorttiparienMaaranNollaus();
        muistipeli.getPelaaja().yritystenMaaranNollaus();
        tarkastaTulokset();
    }

    /**
     * Metodi kuulee tapahtuman, ja kertoo peli-luokalle, mitä nappia painettiin
     * ja kääntää kortin tai lopettaa pelin tai aloittaa uuden pelin samalla tai
     * uudella määrällä kortteja, riippuen painetusta napista
     *
     * @param e tapahtuma, joka tapahtuu
     */
    public void actionPerformed(ActionEvent e) {
        if (!muistipeli.getKaksiKorttiaOnKaannettyna()) {
            for (int i = 0; i < kortit.length; i++) {
                if (kortit[i] == e.getSource()) {
                    kortit[i].setText(muistipeli.getKortinArvoMerkkiJonona(i));
                    tapahtuma = muistipeli.kaannaKortti(i);
                    ajastin.start();
                }
            }
        }
        if (e.getSource() == lopetusNappi) {
            System.exit(0);
        }
        if (e.getSource() == uudenPelinAloitusNappi) {
            muistipeli = new Peli();
            muistipeli.teeArvotKorttejaVartenJaSekoitaNe(kortit.length);
            nollaaTulokset();
            for (int i = 0; i < kortit.length; i++) {
                kortit[i].setText("Muistipeli");
                kortit[i].setVisible(true);
            }

        }
        if (e.getSource() == uudenTasonValitsemisNappi) {
            muistipeli = new Peli();
            nollaaTulokset();
            pelilauta.remove(korttiPaneeli);
            kysyKorttiParienMaara();
            teeKortit();
            asetaKortitPelilaudalle();
            pelilauta.setVisible(true);

        }
    }

    /**
     * Metodi kertoo, mitä korteille tapahtuu kun aika on on kulunut ajastimesta
     */
    public void aikaOnKulunut() {
        muistipeli.setKaksiKorttiaOnKaannettyna(false);
        if (tapahtuma.equals("Kortit olivat samoja")) {
            piilotaKortit(muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                    muistipeli.getToisenKortinJarjestysNumero());
        } else if (tapahtuma.equals("Kortit eivät olleet samoja")) {
            kaannaKortitTakaisinAlaspain(
                    muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                    muistipeli.getToisenKortinJarjestysNumero());
        }
        tarkastaTulokset();
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

    /**
     * Metodi palauttaa Peli-olion. Tämä on lähinnä testausta varten
     *
     * @return muistipeli-peli
     */
    public Peli getMuistipeli() {
        return muistipeli;
    }
}