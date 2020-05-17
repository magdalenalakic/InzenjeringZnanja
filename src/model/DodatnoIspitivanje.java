package model;

import java.util.List;

public class DodatnoIspitivanje {

    //napraviti fajl iz kog ce moci da se iscitaju portrebni simptomi za odredjeno dodatno ispitivanje
    private DodatnaIspitivanja naziv;
    private List<Simptomi> potrebniSimptomi;


    public DodatnoIspitivanje(){ }

    public DodatnoIspitivanje(DodatnaIspitivanja naziv, List<Simptomi> potrebniSimptomi) {
        this.naziv = naziv;
        this.potrebniSimptomi = potrebniSimptomi;
    }

    public DodatnaIspitivanja getNaziv() {
        return naziv;
    }

    public void setNaziv(DodatnaIspitivanja naziv) {
        this.naziv = naziv;
    }

    public List<Simptomi> getPotrebniSimptomi() {
        return potrebniSimptomi;
    }

    public void setPotrebniSimptomi(List<Simptomi> potrebniSimptomi) {
        this.potrebniSimptomi = potrebniSimptomi;
    }
}
