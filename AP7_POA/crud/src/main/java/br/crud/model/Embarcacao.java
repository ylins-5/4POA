package br.crud.model;

public class Embarcacao {

    private int id;
    private String nome;
    private String tipo;
    private boolean ativa;

    public Embarcacao() {
    }

    public Embarcacao(int id, String nome, String tipo, boolean ativa) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.ativa = ativa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - %s", id, nome, tipo, ativa ? "Ativa" : "Inativa");
    }
}