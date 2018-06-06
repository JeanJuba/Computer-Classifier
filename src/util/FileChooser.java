/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author m95952
 */
public class FileChooser {
    private JFileChooser jf;
    
    /**
     * Construtor
     */
    public FileChooser() {
        jf = new JFileChooser();
        jf.setMultiSelectionEnabled(false);
        jf.setAcceptAllFileFilterUsed(false);
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //jf.setFileFilter(new FileNameExtensionFilter("Arquivos", "jpg, png, txt, docx, xls, xlsx"));
    }

    /**
     * Lê um arquivo escolhido pelo usuario e o retorna.
     * @return The chosen file or null.
     */
    public File findFile() {
        File f = null;
        int option = jf.showOpenDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            f = jf.getSelectedFile();
        }
        return f;
    }
}
