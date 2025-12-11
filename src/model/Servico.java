package model;

import java.time.LocalDate;

public class Servico {

    private String tipo;
    private double preco;
    private String nomeCliente;
    private String nomePet;
    private LocalDate data;

    public Servico(String tipo, double preco, String nomeCliente, String nomePet, LocalDate data) {
        this.tipo = tipo;
        this.preco = preco;
        this.nomeCliente = nomeCliente;
        this.nomePet = nomePet;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return tipo + ";" + preco + ";" + nomeCliente + ";" + nomePet + ";" + data.toString();
    }
}
