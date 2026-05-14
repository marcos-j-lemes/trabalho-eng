package com.eng.refatoracao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.eng.refatoracao.control.PedidoController;
import com.eng.refatoracao.model.Pedido;

public class Main {

    private static final String ARQUIVO_PADRAO = "src/main/java/com/eng/refatoracao/pedidos.txt";

    public static void main(String[] args) {
        PedidoController controller = new PedidoController();

        String caminhoArquivo = args.length > 0 ? args[0] : ARQUIVO_PADRAO;
        Pedido pedido = carregarPedido(caminhoArquivo, controller);

        if (pedido == null) {
            System.out.println("Nenhum pedido foi processado.");
            return;
        }

        System.out.println("Resumo do Pedido:");
        controller.exibirResumo(pedido);
        controller.finalizar(pedido, "CARTAO");
    }

    private static Pedido carregarPedido(
            String caminhoArquivo,
            PedidoController controller) {
        Pedido pedidoAtual = null;

        try {
            List<String> linhas = Files.readAllLines(Path.of(caminhoArquivo));

            for (String linha : linhas) {
                String linhaTratada = linha.trim();

                if (linhaTratada.isEmpty() || linhaTratada.startsWith("#")) {
                    continue;
                }

                String[] partes = linhaTratada.split(";");
                String tipo = partes[0].trim().toUpperCase();

                if ("PRODUTO".equals(tipo)) {
                    cadastrarProduto(partes, controller);
                } else if ("CLIENTE".equals(tipo)) {
                    pedidoAtual = criarPedido(partes, controller);
                } else if ("ITEM".equals(tipo)) {
                    adicionarItem(partes, pedidoAtual, controller);
                } else {
                    System.out.println("Linha ignorada: " + linhaTratada);
                }
            }

            System.out.println("Dados carregados de: " + caminhoArquivo);
        } catch (IOException ex) {
            System.out.println("Erro ao ler o arquivo: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("Erro nos dados do arquivo: " + ex.getMessage());
        }

        return pedidoAtual;
    }

    private static void cadastrarProduto(String[] partes, PedidoController controller) {
        validarCampos(partes, 4, "PRODUTO");

        String nome = partes[1].trim();
        double preco = Double.parseDouble(partes[2].trim());
        int quantidade = Integer.parseInt(partes[3].trim());

        controller.cadastrarProdutoEstoque(nome, preco, quantidade);
    }

    private static Pedido criarPedido(String[] partes, PedidoController controller) {
        validarCampos(partes, 4, "CLIENTE");

        String nome = partes[1].trim();
        String email = partes[2].trim();
        String endereco = partes[3].trim();

        return controller.criarPedido(nome, email, endereco);
    }

    private static void adicionarItem(
            String[] partes,
            Pedido pedido,
            PedidoController controller) {
        validarCampos(partes, 4, "ITEM");

        if (pedido == null) {
            throw new IllegalArgumentException("ITEM informado antes de CLIENTE.");
        }

        String nome = partes[1].trim();
        double preco = Double.parseDouble(partes[2].trim());
        int quantidade = Integer.parseInt(partes[3].trim());

        controller.adicionarItem(pedido, nome, preco, quantidade);
    }

    private static void validarCampos(String[] partes, int quantidadeEsperada, String tipo) {
        if (partes.length != quantidadeEsperada) {
            throw new IllegalArgumentException(tipo + " deve possuir " + quantidadeEsperada + " campos.");
        }
    }
}
