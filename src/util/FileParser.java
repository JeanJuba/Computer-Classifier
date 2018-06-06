/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import util.Props;

/**
 *
 * @author m98567
 */
public class FileParser {

    public static DataSet readFile(File file) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(file));
        DataSet trainingSet = new DataSet(Props.TAMANHO_ENTRADA, Props.TAMANHO_SAIDA);
        
        String line = br.readLine();
        while (line != null){
            double[] input = new double[Props.TAMANHO_ENTRADA];
            double[] targetOutput = new double[Props.TAMANHO_SAIDA];
            String attr[] = line.split(",");
            
            try{
                input[identifyProcessor(attr[0])] = 1;
                input[identifyRAM(attr[1])] = 1;
                input[identifyVideoCard(attr[2])] = 1;
            }catch(IndexOutOfBoundsException e){
                System.out.println("ERROR reading from file.");
            }
            
            if (attr[3].equals("1")){
                targetOutput[0] = 1;
            }else{
                targetOutput[0] = 0;
            }
            
            System.out.println(Arrays.toString(input) + " = " + Arrays.toString(targetOutput));
            trainingSet.Add(new DataSetObject(input, targetOutput));
            line = br.readLine();
        }
        
        return trainingSet;
    }
    
    private static int identifyProcessor(String component){
        String temp_arr[] = component.split("-");
        String component_brand = temp_arr[0];
        String component_model = temp_arr[1];
        
        if (component_brand.equals("Intel")){
            if (component_model.contains("3")){
                return 0;
            }else if (component_model.contains("5")){
                return 1;
            }else if (component_model.contains("7")){
                return 2;
            }
        }else if (component_brand.equals("AMD")){
            if (component_model.contains("3")){
                return 3;
            }else if (component_model.contains("5")){
                return 4;
            }else if (component_model.contains("7")){
                return 5;
            }
        }
        return -1;
    }
    
    private static int identifyRAM(String component){
        switch (Integer.parseInt(component)){
            case 4: return Props.NUMERO_PROCESSADORES;
            case 6: return Props.NUMERO_PROCESSADORES + 1;
            case 8: return Props.NUMERO_PROCESSADORES + 2;
            case 16: return Props.NUMERO_PROCESSADORES + 3;
            case 32: return Props.NUMERO_PROCESSADORES + 4;
            default: return -1;
        }
    }
    
    private static int identifyVideoCard(String component){
        switch (Integer.parseInt(component)){
            case 0: return Props.NUMERO_PROCESSADORES + Props.NUMERO_RAM;
            case 2: return Props.NUMERO_PROCESSADORES + Props.NUMERO_RAM + 1;
            case 3: return Props.NUMERO_PROCESSADORES + Props.NUMERO_RAM + 2;
            case 6: return Props.NUMERO_PROCESSADORES + Props.NUMERO_RAM + 3;
            case 8: return Props.NUMERO_PROCESSADORES + Props.NUMERO_RAM + 4;
            case 11: return Props.NUMERO_PROCESSADORES + Props.NUMERO_RAM + 5;
            default: return -1;
        }
    }
}
