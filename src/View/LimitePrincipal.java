/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controler.ControlePrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author lucas
 */
public class LimitePrincipal extends JFrame implements WindowListener{
    private ControlePrincipal ctrPrincipal;
    
    private JMenuBar barraMenu;
    private JMenu menuCad, menuCon, menuRel, menuImportar;
    private JMenuItem cadastraCorretor, cadastraComprador, cadastraVendedor, cadastraImovel;
    private JMenuItem consultaCatalogo, consultaPropostas, consultaInativaImovel;
    private JMenuItem relatoriosItem, importarItem;
    private String caminhoSalvar = "";

    static final int width = 600;
    static final int height = 400;

    public LimitePrincipal(ControlePrincipal controle){
        ctrPrincipal = controle;
        
        cadastraCorretor = new JMenuItem("Cadastrar Corretor");
        cadastraCorretor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrPrincipal.interfaceCadCorretor();
            }
        });
        
        cadastraVendedor = new JMenuItem("Cadastrar Vendedor");
        cadastraVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrPrincipal.interfaceCadVendedor();
            }
        });
        
        cadastraImovel = new JMenuItem("Cadastrar Imovel");
        cadastraImovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrPrincipal.interfaceCadImovel();
            }
        });
        
        cadastraComprador = new JMenuItem("Cadastrar Comprador");
        cadastraComprador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrPrincipal.interfaceCadComprador();
            }
        });
        
        consultaCatalogo = new JMenuItem("Catálogo");
        consultaCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrPrincipal.interfaceConCatalogo();
            }
        });
        
        consultaPropostas = new JMenuItem("Propostas");
        consultaPropostas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrPrincipal.interfaceConPropostas();
            }
        });
        
        consultaInativaImovel = new JMenuItem("Inativa Imovel");
        consultaInativaImovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrPrincipal.interfaceConImovel();
            }
        });
        
        menuCad = new JMenu("Cadastro");
        menuCon = new JMenu("Consulta");
        menuRel = new JMenu("Relatórios");
        menuImportar = new JMenu("Importar");

        relatoriosItem = new JMenuItem("Gerar Relatórios");
        relatoriosItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ctrPrincipal.interfaceRelatorios();
            }
        });
        
        importarItem = new JMenuItem("Importar Imagem");
        importarItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                caminhoSalvar = JOptionPane.showInputDialog(null, "Digite o caminho até a pasta \"images\" que está na pasta do projeto.\nExemplo: C:\\user\\nome\\Documentos\\NetBeansProjects\\NomeDoProjeto\\images\\\n", "Caminho pasta Images", 1);
                if(caminhoSalvar!=null){
                    JFileChooser arquivo = new JFileChooser();
                    arquivo.setCurrentDirectory(new File(System.getProperty("user.home")));
                    FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.imagens", "jpeg", "jpg", "png", "gif");
                    int resultado = arquivo.showSaveDialog(null);
                    if(resultado == JFileChooser.APPROVE_OPTION){
                        File arqSelecionado = arquivo.getSelectedFile();
                        String caminho = arqSelecionado.getAbsolutePath();
                        System.out.println("PATH: " + caminho);
                        System.out.println("Foto: " + arqSelecionado.getName());
                        try{
                            String inFileName = caminho;
                            String outFileName = caminhoSalvar+arqSelecionado.getName();

                            FileChannel in;
                            FileChannel out;

                            FileInputStream input = new FileInputStream(inFileName);
                            FileOutputStream output = new FileOutputStream(outFileName);


                            in = input.getChannel();
                            out = output.getChannel();

                            in.transferTo(0, in.size(), out);//copiando o arquivo e salvando no diretório que você escolheu

                            out.close();
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(resultado == JFileChooser.CANCEL_OPTION){
                        System.out.println("Nenhum arquivo selecionado.");
                    }
                }
            }
        });
        
        //Criar JMenuBar
        barraMenu = new JMenuBar();

        //Adicionar componentes ao JMenu
        menuCad.add(cadastraCorretor);
        menuCad.add(cadastraVendedor);
        menuCad.add(cadastraImovel);
        menuCad.add(cadastraComprador);
        menuCad.add(new JSeparator(JSeparator.HORIZONTAL));

        menuCon.add(consultaCatalogo);
        menuCon.add(consultaPropostas);
        menuCon.add(consultaInativaImovel);
        menuCon.add(new JSeparator(JSeparator.HORIZONTAL));
        
        menuRel.add(relatoriosItem);
        menuRel.add(new JSeparator(JSeparator.HORIZONTAL));
        
        menuImportar.add(importarItem);
        
        barraMenu.add(menuCad);
        barraMenu.add(menuCon);
        barraMenu.add(menuRel);
        barraMenu.add(menuImportar);

        super.addWindowListener(this);
        
        super.setTitle("Lobby Principal - ItaHouse");
        super.setJMenuBar(barraMenu);
        super.setSize(width, height);
        super.setAlwaysOnTop(true);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setVisible(true);

    }

    @Override
    public void windowOpened(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //serializa todos
        ctrPrincipal.serializaDados();
        dispose();
    }

    @Override
    public void windowClosed(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
