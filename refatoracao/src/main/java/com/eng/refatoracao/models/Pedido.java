package com.eng.refatoracao.models;

import java.util.*;

public class Pedido {
    private List<ItemPedido> itens = new ArrayList<>();
    private Cliente cliente;
    private double total;
    private double frete;
    private String status;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.status = StatusPedido.PENDENTE;
    }

    public void adicionarItem(String nome, double preco, int quantidade) {
        itens.add(new ItemPedido(nome, preco, quantidade));
    }

    // Getters e Setters
    public List<ItemPedido> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}