package infsolution.com.br.fidelize.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infsolution.com.br.fidelize.FidelizeMainActivity;
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

    public String insert(Elector elector){
        String result ="Eleitor já consta em sua base de dados";
       if(!isElector(elector.getName())){
            ContentValues cv = new ContentValues();
            cv.put("fid_name",elector.getName());
           cv.put("fid_date_birth", String.valueOf(elector.getDateBirth()));
           cv.put("fid_scholarity",elector.getScholarity());
            cv.put("fid_party",elector.getParty());
           cv.put("fid_affiliate",elector.isFidelity());
           cv.put("fid_sex",elector.getSex());
           cv.put("fid_id_campaigner", elector.getCampaigner());
           cv.put("fid_id_candidate", elector.getCandidate());
            dao.getWritableDatabase().insert("fid_elector", null, cv);
            result="Eleitor cadastrado com sucesso!";
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
            String party = c.getString(c.getColumnIndex("fid_party"));
            String scholarity = c.getString(c.getColumnIndex("fid_scholarity"));
            int affiliat = c.getInt(c.getColumnIndex("fid_affiliate"));
            long idCampaigner = c.getLong(c.getColumnIndex("fid_id_campaigner"));
            long idCandidate = c.getLong(c.getColumnIndex("fid_id_candidate"));
            dao.close();
            Elector elector = new Elector(name);
            elector.setId(id);
            elector.setParty(party);
            elector.setScholarity(scholarity);
            String sqlT = "SELECT * FROM fid_title WHERE fid_id_elector = "+id+";";
            Cursor cT = dao.getReadableDatabase().rawQuery(sqlT, null);
            if(cT.moveToFirst()){
            String title = cT.getString(cT.getColumnIndex("fid_number"));
            String zone = cT.getString(cT.getColumnIndex("fid_zone"));
            String section = cT.getString(cT.getColumnIndex("fid_section"));
            Title titleE = new Title(title,zone,section);
            elector.setTitle(titleE);}
            electors.add(elector);
        }
        return electors;
    }

    public boolean isElector(String elector){
        boolean res = true;
        String sql="SELECT * FROM fid_elector WHERE fid_name = '"+elector+"';";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
        if(!c.moveToNext()){
         res=false;
        }
        return res;
    }


}
