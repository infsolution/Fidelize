package infsolution.com.br.fidelize.model;

/**
 * Created by Cicero on 05/06/2016.
 */
public class Adress {
    private long id;
    private String street;
    private int number;
    private String district;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String complement;
    public Adress(String street, int number,String district){
        this.street=street;
        this.number=number;
        this.district=district;
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }


    public String getDistrict() {
        return district;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    @Override
    public String toString() {
        return "Rua: "+getStreet()+" Numero: "+getNumber()+"\nBairro: "+getDistrict();
    }
}


