package infsolution.com.br.fidelize.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infsolution.com.br.fidelize.FidelizeMainActivity;
import infsolution.com.br.fidelize.model.Address;
import infsolution.com.br.fidelize.model.Elector;
import infsolution.com.br.fidelize.model.Title;

/**
 * Created by Cicero on 05/06/2016.
 */
public class ElectorDAO {
    private DAO dao;
    private Elector elector;
    private SQLiteDatabase database;
    public ElectorDAO(Context context){
        dao = new DAO(context);
    }
    public void open ()throws SQLException{
        database = dao.getWritableDatabase();
    }
    public void close(){
        dao.close();
    }

    public long insert(Elector elector){
        long result =0;
       if(!isElector(elector.getName(),elector.getAddress().getId())){
            ContentValues cv = new ContentValues();
            cv.put(DAO.prefixe+"name",elector.getName());
           cv.put(DAO.prefixe+"date_birth", String.valueOf(elector.getDateBirth()));
           cv.put(DAO.prefixe+"affiliate",elector.getAffiliate());
           cv.put(DAO.prefixe+"sex",elector.getSex());
           cv.put(DAO.prefixe+"scholarity",elector.getScholarity());
           cv.put(DAO.prefixe+"id_title",elector.getTitle().getId());
           cv.put(DAO.prefixe+"id_address",elector.getAddress().getId());
           cv.put(DAO.prefixe+"id_campaigner", elector.getCampaigner());
           cv.put(DAO.prefixe+"id_candidate", elector.getCandidate());
           result=dao.getWritableDatabase().insert(DAO.prefixe+"elector", null, cv);
           dao.close();
       }
        return result;
    }
    public List<Elector> listEle(){
        List<Elector> electors = new ArrayList<>();
        String sql = "SELECT * FROM fid_elector;";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("fid_id_elector"));
            String name = c.getString(c.getColumnIndex("fid_name"));
            String dtBirth = c.getString(c.getColumnIndex("fid_date_birth"));
            int affiliat = c.getInt(c.getColumnIndex("fid_affiliate"));
            String sex = c.getString(c.getColumnIndex("fid_sex"));
            String scholarity = c.getString(c.getColumnIndex("fid_scholarity"));
            long idTitle = c.getLong(c.getColumnIndex("fid_id_title"));
            long idAddres = c.getLong(c.getColumnIndex("fid_id_address"));
            long idCampaigner = c.getLong(c.getColumnIndex("fid_id_campaigner"));
            long idCandidate = c.getLong(c.getColumnIndex("fid_id_candidate"));
            dao.close();
            Elector elector = new Elector(name);
            elector.setId(id);
            elector.setScholarity(scholarity);
            elector.setDateBirth(dtBirth);
            elector.setAffiliate(affiliat);
            elector.setSex(sex);
            elector.setCampaigner(idCampaigner);
            elector.setCandidate(idCandidate);
            String sqlT = "SELECT * FROM fid_title WHERE fid_id_title = "+idTitle+";";
            Cursor cT = dao.getReadableDatabase().rawQuery(sqlT, null);
            if(cT.moveToFirst()){
            String title = cT.getString(cT.getColumnIndex("fid_number"));
            String zone = cT.getString(cT.getColumnIndex("fid_zone"));
            String section = cT.getString(cT.getColumnIndex("fid_section"));
                dao.close();
            Title titleE = new Title(title,zone,section);
            elector.setTitle(titleE);}

            String sqlA = "SELECT * FROM fid_address WHERE fid_id_address = "+idAddres+";";
            Cursor cA = dao.getReadableDatabase().rawQuery(sqlA,null);
            if(cA.moveToFirst()){
                long idA = cA.getLong(cA.getColumnIndex("fid_id_address"));
                String street = cA.getString(cA.getColumnIndex("fid_street"));
                String num = cA.getString(cA.getColumnIndex("fid_number"));
                String distr = cA.getString(cA.getColumnIndex("fid_district"));
                String city = cA.getString(cA.getColumnIndex("fid_city"));
                String state = cA.getString(cA.getColumnIndex("fid_state"));
                String country = cA.getString(cA.getColumnIndex("fid_country"));
                String zip = cA.getString(cA.getColumnIndex("fid_zip_code"));
                Address address = new Address(street,num,distr);
                address.setId(idA);
                address.setCity(city);
                address.setState(state);
                address.setCountry(country);
                address.setZipCode(zip);
                elector.setAddress(address);
                dao.close();
            }
            electors.add(elector);
        }
        return electors;
    }

    public boolean isElector(String elector, long address){
        boolean res = true;
        String sql="SELECT * FROM fid_elector WHERE fid_name = '"+elector+"' and fid_id_address = "+address+";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
        if(!c.moveToNext()){
         res=false;
        }
        return res;
    }


}
