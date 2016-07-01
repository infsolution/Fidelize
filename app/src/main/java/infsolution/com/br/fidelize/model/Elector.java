package infsolution.com.br.fidelize.model;

import android.app.Activity;

import infsolution.com.br.fidelize.dao.TitleDAO;

/**
 * Created by Cicero on 05/06/2016.
 */
public class Elector extends User{
    private boolean fidelity;
    private Activity activity;
    public Elector(String name){
        super(name);
    }

    public boolean isFidelity(){
        return fidelity;
    }

    public void setFidelity(boolean fidelity){
        this.fidelity = fidelity;
    }
    public Title getTitleEle(Elector elector){
        TitleDAO dao = new TitleDAO(activity);
        Title title =dao.pullIitle(elector);
        return title;
    }

    @Override
    public String toString() {
        String elector="";
        if(getTitle()==null){
        elector= "Eleitor: "+getName();}
        else{
            elector="Eleitor: "+getName()+"\nTitulo: "+getTitle().toString();
        }
        return elector;
    }
}
