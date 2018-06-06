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
public class CustomFileReader {
    private File f;
    
    public CustomFileReader(File file){
        f = file;
    }
    
    
    public void read(){
        try {
            BufferedReader bf = new BufferedReader(new java.io.FileReader(f));
            
            String line = bf.readLine();
            
            while(line != null){
                
                System.out.println(line);
                
                String[] exploded = line.split(",");
                
                String processor = exploded[0];
                
                switch(processor){
                    
                    case "i3":
                        
                        
                    case "i5":
                        
                        
                    case "i7":
                        
                    
                    
                }
                
                
                String ram = exploded[1];
                
                switch(ram){
                    
                    
                }
                
                String placa = exploded[2];
                
                switch(placa){
                    
                    
                }
                
                line = bf.readLine();
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomFileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomFileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
