package br.com.fiap.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // metodo de conexão com o banco de dados
    public Connection conn() throws ClassNotFoundException, SQLException {
        // Driver oracle
        Class.forName("oracle.jdbc.driver.OracleDriver");
        // Retornar conexão
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                "RM565127",
                "130606");
    }
}
