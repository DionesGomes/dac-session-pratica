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
	private ClienteInterface clienteDAO;
	
	
	public List<Cliente> getTodosOsClientes(){
		return clienteDAO.todosOsClientes();
	}

	public String adicionar(){
		this.getTodosOsClientes().add(cliente);
		return null;
	}
	public String remover(Cliente cliente){
		this.clienteDAO.remover(cliente);
		return null;
	}

	public String atualizar(Cliente cliente){
		this.clienteDAO.atualizar(cliente);
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
