package infsolution.com.br.fidelize.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import infsolution.com.br.fidelize.model.Adress;
import infsolution.com.br.fidelize.model.Elector;

/**
 * Created by Cicero on 27/06/2016.
 */
public class ElectorAdressDAO {
    private DAO dao;
    private SQLiteDatabase database;
    public ElectorAdressDAO(Context context){
        dao = new DAO(context);
    }
    public void open ()throws SQLException {
        database = dao.getWritableDatabase();
    }
    public void close(){
        dao.close();
    }
    public void insertEleAdr(Elector elector, Adress adress){
        ContentValues cv = new ContentValues();
        cv.put(DAO.prefixe+"id_elector",elector.getId());
        cv.put(DAO.prefixe+"id_adress",adress.getId());
        dao.getWritableDatabase().insert(DAO.prefixe + "elector_adress", null, cv);
    }
}
