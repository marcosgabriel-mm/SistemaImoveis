/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import Model.Comprador;
import Model.Corretor;
import Model.Imovel;
import Model.Proposta;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class LimiteProposta extends JFrame implements ActionListener{
    
    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pComprador, pCorretor, pData, pHorario, pButton, pValor;
    private JLabel labelComprador, labelCorretor, labelData, labelHora, labelValor;
    private JTextField textComprador, textCorretor, textDia, textMes, textAno, textHora, textMinutos, textValor;
    private JButton buttonCadastrar;
    private Imovel imovelEscolhido;
    
    static final int rowsValue1 = 2;
    static final int rowsValue2 = 5;
    static final int colsValue1 = 1;
    static final int colsValue2 = 1;

    static final int columns1 = 10;
    static final int columns2 = 2;
    static final int columns3 = 4;
    static final int columns4 = 8;

    static final int width = 600;
    static final int height = 400;
    
    public LimiteProposta(ControlePrincipal controle, Imovel imovel) {
        ctrPrincipal = controle;
        imovelEscolhido = imovel;
        
        //criação dos paineis
        painelPrincipal = new JPanel(new GridLayout(rowsValue1,colsValue1));
        painelSecundario = new JPanel(new GridLayout(rowsValue2,colsValue2));
        pComprador = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pCorretor = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pData = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pHorario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pValor = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Criação dos labels
        labelComprador = new JLabel("CPF-Comprador ");
        labelCorretor = new JLabel("CPF-Corretor ");
        labelData = new JLabel("Data ");
        labelHora = new JLabel("Hora ");
        labelValor = new JLabel("Valor ");
        
        //Criação dos textFields
        textComprador = new JTextField(columns1);
        textCorretor = new JTextField(columns1);
        textDia = new JTextField(columns2);
        textMes = new JTextField(columns2);
        textAno = new JTextField(columns3);
        textHora = new JTextField(columns2);
        textMinutos = new JTextField(columns2);
        textValor = new JTextField(columns4);
        
        pCorretor.add(labelCorretor);
        pCorretor.add(textCorretor);
        
        pComprador.add(labelComprador);
        pComprador.add(textComprador);
        
        pData.add(labelData);
        pData.add(textDia);
        pData.add(textMes);
        pData.add(textAno);        
        
        pHorario.add(labelHora);
        pHorario.add(textHora);
        pHorario.add(textMinutos);
        
        pValor.add(labelValor);
        pValor.add(textValor);
        
        painelSecundario.add(pCorretor);
        painelSecundario.add(pComprador);
        painelSecundario.add(pData);
        painelSecundario.add(pHorario);
        painelSecundario.add(pValor);
        
        buttonCadastrar = new JButton("Cadastrar Proposta");
        buttonCadastrar.addActionListener(this);
        pButton.add(buttonCadastrar);
        
        painelPrincipal.add(painelSecundario);
        
        painelPrincipal.add(pButton);
        
        super.add(painelPrincipal);
        
        super.setTitle("Realizar Proposta");
        super.setSize(width, height);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String strCorretor, strComprador;
        String strDia, strMes, strAno, strHora, strMin;
        String strValor;
        strCorretor = textCorretor.getText();
        strComprador = textComprador.getText();
        strDia = textDia.getText();
        strMes = textMes.getText();
        strAno = textAno.getText();
        strHora = textHora.getText();
        strMin = textMinutos.getText();
        strValor = textValor.getText();
        
        int ano, mes, dia, hora, minutos;
        ano = Integer.parseInt(strAno);
        mes = Integer.parseInt(strMes);
        dia = Integer.parseInt(strDia);
        hora = Integer.parseInt(strHora);
        minutos = Integer.parseInt(strMin);
        
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(GregorianCalendar.YEAR, ano);
        calendar.set(GregorianCalendar.MONTH, mes);
        calendar.set(GregorianCalendar.DAY_OF_MONTH, dia);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, hora);
        calendar.set(GregorianCalendar.MINUTE, minutos);
        double valor = Double.parseDouble(strValor);
        
        System.out.println(calendar.getTime().getDay()+"/"+calendar.getTime().getMonth()+"/"+calendar.getTime().getYear());
        
        Comprador comprador = ctrPrincipal.retornaCompradorPorCpf(strComprador);
        Corretor corretor = ctrPrincipal.retornaCorretorPorCpf(strCorretor);
        
        Proposta propostaImovel = new Proposta(calendar,comprador, corretor, valor);
        
        boolean realiza = ctrPrincipal.registraPropostaPorCodigo(imovelEscolhido.getCodigo(), propostaImovel);
        if(realiza){
            System.out.println("Proposta realizada!");
            dispose();
        }else{
            System.out.println("Problema em fazer a proposta!");
        }
    }
    
}
