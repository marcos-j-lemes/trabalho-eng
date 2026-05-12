package com.eng.refatoracao.banco;

import java.util.*;

public class BancoEstoque {

    public List<String> produtos = new ArrayList<>();
    public List<Double> precos = new ArrayList<>();
    public List<Integer> quantidades = new ArrayList<>();

    public String clienteNome;
    public String clienteEmail;
    public String clienteEndereco;

    public double total;
    public double frete;
    public String status;

    // crude methods for managing stock
    public boolean addProduto(String nome, double preco, int qtd) {
        produtos.add(nome);
        precos.add(preco);
        quantidades.add(qtd);
        return true;
    }

    public boolean atualizarEstoque(String nome) {
        System.out.println("Atualizando estoque de: " + nome);
        if (produtos.contains(nome)) {
            int index = produtos.indexOf(nome);
            int qtd = quantidades.get(index);
            if (qtd > 0) {
                quantidades.set(index, qtd - 1);
                return true;
            } else {
                System.out.println("Produto " + nome + " sem estoque");
                return false;
            }
        } else {
            System.out.println("Produto " + nome + " não encontrado");
            return false;
        }
        return true;
    }

    public boolean removerProduto(String nome) {
        if (produtos.contains(nome)) {
            int index = produtos.indexOf(nome);
            produtos.remove(index);
            precos.remove(index);
            quantidades.remove(index);
            return true;
        } else {
            System.out.println("Produto " + nome + " não encontrado");
            return false;
        }
    }

    public void listarProdutos() {
        System.out.println("Produtos em estoque:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(produtos.get(i) + " - R$ " + precos.get(i) + " - " + quantidades.get(i) + " unidades");
        }
    }
    
}
