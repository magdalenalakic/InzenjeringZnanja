package controller;

import model.AuskultacijaEnum;
import view.FizikalniPregledWindow;
import view.MainWindow;
import view.PredloziDodatnaIspitivanjaWindow;
import view.WelcomeWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PredloziDodatnaIspitivanjaListener implements ActionListener {

    public static PacijentController pacijentController = new PacijentController();

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow.getInstance().getStatusLinija().setForeground(new Color(255,0,0));

        if(FizikalniPregledWindow.getInstance().getPritisakG() == null || FizikalniPregledWindow.getInstance().getPritisakG().getText().equals("") ||
                FizikalniPregledWindow.getInstance().getPritisakD() == null || FizikalniPregledWindow.getInstance().getPritisakD().getText().equals("")){
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }
        Integer pritisakGornji = null;
        Integer pritisakDonji = null;
        AuskultacijaEnum auskultacija = null;
        try{
            pritisakGornji = Integer.parseInt(FizikalniPregledWindow.getInstance().getPritisakG().getText());
            pritisakDonji = Integer.parseInt(FizikalniPregledWindow.getInstance().getPritisakD().getText());
        }catch (NumberFormatException nfe){
            MainWindow.getInstance().getStatusLinija().setText("Popunite polje za pritisak iskljucivo brojevima!");
            return;
        }

        pritisakGornji = Integer.valueOf(FizikalniPregledWindow.getInstance().getPritisakG().getText());
        pritisakDonji = Integer.valueOf(FizikalniPregledWindow.getInstance().getPritisakD().getText());
        if(FizikalniPregledWindow.getInstance().getUredna().isSelected()){
            auskultacija = AuskultacijaEnum.uredna;
        }else if(FizikalniPregledWindow.getInstance().getPostojiSum().isSelected()){
            auskultacija = AuskultacijaEnum.postojiSum;
        }else if(FizikalniPregledWindow.getInstance().getPoremecajRitma().isSelected()){
            auskultacija = AuskultacijaEnum.poremecajRitma;
        }else{
            MainWindow.getInstance().getStatusLinija().setText("Popunite sva polja!");
            return;
        }
        MainWindow.getInstance().getTrenutnoAktivanPacijent().setRezPritiska(pacijentController.racunanjeRezultataPritiska(pritisakGornji,pritisakDonji));
        MainWindow.getInstance().getTrenutnoAktivanPacijent().setAuskultacija(auskultacija);

        MainWindow.getInstance().getStatusLinija().setForeground(new Color(0, 255,0));
        MainWindow.getInstance().getStatusLinija().setText("Podaci su sacuvani!");

        PredloziDodatnaIspitivanjaWindow wz1 = PredloziDodatnaIspitivanjaWindow.getInstance();
        wz1.setVisible(true);
        WelcomeWindow.getInstance().setVisible(false);
    }
}
