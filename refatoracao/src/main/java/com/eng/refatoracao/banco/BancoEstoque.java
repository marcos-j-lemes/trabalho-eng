package com.eng.refatoracao.banco;

import java.util.ArrayList;
import java.util.List;

public class BancoEstoque {

    private final List<String> produtos = new ArrayList<>();
    private final List<Double> precos = new ArrayList<>();
    private final List<Integer> quantidades = new ArrayList<>();

    public boolean addProduto(String nome, double preco, int qtd) {
        if (produtos.contains(nome)) {
            int index = produtos.indexOf(nome);
            precos.set(index, preco);
            quantidades.set(index, qtd);
            return true;
        }

        produtos.add(nome);
        precos.add(preco);
        quantidades.add(qtd);
        return true;
    }

    public boolean atualizarEstoque(String nome, int quantidadeVendida) {
        if (produtos.contains(nome)) {
            int index = produtos.indexOf(nome);
            int quantidadeAtual = quantidades.get(index);
            if (quantidadeAtual >= quantidadeVendida) {
                quantidades.set(index, quantidadeAtual - quantidadeVendida);
                return true;
            }

            System.out.println("Produto " + nome + " sem estoque suficiente");
            return false;
        }

        System.out.println("Produto " + nome + " não encontrado");
        return false;
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
