package br.com.imoob.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import br.com.imoob.jumper.Passaro;
import br.com.imoob.jumper.R;
import br.com.imoob.jumper.Tela;

/**
 * Created by jose on 10/22/17.
 */

public class Game extends SurfaceView implements Runnable{

    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Bitmap background;
    private Tela tela;

    public Game(Context context){
        super(context);

        inicializaElementos();
    }


    // Aqui vamos implementar o loop principal do nosso jogo!
    @Override
    public void run() {
        while(isRunning){
            if(!holder.getSurface().isValid()) continue;
            //Neste loop vamos gerenciar os elementos do Jumper.

            //Para termos acesso ao canvas, precisamos de um objeto que permita a edição de cada pixel da nossa tela. Esse objeto é o SurfaceHolder.
            Canvas canvas = holder.lockCanvas();

            canvas.drawBitmap(background, 0, 0, null);

            passaro.desenhaNo(canvas);
            passaro.cai();

            // Aqui vamos desenhar os elementos do jogo!
            holder.unlockCanvasAndPost(canvas);
        }
    }

    // Termina o jogo, liberando recursos do aparelho
    public void cancela(){
        this.isRunning = false;
    }

    // Inicia o jogo
    public void inicia(){
        this.isRunning = true;
    }

    private void inicializaElementos(){
        this.passaro = new Passaro();
        this.tela = new Tela(getContext());

        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);

    }
}
