package android.example.com.fittrack.FitDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String LOG = DatabaseHelper.class.getName();
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "fitDB.db";
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private static final String CREATE_TABLE_BENUTZER = "CREATE TABLE benutzer (" + 
	"benutzer_id INTEGER PRIMARY KEY," + 
	"benutzer_name VARCHAR," + 
	"benutzer_alter INTEGER," + 
	"benutzer_gewicht INTEGER	" + 
	")";

	private static final String CREATE_TABLE_TRAINING = "CREATE TABLE training (" + 
	"training_id INTEGER PRIMARY KEY," + 
	"training_datum DATE," + 
	"training_benutzer_id INTEGER," + 
	"training_station_id INTEGER," + 
	"training_beschreibung VARCHAR," + 
	"training_dauer INTEGER," + 
	"training_geschwindigkeit INTEGER	" + 
	")";

	private static final String CREATE_TABLE_SATZ = "CREATE TABLE satz (" + 
	"satz_id INTEGER PRIMARY KEY," + 
	"satz_training_id INTEGER," + 
	"satz_nr INTEGER," + 
	"satz_gewicht INTEGER	" + 
	")";

	private static final String CREATE_TABLE_STATION = "CREATE TABLE station (" + 
	"station_id INTEGER PRIMARY KEY," +
	"station_name VARCHAR," + 
	"station_typ INTEGER	" + 
	")";

	private static final String CREATE_TABLE_TRAIN_ZIEL = "CREATE TABLE train_ziel (" + 
	"train_ziel_id INTEGER PRIMARY KEY," +
	"train_ziel_benutzer_id INTEGER," + 
	"train_ziel_station_id INTEGER," + 
	"train_ziel_soll_geschwindigkeit INTEGER," + 
	"train_ziel_soll_dauer INTEGER," + 
	"train_ziel_soll_gewicht INTEGER," + 
	"train_ziel_pos1 VARCHAR," + 
	"train_ziel_pos2 VARCHAR," + 
	"train_ziel_pos3 VARCHAR	" + 
	")";


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_BENUTZER);
		db.execSQL(CREATE_TABLE_TRAINING);
		db.execSQL(CREATE_TABLE_SATZ);
		db.execSQL(CREATE_TABLE_STATION);
		db.execSQL(CREATE_TABLE_TRAIN_ZIEL);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS benutzer");
		db.execSQL("DROP TABLE IF EXISTS training");
		db.execSQL("DROP TABLE IF EXISTS satz");
		db.execSQL("DROP TABLE IF EXISTS station");
		db.execSQL("DROP TABLE IF EXISTS train_ziel");
		onCreate(db);
	}


	public void clear(SQLiteDatabase db) {
		db.execSQL("DELETE FROM benutzer");
		db.execSQL("DELETE FROM training");
		db.execSQL("DELETE FROM satz");
		db.execSQL("DELETE FROM station");
		db.execSQL("DELETE FROM train_ziel");
	}


	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen()) db.close();
	}

	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

	public long createBenutzer(ModelBenutzer benutzer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		//values.put("benutzer_id", benutzer.getBenutzer_id());
		values.put("benutzer_name", benutzer.getBenutzer_name());
		values.put("benutzer_alter", benutzer.getBenutzer_alter());
		values.put("benutzer_gewicht", benutzer.getBenutzer_gewicht());
		return db.insertOrThrow("benutzer", null, values);
	}

	public int updateBenutzer(ModelBenutzer benutzer) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("benutzer_id", benutzer.getBenutzer_id());
		values.put("benutzer_name", benutzer.getBenutzer_name());
		values.put("benutzer_alter", benutzer.getBenutzer_alter());
		values.put("benutzer_gewicht", benutzer.getBenutzer_gewicht());
		return db.update("benutzer", values, "benutzer_id = ?", new String[] {String.valueOf(benutzer.getBenutzer_id())});
	}

	public int deleteBenutzer(ModelBenutzer benutzer) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("benutzer", "benutzer_id = ?", new String[] {String.valueOf(benutzer.getBenutzer_id())});
	}

	protected ModelBenutzer getModelBenutzerFromCursor(Cursor c){
		ModelBenutzer benutzer = new ModelBenutzer();
		benutzer.setBenutzer_id(c.getInt(c.getColumnIndex("benutzer_id")));
		benutzer.setBenutzer_name(c.getString(c.getColumnIndex("benutzer_name")));
		benutzer.setBenutzer_alter(c.getInt(c.getColumnIndex("benutzer_alter")));
		benutzer.setBenutzer_gewicht(c.getInt(c.getColumnIndex("benutzer_gewicht")));
		return benutzer;
	}

	public ModelBenutzer getBenutzer(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM benutzer Where benutzer_id =  ?";
		Cursor c = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });
		if (c != null) c.moveToFirst();
		return getModelBenutzerFromCursor(c);
	}

	public ArrayList<ModelBenutzer> getAllBenutzer() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ModelBenutzer> benutzerList = new ArrayList<>();
		String selectQuery = "SELECT * FROM benutzer";
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				ModelBenutzer benutzer = getModelBenutzerFromCursor(c);
				benutzerList.add(benutzer);
			} while (c.moveToNext());
		}
		return benutzerList;
	}

	public int getBenutzerCount() {
		String countQuery = "SELECT * FROM benutzer";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	public long createTraining(ModelTraining training) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("training_id", training.getTraining_id());
		values.put("training_datum", String.valueOf( training.getTraining_datum() ) );
		values.put("training_benutzer_id", training.getTraining_benutzer_id());
		values.put("training_station_id", training.getTraining_station_id());
		values.put("training_beschreibung", training.getTraining_beschreibung());
		values.put("training_dauer", training.getTraining_dauer());
		values.put("training_geschwindigkeit", training.getTraining_geschwindigkeit());
		return db.insertOrThrow("training", null, values);
	}

	public int updateTraining(ModelTraining training) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("training_id", training.getTraining_id());
		values.put("training_datum", String.valueOf( training.getTraining_datum() ) );
		values.put("training_benutzer_id", training.getTraining_benutzer_id());
		values.put("training_station_id", training.getTraining_station_id());
		values.put("training_beschreibung", training.getTraining_beschreibung());
		values.put("training_dauer", training.getTraining_dauer());
		values.put("training_geschwindigkeit", training.getTraining_geschwindigkeit());
		return db.update("training", values, "training_id = ?", new String[] {String.valueOf(training.getTraining_id())});
	}

	public int deleteTraining(ModelTraining training) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("training", "training_id = ?", new String[] {String.valueOf(training.getTraining_id())});
	}

	protected ModelTraining getModelTrainingFromCursor(Cursor c){
		ModelTraining training = new ModelTraining();
		training.setTraining_id(c.getInt(c.getColumnIndex("training_id")));
		training.setTraining_datum(c.getString(c.getColumnIndex("training_datum")));
		training.setTraining_benutzer_id(c.getInt(c.getColumnIndex("training_benutzer_id")));
		training.setTraining_station_id(c.getInt(c.getColumnIndex("training_station_id")));
		training.setTraining_beschreibung(c.getString(c.getColumnIndex("training_beschreibung")));
		training.setTraining_dauer(c.getInt(c.getColumnIndex("training_dauer")));
		training.setTraining_geschwindigkeit(c.getInt(c.getColumnIndex("training_geschwindigkeit")));
		return training;
	}

	public ModelTraining getTraining(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM training Where training_id =  ?";
		Cursor c = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });
		if (c != null) c.moveToFirst();
		return getModelTrainingFromCursor(c);
	}

	public ArrayList<ModelTraining> getAllTraining() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ModelTraining> trainingList = new ArrayList<ModelTraining>();
		String selectQuery = "SELECT * FROM training";
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				ModelTraining training = getModelTrainingFromCursor(c);
				trainingList.add(training);
			} while (c.moveToNext());
		}
		return trainingList;
	}

	public int getTrainingCount() {
		String countQuery = "SELECT * FROM training";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	public long createSatz(ModelSatz satz) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("satz_id", satz.getSatz_id());
		values.put("satz_training_id", satz.getSatz_training_id());
		values.put("satz_nr", satz.getSatz_nr());
		values.put("satz_gewicht", satz.getSatz_gewicht());
		return db.insertOrThrow("satz", null, values);
	}

	public int updateSatz(ModelSatz satz) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("satz_id", satz.getSatz_id());
		values.put("satz_training_id", satz.getSatz_training_id());
		values.put("satz_nr", satz.getSatz_nr());
		values.put("satz_gewicht", satz.getSatz_gewicht());
		return db.update("satz", values, "satz_id = ?", new String[] {String.valueOf(satz.getSatz_id())});
	}

	public int deleteSatz(ModelSatz satz) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("satz", "satz_id = ?", new String[] {String.valueOf(satz.getSatz_id())});
	}

	protected ModelSatz getModelSatzFromCursor(Cursor c){
		ModelSatz satz = new ModelSatz();
		satz.setSatz_id(c.getInt(c.getColumnIndex("satz_id")));
		satz.setSatz_training_id(c.getInt(c.getColumnIndex("satz_training_id")));
		satz.setSatz_nr(c.getInt(c.getColumnIndex("satz_nr")));
		satz.setSatz_gewicht(c.getInt(c.getColumnIndex("satz_gewicht")));
		return satz;
	}

	public ModelSatz getSatz(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM satz Where satz_id =  ?";
		Cursor c = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });
		if (c != null) c.moveToFirst();
		return getModelSatzFromCursor(c);
	}

	public ArrayList<ModelSatz> getAllSatz() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ModelSatz> satzList = new ArrayList<ModelSatz>();
		String selectQuery = "SELECT * FROM satz";
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				ModelSatz satz = getModelSatzFromCursor(c);
				satzList.add(satz);
			} while (c.moveToNext());
		}
		return satzList;
	}

	public int getSatzCount() {
		String countQuery = "SELECT * FROM satz";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	public long createStation(ModelStation station) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("station_name", station.getStation_name());
		values.put("station_typ", station.getStation_typ());
		return db.insertOrThrow("station", null, values);
	}

	public int updateStation(ModelStation station) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("station_id", station.getStation_id());
		values.put("station_name", station.getStation_name());
		values.put("station_typ", station.getStation_typ());
		return db.update("station", values, "station_id = ?", new String[] {String.valueOf(station.getStation_id())});
	}

	public int deleteStation(ModelStation station) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("station", "station_id = ?", new String[] {String.valueOf(station.getStation_id())});
	}

	protected ModelStation getModelStationFromCursor(Cursor c){
		ModelStation station = new ModelStation();
		station.setStation_id(c.getInt(c.getColumnIndex("station_id")));
		station.setStation_name(c.getString(c.getColumnIndex("station_name")));
		station.setStation_typ(c.getInt(c.getColumnIndex("station_typ")));
		return station;
	}

	public ModelStation getStation(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM station Where station_id =  ?";
		Cursor c = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });
		if (c != null) c.moveToFirst();
		return getModelStationFromCursor(c);
	}

	public ArrayList<ModelStation> getAllStation() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ModelStation> stationList = new ArrayList<ModelStation>();
		String selectQuery = "SELECT * FROM station";
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				ModelStation station = getModelStationFromCursor(c);
				stationList.add(station);
			} while (c.moveToNext());
		}
		return stationList;
	}

	public int getStationCount() {
		String countQuery = "SELECT * FROM station";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	public long createTrain_ziel(ModelTrain_ziel train_ziel) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("train_ziel_id", train_ziel.getTrain_ziel_id());
		values.put("train_ziel_benutzer_id", train_ziel.getTrain_ziel_benutzer_id());
		values.put("train_ziel_station_id", train_ziel.getTrain_ziel_station_id());
		values.put("train_ziel_soll_geschwindigkeit", train_ziel.getTrain_ziel_soll_geschwindigkeit());
		values.put("train_ziel_soll_dauer", train_ziel.getTrain_ziel_soll_dauer());
		values.put("train_ziel_soll_gewicht", train_ziel.getTrain_ziel_soll_gewicht());
		values.put("train_ziel_pos1", train_ziel.getTrain_ziel_pos1());
		values.put("train_ziel_pos2", train_ziel.getTrain_ziel_pos2());
		values.put("train_ziel_pos3", train_ziel.getTrain_ziel_pos3());
		return db.insertOrThrow("train_ziel", null, values);
	}

	public int updateTrain_ziel(ModelTrain_ziel train_ziel) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("train_ziel_id", train_ziel.getTrain_ziel_id());
		values.put("train_ziel_benutzer_id", train_ziel.getTrain_ziel_benutzer_id());
		values.put("train_ziel_station_id", train_ziel.getTrain_ziel_station_id());
		values.put("train_ziel_soll_geschwindigkeit", train_ziel.getTrain_ziel_soll_geschwindigkeit());
		values.put("train_ziel_soll_dauer", train_ziel.getTrain_ziel_soll_dauer());
		values.put("train_ziel_soll_gewicht", train_ziel.getTrain_ziel_soll_gewicht());
		values.put("train_ziel_pos1", train_ziel.getTrain_ziel_pos1());
		values.put("train_ziel_pos2", train_ziel.getTrain_ziel_pos2());
		values.put("train_ziel_pos3", train_ziel.getTrain_ziel_pos3());
		return db.update("train_ziel", values, "id = ?", new String[] {String.valueOf(train_ziel.getTrain_ziel_id())});
	}

	public int deleteTrain_ziel(ModelTrain_ziel train_ziel) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("train_ziel", "id = ?", new String[] {String.valueOf(train_ziel.getTrain_ziel_id())});
	}

	protected ModelTrain_ziel getModelTrain_zielFromCursor(Cursor c){
		ModelTrain_ziel train_ziel = new ModelTrain_ziel();
		train_ziel.setTrain_ziel_id(c.getInt(c.getColumnIndex("train_ziel_id")));
		train_ziel.setTrain_ziel_benutzer_id(c.getInt(c.getColumnIndex("train_ziel_benutzer_id")));
		train_ziel.setTrain_ziel_station_id(c.getInt(c.getColumnIndex("train_ziel_station_id")));
		train_ziel.setTrain_ziel_soll_geschwindigkeit(c.getInt(c.getColumnIndex("train_ziel_soll_geschwindigkeit")));
		train_ziel.setTrain_ziel_soll_dauer(c.getInt(c.getColumnIndex("train_ziel_soll_dauer")));
		train_ziel.setTrain_ziel_soll_gewicht(c.getInt(c.getColumnIndex("train_ziel_soll_gewicht")));
		train_ziel.setTrain_ziel_pos1(c.getString(c.getColumnIndex("train_ziel_pos1")));
		train_ziel.setTrain_ziel_pos2(c.getString(c.getColumnIndex("train_ziel_pos2")));
		train_ziel.setTrain_ziel_pos3(c.getString(c.getColumnIndex("train_ziel_pos3")));
		return train_ziel;
	}

	public ModelTrain_ziel getTrain_ziel(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM train_ziel Where id =  ?";
		Cursor c = db.rawQuery(selectQuery, new String[] { String.valueOf(id) });
		if (c != null) c.moveToFirst();
		return getModelTrain_zielFromCursor(c);
	}

	public ArrayList<ModelTrain_ziel> getAllTrain_ziel() {
		SQLiteDatabase db = this.getReadableDatabase();
		ArrayList<ModelTrain_ziel> train_zielList = new ArrayList<ModelTrain_ziel>();
		String selectQuery = "SELECT * FROM train_ziel";
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				ModelTrain_ziel train_ziel = getModelTrain_zielFromCursor(c);
				train_zielList.add(train_ziel);
			} while (c.moveToNext());
		}
		return train_zielList;
	}

	public int getTrain_zielCount() {
		String countQuery = "SELECT * FROM train_ziel";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}

	public void sqlquery(String q){
		SQLiteDatabase db = this.getWritableDatabase();
		db.rawQuery( q,null );

	}

}