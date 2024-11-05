package businessOperation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dataAccessObject.VeiculoDAO;
import models.Veiculo;

public class VeiculoBO {

	private VeiculoDAO veiculodao;
	
	public VeiculoBO(Connection connection) throws ClassNotFoundException, SQLException {
		 this.veiculodao = new VeiculoDAO();
	}


	public void inserirVeiculo(Veiculo veiculo) throws SQLException, ClassNotFoundException {
		if (veiculo == null) {
			throw new IllegalArgumentException("O veículo não pode ser nulo.");
		}
		if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
			throw new IllegalArgumentException("A placa do veículo é obrigatória.");
		}
		if (veiculo.getProprietario() == null || veiculo.getProprietario().getCpf().isEmpty()) {
			throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        
        if (buscarVeiculoPorPlaca(veiculo.getPlaca()) != null) {
            throw new IllegalArgumentException("Veículo com esta placa já está cadastrado.");
        }
        veiculodao.inserir(veiculo,veiculo.getProprietario().getCpf());
	}

	
    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        if (veiculo == null) {
            throw new IllegalArgumentException("O veículo não pode ser nulo.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("A placa do veículo é obrigatória.");
        }
        
        if (buscarVeiculoPorPlaca(veiculo.getPlaca()) == null) {
            throw new IllegalArgumentException("Veículo com esta placa não encontrado.");
        }
        veiculodao.atualizar(veiculo);
    }
	
    
    public void deletarVeiculo(String placa) throws SQLException {
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("A placa é obrigatória para deletar um veículo.");
        }
        
        if (buscarVeiculoPorPlaca(placa) == null) {
            throw new IllegalArgumentException("Veículo com esta placa não encontrado.");
        }
        veiculodao.deletar(placa);
    }

    
    
    
    public List<Veiculo> listarVeiculosPorCliente(String clienteCpf) throws SQLException, ClassNotFoundException {
        if (clienteCpf == null || clienteCpf.isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório para listar veículos.");
        }
        return veiculodao.listarVeiculosPorCliente(clienteCpf);
    }
    
    
	public Veiculo buscarVeiculoPorPlaca(String placa) throws SQLException {
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("A placa é obrigatória para buscar um veículo.");
        }
        return veiculodao.buscaPlacaNoBanco(placa);
    }

	
	
    

}
