package dataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoSQL.ConexaoBDD;
import models.Cliente;
import models.Veiculo;

public class VeiculoDAO {
	private Connection minhaConexao;

	public VeiculoDAO() throws ClassNotFoundException, SQLException {
		this.minhaConexao = new ConexaoBDD().conexao();
	}

	// Insere Veículo
	public void inserir(Veiculo veiculo, String cpf) throws SQLException {
		String sqlCPF = "SELECT id_cliente FROM CLIENTE WHERE cpf = ?";
		String sql = "INSERT INTO VEICULO (id_veiculo, marca, modelo, cor, quilometragem, cliente_id_cliente) VALUES (?, ?, ?, ?, ?, ?)";

		minhaConexao.setAutoCommit(false);
		try (PreparedStatement buscaCPF = minhaConexao.prepareStatement(sqlCPF)) {
			buscaCPF.setString(1, cpf);
			ResultSet cpfCliente = buscaCPF.executeQuery();

			if (cpfCliente.next()) {
				int idCliente = cpfCliente.getInt(1);

				try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
					stmt.setString(1, veiculo.getPlaca());
					stmt.setString(2, veiculo.getMarca());
					stmt.setString(3, veiculo.getModelo());
					stmt.setString(4, veiculo.getCor());
					stmt.setInt(5,veiculo.getQuilometragem());
					stmt.setInt(6, idCliente);
					stmt.executeUpdate();
					minhaConexao.commit();
				}
			} else {
				System.out.println("Cliente não encontrado com o CPF: " + cpf);
			}
		} catch (SQLException e) {
			minhaConexao.rollback();
			throw e;
		} finally {
			minhaConexao.setAutoCommit(true);
		}
	}

	// Deletar Veículo
	public String deletar(String placa) throws SQLException {
		String sql = "DELETE FROM VEICULO WHERE id_veiculo = ?";
		try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
			stmt.setString(1, placa);
			stmt.execute();
			return "Deletado com Sucesso!";
		}
	}

	// Atualizar Veículo
	public String atualizar(Veiculo veiculo) throws SQLException {
		String sql = "UPDATE VEICULO SET modelo = ?, marca = ?, cor = ?, quilometragem = ? WHERE id_veiculo = ?";
		try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
			stmt.setString(1, veiculo.getModelo());
			stmt.setString(2, veiculo.getMarca());
			stmt.setString(3, veiculo.getCor());
			stmt.setInt(4, veiculo.getQuilometragem());
			stmt.setString(5, veiculo.getPlaca());
			
			stmt.executeUpdate();
			return "Atualizado com Sucesso!";
		}
	}

	// Selecionar Veículos
	public Veiculo buscaPlacaNoBanco(String placa) throws SQLException {
		String comandoSQL = """
				    SELECT id_veiculo, marca, modelo, cor, quilometragem
				    FROM VEICULO
				    WHERE id_veiculo = ?
				""";

		try (PreparedStatement stmt = minhaConexao.prepareStatement(comandoSQL)) {
			stmt.setString(1, placa); // Define o parâmetro da placa no SQL

			try (ResultSet resultado = stmt.executeQuery()) {
				if (resultado.next()) {
					// Cria um novo objeto Veiculo e define os valores recuperados
					Veiculo veiculo = new Veiculo();
					veiculo.setPlaca(resultado.getString("placa"));
					veiculo.setMarca(resultado.getString("marca"));
					veiculo.setModelo(resultado.getString("modelo"));
					veiculo.setCor(resultado.getString("Cor"));
					veiculo.setQuilometragem(resultado.getInt("Quilometragem"));

					return veiculo; // Retorna o veículo encontrado
				} else {
					System.out.println("Nenhum veículo encontrado para a placa fornecida.");
					return null;
				}
			}
		}
	}

	public List<Veiculo> listarVeiculosPorCliente(String clienteCpf) throws SQLException, ClassNotFoundException {
		List<Veiculo> veiculos = new ArrayList<>();
		String sql = "SELECT * FROM CLIENTE WHERE cliente_id_cliente = ?";

		// Cria uma instância de ClienteDAO para obter o cliente associado
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente proprietario = clienteDAO.buscarPorCpf(clienteCpf); // Método para buscar Cliente por CPF

		if (proprietario == null) {
			System.out.println("Cliente não encontrado para o CPF fornecido.");
			return veiculos;
		}

		try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
			stmt.setString(1, clienteCpf);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Veiculo veiculo = new Veiculo(rs.getString("placa"), rs.getString("modelo"), rs.getString("marca"),
							rs.getString("cor"),rs.getInt("quilometragem"), proprietario // Define o cliente como proprietário do veículo
					);
					veiculos.add(veiculo);
				}
			}
		}

		return veiculos;
	}

}
