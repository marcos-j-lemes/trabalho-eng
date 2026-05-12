package com.eng.refatoracao;

//import com.eng.refatoracao.banco.BancoDeDados;
//import com.eng.refatoracao.utils.RelatorioService;
import com.eng.refatoracao.control.Pedido;
import com.eng.refatoracao.control.SalvarLog;
import com.eng.refatoracao.control.controlRelatorio;


public class Main {

    public static void main(String[] args) {

        Pedido p = new Pedido();

        p.clienteNome = "Maria";
        p.clienteEmail = "maria@email.com";
        p.clienteEndereco = "SC";

        p.adicionarItem("Notebook", 3000, 1);
        p.adicionarItem("Mouse", 100, 2);

        p.finalizar();

        SalvarLog.salvar("Sistema finalizado");


        controlRelatorio cr = new controlRelatorio();
        cr.gerar(p);

        System.out.println("Frete: " + p.frete);
        System.out.println("Status: " + p.status);
    }
}