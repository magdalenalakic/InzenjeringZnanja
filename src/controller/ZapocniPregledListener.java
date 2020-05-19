package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZapocniPregledListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ZAPOCINJE PREGLED");
        System.out.println(String.valueOf(MainWindow.getInstance().getCbPacijenti().getSelectedItem()));
        MainWindow.getInstance().prikaziInfoPacijenta();
    }
}
