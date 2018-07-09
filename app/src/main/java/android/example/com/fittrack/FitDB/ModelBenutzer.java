package android.example.com.fittrack.FitDB;

/**
 * Javaklasse für die interne behandlung der daten
 */

public class ModelBenutzer {
	private int benutzer_id;
	private String benutzer_name;
	private int benutzer_alter;
	private int benutzer_gewicht;


	public ModelBenutzer(){
	}

	/**
	 * Contructor zur erstellung von ModelBenutzer-Objekte
	 * @param benutzer_name
	 * @param benutzer_alter
	 * @param benutzer_gewicht
	 */
	public ModelBenutzer(String benutzer_name, int benutzer_alter, int benutzer_gewicht) {

		this.benutzer_name = benutzer_name;
		this.benutzer_alter = benutzer_alter;
		this.benutzer_gewicht = benutzer_gewicht;
	}

	/**
	 * benutzer id setzen
	 * @param benutzer_id
	 */
	public void setBenutzer_id(int benutzer_id) {
		this.benutzer_id = benutzer_id;
	}

	/**
	 *
	 * @return int benutzer id
	 */
	public int getBenutzer_id() {
		return this.benutzer_id;
	}

	/**
	 * stammdaten: benutzer name
	 * @param benutzer_name
	 */
	public void setBenutzer_name(String benutzer_name) {
		this.benutzer_name = benutzer_name;
	}

	/**
	 *
	 * @return string benutzer name
	 */
	public String getBenutzer_name() {
		return this.benutzer_name;
	}

	/**
	 * stammdaten: alter
	 * @param benutzer_alter
	 */
	public void setBenutzer_alter(int benutzer_alter) {
		this.benutzer_alter = benutzer_alter;
	}

	/**
	 *
	 * @return int alter
	 */
	public int getBenutzer_alter() {
		return this.benutzer_alter;
	}

	/**
	 * stammdaten: benutzer gewicht
	 * @param benutzer_gewicht
	 */
	public void setBenutzer_gewicht(int benutzer_gewicht) {
		this.benutzer_gewicht = benutzer_gewicht;
	}

	/**
	 *
	 * @return int benutzer gewicht
	 */
	public int getBenutzer_gewicht() {
		return this.benutzer_gewicht;
	}


}
