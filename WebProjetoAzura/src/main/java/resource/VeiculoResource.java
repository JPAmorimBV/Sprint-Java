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

import businessOperation.VeiculoBO;
import conexaoSQL.ConexaoBDD;
import models.Veiculo;

@Path("/veiculos")
public class VeiculoResource {

    @GET
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVeiculo(@PathParam("placa") String placa) {
        try (Connection connection = new ConexaoBDD().conexao()) {
            VeiculoBO veiculoBo = new VeiculoBO(connection);
            Veiculo veiculo = veiculoBo.buscarVeiculoPorPlaca(placa);
            if (veiculo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado.").build();
            }
            return Response.ok(veiculo).build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar veículo.").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarVeiculo(Veiculo veiculo) {
        try (Connection connection = new ConexaoBDD().conexao()) {
        	VeiculoBO veiculoBo = new VeiculoBO(connection);
            veiculoBo.inserirVeiculo(veiculo);
            return Response.status(Response.Status.CREATED).entity("Veículo adicionado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar veículo.").build();
        }
    }

    @PUT
    @Path("/{placa}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(@PathParam("placa") String placa, Veiculo veiculo) {
        try (Connection connection = new ConexaoBDD().conexao()) {
        	VeiculoBO veiculoBo = new VeiculoBO(connection);
            veiculo.setPlaca(placa); // Definindo a placa do veículo com o valor do caminho da URL
            veiculoBo.atualizarVeiculo(veiculo);
            return Response.ok("Veículo atualizado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar veículo.").build();
        }
    }

    @DELETE
    @Path("/{placa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarVeiculo(@PathParam("placa") String placa) {
        try (Connection connection = new ConexaoBDD().conexao()) {
        	VeiculoBO veiculoBo = new VeiculoBO(connection);
            veiculoBo.deletarVeiculo(placa);
            return Response.ok("Veículo deletado com sucesso.").build();
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar veículo.").build();
        }
    }
}
