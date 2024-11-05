package models;

public class Cliente {
	private String nome;
	private String cpf;
	private String numCelular;
	private String email;
	private String endereco;
	private String data_nasc;
	private String genero;
	private String senha;

	

	public Cliente(String nome, String cpf, String numCelular, String email, String endereco, String data_nasc,
			String genero, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.numCelular = numCelular;
		this.email = email;
		this.endereco = endereco;
		this.data_nasc = data_nasc;
		this.genero = genero;
		this.senha = senha;
	}

	public Cliente() {
		super();
	}

	public String exibirDados() {
		String msg = "Seus dados são: \n\n" + "Nome: " + getNome() + "\n" + "CPF: " + getCpf() + "\n"
				+ "Número de celular: " + getNumCelular() + "\n" + "E-mail: " + getEmail() + "\n";
		return msg;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
