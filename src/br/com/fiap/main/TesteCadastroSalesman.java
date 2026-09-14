package br.com.fiap.main;

import br.com.fiap.dao.users.SalesmanDao;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.entities.users.Salesman;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class TesteCadastroSalesman {

    private static final String[] OPCOES = {
            "Ler sales",
            "Iniciar/finalizar sale",
            "Sair"
    };

    static Integer inteiro(String mensagem) {
        while (true) {
            String valor = JOptionPane.showInputDialog(mensagem);
            if (valor == null) {
                return null;
            }
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Informe um numero inteiro valido.");
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SalesmanDao dao = new SalesmanDao();
        try {
            Salesman salesman = selecionarSalesman(dao);
            if (salesman == null) {
                return;
            }

            boolean executando = true;
            while (executando) {
                int opcao = JOptionPane.showOptionDialog(
                        null,
                        "Salesman selecionado: " + salesman.getName()
                                + " (ID " + salesman.getId() + ")",
                        "Operacoes de sale",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        OPCOES,
                        OPCOES[0]);

                if (opcao == -1 || opcao == 2) {
                    executando = false;
                    continue;
                }

                try {
                    switch (opcao) {
                        case 0 -> listarSales(dao, salesman);
                        case 1 -> alterarEstadoSale(dao, salesman);
                        default -> {
                        }
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Nao foi possivel concluir a operacao: " + e.getMessage(),
                            "Erro no banco",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } finally {
            dao.myConnection.close();
        }
    }

    private static Salesman selecionarSalesman(SalesmanDao dao) throws SQLException {
        while (true) {
            String nome = JOptionPane.showInputDialog("Nome do Salesman cadastrado");
            if (nome == null) {
                return null;
            }
            if (nome.isBlank()) {
                JOptionPane.showMessageDialog(null, "Informe um nome.");
                continue;
            }

            List<Salesman> encontrados = dao.buscarPorNome(nome.trim());
            if (encontrados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum salesman encontrado com esse nome.");
                continue;
            }

            StringBuilder lista = new StringBuilder("Salesmen encontrados:\n");
            for (Salesman salesman : encontrados) {
                lista.append("ID ")
                        .append(salesman.getId())
                        .append(" | ")
                        .append(salesman.getName())
                        .append('\n');
            }

            Integer idEscolhido = inteiro(lista + "\nInforme o ID desejado");
            if (idEscolhido == null) {
                return null;
            }
            for (Salesman salesman : encontrados) {
                if (salesman.getId() == idEscolhido) {
                    return salesman;
                }
            }
            JOptionPane.showMessageDialog(
                    null,
                    "O ID informado nao pertence aos salesmen listados.");
        }
    }

    private static void listarSales(SalesmanDao dao, Salesman salesman) throws SQLException {
        List<Sale> sales = dao.listarSales(salesman);
        StringBuilder resultado = new StringBuilder("Sales encontradas: ")
                .append(sales.size())
                .append('\n');

        for (Sale sale : sales) {
            resultado.append(formatarSale(sale)).append('\n');
        }

        System.out.println(resultado);
        JOptionPane.showMessageDialog(null, resultado.toString());
    }

    private static void alterarEstadoSale(SalesmanDao dao, Salesman salesman)
            throws SQLException {
        Integer idSale = inteiro("ID da Sale para iniciar/finalizar");
        if (idSale == null) {
            return;
        }

        Sale sale = dao.buscarSale(idSale, salesman.getId());
        if (sale == null) {
            JOptionPane.showMessageDialog(null, "Sale nao encontrada para este salesman.");
            return;
        }

        String resultado;
        if (sale.getIs_finished()) {
            resultado = "A sale ja foi finalizada.";
        } else if (!sale.getIs_started()) {
            resultado = dao.iniciarSale(idSale, salesman.getId());
        } else {
            resultado = dao.finalizarSale(idSale, salesman.getId());
        }

        Sale atualizada = dao.buscarSale(idSale, salesman.getId());
        if (atualizada != null) {
            resultado += "\n" + formatarSale(atualizada);
        }
        System.out.println(resultado);
        JOptionPane.showMessageDialog(null, resultado);
    }

    private static String formatarSale(Sale sale) {
        return "Sale " + sale.getId_sale()
                + " | customer=" + sale.getFk_customer()
                + " | started=" + sale.getIs_started()
                + " | finished=" + sale.getIs_finished();
    }
}
