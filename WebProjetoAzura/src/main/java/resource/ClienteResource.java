package resource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import businessOperation.ClienteBO;
import conexaoSQL.ConexaoBDD;
import models.Cliente;
import models.ClienteCadastroDTO;

@Path("/clientes")
public class ClienteResource {

	 @POST
	    @Path("/cadastro")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response cadastrarCliente(ClienteCadastroDTO cadastroDTO) {
	        String senha = cadastroDTO.getSenha();
	        String confirmarSenha = cadastroDTO.getConfirmarSenha();
	        
	        // Lógica para verificar se as senhas coincidem
	        if (!senha.equals(confirmarSenha)) {
	            return Response.status(Response.Status.BAD_REQUEST).entity("As senhas não coincidem.").build();
	        }
	        return Response.status(Response.Status.CREATED).entity("Cliente cadastrado com sucesso!").build();

		
	}

	@POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        try (Connection connection = new ConexaoBDD().conexao()) {
            ClienteBO clienteBO = new ClienteBO(connection);

            boolean loginValido = clienteBO.fazerLogin(loginRequest.getEmail(), loginRequest.getSenha());

            if (loginValido) {
                return Response.ok("Login bem-sucedido.").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciais inválidas.").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao processar o login.").build();
        }
    }

	@GET
	@Path("/{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarCliente(@PathParam("cpf") String cpf) {
		try (Connection connection = new ConexaoBDD().conexao()) {
			ClienteBO clienteBo = new ClienteBO(connection);
			Cliente cliente = clienteBo.buscarClientePorCpf(cpf);
			if (cliente == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado.").build();
			}
			return Response.ok(cliente).build();
		} catch (SQLException | ClassNotFoundException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar cliente.").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarCliente(Cliente cliente) {
		try (Connection connection = new ConexaoBDD().conexao()) {
			ClienteBO clienteBo = new ClienteBO(connection);
			clienteBo.adicionarCliente(cliente);
			return Response.status(Response.Status.CREATED).entity("Cliente adicionado com sucesso.").build();
		} catch (SQLException | ClassNotFoundException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar cliente.").build();
		}
	}

	@PUT
	@Path("/{cpf}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarCliente(@PathParam("cpf") String cpf, Cliente cliente) {
		try (Connection connection = new ConexaoBDD().conexao()) {
			ClienteBO clienteBo = new ClienteBO(connection);
			cliente.setCpf(cpf);
			clienteBo.atualizarCliente(cliente);
			return Response.ok("Cliente atualizado com sucesso.").build();
		} catch (SQLException | ClassNotFoundException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar cliente.").build();
		}
	}

	@DELETE
	@Path("/{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletarCliente(@PathParam("cpf") String cpf) {
		try (Connection connection = new ConexaoBDD().conexao()) {
			ClienteBO clienteBo = new ClienteBO(connection);
			clienteBo.deletarCliente(cpf);
			return Response.ok("Cliente deletado com sucesso.").build();
		} catch (SQLException | ClassNotFoundException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar cliente.").build();
		}
	}
}