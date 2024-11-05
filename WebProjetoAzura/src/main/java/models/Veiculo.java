package models; 

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private int quilometragem;
    private Cliente proprietario;
    
	public Veiculo() {
		super();
	}

	

	public Veiculo(String placa, String marca, String modelo, String cor, int quilometragem, Cliente proprietario) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cor = cor;
		this.quilometragem = quilometragem;
		this.proprietario = proprietario;
	}



	public String exibirDados() {
		return "\n" + "Placa: " + getPlaca() + "\n" + "Marca: " + getMarca() + "\n" + "Modelo: " + getModelo() + "\n"
				+ "Cor: " + getCor() + "Quilometragem: "+ getQuilometragem()+"\n\n";
	}

	public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Cliente getProprietario() { 
        return proprietario;
    }

    public void setProprietario(Cliente proprietario) { 
        this.proprietario = proprietario;
    }


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(int quilometragem) {
		this.quilometragem = quilometragem;
	}





}
