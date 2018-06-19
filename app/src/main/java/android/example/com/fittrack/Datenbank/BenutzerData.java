package android.example.com.fittrack.Datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.example.com.fittrack.TabellenObjekte.Benutzer;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


public class BenutzerData {

    private static final String LOG = BenutzerData.class.getSimpleName();

    private SQLiteDatabase database;
    private FitnessDbHelper dbHelper;


    public BenutzerData(Context context){
        Log.d(LOG,"Unsere DataSource erzeugt jetzt den dbHelper. ");
        dbHelper = new FitnessDbHelper(context);
    }
    public void open() {
        Log.d(LOG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
        database = dbHelper.getWritableDatabase();
        database.execSQL(DatabaseContract.ENABLE_FOREIGN_KEYS);
        Log.d(LOG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
    }

    public void close() {
        dbHelper.close();
        Log.d(LOG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
    }

    public Benutzer createBenutzer(String benutzername, int gewicht, int alter) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.Benutzer.BENUTZERNAME, benutzername);
        contentValues.put(DatabaseContract.Benutzer.GEWICHT, gewicht);
        contentValues.put(DatabaseContract.Benutzer.ALTER, alter);
        //datensatz einfügen: ID PRIMARY KEY -> result
        long result = database.insert(DatabaseContract.Benutzer.TBL_NAME,
                null, contentValues);

        //den gerade eingefügten Datensatz suchen um in ein Benutzer umzuwandeln
        Cursor cursor = database.query(DatabaseContract.Benutzer.TBL_NAME,
                DatabaseContract.Benutzer.COLUMNS_ARRAY,DatabaseContract.Benutzer._ID
                        +"="+result,null,null,null,null);

        cursor.moveToFirst();
        Benutzer benutzer = cursorToBenutzer(cursor);// CursorObjekt wird zum Benutzer
        cursor.close();

        return benutzer;


    }

    private Benutzer cursorToBenutzer(Cursor cursor) {
        //SpaltenIndizes
        int idIndex = cursor.getColumnIndex(DatabaseContract.Benutzer._ID);
        int idBenutzerName = cursor.getColumnIndex(DatabaseContract.Benutzer.BENUTZERNAME);
        int idGewicht = cursor.getColumnIndex(DatabaseContract.Benutzer.GEWICHT);
        int idAlter= cursor.getColumnIndex(DatabaseContract.Benutzer.ALTER);

        //Inhalt aus den Spalten
        int id = cursor.getInt(idIndex);
        String benutzerName = cursor.getString(idBenutzerName);
        double gewicht = cursor.getDouble(idGewicht);
        int alter = cursor.getInt(idAlter);

        Benutzer benutzer = new Benutzer(id,benutzerName,alter,gewicht);

        return benutzer;

    }
    public void deleteBenutzer(Benutzer benutzer){
        int id = benutzer.getId();

        database.delete(DatabaseContract.Benutzer.TBL_NAME,DatabaseContract.Benutzer._ID
                +"="+id,null);

        Log.d(LOG,"Inhalt wird gelöscht: "+benutzer.toString());
    }

    public Cursor getTableData(String table){
     open();
     String query = "SELECT * FROM "+ table +";";
     Cursor data = database.rawQuery( query,null );

     return data;
    }

    public String getUserNamebyID (long id){

        String username;

        open();
        String query = "SELECT * FROM Benutzer WHERE _id=="+id+";";
        Cursor data = database.rawQuery( query,null );
        data.moveToFirst();
        username = data.getString( data.getColumnIndex( "Benutzername" ) );
        data.close();



        return username;


    }
    public int getUserAgebyID(long id){
        int age;
        String query = "SELECT * FROM Benutzer WHERE _id=="+id+";";
        Cursor data = database.rawQuery( query,null );
        data.moveToFirst();
        age = Integer.parseInt( data.getString( data.getColumnIndex( "_Alter" ) ) );
        data.close();
        return age;
    }
    public int getUserWeightbyID(long id){
        int gewicht;
        String query = "SELECT * FROM Benutzer WHERE _id=="+id+";";
        Cursor data = database.rawQuery( query,null );
        data.moveToFirst();
        gewicht = Integer.parseInt( data.getString( data.getColumnIndex( "Gewicht" ) ) );
        data.close();
        return gewicht;
    }
    public Cursor query (String query){
        Cursor data = database.rawQuery( query,null );
        data.close();
        return data;

    }

    }


