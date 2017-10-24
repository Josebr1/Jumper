package br.com.imoob.jumper.engine;

import br.com.imoob.jumper.elementos.Passaro;

/**
 * Created by jose on 10/23/17.
 */

public class VerificadorDeColisao {

    private final Passaro passaro;
    private final Canos canos;


    public VerificadorDeColisao(Passaro passaro, Canos canos) {
        this.passaro = passaro;
        this.canos = canos;
    }

    public boolean temColisao(){
        return canos.temColisaoCom(passaro);
    }
}
