package com.eng.refatoracao.services;

public class PagamentoService {

    public boolean processarPagamento(String tipo) {
        switch (tipo.toLowerCase()) {
            case "cartao":
                System.out.println("Pagamento cartão OK");
                return true;
            case "boleto":
                System.out.println("Boleto gerado");
                return true;
            case "pix":
                System.out.println("PIX enviado");
                return true;
            default:
                System.out.println("Tipo de pagamento inválido");
                return false;
        }
    }
}