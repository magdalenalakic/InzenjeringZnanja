package controller;

import view.FizikalniPregledWindow;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FizikalniPregledListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("FIZIKALNIIIIIIIII PREGLEEEED");
        FizikalniPregledWindow wz1 = FizikalniPregledWindow.getInstance();
        wz1.setVisible(true);
        WelcomeWindow.getInstance().setVisible(false);

    }
}
