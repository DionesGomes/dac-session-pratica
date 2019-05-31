package ifpb.dac.stateless.services;

import ifpb.dac.domain.Item;
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
import java.util.ArrayList;
import java.util.Collections;
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
                    + "  venda (idcliente, total)"
                    + "  VALUES(?,?)"
                    + " RETURNING id; ";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1,venda.getIdcliente());
            stm.setDouble(2,venda.getTotal());
            ResultSet rs = stm.executeQuery();
            if (rs.next())
                venda.setId(rs.getInt("id"));
            // Manipular a tabela itemVenda

        } catch (SQLException ex) {
            Logger.getLogger(ClientesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void remover(Venda venda) {
        try {
            String query = " DELETE FROM venda "
                    + " WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, venda.getId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Venda> listar() {
        try {
            List<Venda> lista = new ArrayList<>();
            String query = " SELECT * "
                    + " FROM venda "
                    + " ORDER BY id ";
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(criarVenda(rs));
            }
            return lista;
        } catch (SQLException ex) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Venda buscar(int id) {
        try {
            String query = "SELECT * "
                    + " FROM venda "
                    + " WHERE id = ? ";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next())
                return criarVenda(rs);
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Venda criarVenda(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int idcliente = result.getInt("id_cliente");
        double total = result.getDouble("total");
        return new Venda(id,idcliente,total);
    }
}
