package com.eng.refatoracao.control;

import com.eng.refatoracao.banco.BancoDeDados;


public class SalvarLog {


    public static void salvar(String msg) {
        BancoDeDados.salvarLog(msg);
    }
    
}
