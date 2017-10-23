package br.com.imoob.jumper;

import android.graphics.Canvas;

/**
 * Created by jose on 10/23/17.
 */

public class Cano {

    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private Tela tela;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private int posicao;

    public Cano(Tela tela, int posicao){
        this.tela = tela;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
        this.posicao = posicao;
    }



    public void desenhaNo(Canvas canvas){
        desenhaCanoSuperiorNo(canvas);
        desenhaCanoInferiorNo(canvas);
    }

    private void desenhaCanoInferiorNo(Canvas canvas){
        canvas.drawRect(posicao, alturaDoCanoInferior, posicao + LARGURA_DO_CANO, tela.getAltura(), Cores.getCorDoCano());
    }

    private void desenhaCanoSuperiorNo(Canvas canvas){
        canvas.drawRect(posicao, 0, posicao + LARGURA_DO_CANO, alturaDoCanoSuperior, Cores.getCorDoCano());
    }


    public void move(){
        this.posicao -= 5;
    }

    private int valorAleatorio(){
        return (int) (Math.random() * 150);
    }

}
