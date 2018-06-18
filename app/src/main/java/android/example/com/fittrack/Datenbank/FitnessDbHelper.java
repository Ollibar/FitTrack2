package android.example.com.fittrack.Datenbank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FitnessDbHelper extends SQLiteOpenHelper {


    //von : https://developer.android.com/training/data-storage/sqlite#java

    public static final String LOG = FitnessDbHelper.class.getSimpleName();
    public static final int DB_VERSION = 1;
    public static final String DB_NAME= "FitnessDB.db";




    public FitnessDbHelper(Context context) {
        super(context, DB_NAME , null, DB_VERSION);
        Log.d(LOG,"FitnessDBHelper hat die Datenbank: "+ getDatabaseName()+" erzeugt.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String [] SQL_CREATE_QUERIES = DatabaseContract.SQL_CREATE_TABLE_ARRAY;
        try{
            for(int i = 0;i<SQL_CREATE_QUERIES.length;i++) {
                Log.d(LOG, "Die Tabellen mit den Anweisungen " + SQL_CREATE_QUERIES[i]
                        + " werden erzeugt.");
                db.execSQL(SQL_CREATE_QUERIES[i]);
            }
        }catch(Exception e){
            for (int i =0;i<SQL_CREATE_QUERIES.length;i++) {
                Log.d(LOG, "Fehler beim Anlegen der Tabellen: "+SQL_CREATE_QUERIES[i]
                        + e.getStackTrace() +
                        "  " + e.getMessage());
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String [] SQL_DELETE_QUERIES = DatabaseContract.SQL_DELETE_TABLE_ARRAY;
        try{
            for(int i = 0;i<SQL_DELETE_QUERIES.length;i++) {
                Log.d(LOG, "Die Tabellen mit den Anweisungen " + SQL_DELETE_QUERIES[i] + " werden gelöscht.");
                db.execSQL(SQL_DELETE_QUERIES[i]);
            }
            onCreate(db);
        }catch(Exception e) {
            for (int i = 0; i < SQL_DELETE_QUERIES.length; i++) {
                Log.d(LOG, "Fehler beim Löschen der Tabellen: " +SQL_DELETE_QUERIES[i]+
                        e.getStackTrace() +
                        "  " + e.getMessage());
            }
        }

    }

}
