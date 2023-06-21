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
public class LimiteCadImovel extends JFrame implements ActionListener{

    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pCodigoTipo,pDescricaoEndereco, pFotoPreco, pComissaoVendedor, pButtonCadastrar;
    private JLabel labelCodigo, labelTipo, labelDescricao, labelEndereco, labelFoto, labelPreco, labelComissao, labelVendedor;
    private JTextField textCodigo, textTipo, textDescricao, textEndereco, textFoto, textPreco, textComissao, textVendedor;
    private JButton buttonCadastrar;

    static final int rowsValue1 = 0;
    static final int rowsValue2 = 1;
    static final int colsValue1 = 1;
    static final int colsValue2 = 2;

    static final int columns1 = 10;
    static final int columns2 = 2;
    static final int columns3 = 5;

    static final int width = 600;
    static final int height = 400;
    
    public LimiteCadImovel(ControlePrincipal controle){
        ctrPrincipal = controle;
        
        //Criação dos paineis
        painelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelSecundario = new JPanel(new GridLayout(rowsValue1,colsValue1));
        pCodigoTipo = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pDescricaoEndereco = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pFotoPreco = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pComissaoVendedor = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pButtonCadastrar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Criação dos labels
        labelCodigo = new JLabel("Código");
        labelTipo = new JLabel("   Tipo");
        labelDescricao = new JLabel("Descrição");
        labelEndereco = new JLabel("   Endereço");
        labelFoto = new JLabel("Foto(opcional)");
        labelPreco = new JLabel("   Preço");
        labelComissao = new JLabel("Comissão");
        labelVendedor = new JLabel("   Cpf-Vendedor");
        
        //Criação dos TextFields
        textCodigo = new JTextField(columns1);
        textTipo = new JTextField(columns1);
        textDescricao = new JTextField(columns1);
        textEndereco = new JTextField(columns1);
        textFoto = new JTextField(columns2);
        textPreco = new JTextField(columns3);
        textComissao = new JTextField(columns3);
        textVendedor = new JTextField(columns1);
        
        //Criação do Botão
        buttonCadastrar = new JButton("Cadastrar");
        buttonCadastrar.addActionListener(this);
        
        pCodigoTipo.add(labelCodigo);
        pCodigoTipo.add(textCodigo);
        pCodigoTipo.add(labelTipo);
        pCodigoTipo.add(textTipo);
        
        pDescricaoEndereco.add(labelDescricao);
        pDescricaoEndereco.add(textDescricao);
        pDescricaoEndereco.add(labelEndereco);
        pDescricaoEndereco.add(textEndereco);
        
        pFotoPreco.add(labelFoto);
        pFotoPreco.add(textFoto);
        pFotoPreco.add(labelPreco);
        pFotoPreco.add(textPreco);
        
        pComissaoVendedor.add(labelComissao);
        pComissaoVendedor.add(textComissao);
        pComissaoVendedor.add(labelVendedor);
        pComissaoVendedor.add(textVendedor);
        
        pButtonCadastrar.add(buttonCadastrar);
        
        painelSecundario.add(pCodigoTipo);
        painelSecundario.add(pDescricaoEndereco);
        painelSecundario.add(pFotoPreco);
        painelSecundario.add(pComissaoVendedor);
        painelSecundario.add(pButtonCadastrar);
        
        painelPrincipal.add(painelSecundario);
        
        super.add(painelPrincipal);
        
        super.setTitle("Cadastra Imovel");
        super.setSize(width, height);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    //cadastro do corretor
    @Override
    public void actionPerformed(ActionEvent ae) {
        String strCodigo, strTipo, strDescricao, strEndereco, strFoto, strPreco, strComissao, strCpfVendedor;
        strCodigo = textCodigo.getText();
        strTipo = textTipo.getText();
        strDescricao = textDescricao.getText();
        strEndereco = textEndereco.getText();
        strFoto = textFoto.getText();
        strPreco = textPreco.getText();
        strComissao = textComissao.getText();
        strCpfVendedor = textVendedor.getText();
        
        int intCodigo = Integer.parseInt(strCodigo);
        
        if(strFoto.equals(""))
            strFoto="..\\images\\defaultImage.jpeg";
        else{
            strFoto="..\\images\\"+strFoto;
        }
        
        double doublePreco = Double.parseDouble(strPreco);
        double doubleComissao = Double.parseDouble(strComissao);
        ctrPrincipal.cadImovel(intCodigo, strTipo, strDescricao, strEndereco, strFoto, doublePreco, doubleComissao, strCpfVendedor);
        dispose();
    }
    
}
