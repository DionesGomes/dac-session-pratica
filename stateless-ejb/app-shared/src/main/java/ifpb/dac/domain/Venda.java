package ifpb.dac.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable{

    private int id;
    private int idcliente;
    private List<Produto> produtos;
    private double total;
    
    public Venda(){
    }

    public Venda(int idcliente) {
        this.idcliente = idcliente;
        this.produtos = new ArrayList<>();
    }

    public Venda(int idcliente, List<Produto> produtos, double total) {
        this.idcliente = idcliente;
        this.produtos = produtos;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
