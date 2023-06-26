/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import Model.Imovel;
import Model.Proposta;
import Model.Util;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author lucas
 */
public class LimiteConPropostas extends JFrame implements ActionListener{
    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pEscolhaTipoImovel, pListaTipo;
    private JList listImoveis, listPropostas;
    private ArrayList<Imovel> listaImoveisComProposta;
    private ArrayList<String> listaImoveis = new ArrayList<String>();
    private ArrayList<String> listaPropostas = new ArrayList<String>();
    private String flagJanela = "", flagJanelaHist = "", flagJanela2="", flagJanela2Hist="";
    private Imovel imovelEscolhido = null;
    private Proposta propostaEscolhida = null;

    static final int indexInicial = 0;

    static final int width = 700;
    static final int height = 400;
    
    public LimiteConPropostas(ControlePrincipal controle){
        ctrPrincipal = controle;
        
        //Criação dos paineis
        painelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSecundario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pEscolhaTipoImovel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pListaTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        //Preenche a lista com todos os imoveis
        listImoveis = new JList();
        listPropostas = new JList();
        
        listPropostas.setVisible(false);
        
        listaImoveis.clear();
        listaPropostas.clear();
        listaImoveisComProposta = ctrPrincipal.retornoArrayListImovel("Proposta");
        
        listaImoveis.add("");
        for(Imovel movel: listaImoveisComProposta){
            boolean flag = false;
            if(movel.getEstado().equals(Util.ATIVO)){
                for(Proposta prop: movel.getListaPropostas()){
                    if(prop.getEstado().equals(Util.SUBMEDITA)){
                        flag = true;
                        break;
                    }
                }
                if(flag==true){
                    listaImoveis.add(movel.getCodigo()+" - "+movel.getDescricao());
                }
            }
        }
        listImoveis.setListData(listaImoveis.toArray());
        
        listImoveis.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int indexEscolhido = listImoveis.getSelectedIndex();
                if(indexEscolhido<=indexInicial){
                    System.out.println("Nenhum item selecionado");
                }else{
                    String escolhido = listImoveis.getSelectedValue().toString(); 
                    flagJanela = escolhido;
                    Imovel auxiliarEscolhido = null;
                    for(Imovel im: listaImoveisComProposta){
                        String str = im.getCodigo()+ " - "+ im.getDescricao();
                        if(str.equals(escolhido)){
                            auxiliarEscolhido=im;
                            imovelEscolhido = auxiliarEscolhido;
                            break;
                        }
                    }
                    if(!flagJanela.equals(flagJanelaHist)){
                        flagJanelaHist=flagJanela;
                        //settar valores na outra jlist e mostrar ela
        
                        listaPropostas.clear();
                        //seleciona o movel do arraylist moveis escolhido

                        listaPropostas.add("");
                        for(Proposta prop: auxiliarEscolhido.getListaPropostas()){
                            System.out.println("PRECO: "+ prop.getValor());
                            if(prop.getEstado().equals(Util.SUBMEDITA)){
                                
                                listaPropostas.add("Comprador: " + prop.getComprador().getNome()+" - R$" + prop.getValor());
                            }
                        }
                        listPropostas.setListData(listaPropostas.toArray());
                        listPropostas.setVisible(true);
                    }else{
                        System.out.println("Já foi aberto uma janela.");
                    }
                }
            }
            
        });
        
        listPropostas.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int indexEscolhido = listPropostas.getSelectedIndex();
                if(indexEscolhido<=indexInicial){
                    System.out.println("Nenhum item selecionado");
                }else{
                    String escolhido = listPropostas.getSelectedValue().toString();  
                    flagJanela2=escolhido;
                    String strAux="";
                    boolean flagProposta=false;
                    for(Proposta prop: imovelEscolhido.getListaPropostas()){
                        strAux = "Comprador: "+prop.getComprador().getNome()+" - R$" + prop.getValor();
                        if(strAux.equals(escolhido)){
                            propostaEscolhida = prop;
                            flagProposta = true;
                            break;
                        }
                    }
                    if(!flagJanela2.equals(flagJanela2Hist) && flagProposta){
                        flagJanela2Hist = flagJanela2;
                        new LimiteEscolhaProposta(ctrPrincipal, imovelEscolhido,propostaEscolhida);
                    }else{
                        System.out.println("Já foi aberto uma janela ou erro na proposta selecionada.");
                    }
                }
            } 
        });
        
        pEscolhaTipoImovel.add(listImoveis);
        pListaTipo.add(listPropostas);
        
        painelSecundario.add(pEscolhaTipoImovel);
        painelSecundario.add(pListaTipo);
        
        painelPrincipal.add(painelSecundario);
        
        super.add(painelPrincipal);
        
        super.setTitle("Propostas");
        super.setSize(width, height);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Sem utilizar");
    }
}
