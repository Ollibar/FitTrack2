package android.example.com.fittrack.FitDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = DatabaseHelper.class.getName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "fitDB.db";
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
            "training_dauer INTEGER DEFAULT 0," +
            "training_geschwindigkeit INTEGER DEFAULT 0,	" +
            "training_kcal INTEGER DEFAULT 0,	" +
            "training_wiederholung INTEGER DEFAULT 0,	" +
            "training_gewicht INTEGER DEFAULT 0	" +
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
            "train_ziel_soll_dauer INTEGER ," +
            "train_ziel_soll_gewicht INTEGER ," +
            "train_ziel_gewicht_wunsch INTEGER ," +
            "train_ziel_pos1 VARCHAR ," +
            "train_ziel_pos2 VARCHAR ," +
            "train_ziel_pos3 VARCHAR " +
            ")";

    public DatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( CREATE_TABLE_BENUTZER );
        db.execSQL( CREATE_TABLE_TRAINING );
        db.execSQL( CREATE_TABLE_STATION );
        db.execSQL( CREATE_TABLE_TRAIN_ZIEL );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS benutzer" );
        db.execSQL( "DROP TABLE IF EXISTS training" );
        db.execSQL( "DROP TABLE IF EXISTS station" );
        db.execSQL( "DROP TABLE IF EXISTS train_ziel" );
        onCreate( db );
    }

    public void clear(SQLiteDatabase db) {
        db.execSQL( "DELETE FROM benutzer" );
        db.execSQL( "DELETE FROM training" );
        db.execSQL( "DELETE FROM station" );
        db.execSQL( "DELETE FROM train_ziel" );
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) db.close();
    }

    // Methoden für die Tabelle benutzer

    public long createBenutzer(ModelBenutzer benutzer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "benutzer_name", benutzer.getBenutzer_name() );
        values.put( "benutzer_alter", benutzer.getBenutzer_alter() );
        values.put( "benutzer_gewicht", benutzer.getBenutzer_gewicht() );
        return db.insertOrThrow( "benutzer", null, values );
    }

    public int updateBenutzer(ModelBenutzer benutzer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "benutzer_name", benutzer.getBenutzer_name() );
        values.put( "benutzer_alter", benutzer.getBenutzer_alter() );
        values.put( "benutzer_gewicht", benutzer.getBenutzer_gewicht() );
        return db.update( "benutzer", values, "benutzer_id = ?", new String[]{String.valueOf( benutzer.getBenutzer_id() )} );
    }

    public int deleteBenutzer(ModelBenutzer benutzer) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( "benutzer", "benutzer_id = ?", new String[]{String.valueOf( benutzer.getBenutzer_id() )} );
    }

    protected ModelBenutzer getModelBenutzerFromCursor(Cursor c) {
        ModelBenutzer benutzer = new ModelBenutzer();
        benutzer.setBenutzer_id( c.getInt( c.getColumnIndex( "benutzer_id" ) ) );
        benutzer.setBenutzer_name( c.getString( c.getColumnIndex( "benutzer_name" ) ) );
        benutzer.setBenutzer_alter( c.getInt( c.getColumnIndex( "benutzer_alter" ) ) );
        benutzer.setBenutzer_gewicht( c.getInt( c.getColumnIndex( "benutzer_gewicht" ) ) );
        return benutzer;
    }

    public ModelBenutzer getBenutzer(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM benutzer Where benutzer_id =  ?";
        Cursor c = db.rawQuery( selectQuery, new String[]{String.valueOf( id )} );
        if (c != null) c.moveToFirst();
        return getModelBenutzerFromCursor( c );
    }

    public ModelBenutzer getBenutzer(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM benutzer Where benutzer_name = ?";
        Cursor c = db.rawQuery( selectQuery, new String[]{name} );
        if (c != null) c.moveToFirst();
        return getModelBenutzerFromCursor( c );
    }

    public ArrayList<ModelBenutzer> getAllBenutzer() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelBenutzer> benutzerList = new ArrayList<>();
        String selectQuery = "SELECT * FROM benutzer";
        Cursor c = db.rawQuery( selectQuery, null );
        if (c.moveToFirst()) {
            do {
                ModelBenutzer benutzer = getModelBenutzerFromCursor( c );
                benutzerList.add( benutzer );
            } while (c.moveToNext());
        }
        return benutzerList;
    }

    public String[] getAllBenutzerNamen() {

        ArrayList<ModelBenutzer> benutzerAL = getAllBenutzer();
        String[] stringarray = new String[benutzerAL.size()];

        for (int k = 0; k < benutzerAL.size(); k++) {
            stringarray[k] = benutzerAL.get( k ).getBenutzer_name();
        }


        return stringarray;
    }

    public int getBenutzerCount() {
        String countQuery = "SELECT * FROM benutzer";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( countQuery, null );
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Methoden für die Tabelle training

    public long createTraining(ModelTraining training) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("training_id", training.getTraining_id());
        values.put( "training_datum", String.valueOf( training.getTraining_datum() ) );
        values.put( "training_benutzer_id", training.getTraining_benutzer_id() );
        values.put( "training_station_id", training.getTraining_station_id() );
        values.put( "training_beschreibung", training.getTraining_beschreibung() );
        values.put( "training_dauer", training.getTraining_dauer() );
        values.put( "training_geschwindigkeit", training.getTraining_geschwindigkeit() );
        values.put( "training_kcal", training.getTraining_kcal() );
        values.put( "training_wiederholung", training.getTraining_wiederholung() );
        values.put( "training_gewicht", training.getTraining_gewicht() );
        return db.insertOrThrow( "training", null, values );
    }

    public int updateTraining(ModelTraining training) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "training_id", training.getTraining_id() );
        values.put( "training_datum", String.valueOf( training.getTraining_datum() ) );
        values.put( "training_benutzer_id", training.getTraining_benutzer_id() );
        values.put( "training_station_id", training.getTraining_station_id() );
        values.put( "training_beschreibung", training.getTraining_beschreibung() );
        values.put( "training_dauer", training.getTraining_dauer() );
        values.put( "training_geschwindigkeit", training.getTraining_geschwindigkeit() );
        values.put( "training_kcal", training.getTraining_kcal() );
        values.put( "training_wiederholung", training.getTraining_wiederholung() );
        values.put( "training_gewicht", training.getTraining_gewicht() );
        return db.update( "training", values, "training_id = ?", new String[]{String.valueOf( training.getTraining_id() )} );
    }

    public int deleteTraining(ModelTraining training) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( "training", "training_id = ?", new String[]{String.valueOf( training.getTraining_id() )} );
    }

    public void deleteTrainingbyID(long trainingID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( "training", "training_id = " + trainingID + ";", null );
    }

    protected ModelTraining getModelTrainingFromCursor(Cursor c) {
        ModelTraining training = new ModelTraining();
        training.setTraining_id( c.getInt( c.getColumnIndex( "training_id" ) ) );
        training.setTraining_datum( c.getString( c.getColumnIndex( "training_datum" ) ) );
        training.setTraining_benutzer_id( c.getInt( c.getColumnIndex( "training_benutzer_id" ) ) );
        training.setTraining_station_id( c.getInt( c.getColumnIndex( "training_station_id" ) ) );
        training.setTraining_beschreibung( c.getString( c.getColumnIndex( "training_beschreibung" ) ) );
        training.setTraining_dauer( c.getInt( c.getColumnIndex( "training_dauer" ) ) );
        training.setTraining_geschwindigkeit( c.getInt( c.getColumnIndex( "training_geschwindigkeit" ) ) );
        training.setTraining_kcal( c.getInt( c.getColumnIndex( "training_kcal" ) ) );
        training.setTraining_wiederholung( c.getInt( c.getColumnIndex( "training_wiederholung" ) ) );
        training.setTraining_gewicht( c.getInt( c.getColumnIndex( "training_gewicht" ) ) );
        return training;
    }

    public ModelTraining getTraining(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM training Where training_id =  ?";
        Cursor c = db.rawQuery( selectQuery, new String[]{String.valueOf( id )} );
        if (c != null) c.moveToFirst();
        return getModelTrainingFromCursor( c );
    }

    public DataPoint[] getData(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] colummns = {"training_datum", "sum(training_kcal)"};
        String groupBy = "training_datum";
        String selection = "training_benutzer_id = ?";
        String[] selectionArgs = new String[]{String.valueOf( userID )};
        Cursor cursor = db.query( "training", colummns, selection, selectionArgs, groupBy,
                null, null );
        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            //x=datum  y=sum(kcal)
            dp[i] = new DataPoint( cursor.getInt( 0 ), cursor.getInt( 1 ) );
            Log.d( LOG, "get data: " + dp[i] );
        }
        return dp;

    }

    public Cursor getPumperStats(long benutzerId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select training_datum,Sum(training_kcal)As kcal,Sum(training_wiederholung * training_gewicht) As pumpPower  from training group by training_datum\n" +
                "having training_benutzer_id =" + benutzerId + ";";
        Cursor c = db.rawQuery( query, null );
        return c;
    }

    public ArrayList<ModelTraining> getAllTraining() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelTraining> trainingList = new ArrayList<ModelTraining>();
        String selectQuery = "SELECT * FROM training";
        Cursor c = db.rawQuery( selectQuery, null );
        if (c.moveToFirst()) {
            do {
                ModelTraining training = getModelTrainingFromCursor( c );
                trainingList.add( training );
            } while (c.moveToNext());
        }
        return trainingList;
    }

    public ArrayList<ModelTraining> getTrainingByUserID(int userID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelTraining> trainingList = new ArrayList<ModelTraining>();
        String selectQuery = "SELECT * FROM training Where training_benutzer_id =" + userID + " ;";
        Cursor c = db.rawQuery( selectQuery, null );
        if (c.moveToFirst()) {
            do {
                ModelTraining training = getModelTrainingFromCursor( c );
                trainingList.add( training );
            } while (c.moveToNext());
        }
        return trainingList;
    }

    public int getTrainingCount() {
        String countQuery = "SELECT * FROM training";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( countQuery, null );
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Methoden für die Tabelle station

    public long createStation(ModelStation station) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "station_name", station.getStation_name() );
        values.put( "station_typ", station.getStation_typ() );
        return db.insertOrThrow( "station", null, values );
    }

    public int updateStation(ModelStation station) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "station_id", station.getStation_id() );
        values.put( "station_name", station.getStation_name() );
        values.put( "station_typ", station.getStation_typ() );
        return db.update( "station", values, "station_id = ?", new String[]{String.valueOf( station.getStation_id() )} );
    }

    public int deleteStation(ModelStation station) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( "station", "station_id = ?", new String[]{String.valueOf( station.getStation_id() )} );
    }

    public void deleteStationbyID(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( "station", "station_id = " + id + ";", null );
    }

    protected ModelStation getModelStationFromCursor(Cursor c) {
        ModelStation station = new ModelStation();
        station.setStation_id( c.getInt( c.getColumnIndex( "station_id" ) ) );
        station.setStation_name( c.getString( c.getColumnIndex( "station_name" ) ) );
        station.setStation_typ( c.getInt( c.getColumnIndex( "station_typ" ) ) );
        return station;
    }

    public ModelStation getStation(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM station Where station_id =  ?";
        Cursor c = db.rawQuery( selectQuery, new String[]{String.valueOf( id )} );
        if (c != null) c.moveToFirst();
        return getModelStationFromCursor( c );
    }

    public ModelStation getStation(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM station Where station_name =\"" + name + "\";";
        Cursor c = db.rawQuery( selectQuery, null );
        if (c != null) c.moveToFirst();
        return getModelStationFromCursor( c );
    }

    public ArrayList<ModelStation> getAllStation() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelStation> stationList = new ArrayList<ModelStation>();
        String selectQuery = "SELECT * FROM station";
        Cursor c = db.rawQuery( selectQuery, null );
        if (c.moveToFirst()) {
            do {
                ModelStation station = getModelStationFromCursor( c );
                stationList.add( station );
            } while (c.moveToNext());
        }
        return stationList;
    }

    public String[] getAllStationNamen() {

        ArrayList<ModelStation> stationAL = getAllStation();
        String[] stringarray = new String[stationAL.size()];

        for (int k = 0; k < stationAL.size(); k++) {
            stringarray[k] = stationAL.get( k ).getStation_name();
        }


        return stringarray;
    }

    public int getStationCount() {
        String countQuery = "SELECT * FROM station";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( countQuery, null );
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    // Methoden für die Tabelle train_ziel

    public long createTrain_ziel(ModelTrain_ziel train_ziel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "train_ziel_benutzer_id", train_ziel.getTrain_ziel_benutzer_id() );
        values.put( "train_ziel_station_id", train_ziel.getTrain_ziel_station_id() );
        values.put( "train_ziel_soll_geschwindigkeit", train_ziel.getTrain_ziel_soll_geschwindigkeit() );
        values.put( "train_ziel_soll_dauer", train_ziel.getTrain_ziel_soll_dauer() );
        values.put( "train_ziel_soll_gewicht", train_ziel.getTrain_ziel_soll_gewicht() );
        values.put( "train_ziel_gewicht_wunsch", train_ziel.getTrain_ziel_korper_gewicht() );
        values.put( "train_ziel_pos1", train_ziel.getTrain_ziel_pos1() );
        values.put( "train_ziel_pos2", train_ziel.getTrain_ziel_pos2() );
        values.put( "train_ziel_pos3", train_ziel.getTrain_ziel_pos3() );
        return db.insertOrThrow( "train_ziel", null, values );
    }

    public int updateTrain_ziel(ModelTrain_ziel train_ziel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "train_ziel_benutzer_id", train_ziel.getTrain_ziel_benutzer_id() );
        values.put( "train_ziel_station_id", train_ziel.getTrain_ziel_station_id() );
        values.put( "train_ziel_soll_geschwindigkeit", train_ziel.getTrain_ziel_soll_geschwindigkeit() );
        values.put( "train_ziel_soll_dauer", train_ziel.getTrain_ziel_soll_dauer() );
        values.put( "train_ziel_soll_gewicht", train_ziel.getTrain_ziel_soll_gewicht() );
        values.put( "train_ziel_gewicht_wunsch", train_ziel.getTrain_ziel_korper_gewicht() );
        values.put( "train_ziel_pos1", train_ziel.getTrain_ziel_pos1() );
        values.put( "train_ziel_pos2", train_ziel.getTrain_ziel_pos2() );
        values.put( "train_ziel_pos3", train_ziel.getTrain_ziel_pos3() );
        return db.update( "train_ziel", values, "train_ziel_id = ?", new String[]{String.valueOf( train_ziel.getTrain_ziel_id() )} );
    }

    public int deleteTrain_ziel(ModelTrain_ziel train_ziel) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( "train_ziel", "train_ziel_id = ?", new String[]{String.valueOf( train_ziel.getTrain_ziel_id() )} );
    }

    protected ModelTrain_ziel getModelTrain_zielFromCursor(Cursor c) {
        ModelTrain_ziel train_ziel = new ModelTrain_ziel();
        train_ziel.setTrain_ziel_id( c.getInt( c.getColumnIndex( "train_ziel_id" ) ) );
        train_ziel.setTrain_ziel_benutzer_id( c.getInt( c.getColumnIndex( "train_ziel_benutzer_id" ) ) );
        train_ziel.setTrain_ziel_station_id( c.getInt( c.getColumnIndex( "train_ziel_station_id" ) ) );
        train_ziel.setTrain_ziel_soll_geschwindigkeit( c.getInt( c.getColumnIndex( "train_ziel_soll_geschwindigkeit" ) ) );
        train_ziel.setTrain_ziel_soll_dauer( c.getInt( c.getColumnIndex( "train_ziel_soll_dauer" ) ) );
        train_ziel.setTrain_ziel_soll_gewicht( c.getInt( c.getColumnIndex( "train_ziel_soll_gewicht" ) ) );
        train_ziel.setTrain_ziel_korper_gewicht( c.getInt( c.getColumnIndex( "train_ziel_gewicht_wunsch" ) ) );
        train_ziel.setTrain_ziel_pos1( c.getString( c.getColumnIndex( "train_ziel_pos1" ) ) );
        train_ziel.setTrain_ziel_pos2( c.getString( c.getColumnIndex( "train_ziel_pos2" ) ) );
        train_ziel.setTrain_ziel_pos3( c.getString( c.getColumnIndex( "train_ziel_pos3" ) ) );
        return train_ziel;
    }

    public long getTrainZielID(long stationID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM train_ziel Where train_ziel_station_id =  ?";
        Cursor c = db.rawQuery( selectQuery, new String[]{String.valueOf( stationID )} );
        if (c != null) c.moveToFirst();
        ModelTrain_ziel ziel = getModelTrain_zielFromCursor( c );
        return ziel.getTrain_ziel_id();
    }

    public ModelTrain_ziel getTrain_ziel(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM train_ziel Where train_ziel_id =  ?";
        Cursor c = db.rawQuery( selectQuery, new String[]{String.valueOf( id )} );
        if (c != null) c.moveToFirst();
        return getModelTrain_zielFromCursor( c );
    }

    public ArrayList<ModelTrain_ziel> getAllTrain_ziel(int benutzerID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelTrain_ziel> train_zielList = new ArrayList<>();
        String selectQuery = "SELECT * FROM train_ziel Where train_ziel_benutzer_id =" + benutzerID + " ;";
        Cursor c = db.rawQuery( selectQuery, null );
        if (c.moveToFirst()) {
            do {
                ModelTrain_ziel train_ziel = getModelTrain_zielFromCursor( c );
                train_zielList.add( train_ziel );
            } while (c.moveToNext());
        }
        return train_zielList;
    }

    public ArrayList<ModelTrain_ziel> getAllTrain_ziel() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ModelTrain_ziel> train_zielList = new ArrayList<ModelTrain_ziel>();
        String selectQuery = "SELECT * FROM train_ziel";
        Cursor c = db.rawQuery( selectQuery, null );
        if (c.moveToFirst()) {
            do {
                ModelTrain_ziel train_ziel = getModelTrain_zielFromCursor( c );
                train_zielList.add( train_ziel );
            } while (c.moveToNext());
        }
        return train_zielList;
    }

    public int getTrain_zielCount() {
        String countQuery = "SELECT * FROM train_ziel";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( countQuery, null );
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


}