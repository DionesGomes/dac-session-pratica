package ifpb.dac.stateless.controler;

import ifpb.dac.domain.Carrinho;
import ifpb.dac.domain.Produto;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class ControladorDeCarrinho implements Serializable {

    private Produto produto;

    @EJB
    private Carrinho carrinho;

    public String adicionar(Produto p){
        this.carrinho.adicionar(p);
        return null;
    }

    public List<Produto> getTodos(){
        return this.carrinho.produtos();
    }

    public String remover(){
        this.carrinho.remover(produto);
        return null;
    }

    public void finalizarSessao(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
        session.invalidate();
    }

    public String finalizar(){
        this.carrinho.finalizar();
        finalizarSessao();
        return "carinho.xhtml?faces-redirect=true";

    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
