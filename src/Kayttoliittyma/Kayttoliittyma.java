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
 * Luokka luo pelilaudan ja sen muut komponentit ja asettaa ne paikoilleen
 * pelaamista varten, ja hallitsee pelilaudalle tapahtuvia muutoksia
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
     * Nappi, jota painamalla voi aloittaa uuden samanlaisen pelin
     */
    JButton uudenPelinAloitusNappi;
    /**
     * Nappi, jota painamalla voi aloittaa uuden pelin, johon tulee uusi määrä
     * kortteja
     */
    JButton uudenTasonValitsemisNappi;
    /**
     * Taulu, josta näkee ensimmäisen pelaajan löytämien parien määrän
     */
    JLabel loydetytPelaaja1;
    /**
     * Taulu, josta näkee toisen pelaajan löytämien parien määrän
     */
    JLabel loydetytPelaaja2;
    /**
     * Taulu, josta näkee ensimmäisen pelaajan yrityksien määrän
     */
    JLabel yrityksetPelaaja1;
    /**
     * Taulu, josta näkee toisen pelaajan yrityksien määrän
     */
    JLabel yrityksetPelaaja2;
    /**
     * Taulu, josta näkee ensimmäisen pelaajan nimen
     */
    JLabel nimiPelaaja1;
    /**
     * Taulu, josta näkee toisen pelaajan nimen
     */
    JLabel nimiPelaaja2;
    /**
     * Säiliö korteille, joka asetetaan pelilaudalle
     */
    Panel korttiPaneeli;
    /**
     * Merkkijono kertoo, mitä kortin kääntämisen jälkeen huomattiin
     */
    String kortinKaantamisenJalkeenHuomattiin;
    /**
     * Muuttuja kertoo, pelataanko kaksin- vai yksinpeliä
     */
    boolean pelataanKaksinpelia;
    /**
     * Olio, joka hallitsee ajan kulumista
     */
    Ajastin ajastin;
    /**
     * Tieto siitä, montako korttia on näkyvissä pelilaudalla, eli kaikki kortit
     * joille ei ole vielä löydetty paria
     */
    int korttienMaaraPelilaudalla;

    /**
     * Konstruktori, jossa luodaan luokan oliot ja kutsutaan metodia, joka tekee
     * pelilaudan
     */
    public Kayttoliittyma() {
        muistipeli = new Peli();
        ajastin = new Ajastin(this, 350);
        pelataanKaksinpelia = false;
        teePelilauta();

    }

    /**
     * Metodi, jossa luodaan pelilauta, jossa peliä pelataan, ja kutsutaan
     * metodeita, jotka tekevät ja asettavat sinne kortit ja muut napit ja
     * kysyvät käyttäjältä pelin asetukset
     */
    public void teePelilauta() {
        kysyPelaajienLukumaara();
        kysyPelaajienNimetKaksinpelissa();
        kysyKorttiParienMaara();
        pelilauta = new JFrame();
        pelilauta.setSize(900, 500);
        pelilauta.setTitle("Muistipeli");
        pelilauta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        teeKortit();
        teeNapitJaTulostaulut();
        asetaKortitPelilaudalle();
        asetaNapitJaTulostaulutPelilaudalle();
        pelilauta.setVisible(true);
        vaihdaVuoroa();
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
            muistipeli.setPelaaja2("");
        }
    }

    /**
     * Metodi, jossa kysytään pelaajalta, haluaako hän nimetä pelaajat, jos
     * kyseessä on kaksinpeli. Jos pelaajia ei nimetä, niiden nimiksi tulee P1
     * ja P2
     */
    public void kysyPelaajienNimetKaksinpelissa() {
        if (pelataanKaksinpelia) {
            int vastauksenIndeksi = Ponnahdusikkuna.valitseNappula(
                    "Haluatko nimetä pelaajat?", "Kyllä", "Ei");
            if (vastauksenIndeksi == 0) {
                muistipeli.getPelaaja1().setPelaajanNimi(Ponnahdusikkuna.kysySana("Ensimmäisen pelaajan nimi?"));
                muistipeli.getPelaaja2().setPelaajanNimi(Ponnahdusikkuna.kysySana("Toisen pelaajan nimi"));
            } else {
                muistipeli.getPelaaja1().setPelaajanNimi("P1");
                muistipeli.getPelaaja2().setPelaajanNimi("P2");
            }
        }
    }

    /**
     * Metodi, jossa kysytään pelaajalta, kuinka monta korttia peliin halutaan.
     * Kysymykseen on pakko vastata kokonaisluvulla, joka on tiettyjen rajojen
     * sisällä
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
        korttienMaaraPelilaudalla = kortit.length;
    }

    /**
     * Metodi luo muistipelin kortit ja laittaa ne taulukkoon, ja kutsuu metodia
     * joka tekee korteille arvot ja sekoittaa ne
     */
    public void teeKortit() {
        for (int i = 0; i < kortit.length; i++) {
            kortit[i] = new JButton("Muistipeli");
            kortit[i].addActionListener(this);
        }
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(kortit.length);
    }

    /**
     * Metodi asettaa kortit pelilaudalle. Rivien määrä vaihtelee korttien
     * määrän mukaan, jotta kortit saataisiin aseteltua mahdollisimman hyvin
     * pelilaudalle
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
     * Metodi luo napit, joilla pelistä pystyy poistumaan ja aloittamaan uuden
     * pelin, ja taulut joista näkee tulokset
     */
    public void teeNapitJaTulostaulut() {
        lopetusNappi = new JButton("Lopeta");
        lopetusNappi.addActionListener(this);
        uudenPelinAloitusNappi = new JButton("Uusi peli");
        uudenPelinAloitusNappi.addActionListener(this);
        uudenTasonValitsemisNappi = new JButton("Uusi taso");
        uudenTasonValitsemisNappi.addActionListener(this);
        if (pelataanKaksinpelia) {
            nimiPelaaja1 = new JLabel(muistipeli.getPelaaja1().getPelaajanNimi());
            nimiPelaaja2 = new JLabel(muistipeli.getPelaaja2().getPelaajanNimi());
            loydetytPelaaja1 = new JLabel("löydetyt: ");
            loydetytPelaaja2 = new JLabel("löydetyt: ");
            yrityksetPelaaja1 = new JLabel("yritykset: ");
            yrityksetPelaaja2 = new JLabel("yritykset:");

        } else {
            loydetytPelaaja1 = new JLabel("Löydetyt: ");
            yrityksetPelaaja1 = new JLabel("Yritykset: ");
        }

    }

    /**
     * Metodi asettaa napit ja tulostaulut pelilaudalle
     */
    public void asetaNapitJaTulostaulutPelilaudalle() {
        Panel nappiPaneeli = new Panel();
        if (pelataanKaksinpelia) {
            nappiPaneeli.setLayout(new GridLayout(9, 1));
        } else {
            nappiPaneeli.setLayout(new GridLayout(5, 1));
        }
        nappiPaneeli.add(lopetusNappi);
        nappiPaneeli.add(uudenPelinAloitusNappi);
        nappiPaneeli.add(uudenTasonValitsemisNappi);
        if (pelataanKaksinpelia) {
            nappiPaneeli.add(nimiPelaaja1);
        }
        nappiPaneeli.add(loydetytPelaaja1);
        nappiPaneeli.add(yrityksetPelaaja1);
        if (pelataanKaksinpelia) {
            nappiPaneeli.add(nimiPelaaja2);
            nappiPaneeli.add(loydetytPelaaja2);
            nappiPaneeli.add(yrityksetPelaaja2);
        }
        pelilauta.add(nappiPaneeli, BorderLayout.WEST);
    }

    /**
     * Metodi päivittää pelaajien tulokset pelilaudalle jokaisen vuoron jälkeen
     */
    public void tarkastaTulokset() {
        if (pelataanKaksinpelia) {
            loydetytPelaaja1.setText("löydetyt: "
                    + muistipeli.getPelaaja1().getLoydettyjenKorttiparienMaara());
            loydetytPelaaja2.setText("löydetyt: "
                    + muistipeli.getPelaaja2().getLoydettyjenKorttiparienMaara());
            yrityksetPelaaja1.setText("yritykset: " + muistipeli.getPelaaja1().getYritystenMaara());
            yrityksetPelaaja2.setText("yritykset: " + muistipeli.getPelaaja2().getYritystenMaara());
        } else {
            loydetytPelaaja1.setText("Löydetyt: "
                    + muistipeli.getPelaaja1().getLoydettyjenKorttiparienMaara());
            yrityksetPelaaja1.setText("Yritykset: " + muistipeli.getPelaaja1().getYritystenMaara());
        }
    }

    /**
     * Metodi nollaa pelaajien tulokset pelilaudalla, kun uusi peli aloitetaan
     */
    public void nollaaTulokset() {
        if (pelataanKaksinpelia) {
            muistipeli.getPelaaja2().tulostenNollaus();
        }
        muistipeli.getPelaaja1().tulostenNollaus();
        tarkastaTulokset();

    }

    /**
     * Metodi asettaa pelin otsikoksi tiedon siitä, kumman pelaajan vuoro on
     * kaksinpelissä. Jos pelilaudalla ei ole enää kortteja, kutsutaan metodia
     * joka kertoo kumpi pelaajista on voittanut
     */
    public void vaihdaVuoroa() {
        if (pelataanKaksinpelia) {
            if (korttienMaaraPelilaudalla == 0) {
                tarkastaKumpiPelaajaVoitti();
            } else if (muistipeli.getOnkoEnsimmaisenPelaajanVuoro()) {
                pelilauta.setTitle("Muistipeli - pelaajan " + muistipeli.getPelaaja1().getPelaajanNimi() + " vuoro");
            } else {
                pelilauta.setTitle("Muistipeli - pelaajan " + muistipeli.getPelaaja2().getPelaajanNimi() + " vuoro");
            }
            pelilauta.setVisible(true);
        }
    }

    /**
     * Metodi asettaa pelin otsikoksi tiedon siitä, kumpi pelaaja voitti pelin
     */
    public void tarkastaKumpiPelaajaVoitti() {
        if (muistipeli.getPelaaja1().getLoydettyjenKorttiparienMaara()
                > muistipeli.getPelaaja2().getLoydettyjenKorttiparienMaara()) {
            pelilauta.setTitle("Pelaaja " + muistipeli.getPelaaja1().getPelaajanNimi() + " voitti!");
        } else if (muistipeli.getPelaaja1().getLoydettyjenKorttiparienMaara()
                == muistipeli.getPelaaja2().getLoydettyjenKorttiparienMaara()) {
            pelilauta.setTitle("Tasapeli!");
        } else {
            pelilauta.setTitle("Pelaaja " + muistipeli.getPelaaja2().getPelaajanNimi() + " voitti!");
        }
    }

    /**
     * Metodi kuulee tapahtuman eli napin painamisen, ja kertoo peli-luokalle,
     * mitä nappia painettiin tai kutsuu metodia joka lopettaa pelin tai
     * aloittaa uuden pelin samalla tai uudella määrällä kortteja, riippuen
     * painetusta napista. Jos kaksi korttia on käännettynä, metodi kutsuu
     * ajastinta. Metodi ei tietysti huomioi uuden kortin kääntämistä eli sen
     * painamista, jos laudalla on vielä kaksi korttia ylöspäin käännettynä
     * odottamassa ajastimen ajan kulumista
     *
     * @param e tapahtuma, joka tapahtuu, eli tietyn napin painaminen
     */
    public void actionPerformed(ActionEvent e) {
        if (!muistipeli.getKaksiKorttiaOnKaannettyna()) {
            for (int i = 0; i < kortit.length; i++) {
                if (kortit[i] == e.getSource()) {
                    kortit[i].setText(muistipeli.getKortinArvoMerkkiJonona(i));
                    kortinKaantamisenJalkeenHuomattiin = muistipeli.kaannaKortti(i, pelataanKaksinpelia);
                    if (kortinKaantamisenJalkeenHuomattiin.equals("Kortit olivat samoja")
                            || kortinKaantamisenJalkeenHuomattiin.equals("Kortit eivät olleet samoja")) {
                        ajastin.start();
                    }
                }
            }
        }
        if (e.getSource() == lopetusNappi) {
            System.exit(0);
        }
        if (e.getSource() == uudenPelinAloitusNappi) {
            aloitaUusiSamanlainenPeli();

        }
        if (e.getSource() == uudenTasonValitsemisNappi) {
            aloitaUusiEriTasoinenPeli();

        }
    }

    /**
     * Metodi aloittaa uuden pelin, jossa on yhtä monta korttia kuin edellisessä
     * pelissä
     */
    public void aloitaUusiSamanlainenPeli() {
        muistipeli.aloitaUusiPeli();
        korttienMaaraPelilaudalla = kortit.length;
        nollaaTulokset();
        vaihdaVuoroa();
        muistipeli.teeArvotKorttejaVartenJaSekoitaNe(kortit.length);
        for (int i = 0; i < kortit.length; i++) {
            kortit[i].setText("Muistipeli");
            kortit[i].setVisible(true);
        }
    }

    /**
     * Metodi aloittaa uuden pelin, jonka korttien määrän käyttäjä saa taas
     * valita uudestaan
     */
    public void aloitaUusiEriTasoinenPeli() {
        muistipeli.aloitaUusiPeli();
        kysyKorttiParienMaara();
        teeKortit();
        nollaaTulokset();
        vaihdaVuoroa();
        pelilauta.remove(korttiPaneeli);
        asetaKortitPelilaudalle();
        pelilauta.setVisible(true);
    }

    /**
     * Metodi kutsuu joko metodia, joka piilottaa kortit, sillä ne olivat pari,
     * tai kääntää kortit takaisin alaspäin, riippuen siitä olivatko kortit pari
     * vai ei, ja kutsuu tämän jälkeen metodeita jotka tarkastavat tulokset ja
     * kumman pelaajan vuoro on. Tätä metodia kutsutaan, kun ajastimesta on
     * loppunut aika
     */
    public void aikaOnKulunut() {
        if (kortinKaantamisenJalkeenHuomattiin.equals("Kortit olivat samoja")) {
            piilotaKortit(muistipeli.getEnsimmaisenKortinJarjestysnumero(),
                    muistipeli.getToisenKortinJarjestysnumero());
        } else if (kortinKaantamisenJalkeenHuomattiin.equals("Kortit eivät olleet samoja")) {
            kaannaKortitTakaisinAlaspain(
                    muistipeli.getEnsimmaisenKortinJarjestysnumero(),
                    muistipeli.getToisenKortinJarjestysnumero());
        }
        muistipeli.setKaksiKorttiaOnKaannettyna(false);
        tarkastaTulokset();
        vaihdaVuoroa();
    }

    /**
     * Metodi piilottaa näkyvistä kortit, jotka olivat pari
     *
     * @param ensimmaisenKortinJarjestysnumero ensimmäiseksi käännetyn kortin
     * järjestysnumero
     * @param toisenKortinJarjestysnumero toiseksi käännetyn kortin
     * järjestysnumero
     */
    public void piilotaKortit(int ensimmaisenKortinJarjestysnumero,
            int toisenKortinJarjestysnumero) {
        kortit[ensimmaisenKortinJarjestysnumero].setVisible(false);
        kortit[toisenKortinJarjestysnumero].setVisible(false);
        korttienMaaraPelilaudalla = korttienMaaraPelilaudalla - 2;

    }

    /**
     * Metodi kääntää kortit takaisin väärinpäin, koska ne eivät olleet pari
     *
     * @param ensimmaisenKortinJarjestysnumero ensimmäiseksi käännetyn kortin
     * järjestysnumero
     * @param toisenKortinJarjestysnumero toiseksi käännetyn kortin
     * järjestysnumero
     */
    public void kaannaKortitTakaisinAlaspain(int ensimmaisenKortinJarjestysnumero,
            int toisenKortinJarjestysnumero) {
        kortit[ensimmaisenKortinJarjestysnumero].setText("Muistipeli");
        kortit[toisenKortinJarjestysnumero].setText("Muistipeli");


    }

    /**
     * Main-metodi tekee uuden käyttöliittymän
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Kayttoliittyma();


    }
}
