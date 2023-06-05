public class App {

	public static void main(String[] args)
	{
		Medienbibliothek bib = new Medienbibliothek();
		
		VHS heman = new VHS("Aus einem Land vor unserer Zeit", 65.99, "Büro, UG", 120, "Sehr Gut");
		Cartridge zeldaGold = new Cartridge("DonkeyKong64", 120.00, "Keller, UG", true, "Nintendo", Konsolentyp.N64);
		Buch herr = new Buch("Der Herr der Ringe: Die Zwei Türme", 49.99, "Wohnzimmer, OG", "1234565432123");
		
		bib.mediumHinzufuegen(heman);
		bib.mediumHinzufuegen(zeldaGold);
		bib.mediumHinzufuegen(herr);
		
		bib.alleMedienAusgeben();
		

		System.out.println("\n--- alle VHS in der Liste ---");
		for(VHS vhs : bib.alleVHSalsListe())
		{
			vhs.anzeigen();
		}
	}
}