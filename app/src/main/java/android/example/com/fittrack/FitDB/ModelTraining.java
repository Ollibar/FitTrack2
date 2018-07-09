package android.example.com.fittrack.FitDB;
/**
 *Javaklasse für die interne behandlung der daten
 */
public class ModelTraining {
    int training_id;
    String training_datum;
    int training_benutzer_id;
    int training_station_id;
    String training_beschreibung;
    int training_dauer;
    int training_geschwindigkeit;
    int training_kcal;
    int training_wiederholung;
    int training_gewicht;

    public ModelTraining() {
    }

    /**
     * Constructor
     * @param training_id
     * @param training_datum
     * @param training_benutzer_id
     * @param training_station_id
     * @param training_beschreibung
     * @param training_dauer
     * @param training_geschwindigkeit
     * @param training_kcal
     * @param training_wiederholung
     * @param training_gewicht
     */
    public ModelTraining(int training_id, String training_datum, int training_benutzer_id, int training_station_id, String training_beschreibung, int training_dauer, int training_geschwindigkeit, int training_kcal, int training_wiederholung, int training_gewicht) {
        this.training_id = training_id;
        this.training_datum = training_datum;
        this.training_benutzer_id = training_benutzer_id;
        this.training_station_id = training_station_id;
        this.training_beschreibung = training_beschreibung;
        this.training_dauer = training_dauer;
        this.training_geschwindigkeit = training_geschwindigkeit;
        this.training_kcal = training_kcal;
        this.training_gewicht = training_gewicht;
        this.training_wiederholung = training_wiederholung;
    }

    /**
     * Constructor ohne ID
     * @param training_datum
     * @param training_benutzer_id
     * @param training_station_id
     * @param training_beschreibung
     * @param training_dauer
     * @param training_geschwindigkeit
     * @param training_kcal
     * @param training_wiederholung
     * @param training_gewicht
     */
    public ModelTraining(String training_datum, int training_benutzer_id, int training_station_id, String training_beschreibung, int training_dauer, int training_geschwindigkeit, int training_kcal, int training_wiederholung, int training_gewicht) {
        //this.training_id = training_id;
        this.training_datum = training_datum;
        this.training_benutzer_id = training_benutzer_id;
        this.training_station_id = training_station_id;
        this.training_beschreibung = training_beschreibung;
        this.training_dauer = training_dauer;
        this.training_geschwindigkeit = training_geschwindigkeit;
        this.training_kcal = training_kcal;
        this.training_gewicht = training_gewicht;
        this.training_wiederholung = training_wiederholung;
    }

    /**
     *
     * @return gibt die TrainingsID zurück
     */
    public int getTraining_id() {
        return this.training_id;
    }

    /**
     * setzt die TrainingsID
     * @param training_id
     */
    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }

    /**
     *
     * @return gibt das Trainingsdatum zurück
     */
    public String getTraining_datum() {
        return String.valueOf( this.training_datum );
    }

    /**
     *
     * @param training_datum setzt das Trainingsdatum
     */
    public void setTraining_datum(String training_datum) {
        this.training_datum = training_datum;
    }

    /**
     *
     * @return gibt die Benutzer ID der Benutzers zurück
     */
    public int getTraining_benutzer_id() {
        return this.training_benutzer_id;
    }

    /**
     *
     * @param training_benutzer_id setzt die Benutzer ID
     */
    public void setTraining_benutzer_id(int training_benutzer_id) {
        this.training_benutzer_id = training_benutzer_id;
    }

    /**
     *
     * @return gibt die Station ID zurück
     */
    public int getTraining_station_id() {
        return this.training_station_id;
    }

    /**
     *
     * @param training_station_id setzt die Station ID
     */
    public void setTraining_station_id(int training_station_id) {
        this.training_station_id = training_station_id;
    }

    /**
     *
     * @return gibt die Beschreibung zurück
     */
    public String getTraining_beschreibung() {
        return this.training_beschreibung;
    }

    /**
     *
     * @param training_beschreibung setzt die Beschreibung
     */
    public void setTraining_beschreibung(String training_beschreibung) {
        this.training_beschreibung = training_beschreibung;
    }

    /**
     *
     * @return gibt die Trainingsdauer zurück
     */
    public int getTraining_dauer() {
        return this.training_dauer;
    }

    /**
     *
     * @param training_dauer setzt die Trainingsdauer
     */
    public void setTraining_dauer(int training_dauer) {
        this.training_dauer = training_dauer;
    }

    /**
     *
     * @return gibt die geschwindigkeit zurück
     */
    public int getTraining_geschwindigkeit() {
        return this.training_geschwindigkeit;
    }

    /**
     *
     * @param training_geschwindigkeit setzt die Trainingsgeschwindigkeit
     */
    public void setTraining_geschwindigkeit(int training_geschwindigkeit) {
        this.training_geschwindigkeit = training_geschwindigkeit;
    }

    /**
     *
     * @return gibt kcal zurück
     */
    public int getTraining_kcal() {
        return this.training_kcal;
    }

    /**
     *
     * @param kcal setzt kcal
     */
    public void setTraining_kcal(int kcal) {
        this.training_kcal = kcal;

    }

    /**
     *
     * @return gibt die ANzahl der Wiederholungen zurück
     */
    public int getTraining_wiederholung() {
        return this.training_wiederholung;
    }

    /**
     *
     * @param training_wiederholung setzt die Wiederholungsanzahl
     */
    public void setTraining_wiederholung(int training_wiederholung) {
        this.training_wiederholung = training_wiederholung;
    }

    /**
     *
     * @return gibt das Gewicht zurück
     */
    public int getTraining_gewicht() {
        return training_gewicht;
    }

    /**
     *
     * @param training_gewicht setzt das Gewicht
     */
    public void setTraining_gewicht(int training_gewicht) {
        this.training_gewicht = training_gewicht;
    }
}
