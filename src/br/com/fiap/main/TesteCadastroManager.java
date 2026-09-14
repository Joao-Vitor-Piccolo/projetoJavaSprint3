package br.com.fiap.main;

import br.com.fiap.dao.users.ManagerDao;
import br.com.fiap.entities.sale.Sale;
import br.com.fiap.entities.users.Customer;
import br.com.fiap.entities.users.Salesman;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TesteCadastroManager {

    private static final String[] OPCOES_PRINCIPAIS = {
            "Vendedor",
            "Venda",
            "Cliente",
            "Sair"
    };

    private static final String[] OPCOES_CRUD = {
            "Cadastrar",
            "Buscar por ID",
            "Listar todos",
            "Atualizar",
            "Excluir",
            "Voltar"
    };

    static String textoObrigatorio(String mensagem) {
        while (true) {
            String valor = JOptionPane.showInputDialog(mensagem);
            if (valor == null) {
                return null;
            }
            if (!valor.isBlank()) {
                return valor.trim();
            }
            JOptionPane.showMessageDialog(null, "O valor nao pode ficar vazio.");
        }
    }

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
        ManagerDao dao = new ManagerDao();
        try {
            boolean executando = true;
            while (executando) {
                int opcao = escolherOpcao(
                        "Manager",
                        "Escolha uma tela",
                        OPCOES_PRINCIPAIS);

                if (opcao == -1 || opcao == 3) {
                    executando = false;
                    continue;
                }

                try {
                    switch (opcao) {
                        case 0 -> menuSalesman(dao);
                        case 1 -> menuSale(dao);
                        case 2 -> menuCustomer(dao);
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

    private static void menuSalesman(ManagerDao dao) throws SQLException {
        boolean executando = true;
        while (executando) {
            int opcao = escolherOpcao("Vendedor", "Escolha uma operacao", OPCOES_CRUD);
            switch (opcao) {
                case 0 -> cadastrarSalesman(dao);
                case 1 -> buscarSalesman(dao);
                case 2 -> listarSalesmen(dao);
                case 3 -> atualizarSalesman(dao);
                case 4 -> deletarSalesman(dao);
                default -> executando = false;
            }
        }
    }

    private static void menuCustomer(ManagerDao dao) throws SQLException {
        boolean executando = true;
        while (executando) {
            int opcao = escolherOpcao("Cliente", "Escolha uma operacao", OPCOES_CRUD);
            switch (opcao) {
                case 0 -> cadastrarCustomer(dao);
                case 1 -> buscarCustomer(dao);
                case 2 -> listarCustomers(dao);
                case 3 -> atualizarCustomer(dao);
                case 4 -> deletarCustomer(dao);
                default -> executando = false;
            }
        }
    }

    private static void menuSale(ManagerDao dao) throws SQLException {
        boolean executando = true;
        while (executando) {
            int opcao = escolherOpcao("Venda", "Escolha uma operacao", OPCOES_CRUD);
            switch (opcao) {
                case 0 -> cadastrarSale(dao);
                case 1 -> buscarSale(dao);
                case 2 -> listarSales(dao);
                case 3 -> atualizarSale(dao);
                case 4 -> deletarSale(dao);
                default -> executando = false;
            }
        }
    }

    private static void cadastrarSalesman(ManagerDao dao) throws SQLException {
        String nome = textoObrigatorio("Nome do Salesman");
        if (nome == null) {
            return;
        }
        String cpf = textoObrigatorio("CPF do Salesman");
        if (cpf == null) {
            return;
        }
        String telefone = textoObrigatorio("Telefone do Salesman");
        if (telefone == null) {
            return;
        }

        Salesman salesman = new Salesman(nome, cpf, telefone);
        dao.inserirSalesman(salesman);
        exibirSucesso("Salesman cadastrado com ID " + salesman.getId());
    }

    private static void buscarSalesman(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID do Salesman");
        if (id == null) {
            return;
        }
        Salesman salesman = dao.buscarSalesman(id);
        exibirResultado(salesman == null
                ? "Salesman nao encontrado."
                : formatarSalesman(salesman));
    }

    private static void listarSalesmen(ManagerDao dao) throws SQLException {
        List<Salesman> salesmen = dao.listarSalesmen();
        if (salesmen.isEmpty()) {
            exibirResultado("Nenhum salesman cadastrado.");
            return;
        }
        StringBuilder resultado = new StringBuilder();
        for (Salesman salesman : salesmen) {
            resultado.append(formatarSalesman(salesman)).append('\n');
        }
        exibirResultado(resultado.toString());
    }

    private static void atualizarSalesman(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID do Salesman que sera atualizado");
        if (id == null) {
            return;
        }
        if (dao.buscarSalesman(id) == null) {
            exibirResultado("Salesman nao encontrado.");
            return;
        }

        String nome = textoObrigatorio("Novo nome do Salesman");
        if (nome == null) {
            return;
        }
        String cpf = textoObrigatorio("Novo CPF do Salesman");
        if (cpf == null) {
            return;
        }
        String telefone = textoObrigatorio("Novo telefone do Salesman");
        if (telefone == null) {
            return;
        }

        exibirResultado(dao.atualizarSalesman(new Salesman(id, nome, cpf, telefone)));
    }

    private static void deletarSalesman(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID do Salesman que sera excluido");
        if (id == null) {
            return;
        }
        Salesman salesman = dao.buscarSalesman(id);
        if (salesman == null) {
            exibirResultado("Salesman nao encontrado.");
            return;
        }
        if (confirmar("Excluir " + formatarSalesman(salesman) + "?")) {
            exibirResultado(dao.deletarSalesman(id));
        }
    }

    private static void cadastrarCustomer(ManagerDao dao) throws SQLException {
        String nome = textoObrigatorio("Nome do Customer");
        if (nome == null) {
            return;
        }
        String cpf = textoObrigatorio("CPF do Customer");
        if (cpf == null) {
            return;
        }
        String telefone = textoObrigatorio("Telefone do Customer");
        if (telefone == null) {
            return;
        }

        Customer customer = new Customer(nome, cpf, telefone);
        dao.inserirCustomer(customer);
        exibirSucesso("Customer cadastrado com ID " + customer.getId());
    }

    private static void buscarCustomer(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID do Customer");
        if (id == null) {
            return;
        }
        Customer customer = dao.buscarCustomer(id);
        exibirResultado(customer == null
                ? "Customer nao encontrado."
                : formatarCustomer(customer));
    }

    private static void listarCustomers(ManagerDao dao) throws SQLException {
        List<Customer> customers = dao.listarCustomers();
        if (customers.isEmpty()) {
            exibirResultado("Nenhum customer cadastrado.");
            return;
        }
        StringBuilder resultado = new StringBuilder();
        for (Customer customer : customers) {
            resultado.append(formatarCustomer(customer)).append('\n');
        }
        exibirResultado(resultado.toString());
    }

    private static void atualizarCustomer(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID do Customer que sera atualizado");
        if (id == null) {
            return;
        }
        if (dao.buscarCustomer(id) == null) {
            exibirResultado("Customer nao encontrado.");
            return;
        }

        String nome = textoObrigatorio("Novo nome do Customer");
        if (nome == null) {
            return;
        }
        String cpf = textoObrigatorio("Novo CPF do Customer");
        if (cpf == null) {
            return;
        }
        String telefone = textoObrigatorio("Novo telefone do Customer");
        if (telefone == null) {
            return;
        }

        exibirResultado(dao.atualizarCustomer(new Customer(id, nome, cpf, telefone)));
    }

    private static void deletarCustomer(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID do Customer que sera excluido");
        if (id == null) {
            return;
        }
        Customer customer = dao.buscarCustomer(id);
        if (customer == null) {
            exibirResultado("Customer nao encontrado.");
            return;
        }
        if (confirmar("Excluir " + formatarCustomer(customer) + "?")) {
            exibirResultado(dao.deletarCustomer(id));
        }
    }

    private static void cadastrarSale(ManagerDao dao) throws SQLException {
        Integer idSalesman = inteiro("ID do Salesman cadastrado");
        if (idSalesman == null) {
            return;
        }
        if (dao.buscarSalesman(idSalesman) == null) {
            JOptionPane.showMessageDialog(null, "Salesman nao encontrado.");
            return;
        }

        Integer idCustomer = inteiro("ID do Customer cadastrado");
        if (idCustomer == null) {
            return;
        }
        if (dao.buscarCustomer(idCustomer) == null) {
            JOptionPane.showMessageDialog(null, "Customer nao encontrado.");
            return;
        }

        Sale sale = new Sale(
                idSalesman,
                idCustomer,
                0,
                0,
                new Date(System.currentTimeMillis()));
        dao.inserirSale(sale);
        exibirSucesso("Sale cadastrada com ID " + sale.getId_sale());
    }

    private static void buscarSale(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID da Sale");
        if (id == null) {
            return;
        }
        Sale sale = dao.buscarSale(id);
        exibirResultado(sale == null
                ? "Sale nao encontrada."
                : formatarSale(sale));
    }

    private static void listarSales(ManagerDao dao) throws SQLException {
        List<Sale> sales = dao.listarSales();
        if (sales.isEmpty()) {
            exibirResultado("Nenhuma sale cadastrada.");
            return;
        }
        StringBuilder resultado = new StringBuilder();
        for (Sale sale : sales) {
            resultado.append(formatarSale(sale)).append('\n');
        }
        exibirResultado(resultado.toString());
    }

    private static void atualizarSale(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID da Sale que sera atualizada");
        if (id == null) {
            return;
        }
        Sale atual = dao.buscarSale(id);
        if (atual == null) {
            exibirResultado("Sale nao encontrada.");
            return;
        }

        Integer idSalesman = inteiro("Novo ID do Salesman");
        if (idSalesman == null) {
            return;
        }
        if (dao.buscarSalesman(idSalesman) == null) {
            exibirResultado("Salesman nao encontrado.");
            return;
        }

        Integer idCustomer = inteiro("Novo ID do Customer");
        if (idCustomer == null) {
            return;
        }
        if (dao.buscarCustomer(idCustomer) == null) {
            exibirResultado("Customer nao encontrado.");
            return;
        }

        Integer iniciada = escolherEstado("A sale esta iniciada?");
        if (iniciada == null) {
            return;
        }
        Integer finalizada = escolherEstado("A sale esta finalizada?");
        if (finalizada == null) {
            return;
        }
        if (finalizada == 1 && iniciada == 0) {
            exibirResultado("Uma sale finalizada tambem deve estar iniciada.");
            return;
        }

        Sale sale = new Sale(
                id,
                idSalesman,
                idCustomer,
                iniciada,
                finalizada,
                atual.getCreated_at());
        exibirResultado(dao.atualizarSale(sale));
    }

    private static void deletarSale(ManagerDao dao) throws SQLException {
        Integer id = inteiro("ID da Sale que sera excluida");
        if (id == null) {
            return;
        }
        Sale sale = dao.buscarSale(id);
        if (sale == null) {
            exibirResultado("Sale nao encontrada.");
            return;
        }
        if (confirmar("Excluir " + formatarSale(sale) + "?")) {
            exibirResultado(dao.deletarSale(id));
        }
    }

    private static int escolherOpcao(String titulo, String mensagem, String[] opcoes) {
        return JOptionPane.showOptionDialog(
                null,
                mensagem,
                titulo,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]);
    }

    private static Integer escolherEstado(String mensagem) {
        String[] opcoes = {"Nao", "Sim", "Cancelar"};
        int opcao = escolherOpcao("Estado da sale", mensagem, opcoes);
        if (opcao == 0) {
            return 0;
        }
        if (opcao == 1) {
            return 1;
        }
        return null;
    }

    private static boolean confirmar(String mensagem) {
        return JOptionPane.showConfirmDialog(
                null,
                mensagem,
                "Confirmacao",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private static String formatarSalesman(Salesman salesman) {
        return "ID " + salesman.getId()
                + " | nome=" + salesman.getName()
                + " | CPF=" + salesman.getCpf()
                + " | telefone=" + salesman.getPhone();
    }

    private static String formatarCustomer(Customer customer) {
        return "ID " + customer.getId()
                + " | nome=" + customer.getName()
                + " | CPF=" + customer.getCpf()
                + " | telefone=" + customer.getPhone();
    }

    private static String formatarSale(Sale sale) {
        return "ID " + sale.getId_sale()
                + " | salesman=" + sale.getFk_salesman()
                + " | customer=" + sale.getFk_customer()
                + " | iniciada=" + sale.getIs_started()
                + " | finalizada=" + sale.getIs_finished()
                + " | criada em=" + sale.getCreated_at();
    }

    private static void exibirResultado(String mensagem) {
        System.out.println(mensagem);
        JOptionPane.showMessageDialog(null, mensagem);
    }

    private static void exibirSucesso(String mensagem) {
        exibirResultado(mensagem);
    }
}
