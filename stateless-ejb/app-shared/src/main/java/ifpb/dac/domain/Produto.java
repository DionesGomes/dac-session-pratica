package ifpb.dac.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable{
    
    private int codigo;
    private String descricao;
    private BigDecimal valor;

    public Produto() {
    }
    
    public Produto(String descricao, BigDecimal valor){
        this(-1, descricao, valor);
    }

    public Produto(int codigo, String descricao, BigDecimal valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
