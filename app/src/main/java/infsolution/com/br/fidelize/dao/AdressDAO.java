package infsolution.com.br.fidelize.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import infsolution.com.br.fidelize.model.Adress;
import infsolution.com.br.fidelize.model.Elector;

/**
 * Created by Cicero on 07/06/2016.
 */
public class AdressDAO {
    private DAO dao;
    private SQLiteDatabase database;
    public AdressDAO(Context context){
        dao = new DAO(context);
    }
    public void open ()throws SQLException {
        database = dao.getWritableDatabase();
    }
    public void close(){
        dao.close();
    }
    public void insertAdress(Elector elector, Adress adress){
            ContentValues cv = new ContentValues();
            cv.put(DAO.prefixe+"street",adress.getStreet());
            cv.put(DAO.prefixe+"number",adress.getNumber());
            cv.put(DAO.prefixe+"district",adress.getDistrict());
            cv.put(DAO.prefixe+"city",adress.getCity());
            cv.put(DAO.prefixe+"state",adress.getState());
            cv.put(DAO.prefixe+"country",adress.getCountry());
            cv.put(DAO.prefixe+"zip_code",adress.getZipCode());
            cv.put(DAO.prefixe+"complement",adress.getComplement());
            dao.getWritableDatabase().insert(DAO.prefixe + "adress", null, cv);
    }

}
