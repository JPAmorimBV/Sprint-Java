package models;

public class Chave {
    private String mensagem;

    public String exibir(){
        return getMensagem();
    }

    public String getMensagem() {
        mensagem = "As possiveis causas são: \n" +
                "\n" +
                "1. Chave sem bateria\n" +
                "2. Desgaste na codificação\n" +
                "3. Checagem de danos\n" +
                "4. Problema nas portas\n";
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
