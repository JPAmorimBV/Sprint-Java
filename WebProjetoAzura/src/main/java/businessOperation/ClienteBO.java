package businessOperation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;
import dataAccessObject.ClienteDAO;
import models.Cliente;

public class ClienteBO {

    private ClienteDAO clienteDao;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public ClienteBO(Connection connection) throws ClassNotFoundException, SQLException {
        this.clienteDao = new ClienteDAO();
    }

    // Validação do formato do email
    private boolean isEmailValido(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Método para adicionar um cliente com validações
    public void adicionarCliente(Cliente cliente) throws SQLException {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty() || !isEmailValido(cliente.getEmail())) {
            throw new IllegalArgumentException("O email do cliente é inválido.");
        }

        // Verificar se já existe um cliente com o mesmo CPF ou email
        if (buscarClientePorCpf(cliente.getCpf()) != null) {
            throw new IllegalArgumentException("Cliente com este CPF já está cadastrado.");
        }
        Cliente clienteExistentePorEmail = clienteDao.buscarPorEmail(cliente.getEmail());
        if (clienteExistentePorEmail != null) {
            throw new IllegalArgumentException("Cliente com este email já está cadastrado.");
        }

        clienteDao.inserir(cliente);
    }

    // Deletar cliente
    public void deletarCliente(String cpf) throws SQLException {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório para deletar um cliente.");
        }
        if (buscarClientePorCpf(cpf) == null) {
            throw new IllegalArgumentException("Cliente com este CPF não encontrado.");
        }
        clienteDao.deletar(cpf);
    }

    // Atualizar cliente
    public void atualizarCliente(Cliente cliente) throws SQLException {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if (buscarClientePorCpf(cliente.getCpf()) == null) {
            throw new IllegalArgumentException("Cliente com este CPF não encontrado.");
        }
        clienteDao.atualizar(cliente);
    }

    // Buscar cliente pelo CPF
    public Cliente buscarClientePorCpf(String cpf) throws SQLException {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF é obrigatório para buscar um cliente.");
        }
        return clienteDao.buscarPorCpf(cpf);
    }
    
    
    public boolean fazerLogin(String email, String senha) throws SQLException {
        if (email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Email e senha são obrigatórios.");
        }
        return clienteDao.validarLogin(email, senha);
    }
}
