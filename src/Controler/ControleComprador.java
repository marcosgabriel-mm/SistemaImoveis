/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Comprador;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class ControleComprador{
    private ArrayList<Comprador> listaComprador = new ArrayList<Comprador>();
    final int MessageType = 1;
    
    public ControleComprador(){
        desserializaComprador();
    }
    
    public void cadastraComprador(String cpf, String nome, String email, String fone,
                    String contatoPref){
        Comprador novoComprador = new Comprador(cpf, nome, email,fone,contatoPref);
        listaComprador.add(novoComprador);
        System.out.println("Cadastrado Comprador!");
        JOptionPane.showMessageDialog(null, "Comprador cadastrado!", "Sucesso", MessageType);
    }

    public Comprador retornaCompradorPorCpf(String cpf) {
        for(Comprador comp: listaComprador){
            if(cpf.equals(comp.getCpf())){
                return comp;
            }
        }
        return null;
    }
       
    void desserializaComprador(){
        //Parada com arquivo
        try{
            FileInputStream arquivo = new FileInputStream("compradores.tmp");
            ObjectInputStream leArq = new ObjectInputStream(arquivo);
            listaComprador = (ArrayList<Comprador>) leArq.readObject();
            leArq.close();
        }catch(Exception ex){
            System.out.println("Não existe arquivo compradores");
        }
    }

    void serializaComprador() {
        try{
            FileOutputStream arquivo = new FileOutputStream("compradores.tmp");
            ObjectOutputStream escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaComprador);
            escreveArq.close();
        }catch(Exception ex){
            System.out.println("Erro ao serializar arquivo compradores.");
        }
    }
}
