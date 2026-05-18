package com.eng.refatoracao.services;


import com.eng.refatoracao.models.ItemPedido;
import com.eng.refatoracao.models.Pedido;
import java.util.*;

public class RelatorioService {

    public void gerarRelatorio(Pedido pedido) {
        System.out.println("=== RELATÓRIO DO PEDIDO ===");
        System.out.println("Cliente: " + pedido.getCliente().getNome());
        System.out.println("Itens do pedido:");

        for (ItemPedido item : pedido.getItens()) {
            System.out.printf("  - %s: %d x R$ %.2f = R$ %.2f%n",
                    item.getNome(),
                    item.getQuantidade(),
                    item.getPreco(),
                    item.getSubtotal()
            );
        }

        System.out.printf("Subtotal: R$ %.2f%n", pedido.getTotal() - pedido.getFrete());
        System.out.printf("Frete: R$ %.2f%n", pedido.getFrete());
        System.out.printf("Total Final: R$ %.2f%n", pedido.getTotal());
        System.out.println("Status: " + pedido.getStatus());
    }
}