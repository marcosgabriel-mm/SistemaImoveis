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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author lucas
 */
public class LimiteConCatalogo extends JFrame implements ActionListener{
    private ControlePrincipal ctrPrincipal;
    private JPanel painelPrincipal, painelSecundario, pEscolhaTipoImovel, pListaTipo;
    private JComboBox comboBoxTiposImoveis;
    private JList listaDoTipoEscolhido;
    private ArrayList<Imovel> imoveisEscolha;
    private ArrayList<String> listaImoveis = new ArrayList<String>();
    private boolean flagJanela = false;
    
    public LimiteConCatalogo(ControlePrincipal controle){
        ctrPrincipal = controle;
        
        //Criação dos paineis
        painelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSecundario = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pEscolhaTipoImovel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pListaTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        comboBoxTiposImoveis = new JComboBox();
        comboBoxTiposImoveis.setModel(new DefaultComboBoxModel(ctrPrincipal.retornoArrayListTipos().toArray()));
        comboBoxTiposImoveis.addActionListener(this);
        
        //Preenche a lista com todos os imoveis
        listaDoTipoEscolhido = new JList();
        listaImoveis.clear();
        imoveisEscolha = ctrPrincipal.retornoArrayListImovel("Todos");
        listaImoveis.add("");
        for(Imovel movel: imoveisEscolha){
            if(movel.getEstado().equals(Util.ATIVO)){
                listaImoveis.add(movel.getCodigo()+" - "+movel.getDescricao());
            }
        }
        listaDoTipoEscolhido.setListData(listaImoveis.toArray());
        
        listaDoTipoEscolhido.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                int indexEscolhido = listaDoTipoEscolhido.getSelectedIndex();
                if(indexEscolhido<=0){
                    System.out.println("Nenhum item selecionado");
                }else{
                    String escolhido = listaDoTipoEscolhido.getSelectedValue().toString();  
                    Imovel auxiliarEscolhido = null;
                    for(Imovel im: imoveisEscolha){
                        String str = im.getCodigo()+ " - "+ im.getDescricao();
                        if(str.equals(escolhido)){
                            auxiliarEscolhido=im;
                            break;
                        }
                    }
                    if(flagJanela==false){
                        flagJanela=true;
                        new LimiteEscolhaCatalogo(ctrPrincipal, auxiliarEscolhido);
                    }else{
                        System.out.println("Já foi aberto uma janela.");
                    }
                }
            }
            
        });
        
        pEscolhaTipoImovel.add(comboBoxTiposImoveis);
        pListaTipo.add(listaDoTipoEscolhido);
        
        painelSecundario.add(pEscolhaTipoImovel);
        painelSecundario.add(pListaTipo);
        
        painelPrincipal.add(painelSecundario);
        
        super.add(painelPrincipal);
        
        super.setTitle("Catálogo");
        super.setSize(700, 400);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    //comboBox
    @Override
    public void actionPerformed(ActionEvent ae) {
        flagJanela = false;
        String opcaoTipo = comboBoxTiposImoveis.getSelectedItem().toString();
        listaImoveis.clear();
        System.out.println(opcaoTipo);
        imoveisEscolha = ctrPrincipal.retornoArrayListImovel(opcaoTipo);
        listaImoveis.add("");
        for(Imovel movel: imoveisEscolha){
            if(movel.getEstado().equals(Util.ATIVO)){
                listaImoveis.add(movel.getCodigo()+" - "+movel.getDescricao());
            }
        }
        listaDoTipoEscolhido.setListData(listaImoveis.toArray());
    }
    
}
