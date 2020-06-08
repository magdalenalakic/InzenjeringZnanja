package controller;

import view.FizikalniPregledWindow;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FizikalniPregledListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        FizikalniPregledWindow wz1 = FizikalniPregledWindow.getInstance();
        wz1.init();

    }
}
