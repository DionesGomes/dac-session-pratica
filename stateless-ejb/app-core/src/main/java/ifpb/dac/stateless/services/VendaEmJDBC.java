package ifpb.dac.stateless.services;

import ifpb.dac.domain.Venda;
import ifpb.dac.domain.VendaInterface;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@Remote(value = VendaInterface.class)
public class VendaEmJDBC implements VendaInterface {

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
    public void adicionar(Venda venda) {
        try {
            String query = "INSERT INTO"
                    + "  venda (cpf, nome)"
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
    public void remover(Venda venda) {

    }

    @Override
    public List<Venda> listar() {
        return null;
    }

    @Override
    public Venda buscar(int id) {
        return null;
    }
}
