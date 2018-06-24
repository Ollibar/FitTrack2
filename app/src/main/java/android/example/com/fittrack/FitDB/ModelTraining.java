package android.example.com.fittrack.FitDB;

import java.util.Date;

public class ModelTraining {
	int training_id;
	String training_datum;
	int training_benutzer_id;
	int training_station_id;
	String training_beschreibung;
	int training_dauer;
	int training_geschwindigkeit;
	int training_kcal;

	public ModelTraining() {}
	public ModelTraining(int training_id,String training_datum,int training_benutzer_id,int training_station_id,String training_beschreibung,int training_dauer,int training_geschwindigkeit, int training_kcal) {
		this.training_id = training_id;
		this.training_datum = training_datum;
		this.training_benutzer_id = training_benutzer_id;
		this.training_station_id = training_station_id;
		this.training_beschreibung = training_beschreibung;
		this.training_dauer = training_dauer;
		this.training_geschwindigkeit = training_geschwindigkeit;
		this.training_kcal = training_kcal;
	}
	public ModelTraining(String training_datum,int training_benutzer_id,int training_station_id,String training_beschreibung,int training_dauer,int training_geschwindigkeit, int training_kcal) {
		//this.training_id = training_id;
		this.training_datum = training_datum;
		this.training_benutzer_id = training_benutzer_id;
		this.training_station_id = training_station_id;
		this.training_beschreibung = training_beschreibung;
		this.training_dauer = training_dauer;
		this.training_geschwindigkeit = training_geschwindigkeit;
		this.training_kcal = training_kcal;
	}

	public void setTraining_id(int training_id) {
		this.training_id = training_id;
	}
	public int getTraining_id() {
		return this.training_id;
	}
	public void setTraining_datum(String training_datum) {
		this.training_datum = training_datum;
	}
	public String getTraining_datum() {
		return String.valueOf( this.training_datum );
	}
	public void setTraining_benutzer_id(int training_benutzer_id) {
		this.training_benutzer_id = training_benutzer_id;
	}
	public int getTraining_benutzer_id() {
		return this.training_benutzer_id;
	}
	public void setTraining_station_id(int training_station_id) {
		this.training_station_id = training_station_id;
	}
	public int getTraining_station_id() {
		return this.training_station_id;
	}
	public void setTraining_beschreibung(String training_beschreibung) {
		this.training_beschreibung = training_beschreibung;
	}
	public String getTraining_beschreibung() {
		return this.training_beschreibung;
	}
	public void setTraining_dauer(int training_dauer) {
		this.training_dauer = training_dauer;
	}
	public int getTraining_dauer() {
		return this.training_dauer;
	}
	public void setTraining_geschwindigkeit(int training_geschwindigkeit) {
		this.training_geschwindigkeit = training_geschwindigkeit;
	}
	public int getTraining_geschwindigkeit() {
		return this.training_geschwindigkeit;
	}
	public void setTraining_kcal(int kcal){
		this.training_kcal = kcal;

	}
	public int getTraining_kcal(){
		return this.training_kcal;
	}
}
