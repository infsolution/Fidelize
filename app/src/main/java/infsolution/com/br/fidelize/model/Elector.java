package infsolution.com.br.fidelize.model;

import android.app.Activity;

import infsolution.com.br.fidelize.dao.TitleDAO;

/**
 * Created by Cicero on 05/06/2016.
 */
public class Elector extends User{

    public Elector(String name){
        super(name);
    }



    @Override
    public String toString() {
        String elector="";
        if(getTitle()==null){
        elector= "Eleitor: "+getName();}
        else{
            elector="Eleitor: "+getName()+"\nTitulo: "+getTitle().getNumber();
        }
        if(getAddress()!=null) {
            elector+="\nRua: "+getAddress().getStreet()+" Numero: "+getAddress().getNumber();
        }
        return elector;
    }
}
