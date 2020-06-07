package controller;

import com.thoughtworks.xstream.XStream;
import model.Pacijent;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.io.*;

public class CuvanjePacijenta extends AbstractAction {

    private OutputStream os;
    private Pacijent pacijent;

    @Override
    public void actionPerformed(ActionEvent e) {
        File f = new File("database/pacijenti.txt");
        System.out.println(f.getAbsolutePath());

//        try {
//
//            XStream xs = new XStream();
//            os = new BufferedOutputStream(new FileOutputStream(f));
//            xs.alias("Pacijent", Pacijent.class);
//
//            DefaultMutableTreeNode workspaceNode = (DefaultMutableTreeNode) RadniOkvir.getInstance().getTreeHandler().getTreeModel().getRoot();
//
//
//            xs.toXML(workspaceNode, os);
//
//
//        } catch (FileNotFoundException ex) {
//            // TODO Auto-generated catch block
//            ex.printStackTrace();
//        } finally {
//            try {
//                os.close();
//            } catch (IOException ex) {
//                // TODO Auto-generated catch block
//                ex.printStackTrace();
//            }
//
//
//        }

    }
}
