package ifpb.dac.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.ClienteInterface;


@SessionScoped
@Named
public class ControleDeClientes implements Serializable{
	
	@EJB
	private ClienteInterface clientesEmJDBC;
	
	
	public List<Cliente> getTodosOsClientes(){
		return clientesEmJDBC.todososclientes();
	}
	
	

}
