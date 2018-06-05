/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m95952
 */
public class FileReader {
    private final String FILE_PATH = "data/dados.txt";
    
    
    public void read(){
        File f = new File(FILE_PATH);
        try {
            BufferedReader bf = new BufferedReader(new java.io.FileReader(f));
            
            String line = bf.readLine();
            
            while(line != null){
                
                
                String[] array = line.trim().split(" ");
                System.out.println(Arrays.toString(array));
                
                line = bf.readLine();
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
