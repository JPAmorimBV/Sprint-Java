package models;

public class Segurado extends Veiculo{
    public String infoBeneficios = """
    \n\nBenefícios:
    - Guincho ilimitado 24hrs
    - Desconto para estacionar
    - Serviços residenciais
    - Crédito em app de transporte
    """;

    public String infoServicos = """
    Serviços gratuitos para o veículo:
    - Troca de lâmpadas
    - Diagnósticos e reparo de pneu
    """;

    public String infoCoberturas = """
    Coberturas:
    - Roubo e furto
    - Perda total
    - Danos a terceiros
    - Ocupantes do veículo
    - Proteção aos vidros
    - Incêndio
    - ColisãoS
    - Veículo reserva
    - Cabos de carregamento
    """;

    
    public Segurado() {
		super();
	}

    

    public Segurado(String infoBeneficios, String infoServicos, String infoCoberturas) {
		super();
		this.infoBeneficios = infoBeneficios;
		this.infoServicos = infoServicos;
		this.infoCoberturas = infoCoberturas;
	}



	public String exibirInfoSeguro(){
        System.out.println(infoBeneficios + "\n" + infoCoberturas + "\n" + infoServicos);
        return null;
    }

}
