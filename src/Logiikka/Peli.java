/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logiikka;

import java.util.*;

/**
 * Luokka ohjaa pelin pelaamista ja muuta toimintaa pelissä
 *
 * @author karoliee
 */
public class Peli {

    /**
     * Kertoo, onko pelilaudalla kaksi korttia vielä oikeinpäin käännettynä
     * odottamassa ajastimen ajan loppumista. Tällöin ei tietysti voi kääntää
     * uutta korttia ennen kuin kortit on käännetty takaisin väärinpäin
     */
    boolean kaksiKorttiaOnKaannettyna;
    /**
     * Lista korttien "kuvista", eli arvoista. Jokaista arvoa on tietysti kaksi
     * kappaletta pelissä
     */
    ArrayList<Integer> korttienArvot;
    /**
     * Olio, joka pelaa peliä, eli pelaajan ilmentymä
     */
    Pelaaja pelaaja1;
    /**
     * Mahdollinen toinen olio, joka pelaa peliä, eli ensimmäisen pelaajan
     * vastapelaaja. Vastapelaaja luodaan vain kaksinpelissä
     */
    Pelaaja pelaaja2;
    /**
     * Kertoo, onko käännetty kortti ensimmäinen käännetty vai toinen
     */
    boolean onEnsimmainenKortti;
    /**
     * Kertoo ensimmäiseksi käännetyn kortin järjestysnumeron
     */
    int ensimmaisenKortinJarjestysnumero;
    /**
     * Kertoo toiseksi käännetyn kortin järjestysnumeron
     */
    int toisenKortinJarjestysnumero;
    /**
     * Kertoo, kumman pelaajan vuoro on kaksinpelissä
     */
    boolean ensimmaisenPelaajanVuoro;

    /**
     * Konstruktori luo pelin pelaajan ja kutsuu metodia, jossa aloitetaan uusi
     * peli
     */
    public Peli() {
        aloitaUusiPeli();
        pelaaja1 = new Pelaaja("");
    }

    /**
     * Metodi asettaa uuden pelin muuttujille oikeat arvot
     */
    public void aloitaUusiPeli() {
        korttienArvot = new ArrayList<Integer>();
        onEnsimmainenKortti = true;
        ensimmaisenKortinJarjestysnumero = -1;
        kaksiKorttiaOnKaannettyna = false;
        ensimmaisenPelaajanVuoro = true;

    }

    /**
     * Metodi vaihtaa listan, jonka alkiot ovat pelikorttejen "kuvat" eli arvot,
     * alkiot parametrina olevan listan alkioilla, eli asettaa korteille uudet
     * arvot
     *
     * @param ArrayList<Integer> korttienArvot pelikorttien uudet arvot listana
     */
    public void setKorttienArvot(ArrayList<Integer> korttienArvot) {
        this.korttienArvot = korttienArvot;
    }

    /**
     * Metodi luo listan, jonka alkiot ovat pelikorttejen "kuvat" eli arvot.
     * Kaikkia arvoja on kaksi kappaletta taulukossa. Tämän jälkeen metodi
     * sekoittaa listan
     *
     * @param korttienMaara pelikorttien määrä
     */
    public void teeArvotKorttejaVartenJaSekoitaNe(int korttienMaara) {
        for (int i = 1; i <= korttienMaara / 2; i++) {
            korttienArvot.add(i);
            korttienArvot.add(i);
        }
        Collections.shuffle(korttienArvot);

    }

    /**
     * Metodi palauttaa korttien arvojen määrän, eli korttien lukumäärän
     *
     * @return korttien arvojen määrä
     */
    public int getArvojenMaara() {
        return korttienArvot.size();
    }

    /**
     * Metodi katsoo, onko käännetty kortti ensimmäinen vai toinen vai
     * yritettiinkö kääntää uudestaan samaa korttia, mikä on vielä käännettynä
     * pelilaudalla. Jos kortti on ensimmäinen käännettävä, sen järjestysnumero
     * korttitaulukossa talletetaan. Jos pelataan yksinpeliä, niin pelaaja1
     * ainoana pelaajana käänsi tämän kortin, ja kutsutaan metodia, jossa
     * selviää mitä tämän jälkeen tapahtuu. Jos pelataan kaksinpeliä, täytyy
     * katsoa ensin kumman pelaajan vuoro oli
     *
     * @param kortinJarjestysnumero kertoo, monesko käännetty kortti on
     * @param pelataanKaksinpelia kertoo, pelataanko kaksin- vai yksinpeliä
     *
     * @return merkkijono, joka kertoo mitä huomattiin kun kortti käännettiin
     */
    public String kaannaKortti(int kortinJarjestysnumero, boolean pelataanKaksinpelia) {
        if (onEnsimmainenKortti) {
            ensimmaisenKortinJarjestysnumero = kortinJarjestysnumero;
            onEnsimmainenKortti = false;
            return "Oli ensimmäinen kortti";
        } else {
            if (kortinJarjestysnumero == ensimmaisenKortinJarjestysnumero) {
                return "Painoit samaa korttia!";
            } else {
                kaksiKorttiaOnKaannettyna = true;
                onEnsimmainenKortti = true;
                toisenKortinJarjestysnumero = kortinJarjestysnumero;
                if (!pelataanKaksinpelia) {
                    return pelaajaKaansiToisenKortin(pelaaja1, kortinJarjestysnumero);
                } else {
                    if (ensimmaisenPelaajanVuoro) {
                        ensimmaisenPelaajanVuoro = false;
                        return pelaajaKaansiToisenKortin(pelaaja1, kortinJarjestysnumero);

                    } else {
                        ensimmaisenPelaajanVuoro = true;
                        return pelaajaKaansiToisenKortin(pelaaja2, kortinJarjestysnumero);
                    }
                }
            }
        }

    }

    /**
     * Metodi kasvattaa pelaajan yrityksien määrää, ja jos kortit olivat samoja,
     * niin myös löydettyjen parien määrää, ja palauttaa tiedon olivatko kortit
     * pari vai ei
     *
     * @param pelaaja pelaaja, joka pelaa tällä hetkellä peliä
     * @param kortinJarjestysnumero kertoo, monesko valittu kortti on
     * korttitaulukossa
     *
     * @return merkkijono, joka kertoo mitä huomattiin kun kortti käännettiin
     */
    public String pelaajaKaansiToisenKortin(Pelaaja pelaaja, int kortinJarjestysnumero) {
        pelaaja.yritystenMaaranKasvu();
        if (testaaOvatkoKortitSamoja(ensimmaisenKortinJarjestysnumero,
                kortinJarjestysnumero)) {
            pelaaja.loydettyjenKorttiparienMaaranKasvu();
            return "Kortit olivat samoja";
        } else {
            return "Kortit eivät olleet samoja";
        }

    }

    /**
     * Metodi testaa ovatko kaksi korttia samoja, eli ovatko niihin liittyvät
     * arvot samat
     *
     * @param ensimmaisenKortinJarjestysnumero kertoo, monesko ensimmäiseksi
     * valittu kortti on korttitaulukossa
     * @param toisenKortinJarjestysnumero kertoo, monesko toiseksi valittu
     * kortti on korttitaulukossa
     *
     * @return true tai false riippuen siitä, ovatko kortit samoja
     */
    public boolean testaaOvatkoKortitSamoja(int ensimmaisenKortinJarjestysnumero,
            int toisenKortinJarjestysnumero) {
        if (korttienArvot.get(ensimmaisenKortinJarjestysnumero).equals(
                korttienArvot.get(toisenKortinJarjestysnumero))) {
            return true;

        }
        return false;
    }

    /**
     * Metodi vaihtaa totuusarvoa muuttujalle, joka kertoo onko kaksi korttia
     * vielä ylöspäin käännettynä pelilaudalla
     *
     * @param onkoKaksiKorttiaKaannetty true tai false sen mukaan, onko
     * pelilaudalla kaksi korttia vielä ylöspäin käännettyinä
     */
    public void setKaksiKorttiaOnKaannettyna(boolean onkoKaksiKorttiaKaannetty) {
        kaksiKorttiaOnKaannettyna = onkoKaksiKorttiaKaannetty;
    }

    /**
     * Kertoo, onko pelilaudalla vielä kaksi korttia oikeinpäin käännettynä
     * odottamassa ajastimen ajan loppumista
     *
     * @return true tai false sen mukaan, onko pelilaudalla kaksi korttia vielä
     * käännettyinä
     */
    public boolean getKaksiKorttiaOnKaannettyna() {
        return kaksiKorttiaOnKaannettyna;
    }

    /**
     * Metodi palauttaa valitun kortin arvon
     *
     * @return kortin arvo
     */
    public int getKortinArvo(int kortinJarjestysnumero) {
        return korttienArvot.get(kortinJarjestysnumero);
    }

    /**
     * Metodi palauttaa valitun kortin arvon merkkijonona
     *
     * @param kortinJarjestysnumero kertoo, monesko valittu kortti on
     * korttitaulukossa
     * @return kortin arvo merkkijonona
     */
    public String getKortinArvoMerkkiJonona(int kortinJarjestysnumero) {
        return Integer.toString(korttienArvot.get(kortinJarjestysnumero));
    }

    /**
     * Metodi palauttaa ensimmäisenä käännetyn kortin järjestysnumeron
     *
     * @return kortin järjestysnumero
     */
    public int getEnsimmaisenKortinJarjestysnumero() {
        return ensimmaisenKortinJarjestysnumero;
    }

    /**
     * Metodi palauttaa toisena käännetyn kortin järjestysnumeron
     *
     * @return kortin järjestysnumero
     */
    public int getToisenKortinJarjestysnumero() {
        return toisenKortinJarjestysnumero;
    }

    /**
     * Metodi palauttaa pelin ensimmäisen pelaajan
     *
     * @return pelin ensimmäinen pelaaja
     */
    public Pelaaja getPelaaja1() {
        return pelaaja1;
    }

    /**
     * Metodi luo pelille toisen pelaajan kun pelataan kaksinpeliä
     *
     * @param nimi pelaajan nimi
     */
    public void setPelaaja2(String nimi) {
        pelaaja2 = new Pelaaja(nimi);
    }

    /**
     * Metodi palauttaa pelin toisen pelaajan
     *
     * @return pelin toinen pelaaja
     */
    public Pelaaja getPelaaja2() {
        return pelaaja2;
    }

    /**
     * Metodi palauttaa tiedon siitä, onko ensimmäisen vai toisen pelaajan vuoro
     * kaksinpelissä
     *
     * @return true tai false riippuen siitä, onko ensimmäisen pelaajan vuoro
     */
    public boolean getOnkoEnsimmaisenPelaajanVuoro() {
        return ensimmaisenPelaajanVuoro;
    }
}
