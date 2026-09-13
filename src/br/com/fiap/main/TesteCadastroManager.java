package br.com.fiap.main;

import br.com.fiap.dao.users.ManagerDao;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.entities.users.Customer;
import br.com.fiap.entities.users.Salesman;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;

public class TesteCadastroManager {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ManagerDao dao = new ManagerDao();

        Customer customer = new Customer(
                inteiro("ID do Customer"),
                texto("Nome do Customer"),
                texto("CPF do Customer"),
                texto("Telefone do Customer"));
        System.out.println(dao.inserirCustomer(customer));

        Salesman salesman = new Salesman(
                inteiro("ID do Salesman"),
                texto("Nome do Salesman"),
                texto("CPF do Salesman"),
                texto("Telefone do Salesman"));
        System.out.println(dao.inserirSalesman(salesman));
        Sale sale = new Sale(
                inteiro("ID da Sale"),
                salesman.getId(),
                customer.getId(),
                0,
                0,
                new Date(System.currentTimeMillis()));
        System.out.println(dao.inserirSale(sale));

    }
}
