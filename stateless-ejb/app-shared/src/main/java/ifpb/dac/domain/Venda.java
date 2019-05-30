package ifpb.dac.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable{
    
    private int idcliente;
    private List<Produto> produtos;
    
    public Venda(){
    }

    public Venda(int idcliente) {
        this.idcliente = idcliente;
        this.produtos = new ArrayList<>();
    }

    public Venda(int idcliente, List<Produto> produtos) {
        this.idcliente = idcliente;
        this.produtos = produtos;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
    
}
