package infsolution.com.br.fidelize.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cicero on 05/06/2016.
 */
public class DAO extends SQLiteOpenHelper {
    public static final String dbName = "fidDb";
    public static int versionDb = 1;
    public static final String prefixe="fid_";
    public DAO(Context context) {
        super(context, dbName, null, versionDb);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE fid_elector (fid_id_elector INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "fid_name VARCHAR (120) NOT NULL, fid_date_birth TEXT, "+
            "fid_affiliate INTEGER, fid_sex VARCHAR (10), fid_scholarity VARCHAR(80), fid_id_title INTEGER, " +
                "fid_id_address INTEGER, fid_id_campaigner INTEGER, fid_id_candidate INTEGER);";
        db.execSQL(sql);
        sql = "CREATE TABLE fid_campaigner (fid_id_campaigner INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fid_name VARCHAR (120) NOT NULL, fid_id_title INTEGER, fid_date_birth TEXT, fid_id_adress"+
                "fid_scholarity VARCHAR(80), fid_party VARCHAR, fid_affiliate INTEGER, fid_sex VARCHAR (10), fid_id_candidate INTEGER" +
                "fid_code INTEGER, fid_status INTEGER);";
        db.execSQL(sql);
        sql = "CREATE TABLE fid_candidate (fid_id_candidate INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fid_name VARCHAR (120) NOT NULL, fid_id_title INTEGER, fid_date_birth TEXT, fid_id_adress"+
                "fid_scholarity VARCHAR(80),fid_sex VARCHAR (10), fid_party VARCHAR, fid_code INTEGER, fid_status INTEGER);";
        db.execSQL(sql);
        sql = "CREATE TABLE fid_title (fid_id_title INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fid_number VARCHAR (20) NOT NULL, fid_zone VARCHAR (10) NOT NULL, fid_section VARCHAR (10) NOT NULL);";
        db.execSQL(sql);
        sql = "CREATE TABLE fid_address (fid_id_address INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "fid_street VARCHAR (120), fid_number VARCHAR (6), fid_district VARCHAR (120), fid_city VARCHAR (120), " +
                "fid_state VARCHAR (120), fid_country VARCHAR (80), fid_zip_code VARCHAR (12));";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="DROP TABLE IF EXISTS fid_elector;";
        db.execSQL(sql);
        sql="DROP TABLE IF EXISTS fid_campaigner;";
        db.execSQL(sql);
        sql="DROP TABLE IF EXISTS fid_candidate;";
        db.execSQL(sql);
        sql="DROP TABLE IF EXISTS fid_title;";
        db.execSQL(sql);
        sql="DROP TABLE IF EXISTS fid_address;";
        db.execSQL(sql);
        onCreate(db);
    }

}
