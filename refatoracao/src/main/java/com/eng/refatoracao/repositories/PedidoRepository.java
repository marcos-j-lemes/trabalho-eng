package com.eng.refatoracao.repositories;


import com.eng.refatoracao.models.Pedido;
import com.eng.refatoracao.entity.BancoDeDados;

public class PedidoRepository {

    public void salvar(Pedido pedido) {
        BancoDeDados.salvarPedido(pedido);
        BancoDeDados.salvarLog("Pedido salvo: " + pedido.getCliente().getNome());
    }
}