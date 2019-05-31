package ifpb.dac.stateless.controler;


import ifpb.dac.domain.Produto;
import ifpb.dac.domain.ProdutoInterface;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class ControladorDeProdutos implements Serializable {

	private Produto produto;
	
	private String descricaoProduto;
	
    @EJB
    private ProdutoInterface produtoDAO;
    
    public List<Produto> getTodosOsProdutos(){
    	return produtoDAO.todosOsProduto();
    }
    
    public String adicionar() {
    	this.produtoDAO.adicionar(produto);
    	return null;    	
    }
    
    public String remover(Produto produto) {
    	this.produtoDAO.remover(produto);
    	return null;
    }   
    
    public List<Produto> getProdutosBuscados(){
    	return null;
    }
    

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
    
    
    

    

}
