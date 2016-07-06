package infsolution.com.br.fidelize;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONStringer;

import infsolution.com.br.fidelize.dao.ElectorDAO;
import infsolution.com.br.fidelize.dao.TitleDAO;
import infsolution.com.br.fidelize.model.Elector;
import infsolution.com.br.fidelize.model.Title;

public class FidelizeMainActivity extends AppCompatActivity {
    private Elector eleitor;
    private Button bt;
    private EditText nomeEdit;
    private EditText escolaEdit;
    private EditText partidoEdit;
    private EditText title;
    private EditText zone;
    private EditText section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fidelize_main);

        this.bt = (Button)findViewById(R.id.bt_new_elector);


    }

    public void insert(View v){
        String nomeEle = this.nomeEdit.getText().toString();
        String partyEle = this.partidoEdit.getText().toString();
        String escolar = this.escolaEdit.getText().toString();
        eleitor = new Elector(nomeEle);
        eleitor.setParty(partyEle);
        eleitor.setScholarity(escolar);
        ElectorDAO dao = new ElectorDAO(this);
        String resu = dao.insert(eleitor);
        dao.close();
        Title title = goTitle();
        eleitor.setTitle(title);
        TitleDAO tDao = new TitleDAO(this);
        long ok = tDao.insertTitle(eleitor.getTitle());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        tDao.close();
        if(ok!=0){
            builder.setMessage(resu);
        }else{
            builder.setMessage("Houve um problema.");
        }

        builder.show();
    }

    public Title goTitle(){
        String numTitle = this.title.getText().toString();
        String zoneTitle = this.zone.getText().toString();
        String sectTitle = this.section.getText().toString();
        Title title = new Title(numTitle,zoneTitle,sectTitle);
        return title;
    }
    public void goElector(View v){
        Intent goToElector = new Intent(this,ElectorsActivity.class);
        startActivity(goToElector);
    }
    public void webElector(View v){
        Intent goToElector = new Intent(this,ElectorWebActivity.class);
        startActivity(goToElector);
    }

    public void goToNewElector(View v){
        Intent gtEle = new Intent(this,NewElectorActivity.class);
        startActivity(gtEle);
    }

}
