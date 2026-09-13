package br.com.fiap.main;

import br.com.fiap.dao.users.SalesmanDao;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.entities.users.Salesman;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class TesteCadastroSalesman {

    static String texto(String j) {
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j) {
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        SalesmanDao dao = new SalesmanDao();

        Salesman salesman = new Salesman(
                inteiro("ID do Salesman"),
                texto("Nome do Salesman"),
                texto("CPF do Salesman"),
                texto("Telefone do Salesman"));

        List<Sale> sales = dao.listarSales(salesman);
        System.out.println("Sales encontradas: " + sales.size());
        for (Sale sale : sales) {
            System.out.println("Sale " + sale.getId_sale()
                    + " | customer=" + sale.getFk_customer()
                    + " | started=" + sale.getIs_started()
                    + " | finished=" + sale.getIs_finished());
        }

        int idSale = inteiro("ID da Sale para iniciar/finalizar");
        System.out.println(dao.iniciarSale(idSale, salesman.getId()));

        Sale started = dao.buscarSale(idSale, salesman.getId());
        if (started != null) {
            System.out.println("Apos iniciar | started=" + started.getIs_started()
                    + " | finished=" + started.getIs_finished());
        }

        System.out.println(dao.finalizarSale(idSale, salesman.getId()));

        Sale finished = dao.buscarSale(idSale, salesman.getId());
        if (finished != null) {
            System.out.println("Apos finalizar | started=" + finished.getIs_started()
                    + " | finished=" + finished.getIs_finished());
        }
    }
}
