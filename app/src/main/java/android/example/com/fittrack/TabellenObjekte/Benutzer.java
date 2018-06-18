package android.example.com.fittrack.TabellenObjekte;

public class Benutzer {


    private String benutzername;
    private int alter,id;
    private double gewicht;




    public Benutzer(int id, String benutzername, int alter, double gewicht){
        this.alter=alter;
        this.gewicht=gewicht;
        this.benutzername=benutzername;
        this.id=id;

    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }


    @Override
    public String toString() {
        String s = "->ID: "+id+" Benutzername: "+ benutzername+" Gewicht: "+gewicht+" Alter: "+
                alter;
        return s;
    }
}
