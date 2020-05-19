package connector;

import model.DodatnaIspitivanjaEnum;
import model.PorodicneBolesti;
import ucm.gaia.jcolibri.exception.NoApplicableSimilarityFunctionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProveraRezDodIsUListi implements LocalSimilarityFunction {
    @Override
    public double compute(Object o, Object o1) throws NoApplicableSimilarityFunctionException {
        HashMap<DodatnaIspitivanjaEnum, List<String>> map1 = (HashMap<DodatnaIspitivanjaEnum, List<String>>) o;
        HashMap<DodatnaIspitivanjaEnum, List<String>> map2 = (HashMap<DodatnaIspitivanjaEnum, List<String>>) o1;
        int brojac = 0;

        System.out.println("--------------------------");
        if(map2.size()==0){
            return 1.0;
        }
        for(DodatnaIspitivanjaEnum s : map2.keySet()){
            if(map1.containsKey(s)){

                if(s == DodatnaIspitivanjaEnum.analizaKrvi){
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    List<Double> rez2 = new ArrayList<>();
                    for(String r : rezultati2){
                        rez2.add(Double.parseDouble(r));
                    }
                    List<Double> rez1 = new ArrayList<>();
                    for(String r : rezultati1){
                        rez1.add(Double.parseDouble(r));
                    }
                    if( Math.abs(rez1.get(0) - rez2.get(0)) <= 0.2 ){
                        System.out.println("1");
                        if(Math.abs(rez1.get(1) - rez2.get(1)) <= 0.2){
                            System.out.println("2");
                            if(Math.abs(rez1.get(2) - rez2.get(2)) <= 0.2){
                                System.out.println("3");
                                brojac++;
                            }
                        }
                    }
                }
                if(s == DodatnaIspitivanjaEnum.ekg){
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    if(rezultati2.get(0).equals(rezultati1.get(0))){
                        if(rezultati2.get(1).equals(rezultati1.get(1))){
                            brojac++;
                        }
                    }

                }
                if(s == DodatnaIspitivanjaEnum.ct) {
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    if(rezultati2.get(0).equals(rezultati1.get(0))){
                        brojac++;
                    }
                }
                if(s == DodatnaIspitivanjaEnum.ehokardiografija){
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    if(rezultati2.get(0).equals(rezultati1.get(0))){
                        brojac++;
                    }
                }
                if(s == DodatnaIspitivanjaEnum.ergometrija){
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    if(rezultati2.get(0).equals(rezultati1.get(0))){
                        brojac++;
                    }
                }
                if(s == DodatnaIspitivanjaEnum.holter24){
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    if(rezultati2.get(0).equals(rezultati1.get(0))){
                        if(rezultati2.get(1).equals(rezultati1.get(1))){
                            if(rezultati2.get(2).equals(rezultati1.get(2))){
                                brojac++;
                            }
                        }
                    }
                }
                if(s == DodatnaIspitivanjaEnum.koronarnaAngiografija){
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    if(rezultati2.get(0).equals(rezultati1.get(0))){
                        brojac++;
                    }
                }
                if(s == DodatnaIspitivanjaEnum.rendgen){
                    List<String> rezultati2 = map2.get(s);
                    List<String> rezultati1 = map1.get(s);
                    System.out.println("Rez 2: " + rezultati2);
                    System.out.println("Rez 1: " + rezultati1);
                    if(rezultati2.get(0).equals(rezultati1.get(0))){
                        brojac++;
                    }
                }

            }
        }
        System.out.println("Brojac :" + brojac);
        double ret = (double) brojac/map2.size();

        System.out.println("--------------------------");
        return ret;
    }

    @Override
    public boolean isApplicable(Object o, Object o1) {
        return false;
    }
}
