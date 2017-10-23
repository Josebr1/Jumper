package br.com.imoob.jumper;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jose on 10/22/17.
 */

public class Passaro {

    private static final Paint vermelho = Cores.getCorDoPassaro();
    private static final int X = 100;
    private static final int RAIO = 50;
    private int altura;
    private Tela tela;

    public Passaro(Tela tela){
        this.altura = 100;
        this.tela = tela;
    }

    public void desenhaNo(Canvas canvas){
        canvas.drawCircle(X, altura, RAIO, vermelho);
    }

    public void cai(){
        boolean chegouNoChao = altura + RAIO > tela.getAltura();

        if(!chegouNoChao){
            this.altura += 5;
        }

    }

    public void pula(){
        if(altura > RAIO){
            this.altura -= 150;
        }
    }

}
