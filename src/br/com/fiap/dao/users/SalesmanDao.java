package br.com.fiap.dao.users;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.entities.users.Salesman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesmanDao {
    public Connection myConnection;

    public SalesmanDao() throws SQLException, ClassNotFoundException {
        this.myConnection = new ConnectionFactory().conn();
    }

    public List<Salesman> buscarPorNome(String nome) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALESMAN WHERE UPPER(NOME) = UPPER(?) ORDER BY ID_SALESMAN");
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        List<Salesman> salesmen = new ArrayList<>();
        while (rs.next()) {
            salesmen.add(new Salesman(
                    rs.getInt("ID_SALESMAN"),
                    rs.getString("NOME"),
                    rs.getString("CPF"),
                    rs.getString("TELEFONE")));
        }
        return salesmen;
    }

    public List<Sale> listarSales(Salesman salesUser) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALE WHERE FK_SALESMAN = ?");
        stmt.setInt(1, salesUser.getId());
        ResultSet rs = stmt.executeQuery();
        List<Sale> sales = new ArrayList<>();
        while (rs.next()) {
            sales.add(mapSale(rs));
        }
        return sales;
    }

    public Sale buscarSale(int idSale, int idSalesman) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "SELECT * FROM SALE WHERE ID_SALE = ? AND FK_SALESMAN = ?");
        stmt.setInt(1, idSale);
        stmt.setInt(2, idSalesman);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return mapSale(rs);
        }
        return null;
    }

    public String iniciarSale(int idSale, int idSalesman) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE SALE SET IS_STARTED = 1 "
                        + "WHERE ID_SALE = ? AND FK_SALESMAN = ? "
                        + "AND IS_STARTED = 0 AND IS_FINISHED = 0");
        stmt.setInt(1, idSale);
        stmt.setInt(2, idSalesman);
        int updated = stmt.executeUpdate();
        if (updated == 0) {
            return "Sale nao encontrada ou nao pode ser iniciada!";
        }
        return "Sale iniciada com sucesso!";
    }

    public String finalizarSale(int idSale, int idSalesman) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement(
                "UPDATE SALE SET IS_FINISHED = 1 "
                        + "WHERE ID_SALE = ? AND FK_SALESMAN = ? "
                        + "AND IS_STARTED = 1 AND IS_FINISHED = 0");
        stmt.setInt(1, idSale);
        stmt.setInt(2, idSalesman);
        int updated = stmt.executeUpdate();
        if (updated == 0) {
            return "Sale nao encontrada ou nao pode ser finalizada!";
        }
        return "Sale finalizada com sucesso!";
    }

    private Sale mapSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId_sale(rs.getInt("ID_SALE"));
        sale.setFk_salesman(rs.getInt("FK_SALESMAN"));
        sale.setFk_customer(rs.getInt("FK_CUSTOMER"));
        sale.setIs_started(rs.getInt("IS_STARTED"));
        sale.setIs_finished(rs.getInt("IS_FINISHED"));
        sale.setCreated_at(rs.getDate("CREATED_AT"));
        return sale;
    }
}
