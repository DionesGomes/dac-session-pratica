/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dac.stateless.controler;

import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.Venda;
import ifpb.dac.domain.VendaInterface;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author diones
 */

@RequestScoped
@Named
public class ControladorDeVendas implements Serializable {
    
    private Venda venda;
    
    @EJB
    private VendaInterface vendasEmJDBC;
    
   
    public List<Venda> getTodasVendas(){        
        return vendasEmJDBC.listar();
    }
    
    public String adicionar(){
        this.vendasEmJDBC.adicionar(venda);
        return null;
    }
    
    public String remove(Venda venda){
        this.vendasEmJDBC.remover(venda);
        return null;
    }   
    
    public Venda getVenda(){
        return venda;
    }
    
    public void setVenda(Venda venda){
        this.venda = venda;
    }  

    public VendaInterface getVendasEmJDBC() {
        return vendasEmJDBC;
    }
      
}
