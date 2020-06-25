package controller;

import com.ugos.jiprolog.engine.*;
import main.TerapijaApp;
import model.Dijagnoze;
import model.IzabranaOpcija;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.IzvjestajWindow;
import view.MainWindow;
import view.PredloziDijagnozuWindow;
import view.PredloziTerapijuWindow;
import main.TerapijaApp;
import model.*;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzvjetsajListener  implements ActionListener {

    private JIPEngine engine = new JIPEngine();


    @Override
    public void actionPerformed(ActionEvent e) {

        MainWindow.getInstance().getTrenutnoAktivanPacijent().setListaLekova(MainWindow.getInstance().getTerapija());




        IzvjestajWindow wz1 = IzvjestajWindow.getInstance().getInstance();
        wz1.init();

    }
}