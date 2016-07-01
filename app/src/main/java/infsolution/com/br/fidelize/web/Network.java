package infsolution.com.br.fidelize.web;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONStringer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


/**
 * Created by Cicero on 13/06/2016.
 */
public class Network {

    public Network(){

    }
    public static String getJSONFromApi(String url){
        String response="";
        try {
            URL pathApi = new URL(url);
            int responseCod;
            HttpURLConnection connect;
            InputStream inS;

            connect =(HttpURLConnection)pathApi.openConnection();
            connect.setRequestMethod("GET");
            connect.setReadTimeout(15000);
            connect.setConnectTimeout(15000);
            connect.connect();

            responseCod = connect.getResponseCode();
            if(responseCod < HttpURLConnection.HTTP_BAD_REQUEST){
                inS = connect.getInputStream();
            }else{
                inS = connect.getErrorStream();
            }
            response = convertInputStreamToString(inS);
            inS.close();
            connect.disconnect();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    private static String convertInputStreamToString(InputStream inS){
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader bfr;
            String row;
            bfr = new BufferedReader(new InputStreamReader(inS));
            while ((row=bfr.readLine())!=null){
                buffer.append(row);
            }
            bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
