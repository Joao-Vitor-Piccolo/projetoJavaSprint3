import br.com.fiap.entities.sale.BeforeSale;
import br.com.fiap.entities.users.Salesman;
import br.com.fiap.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class SalesmanDao {
    public Connection myConnection;

    public SalesmanDao() throws SQLException, ClassNotFoundException {
        this.myConnection = new ConnectionFactory().conn();
    }

    // Insert metodo para inserir no banco de dados
    public String readSale(Salesman salesUser) throws SQLException {
        PreparedStatement stmt = myConnection.prepareStatement("SELECT * FROM T_SALE");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            BeforeSale sale = new BeforeSale();

        }
        stmt.execute();
        return "Produto cadastrado com sucesso!";
    }
}
