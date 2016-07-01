package infsolution.com.br.fidelize;

import android.app.ProgressDialog;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import infsolution.com.br.fidelize.dao.ElectorDAO;
import infsolution.com.br.fidelize.model.Elector;
import infsolution.com.br.fidelize.model.Utils;

public class ElectorWebActivity extends AppCompatActivity {
    private ProgressDialog load;



    private ListView listElector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elector_web);

        GetJson json = new GetJson();

        listElector = (ListView)findViewById(R.id.list_web);
        //String response = makeRequest("http://www.infsolution.com.br/fidelize/WebService.php");
        //("http://192.168.56.1/webservice/index.php");
            json.execute();

    }
    private class GetJson extends AsyncTask<Elector,Elector,List<Elector>>{


        @Override
        protected void onPreExecute(){
            load = ProgressDialog.show(ElectorWebActivity.this,"Carregando dados...","Aquarde...");

        }

        @Override
        protected List<Elector> doInBackground(Elector... params){
            Utils utils = new Utils();
            return utils.getInfElector("http://192.168.1.2/webservice/index.php");

        }
        @Override
        protected void onPostExecute(List<Elector> electors){
            super.onPostExecute(electors);
            load.dismiss();
            if(electors==null){
                AlertDialog.Builder builder = new AlertDialog.Builder(ElectorWebActivity.this).setTitle("Atenção")
                        .setMessage("A base de dados não exite!").setPositiveButton("OK", null);
                builder.create().show();
            }else{
                listElector = (ListView)findViewById(R.id.list_web);
                ArrayAdapter<Elector> adapter = new ArrayAdapter<Elector>(ElectorWebActivity.this,android.R.layout.simple_list_item_1,electors);
                listElector.setAdapter(adapter);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


}
