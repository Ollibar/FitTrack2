package android.example.com.fittrack.FitDB;

public class ModelSatz {
	int satz_id;
	int satz_training_id;
	int satz_nr;
	int satz_gewicht;
	int satz_wiederholung;

	public ModelSatz (){}
	public ModelSatz(int satz_id,int satz_training_id, int satz_nr, int satz_gewicht, int satz_wiederholung) {
		this.satz_id = satz_id;
		this.satz_training_id = satz_training_id;
		this.satz_nr = satz_nr;
		this.satz_gewicht = satz_gewicht;
		this.satz_wiederholung = satz_wiederholung;
	}
	public void setSatz_id(int satz_id) {
		this.satz_id = satz_id;
	}
	public int getSatz_id() {
		return this.satz_id;
	}

	public void setSatz_training_id(int satz_training_id) {
		this.satz_training_id = satz_training_id;
	}
	public int getSatz_training_id() {
		return this.satz_training_id;
	}

	public void setSatz_nr(int satz_nr) {
		this.satz_nr = satz_nr;
	}
	public int getSatz_nr() {
		return this.satz_nr;
	}

	public void setSatz_gewicht(int satz_gewicht) {
		this.satz_gewicht = satz_gewicht;
	}
	public int getSatz_gewicht() {
		return this.satz_gewicht;
	}
	public void setSatz_wiederholung(int satz_wiederholung){
		this.satz_wiederholung =satz_wiederholung;
	}
	public int getSatz_wiederholung(){
		return this.satz_wiederholung;
	}
}
