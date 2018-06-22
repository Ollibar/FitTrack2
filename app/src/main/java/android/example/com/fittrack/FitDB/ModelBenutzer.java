package android.example.com.fittrack.FitDB;

public class ModelBenutzer {
	private int benutzer_id;
	private String benutzer_name;
	private int benutzer_alter;
	private int benutzer_gewicht;


	public ModelBenutzer(){
	}

	public ModelBenutzer(String benutzer_name, int benutzer_alter, int benutzer_gewicht) {

		this.benutzer_name = benutzer_name;
		this.benutzer_alter = benutzer_alter;
		this.benutzer_gewicht = benutzer_gewicht;
	}


	public void setBenutzer_id(int benutzer_id) {
		this.benutzer_id = benutzer_id;
	}

	public int getBenutzer_id() {
		return this.benutzer_id;
	}

	public void setBenutzer_name(String benutzer_name) {
		this.benutzer_name = benutzer_name;
	}

	public String getBenutzer_name() {
		return this.benutzer_name;
	}

	public void setBenutzer_alter(int benutzer_alter) {
		this.benutzer_alter = benutzer_alter;
	}

	public int getBenutzer_alter() {
		return this.benutzer_alter;
	}

	public void setBenutzer_gewicht(int benutzer_gewicht) {
		this.benutzer_gewicht = benutzer_gewicht;
	}

	public int getBenutzer_gewicht() {
		return this.benutzer_gewicht;
	}

}
