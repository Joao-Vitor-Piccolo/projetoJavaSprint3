package br.com.fiap.dao;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoDao {

    public Connection minhaConexao;

    public ProdutoDao() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Insert metodo para inserir no banco de dados
    public String inserir(Produto produto) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement("Insert into T_FIAP_PRODUTO values (?,?,?,?)");
        stmt.setInt(1, produto.getCodigo());
        stmt.setString(2, produto.getTipo());
        stmt.setString(3, produto.getMarca());
        stmt.setDouble(4, produto.getPreco());
        stmt.execute();

        return "Produto cadastrado com sucesso!";
    }
}
