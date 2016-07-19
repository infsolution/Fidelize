package infsolution.com.br.fidelize;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import infsolution.com.br.fidelize.dao.AddressDAO;
import infsolution.com.br.fidelize.dao.ElectorDAO;
import infsolution.com.br.fidelize.dao.TitleDAO;
import infsolution.com.br.fidelize.dialogs.DateTime;
import infsolution.com.br.fidelize.dialogs.DialogTitle;
import infsolution.com.br.fidelize.model.Address;
import infsolution.com.br.fidelize.model.Elector;
import infsolution.com.br.fidelize.model.Title;
import infsolution.com.br.fidelize.tools.Tools;

public class NewElectorActivity extends AppCompatActivity {
   private Button btDate;
    private Button btTitle;
    private Button btAddress;
    private Button btSave;
    private EditText etName;
    private RadioGroup rdSex;
    private RadioGroup rdFil;
    private Spinner spSchol;
    private TitleDAO tDao;
    private Title newTitle;
    private AddressDAO aDao;
    private Address address;
    private long idTitle;
    private long idAddres=0;
    private String dt="";
    private String tit="";
    private String sex="";
    private String adrsSetBut="";
    private int affiliate=0;

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
        rdSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbt_female:
                        sex="Feminino";
                        break;
                    case R.id.rbt_male:
                        sex="Masculino";
                        break;
                }
            }
        });
        rdFil.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbt_yes:
                        affiliate=1;
                        break;
                    case R.id.rbt_not:
                        affiliate=0;
                        break;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!this.dt.equals("")){
            btDate.setText(this.dt);
        }
        if(!tit.equals("")){
            btTitle.setText(tit);
        }
        if(!adrsSetBut.equals("")){
            btAddress.setText(adrsSetBut);
        }
    }

    public void newDateBirth(View v){
        DateTime date = new DateTime(v);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        date.show(ft,"Data");
        this.dt=date.getDate();

    }

    public void newTitle(View v){
        AlertDialog.Builder buider = new AlertDialog.Builder(this);
        LayoutInflater inf = LayoutInflater.from(this);
        final View layoutView =inf.inflate(R.layout.dialog_new_title,null);
        buider.setView(layoutView);
        buider.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                final EditText title = (EditText)layoutView.findViewById(R.id.dlg_title_num);
                final EditText zone =(EditText)layoutView.findViewById(R.id.dlg_title_zone);
                final EditText section = (EditText)layoutView.findViewById(R.id.dlg_title_sec);
                tit = title.getText().toString();
                String zon = zone.getText().toString();
                String sec = section.getText().toString();
                tDao = new TitleDAO(NewElectorActivity.this);
                newTitle = new Title(tit,zon,sec);
                idTitle= tDao.insertTitle(newTitle);
                tDao.close();
                if(idTitle == -1){
                    Toast.makeText(NewElectorActivity.this, "O titulo já é cadastrado para um eleitor!", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(NewElectorActivity.this, "Titulo salvo", Toast.LENGTH_SHORT).show();
                    btTitle.setText(newTitle.getNumber());
                    newTitle.setId(idTitle);
                }

            }
        }).setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Toast.makeText(NewElectorActivity.this, "Operação cancelada!", Toast.LENGTH_SHORT).show();
            }
        });
        buider.show();
    }
    public void newAddress(View v){
        AlertDialog.Builder buider = new AlertDialog.Builder(this);
        LayoutInflater inf = LayoutInflater.from(this);
        final View layoutView =inf.inflate(R.layout.dialog_new_address,null);
        buider.setView(layoutView);
        buider.setPositiveButton(getString(R.string.ok), new Dialog.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                final EditText street =(EditText)layoutView.findViewById(R.id.edt_new_address_street);
                final EditText num = (EditText)layoutView.findViewById(R.id.edt_new_address_number);
                final EditText district = (EditText)layoutView.findViewById(R.id.edt_new_address_district);
                final EditText city =  (EditText)layoutView.findViewById(R.id.edt_new_address_city);
                final Spinner state = (Spinner)layoutView.findViewById(R.id.spin_state);
                final Spinner country = (Spinner)layoutView.findViewById(R.id.spin_country);
                final EditText zipCode = (EditText) layoutView.findViewById(R.id.edt_newaddress__zip_code);
                String st = street.getText().toString();
                String nu = num.getText().toString();
                String dist = district.getText().toString();
                String cty = city.getText().toString();
                String stt = state.getSelectedItem().toString();
                String count = country.getSelectedItem().toString();
                String zip = zipCode.getText().toString();
                aDao = new AddressDAO(NewElectorActivity.this);
                address = new Address(st,nu,cty);
                address.setState(stt);
                address.setCountry(count);
                address.setZipCode(zip);
                idAddres = aDao.insertAddress(address);
                address.setId(idAddres);
                Toast.makeText(NewElectorActivity.this, "Endereço salvo!", Toast.LENGTH_SHORT).show();
                Tools t = new Tools();
                adrsSetBut = t.newSubString(st);
                btAddress.setText(adrsSetBut);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Toast.makeText(NewElectorActivity.this, "Operação cancelada!", Toast.LENGTH_SHORT).show();
            }
        });
        buider.show();
    }

    public void saveElector(View v){
        String name = etName.getText().toString();
        String school = spSchol.getSelectedItem().toString();
        ElectorDAO eDao = new ElectorDAO(NewElectorActivity.this);
        Elector elec = new Elector(name);
        elec.setDateBirth(this.dt);
        elec.setAffiliate(affiliate);
        elec.setSex(sex);
        elec.setScholarity(school);
        if(newTitle!=null)
        elec.setTitle(newTitle);
        if(address!=null)
        elec.setAddress(address);
        long ins = eDao.insert(elec);
        
        if(ins==0){
            Toast.makeText(NewElectorActivity.this, "Eleitor já cadastrado!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(NewElectorActivity.this,"Eleitor cadastrado!", Toast.LENGTH_SHORT).show();

        }
        finish();
    }

}
