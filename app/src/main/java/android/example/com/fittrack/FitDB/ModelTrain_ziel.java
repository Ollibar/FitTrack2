package android.example.com.fittrack.FitDB;

public class ModelTrain_ziel {
	int train_ziel_id;
	int train_ziel_benutzer_id;
	int train_ziel_station_id;
	int train_ziel_soll_geschwindigkeit;
	int train_ziel_soll_dauer;
	int train_ziel_soll_gewicht;
	String train_ziel_pos1;
	String train_ziel_pos2;
	String train_ziel_pos3;

	public ModelTrain_ziel() {}
	public ModelTrain_ziel(int train_ziel_id,int train_ziel_benutzer_id,int train_ziel_station_id,int train_ziel_soll_geschwindigkeit,int train_ziel_soll_dauer,int train_ziel_soll_gewicht,String train_ziel_pos1,String train_ziel_pos2,String train_ziel_pos3) {
		this.train_ziel_id = train_ziel_id;
		this.train_ziel_benutzer_id = train_ziel_benutzer_id;
		this.train_ziel_station_id = train_ziel_station_id;
		this.train_ziel_soll_geschwindigkeit = train_ziel_soll_geschwindigkeit;
		this.train_ziel_soll_dauer = train_ziel_soll_dauer;
		this.train_ziel_soll_gewicht = train_ziel_soll_gewicht;
		this.train_ziel_pos1 = train_ziel_pos1;
		this.train_ziel_pos2 = train_ziel_pos2;
		this.train_ziel_pos3 = train_ziel_pos3;
	}
	public void setTrain_ziel_id(int train_ziel_id) {
		this.train_ziel_id = train_ziel_id;
	}
	public int getTrain_ziel_id() {
		return this.train_ziel_id;
	}
	public void setTrain_ziel_benutzer_id(int train_ziel_benutzer_id) {
		this.train_ziel_benutzer_id = train_ziel_benutzer_id;
	}
	public int getTrain_ziel_benutzer_id() {
		return this.train_ziel_benutzer_id;
	}
	public void setTrain_ziel_station_id(int train_ziel_station_id) {
		this.train_ziel_station_id = train_ziel_station_id;
	}
	public int getTrain_ziel_station_id() {
		return this.train_ziel_station_id;
	}
	public void setTrain_ziel_soll_geschwindigkeit(int train_ziel_soll_geschwindigkeit) {
		this.train_ziel_soll_geschwindigkeit = train_ziel_soll_geschwindigkeit;
	}
	public int getTrain_ziel_soll_geschwindigkeit() {
		return this.train_ziel_soll_geschwindigkeit;
	}
	public void setTrain_ziel_soll_dauer(int train_ziel_soll_dauer) {
		this.train_ziel_soll_dauer = train_ziel_soll_dauer;
	}
	public int getTrain_ziel_soll_dauer() {
		return this.train_ziel_soll_dauer;
	}
	public void setTrain_ziel_soll_gewicht(int train_ziel_soll_gewicht) {
		this.train_ziel_soll_gewicht = train_ziel_soll_gewicht;
	}
	public int getTrain_ziel_soll_gewicht() {
		return this.train_ziel_soll_gewicht;
	}
	public void setTrain_ziel_pos1(String train_ziel_pos1) {
		this.train_ziel_pos1 = train_ziel_pos1;
	}
	public String getTrain_ziel_pos1() {
		return this.train_ziel_pos1;
	}
	public void setTrain_ziel_pos2(String train_ziel_pos2) {
		this.train_ziel_pos2 = train_ziel_pos2;
	}
	public String getTrain_ziel_pos2() {
		return this.train_ziel_pos2;
	}
	public void setTrain_ziel_pos3(String train_ziel_pos3) {
		this.train_ziel_pos3 = train_ziel_pos3;
	}
	public String getTrain_ziel_pos3() {
		return this.train_ziel_pos3;
	}
}
