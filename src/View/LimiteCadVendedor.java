/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class LimiteCadVendedor extends JFrame implements ActionListener{
    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pNomeCpf, pEmailFone, pContatoBut;
    private JLabel labelNome, labelCpf, labelEmail, labelFone, labelContatoPreferencial, labelAuxiliar;
    private JTextField textNome, textCpf, textEmail, textFone, textContatoPreferencial;
    private JButton buttonCadastrar;

    static final int rowsValue1 = 0;
    static final int rowsValue2 = 1;
    static final int colsValue1 = 1;
    static final int colsValue2 = 2;

    static final int columns = 10;

    static final int width = 600;
    static final int height = 400;
    
    public LimiteCadVendedor(ControlePrincipal controle){
        ctrPrincipal = controle;
        
        //Criação dos paineis
        painelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSecundario = new JPanel(new GridLayout(rowsValue1,colsValue1));
        pNomeCpf = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pEmailFone = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pContatoBut = new JPanel(new GridLayout(rowsValue2,colsValue2));
        
        //Criação dos labels
        labelNome = new JLabel("Nome");
        labelCpf = new JLabel("   CPF");
        labelEmail = new JLabel("Email");
        labelFone = new JLabel("   Telefone");
        labelContatoPreferencial = new JLabel("Contato Pref.");
        labelAuxiliar = new JLabel("");
        
        //Criação dos TextFields
        textNome = new JTextField(columns);
        textCpf = new JTextField(columns);
        textEmail = new JTextField(columns);
        textFone = new JTextField(columns);
        textContatoPreferencial = new JTextField(columns);
        
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
        
        pContatoBut.add(labelContatoPreferencial);
        pContatoBut.add(textContatoPreferencial);
        pContatoBut.add(labelAuxiliar);
        pContatoBut.add(buttonCadastrar);
        
        painelSecundario.add(pNomeCpf);
        painelSecundario.add(pEmailFone);
        painelSecundario.add(pContatoBut);
        
        painelPrincipal.add(painelSecundario);
        
        super.add(painelPrincipal);
        
        super.setTitle("Cadastra Vendedor");
        super.setSize(width, height);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    //cadastro do corretor
    @Override
    public void actionPerformed(ActionEvent ae) {
        String strNome, strCpf, strEmail, strFone, strContatoBut;
        strNome = textNome.getText();
        strCpf = textCpf.getText();
        strEmail = textEmail.getText();
        strFone = textFone.getText();
        strContatoBut = textContatoPreferencial.getText();
        
        ctrPrincipal.cadVendedor(strCpf, strNome, strEmail, strFone, strContatoBut);
        dispose();
    }
    
}
