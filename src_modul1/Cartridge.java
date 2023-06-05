public class Cartridge extends Medium {
	
	private boolean vollstaendigesSet;
	private String entwicklerstudio;
	private Konsolentyp konsole;
	
	public Cartridge(String titel, double wert, String standort, boolean vollstaendigesSet, String entwicklerstudio,
			Konsolentyp konsole) {
		super(titel, wert, standort);
		this.vollstaendigesSet = vollstaendigesSet;
		this.entwicklerstudio = entwicklerstudio;
		this.konsole = konsole;
	}

	public boolean isVollstaendigesSet() {
		return vollstaendigesSet;
	}

	public void setVollstaendigesSet(boolean vollstaendigesSet) {
		this.vollstaendigesSet = vollstaendigesSet;
	}

	public String getEntwicklerstudio() {
		return entwicklerstudio;
	}

	public void setEntwicklerstudio(String entwicklerstudio) {
		this.entwicklerstudio = entwicklerstudio;
	}

	public Konsolentyp getKonsole() {
		return konsole;
	}

	public void setKonsole(Konsolentyp konsole) {
		this.konsole = konsole;
	}
	
	public void anzeigen() //Überschreiben der Mutterklassenmethode
	{
		super.anzeigen();
		System.out.println("[Cartridge] Ist das Set vollständig? -> " + this.isVollstaendigesSet() + " | Entwicklerstudio -> " + this.getEntwicklerstudio() + " | Konsolentyp -> " + this.getKonsole());
	}
	
}
