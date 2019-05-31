package ifpb.dac.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ifpb.dac.domain.Cliente;
import ifpb.dac.stateless.services.ClientesEmJDBC;


@SessionScoped
@Named
public class ControleDeClientes implements Serializable{
	
	@EJB
	private ClientesEmJDBC clientesEmJDBC;
	
	
	public List<Cliente> getTodosOsClientes(){
		return clientesEmJDBC.todososclientes();
	}
	
	

}
