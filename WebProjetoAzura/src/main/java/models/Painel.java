package models;

import java.util.Scanner;

public class Painel {
    private String mensagem;
    private String verde;
    private String amarelo;
    private String vermelho;

    Scanner scanner = new Scanner(System.in);

    public String exibir(){
        System.out.print(getMensagem());
        String cor = scanner.nextLine();
        if (cor.equals("1")){
            return getVerde();
        } else if (cor.equals("2")){
            return getAmarelo();
        } else if (cor.equals("3")){
            return getVermelho();
        } else {
            return "Opção inválida";
        }
    }

    public String getMensagem() {
        mensagem = "Tabela das cores do painel:\n" +
                "\n" +
                "[1] Verde/Azul = Equipamento ligado\n" +
                "[2] Amarelo = Falha moderada\n" +
                "[3] Vermelha = Falha grave\n" +
                "=>";
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getVerde() {
        verde = "Sinais:                   As causas são:\n" +
                "\n" +
                "1. Faróis                 - Indicam o acionamento dos faróis\n" +
                "2. Indicadores de direção - Acionam a seta";
        return verde;
    }

    public void setVerde(String verde) {
        this.verde = verde;
    }

    public String getAmarelo() {
        amarelo = "Sinais:                 As causas são:\n" +
                "\n" +
                "1. Bomba de combustível - Combustível na reserva \n" +
                "2. ABS                  - Falha no sistema ABS\n" +
                "3. EPC                  - Problema na central eletrônica\n" +
                "4. EPS/Volante          - Problema na direção elétrica\n" +
                "5. ESC                  - ESC ligado ou, se permanecer no painel, com defeito\n" +
                "6. TPMS                 - Pneu descalibrado ";
        return amarelo;
    }

    public void setAmarelo(String amarelo) {
        this.amarelo = amarelo;
    }

    public String getVermelho() {
        vermelho = "Sinais:                 As causas são:\n" +
                "\n" +
                "1. Bateria              - Mau funcionamento do sistema elétrico \n" +
                "2. Câmbio               - Defeito no sistema ou baixo nível do óleo do câmbio\n" +
                "3. Cinto de segurança   - Ocupantes do veículo sem cinto\n" +
                "4. P/!/BREAK            - Freio de mão acionado ou, caso desativado, indica desgaste dos componentes\n" +
                " 5. Chave/CODE           - Chave codificada e/ou o receptor não estão funcionando corretamente\n" +
                "6. Óleo                 - Baixo nível de óleo ou falhas na lubrificação do motor\n" +
                "7. Temperatura do motor - Líquido de refrigeração está prestes a ferver";
        return vermelho;
    }

    public void setVermelho(String vermelho) {
        this.vermelho = vermelho;
    }
}
