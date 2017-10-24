package br.com.imoob.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import br.com.imoob.jumper.R;

/**
 * Created by jose on 10/23/17.
 */

public class Cano {

    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private int posicao;
    private Bitmap bp;

    public Cano(Tela tela, int posicao) {
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();
        this.posicao = posicao;
    }


    public void desenhaNo(Canvas canvas, Context context) {
        bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        desenhaCanoSuperiorNo(canvas);
        desenhaCanoInferiorNo(canvas);
    }

    private void desenhaCanoInferiorNo(Canvas canvas) {
        Bitmap canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoInferior, false);
        canvas.drawBitmap(canoInferior,  posicao , alturaDoCanoInferior, null);
    }

    private void desenhaCanoSuperiorNo(Canvas canvas) {
        Bitmap canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoSuperior, false);
        canvas.drawBitmap(canoSuperior, posicao, 0, null);
    }


    public void move() {
        this.posicao -= 5;
    }

    private int valorAleatorio() {
        return (int) (Math.random() * 150);
    }

    public boolean saiuDaTela() {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao(){
        return posicao;
    }


    /**
     * Para o cano superior, a ideia é semelhante: precisamos saber quando
     * a borda superior do pássaro toca a base do cano superior. Para pegarmos a borda superior do pássaro basta subtrair o RAIO da sua altura.
     */
    public boolean temColisaoVerticalCom(Passaro passaro){
        return passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior
                || passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }

    /**
     * Se a distância entre a posicao horizontal do cano e a posição X (ho- rizontal)
     * do pássaro for menor que o seu RAIO, sabemos que houve uma colisão.
     */
    public boolean temColisaoHorizontalCom(Passaro passaro){
        return this.posicao - passaro.X < passaro.RAIO;
    }

}
