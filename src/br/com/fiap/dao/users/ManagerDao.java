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
import java.util.ArrayList;
import java.util.List;

public class ManagerDao {

    public Connection myConnection;

    public ManagerDao() throws SQLException, ClassNotFoundException {
        this.myConnection = new ConnectionFactory().conn();
    }

    public String inserir(Manager managerUser) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO MANAGER (NOME, CPF, TELEFONE) VALUES (?,?,?)",
                new String[] { "ID_MANAGER" })) {
            stmt.setString(1, managerUser.getName());
            stmt.setString(2, managerUser.getCpf());
            stmt.setString(3, managerUser.getPhone());
            stmt.executeUpdate();
            managerUser.setId(obterIdGerado(stmt));
        }
        return "Manager cadastrado com sucesso!";
    }

    public String atualizar(Manager managerUser) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE MANAGER SET NOME=?, CPF=?, TELEFONE=? WHERE ID_MANAGER=?")) {
            stmt.setString(1, managerUser.getName());
            stmt.setString(2, managerUser.getCpf());
            stmt.setString(3, managerUser.getPhone());
            stmt.setInt(4, managerUser.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Manager atualizado com sucesso!"
                    : "Manager nao encontrado!";
        }
    }

    public String deletar(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM MANAGER WHERE ID_MANAGER=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Manager deletado com sucesso!"
                    : "Manager nao encontrado!";
        }
    }

    public Manager buscar(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM MANAGER WHERE ID_MANAGER=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Manager(
                            rs.getInt("ID_MANAGER"),
                            rs.getString("NOME"),
                            rs.getString("CPF"),
                            rs.getString("TELEFONE"));
                }
            }
        }
        return null;
    }

    public String inserirCustomer(Customer customer) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO CUSTOMER (NOME, CPF, TELEFONE) VALUES (?,?,?)",
                new String[] { "ID_CUSTOMER" })) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getPhone());
            stmt.executeUpdate();
            customer.setId(obterIdGerado(stmt));
        }
        return "Customer cadastrado com sucesso!";
    }

    public String atualizarCustomer(Customer customer) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE CUSTOMER SET NOME=?, CPF=?, TELEFONE=? WHERE ID_CUSTOMER=?")) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getCpf());
            stmt.setString(3, customer.getPhone());
            stmt.setInt(4, customer.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Customer atualizado com sucesso!"
                    : "Customer nao encontrado!";
        }
    }

    public String deletarCustomer(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM CUSTOMER WHERE ID_CUSTOMER=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Customer deletado com sucesso!"
                    : "Customer nao encontrado!";
        }
    }

    public Customer buscarCustomer(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM CUSTOMER WHERE ID_CUSTOMER=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapCustomer(rs);
                }
            }
        }
        return null;
    }

    public List<Customer> listarCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM CUSTOMER ORDER BY ID_CUSTOMER");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                customers.add(mapCustomer(rs));
            }
        }
        return customers;
    }

    public String inserirSalesman(Salesman salesman) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO SALESMAN (NOME, CPF, TELEFONE) VALUES (?,?,?)",
                new String[] { "ID_SALESMAN" })) {
            stmt.setString(1, salesman.getName());
            stmt.setString(2, salesman.getCpf());
            stmt.setString(3, salesman.getPhone());
            stmt.executeUpdate();
            salesman.setId(obterIdGerado(stmt));
        }
        return "Salesman cadastrado com sucesso!";
    }

    public String atualizarSalesman(Salesman salesman) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE SALESMAN SET NOME=?, CPF=?, TELEFONE=? WHERE ID_SALESMAN=?")) {
            stmt.setString(1, salesman.getName());
            stmt.setString(2, salesman.getCpf());
            stmt.setString(3, salesman.getPhone());
            stmt.setInt(4, salesman.getId());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Salesman atualizado com sucesso!"
                    : "Salesman nao encontrado!";
        }
    }

    public String deletarSalesman(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM SALESMAN WHERE ID_SALESMAN=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Salesman deletado com sucesso!"
                    : "Salesman nao encontrado!";
        }
    }

    public Salesman buscarSalesman(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALESMAN WHERE ID_SALESMAN=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapSalesman(rs);
                }
            }
        }
        return null;
    }

    public List<Salesman> listarSalesmen() throws SQLException {
        List<Salesman> salesmen = new ArrayList<>();
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALESMAN ORDER BY ID_SALESMAN");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                salesmen.add(mapSalesman(rs));
            }
        }
        return salesmen;
    }

    public String inserirSale(Sale sale) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "INSERT INTO SALE (FK_SALESMAN, FK_CUSTOMER, IS_STARTED, IS_FINISHED, CREATED_AT) VALUES (?,?,?,?,?)",
                new String[] { "ID_SALE" })) {
            stmt.setInt(1, sale.getFk_salesman());
            stmt.setInt(2, sale.getFk_customer());
            stmt.setInt(3, sale.getIs_started_value());
            stmt.setInt(4, sale.getIs_finished_value());
            stmt.setDate(5, sale.getCreated_at());
            stmt.executeUpdate();
            sale.setId_sale(obterIdGerado(stmt));
        }
        return "Sale cadastrada com sucesso!";
    }

    private int obterIdGerado(PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        throw new SQLException("O banco nao retornou o ID gerado.");
    }

    public String atualizarSale(Sale sale) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE SALE SET FK_SALESMAN=?, FK_CUSTOMER=?, IS_STARTED=?, IS_FINISHED=?, CREATED_AT=? WHERE ID_SALE=?")) {
            stmt.setInt(1, sale.getFk_salesman());
            stmt.setInt(2, sale.getFk_customer());
            stmt.setInt(3, sale.getIs_started_value());
            stmt.setInt(4, sale.getIs_finished_value());
            stmt.setDate(5, sale.getCreated_at());
            stmt.setInt(6, sale.getId_sale());
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Sale atualizada com sucesso!"
                    : "Sale nao encontrada!";
        }
    }

    public String deletarSale(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "DELETE FROM SALE WHERE ID_SALE=?")) {
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            return linhas > 0
                    ? "Sale deletada com sucesso!"
                    : "Sale nao encontrada!";
        }
    }

    public Sale buscarSale(int id) throws SQLException {
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALE WHERE ID_SALE=?")) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapSale(rs);
                }
            }
        }
        return null;
    }

    public List<Sale> listarSales() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        try (PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALE ORDER BY ID_SALE");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                sales.add(mapSale(rs));
            }
        }
        return sales;
    }

    private Customer mapCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("ID_CUSTOMER"),
                rs.getString("NOME"),
                rs.getString("CPF"),
                rs.getString("TELEFONE"));
    }

    private Salesman mapSalesman(ResultSet rs) throws SQLException {
        return new Salesman(
                rs.getInt("ID_SALESMAN"),
                rs.getString("NOME"),
                rs.getString("CPF"),
                rs.getString("TELEFONE"));
    }

    private Sale mapSale(ResultSet rs) throws SQLException {
        return new Sale(
                rs.getInt("ID_SALE"),
                rs.getInt("FK_SALESMAN"),
                rs.getInt("FK_CUSTOMER"),
                rs.getInt("IS_STARTED"),
                rs.getInt("IS_FINISHED"),
                rs.getDate("CREATED_AT"));
    }
}
