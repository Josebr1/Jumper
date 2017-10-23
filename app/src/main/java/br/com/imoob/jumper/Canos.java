package br.com.imoob.jumper;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by jose on 10/23/17.
 */

public class Canos {

    private static final int QUANTIDADE_DE_CANOS = 5;
    private static final int DISTANCIA_ENTRE_CANOS = 250;
    private List<Cano> canos = new ArrayList<>();
    private Tela tela;

    public Canos(Tela tela){

        int posicaoInicial = 200;
        for(int i =0; i<QUANTIDADE_DE_CANOS; i++){
            posicaoInicial += DISTANCIA_ENTRE_CANOS;
            canos.add(new Cano(tela, posicaoInicial) );
        }

        this.tela = tela;
    }

    public void desenhaNo(Canvas canvas){
        for(Cano cano: canos)
            cano.desenhaNo(canvas);
    }

    public void move(){
        ListIterator<Cano> iterator = canos.listIterator();

        while (iterator.hasNext()){
            Cano cano = (Cano) iterator.next();
            cano.move();

            if(cano.saiuDaTela()){
                Cano outroCano = new Cano(tela, getMaximo() + DISTANCIA_ENTRE_CANOS);
                iterator.add(outroCano);
            }
        }
    }

    private int getMaximo(){
        int maximo = 0;

        for(Cano cano: canos){
            maximo = Math.max(cano.getPosicao(), maximo);
        }

        return maximo;
    }

}
