import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    static char[] pulaZnakowHasla = {'g', 'j', 'k', 'f', 'r', 'd', 'a', 'e'};
    static char[] wylosowaneHaslo;
    static char[] hasloUzytkownika;
    static int liczbaProb = 0;
    static int dlugoscHasla = 4;

    public static void main(String[] args) {
        System.out.println("Zaczynamy grę!");
        System.out.println("Losuję hasło...");
        wylosowaneHaslo = losujHaslo();
        System.out.println(wylosowaneHaslo);
        System.out.println("Hasło gotowe. Składa się z " + dlugoscHasla + " znaków. Spróbuj je odgadnąć. Masz 10 prób. Powodzenia!");
        System.out.println("Dostępne litery to: " + Arrays.toString(pulaZnakowHasla) + "\nLitery mogą się powtarzać. :)");
        int iloscCzarnych;
        int iloscBialych;
        do {
            liczbaProb++;
            System.out.println("Podejście nr " + liczbaProb + ". Podaj hasło");
            hasloUzytkownika = podajHaslo();
            System.out.println("Sprawdzam podane hasło...");
            boolean[][] tablicaRezultatow = sprawdzHaslo();
            System.out.println(Arrays.toString(tablicaRezultatow[0]));
            System.out.println(Arrays.toString(tablicaRezultatow[1]));
            iloscCzarnych = policzIloscPinesek(tablicaRezultatow, 1);
            iloscBialych = policzIloscPinesek(tablicaRezultatow, 2);
            System.out.println("Czarne pinezki: " + iloscCzarnych + ". Białe pinezki: " + iloscBialych + ".");
            if (iloscCzarnych == dlugoscHasla) {
                System.out.println("Brawo. Odgadłeś hasło. Liczba prób: " + liczbaProb);
            }
        } while (liczbaProb <= 10 && iloscCzarnych != dlugoscHasla);
        if (liczbaProb > 10 && iloscCzarnych != dlugoscHasla) {
            System.out.println("Niestety wyczerpałeś liczbę prób :(. Prawidłowe hasło to: " + Arrays.toString(wylosowaneHaslo));
        }
    }

    static char[] losujHaslo() {
        Random losowanie = new Random();
        char[] haslo = new char[dlugoscHasla];
        for (int i = 0; i < dlugoscHasla; i++) {
            int pozycjaWtablicy = losowanie.nextInt(pulaZnakowHasla.length);
            haslo[i] = pulaZnakowHasla[pozycjaWtablicy];
        }
        return haslo;
    }

    static char[] podajHaslo() {
        Scanner podawaneHaslo = new Scanner(System.in);
        String hasloString = podawaneHaslo.nextLine();
        char[] probaHasla = new char[dlugoscHasla];
        for (int i = 0; i < dlugoscHasla; i++) {
            probaHasla[i] = hasloString.charAt(i);
        }
        return probaHasla;
    }

    static boolean[][] sprawdzHaslo() {
        // biala pinezka - trafiona literka + niewlasciwa pozycja literki
        //czarna pinezka - trafiona literka + trafiona pozycja literki

     /*   boolean[] bialaPinezka = new boolean[dlugoscHasla], czarnaPinezka = new boolean[dlugoscHasla];

        for (int i = 0; i < czarnaPinezka.length; i++) {
            if (hasloUzytkownika[i] == wylosowaneHaslo[i]) {
                czarnaPinezka[i] = true;
                iloscCzarnych++;
                continue;
            }
            for (int j = 0; j < czarnaPinezka.length; j++) {
                if (hasloUzytkownika[j] == wylosowaneHaslo[i]) {
                    bialaPinezka[i] = true;
                    iloscBialych++;
                }
            }
        }*/
        //true false false false
        //false true true false
        boolean[][] tablicaPinesek = new boolean[2][4];
        for (int xUzytkownika = 0; xUzytkownika < tablicaPinesek[0].length; xUzytkownika++) {
            if (hasloUzytkownika[xUzytkownika] == wylosowaneHaslo[xUzytkownika]) {
                tablicaPinesek[0][xUzytkownika] = true;
                continue;
            }
            for (int xHaslo = 0; xHaslo < tablicaPinesek[1].length; xHaslo++) {
                if (hasloUzytkownika[xHaslo] == wylosowaneHaslo[xUzytkownika]) {
                    tablicaPinesek[1][xHaslo] = true;
                }
            }
        }
        return tablicaPinesek;
    }

    static int policzIloscPinesek(boolean[][] pinezki, int rzadTablicy) {
        int iloscPinesek = 0;
        for (int x = 0; x < pinezki[rzadTablicy - 1].length; x++) {
            if (pinezki[rzadTablicy - 1][x]){
                iloscPinesek++;
            }
        }
        return iloscPinesek;
    }
}
