import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    static char[] passwordCharPool = {'g', 'j', 'k', 'f', 'r', 'd', 'a', 'e'};
    static char[] randomlySelectedPassword;
    static char[] playersPassword;
    static int attemptCount = 0;
    static int passwordLength = 4;

    public static void main(String[] args) {
        setupGame();
        playGame();
    }

    static void setupGame() {
        System.out.println("Zaczynamy grę!");
        System.out.println("Losuję hasło...");
        randomlySelectedPassword = drawPassword();
     //   System.out.println(randomlySelectedPassword);
        System.out.println("Hasło gotowe. Składa się z " + passwordLength + " znaków. Spróbuj je odgadnąć. " +
                "Masz 10 prób. Powodzenia!");
        System.out.println("Dostępne litery to: " + Arrays.toString(passwordCharPool) +
                "Litery mogą się powtarzać. :)");
    }

    static char[] drawPassword() {
        Random draw = new Random();
        char[] password = new char[passwordLength];
        for (int i = 0; i < passwordLength; i++) {
            int letterIndexInCharPool = draw.nextInt(passwordCharPool.length);
            password[i] = passwordCharPool[letterIndexInCharPool];
        }
        return password;
    }

    static void playGame() {
        int numberOfBlackPegs;
        int numberOfWhitePegs;
        do {
            attemptCount++;
            System.out.println("Podejście nr " + attemptCount + ". Podaj hasło");
            playersPassword = guessPassword();
            System.out.println("Sprawdzam podane hasło...");
            boolean[][] results = checkIfPasswordGuessed();
         //   System.out.println(Arrays.toString(results[0]));
         //   System.out.println(Arrays.toString(results[1]));
            numberOfBlackPegs = countNumberOfPegs(results, 1);
            numberOfWhitePegs = countNumberOfPegs(results, 2);
            System.out.println("Czarne pinezki: " + numberOfBlackPegs + ". Białe pinezki: " + numberOfWhitePegs + ".");
            if (numberOfBlackPegs == passwordLength) {
                System.out.println("Brawo. Odgadłeś hasło. Liczba prób: " + attemptCount);
            }
        } while (attemptCount < 10 && numberOfBlackPegs != passwordLength);
        attemptCount++;
        if (attemptCount > 10 && numberOfBlackPegs != passwordLength) {
            System.out.println("Niestety wyczerpałeś liczbę prób :(. Prawidłowe hasło to: " +
                    Arrays.toString(randomlySelectedPassword));
        }
    }

    static char[] guessPassword() {
        Scanner scanner = new Scanner(System.in);
        String playersPasswordAsString = scanner.nextLine();
        char[] passwordTry = new char[passwordLength];
        for (int i = 0; i < passwordLength; i++) {
            passwordTry[i] = playersPasswordAsString.charAt(i);
        }
        return passwordTry;
    }

    static boolean[][] checkIfPasswordGuessed() {
        boolean[][] pegArray = new boolean[2][4];
        for (int playerX = 0; playerX < pegArray[0].length; playerX++) {
            if (playersPassword[playerX] == randomlySelectedPassword[playerX]) {
                pegArray[0][playerX] = true;
                continue;
            }
            for (int passwordX = 0; passwordX < pegArray[1].length; passwordX++) {
                if (playersPassword[passwordX] == randomlySelectedPassword[playerX]) {
                    pegArray[1][passwordX] = true;
                }
            }
        }
        return pegArray;
    }

    static int countNumberOfPegs(boolean[][] pegs, int pegArrayRow) {
        int numberOfPegs = 0;
        for (int x = 0; x < pegs[pegArrayRow - 1].length; x++) {
            if (pegs[pegArrayRow - 1][x]) {
                numberOfPegs++;
            }
        }
        return numberOfPegs;
    }
}
