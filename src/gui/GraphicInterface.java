/*
 * To change this license header, choose License Headers in Project Props.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ADReNA_API.Data.DataSet;
import java.io.File;
import java.util.Arrays;
import javax.swing.JOptionPane;
import util.Props;
import main.Rna;
import util.FileChooser;
import util.FileParser;

/**
 *
 * @author m95952
 */
public class GraphicInterface extends javax.swing.JFrame {

    private final String STANDARD_SELECT = "Selecionar";
    private Rna redeNeural;
    private File trainingFile = null;
    private boolean trained = false;
    
    private final FileChooser fileChooser;
    
    /**
     * Creates new form GraphicInterface
     */
    public GraphicInterface() {
        initComponents();
        config();
        addListeners();
        
        redeNeural = new Rna();
        redeNeural.configure();
        fileChooser = new FileChooser();
    }

    private void config() {
        this.setLocationRelativeTo(null);
    }

    private void addListeners() {
        botaoReconhecer.addActionListener((e) -> {
            try {
                validateInput();
                double[] entrada = getEntradaReconhecimento();
                
                textAreaOutput.append("Processador " + (String)comboProcessador.getSelectedItem() + ", " + (String)comboRam.getSelectedItem() + " RAM, " + (String)comboPlaca.getSelectedItem() + " vídeo\n");
                textAreaOutput.append(Arrays.toString(entrada) + " = "); //Usado para testar qual a  entrada escolhida quando traduzida para binário
                iniciarBackPropagation(entrada);
                
            } catch (Exception ex) {
                showDialog(ex.getMessage());
            }
        });
        
        
        botaoConfigurar.addActionListener((e) -> {
            new ConfigDialog(this, true).showDialog();
            redeNeural.configure();
            trained = false;
        });
        
        botaoBuscar.addActionListener((e) -> {
            getTrainingFile();
        });
        
        botaoTreinar.addActionListener((e) -> {
            try {
                treinar();
            } catch (Exception ex) {
                showDialog(ex.getMessage());
            }
        });
        
    }

    private void getTrainingFile(){
        trainingFile = fileChooser.findFile();
    
        if(trainingFile != null){
            campoArquivoTreinamento.setText(trainingFile.getName());
        }else{
            campoArquivoTreinamento.setText("");
        }
    }
    
    private void treinar() throws Exception{
        
        if(trainingFile == null){
            throw new Exception("Nenhum arquivo de treinamento escolhido!");
        }
        
        DataSet tSet = FileParser.readFile(trainingFile);
        //System.out.println("Input size: " + tSet.GetList().size());
        redeNeural.treinar(tSet);
        trained = true;
    }
    
    /**
     * Lê os dados e valida caso estiverem selecionados corretamente
     *
     * @throws Exception
     */
    private void validateInput() throws Exception {
        String selectedProcessor = (String) comboProcessador.getSelectedItem();
        String selectedRam = (String) comboRam.getSelectedItem();
        String selectedPlaca = (String) comboPlaca.getSelectedItem();
        
        if(!trained){
            throw new Exception("É necessário treinar a RNA antes de tentar reconhecer uma entrada!");
        }
        
        if (selectedProcessor.equals(STANDARD_SELECT)) {
            throw new Exception("Processador inválido!");
        }

        if (selectedRam.equals(STANDARD_SELECT)) {
            throw new Exception("RAM inválida!");
        }

        if (selectedPlaca.equals(STANDARD_SELECT)) {
            throw new Exception("Placa inválida!");
        }
    }

    /**
     * Retorna um array de bits representando as configurações selecionadas
     * @return 
     */
    private double[] getEntradaReconhecimento() {
        double[] entrada = new double[Props.TAMANHO_ENTRADA];
        
        for(int i = 0; i < Props.TAMANHO_ENTRADA; i++){
            entrada[i] = 0;
        }
        
        int processor = comboProcessador.getSelectedIndex() - 1;
        int ram = comboRam.getSelectedIndex() + Props.NUMERO_PROCESSADORES  - 1;
        int placa = comboPlaca.getSelectedIndex() + Props.NUMERO_PROCESSADORES + Props.NUMERO_RAM - 1 ;
        
        entrada[processor] = 1;
        entrada[ram] = 1;
        entrada[placa] = 1;
        
        return entrada;
    }

    /**
     * Chama os métodos necessários para utilizar o API Adrena
     */
    private void iniciarBackPropagation(double[] entrada) {
        try {
            //redeNeural.configure();
            
            double[] response = redeNeural.reconhecer(entrada);
            
            textAreaOutput.append(String.valueOf(response[0]) + "\n");
           
            if(response[0] > 0.5){
                textAreaOutput.append("Computador Gamer\n");
            }else{
                textAreaOutput.append("Computador Comum\n");
            }
            textAreaOutput.append("Error Rate: " + redeNeural.getError() + "\nIteration Number: " + redeNeural.getIterationNumber() + "\n\n");
            //textAreaOutput.append("1 - Computador comum, 2 - Computador Gamer : " + Arrays.toString(response) + "\n\n");
        } catch (Exception ex) {
            showDialog(ex.getMessage());
        }
    }

    /**
     * Mostra a mensagem passada através de um diálogo modal.
     *
     * @param message
     */
    private void showDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void limpaCampos(){
        textAreaOutput.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGroupProcessador = new javax.swing.ButtonGroup();
        btGroupRam = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        comboProcessador = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        comboRam = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        comboPlaca = new javax.swing.JComboBox<String>();
        botaoConfigurar = new javax.swing.JButton();
        botaoReconhecer = new javax.swing.JButton();
        campoArquivoTreinamento = new javax.swing.JTextField();
        botaoBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        botaoTreinar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Avaliação de Computadores");
        setResizable(false);

        jLabel1.setText("Processador");

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        comboProcessador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "Intel i3", "Intel i5", "Intel i7", "AMD Ryzen 3", "AMD Ryzen 5", "AMD Ryzen 7" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(comboProcessador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel2.setText("RAM");

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        comboRam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "4GB", "6GB", "8GB", "16GB", "32GB" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboRam, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(comboRam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jLabel3.setText("Placa Video");

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        comboPlaca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar", "0GB", "2GB", "3GB", "6GB", "8GB", "11GB", "16GB" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(comboPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        botaoConfigurar.setText("Configurar");

        botaoReconhecer.setText("Reconhecer");

        campoArquivoTreinamento.setEditable(false);

        botaoBuscar.setText("Buscar");

        jLabel4.setText("Arquivo de Treinamento");

        botaoTreinar.setText("Treinar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(botaoTreinar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(campoArquivoTreinamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botaoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(botaoConfigurar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botaoReconhecer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoConfigurar)
                    .addComponent(botaoReconhecer))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoArquivoTreinamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoTreinar)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        textAreaOutput.setColumns(20);
        textAreaOutput.setLineWrap(true);
        textAreaOutput.setRows(5);
        textAreaOutput.setWrapStyleWord(true);
        jScrollPane1.setViewportView(textAreaOutput);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphicInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphicInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoBuscar;
    private javax.swing.JButton botaoConfigurar;
    private javax.swing.JButton botaoReconhecer;
    private javax.swing.JButton botaoTreinar;
    private javax.swing.ButtonGroup btGroupProcessador;
    private javax.swing.ButtonGroup btGroupRam;
    private javax.swing.JTextField campoArquivoTreinamento;
    private javax.swing.JComboBox<String> comboPlaca;
    private javax.swing.JComboBox<String> comboProcessador;
    private javax.swing.JComboBox<String> comboRam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea textAreaOutput;
    // End of variables declaration//GEN-END:variables
}
