/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Model.Corretor;
import Model.Imovel;
import Model.Proposta;
import Model.Util;
import Model.Vendedor;
import Model.Visita;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class ControleImovel {
    private ArrayList<Imovel> listaImovel = new ArrayList<Imovel>();
    private ArrayList<Imovel> listaImovelLote = new ArrayList<Imovel>();
    private ArrayList<Imovel> listaImovelCasa = new ArrayList<Imovel>();
    private ArrayList<Imovel> listaImovelApto = new ArrayList<Imovel>();
    private ArrayList<Imovel> listaImovelSala = new ArrayList<Imovel>();
    private ArrayList<Imovel> listaImovelRural = new ArrayList<Imovel>();
    private ArrayList<Imovel> listaImovelProposta = new ArrayList<Imovel>();
    private ArrayList<Imovel> listaImovelVendido = new ArrayList<Imovel>();
    private List<String> tiposImovel = new ArrayList<String>();
    
    public ControleImovel(){
        desserializaImovel();
        tiposImovel.add("Todos");
        tiposImovel.add(Util.LOTE);
        tiposImovel.add(Util.CASA);
        tiposImovel.add(Util.APTO);
        tiposImovel.add(Util.SALA);
        tiposImovel.add(Util.RURAL);
    }
    
    //Cadastro do imovel
    public void cadastraImovel(int codigo, String tipo, String endereco, String descricao,String foto, double preco, double comissao, Vendedor vendedorImovel){
        Imovel novoImovel = new Imovel(codigo, tipo, endereco,descricao,foto,preco,comissao, Calendar.getInstance(),vendedorImovel);
        listaImovel.add(novoImovel);
        System.out.println("Cadastrado Imovel!");
        JOptionPane.showMessageDialog(null, "Imóvel cadastrado!", "Sucesso", 1);
        if(tipo.equals(Util.LOTE)){
            listaImovelLote.add(novoImovel);
        }else if(tipo.equals(Util.CASA)){
            listaImovelCasa.add(novoImovel);
        }else if(tipo.equals(Util.APTO)){
            listaImovelApto.add(novoImovel);
        }else if(tipo.equals(Util.SALA)){
            listaImovelSala.add(novoImovel);
        }else if(tipo.equals(Util.RURAL)){
            listaImovelRural.add(novoImovel);
        }
        System.out.println("TIPO IMÓVEL CADASTRADO!");
    }

    public void cadastraImovel(int codigo, String tipo, String endereco, String descricao,String foto, double preco, double comissao, Calendar cal,Vendedor vendedorImovel){
        Imovel novoImovel = new Imovel(codigo, tipo, endereco,descricao,foto,preco,comissao, cal,vendedorImovel);
        listaImovel.add(novoImovel);
        System.out.println("Cadastrado Imovel teste!");
        JOptionPane.showMessageDialog(null, "Imóvel cadastrado!", "Sucesso", 1);
        if(tipo.equals(Util.LOTE)){
            listaImovelLote.add(novoImovel);
        }else if(tipo.equals(Util.CASA)){
            listaImovelCasa.add(novoImovel);
        }else if(tipo.equals(Util.APTO)){
            listaImovelApto.add(novoImovel);
        }else if(tipo.equals(Util.SALA)){
            listaImovelSala.add(novoImovel);
        }else if(tipo.equals(Util.RURAL)){
            listaImovelRural.add(novoImovel);
        }
        System.out.println("TIPO IMÓVEL CADASTRADO!");
    }
    
    public ArrayList<Imovel> getListaImovel(String tipo) {
        if(tipo.equals(Util.LOTE)){
            return listaImovelLote;
        }else if(tipo.equals(Util.CASA)){
            return listaImovelCasa;
        }else if(tipo.equals(Util.APTO)){
            return listaImovelApto;
        }else if(tipo.equals(Util.SALA)){
            return listaImovelSala;
        }else if(tipo.equals(Util.RURAL)){
            return listaImovelRural;
        }else if(tipo.equals("Proposta")){
            return listaImovelProposta;
        }
        return listaImovel;
    }

    public List<String> getTiposImovel() {
        return tiposImovel;
    }

    public ArrayList<Imovel> getListaImovelLote() {
        return listaImovelLote;
    }

    public ArrayList<Imovel> getListaImovelCasa() {
        return listaImovelCasa;
    }

    public ArrayList<Imovel> getListaImovelApto() {
        return listaImovelApto;
    }

    public ArrayList<Imovel> getListaImovelSala() {
        return listaImovelSala;
    }

    public ArrayList<Imovel> getListaImovelRural() {
        return listaImovelRural;
    }
    
    public String retornaDadosImovel(Imovel im){
        String str = "";
        str+="CÓDIGO: " + im.getCodigo()+"\nPREÇO: R$"+im.getPreco()+"\n\nDESCRIÇÃO: \n";
        str+=im.getDescricao()+"\n\nTIPO: "+im.getTipo()+"\nESTADO: "+im.getEstado().toUpperCase()+"\n";
        return str;
    }
    
    public boolean procuraPropostaERegistra(int codigo, Proposta proposta){
        boolean flag = false, flagProposta = false;
        Imovel imovel = null;
        for(Imovel im: listaImovel){
            if(im.getCodigo()==codigo){
                im.registraProposta(proposta);
                imovel = im;
                flag = true;
                JOptionPane.showMessageDialog(null, "Proposta cadastrada!", "Sucesso", 1);
                //procura se existe o imovel em listaproposta
                for(Imovel imProp: listaImovelProposta){
                    if(imProp.getCodigo()==codigo){
                        flagProposta = true;
                        break;
                    }
                }
                //se nao existe ele cria
                if(!flagProposta){
                    listaImovelProposta.add(imovel);
                }
                break;
            }
        }
        if(flag==false)
            return false;
        return flag;
    }
    
    public boolean procuraVisitaERegistra(int codigo, Visita visita){
        boolean flag = false;
        Imovel imovel = null;
        for(Imovel im: listaImovel){
            if(im.getCodigo()==codigo){
                im.agendaVisita(visita);
                imovel = im;
                flag = true;
                JOptionPane.showMessageDialog(null, "Visita cadastrada!", "Sucesso", 1);
                break;
            }
        }
        if(flag==false)
            return false;
        return flag;
    }
    
    public void procuraAlteraEstadoProposta(Imovel imo, Proposta pro, String novoEstado){
        String tipoImo = imo.getTipo();
        for(Imovel imov: listaImovel){
            if(imov==imo){
                for(Proposta prop: imov.getListaPropostas()){
                    if(prop==pro){
                        prop.setEstado(novoEstado);
                        if(novoEstado.equals(Util.ACEITA)){
                            imov.setEstado(Util.VENDIDO);
                            listaImovelVendido.add(imov);
                        }
                        break;
                    }
                }
                break;
            }
        }
        if(tipoImo.equals(Util.CASA)){
            for(Imovel imov: listaImovelCasa){
                if(imov==imo){
                    for(Proposta prop: imov.getListaPropostas()){
                        if(prop==pro){
                            prop.setEstado(novoEstado);
                            if(novoEstado.equals(Util.ACEITA)){
                                imov.setEstado(Util.VENDIDO);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }else if(tipoImo.equals(Util.APTO)){
            for(Imovel imov: listaImovelApto){
                if(imov==imo){
                    for(Proposta prop: imov.getListaPropostas()){
                        if(prop==pro){
                            prop.setEstado(novoEstado);
                            if(novoEstado.equals(Util.ACEITA)){
                                imov.setEstado(Util.VENDIDO);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }else if(tipoImo.equals(Util.SALA)){
            for(Imovel imov: listaImovelSala){
                if(imov==imo){
                    for(Proposta prop: imov.getListaPropostas()){
                        if(prop==pro){
                            prop.setEstado(novoEstado);
                            if(novoEstado.equals(Util.ACEITA)){
                                imov.setEstado(Util.VENDIDO);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }else if(tipoImo.equals(Util.RURAL)){
            for(Imovel imov: listaImovelRural){
                if(imov==imo){
                    for(Proposta prop: imov.getListaPropostas()){
                        if(prop==pro){
                            prop.setEstado(novoEstado);
                            if(novoEstado.equals(Util.ACEITA)){
                                imov.setEstado(Util.VENDIDO);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }else if(tipoImo.equals(Util.LOTE)){
            for(Imovel imov: listaImovelLote){
                if(imov==imo){
                    for(Proposta prop: imov.getListaPropostas()){
                        if(prop==pro){
                            prop.setEstado(novoEstado);
                            if(novoEstado.equals(Util.ACEITA)){
                                imov.setEstado(Util.VENDIDO);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        for(Imovel imov: listaImovelProposta){
            if(imov==imo){
                for(Proposta prop: imov.getListaPropostas()){
                    if(prop==pro){
                        prop.setEstado(novoEstado);
                        if(novoEstado.equals(Util.ACEITA)){
                            imov.setEstado(Util.VENDIDO);
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    public Imovel retornoImovelPorCodigo(int codigo){
        for(Imovel im: listaImovel){
            if(im.getCodigo()==codigo){
                return im;
            }
        }
        return null;
    }
    
    public void desativaImovel(int codigo){
        String tipoIm = "";
        for(Imovel im: listaImovel){
            if(im.getCodigo()==codigo){
                im.setEstado(Util.INATIVO);
                tipoIm = im.getTipo();
                break;
            }
        }
        if(tipoIm.equals(Util.APTO)){
            for(Imovel im: listaImovelApto){
                if(im.getCodigo()==codigo){
                    im.setEstado(Util.INATIVO);
                    break;
                }
            }
        }else if(tipoIm.equals(Util.CASA)){
            for(Imovel im: listaImovelCasa){
                if(im.getCodigo()==codigo){
                    im.setEstado(Util.INATIVO);
                    break;
                }
            }
        }else if(tipoIm.equals(Util.RURAL)){
            for(Imovel im: listaImovelRural){
                if(im.getCodigo()==codigo){
                    im.setEstado(Util.INATIVO);
                    break;
                }
            }
        }else if(tipoIm.equals(Util.SALA)){
            for(Imovel im: listaImovelSala){
                if(im.getCodigo()==codigo){
                    im.setEstado(Util.INATIVO);
                    break;
                }
            }
        }else if(tipoIm.equals(Util.LOTE)){
            for(Imovel im: listaImovelLote){
                if(im.getCodigo()==codigo){
                    im.setEstado(Util.INATIVO);
                    break;
                }
            }
        }
    }
    
    public double retornoDinheiroImobiliaria(Calendar inicio, Calendar fim, boolean comCorretor){
        double total = 0;
        System.out.println("Tamanho do arrayvendido:" +listaImovelVendido.size());
        for(Imovel im: listaImovelVendido){
            
            double comissao = im.getComissao();
            double resultado = 0;
            for(Proposta prop: im.getListaPropostas()){
                if(prop.getEstado().equals(Util.ACEITA)){
                    if((prop.getData().before(fim) && prop.getData().after(inicio)) ||(fim.equals(prop.getData()) || inicio.equals(prop.getData()))){
                        resultado+=prop.getValor()*(comissao/100);
                        if(comCorretor==true){
                            resultado-=resultado*(prop.getCorretor().getPercCorretagem()/100);
                        }
                        total+=resultado;
                        resultado=0;
                        break;
                    }
                }
            }
        }
        return total;
    }
    
    public double retornoDinheiroCorretor(Calendar inicio, Calendar fim, String cpf){
        double total = 0, imobiliaria=0, corretor=0;
        System.out.println("Tamanho do arrayvendido:" +listaImovelVendido.size());
        for(Imovel im: listaImovelVendido){
            
            double comissao = im.getComissao();
            for(Proposta prop: im.getListaPropostas()){
                if(prop.getEstado().equals(Util.ACEITA)){
                    if(prop.getCorretor().getCpf().equals(cpf)){
                        if((prop.getData().before(fim) && prop.getData().after(inicio)) ||(fim.equals(prop.getData()) || inicio.equals(prop.getData()))){
                            imobiliaria+=prop.getValor()*(comissao/100);
                            corretor+=imobiliaria*prop.getCorretor().getPercCorretagem()/100;
                            total+=corretor;
                            imobiliaria=0;
                            corretor=0;
                            break;
                        }
                    }
                }
            }
        }
        return total;
    }
    
    public String retornoImoveisVendidosString(Calendar inicio, Calendar fim){
        String resultado = "Imóveis Vendidos:\n";
        for(Imovel im: listaImovelVendido){
            double comissao = im.getComissao();
            for(Proposta prop: im.getListaPropostas()){
                if(prop.getEstado().equals(Util.ACEITA)){
                    if((prop.getData().before(fim) && prop.getData().after(inicio)) ||(fim.equals(prop.getData()) || inicio.equals(prop.getData()))){
                        double comImob = 0;
                        resultado+=retornaDadosImovel(im);
                        resultado+="Valor de Venda(Proposta): R$"+ prop.getValor()+"\n";
                        resultado+="Valor da Comissao ganha pela imobiliária: R$";
                        comImob = (comissao/100)*prop.getValor();
                        resultado+=comImob+"\n";
                        break;
                    }
                }
            }
            resultado+="\n";
        }
        return resultado;
    }
    
    public String retornoImoveisPorVendedor(String cpf){
        String resultado = "Imóveis do Vendedor:\n";
        for(Imovel im: listaImovel){
            if(im.getVendedor().getCpf().equals(cpf)){
                resultado+="\n";
                resultado+=retornaDadosImovel(im);
            }
        }
        return resultado;
    }
    
    public String retornoEventosImovelPorPeriodo(Calendar inicio, Calendar fim, int codigo){
        String resultado = "Eventos dos Imóveis:\n";
        for(Imovel im: listaImovel){
            if(im.getCodigo() == codigo){
                resultado+="\n";
                String visitasRecebidas = "";
                String propostasRecebidas = "";
                for(Visita v: im.getListaVisitas()){
                    visitasRecebidas+= retornaVisitasRecebidas(v, inicio, fim);
                    visitasRecebidas+="\n";
                }
                for(Proposta p: im.getListaPropostas()){
                    propostasRecebidas+=retornaPropostasRecebidas(p, inicio, fim);
                    propostasRecebidas+="\n";
                }
                resultado+="Visitas:\n";
                resultado+=visitasRecebidas;
                resultado+="\nPropostas:\n";
                resultado+=propostasRecebidas;
                break;
            }
        }
        return resultado;
    }
    
    public String retornaVisitasRecebidas(Visita visita, Calendar inicio, Calendar fim){
        String visitaString = "";
        if((visita.getData().before(fim) && visita.getData().after(inicio)) ||(fim.equals(visita.getData()) || inicio.equals(visita.getData()))){
            visitaString+="Data: "+visita.getData().getTime().toString()+
                    "\nCorretor: "+visita.getCorretor().getNome()+
                    "\nComprador: "+visita.getComprador().getNome()+"\n";
        }
        return visitaString;
    }
    
    public String retornaPropostasRecebidas(Proposta proposta, Calendar inicio, Calendar fim){
        String propostaString = "";
        if((proposta.getData().before(fim) && proposta.getData().after(inicio)) ||(fim.equals(proposta.getData()) || inicio.equals(proposta.getData()))){
            propostaString+="Data: "+proposta.getData().getTime().toString()+
                    "\nCorretor: "+proposta.getCorretor().getNome()+
                    "\nComprador: "+proposta.getComprador().getNome()+
                    "\nValor: "+proposta.getValor()+
                    "\nEstado: "+proposta.getEstado()+"\n";
        }
        return propostaString;
    }
    
    public String retornaVisitasCorretorString(Calendar inicio, Calendar fim, String cpfCorretor){
        String visitasCorretor = "Visitas pelo Corretor:\n";
        for(Imovel im: listaImovel){
            for(Visita v: im.getListaVisitas()){
                if((v.getData().before(fim) && v.getData().after(inicio)) ||(fim.equals(v.getData()) || inicio.equals(v.getData()))){
                    if(v.getCorretor().getCpf().equals(cpfCorretor)){
                        visitasCorretor+="\nCódigo do Imóvel:"+im.getCodigo()+"\nVisita:\n"+retornaVisitasRecebidas(v, inicio, fim);
                    }
                }
            }
        }
        return visitasCorretor;
    }
    
    public void desserializaImovel(){
        //Parada com arquivo
        try{
            FileInputStream arquivo = new FileInputStream("imoveis.tmp");
            ObjectInputStream leArq = new ObjectInputStream(arquivo);
            listaImovel = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
            arquivo = new FileInputStream("imoveisLote.tmp");
            leArq = new ObjectInputStream(arquivo);
            listaImovelLote = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
            arquivo = new FileInputStream("imoveisCasa.tmp");
            leArq = new ObjectInputStream(arquivo);
            listaImovelCasa = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
            arquivo = new FileInputStream("imoveisApto.tmp");
            leArq = new ObjectInputStream(arquivo);
            listaImovelApto = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
            arquivo = new FileInputStream("imoveisSala.tmp");
            leArq = new ObjectInputStream(arquivo);
            listaImovelSala = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
            arquivo = new FileInputStream("imoveisRural.tmp");
            leArq = new ObjectInputStream(arquivo);
            listaImovelRural = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
            arquivo = new FileInputStream("imoveisProp.tmp");
            leArq = new ObjectInputStream(arquivo);
            listaImovelProposta = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
            arquivo = new FileInputStream("imoveisVend.tmp");
            leArq = new ObjectInputStream(arquivo);
            listaImovelVendido = (ArrayList<Imovel>) leArq.readObject();
            leArq.close();
            
        }catch(Exception ex){
            System.out.println("Não existe arquivo imoveis");
        }
    }

    void serializaImovel() {
        try{
            FileOutputStream arquivo = new FileOutputStream("imoveis.tmp");
            ObjectOutputStream escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovel);
            escreveArq.close();
            
            arquivo = new FileOutputStream("imoveisLote.tmp");
            escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovelLote);
            escreveArq.close();
            
            arquivo = new FileOutputStream("imoveisCasa.tmp");
            escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovelCasa);
            escreveArq.close();
            
            arquivo = new FileOutputStream("imoveisApto.tmp");
            escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovelApto);
            escreveArq.close();
            
            arquivo = new FileOutputStream("imoveisSala.tmp");
            escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovelSala);
            escreveArq.close();
            
            arquivo = new FileOutputStream("imoveisRural.tmp");
            escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovelRural);
            escreveArq.close();
            
            arquivo = new FileOutputStream("imoveisProp.tmp");
            escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovelProposta);
            escreveArq.close();
            
            arquivo = new FileOutputStream("imoveisVend.tmp");
            escreveArq = new ObjectOutputStream(arquivo);
            escreveArq.writeObject(listaImovelVendido);
            escreveArq.close();
            
        }catch(Exception ex){
            System.out.println("Erro ao serializar arquivo imoveis.");
        }
    }
}
