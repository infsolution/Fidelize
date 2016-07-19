package infsolution.com.br.fidelize.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import infsolution.com.br.fidelize.web.Network;

/**
 * Created by Cicero on 15/06/2016.
 */
public class Utils {
    public Utils(){

    }
    public List<Elector> getInfElector(String res){
        String json;
        json = Network.getJSONFromApi(res);
        Log.i("Resultado",json);
        List<Elector> electors = parserJson(json);
        return electors;
    }
    private List<Elector> parserJson(String json){
        List<Elector> electors = new ArrayList<>();
        try {
            JSONObject jObj = new JSONObject(json);
            JSONArray elecArray = jObj.getJSONArray("Electors");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            Date date;
            for(int i = 0;i<elecArray.length();i++){
            JSONObject jOArray = elecArray.getJSONObject(i);
            Elector elec = new Elector(jOArray.getString("fid_name"));
            elec.setId(jOArray.getLong("fid_id_elector"));
            date = new Date(jOArray.getLong("fid_date_birth")*1000);
            elec.setDateBirth(sdf.format(date));
            elec.setScholarity(jOArray.getString("fid_scholarity"));
            elec.setParty(jOArray.getString("fid_party"));
            elec.setAffiliate(jOArray.getInt("fid_affilate"));
            elec.setSex(jOArray.getString("fid_sex"));
            electors.add(elec);
            }
            return electors;
        }catch (JSONException e){
            e.printStackTrace();
            return  null;
        }

    }
}
