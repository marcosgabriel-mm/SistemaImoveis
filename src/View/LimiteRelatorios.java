/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import Model.Corretor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class LimiteRelatorios extends JFrame implements ActionListener{
    
    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pDataInicio, pDataFinal, pButton;
    private JButton buttonProcura;
    private JMenuBar barraMenu;
    private JMenu menuFatura, menuImoveis, menuEventos;
    private JMenuItem menuItemValorTotal, menuItemValorTotalCorretor, menuItemImoveisVendidos, menuItemImoveisPorVendedor, menuItemEventosPorImovelPorPeriodo, menuItemVisitasCorretor;
    private JLabel labelDataInicio, labelDataFinal, labelHoraInicio, labelHoraFinal, labelPessoaCpf;
    private JTextField textDiaInicio, textMesInicio, textAnoInicio, textHoraInicio, textMinutosInicio;
    private JTextField textDiaFinal, textMesFinal, textAnoFinal, textHoraFinal, textMinutosFinal, textPessoaCpf;
    private JTextArea resultadosTextArea;
    private JScrollPane spScrollResultados;
    private int janela=0;

    public LimiteRelatorios(ControlePrincipal controle) {
        ctrPrincipal = controle;
        
        menuFatura = new JMenu("Faturas");
        menuImoveis = new JMenu("Imóveis");
        menuEventos = new JMenu("Eventos");
        
        menuItemValorTotal = new JMenuItem("Valor Total Faturado");
        menuItemValorTotal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                janela = 0;
                resultadosTextArea.setVisible(false);
                limpaTextFieldsCalendar();
                pButton.setVisible(false);
                pDataInicio.setVisible(true);
                pDataFinal.setVisible(true);
                labelPessoaCpf.setVisible(false);
                textPessoaCpf.setVisible(false);
                pButton.setVisible(true);
                
            }
        });
        
        menuItemValorTotalCorretor = new JMenuItem("Valor Total por Corretor");
        menuItemValorTotalCorretor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                janela=1;
                resultadosTextArea.setVisible(false);
                textPessoaCpf.setText("");
                limpaTextFieldsCalendar();
                pDataInicio.setVisible(true);
                pDataFinal.setVisible(true);
                pButton.setVisible(false);
                labelPessoaCpf.setText("Cpf do Corretor");
                labelPessoaCpf.setVisible(true);
                textPessoaCpf.setVisible(true);
                pButton.setVisible(true);
            }
            
        });
        
        menuItemImoveisVendidos = new JMenuItem("Imoveis Vendidos");
        menuItemImoveisVendidos.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                janela = 2;
                resultadosTextArea.setVisible(false);
                limpaTextFieldsCalendar();
                pButton.setVisible(false);
                pDataInicio.setVisible(true);
                pDataFinal.setVisible(true);
                labelPessoaCpf.setVisible(false);
                textPessoaCpf.setVisible(false);
                pButton.setVisible(true);
            }
            
        });
        
        menuItemImoveisPorVendedor = new JMenuItem("Imoveis por Vendedor");
        menuItemImoveisPorVendedor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                janela=3;
                resultadosTextArea.setVisible(false);
                textPessoaCpf.setText("");
                limpaTextFieldsCalendar();
                pDataInicio.setVisible(false);
                pDataFinal.setVisible(false);
                pButton.setVisible(false);
                labelPessoaCpf.setText("Cpf do Vendedor");
                labelPessoaCpf.setVisible(true);
                textPessoaCpf.setVisible(true);
                pButton.setVisible(true);
            }
            
        });
        
        menuItemEventosPorImovelPorPeriodo = new JMenuItem("Eventos Por Imovel Por Período");
        menuItemEventosPorImovelPorPeriodo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                janela = 4;
                resultadosTextArea.setVisible(false);
                textPessoaCpf.setText("");
                limpaTextFieldsCalendar();
                pDataInicio.setVisible(true);
                pDataFinal.setVisible(true);
                pButton.setVisible(false);
                labelPessoaCpf.setText("Código do Imóvel");
                labelPessoaCpf.setVisible(true);
                textPessoaCpf.setVisible(true);
                pButton.setVisible(true);
            }
        });
        
        menuItemVisitasCorretor = new JMenuItem("Visitas por Corretor por período");
        menuItemVisitasCorretor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                janela = 5;
                resultadosTextArea.setVisible(false);
                limpaTextFieldsCalendar();
                pButton.setVisible(false);
                pDataInicio.setVisible(true);
                pDataFinal.setVisible(true);
                labelPessoaCpf.setText("Código do Corretor");
                labelPessoaCpf.setVisible(true);
                textPessoaCpf.setVisible(true);
                pButton.setVisible(true);
            }
        });
        
        menuFatura.add(menuItemValorTotal);
        menuFatura.add(menuItemValorTotalCorretor);
        menuImoveis.add(menuItemImoveisVendidos);
        menuImoveis.add(menuItemImoveisPorVendedor);
        menuEventos.add(menuItemEventosPorImovelPorPeriodo);
        menuEventos.add(menuItemVisitasCorretor);
        menuFatura.add(new JSeparator(JSeparator.HORIZONTAL));
        menuImoveis.add(new JSeparator(JSeparator.HORIZONTAL));
        menuEventos.add(new JSeparator(JSeparator.HORIZONTAL));
        
        //Criar JMenuBar
        barraMenu = new JMenuBar();
        
        barraMenu.add(menuFatura);
        barraMenu.add(menuImoveis);
        barraMenu.add(menuEventos);

        //Criação dos painels
        painelPrincipal = new JPanel(new GridLayout(2,1));
        painelSecundario = new JPanel(new GridLayout(3,1));
        pDataInicio = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pDataFinal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Criação das labels
        labelDataInicio = new JLabel("Data-Inicial");
        labelHoraInicio = new JLabel("Hora-Inicial");
        labelDataFinal = new JLabel("Data-Fim");
        labelHoraFinal = new JLabel("Hora-Fim");
        
        labelPessoaCpf = new JLabel("Cpf do Corretor");
        
        //Criação dos textFields
        textDiaInicio = new JTextField(2);
        textMesInicio = new JTextField(2);
        textAnoInicio = new JTextField(4);
        textHoraInicio = new JTextField(2);
        textMinutosInicio = new JTextField(2);
        
        textDiaFinal = new JTextField(2);
        textMesFinal = new JTextField(2);
        textAnoFinal = new JTextField(4);
        textHoraFinal = new JTextField(2);
        textMinutosFinal = new JTextField(2);
        
        textPessoaCpf = new JTextField(8);
        
        //Criacao do textArea
        resultadosTextArea = new JTextArea();
        resultadosTextArea.setWrapStyleWord(true);
        resultadosTextArea.setLineWrap(true);
        
        spScrollResultados = new JScrollPane(resultadosTextArea);
        
        //Criacao do jbutton
        buttonProcura = new JButton("Gerar Relatório");
        buttonProcura.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Calendar cInicio = null, cFinal = null;
                if(janela!=3){
                    cInicio = GregorianCalendar.getInstance();
                    cFinal = GregorianCalendar.getInstance();
                    int ano = Integer.parseInt(textAnoInicio.getText());
                    int mes = Integer.parseInt(textMesInicio.getText());
                    int dia = Integer.parseInt(textDiaInicio.getText());
                    int hora = Integer.parseInt(textHoraInicio.getText());
                    int minutos = Integer.parseInt(textMinutosInicio.getText());
                    cInicio.set(GregorianCalendar.YEAR, ano);
                    cInicio.set(GregorianCalendar.MONTH, mes);
                    cInicio.set(GregorianCalendar.DAY_OF_MONTH, dia);
                    cInicio.set(GregorianCalendar.HOUR_OF_DAY, hora);
                    cInicio.set(GregorianCalendar.MINUTE, minutos);

                    ano = Integer.parseInt(textAnoFinal.getText());
                    mes = Integer.parseInt(textMesFinal.getText());
                    dia = Integer.parseInt(textDiaFinal.getText());
                    hora = Integer.parseInt(textHoraFinal.getText());
                    minutos = Integer.parseInt(textMinutosFinal.getText());
                    cFinal.set(GregorianCalendar.YEAR, ano);
                    cFinal.set(GregorianCalendar.MONTH, mes);
                    cFinal.set(GregorianCalendar.DAY_OF_MONTH, dia);
                    cFinal.set(GregorianCalendar.HOUR_OF_DAY, hora);
                    cFinal.set(GregorianCalendar.MINUTE, minutos);
                }
                String cpfCorretor = textPessoaCpf.getText();
                
                String resultados = "";
                if(janela==0){
                    double dinheiroImobiliaria = ctrPrincipal.retornoDinheiroImobiliaria(cInicio, cFinal, false);
                    double dinheiroImobiliariaSemCorretor = ctrPrincipal.retornoDinheiroImobiliaria(cInicio, cFinal, true);
                
                
                    double dinheiroCorretores = dinheiroImobiliaria-dinheiroImobiliariaSemCorretor;
                
                    resultados = "Fatura total de Corretores: R$"+dinheiroCorretores+"\n";
                    resultados+="Lucro total da Imobiliária: R$"+dinheiroImobiliariaSemCorretor+"\n";
                }else if(janela==1){
                    double dinheiroCorretor = ctrPrincipal.retornoDinheiroCorretor(cInicio, cFinal, cpfCorretor);
                    Corretor c = ctrPrincipal.retornaCorretorPorCpf(cpfCorretor);
                    resultados = "Fatura total do Corretor "+c.getNome()+"("+cpfCorretor+"):";
                    resultados+= " R$"+dinheiroCorretor+"\n";
                }else if(janela==2){
                    String vendas = ctrPrincipal.retornoImoveisVendidosPorTempo(cInicio, cFinal);
                    resultados = vendas;
                }else if(janela==3){
                    String cpfVendedor = "";
                    cpfVendedor = textPessoaCpf.getText();
                    String vendedorIm = ctrPrincipal.retornoImoveisPorVendedor(cpfVendedor);
                    resultados = vendedorIm;
                }else if(janela==4){
                    int codigoImovel = Integer.parseInt(cpfCorretor);
                    String eventosIm = "";
                    eventosIm = ctrPrincipal.retornoEventosImovelPorPeriodo(cInicio, cFinal, codigoImovel);
                    resultados = eventosIm;
                }else if(janela==5){
                    String visitasCorretor = "";
                    visitasCorretor = ctrPrincipal.retornaVisitasCorretorString(cInicio, cFinal, cpfCorretor);
                    resultados = visitasCorretor;
                }
                
                resultadosTextArea.setText(resultados);
                JOptionPane.showMessageDialog(null, resultados, "Resultados", 1);
                resultadosTextArea.setVisible(true);
               
            }
        });
        
        //Adicionados os componentes nos paineis
        pDataInicio.add(labelDataInicio);
        pDataInicio.add(textDiaInicio);
        pDataInicio.add(textMesInicio);
        pDataInicio.add(textAnoInicio);
        
        pDataInicio.add(labelHoraInicio);
        pDataInicio.add(textHoraInicio);
        pDataInicio.add(textMinutosInicio);
        
        pDataFinal.add(labelDataFinal);
        pDataFinal.add(textDiaFinal);
        pDataFinal.add(textMesFinal);
        pDataFinal.add(textAnoFinal);
        
        pDataFinal.add(labelHoraFinal);
        pDataFinal.add(textHoraFinal);
        pDataFinal.add(textMinutosFinal);
        
        pButton.add(labelPessoaCpf);
        pButton.add(textPessoaCpf);
        pButton.add(buttonProcura);
        
        //resultadosTextArea.setBounds(5, 5, 5, 5);
        //resultadosTextArea.setEditable(false);
        
        painelSecundario.add(pDataInicio);
        painelSecundario.add(pDataFinal);
        painelSecundario.add(resultadosTextArea);
        
        //painelSecundario.add(spScrollResultados);
        
        painelPrincipal.add(painelSecundario);
        painelPrincipal.add(pButton);
        
        //começo
        pDataInicio.setVisible(false);
        pDataFinal.setVisible(false);
        pButton.setVisible(false);
        resultadosTextArea.setVisible(false);
        
        super.add(painelPrincipal);
        
        super.setTitle("Relatórios da Imobiliária");
        super.setJMenuBar(barraMenu);
        super.setSize(600, 400);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    public void limpaTextFieldsCalendar(){
        textDiaInicio.setText("");
        textMesInicio.setText("");
        textAnoInicio.setText("");
        textHoraInicio.setText("");
        textMinutosInicio.setText("");
        textDiaFinal.setText("");
        textMesFinal.setText("");
        textAnoFinal.setText("");
        textHoraFinal.setText("");
        textMinutosFinal.setText("");
        textPessoaCpf.setText("");
        resultadosTextArea.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("sem uso por enquanto");
    }
    
}
