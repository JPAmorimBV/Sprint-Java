package models;

public class Alarme {
    private String mensagem;

    public String exibir() {
        return getMensagem();
    }

    public String getMensagem() {
        mensagem = "As possiveis causas são: \n" +
                "\n" +
                "Caso seu alarme esteja disparado e você não consegue desativá-lo com controle remoto, \ndesligue a bateria automotiva, desconecte o polo negativo e espere dez minutos. \nEm seguida, leve-o para a manutenção.\n" +
                "\n" +
                "1. Problemas no próprio alarme\n" +
                "2. Janelas, portas e capô empenados\n" +
                "3. Acúmulo de poeira nos componentes elétricos\n" +
                "4. Janelas ou portas abertas\n" +
                "5. Curto-circuito\n" +
                "6. Problemas com a sirene\n" +
                "7. Desgaste da bateria dos controles \n";
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
