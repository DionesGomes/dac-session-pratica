package ifpb.dac.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venda implements Serializable{

    private int id;
    private int idcliente;
    private List<Item> itens;
    private double total;
    
    public Venda(){
    }

    public Venda(int idcliente) {
        this.idcliente = idcliente;
        this.itens = new ArrayList<>();
    }

    public Venda(int id, int idcliente, double total){
        this.id = id;
        this.idcliente = idcliente;
        this.total = total;
        this.itens = new ArrayList<>();
    }

    public Venda(int idcliente, List<Item> itens, double total) {
        this.idcliente = idcliente;
        this.itens = itens;
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

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    
    
    
}
