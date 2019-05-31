/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ifpb.dac.stateless.services;

import ifpb.dac.domain.Cliente;
import ifpb.dac.domain.Produto;
import ifpb.dac.domain.ProdutoInterface;
import java.math.BigDecimal;
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

/**
 *
 * @author diones
 */

@Stateless
@Remote(value = ProdutoInterface.class)
public class ProdutosEmJDBC implements ProdutoInterface{
    
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
    public void adicionar(Produto produto) {
        
        try {
			String query = "INSERT INTO " 
					+ "  produtos (descricao, valor) "
					+ "  VALUES(?,?) "; 
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1,produto.getDescricao());
            stm.setBigDecimal(2,produto.getValor());
            ResultSet rs = stm.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void remover(Produto produto) {
        
        try {
			String query = " DELETE FROM produto "
					+ " WHERE id = ?";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setInt(1, produto.getCodigo());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
    }

   

    @Override
    public List<Produto> buscarPelaDescricao(String descricao) {
        List<Produto> produtos = new ArrayList<Produto>();
        try {
			String query = "SELECT * "
					+ " FROM produto "
					+ " WHERE descricao ILIKE ? ";
			PreparedStatement stm = connection.prepareStatement(query);
			stm.setString(1, "%"+descricao+"%");
			ResultSet rs = stm.executeQuery();
			while (rs.next())
				produtos.add(criarProduto(rs));
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		return produtos;
        }   
    
    private Produto criarProduto(ResultSet result) throws SQLException {
        Integer codigo = result.getInt("id");
        String descricao = result.getString("descricao");
        BigDecimal valor = result.getBigDecimal("valor");
        return new Produto(codigo,descricao,valor);
    
    }

    @Override
    public void atualizar(Produto produto) {       
        
        try {         
                String query = "UPDATE produto SET descricao = ?, valor = ? "
                                + "WHERE id = ?";
					
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1,produto.getDescricao());
            stm.setBigDecimal(2,produto.getValor());
            stm.setInt(3, produto.getCodigo());
            ResultSet rs = stm.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public List<Produto> todosOsProduto() {        
        try {
	        List<Produto> lista = new ArrayList<>();
	        String query = " SELECT * "
	            		+ " FROM produto "
	            		+ " ORDER BY nome ";
	         PreparedStatement stm = connection.prepareStatement(query); 
	         ResultSet rs = stm.executeQuery();
	            while (rs.next()) {
	                lista.add(
	                    criarProduto(rs)
	                );
	            }
	            return lista;
	    } catch (SQLException ex) {
	      return Collections.EMPTY_LIST;
         }
    }
}
