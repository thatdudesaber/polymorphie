import java.util.Scanner;
import java.util.regex.*;
import java.util.concurrent.ThreadLocalRandom;

public class Arena {
    
    private Charakter spieler1;
    private Charakter spieler2;
    private Charakter gewinner;

    private int angreiferBestimmung;
    private int runde;

    private boolean gameOver = false;

    public Arena(Charakter constrKaempfer1, Charakter constrKaempfer2) {
        spieler1 = constrKaempfer1;
        spieler2 = constrKaempfer2;
        runde = 1;
    }

    /**
     * Diese Methode führt den ersten Kampfablauf aus und ist nur dafür zuständig, dass
     * der Beginn Random ist.
     */
    public void ersterFight() {
        statusinformationenAusgeben();
        switch(ThreadLocalRandom.current().nextInt(0, 2)) {
            case 0: 
                kampfSimulieren(spieler1, spieler2);
                angreiferBestimmung = 1;
                break;
            case 1: 
                kampfSimulieren(spieler2, spieler1);
                angreiferBestimmung = 0;
                break;
        }
    }

    public void fight() {
        if (spieler1.getLebenspunkte() <= 0 || spieler2.getLebenspunkte() <= 0) {
            gameOver = true;
            spielFertig();
        }
        else {
            runde++;
            statusinformationenAusgeben();

            if (runde % 2 == angreiferBestimmung) {
                kampfSimulieren(spieler1, spieler2);
            }
            else {
                kampfSimulieren(spieler2, spieler1);
            }
        }
    }

    public void kampfSimulieren(Charakter angreifer, Charakter opfer) {
        String wahl;
        boolean validChoice = false;
        Scanner scan = new Scanner(System.in);

        if(angreifer instanceof Zwerg){
            System.out.println("\n***** Runde " + runde + ": " + angreifer.getName().toUpperCase() + " ist dran! *****");
            System.out.println("""
                ------------------------------------
                Was machst du diese Runde?
                1 - Angreifen
                2 - Spezialfähigkeit aktivieren
                3 - Spezialfähigkeit deaktivieren
                4 - Heiltrank benutzen
                ------------------------------------""");
        } else {
            System.out.println("\n***** Runde " + runde + ": " + angreifer.getName().toUpperCase() + " ist dran! *****");
            System.out.println("""
                ------------------------------------
                Was machst du diese Runde?
                1 - Angreifen
                2 - Spezialfähigkeit aktivieren
                3 - Spezialfähigkeit deaktivieren
                ------------------------------------""");
        }



        do {
            System.out.print("\nWahl treffen: ");
                wahl = scan.nextLine();
            if (!Pattern.matches("-?[0-9]+", wahl)) {
                System.out.println("Falsche Eingabe! Keine Buchstabeneingabe erlaubt!");
                continue;
            } else if (angreifer instanceof Zwerg && Integer.parseInt(wahl) < 1 | Integer.parseInt(wahl) > 4) {
                System.out.println("Falsche Eingabe! Nur [1], [2], [3] und [4] möglich!");
                continue;
            } else if (angreifer instanceof Drache && Integer.parseInt(wahl) < 1 | Integer.parseInt(wahl) > 3) {
                System.out.println("Falsche Eingabe! Nur [1], [2] und [3] möglich!");
                continue;
            }

            if (wahl.equals("2") && angreifer.istSpezialfaehigkeitAktiv()) {
                System.out.println("Spezialfähigkeit bereits aktiv!");
            }
            else if (wahl.equals("3") && !angreifer.istSpezialfaehigkeitAktiv()) {
                System.out.println("Spezialfähigkeit ist schon inaktiv!");
            }
            else if (wahl.equals("2") && angreifer instanceof Zwerg && angreifer.getLebenspunkte() > 50) {
                System.out.println("Zwerge können ihre Spezialfähigkeit nur unter 50 Lebenspunkten aktivieren!");
            }
            else {
                validChoice = true;
            }
        } while(!validChoice);
        
        switch(Integer.parseInt(wahl)) {
            case 1: angreifer.angreifen(opfer); break;
            case 2: angreifer.setSpezialfaehigkeit(true); break;
            case 3: angreifer.setSpezialfaehigkeit(false); break;
            case 4: angreifer.heilen(angreifer); break;
        }
    }

    public void charakterRegelnAusgeben() {
        System.out.println("\n\nCHARAKTERAUFBAU");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>\n");
        if (spieler1.getClass() == spieler2.getClass()) {
            System.out.println("Ihr seid beide: " + spieler1.getClass().getSimpleName() + "\n");
            spieler1.regelnAusgeben();
        }
        else {
            spieler1.regelnAusgeben();
            System.out.println();
            spieler2.regelnAusgeben();
        }
        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void statusinformationenAusgeben() {
        String output = "";
        String temp1 = "";
        String temp2 = "";
        String stars = "*****";
        String spaces = " ";
        int spacelength = 0;
        int maxlength = 50;

        temp1 = stars + " " + spieler1.getName() + " " + stars;
        temp2 = stars + " " + spieler2.getName() + " " + stars;
        spacelength = maxlength - temp1.length() - temp2.length();
        output += "\n" +  temp1 + spaces.repeat(spacelength) + temp2 + "\n";

        temp1 = "Typ: " + spieler1.getClass().getSimpleName().toUpperCase();
        temp2 = spaces.repeat(stars.length() / 2) + "Typ: " + spieler2.getClass().getSimpleName().toUpperCase();
        spacelength = maxlength - temp1.length() - temp2.length();
        output += temp1 + spaces.repeat(spacelength) + temp2 + "\n";

        temp1 = "Lebenspunkte: " + spieler1.getLebenspunkte();
        temp2 = spaces.repeat(stars.length() / 2) + "Lebenspunkte: " + spieler2.getLebenspunkte();
        spacelength = maxlength - temp1.length() - temp2.length();
        output += temp1 + spaces.repeat(spacelength) + temp2 + "\n";

        temp1 = "Spezial aktiv: " + spieler1.istSpezialfaehigkeitAktiv();
        temp2 = spaces.repeat(stars.length() / 2) + "Spezial aktiv: " + spieler2.istSpezialfaehigkeitAktiv();
        spacelength = maxlength - temp1.length() - temp2.length();
        output += temp1 + spaces.repeat(spacelength) + temp2 + "\n";

        System.out.println("\n" + output);
    }

    public void spielFertig() {
        if (spieler1.getLebenspunkte() < 0) {
            gewinner = spieler2;
        }
        else {
            gewinner = spieler2;
        }

        System.out.println("\n\n\n" + "<><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println(gewinner.getName().toUpperCase() + " HAT GEWONNEN!");
        System.out.println("Es wurde " + runde + " Runden lang gespielt.");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><>");
    }

    public boolean istGameOver() {
        return gameOver;
    }

}