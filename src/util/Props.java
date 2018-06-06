/*
 * To change this license header, choose License Headers in Project Props.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author m95952
 */
public class Props {
    public static  int CAMADAS_INTERMEDIARIAS = 1;
    public static  int[] NUMERO_NEURONIOS = {6}; //Número de neurônios em cada camada intermediária (Deve ser do mesmo comprimento do número de camadas intermediárias)
    public static  double TAXA_APRENDIZADO = 0.3;
    public static  double FATOR_MOMENTANEO = 0.005; //Taxa erro
    public static  int NUMERO_ITERACOES = 10000;    
    
    public static final int TAMANHO_ENTRADA = 18; //Neurônios de entrada
    public static final int TAMANHO_SAIDA   = 1; //Neurônios de saída
    
    //Necessários para pegar o index correto da seleção no array binário
    public static final int NUMERO_PROCESSADORES = 6;
    public static final int NUMERO_RAM = 5;
    public static final int NUMERO_PLACAS = 7;
}

/*
Processador : 6 
    intel i3, i5, i7
    amd   ryzen3, ryzen5, ryzen7

RAM: 5
    4gb, 6gb, 8gb, 16gb, 32gb

Placa Video: 7
    0,  2, 3, 6, 8, 11, 16

*/
