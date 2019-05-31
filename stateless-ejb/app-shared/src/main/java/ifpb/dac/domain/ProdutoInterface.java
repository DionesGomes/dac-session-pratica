package ifpb.dac.domain;

import java.util.List;


public interface ProdutoInterface {
    
    void adicionar(Produto produto);
    
    void remover(Produto produto);
    
    void atualizar(Produto produto);
    
    List<Produto> todosOsProduto();
    
    List<Produto> buscarPelaDescricao(String descricao);
}
