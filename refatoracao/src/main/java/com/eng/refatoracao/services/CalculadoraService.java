package com.eng.refatoracao.services;


import com.eng.refatoracao.models.ItemPedido;
import com.eng.refatoracao.models.Pedido;
import java.util.*;

public class CalculadoraService {

    public double calcularTotal(List<ItemPedido> itens) {
        return itens.stream()
                .mapToDouble(ItemPedido::getSubtotal)
                .sum();
    }

    public double calcularFrete(String endereco, double total) {
        if (endereco != null && endereco.contains("SC")) {
            return total * 0.05;
        }
        return total * 0.15;
    }

    public double aplicarDesconto(double total) {
        if (total > 500) {
            return total * 0.85;
        } else if (total > 200) {
            return total * 0.9;
        }
        return total;
    }
}