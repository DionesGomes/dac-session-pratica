package ifpb.dac.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.ClienteInterface;


@RequestScoped
@Named
public class ControleDeClientes implements Serializable{

	private Cliente cliente;
	
	@EJB
	private ClienteInterface clientesEmJDBC;
	
	
	public List<Cliente> getTodosOsClientes(){
		return clientesEmJDBC.todosOsClientes();
	}

	public String adicionar(){
		this.getTodosOsClientes().add(cliente);
		return null;
	}
	public String remover(Cliente cliente){
		this.clientesEmJDBC.remover(cliente);
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
