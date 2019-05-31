package ifpb.dac.domain;

import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private Produto produto;
    private int quantidade;
    private double subtotal;

    public Item() {
    }

    public Item(int id, Produto produto, int quantidade, double subtotal) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
