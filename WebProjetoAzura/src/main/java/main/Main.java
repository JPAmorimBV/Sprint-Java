package main;

import java.sql.SQLException;
import java.util.Scanner;

import dataAccessObject.ClienteDAO;
import dataAccessObject.VeiculoDAO;
import models.Autodiagnostico;
import models.Cliente;
import models.Segurado;
import models.Veiculo;

public class Main {
    
    private static Cliente cadastrarUsuarioEVeiculo(Scanner scanner, VeiculoDAO veiculoDAO, ClienteDAO clienteDAO) throws SQLException {
        // Criar novos objetos
        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        
        // Cadastro do cliente
        System.out.print("\nMe informe seu CPF: ");
        String cpf = scanner.nextLine();
        cliente.setCpf(cpf);
              
        System.out.print("Me informe seu nome: ");
        String nome = scanner.nextLine();
        cliente.setNome(nome);

        System.out.print("\nMe informe seu número de celular: ");
        String numCelular = scanner.nextLine();
        cliente.setNumCelular(numCelular);

        System.out.print("\nMe informe seu e-mail: ");
        String email = scanner.nextLine();
        cliente.setEmail(email);
        
        System.out.print("\nMe informe seu endereço: ");
        String endereco = scanner.nextLine();
        cliente.setEndereco(endereco);
        
        
        System.out.print("\nMe informe seu data de nascimento (YYYY-MM-DD): ");
        String data_nasc = scanner.nextLine();
        cliente.setData_nasc(data_nasc);
        
        System.out.print("\nMe informe seu gênero: ");
        String genero = scanner.nextLine();
        cliente.setGenero(genero);
        
        System.out.print("\nCrie uma senha: ");
        String senha = scanner.nextLine();
        cliente.setSenha(senha);

        
        clienteDAO.inserir(cliente);
        

        // Cadastro do veículo
        System.out.print("\nInforme a placa do carro: ");
        String placa = scanner.nextLine();
        veiculo.setPlaca(placa);
        
        System.out.print("\nInforme a marca do carro: ");
        String marca = scanner.nextLine();
        veiculo.setMarca(marca);

        System.out.print("\nInforme o modelo do carro: ");
        String modelo = scanner.nextLine();
        veiculo.setModelo(modelo);

        System.out.print("\nInforme a cor do carro: ");
        String cor = scanner.nextLine();
        veiculo.setCor(cor);
        
        System.out.print("\nInforme a quilometragem do carro: ");
        int quilometragem = scanner.nextInt();
        veiculo.setQuilometragem(quilometragem);
        
        scanner.nextLine(); 
        
       
        veiculoDAO.inserir(veiculo, cpf);
        System.out.println("Cadastro realizado com sucesso!");
        
        return cliente;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        boolean executando = true;
        Scanner scanner = new Scanner(System.in);
        
        
        Cliente cliente01 = null;  
        Veiculo veiculo01 = new Veiculo();  
        Segurado segurado = new Segurado();  
        Autodiagnostico autodiagnostico = new Autodiagnostico();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ClienteDAO clienteDAO = new ClienteDAO(); 
        
        while (executando) {
            exibirMenu();

            try {
                System.out.print("=> ");
                int opcao = scanner.nextInt();
                scanner.nextLine();  

                switch (opcao) {
                    case 1:
                        System.out.println(segurado.exibirInfoSeguro());
                        break;
                        
                    case 2:
                        if (cliente01 != null) {
                            gerenciarInformacoesUsuario(scanner, cliente01, veiculo01);
                        } else {
                            oferecerCadastro(scanner, veiculoDAO, clienteDAO);
                        }
                        break;
                        
                    case 3:
                        cliente01 = cadastrarUsuarioEVeiculo(scanner, veiculoDAO, clienteDAO);
                        break;
                        
                    case 4:
                        System.out.println(autodiagnostico.autodiag());
                        break;
                        
                    case 5:
                        System.out.println("Sessão finalizada com sucesso! Até logo!");
                        executando = false;
                        break;
                        
                    default:
                        System.out.println("Entrada inválida. Tente novamente.");
                        break;
                }
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        String menu = """
                    Bom dia, como posso ajudar?
                    
                    [1] Informações do seguro
                    [2] Minhas informações
                    [3] Cadastre-se 
                    [4] Problemas com o veículo
                    [5] Sair
                    
                """;
        System.out.println(menu);
    }

    private static void gerenciarInformacoesUsuario(Scanner scanner, Cliente cliente, Veiculo veiculo) {
        System.out.print(cliente.exibirDados() + veiculo.exibirDados());
        System.out.println("""
                Deseja alterar alguma informação?
                
                [1] Sim, os dados estão incorretos
                [2] Não, os dados estão corretos
                """);
        
        System.out.print("=> ");
        int opcaoAlteraDados = scanner.nextInt();
        scanner.nextLine();
        
        if (opcaoAlteraDados == 1) {
            alterarDados(scanner, cliente);
        }
    }

    private static void alterarDados(Scanner scanner, Cliente cliente) {
        System.out.println("""
            Selecione o dado que deseja alterar
            
            [1] Nome
            [2] CPF
            
            """);
        System.out.print("=> ");
        int opcaoSelecionaDado = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoSelecionaDado) {
            case 1:
                System.out.print("Informe novo nome: ");
                cliente.setNome(scanner.nextLine());
                break;
            case 2:
                System.out.print("Informe novo CPF: ");
                cliente.setCpf(scanner.nextLine());
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    private static void oferecerCadastro(Scanner scanner, VeiculoDAO veiculoDAO, ClienteDAO clienteDAO) throws SQLException {
        System.out.println("""
                Você ainda não se cadastrou 
                
                Deseja se cadastrar ?
                
                [1] Sim 
                [2] Não
                """);
        System.out.print("=> ");
        int opcaoSeCadastrar = scanner.nextInt();
        scanner.nextLine();
        
        if (opcaoSeCadastrar == 1) {
            cadastrarUsuarioEVeiculo(scanner, veiculoDAO, clienteDAO);
        }
    }
}