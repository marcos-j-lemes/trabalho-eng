package com.eng.refatoracao.control;

import com.eng.refatoracao.banco.BancoEstoque;
import com.eng.refatoracao.model.ItemPedido;
import com.eng.refatoracao.model.Pedido;

public class PedidoController {

    private final BancoEstoque bancoEstoque;

    public PedidoController() {
        this(new BancoEstoque());
        cadastrarProdutosIniciais();
    }

    public PedidoController(BancoEstoque bancoEstoque) {
        this.bancoEstoque = bancoEstoque;
    }

    public Pedido criarPedido(String clienteNome, String clienteEmail, String clienteEndereco) {
        Pedido pedido = new Pedido();
        pedido.setClienteNome(clienteNome);
        pedido.setClienteEmail(clienteEmail);
        pedido.setClienteEndereco(clienteEndereco);
        return pedido;
    }

    public void adicionarItem(Pedido pedido, String nome, double preco, int quantidade) {
        pedido.adicionarItem(nome, preco, quantidade);
    }

    public void processarPagamento(String formaPagamento) {
        if ("CARTAO".equalsIgnoreCase(formaPagamento)) {
            System.out.println("Pagamento cartão OK");
        } else if ("BOLETO".equalsIgnoreCase(formaPagamento)) {
            System.out.println("Boleto gerado");
        } else if ("PIX".equalsIgnoreCase(formaPagamento)) {
            System.out.println("PIX enviado");
        } else {
            System.out.println("Forma de pagamento desconhecida: " + formaPagamento);
        }
    }

    public void finalizar(Pedido pedido, String formaPagamento) {
        pedido.calcularTotal();
        pedido.aplicarDesconto();
        pedido.calcularFrete();
        atualizarEstoque(pedido);
        processarPagamento(formaPagamento);
        enviarNotificacao(pedido);
        pedido.setStatus("FINALIZADO");
    }

    public void enviarNotificacao(Pedido pedido) {
        System.out.println("Email enviado para " + pedido.getClienteEmail());
    }

    public void exibirResumo(Pedido pedido) {
        calcularValores(pedido);
        for (ItemPedido item : pedido.getItens()) {
            System.out.println(item.getQuantidade() + "x " + item.getNome() + " - " + item.getSubtotal());
        }


        System.out.println("Clientes: " );
        for (String cliente : pedido.getClientesNome()) {
            System.out.println("- " + cliente);
        }

        System.out.println("Total: " + pedido.getTotal());
        System.out.println("Desconto: " + pedido.getDesconto());
        System.out.println("Frete: " + pedido.getFrete());
    }


    public void listarProdutosEmEstoque() {
        bancoEstoque.listarProdutos();
    }

    public void cadastrarProdutoEstoque(String nome, double preco, int quantidade) {
        bancoEstoque.addProduto(nome, preco, quantidade);
    }


    private void cadastrarProdutosIniciais() {
        bancoEstoque.addProduto("Notebook", 3000, 5);
        bancoEstoque.addProduto("Mouse", 100, 10);
        bancoEstoque.addProduto("Teclado", 150, 8);
        bancoEstoque.addProduto("Monitor", 900, 4);
    }

    private void calcularValores(Pedido pedido) {
        pedido.calcularTotal();
        pedido.aplicarDesconto();
        pedido.calcularFrete();
    }

    private void atualizarEstoque(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()) {
            bancoEstoque.atualizarEstoque(item.getNome(), item.getQuantidade());
        }
    }

}
