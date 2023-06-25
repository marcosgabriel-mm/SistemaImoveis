/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.DetalhesVendedor;
import Model.Vendedor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class ControleVendedor implements Serializable {
    private ArrayList<Vendedor> listaVendedor = new ArrayList<Vendedor>();

    public ControleVendedor() {
        desserializaVendedor();
    }

    public void cadastraVendedor(DetalhesVendedor detalhes) {
        Vendedor novoVendedor = new Vendedor(detalhes.getCpf(), detalhes.getNome(),
                detalhes.getEmail(), detalhes.getFone(), detalhes.getContatoPref());
        listaVendedor.add(novoVendedor);
        System.out.println("Cadastrado Vendedor!");
        JOptionPane.showMessageDialog(null, "Vendedor cadastrado!", "Sucesso", 1);
    }

    public boolean existeVendedor(String cpf) {
        for (Vendedor vendedor : listaVendedor) {
            if (vendedor.getCpf().equals(cpf))
                return true;
        }
        return false;
    }

    public Vendedor retornaVendedorPorCpf(String cpf) {
        for (Vendedor vendedor : listaVendedor) {
            if (vendedor.getCpf().equals(cpf))
                return vendedor;
        }
        return null;
    }

    void desserializaVendedor() {
        // Parada com arquivo
        try {
            FileInputStream arquivo = new FileInputStream("vendedores.tmp");
            ObjectInputStream leArq = new ObjectInputStream(arquivo);
            listaVendedor = (ArrayList<Vendedor>) leArq.readObject();
            leArq.close();
        } catch (Exception ex) {
            System.out.println("Não existe arquivo vendedores");
        }
    }

    void serializaVendedor() {
        try {
            FileOutputStream arquivo = new FileOutputStream("vendedores.tmp");
            ObjectOutputStream escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaVendedor);
            escreveArq.close();
        } catch (Exception ex) {
            System.out.println("Erro ao serializar arquivo vendedores.");
        }
    }
}
