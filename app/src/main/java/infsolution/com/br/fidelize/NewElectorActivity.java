package infsolution.com.br.fidelize;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import infsolution.com.br.fidelize.dialogs.DateTime;

public class NewElectorActivity extends AppCompatActivity {
    Button btDate;
    Button btTitle;
    Button btAddress;
    Button btSave;
    EditText etName;
    RadioGroup rdSex;
    RadioGroup rdFil;
    Spinner spSchol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_elector);
        btDate = (Button)findViewById(R.id.bt_new_date_birth);
        btTitle = (Button)findViewById(R.id.bt_go_new_title);
        btAddress = (Button)findViewById(R.id.bt_go_new_address);
        btSave = (Button)findViewById(R.id.bt_save_elector);
        etName = (EditText)findViewById(R.id.et_name);
        rdFil =(RadioGroup)findViewById(R.id.rg_affiliate);
        rdSex = (RadioGroup)findViewById(R.id.rg_sex);
        spSchol = (Spinner)findViewById(R.id.spin_scholarity);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void newDateBirth(View v){
        DateTime date = new DateTime(v);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        date.show(ft,"Data");
    }
}
