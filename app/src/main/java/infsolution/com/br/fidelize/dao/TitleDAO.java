package infsolution.com.br.fidelize.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import infsolution.com.br.fidelize.model.Elector;
import infsolution.com.br.fidelize.model.Title;

/**
 * Created by Cicero on 07/06/2016.
 */
public class TitleDAO {
    private DAO dao;
    private Title title;
    private SQLiteDatabase database;
    public TitleDAO(Context context){
        dao = new DAO(context);
    }

    public void open ()throws SQLException {
        database = dao.getWritableDatabase();
    }
    public void close(){
        dao.close();
    }

    public long insertTitle(Title title ){
        long ret=0;
            if(!titleIsCad(title)){
                ContentValues cv = new ContentValues();
                cv.put(DAO.prefixe+"number",title.getNumber());
                cv.put(DAO.prefixe+"zone",title.getZone());
                cv.put(DAO.prefixe+"section",title.getSection());
                ret = dao.getWritableDatabase().insert(DAO.prefixe + "title", null, cv);
            }else{
                ret = 112;
            }
        return ret;
    }
    public boolean titleIsCad(Title title){
        boolean res = false;
        String sql = "SELECT * FROM fid_title WHERE fid_number = "+title.getNumber()+";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql, null);
        if(c.moveToNext()){
            res=true; }
        return res;
    }
    public long pullIdElector(Elector elector){
        long idElector=0;
        String sql="SELECT * FROM fid_elector WHERE fid_name = '"+elector.getName()+"';";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
        if(c.moveToNext()){
            idElector=c.getLong(c.getColumnIndex("fid_id_elector"));
        }
        dao.close();
        return idElector;
    }
    public Title pullIitle(Elector elector){
        String sql = "SELECT * FROM fid_title WHERE fid_id_elector = "+elector.getId()+";";
        Cursor c = dao.getReadableDatabase().rawQuery(sql,null);
        if(c.moveToNext()){
            long id = c.getInt(c.getColumnIndex("fid_id_title"));
            String num =c.getString(c.getColumnIndex("fid_number"));
            String zone = c.getString(c.getColumnIndex("fid_zone"));
            String sect = c.getString(c.getColumnIndex("fid_section"));
            this.title = new Title(num, zone,sect);
            this.title.setId(id);
            dao.close();
        }
        return this.title;
    }

}

