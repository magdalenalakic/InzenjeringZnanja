package controller;

import view.FizikalniPregledWindow;
import view.UnesiRezDIWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnesiRezDIListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        UnesiRezDIWindow wz1 = UnesiRezDIWindow.getInstance();
        wz1.init();

    }
}
