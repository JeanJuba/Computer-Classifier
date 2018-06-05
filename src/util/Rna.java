/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;

/**
 *
 * @author m95952
 */
public class Rna {
    private Backpropagation neuralNetwork;
    private DataSet trainingSet;
    //private double[] answer;
    
    public void configure(){
        int[] layout = new int[Properties.CAMADAS_INTERMEDIARIAS]; //lay = layout de rna (quantidade de camadas e neuronis por camadas)
        for (int a = 0; a < Properties.CAMADAS_INTERMEDIARIAS ; a++){
            layout[a] = Properties.NUMERO_NEURONIOS[a];
        }
        
        neuralNetwork = new Backpropagation(Properties.TAMANHO_ENTRADA, Properties.TAMANHO_SAIDA, layout);
        neuralNetwork.SetLearningRate(Properties.TAXA_APRENDIZADO);
        neuralNetwork.SetErrorRate(Properties.FATOR_MOMENTANEO);
        neuralNetwork.SetMaxIterationNumber(Properties.NUMERO_ITERACOES);
    }
    
    public void treinar() throws Exception{
        trainingSet = new DataSet(Properties.TAMANHO_ENTRADA, Properties.TAMANHO_SAIDA);
        trainingSet.Add(new DataSetObject(new double[]{}, new double[]{}));
        trainingSet.Add(new DataSetObject(new double[]{}, new double[]{}));
        trainingSet.Add(new DataSetObject(new double[]{}, new double[]{}));
    }
    
    public double[] reconhecer(double[] entrada) throws Exception{
        return neuralNetwork.Recognize(entrada);
    }
    
}
