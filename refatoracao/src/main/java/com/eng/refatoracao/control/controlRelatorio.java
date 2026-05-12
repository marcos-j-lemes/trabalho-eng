package com.eng.refatoracao.control;

import com.eng.refatoracao.utils.RelatorioService;


public class controlRelatorio {

    public void gerar(Pedido p) {
        RelatorioService r = new RelatorioService();
        r.gerar(p);
    }
    
}
