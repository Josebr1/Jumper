package br.com.imoob.jumper.elementos;

import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.imoob.jumper.engine.Cores;
import br.com.imoob.jumper.engine.Som;

/**
 * Created by jose on 10/23/17.
 */

public class Pontuacao {

    private static final Paint BRANCO = Cores.getCorDaPontuacao();
    private int pontos = 0;
    private Som som;

    public Pontuacao(Som som){
        this.som = som;
    }

    public void aumenta(){
        pontos++;
        som.play(Som.PONTO);
    }

    public void desenhaNo(Canvas canvas){
        canvas.drawText(String.valueOf(pontos), 100, 100, BRANCO);
    }

}
