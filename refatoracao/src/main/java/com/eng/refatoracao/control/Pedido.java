package com.eng.refatoracao.control;

import java.util.*;

import com.eng.refatoracao.banco.BancoDeDados;
import com.eng.refatoracao.utils.RelatorioService;


public class Pedido {

    public List<String> produtos = new ArrayList<>();
    public List<Double> precos = new ArrayList<>();
    public List<Integer> quantidades = new ArrayList<>();

    public String clienteNome;
    public String clienteEmail;
    public String clienteEndereco;

    public double total;
    public double frete;
    public String status;

    private RelatorioService relatorioService = new RelatorioService();

    public void adicionarItem(String nome, double preco, int qtd) {
        produtos.add(nome);
        precos.add(preco);
        quantidades.add(qtd);
    }

    public void calcularTotal() {
        total = 0;
        for (int i = 0; i < precos.size(); i++) {
            total += precos.get(i) * quantidades.get(i);
        }
    }

    public void calcularFrete() {
        if (clienteEndereco.contains("SC")) {
            frete = total * 0.05;
        } else {
            frete = total * 0.15;
        }
    }

    public void aplicarDesconto() {
        if (total > 500) {
            total *= 0.85;
        } else if (total > 200) {
            total *= 0.9;
        }
    }

    public void atualizarEstoque() {
        BancoEstoque bancoEstoque = new BancoEstoque();
        for (String p : produtos) {
            bancoEstoque.atualizarEstoque(p);
        }
    }

    public void processarPagamento(String tipo) {
        if (tipo.equals("cartao")) {
            System.out.println("Pagamento cartão OK");
        } else if (tipo.equals("boleto")) {
            System.out.println("Boleto gerado");
        } else if (tipo.equals("pix")) {
            System.out.println("PIX enviado");
        }
    }

    public void enviarNotificacao() {
        System.out.println("Email enviado para " + clienteEmail);
    }

    public void gerarRelatorio() {
        System.out.println("Relatorio do pedido:");
        for (String p : produtos) {
            System.out.println(p);
        }
        System.out.println("Total: " + total);
    }

    public void salvarNoBanco() {
        BancoDeDados.salvarPedido(this);
        BancoDeDados.salvarLog("Pedido salvo: " + clienteNome);
    }

    public void finalizar() {
        calcularTotal();
        aplicarDesconto();
        calcularFrete();
        atualizarEstoque();
        processarPagamento("cartao");
        enviarNotificacao();
        gerarRelatorio();
        salvarNoBanco();
        status = "FINALIZADO";
    }
}