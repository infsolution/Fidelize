package infsolution.com.br.fidelize.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import infsolution.com.br.fidelize.model.Address;

/**
 * Created by Cicero on 07/06/2016.
 */
public class AddressDAO {
    private DAO dao;
    private SQLiteDatabase database;
    public AddressDAO(Context context){
        dao = new DAO(context);
    }
    public void open ()throws SQLException {
        database = dao.getWritableDatabase();
    }
    public void close(){
        dao.close();
    }
    public long insertAddress(Address address){
            if(addressIsCad(address)==0) {
                ContentValues cv = new ContentValues();
                cv.put(DAO.prefixe + "street", address.getStreet());
                cv.put(DAO.prefixe + "number", address.getNumber());
                cv.put(DAO.prefixe + "district", address.getDistrict());
                cv.put(DAO.prefixe + "city", address.getCity());
                cv.put(DAO.prefixe + "state", address.getState());
                cv.put(DAO.prefixe + "country", address.getCountry());
                cv.put(DAO.prefixe + "zip_code", address.getZipCode());
                return dao.getWritableDatabase().insert(DAO.prefixe + "address", null, cv);
            }else{
                return addressIsCad(address);
            }
    }
    public long addressIsCad(Address address){
        long idAddress=0;
        String sql = "SELECT fid_id_address FROM fid_address WHERE fid_street = '"+address.getStreet()+"' and fid_number = '"+address.getNumber()+"';";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
        if(c.moveToFirst()){
           idAddress = c.getLong(c.getColumnIndex("fid_id_address"));
        }
        return idAddress;
    }

}
