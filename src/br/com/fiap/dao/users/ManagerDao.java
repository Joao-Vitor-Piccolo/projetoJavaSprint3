package br.com.fiap.dao.users;

import br.com.fiap.entities.users.Manager;
import br.com.fiap.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerDao {

    public Connection myConnection;

    public ManagerDao() throws SQLException, ClassNotFoundException {
        this.myConnection = new ConnectionFactory().conn();
    }

    // Insert metodo para inserir no banco de dados
    public String inserir(Manager managerUser) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement("Insert into T_FIAP_PRODUTO values (?,?,?,?)");
        stmt.setInt(1, managerUser.getCodigo());
        stmt.setString(2, managerUser.getTipo());
        stmt.setString(3, managerUser.getMarca());
        stmt.setDouble(4, managerUser.getPreco());
        stmt.execute();

        return "Produto cadastrado com sucesso!";
    }
}
