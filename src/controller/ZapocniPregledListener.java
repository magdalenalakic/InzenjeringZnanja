package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZapocniPregledListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow.getInstance().prikaziInfoPacijenta();
    }
}
