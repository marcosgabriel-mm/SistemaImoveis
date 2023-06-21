/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class LimiteCadCorretor extends JFrame implements ActionListener{
    
    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pNomeCpf, pEmailFone, pComissaoCreci, pButtonCadastrar;
    private JLabel labelNome, labelCpf, labelEmail, labelFone, labelComissao, labelCreci;
    private JTextField textNome, textCpf, textEmail, textFone, textComissao, textCreci;
    private JButton buttonCadastrar;

    static final int rowsValue1 = 0;
    static final int rowsValue2 = 1;
    static final int colsValue1 = 1;
    static final int colsValue2 = 2;

    static final int columns1 = 10;
    static final int columns2 = 2;

    static final int width = 600;
    static final int height = 400;
    
    public LimiteCadCorretor(ControlePrincipal controle){
        ctrPrincipal = controle;
        
        //Criação dos paineis
        painelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSecundario = new JPanel(new GridLayout(rowsValue1,colsValue1));
        pNomeCpf = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pEmailFone = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pComissaoCreci = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pButtonCadastrar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Criação dos labels
        labelNome = new JLabel("Nome");
        labelCpf = new JLabel("   CPF");
        labelEmail = new JLabel("Email");
        labelFone = new JLabel("   Telefone");
        labelComissao = new JLabel("Comissao");
        labelCreci = new JLabel("   Creci");
        
        //Criação dos TextFields
        textNome = new JTextField(columns1);
        textCpf = new JTextField(columns1);
        textEmail = new JTextField(columns1);
        textFone = new JTextField(columns1);
        textComissao = new JTextField(columns2);
        textCreci = new JTextField(columns1);
        
        //Criação do Botão
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(this);
        
        pNomeCpf.add(labelNome);
        pNomeCpf.add(textNome);
        pNomeCpf.add(labelCpf);
        pNomeCpf.add(textCpf);
        
        pEmailFone.add(labelEmail);
        pEmailFone.add(textEmail);
        pEmailFone.add(labelFone);
        pEmailFone.add(textFone);
        
        pComissaoCreci.add(labelComissao);
        pComissaoCreci.add(textComissao);
        pComissaoCreci.add(labelCreci);
        pComissaoCreci.add(textCreci);
        
        pButtonCadastrar.add(buttonCadastrar);
        
        painelSecundario.add(pNomeCpf);
        painelSecundario.add(pEmailFone);
        painelSecundario.add(pComissaoCreci);
        painelSecundario.add(pButtonCadastrar);
        
        painelPrincipal.add(painelSecundario);
        
        super.add(painelPrincipal);
        
        super.setTitle("Cadastra Corretor");
        super.setSize(width, height);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    //cadastro do corretor
    @Override
    public void actionPerformed(ActionEvent ae) {
        String strNome, strCpf, strEmail, strFone, strComissao, strCreci;
        strNome = textNome.getText();
        strCpf = textCpf.getText();
        strEmail = textEmail.getText();
        strFone = textFone.getText();
        strComissao = textComissao.getText();
        strCreci = textCreci.getText();
        
        double doubleComissao = Double.parseDouble(strComissao);
        ctrPrincipal.cadCorretor(strCpf, strNome, strEmail, strFone, strCreci, doubleComissao);
        dispose();
    }
    
}
