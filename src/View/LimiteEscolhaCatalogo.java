/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import Model.Imovel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lucas
 */
public class LimiteEscolhaCatalogo extends JFrame implements ActionListener{
    private ControlePrincipal ctrPrincipal;
    private Imovel imovelEscolha;
    private JPanel painelPrincipal, painelSecundario, pImagem, pInformacoes, pButtons;
    private JLabel labelImagem;
    private JButton buttonVisita, buttonProposta;
    private JTextArea dadosImovel;
    private ImageIcon imagemImovel;

    static final int rowsValue = 1;
    static final int colsValue = 2;

    static final int width1 = 300;
    static final int width2 = 250;
    static final int width3 = 600;
    static final int height1 = 186;
    static final int height2 = 186;
    static final int height3 = 400;
    static final int hints = 100;
    
    public LimiteEscolhaCatalogo(ControlePrincipal ctrPrincipal, Imovel auxiliarEscolhido) {
        this.ctrPrincipal = ctrPrincipal;
        imovelEscolha = auxiliarEscolhido;
        
        //Criação dos paineis
        painelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSecundario = new JPanel(new GridLayout(rowsValue,colsValue));
        pImagem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pInformacoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //criacao da imagem
        imagemImovel = new ImageIcon(getClass().getResource(imovelEscolha.getArquivoFoto()));
        imagemImovel.setDescription(imovelEscolha.getDescricao());
        imagemImovel.setImage(imagemImovel.getImage().getScaledInstance(width1, height1, hints));
        
        labelImagem = new JLabel(imagemImovel);
        
        String dados = ctrPrincipal.retornaStringDadosImovel(imovelEscolha);
        dadosImovel = new JTextArea(dados);
        dadosImovel.setSize(width2, height2);
        dadosImovel.setEditable(false);
        dadosImovel.setLineWrap(true);
        dadosImovel.setWrapStyleWord(true);
        
        buttonVisita = new JButton("Agendar Visita");
        buttonProposta = new JButton("Fazer Proposta");
        
        buttonVisita.addActionListener(this);
        buttonProposta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                new LimiteProposta(ctrPrincipal, imovelEscolha);
                dispose();
            }
        });
        
        pImagem.add(labelImagem);
        pInformacoes.add(dadosImovel);
        pButtons.add(buttonVisita);
        pButtons.add(buttonProposta);
        
        painelSecundario.add(pImagem);
        painelSecundario.add(pInformacoes);
        
        
        painelPrincipal.add(painelSecundario);
        painelPrincipal.add(pButtons);
        
        super.add(painelPrincipal);
        
        String titulo = imovelEscolha.getCodigo()+" - "+ imovelEscolha.getDescricao();
        
        super.setTitle(titulo);
        super.setSize(width3, height3);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        new LimiteVisita(ctrPrincipal, imovelEscolha);
        dispose();
    }
    
}
