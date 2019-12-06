/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import Model.Comprador;
import Model.Imovel;
import Model.Proposta;
import Model.Util;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author lucas
 */
public class LimiteEscolhaProposta extends JFrame implements ActionListener{

    private ControlePrincipal ctrPrincipal;
    private Proposta propostaEscolhida = null;
    private Imovel imovelEscolhido = null;
    
    private JPanel painelPrincipal, painelSecundario, pImagem, pInformacoes, pButtons;
    private JLabel labelImagem;
    private JButton buttonAceita, buttonRejeita;
    private JTextArea dadosImovel;
    private ImageIcon imagemImovel;
    
    
    public LimiteEscolhaProposta(ControlePrincipal controle, Imovel imovelEscolhido, Proposta propostaEscolhida) {
        ctrPrincipal = controle;
        this.propostaEscolhida = propostaEscolhida;
        this.imovelEscolhido = imovelEscolhido;
         
        //Criação dos paineis
        painelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelSecundario = new JPanel(new GridLayout(1,2));
        pImagem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pInformacoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //criacao da imagem
        imagemImovel = new ImageIcon(getClass().getResource(imovelEscolhido.getArquivoFoto()));
        imagemImovel.setDescription(imovelEscolhido.getDescricao());
        imagemImovel.setImage(imagemImovel.getImage().getScaledInstance(300, 186, 100));
        
        labelImagem = new JLabel(imagemImovel);
        
        Comprador comprador = propostaEscolhida.getComprador();
        String dados = ctrPrincipal.retornaStringDadosImovel(imovelEscolhido);
        dados+="\nPROPOSTA: \nNome: "+comprador.getNome();
        dados+=" CPF"+comprador.getCpf()+"\nEmail: "+comprador.getEmail()+"\nTelefone: ";
        dados+=comprador.getFone()+"\nContato Preferencial: "+comprador.getContatoPref()+"\n";
        dados+="Valor da proposta: R$"+propostaEscolhida.getValor()+"\n";
        
        dadosImovel = new JTextArea(dados);
        dadosImovel.setSize(250, 186);
        dadosImovel.setEditable(false);
        dadosImovel.setLineWrap(true);
        dadosImovel.setWrapStyleWord(true);
        
        buttonAceita = new JButton("Aceita Proposta");
        buttonRejeita = new JButton("Rejeita Proposta");
        
        buttonAceita.addActionListener(this);
        buttonRejeita.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ctrPrincipal.procuraAlteraEstadoPropostaMain(imovelEscolhido, propostaEscolhida, Util.REJEITADA);
                dispose();
            }
        });
        
        pImagem.add(labelImagem);
        pInformacoes.add(dadosImovel);
        pButtons.add(buttonAceita);
        pButtons.add(buttonRejeita);
        
        painelSecundario.add(pImagem);
        painelSecundario.add(pInformacoes);
        
        
        painelPrincipal.add(painelSecundario);
        painelPrincipal.add(pButtons);
        
        super.add(painelPrincipal);
        
        
        String titulo = propostaEscolhida.getComprador().getNome()+" - R$"+ propostaEscolhida.getValor();
        
        super.setTitle(titulo);
        super.setSize(600, 400);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ctrPrincipal.procuraAlteraEstadoPropostaMain(imovelEscolhido, propostaEscolhida, Util.ACEITA);
        dispose();
    }
    
}
