package android.example.com.fittrack.TabellenObjekte;

import java.time.LocalDate;

public class Training {


    private int trainingID,stationID,dauer;
    private String benutzerName,beschreibung;
    private double geschwindigkeit;
    private LocalDate datum; //YYYY-MM-DD Localdate.now();-> aktuelles Datum



    public Training(int trainingID, int stationID,String benutzerName,String beschreibung,int dauer,
                    double geschwindigkeit, LocalDate datum ){
        this.trainingID = trainingID;
        this.stationID=stationID;
        this.benutzerName=benutzerName;
        this.beschreibung=beschreibung;
        this.dauer=dauer;
        this.geschwindigkeit=geschwindigkeit;
        this.datum=datum;
    }




    public int getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(int trainingID) {
        this.trainingID = trainingID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setGeschwindigkeit(double geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }


    @Override
    public String toString() {
        String s = "-> TrainingID: "+ trainingID+" StationID: "+stationID+" Benutzername: "+
                benutzerName+" Beschreibung: "+beschreibung+" Dauer: "+ dauer+ " Geschwindigkeit: "
                +geschwindigkeit+" Datum: "+datum;
        return s;
    }

}
