package models;

import java.util.Scanner;

public class Autodiagnostico {
    private String menuAutodiag;

    NaoLiga naoLiga = new NaoLiga();
    Painel painel = new Painel();
    Tremores tremores = new Tremores();
    Alarme alarme = new Alarme();
    Chave chave = new Chave();
    NaoPartida naoPartida = new NaoPartida();
    Scanner scanner = new Scanner(System.in);

    public String autodiag() {
        System.out.print(getMenuAutodiag());
        String problema = scanner.nextLine();
        if (problema.equals("1")) {
            return naoLiga.exibir();
        } else if (problema.equals("2")) {
            return painel.exibir();
        } else if (problema.equals("3")) {
            return tremores.exibir();
        } else if (problema.equals("4")) {
            return alarme.exibir();
        } else if (problema.equals("5")) {
            return chave.exibir();
        } else if (problema.equals("6")) {
            return naoPartida.exibir();
        } else if (problema.equals("7")) {
            return "Ainda não podemos diagnosticar seu problema";
        } else {
            return "Opção inválida";
        }
    }

    public String getMenuAutodiag() {
        menuAutodiag = "Qual o problema?\n" +
                "\n" +
                "[1] O carro não liga\n" +
                "[2] Sinais no painel\n" +
                "[3] Tremores no carro\n" +
                "[4] Alarme\n" +
                "[5] Chave\n" +
                "[6] o carro não da partida\n" +
                "[7] Outro\n" +
                "=>";
        return menuAutodiag;
    }

    public void setMenuAutodiag(String menuAutodiag) {
        this.menuAutodiag = menuAutodiag;
    }
}
