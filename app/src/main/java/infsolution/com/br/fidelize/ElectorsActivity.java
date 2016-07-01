package infsolution.com.br.fidelize;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import infsolution.com.br.fidelize.dao.ElectorDAO;
import infsolution.com.br.fidelize.model.Elector;

public class ElectorsActivity extends AppCompatActivity {
    private ListView listElector;
    private ElectorDAO eleDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electors);
        listElector = (ListView)findViewById(R.id.list_electors);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    public void loadList(){
        listElector = (ListView)findViewById(R.id.list_electors);
        eleDao = new ElectorDAO(this);
        List<Elector> electors = eleDao.listEle();
        ArrayAdapter<Elector> adapter = new ArrayAdapter<Elector>(this,android.R.layout.simple_list_item_1,electors);
        listElector.setAdapter(adapter);
        eleDao.close();
    }


}
