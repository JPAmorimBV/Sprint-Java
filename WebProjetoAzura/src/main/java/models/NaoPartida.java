package models;

public class NaoPartida {
    private String mensagem;

    public String exibir() {
        return getMensagem();
    }

    public String getMensagem() {
        mensagem = "As possiveis causas são: \n" +
                "\n" +
                "Dificuldade para girar a chave: Chave e leitor de ignição podem estar danificados\n" +
                "\n" +
                "1. Falta de combustível\n" +
                "2. Veículo com temperatura elevada\n" +
                "3. Troca entre tipos de combustível\n" +
                "4. Bateria descarregada\n";
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
