package com.eng.refatoracao.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {

    private final List<ItemPedido> itens = new ArrayList<>();

    private String clienteNome;
    private String clienteEmail;
    private String clienteEndereco;
    private double total;
    private double frete;
    private String status = "ABERTO";

    public void adicionarItem(String nome, double preco, int quantidade) {
        itens.add(new ItemPedido(nome, preco, quantidade));
    }

    public void calcularTotal() {
        total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }
    }

    public void aplicarDesconto() {
        if (total > 500) {
            total *= 0.85;
        } else if (total > 200) {
            total *= 0.9;
        }
    }

    public void calcularFrete() {
        if (clienteEndereco != null && clienteEndereco.contains("SC")) {
            frete = total * 0.05;
        } else {
            frete = total * 0.15;
        }
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteEndereco() {
        return clienteEndereco;
    }

    public void setClienteEndereco(String clienteEndereco) {
        this.clienteEndereco = clienteEndereco;
    }

    public double getTotal() {
        return total;
    }

    public double getFrete() {
        return frete;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
