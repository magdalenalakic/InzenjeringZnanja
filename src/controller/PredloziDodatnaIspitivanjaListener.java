package controller;

import view.FizikalniPregledWindow;
import view.PredloziDodatnaIspitivanjaWindow;
import view.WelcomeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PredloziDodatnaIspitivanjaListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PredloziDodatnaIspitivanjaWindow wz1 = PredloziDodatnaIspitivanjaWindow.getInstance();
        wz1.setVisible(true);
        WelcomeWindow.getInstance().setVisible(false);
    }
}
