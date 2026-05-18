package com.eng.refatoracao.services;

import com.eng.refatoracao.models.Pedido;
import com.eng.refatoracao.models.StatusPedido;
import com.eng.refatoracao.repositories.PedidoRepository;

public class FinalizacaoService {
    private CalculadoraService calculadora;
    private EstoqueService estoqueService;
    private PagamentoService pagamentoService;
    private NotificacaoService notificacaoService;
    private RelatorioService relatorioService;
    private PedidoRepository pedidoRepository;

    public FinalizacaoService() {
        this.calculadora = new CalculadoraService();
        this.estoqueService = new EstoqueService();
        this.pagamentoService = new PagamentoService();
        this.notificacaoService = new NotificacaoService();
        this.relatorioService = new RelatorioService();
        this.pedidoRepository = new PedidoRepository();
    }

    public void finalizar(Pedido pedido, String formaPagamento) {
        // 1. Calcular total bruto
        double totalBruto = calculadora.calcularTotal(pedido.getItens());

        // 2. Aplicar desconto
        double totalComDesconto = calculadora.aplicarDesconto(totalBruto);

        // 3. Calcular frete
        double frete = calculadora.calcularFrete(
                pedido.getCliente().getEndereco(),
                totalComDesconto
        );

        // 4. Definir total final
        double totalFinal = totalComDesconto + frete;
        pedido.setTotal(totalFinal);
        pedido.setFrete(frete);

        // 5. Atualizar estoque
        estoqueService.atualizarEstoque(pedido.getItens());

        // 6. Processar pagamento
        if (!pagamentoService.processarPagamento(formaPagamento)) {
            System.out.println("Erro no processamento do pagamento");
            return;
        }

        // 7. Enviar notificação
        notificacaoService.enviarEmailConfirmacao(pedido.getCliente(), totalFinal);

        // 8. Gerar relatório
        relatorioService.gerarRelatorio(pedido);

        // 9. Salvar no banco
        pedidoRepository.salvar(pedido);

        // 10. Atualizar status
        pedido.setStatus(StatusPedido.FINALIZADO);

        System.out.println("Pedido finalizado com sucesso!");
    }
}