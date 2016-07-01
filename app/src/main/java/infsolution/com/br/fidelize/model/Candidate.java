package infsolution.com.br.fidelize.model;

import java.util.ArrayList;
import java.util.List;

import infsolution.com.br.fidelize.enums.Cargo;

/**
 * Created by Cicero on 05/06/2016.
 */
public class Candidate extends User{
    private Cargo cargo;
    private List<Elector> electors = new ArrayList<>();
    private long code;
    private List<Campaigner> campaigners = new ArrayList<>();
    private Diary diary;
    private long totalElectors;
    public Candidate(String name){
        super(name);
    }

}
