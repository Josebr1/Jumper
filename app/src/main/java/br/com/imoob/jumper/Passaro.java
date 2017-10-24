package br.com.imoob.jumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.imoob.jumper.engine.Som;

/**
 * Created by jose on 10/22/17.
 */

public class Passaro {

    private static final Paint vermelho = Cores.getCorDoPassaro();
    public static final int X = 100;
    public static final int RAIO = 50;
    private int altura;
    private Tela tela;
    private Bitmap passoro;
    private Context context;
    private Som som;

    public Passaro(Tela tela, Context context, Som som) {
        this.altura = 100;
        this.tela = tela;
        this.context = context;
        this.som = som;
    }

    public void desenhaNo(Canvas canvas) {
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        this.passoro = Bitmap.createScaledBitmap(bp, RAIO * 2, RAIO * 2, false);
        canvas.drawBitmap(passoro, X-RAIO, altura-RAIO, null);
    }

    public void cai() {
        boolean chegouNoChao = altura + RAIO > tela.getAltura();

        if (!chegouNoChao) {
            this.altura += 5;
        }

    }

    public void pula() {
        if (altura > RAIO) {
            som.play(Som.PULO);
            this.altura -= 150;
        }
    }

    public int getAltura() {
        return this.altura;
    }

}
