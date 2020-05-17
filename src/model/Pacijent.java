package model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pacijent implements CaseComponent {
    private Long id;
    private String ime;
    private PolEnum pol;
    private Integer godine;
    private TezinaEnum tezina;
    private Boolean pusac;
    private Boolean dijabeticar;
    private Boolean asmaticar;
    private Boolean fizickaAktivnost;
    private Boolean trudnoca;
    private Boolean alergican;
    private AuskultacijaEnum auskultacija;
    private Integer gornjiPritisak;
    private Integer donjiPritisak;
    private RezPritiskaEnum rezPritiska;
    private List<PorodicneBolesti> porodicneBolesti = new ArrayList<>();
    private List<Simptomi> listaSimptoma = new ArrayList<>();
    private List<DodatnaIspitivanjaEnum> listaDodatnihIspitivanja = new ArrayList<>();
    private HashMap<DodatnaIspitivanjaEnum, List<String>> listaRezultataDodatnihIspitivanja = new HashMap<>();
    private List<Dijagnoze> listaDijagnoza = new ArrayList<>();
    private List<Lekovi> listaLekova = new ArrayList<>();


    public Pacijent(Long id, String ime, PolEnum pol, Integer godine, TezinaEnum tezina, Boolean pusac,
                    Boolean dijabeticar, Boolean asmaticar, Boolean fizickaAktivnost, Boolean trudnoca,
                    Boolean alergican, AuskultacijaEnum auskultacija, Integer gornjiPritisak,
                    Integer donjiPritisak, RezPritiskaEnum rezPritiska, List<PorodicneBolesti> porodicneBolesti,
                    List<Simptomi> listaSimptoma, List<DodatnaIspitivanjaEnum> listaDodatnihIspitivanja,
                    HashMap<DodatnaIspitivanjaEnum, List<String>> listaRezultataDodatnihIspitivanja,
                    List<Dijagnoze> listaDijagnoza, List<Lekovi> listaLekova) {
        this.id = id;
        this.ime = ime;
        this.pol = pol;
        this.godine = godine;
        this.tezina = tezina;
        this.pusac = pusac;
        this.dijabeticar = dijabeticar;
        this.asmaticar = asmaticar;
        this.fizickaAktivnost = fizickaAktivnost;
        this.trudnoca = trudnoca;
        this.alergican = alergican;
        this.auskultacija = auskultacija;
        this.gornjiPritisak = gornjiPritisak;
        this.donjiPritisak = donjiPritisak;
        this.rezPritiska = rezPritiska;
        this.porodicneBolesti = porodicneBolesti;
        this.listaSimptoma = listaSimptoma;
        this.listaDodatnihIspitivanja = listaDodatnihIspitivanja;
        this.listaRezultataDodatnihIspitivanja = listaRezultataDodatnihIspitivanja;
        this.listaDijagnoza = listaDijagnoza;
        this.listaLekova = listaLekova;
    }

    public Pacijent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public PolEnum getPol() {
        return pol;
    }

    public void setPol(PolEnum pol) {
        this.pol = pol;
    }

    public Integer getGodine() {
        return godine;
    }

    public void setGodine(Integer godine) {
        this.godine = godine;
    }

    public TezinaEnum getTezina() {
        return tezina;
    }

    public void setTezina(TezinaEnum tezina) {
        this.tezina = tezina;
    }

    public Boolean getPusac() {
        return pusac;
    }

    public void setPusac(Boolean pusac) {
        this.pusac = pusac;
    }

    public Boolean getDijabeticar() {
        return dijabeticar;
    }

    public void setDijabeticar(Boolean dijabeticar) {
        this.dijabeticar = dijabeticar;
    }

    public Boolean getAsmaticar() {
        return asmaticar;
    }

    public void setAsmaticar(Boolean asmaticar) {
        this.asmaticar = asmaticar;
    }

    public Boolean getFizickaAktivnost() {
        return fizickaAktivnost;
    }

    public void setFizickaAktivnost(Boolean fizickaAktivnost) {
        this.fizickaAktivnost = fizickaAktivnost;
    }

    public Boolean getTrudnoca() {
        return trudnoca;
    }

    public void setTrudnoca(Boolean trudnoca) {
        this.trudnoca = trudnoca;
    }

    public Boolean getAlergican() {
        return alergican;
    }

    public void setAlergican(Boolean alergican) {
        this.alergican = alergican;
    }

    public AuskultacijaEnum getAuskultacija() {
        return auskultacija;
    }

    public void setAuskultacija(AuskultacijaEnum auskultacija) {
        this.auskultacija = auskultacija;
    }

    public Integer getGornjiPritisak() {
        return gornjiPritisak;
    }

    public void setGornjiPritisak(Integer gornjiPritisak) {
        this.gornjiPritisak = gornjiPritisak;
    }

    public Integer getDonjiPritisak() {
        return donjiPritisak;
    }

    public void setDonjiPritisak(Integer donjiPritisak) {
        this.donjiPritisak = donjiPritisak;
    }

    public RezPritiskaEnum getRezPritiska() {
        return rezPritiska;
    }

    public void setRezPritiska(RezPritiskaEnum rezPritiska) {
        this.rezPritiska = rezPritiska;
    }

    public List<PorodicneBolesti> getPorodicneBolesti() {
        return porodicneBolesti;
    }

    public void setPorodicneBolesti(List<PorodicneBolesti> porodicneBolesti) {
        this.porodicneBolesti = porodicneBolesti;
    }

    public List<Simptomi> getListaSimptoma() {
        return listaSimptoma;
    }

    public void setListaSimptoma(List<Simptomi> listaSimptoma) {
        this.listaSimptoma = listaSimptoma;
    }

    public List<DodatnaIspitivanjaEnum> getListaDodatnihIspitivanja() {
        return listaDodatnihIspitivanja;
    }

    public void setListaDodatnihIspitivanja(List<DodatnaIspitivanjaEnum> listaDodatnihIspitivanja) {
        this.listaDodatnihIspitivanja = listaDodatnihIspitivanja;
    }

    public HashMap<DodatnaIspitivanjaEnum, List<String>> getListaRezultataDodatnihIspitivanja() {
        return listaRezultataDodatnihIspitivanja;
    }

    public void setListaRezultataDodatnihIspitivanja(HashMap<DodatnaIspitivanjaEnum, List<String>> listaRezultataDodatnihIspitivanja) {
        this.listaRezultataDodatnihIspitivanja = listaRezultataDodatnihIspitivanja;
    }

    public List<Dijagnoze> getListaDijagnoza() {
        return listaDijagnoza;
    }

    public void setListaDijagnoza(List<Dijagnoze> listaDijagnoza) {
        this.listaDijagnoza = listaDijagnoza;
    }

    public List<Lekovi> getListaLekova() {
        return listaLekova;
    }

    public void setListaLekova(List<Lekovi> listaLekova) {
        this.listaLekova = listaLekova;
    }

    @Override
    public Attribute getIdAttribute() {
        return null;
    }

    @Override
    public String toString() {
        return "Pacijent{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", pol=" + pol +
                ", godine=" + godine +
                ", tezina=" + tezina +
                ", pusac=" + pusac +
                ", dijabeticar=" + dijabeticar +
                ", asmaticar=" + asmaticar +
                ", fizickaAktivnost=" + fizickaAktivnost +
                ", trudnoca=" + trudnoca +
                ", alergican=" + alergican +
                ", auskultacija=" + auskultacija +
                ", gornjiPritisak=" + gornjiPritisak +
                ", donjiPritisak=" + donjiPritisak +
                ", rezPritiska=" + rezPritiska +
                ", porodicneBolesti=" + porodicneBolesti +
                ", listaSimptoma=" + listaSimptoma +
                ", listaDodatnihIspitivanja=" + listaDodatnihIspitivanja +
                ", listaRezultataDodatnihIspitivanja=" + listaRezultataDodatnihIspitivanja +
                ", listaDijagnoza=" + listaDijagnoza +
                ", listaLekova=" + listaLekova +
                '}';
    }
}
