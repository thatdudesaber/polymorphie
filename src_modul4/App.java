import java.util.ArrayList;
import java.util.GregorianCalendar;
public class App {

    public static void main(String[] args)
    {

        ArrayList<Artikel> al = artikellisteAusDBDummy();


        if(args.length>0)
        {
            String parameter = args[0];
            switch(parameter)
            {
                case "CSV":
                    CSV_Output(al);
                    break;
                case "JSON":
                    JSON_Output(al);
                    break;
                default:
                    CSV_Output(al);
                    break;
            }
        } else
        {
           CSV_Output(al);
        }
    }


    public static ArrayList<Artikel> artikellisteAusDBDummy() {
        ArrayList<Artikel> liste = new ArrayList<>();
        liste.add(new Artikel("1","Sekt",90.23,new GregorianCalendar(2019,3,4),24));
        liste.add(new Artikel("2","Fanta",9.90,new GregorianCalendar(2019,11,1),23));
        liste.add(new Artikel("3","Cola",9.90,new GregorianCalendar(2019,1,12),23));
        liste.add(new Artikel("4","Beinschinken",12.23,new GregorianCalendar(2019,11,12),45));
        liste.add(new Artikel("5","Speck",5.23,new GregorianCalendar(2018,2,3),45));
        return liste;
    }
    public static void CSV_Output(ArrayList<Artikel> liste){
        String output ="";

        output += "Nummer,Bezeichnung,Einkaufspreis,Ablaufdatum, Kategorienummer\n";

        for (int i=0; i< liste.size(); i++ ){

            output+= liste.get(i).getNummer() + ";"  ;
            output+= liste.get(i).getBezeichnung() + ";";
            output+= liste.get(i).getAblaufDatum() + ";";
            output+= liste.get(i).getEinkaufspreis() + ";";
            output+= liste.get(i).getKategorienummer()  + ";";
            output+= "\n";

        }
        System.out.println(output);
    }
    public static void  JSON_Output(ArrayList <Artikel> liste){
        String output = "[\n {\n";

        for (int i =0; i < liste.size(); i++){
            output += "Nummer\" : " + liste.get(i).getNummer() + ",\n";
            output += "Bezeichnung\" : \"" + liste.get(i).getBezeichnung() + ",\n";
            output += "Einkaufspreis\": " + liste.get(i).getEinkaufspreis() + ",\n";
            output += "Ablaufdatum \" : " + liste.get(i).getAblaufDatum() + ",\n";
            output += "Kategorienummer\": " + liste.get(i).getKategorienummer() + ",\n";
            if (i == liste.size() -1){
                output+= "      }\n";
            }
            else {
                output+= "         },\n";

            }
        }
        output += "]";

        System.out.println(output);
    }
}