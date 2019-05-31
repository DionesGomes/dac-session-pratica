package ifpb.dac.domain;

import java.util.List;

public interface Carrinho {

    void adicionar(Produto produto);

    List<Produto> produtos();

    void remover(Produto produto);
    
    void finalizar();
    
}
