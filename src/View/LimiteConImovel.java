/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import Model.Imovel;
import Model.Util;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class LimiteConImovel extends JFrame implements ActionListener{

    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pCodigoImovel, pResultado, pButton;
    private JLabel labelCodigo;
    private JTextField textCodigo;
    private JTextArea resultadoImovel;
    private JButton buttonProcurar, buttonInativo;
    
    public LimiteConImovel(ControlePrincipal aThis) {
        ctrPrincipal = aThis;
        
        painelPrincipal = new JPanel(new GridLayout(3,1));
        painelSecundario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pCodigoImovel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pResultado = new JPanel(new GridLayout(1,1));
        
        labelCodigo = new JLabel("Código ");
        textCodigo = new JTextField(5);
        

        resultadoImovel = new JTextArea();
        resultadoImovel.setVisible(false);
        resultadoImovel.setEditable(false);
        
        buttonProcurar = new JButton("Procurar");
        buttonProcurar.addActionListener(this);
        
        buttonInativo = new JButton("Deixar Inativo");
        buttonInativo.setVisible(false);
        buttonInativo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //procura imovel e deixar inativo
                String strCodigo = textCodigo.getText();
                int codigo = Integer.parseInt(strCodigo);
                ctrPrincipal.desativarImovel(codigo);
                dispose();
            }
        });
        
        pCodigoImovel.add(labelCodigo);
        pCodigoImovel.add(textCodigo);
        pCodigoImovel.add(buttonProcurar);
        
        resultadoImovel.setWrapStyleWord(true);
        resultadoImovel.setLineWrap(true);
        painelSecundario.add(pResultado);
        
        pResultado.add(resultadoImovel);
        pButton.add(buttonInativo);
        
        painelSecundario.add(pCodigoImovel);
        
        painelPrincipal.add(painelSecundario);
        painelPrincipal.add(pResultado);
        painelPrincipal.add(pButton);
        
        super.add(painelPrincipal);
        
        super.setTitle("Inativa Imovel");
        super.setSize(600, 400);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String dados = "";
        String strCodigo = textCodigo.getText();
        int codigo = Integer.parseInt(strCodigo);
        Imovel imovel = ctrPrincipal.retornoImovelPorCodigo(codigo);
        if(imovel!=null){
            if(imovel.getEstado().equals(Util.ATIVO)){
                buttonProcurar.setEnabled(false);
                textCodigo.setEditable(false);
                dados = ctrPrincipal.retornaStringDadosImovel(imovel);
                resultadoImovel.setText(dados);
                resultadoImovel.setVisible(true);
                buttonInativo.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "O imóvel foi vendido ou já está inativo.", "Erro", 0);
                System.out.println("O imóvel foi vendido ou já está inativo.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "O imóvel não existe!", "Erro", 0);
            System.out.println("O imovel não existe!");
        }
    }
    
}
