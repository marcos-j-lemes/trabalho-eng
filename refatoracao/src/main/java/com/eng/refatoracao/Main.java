package com.eng.refatoracao;

import com.eng.refatoracao.models.*;
import com.eng.refatoracao.services.FinalizacaoService;

public class Main {
    public static void main(String[] args) {
        // Criar cliente
        Cliente cliente = new Cliente(
                "João Silva",
                "joao@email.com",
                "Rua das Flores, 123 - SC"
        );

        // Criar pedido
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem("Notebook", 2500.00, 1);
        pedido.adicionarItem("Mouse", 150.00, 2);
        pedido.adicionarItem("Teclado", 200.00, 1);

        // Finalizar pedido
        FinalizacaoService finalizacao = new FinalizacaoService();
        finalizacao.finalizar(pedido, "cartao");
    }
}