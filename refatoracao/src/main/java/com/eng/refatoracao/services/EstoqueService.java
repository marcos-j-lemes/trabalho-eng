package com.eng.refatoracao.services;

import com.eng.refatoracao.models.ItemPedido;
import java.util.*;

public class EstoqueService {

    public void atualizarEstoque(List<ItemPedido> itens) {
        for (ItemPedido item : itens) {
            System.out.println("Atualizando estoque de: " + item.getNome());
        }
    }
}