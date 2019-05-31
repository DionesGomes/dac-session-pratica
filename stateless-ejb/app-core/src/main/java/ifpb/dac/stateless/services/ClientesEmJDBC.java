package ifpb.dac.stateless.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.ClienteInterface;


@Stateless
@Remote(value = ClienteInterface.class)
public class ClientesEmJDBC implements ClienteInterface{
	
	@Resource(name = "java:app/jdbc/clientes")
    private DataSource dataSource;
    private Connection connection;
	
	@PostConstruct
	public void init() {
		try {
            this.connection = this.dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
	}

	@Override
	public void adicionar(Cliente cliente) {
		try {
			String query = "INSERT INTO"
					+ "  cliente (cpf, nome)"
					+ "  VALUES(?,?)"
					+ " RETURNING id; "; 
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1,cliente.getCpf());
            stm.setString(2,cliente.getNome());
            ResultSet rs = stm.executeQuery();
            if (rs.next())
            	cliente.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
	}

	@Override
	public void remover(Cliente cliente) {
		try {
			String query = " DELETE FROM cliente "
					+ " WHERE id = ?";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, cliente.getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public List<Cliente> todosOsClientes() {
		 try {
	            List<Cliente> lista = new ArrayList<>();
	            String query = " SELECT * "
	            		+ " FROM cliente "
	            		+ " ORDER BY nome ";
	            PreparedStatement stm = connection.prepareStatement(query); 
	            ResultSet rs = stm.executeQuery();
	            while (rs.next()) {
	                lista.add(
	                    criarCliente(rs)
	                );
	            }
	            return lista;
	        } catch (SQLException ex) {
	            return Collections.EMPTY_LIST;
	        }
	}

	@Override
	public Cliente buscarCpf(String cpf) {
		try {
			String query = "SELECT * "
					+ " FROM cliente "
					+ " WHERE cpf = ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setString(1, cpf);
			ResultSet rs = stm.executeQuery();
			if(rs.next())
				return criarCliente(rs);
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	private Cliente criarCliente(ResultSet result) throws SQLException {
        String nome = result.getString("nome");
        String cpf = result.getString("cpf");
        int id = result.getInt("id");
        return new Cliente(id,nome,cpf);
    }

    @Override
    public void atualizar(Cliente cliente) {
        
         try {         
                String query = "UPDATE cliente SET nome = ?, cpf = ? "
                                + "WHERE id = ? ";
					
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1,cliente.getNome());
            stm.setString(2, cliente.getCpf());
            stm.setInt(3, cliente.getId());
            ResultSet rs = stm.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

}
