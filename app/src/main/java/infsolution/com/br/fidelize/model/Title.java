package infsolution.com.br.fidelize.model;

import java.util.Timer;

/**
 * Created by Cicero on 05/06/2016.
 */
public class Title {
    private long id;
    private String number;
    private String zone;
    private String section;
    private long elector;
    public Title(String number, String zone, String section){
        this.number=number;
        this.zone=zone;
        this.section=section;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public void setSection(String section) {
        this.section = section;
    }
    public String getSection() {
        return section;
    }

    public String getZone() {
        return zone;
    }

    public String getNumber() {
        return number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getElector() {
        return elector;
    }

    public void setElector(long elector) {
        this.elector = elector;
    }

    @Override
    public String toString() {
        return "Titulo: "+getNumber()+"\nZona: "+getZone()+"\nSeção: "+getSection();
    }
}
