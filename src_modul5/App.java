import java.util.Scanner;
import java.util.regex.*;

public class App {
    
    private static Arena game;

    public static Charakter charakterErstellung(int spielerZahl) {
        Scanner scan = new Scanner(System.in);
        String spielerName;
        String spielerTyp;
        boolean validChoice = false;
        int hoechstLaenge = 14;
        
        System.out.print("\n\n<><><><><><><><> Spieler " + spielerZahl + " <><><><><><><><>");

        do {
            System.out.print("\nWie heisst dein Charakter? ");
                spielerName = scan.nextLine();
            if (spielerName.length() > hoechstLaenge) {
                System.out.println("Name zu lang! Höchstens 14 Buchstaben!");
            }
        } while(spielerName.length() > hoechstLaenge);

        System.out.println("\nWelche Art von Charakter?");
        System.out.println("----------------------------");
        System.out.println("0 - Zwerg");
        System.out.println("1 - Drache");
        System.out.print("----------------------------");

        do {
            System.out.print("\nWahl treffen: ");
                spielerTyp = scan.nextLine();
            if (!Pattern.matches("-?[0-9]+", spielerTyp)) {
                System.out.println("Falsche Eingabe! Nur Ganzzahlenwerte erlaubt!");
            }
            else if (Integer.parseInt(spielerTyp) < 0 || Integer.parseInt(spielerTyp) > 1) {
                System.out.println("Falsche Eingabe! Nur [0] oder [1] möglich!");
            }
            else {
                validChoice = true;
            }
        } while(!validChoice);
        System.out.println("<><><><><><><><><><><><><><><><>");

        switch(Integer.parseInt(spielerTyp)) {
            case 0: return new Zwerg(spielerName);
            default: return new Drache(spielerName);
        }
    }

    public static void main ( String[] args ) {
        
        System.out.println("\n/------------------------------------\\");
        System.out.println("|             Willkommen!            |");
        System.out.println("\\------------------------------------/");

        game = new Arena(charakterErstellung(1), charakterErstellung(2));
        game.charakterRegelnAusgeben();
        game.ersterFight(); //randomized den start
        
        do {
            game.fight();
        } while(!game.istGameOver());

        System.out.println("\n/------------------------------------\\");
        System.out.println("|       Bis zum nächsten Mal!        |");
        System.out.println("\\------------------------------------/");

    }

}
