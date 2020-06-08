package controller;

import model.IzabranaOpcija;
import view.MainWindow;
import view.WelcomeWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RBCheckedListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        MainWindow wz2 = MainWindow.getInstance();
        MainWindow.getInstance().setIzabranaOpcija(IzabranaOpcija.RB);
        wz2.setVisible(true);
        WelcomeWindow.getInstance().setVisible(false);

    }
}
