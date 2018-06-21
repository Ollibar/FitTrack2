package android.example.com.fittrack.FitDB;

public class ModelStation {
	int station_id;
	String station_name;
	int station_typ;

	public ModelStation() {}
	public ModelStation(int station_id,String station_name,int station_typ) {
		this.station_id = station_id;
		this.station_name = station_name;
		this.station_typ = station_typ;
	}
	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}
	public int getStation_id() {
		return this.station_id;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
	public String getStation_name() {
		return this.station_name;
	}
	public void setStation_typ(int station_typ) {
		this.station_typ = station_typ;
	}
	public int getStation_typ() {
		return this.station_typ;
	}
}
