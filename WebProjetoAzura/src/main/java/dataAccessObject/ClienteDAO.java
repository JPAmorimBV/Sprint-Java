package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexaoSQL.ConexaoBDD;
import models.Cliente;

public class ClienteDAO {

	public Connection minhaConexao;

	public ClienteDAO() throws ClassNotFoundException, SQLException {
		super();
		this.minhaConexao = new ConexaoBDD().conexao();
	}

	// Insere Cliente
	public void inserir(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO CLIENTE (nome, cpf, telefone, email, endereco, data_nasc, genero, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getNumCelular());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getEndereco());
			stmt.setString(6, cliente.getData_nasc());
			stmt.setString(7, cliente.getGenero());
			stmt.setString(8, cliente.getSenha());
			stmt.executeUpdate();
		}
	}

	// Deletar Cliente
	public String deletar(String cpf) throws SQLException {
		String sql = "DELETE FROM CLIENTE WHERE cpf = ?";
		try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
			stmt.setString(1, cpf);
			stmt.execute();
		}
		return "Deletado com Sucesso!";
	}

	// Atualizar Cliente
	public String atualizar(Cliente cliente) throws SQLException {
		String sql = "UPDATE CLIENTE SET nome = ?, telefone = ?, email = ?, endereco = ?, data_nasc = ?, genero = ?, senha = ?  WHERE cpf = ?";
		try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getNumCelular());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getCpf());
			stmt.setString(5, cliente.getEndereco());
			stmt.setString(6, cliente.getData_nasc());
			stmt.setString(7, cliente.getGenero());
			stmt.setString(8, cliente.getSenha());
			stmt.executeUpdate();
		}
		return "Atualizado com Sucesso!";
	}

	// seleciona cliente
	public Cliente buscarPorCpf(String cpf) throws SQLException {
		String sql = "SELECT * FROM CLIENTE WHERE cpf=?";
		try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
			stmt.setString(1, cpf);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Cliente(rs.getString("cpf"), 
							rs.getString("nome"), 
							rs.getString("telefone"),
							rs.getString("email"),
							rs.getString("endereco"),
							rs.getString("data_nasc"),
							rs.getString("genero"),
							rs.getString("Senha"));
				}
			}
		}
		return null; // Retorna null se o cliente não for encontrado
	}
	
	// seleciona cliente
		public Cliente buscarPorEmail(String email) throws SQLException {
			String sql = "SELECT * FROM CLIENTE WHERE email=?";
			try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
				stmt.setString(1, email);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						return new Cliente(rs.getString("cpf"), 
								rs.getString("nome"), 
								rs.getString("telefone"),
								rs.getString("email"),
								rs.getString("endereco"),
								rs.getString("data_nasc"),
								rs.getString("genero"),
								rs.getString("Senha"));
					}
				}
			}
			return null; // Retorna null se o cliente não for encontrado
		}
		
		public boolean validarLogin(String email, String senha) throws SQLException {
	        String sql = "SELECT * FROM CLIENTE WHERE email = ? AND senha = ?";
	        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
	            stmt.setString(1, email);
	            stmt.setString(2, senha);
	            try (ResultSet rs = stmt.executeQuery()) {
	                return rs.next();  // Retorna true se encontrar o cliente com email e senha correspondentes
	            }
	        }
	    }
	
}
