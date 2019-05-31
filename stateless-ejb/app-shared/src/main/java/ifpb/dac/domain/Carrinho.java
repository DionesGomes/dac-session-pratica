package ifpb.dac.domain;

import java.util.List;

public interface Carrinho {

    void adicionar(String produto);

    List<String> produtos();

    void remover(String produto);
    
    void finalizar();
    
}
