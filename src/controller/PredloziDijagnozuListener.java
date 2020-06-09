package controller;

import model.DodatnaIspitivanjaEnum;
import sun.applet.Main;
import view.FizikalniPregledWindow;
import view.MainWindow;
import view.UnesiRezDIWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PredloziDijagnozuListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        MainWindow.getInstance().getStatusLinija().setForeground(new Color(255,0,0));

        UnesiRezDIWindow udi = UnesiRezDIWindow.getInstance();

        // -------------------------------------------

        //VALIDACIJA
        for(DodatnaIspitivanjaEnum di: udi.getUnetiRezDI()){
            if(di.equals(DodatnaIspitivanjaEnum.analizaKrvi)){
                if(udi.getRezNivoSecera().equals("")){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polje za nivo secera iskljucivo brojevima!");
                    return;
                }else if(udi.getRezNivoHol().equals("")){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polje za nivo holesterola iskljucivo brojevima!");
                    return;
                }else if(udi.getRezNivoTrig().equals("")){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polje za nivo triglicerida iskljucivo brojevima!");
                    return;
                }
                Double rns = null;
                Double rnh = null;
                Double rnt = null;
                try{
                    rns = Double.valueOf(udi.getRezNivoHol().getText());
                    rnh = Double.valueOf(udi.getRezNivoHol().getText());
                    rnt = Double.valueOf(udi.getRezNivoTrig().getText());

                }catch (NumberFormatException nfe){
                    MainWindow.getInstance().getStatusLinija().setText("Popunite polja za analizu krvi iskljucivo brojevima!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.ekg)){
                if((!udi.getUredan().isSelected() && !udi.getNijeUredan().isSelected() && !udi.getNeodredjen().isSelected()) ||
                        (!udi.getUbrzan().isSelected() && !udi.getUsporen().isSelected() && !udi.getNormalan().isSelected())){
                    System.out.println("ekg");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }

            }else if(di.equals(DodatnaIspitivanjaEnum.ehokardiografija)){
                if(!udi.getUredanEh().isSelected() && !udi.getNijeUredanEh().isSelected()){
                    System.out.println("ehok");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.ergometrija)){
                if(!udi.getNiskaOpt().isSelected() && !udi.getVisokaOpt().isSelected()){
                    System.out.println("ergometrija");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.koronarnaAngiografija)){
                if(!udi.getPozitivno().isSelected() && !udi.getNegativno().isSelected()){
                    System.out.println("KA");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.rendgen)){
                if(!udi.getNijeUredanRend().isSelected() && !udi.getUredanRend().isSelected()){
                    System.out.println("rendgen");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.ct)){
                if(!udi.getNijeUredanCT().isSelected() && !udi.getUredanCT().isSelected()){
                    System.out.println("ct");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }else if(di.equals(DodatnaIspitivanjaEnum.holter24)){
                if((!udi.getPovisen().isSelected() && !udi.getSnizen().isSelected()) ||
                        (!udi.getPrisutno().isSelected() && !udi.getNijePr().isSelected()) ||
                        (!udi.getNormalanST().isSelected() && !udi.getNijeNorST().isSelected())){
                    System.out.println("holter24");
                    MainWindow.getInstance().getStatusLinija().setText("Popunite sva neophodna polja!");
                    return;
                }
            }
        }

        System.out.println("USPESNO//");

        for(DodatnaIspitivanjaEnum di: udi.getUnetiRezDI()){
            if(di.equals(DodatnaIspitivanjaEnum.analizaKrvi)){


            }else if(di.equals(DodatnaIspitivanjaEnum.ekg)){


            }else if(di.equals(DodatnaIspitivanjaEnum.ehokardiografija)){


            }else if(di.equals(DodatnaIspitivanjaEnum.ergometrija)){


            }else if(di.equals(DodatnaIspitivanjaEnum.koronarnaAngiografija)){


            }else if(di.equals(DodatnaIspitivanjaEnum.rendgen)){


            }else if(di.equals(DodatnaIspitivanjaEnum.ct)){


            }else if(di.equals(DodatnaIspitivanjaEnum.holter24)){


            }
        }



        MainWindow.getInstance().getStatusLinija().setForeground(new Color(0, 255,0));
        MainWindow.getInstance().getStatusLinija().setText("Rezultati ispitivanja uspesno sacuvani!");

//        FizikalniPregledWindow wz1 = FizikalniPregledWindow.getInstance();
//        wz1.init();

    }
}
