package com.eng.refatoracao.services;


import com.eng.refatoracao.models.Cliente;

public class NotificacaoService {

    public void enviarEmailConfirmacao(Cliente cliente, double total) {
        System.out.println("Email enviado para " + cliente.getEmail());
        System.out.println("Pedido confirmado no valor de: R$ " + total);
    }
}