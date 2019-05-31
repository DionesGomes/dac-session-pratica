package ifpb.dac.stateless.controler;

import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.ClienteInterface;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@RequestScoped
@Named
public class ControladorDeClientes implements Serializable{

	private Cliente cliente;
	
	@EJB
	private ClienteInterface clientesEmJDBC;
	
	
	public List<Cliente> getTodosOsClientes(){
		return clientesEmJDBC.todososclientes();
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
