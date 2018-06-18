package android.example.com.fittrack.TabellenObjekte;

public class Station {

    private int stationID;
    private String stationName,stationTyp;


    public Station(int stationID,String stationName,String stationTyp){
        this.stationID=stationID;
        this.stationName=stationName;
        this.stationTyp=stationTyp;

    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationTyp() {
        return stationTyp;
    }

    public void setStationTyp(String stationTyp) {
        this.stationTyp = stationTyp;
    }

    @Override
    public String toString() {

        String s = "->StationID: "+stationID+" StationName: "+stationName+" StationTyp: "+stationTyp;

        return s;
    }
}
