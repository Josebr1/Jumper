package br.com.imoob.jumper;

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
