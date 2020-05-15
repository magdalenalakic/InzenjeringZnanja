package model;

import java.util.HashMap;
import java.util.List;

public class Pacijent {
    private Long id;
    private String ime;
    private String pol;
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
    private List<String> porodicneBolesti;
    private List<String> listaSimptoma;
    private List<String> listaDodatnihIspitivanja;
    private HashMap<String, List<String>> listaRezultataDodatnihIspitivanja;
    private List<String> listaDijagnoza;
    private List<String> listaLekova;




}
