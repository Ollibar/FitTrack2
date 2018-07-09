package android.example.com.fittrack.FitDB;

/**
 * Javaklasse für die interne behandlung der daten
 */
public class ModelStation {
    int station_id;
    String station_name;
    int station_typ;

    public ModelStation() {
    }

    /**
     * Konstrucktor
     * @param station_id
     * @param station_name
     * @param station_typ
     */
    public ModelStation(int station_id, String station_name, int station_typ) {
        this.station_id = station_id;
        this.station_name = station_name;
        this.station_typ = station_typ;
    }

    /**
     * Alternativer Constructor ohne ID
     * @param station_name
     * @param station_typ
     */
    public ModelStation(String station_name, int station_typ) {
        //this.station_id = station_id;
        this.station_name = station_name;
        this.station_typ = station_typ;
    }

    /**
     *
     * @return gibt die Station ID zurück
     */
    public int getStation_id() {
        return this.station_id;
    }

    /**
     *
     * @param station_id setzt die Station ID
     */
    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    /**
     * gibt den Staionsnamen zurück
     * @return
     */
    public String getStation_name() {
        return this.station_name;
    }

    /**
     *
     * @param station_name setzt den satationsnamen
     */
    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    /**
     *
     * @return gibt den stationstyp zurück 1= Kraft ; 2= Kardio
     */
    public int getStation_typ() {
        return this.station_typ;
    }

    /**
     *
     * @param station_typ setzt den gerätetyp
     */
    public void setStation_typ(int station_typ) {
        this.station_typ = station_typ;
    }
}
