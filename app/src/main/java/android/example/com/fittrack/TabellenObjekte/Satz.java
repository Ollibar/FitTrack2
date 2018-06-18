package android.example.com.fittrack.TabellenObjekte;

public class Satz {

    private int satzID,traningID,satzNr,wiederholung;
    private double satzGewicht;


    public Satz(int satzID,int traningID,int satzNr,double satzGewicht,int wiederholung){
        this.satzID=satzID;
        this.traningID=traningID;
        this.satzNr=satzNr;
        this.satzGewicht=satzGewicht;
        this.wiederholung=wiederholung;

    }

    public int getSatzID() {
        return satzID;
    }

    public void setSatzID(int satzID) {
        this.satzID = satzID;
    }

    public int getTraningID() {
        return traningID;
    }

    public void setTraningID(int traningID) {
        this.traningID = traningID;
    }

    public int getSatzNr() {
        return satzNr;
    }

    public void setSatzNr(int satzNr) {
        this.satzNr = satzNr;
    }

    public int getWiederholung() {
        return wiederholung;
    }

    public void setWiederholung(int wiederholung) {
        this.wiederholung = wiederholung;
    }

    public double getSatzGewicht() {
        return satzGewicht;
    }

    public void setSatzGewicht(double satzGewicht) {
        this.satzGewicht = satzGewicht;
    }

    @Override
    public String toString() {
        String s = "-> SatzID: "+satzID+" TrainingID: "+traningID+" SatzNR: "+satzNr+
                " SatzGewicht: "+ satzGewicht+" Wiederholung: "+ wiederholung;
        return s;

    }
}
