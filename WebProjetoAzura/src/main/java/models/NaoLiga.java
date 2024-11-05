package models;

public class NaoLiga {
    private String relatorio;

    public String exibir(){
        relatorio = "!!! Aviso: Evite o motor repetidamente !!!\n" +
                "\n" +
                "As possiveis causas são:               Peças:\n" +
                "\n" +
                "1. Bateria descarregada                - Bateria \n" +
                "2. Falta de combustivel                - Bomba ou filtro de combustível\n" +
                "3. Problema na chave do veículo        - Chave \n" +
                "4. Problema no sistema de ignição      - Vela\n" +
                "5. Mudança de combustivel (carro flex) \n";
        return relatorio;
    }
}
