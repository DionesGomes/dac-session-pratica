package ifpb.dac.domain;

import java.util.List;


public interface ProdutoInterface {
    
    void adicionar(Produto produto);
    
    void remover(Produto produto);
    
    List<Produto> todososclientes();
    
    Produto buscardescricao(String descricao);
}
