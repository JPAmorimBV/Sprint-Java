package models;

import java.util.Scanner;

public class Tremores {
    private String relatorio;
    private String parado;
    private String andando;
    private String aceleracao;
    private String frenagem;
    private String baixaVelocidade;
    private String altaVelocidade;

    Scanner scanner = new Scanner(System.in);

    public String exibir(){
        System.out.print(getRelatorio());
        String opcao = scanner.nextLine();
        if (opcao.equals("1")){
            return getParado();
        } else if (opcao.equals("2")){
            System.out.println(getAndando());
            String velocidade = scanner.nextLine();
            if (velocidade.equals("1")){
                return getBaixaVelocidade();
            } else if (velocidade.equals("2")) {
                return getAltaVelocidade();
            } else {
                return "Opção inválida";
            }
        } else if (opcao.equals("3")) {
            return getAceleracao();
        } else if (opcao.equals("4")) {
            return getFrenagem();
        } else {
            return "Opção inválida";
        }
    }

    public String getRelatorio() {
        relatorio = "Os tremores ocorrem quando:\n" +
                "\n" +
                "[1] Estou parado\n" +
                "[2] Estou andando\n" +
                "[3] Estou acelerando\n" +
                "[4] Estou freiando\n" +
                "=>";
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public String getParado() {
        parado = "As possiveis causas são:                Peças:\n" +
                "\n" +
                "1. Problemas de compressão                  - Motor\n" +
                "2. Velas de ignição sujas ou desgastadas    - Motor\n" +
                "3. Problemas no sistema de combustível      - Motor";
        return parado;
    }

    public void setParado(String parado) {
        this.parado = parado;
    }

    public String getAndando() {
        andando = "Em qual velocidade?\n" +
                "\n" +
                "[1] Baixa\n" +
                "[2] Alta\n" +
                "=>";
        return andando;
    }

    public void setAndando(String andando) {
        this.andando = andando;
    }

    public String getAceleracao() {
        aceleracao = "As possiveis causas são:                Peças:\n" +
                "\n" +
                "1. Tulipa com defeito                   - Tulipa\n" +
                "2. Trizeta com defeito                  - Trizeta\n" +
                "3. Mola da trizeta quebrada             - Trizeta\n" +
                "4. Semi eixo com desgaste               - Semi eixo\n";
        return aceleracao;
    }

    public void setAceleracao(String aceleracao) {
        this.aceleracao = aceleracao;
    }

    public String getFrenagem() {
        frenagem = "As possiveis causas são:                Peças:\n" +
                "\n" +
                "1. Disco de freio empenado              - Freio\n";
        return frenagem;
    }
    
    public void setFrenagem(String frenagem) {
        this.frenagem = frenagem;
    }

    public String getBaixaVelocidade() {
        baixaVelocidade = "As possiveis causas são:                Peças:\n" +
                "\n" +
                "1. Tulipa com defeito                   - Tulipa\n" +
                "2. Trizeta com defeito                  - Trizeta\n" +
                "3. Mola da trizeta quebrada             - Trizeta\n" +
                "4. Semi eixo com desgaste               - Semi eixo\n";
        return baixaVelocidade;
    }

    public void setBaixaVelocidade(String baixaVelocidade) {
        this.baixaVelocidade = baixaVelocidade;
    }

    public String getAltaVelocidade() {
        altaVelocidade = "As possiveis causas são:                Peças:\n" +
                "\n" +
                "1. Conjunto pneus desbalanceados       - Pneus\n" +
                "2. Conjunto roda desbalanceados        - Roda\n" +
                "3. Roda(s) empenada(s)                 - Roda\n" +
                "4. Pneu(s) desagregado(s)              - Pneus\n" +
                "5. Disco de freio gravemente empenado  - Disco de Freio\n";
        return altaVelocidade;
    }

    public void setAltaVelocidade(String altaVelocidade) {
        this.altaVelocidade = altaVelocidade;
    }
}
