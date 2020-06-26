package controller;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;
import main.DijagnozeApp;
import main.DodatnaIspitivanjaApp;
import model.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.FizikalniPregledWindow;
import view.MainWindow;
import view.UnesiRezDIWindow;
import view.WelcomeWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UnesiRezDIListener implements ActionListener {



    @Override
    public void actionPerformed(ActionEvent e) {

        UnesiRezDIWindow wz1 = UnesiRezDIWindow.getInstance();
        wz1.init();

    }
}
