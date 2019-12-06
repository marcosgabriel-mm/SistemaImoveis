package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Comprador extends Pessoa implements Serializable{
    
    private String contatoPref;
    private ArrayList<String> listaTipoImovelCompra;

    public Comprador(String cpf, String nome, String email, String fone,
            String contatoPref) {
        super(cpf, nome, email, fone);
        this.contatoPref = contatoPref;
    }

    public String getContatoPref() {
        return contatoPref;
    }

    public void addTipoImovel(String tipoImovel) throws Exception {
        if ((!tipoImovel.equalsIgnoreCase(Util.CASA))
                && (!tipoImovel.equalsIgnoreCase(Util.APTO))
                && (!tipoImovel.equalsIgnoreCase(Util.LOTE))
                && (!tipoImovel.equalsIgnoreCase(Util.RURAL))) {
            throw new Exception("Tipo de imóvel inválido");
        } else {
            listaTipoImovelCompra.add(tipoImovel);
        }
    }

    public boolean removeTipoImovel(String tipoImovel) throws Exception {
        for (int i = 0; i < listaTipoImovelCompra.size(); i++) {
            if (listaTipoImovelCompra.get(i).equalsIgnoreCase(tipoImovel)) {
                listaTipoImovelCompra.remove(i);
                return true;

            }
        }
        return false;
    }

}
