package ifpb.dac.web;

import ifpb.dac.domain.Carrinho;
import ifpb.dac.domain.Produto;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful
@Remote(Carrinho.class)
@StatefulTimeout(unit = TimeUnit.SECONDS,value = 60)
public class CarrinhoDeCompras implements Carrinho{

    private List<Produto> produtos = new ArrayList<>();


    @Override
    public void adicionar(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public List<Produto> produtos(){
        return Collections.unmodifiableList(produtos);
    }

    @Override
    public void remover(Produto produto) {
            this.produtos.remove(produto);
    }

    @Override
    @Remove
    public void finalizar() {

    }
}
