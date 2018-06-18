package android.example.com.fittrack.TabellenObjekte;

public class TraningsZiel {

    private int trainingsZielID,stationID,sollDauer;
    private double sollGewicht,sollGeschw;
    private String benutzerName,pos1,pos2,pos3;


    public TraningsZiel(int trainingsZielID,int stationID,String benutzerName,int sollDauer,
                        double sollGewicht,double sollGeschw,String pos1,String pos2,String pos3){
        this.trainingsZielID=trainingsZielID;
        this.stationID=stationID;
        this.benutzerName=benutzerName;
        this.sollDauer=sollDauer;
        this.sollGewicht=sollGewicht;
        this.sollGeschw=sollGeschw;
        this.pos1=pos1;
        this.pos2=pos2;
        this.pos3=pos3;
    }

    public int getTrainingsZielID() {
        return trainingsZielID;
    }

    public void setTrainingsZielID(int trainingsZielID) {
        this.trainingsZielID = trainingsZielID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public int getSollDauer() {
        return sollDauer;
    }

    public void setSollDauer(int sollDauer) {
        this.sollDauer = sollDauer;
    }

    public double getSollGewicht() {
        return sollGewicht;
    }

    public void setSollGewicht(double sollGewicht) {
        this.sollGewicht = sollGewicht;
    }

    public double getSollGeschw() {
        return sollGeschw;
    }

    public void setSollGeschw(double sollGeschw) {
        this.sollGeschw = sollGeschw;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }

    public String getPos1() {
        return pos1;
    }

    public void setPos1(String pos1) {
        this.pos1 = pos1;
    }

    public String getPos2() {
        return pos2;
    }

    public void setPos2(String pos2) {
        this.pos2 = pos2;
    }

    public String getPos3() {
        return pos3;
    }

    public void setPos3(String pos3) {
        this.pos3 = pos3;
    }

    @Override
    public String toString() {
        String s = "-> TrainingszielID: "+trainingsZielID+" StationID: "+stationID+" Benutzername: "+
                benutzerName+" Solldauer: "+sollDauer+" Sollgewicht: "+ sollGewicht+" Sollgeschwindigkeit: "
                +sollGeschw+" Pos1: "+pos1+" Pos2: "+pos2+"Pos3: "+pos3;
        return s;
    }
}
