package infsolution.com.br.fidelize.dao;

/**
 * Created by Cicero on 27/06/2016.
 */
public class ElectorAdress {
    private long elector;
    private long adress;
    public ElectorAdress(long elector, long adress){
        this.elector=elector;
        this.adress=adress;

    }

    public long getElector() {
        return elector;
    }

    public void setElector(long elector) {
        this.elector = elector;
    }

    public long getAdress() {
        return adress;
    }

    public void setAdress(long adress) {
        this.adress = adress;
    }
}
