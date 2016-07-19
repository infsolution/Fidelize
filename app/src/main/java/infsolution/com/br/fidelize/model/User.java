package infsolution.com.br.fidelize.model;

import infsolution.com.br.fidelize.tools.Tools;

/**
 * Created by Cicero on 05/06/2016.
 */
abstract class User {
    private long id;
    private String name=null;
    private Address address =null;
    private Title title=null;
    private String party=null;
    private String sex=null;
    private String scholarity=null;
    private String dateBirth=null;
    private int affiliate=0;
    private long campaigner =0;
    private long candidate=0;
    private long code=0;
    private boolean status = false;

    public User(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getScholarity() {
        return scholarity;
    }

    public void setScholarity(String scholarity) {
        this.scholarity = scholarity;
    }

    public String getDateBirth() {
        return this.dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public int getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(int affiliate) {
        this.affiliate = affiliate;
    }

    public long getCampaigner() {
        return campaigner;
    }

    public void setCampaigner(int campaigner) {
        this.campaigner = campaigner;
    }

    public long getCandidate() {
        return candidate;
    }

    public void setCandidate(long candidate) {
        this.candidate = candidate;
    }

    public void setCampaigner(long campaigner) {
        this.campaigner = campaigner;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
