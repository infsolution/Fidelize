package infsolution.com.br.fidelize.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import infsolution.com.br.fidelize.NewElectorActivity;
import infsolution.com.br.fidelize.R;
import infsolution.com.br.fidelize.dao.TitleDAO;
import infsolution.com.br.fidelize.model.Title;

/**
 * Created by Cicero on 01/07/2016.
 */
public class DialogTitle extends DialogFragment {
    private TitleDAO tDao;
    private Title newTitle;
    private EditText title;
    private EditText zone;
    private EditText section;
    private View layoutView;
    @Override
    public Dialog onCreateDialog(Bundle SavedStanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inf = getActivity().getLayoutInflater();
        layoutView =inf.inflate(R.layout.dialog_new_title,null);
        builder.setView(inf.inflate(R.layout.dialog_new_title,null)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                title = (EditText)layoutView.findViewById(R.id.dlg_title_num);
                zone =(EditText)layoutView.findViewById(R.id.dlg_title_zone);
                section = (EditText)layoutView.findViewById(R.id.dlg_title_sec);
                String tit = title.getText().toString();
                String zon = zone.getText().toString();
                String sec = section.getText().toString();
                tDao = new TitleDAO(getActivity());
                newTitle = new Title(tit,zon,sec);
                long res= tDao.insertTitle(newTitle);
                tDao.close();
                if(res==0){
                    Toast.makeText(getActivity(), res+": O titulo não foi salvo", Toast.LENGTH_SHORT).show();
                }else if(res == 112){
                    Toast.makeText(getActivity(), res+": O titulo já esta cadastrado!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), res+": Titulo Salvo!", Toast.LENGTH_SHORT).show();
                }

            }
        }).setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Toast.makeText(getActivity(), "Operação Foi Cancelada!", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }
}
