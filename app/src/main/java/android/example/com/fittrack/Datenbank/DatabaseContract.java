package android.example.com.fittrack.Datenbank;

import android.provider.BaseColumns;

public final class DatabaseContract {

    //nach --> https://developer.android.com/training/data-storage/sqlite#java <--


    private DatabaseContract(){}


    public static final String ENABLE_FOREIGN_KEYS ="PRAGMA foreign_keys=ON";//db.execSQL(ENABLE_FOREIGN_KEYS);

    //User_Tabelle
    public static abstract class Benutzer implements BaseColumns {
        public static final String TBL_NAME = "Benutzer";
        public static final String BENUTZERNAME = "Benutzername";
        public static final String GEWICHT = "Gewicht";
        public static final String ALTER = "_Alter";

        public static final String [] COLUMNS_ARRAY = {Benutzer._ID,
                BENUTZERNAME,GEWICHT,ALTER};

        public static final String SQL_CREATE =
                "CREATE TABLE " + TBL_NAME + "(" +
                        Benutzer._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        BENUTZERNAME + " TEXT NOT NULL UNIQUE, " +
                        GEWICHT + " INTEGER, " +
                        ALTER + " INTEGER);";


        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TBL_NAME;
    }

    //Training_Tabelle
    public static class Training implements BaseColumns {
        public static final String TBL_NAME = "Training";
        public static final String BENUTZERNAME = "Benutzername"; //FK
        public static final String STATION_ID = "Station_ID"; //FK
        public static final String BESCHREIBUNG = "Beschreibung";
        public static final String DATUM = "Datum";
        public static final String GESCHW = "Geschwindigkeit_in_Km_h";
        public static final String DAUER = "Dauer_in_Min";

        public static final String[] COLUMNS_ARRAY={Training._ID, Training.BENUTZERNAME,BESCHREIBUNG,
                STATION_ID,GESCHW,DAUER,DATUM};


        public static final String SQL_CREATE =
                " CREATE TABLE " + TBL_NAME + "(" +
                        Training._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Training.BENUTZERNAME + " TEXT, "+
                        BESCHREIBUNG + " TEXT, " +
                        STATION_ID + " INTEGER, " +
                        GESCHW + " DOUBLE, " + //REAL
                        DAUER + " INTEGER, "+
                        DATUM + " TEXT DEFAULT (date('now')), "+
                        "FOREIGN KEY(" + Training.BENUTZERNAME + ") REFERENCES " +
                        Benutzer.TBL_NAME +"("+ Benutzer.BENUTZERNAME +") ON DELETE CASCADE ON UPDATE CASCADE, "
                        +"FOREIGN KEY(" + STATION_ID + ") REFERENCES " +
                        Station.TBL_NAME + "(" + Station._ID + ") ON DELETE NO ACTION ON UPDATE CASCADE);";


        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TBL_NAME;
    }

    //Trainingszieltabelle
    public static class Trainingsziel implements BaseColumns {
        public static final String TBL_NAME = "Trainingsziel";
        public static final String BENUTZERNAME= "Benutzer";//FK
        public static final String STATION_ID = "Station_ID";//FK
        public static final String SOLL_GEWICHT = "Sollgewicht";
        public static final String SOLL_DAUER = "Solldauer_in_Min";
        public static final String SOLL_GESCHW = "Sollgeschwindigkeit_in_Km_H";
        public static final String POSITION_EINSTELLUNG_EINS= "Position_Einstellung_1";
        public static final String POSITION_EINSTELLUNG_ZWEI= "Position_Einstellung_2";
        public static final String POSITION_EINSTELLUNG_DREI= "Position_Einstellung_3";

        public static final String[] COLUMNS_ARRAY={Trainingsziel._ID, Trainingsziel.BENUTZERNAME
                , Trainingsziel.STATION_ID,SOLL_GEWICHT,SOLL_DAUER,SOLL_GESCHW,POSITION_EINSTELLUNG_EINS,
                POSITION_EINSTELLUNG_ZWEI,POSITION_EINSTELLUNG_DREI};


        public static final String SQL_CREATE =
                "CREATE TABLE " + TBL_NAME + "("
                        + Trainingsziel._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + Trainingsziel.BENUTZERNAME + " TEXT NOT NULL, "
                        + Trainingsziel.STATION_ID + " INTEGER NOT NULL, "
                        + SOLL_GEWICHT + " DOUBLE, " +
                        SOLL_DAUER + " INTEGER, " +
                        SOLL_GESCHW + " DOUBLE, "+
                        POSITION_EINSTELLUNG_EINS + " TEXT, "+
                        POSITION_EINSTELLUNG_ZWEI + " TEXT, "+
                        POSITION_EINSTELLUNG_DREI + " TEXT,"+
                        " FOREIGN KEY (" + Trainingsziel.BENUTZERNAME
                        + ")REFERENCES "+ Benutzer.TBL_NAME +" ("+
                        Benutzer.BENUTZERNAME+ ") ON DELETE CASCADE ON UPDATE CASCADE,"+
                        " FOREIGN KEY("+ Trainingsziel.STATION_ID
                        + ") REFERENCES "+ Station.TBL_NAME+ "("+ Station._ID+")"+
                        " ON DELETE CASCADE ON UPDATE CASCADE);";


        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TBL_NAME;
    }

    //Gerätetabelle
    public static class Station implements BaseColumns {
        public static final String TBL_NAME = "Station";
        public static final String NAME = "Station_Name";
        public static final String TYP = "Station_Typ";

        public static final String[] COLUMNS_ARRAY={Station._ID,NAME,TYP};

        public static final String SQL_CREATE =
                "CREATE TABLE " + TBL_NAME + "(" +
                        Station._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NAME + " TEXT NOT NULL, " +
                        TYP + " TEXT NOT NULL);";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TBL_NAME;


    }

    public static class Satz implements BaseColumns {

        public static final String TBL_NAME = "Satz";
        public static final String NR = "Satznummer";
        public static final String GEWICHT = "Satzgewicht";
        public static final String WIEDERHOLUNGEN ="Wiederholungen";
        public static final String TRAINING_ID = "Training_ID";//FK

        public static final String[] COLUMNS_ARRAY={Satz._ID,TRAINING_ID,NR,WIEDERHOLUNGEN,GEWICHT};

        public static final String SQL_CREATE =
                "CREATE TABLE " + TBL_NAME + " (" +
                        Satz._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        Satz.TRAINING_ID + " INTEGER,"+
                        NR + " INTEGER NOT NULL, " +
                        WIEDERHOLUNGEN+ " INTEGER NOT NULL, "+
                        GEWICHT + " DOUBLE NOT NULL,"+
                        "FOREIGN KEY (" + Satz.TRAINING_ID +") REFERENCES " +
                        Training.TBL_NAME + " (" + Training._ID + ") ON DELETE CASCADE ON UPDATE CASCADE);";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TBL_NAME;

    }


    public static final String[] SQL_CREATE_TABLE_ARRAY = {
            Benutzer.SQL_CREATE,
            Training.SQL_CREATE,
            Station.SQL_CREATE,
            Trainingsziel.SQL_CREATE,
            Satz.SQL_CREATE
    };

    public  static final String[] SQL_DELETE_TABLE_ARRAY ={
            Benutzer.DELETE_TABLE,
            Training.DELETE_TABLE,
            Station.DELETE_TABLE,
            Trainingsziel.DELETE_TABLE,
            Satz.DELETE_TABLE
    };
}
