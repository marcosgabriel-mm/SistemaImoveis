/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Corretor;
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
public class ControleCorretor {
    private ArrayList<Corretor> listaCorretor = new ArrayList<Corretor>();
    
    public ControleCorretor(){
        desserializaCorretor();
    }
    
    //Cadastro do corretor
    public void cadastraCorretor(String cpf, String nome, String email, String fone,
                    String creci, double percCorretagem){
        Corretor novoCorretor = new Corretor(cpf, nome, email,fone,creci,percCorretagem);
        listaCorretor.add(novoCorretor);
        System.out.println("Cadastrado Corretor!");
        JOptionPane.showMessageDialog(null, "Corretor cadastrado!", "Sucesso", 1);
    }
    
    public Corretor retornaCorretorPorCpf(String cpf) {
        for(Corretor cor: listaCorretor){
            if(cpf.equals(cor.getCpf())){
                return cor;
            }
        }
        return null;
    }
    
    void desserializaCorretor(){
        //Parada com arquivo
        try{
            FileInputStream arquivo = new FileInputStream("corretores.tmp");
            ObjectInputStream leArq = new ObjectInputStream(arquivo);
            listaCorretor = (ArrayList<Corretor>) leArq.readObject();
            leArq.close();
        }catch(Exception ex){
            System.out.println("Não existe arquivo corretores");
        }
    }

    void serializaCorretor() {
        try{
            FileOutputStream arquivo = new FileOutputStream("corretores.tmp");
            ObjectOutputStream escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaCorretor);
            escreveArq.close();
        }catch(Exception ex){
            System.out.println("Erro ao serializar arquivo corretores.");
        }
    }
}
