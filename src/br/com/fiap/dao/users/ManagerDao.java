package br.com.fiap.dao.users;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.entities.users.Customer;
import br.com.fiap.entities.users.Manager;
import br.com.fiap.entities.users.Salesman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDao {

    public Connection myConnection;

    public ManagerDao() throws SQLException, ClassNotFoundException {
        this.myConnection = new ConnectionFactory().conn();
    }

    public String inserir(Manager managerUser) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO MANAGER (ID_MANAGER, NOME, CPF, TELEFONE) VALUES (?,?,?,?)");
        stmt.setInt(1, managerUser.getId());
        stmt.setString(2, managerUser.getName());
        stmt.setString(3, managerUser.getCpf());
        stmt.setString(4, managerUser.getPhone());
        stmt.execute();
        return "Manager cadastrado com sucesso!";
    }

    public String atualizar(Manager managerUser) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE MANAGER SET NOME=?, CPF=?, TELEFONE=? WHERE ID_MANAGER=?");
        stmt.setString(1, managerUser.getName());
        stmt.setString(2, managerUser.getCpf());
        stmt.setString(3, managerUser.getPhone());
        stmt.setInt(4, managerUser.getId());
        stmt.execute();
        return "Manager atualizado com sucesso!";
    }

    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM MANAGER WHERE ID_MANAGER=?");
        stmt.setInt(1, id);
        stmt.execute();
        return "Manager deletado com sucesso!";
    }

    public Manager buscar(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM MANAGER WHERE ID_MANAGER=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Manager(
                    rs.getInt("ID_MANAGER"),
                    rs.getString("NOME"),
                    rs.getString("CPF"),
                    rs.getString("TELEFONE"));
        }
        return null;
    }

    public String inserirCustomer(Customer customer) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO CUSTOMER (ID_CUSTOMER, NOME, CPF, TELEFONE) VALUES (?,?,?,?)");
        stmt.setInt(1, customer.getId());
        stmt.setString(2, customer.getName());
        stmt.setString(3, customer.getCpf());
        stmt.setString(4, customer.getPhone());
        stmt.execute();
        return "Customer cadastrado com sucesso!";
    }

    public String atualizarCustomer(Customer customer) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE CUSTOMER SET NOME=?, CPF=?, TELEFONE=? WHERE ID_CUSTOMER=?");
        stmt.setString(1, customer.getName());
        stmt.setString(2, customer.getCpf());
        stmt.setString(3, customer.getPhone());
        stmt.setInt(4, customer.getId());
        stmt.execute();
        return "Customer atualizado com sucesso!";
    }

    public String deletarCustomer(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM CUSTOMER WHERE ID_CUSTOMER=?");
        stmt.setInt(1, id);
        stmt.execute();
        return "Customer deletado com sucesso!";
    }

    public Customer buscarCustomer(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM CUSTOMER WHERE ID_CUSTOMER=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Customer(
                    rs.getInt("ID_CUSTOMER"),
                    rs.getString("NOME"),
                    rs.getString("CPF"),
                    rs.getString("TELEFONE"));
        }
        return null;
    }

    public String inserirSalesman(Salesman salesman) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO SALESMAN (ID_SALESMAN, NOME, CPF, TELEFONE) VALUES (?,?,?,?)");
        stmt.setInt(1, salesman.getId());
        stmt.setString(2, salesman.getName());
        stmt.setString(3, salesman.getCpf());
        stmt.setString(4, salesman.getPhone());
        stmt.execute();
        return "Salesman cadastrado com sucesso!";
    }

    public String atualizarSalesman(Salesman salesman) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE SALESMAN SET NOME=?, CPF=?, TELEFONE=? WHERE ID_SALESMAN=?");
        stmt.setString(1, salesman.getName());
        stmt.setString(2, salesman.getCpf());
        stmt.setString(3, salesman.getPhone());
        stmt.setInt(4, salesman.getId());
        stmt.execute();
        return "Salesman atualizado com sucesso!";
    }

    public String deletarSalesman(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM SALESMAN WHERE ID_SALESMAN=?");
        stmt.setInt(1, id);
        stmt.execute();
        return "Salesman deletado com sucesso!";
    }

    public Salesman buscarSalesman(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALESMAN WHERE ID_SALESMAN=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Salesman(
                    rs.getInt("ID_SALESMAN"),
                    rs.getString("NOME"),
                    rs.getString("CPF"),
                    rs.getString("TELEFONE"));
        }
        return null;
    }

    public String inserirSale(Sale sale) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO SALE (ID_SALE, FK_SALESMAN, FK_CUSTOMER, IS_STARTED, IS_FINISHED, CREATED_AT) VALUES (?,?,?,?,?,?)");
        stmt.setInt(1, sale.getId_sale());
        stmt.setInt(2, sale.getFk_salesman());
        stmt.setInt(3, sale.getFk_customer());
        stmt.setInt(4, sale.getIs_started_value());
        stmt.setInt(5, sale.getIs_finished_value());
        stmt.setDate(6, sale.getCreated_at());
        stmt.execute();
        return "Sale cadastrada com sucesso!";
    }

    public String atualizarSale(Sale sale) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE SALE SET FK_SALESMAN=?, FK_CUSTOMER=?, IS_STARTED=?, IS_FINISHED=?, CREATED_AT=? WHERE ID_SALE=?");
        stmt.setInt(1, sale.getFk_salesman());
        stmt.setInt(2, sale.getFk_customer());
        stmt.setInt(3, sale.getIs_started_value());
        stmt.setInt(4, sale.getIs_finished_value());
        stmt.setDate(5, sale.getCreated_at());
        stmt.setInt(6, sale.getId_sale());
        stmt.execute();
        return "Sale atualizada com sucesso!";
    }

    public String deletarSale(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM SALE WHERE ID_SALE=?");
        stmt.setInt(1, id);
        stmt.execute();
        return "Sale deletada com sucesso!";
    }

    public Sale buscarSale(int id) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALE WHERE ID_SALE=?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Sale sale = new Sale();
            sale.setId_sale(rs.getInt("ID_SALE"));
            sale.setFk_salesman(rs.getInt("FK_SALESMAN"));
            sale.setFk_customer(rs.getInt("FK_CUSTOMER"));
            sale.setIs_started(rs.getInt("IS_STARTED"));
            sale.setIs_finished(rs.getInt("IS_FINISHED"));
            sale.setCreated_at(rs.getDate("CREATED_AT"));
            return sale;
        }
        return null;
    }
}
