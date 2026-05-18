package com.eng.refatoracao.entity;

import com.eng.refatoracao.models.Pedido;

public class BancoDeDados {

    public static void salvarPedido(Pedido pedido) {
        System.out.println("Salvando pedido no banco de dados...");
    }

    public static void salvarLog(String mensagem) {
        System.out.println("[LOG] " + mensagem);
        // Implementação real de log
    }
}