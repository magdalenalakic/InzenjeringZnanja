package controller;

import com.ugos.jiprolog.engine.JIPEngine;
import main.TerapijaApp;
import model.Dijagnoze;
import model.IzabranaOpcija;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import view.IzvjestajWindow;
import view.MainWindow;
import view.PredloziDijagnozuWindow;
import view.PredloziTerapijuWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzvjetsajListener  implements ActionListener {

    private JIPEngine engine = new JIPEngine();



    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(MainWindow.getInstance().getTrenutnoAktivanPacijent().getListaLekova().size());

        IzvjestajWindow wz1 = IzvjestajWindow.getInstance().getInstance();
        wz1.init();

    }
}
