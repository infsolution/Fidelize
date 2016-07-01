package infsolution.com.br.fidelize.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cicero on 05/06/2016.
 *
 */
public class Campaigner extends User {
    private String Zone;
    private List<Elector> electors= new ArrayList<>();
    public Campaigner(String name){
        super(name);
    }

    public String getZone() {
        return Zone;
    }

    public void setZone(String zone) {
        Zone = zone;
    }

    public List<Elector> getElectors() {
        return electors;
    }

    @Override
    public String toString() {
        return "Colaborador: "+getName()+"Titulo: "+getTitle().toString();
    }
}
