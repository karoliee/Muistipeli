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
     * Taulu, josta näkee löydettyjen korttiparien määrän
     */
    JLabel loydetytTulos;
    /**
     * Taulu, josta näkee vuorojen määrän
     */
    JLabel yrityksetTulos;
    /**
     * Taulu, josta näkee ensimmäisen pelaajan löytämien parien määrän
     */
    JLabel loydetytPelaaja1;
    /**
     * Taulu, josta näkee toisen pelaajan löytämien parien määrän
     */
    JLabel loydetytPelaaja2;
    /**
     * Säiliö korteille, joka asetetaan pelilaudalle
     */
    Panel korttiPaneeli;
    /**
     * Merkkijono kertoo, mitä korttia käännettäessä tapahtui
     */
    String tapahtuma;
    /**
     * Muuttuja kertoo, pelataanko kaksin- vai yksinpeliä
     */
    boolean pelataanKaksinpelia;
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
        pelataanKaksinpelia = false;

    }

    /**
     * Metodi, jossa luodaan pelilauta, jossa peliä pelataan
     */
    public void teePelilauta() {
        kysyPelaajienLukumaara();
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
     * Metodi, jossa kysytään pelaajalta, haluaako hän pelata yksin vai kaksin
     */
    public void kysyPelaajienLukumaara() {
        int vastauksenIndeksi = Ponnahdusikkuna.valitseNappula(
                "Haluatko pelata yksin- vai kaksinpeliä?",
                "Yksinpeli", "Kaksinpeli");
        if (vastauksenIndeksi == 1) {
            pelataanKaksinpelia = true;
            muistipeli.setPelaaja2("pelaaja2");
        }
    }

    /**
     * Metodi, jossa kysytään pelaajalta, kuinka monta korttia peliin halutaan
     */
    public void kysyKorttiParienMaara() {
        int korttiParienMaara = Ponnahdusikkuna.kysyLuku("Kuinka monta paria?");
        while (korttiParienMaara < 1 || korttiParienMaara > 180) {
            korttiParienMaara = Ponnahdusikkuna.kysyLuku("Kuinka monta paria?"
                    + "\n" + "Ainakin 1, mutta enintään 180");
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
     * pelin, ja taulut joista näkee tulokset
     */
    public void teeMuutNapit() {
        lopetusNappi = new JButton("Lopeta");
        lopetusNappi.addActionListener(this);
        uudenPelinAloitusNappi = new JButton("Uusi peli");
        uudenPelinAloitusNappi.addActionListener(this);
        uudenTasonValitsemisNappi = new JButton("Uusi taso");
        uudenTasonValitsemisNappi.addActionListener(this);
        if (pelataanKaksinpelia) {
            loydetytPelaaja1 = new JLabel("Pelaaja 1: ");
            loydetytPelaaja2 = new JLabel("Pelaaja 2: ");

        } else {
            loydetytTulos = new JLabel("Löydetyt: ");
            yrityksetTulos = new JLabel("Yritykset: ");
        }

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
        } else if (kortit.length <= 90) {
            korttiPaneeli.setLayout(new GridLayout(6, (kortit.length) / 6));
        } else if (kortit.length <= 120) {
            korttiPaneeli.setLayout(new GridLayout(8, (kortit.length) / 8));
        } else {
            korttiPaneeli.setLayout(new GridLayout(12, (kortit.length) / 12));
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
        if (pelataanKaksinpelia) {
            nappiPaneeli.add(loydetytPelaaja1);
            nappiPaneeli.add(loydetytPelaaja2);
        } else {
            nappiPaneeli.add(loydetytTulos);
            nappiPaneeli.add(yrityksetTulos);
        }
        pelilauta.add(nappiPaneeli, BorderLayout.WEST);
    }

    /**
     * Metodi päivittää tulokset pelilaudalle
     */
    public void tarkastaTulokset() {
        if (pelataanKaksinpelia) {
            loydetytPelaaja1.setText("Pelaaja 1: "
                    + muistipeli.getPelaaja1().getLoydettyjenKorttiparienMaara());
            loydetytPelaaja2.setText("Pelaaja 2: "
                    + muistipeli.getPelaaja2().getLoydettyjenKorttiparienMaara());
        } else {
            loydetytTulos.setText("Löydetyt: "
                    + muistipeli.getPelaaja1().getLoydettyjenKorttiparienMaara());
            yrityksetTulos.setText("Yritykset: " + muistipeli.getPelaaja1().getYritystenMaara());
        }
    }

    /**
     * Metodi nollaa pelaajan tulokset pelilaudalla
     */
    public void nollaaTulokset() {
        if (pelataanKaksinpelia) {
            muistipeli.getPelaaja2().loydettyjenKorttiparienMaaranNollaus();
            muistipeli.getPelaaja2().yritystenMaaranNollaus();
        }
        muistipeli.getPelaaja1().loydettyjenKorttiparienMaaranNollaus();
        muistipeli.getPelaaja1().yritystenMaaranNollaus();
        tarkastaTulokset();

    }

    /**
     * Metodi kuulee tapahtuman eli napin painamisen, ja kertoo peli-luokalle,
     * mitä nappia painettiin tai lopettaa pelin tai aloittaa uuden pelin
     * samalla tai uudella määrällä kortteja, riippuen painetusta napista.
     * Metodi ei tietysti huomioi uuden kortin kääntämistä, jos laudalla on
     * vielä kaksi korttia ylöspäin käännettynä odottamassa ajastimen ajan
     * kulumista
     *
     * @param e tapahtuma, joka tapahtuu
     */
    public void actionPerformed(ActionEvent e) {
        if (!muistipeli.getKaksiKorttiaOnKaannettyna()) {
            for (int i = 0; i < kortit.length; i++) {
                if (kortit[i] == e.getSource()) {
                    kortit[i].setText(muistipeli.getKortinArvoMerkkiJonona(i));
                    tapahtuma = muistipeli.kaannaKortti(i, pelataanKaksinpelia);
                    if (tapahtuma.equals("Kortit olivat samoja")
                            || tapahtuma.equals("Kortit eivät olleet samoja")) {
                        ajastin.start();
                    }
                }
            }
        }
        if (e.getSource() == lopetusNappi) {
            System.exit(0);
        }
        if (e.getSource() == uudenPelinAloitusNappi) {
            muistipeli = new Peli();
            if (pelataanKaksinpelia) {
                muistipeli.setPelaaja2("pelaaja2");
            }
            muistipeli.teeArvotKorttejaVartenJaSekoitaNe(kortit.length);
            nollaaTulokset();
            for (int i = 0; i < kortit.length; i++) {
                kortit[i].setText("Muistipeli");
                kortit[i].setVisible(true);
            }

        }
        if (e.getSource() == uudenTasonValitsemisNappi) {
            muistipeli = new Peli();
            if (pelataanKaksinpelia) {
                muistipeli.setPelaaja2("pelaaja2");
            }
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
        if (tapahtuma.equals("Kortit olivat samoja")) {
            piilotaKortit(muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                    muistipeli.getToisenKortinJarjestysNumero());
        } else if (tapahtuma.equals("Kortit eivät olleet samoja")) {
            kaannaKortitTakaisinAlaspain(
                    muistipeli.getEnsimmaisenKortinJarjestysNumero(),
                    muistipeli.getToisenKortinJarjestysNumero());
        }
        muistipeli.setKaksiKorttiaOnKaannettyna(false);
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