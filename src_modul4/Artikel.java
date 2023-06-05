import java.util.GregorianCalendar;
public class Artikel {
    private String nummer;
    private String bezeichnung;
    private double einkaufspreis;
    private GregorianCalendar ablaufdatum;
    private int kategorienummer;

    public Artikel(String nummer, String bezeichnung, double einkaufspreis, GregorianCalendar ablaufdatum,
                   int kategorienummer) {
        super();
        this.nummer = nummer;
        this.bezeichnung = bezeichnung;
        this.einkaufspreis = einkaufspreis;
        this.ablaufdatum = ablaufdatum;
        this.kategorienummer = kategorienummer;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public double getEinkaufspreis() {
        return einkaufspreis;
    }

    public void setEinkaufspreis(double einkaufspreis) {
        this.einkaufspreis = einkaufspreis;
    }

    public GregorianCalendar getAblaufdatum() {
        return ablaufdatum;
    }

    public void setAblaufdatum(GregorianCalendar ablaufdatum) {
        this.ablaufdatum = ablaufdatum;
    }

    public int getKategorienummer() {
        return kategorienummer;
    }

    public void setKategorienummer(int kategorienummer) {
        this.kategorienummer = kategorienummer;
    }

    public String getAblaufDatum(){
        String output = "";
        output += ablaufdatum.get(GregorianCalendar.DAY_OF_MONTH) + ".";
        output += ablaufdatum.get(GregorianCalendar.MONTH) + ".";
        output += ablaufdatum.get(GregorianCalendar.YEAR) + ".";
        return output;
    }

}
