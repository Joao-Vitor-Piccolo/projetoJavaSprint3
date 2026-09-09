package br.com.fiap.main;

import br.com.fiap.dao.ProdutoDao;
import br.com.fiap.entities.Produto;

import javax.swing.*;
import java.sql.SQLException;

public class TesteCadastroProduto {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j){
        return  Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    static double real(String j){
        return Double.parseDouble( JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ProdutoDao dao = new ProdutoDao();

        Produto objProduto = new Produto();

        objProduto.setCodigo(inteiro("Codigo"));
        objProduto.setTipo(texto("Tipo do Produto"));
        objProduto.setMarca(texto("Marca"));
        objProduto.setPreco(real("Preço"));

        System.out.println(dao.inserir(objProduto));

    }

}
