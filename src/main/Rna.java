/*
 * To change this license header, choose License Headers in Project Props.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;
import util.Props;

/**
 *
 * @author m95952
 */
public class Rna {
    private Backpropagation neuralNetwork;
    private DataSet trainingSet;
    
    /*
    Cria as configurações iniciais
    */
    public void configure(){
        int[] layout = new int[Props.CAMADAS_INTERMEDIARIAS]; //lay = layout de rna (quantidade de camadas e neuronis por camadas)
        for (int a = 0; a < Props.CAMADAS_INTERMEDIARIAS ; a++){
            layout[a] = Props.NUMERO_NEURONIOS[a];
        }
        
        neuralNetwork = new Backpropagation(Props.TAMANHO_ENTRADA, Props.TAMANHO_SAIDA, layout);
        neuralNetwork.SetLearningRate(Props.TAXA_APRENDIZADO);
        neuralNetwork.SetErrorRate(Props.FATOR_MOMENTANEO);
        neuralNetwork.SetMaxIterationNumber(Props.NUMERO_ITERACOES);
    }
    
    /**
     * Os dados de inserção e qual saída é esperada
     * @param tSet
     * @throws Exception 
     */
    public void treinar(DataSet tSet) throws Exception{
        /*trainingSet = new DataSet(Props.TAMANHO_ENTRADA, Props.TAMANHO_SAIDA);
                                                       //Intel******  AMD**********  Ram********************  Placa---------------------------
        trainingSet.Add(new DataSetObject(new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0}, new double[]{0}));
        trainingSet.Add(new DataSetObject(new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, new double[]{0}));
        trainingSet.Add(new DataSetObject(new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0}, new double[]{0}));
        trainingSet.Add(new DataSetObject(new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0}, new double[]{1}));
        trainingSet.Add(new DataSetObject(new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0}, new double[]{1}));
        trainingSet.Add(new DataSetObject(new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0}, new double[]{1}));
        */
        neuralNetwork.Learn(tSet);
    }
    
    public double[] reconhecer(double[] entrada) throws Exception{
        //System.out.println("Error rate: " + neuralNetwork.GetErrorRate());
        //System.out.println("Error: " + neuralNetwork.Error);
        //System.out.println("Iteration Number: " + neuralNetwork.iterationNumber);
        //System.out.println("ETA: " + neuralNetwork.ETA);
        //System.out.println("Max Iteration Number: " + neuralNetwork.GetMaxIterationNumber() + "\n");
        
        return neuralNetwork.Recognize(entrada);
    }
    
    public double getError(){
        return neuralNetwork.GetErrorRate();
    }
    
    public int getIterationNumber(){
        return (int)neuralNetwork.GetIterationNumber();
    }
}
