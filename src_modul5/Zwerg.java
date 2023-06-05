import java.util.concurrent.ThreadLocalRandom;

public class Zwerg extends Charakter {

    private int anzahlHeals;

    public Zwerg(String constrName) {
        super(constrName);
        anzahlHeals = 1;
    }

    public void angreifen(Charakter gegner) {
        int lebenspunkte = 0;
        int chance = 0;
        int random = 0;
        
        int minSchaden = 15;
        int maxSchaden = 25;

        int schaden = ThreadLocalRandom.current().nextInt(minSchaden, maxSchaden + 1);

        if (this.istSpezialfaehigkeitAktiv()) {
            lebenspunkte = this.getLebenspunkte();
            if (lebenspunkte > 20 && lebenspunkte <= 50) {
                chance = 30;
            }
            else if (lebenspunkte > 10 && lebenspunkte <= 20) {
                chance = 50;
            }
            else if (lebenspunkte > 0 && lebenspunkte <= 10) {
                chance = 70;
            }

            random = ThreadLocalRandom.current().nextInt(0, 100) + 1;

            if (random < chance) {
                schaden += 2;
            }
            else {
                schaden /= 2;
            }
        }
        gegner.schadenNehmen(schaden);
    }

    private int getAnzahlHeals(){
        return anzahlHeals;
    }

    private void setAnzahlHeals(int heals){
        anzahlHeals = heals;
    }

    /**
     * Diese Methode wurde nach dem Testen des Programmes erstellt, da der Kampfablauf auch nach mehreren Versuchen furchtbar unbalanced war
     * und ich somit, anstatt die ATK-Power der Drachen-Klasse zu nerfen, lieber eine weitere Fähigkeit für die Klasse Zwerg hinzugefügt habe.
     */
    public void heilen(Charakter zwerg){
        if(getAnzahlHeals() > 0){
            int alteLebenspunkte = getLebenspunkte();
            int neueLebenspunkte = getLebenspunkte() + 50;

            if(neueLebenspunkte > 100){
                neueLebenspunkte = 100;
            }

            setLebenspunkte(neueLebenspunkte);

            int heilung = neueLebenspunkte - alteLebenspunkte;

            heilungBekommen(heilung, zwerg.getName());

            int neueAnzahlHeals = getAnzahlHeals() - 1;
            setAnzahlHeals(neueAnzahlHeals);
        } else {
            System.out.println("""
                    <><><><><><><><><><><><><><><><><><><><><><><>
                    Sie haben keinen Heiltrank mehr zur Verfügung!
                    <><><><><><><><><><><><><><><><><><><><><><><>""");
        }
    }

    public void heilungBekommen(int punkte, String name){
        System.out.println("\n<><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println(name + " heilt sich für " + punkte + " Punkte!");
        System.out.print("<><><><><><><><><><><><><><><><><><><><><><>");
    }

    public void regelnAusgeben() {
        System.out.println("""
                Als Zwerg hast du 100 Lebenspunkte.
                Außerdem besitzt du einen Heiltrank, der dich um 50% deiner maximalen Lebenspunkte heilt.
                Du machst zwischen 15 und 25 Schaden Punkte beim Angriff.
                Deine Spezialfähigkeit heisst "Zwergenkopfnuss".
                Sie kann nur aktiviert werden, wenn du weniger als 50 Lebenspunkte hast.
                Ist sie aktiviert, machst du zwar 5-10 Punkte weniger Schaden, hast aber 10 mehr Lebenspunkte.
                Abhaengig von deinen Lebenspunkten hast die Chance, doppelten Schaden zu machen:
                30 % ab einem Lebenspunkte-Wert von 50 oder weniger
                50 % ab einem Lebenspunkte-Wert von 20 oder weniger
                70 % ab einem Lebenspunkte-Wert von 10 oder weniger
                Triffst du nicht, wird dein Schaden für diese Runde halbiert
                """);
    }

}