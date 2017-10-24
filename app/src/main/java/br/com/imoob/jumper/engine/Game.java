package br.com.imoob.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import br.com.imoob.jumper.elementos.Passaro;
import br.com.imoob.jumper.elementos.Pontuacao;
import br.com.imoob.jumper.R;
import br.com.imoob.jumper.elementos.Tela;
import br.com.imoob.jumper.elementos.GameOver;

/**
 * Created by jose on 10/22/17.
 */

public class Game extends SurfaceView implements Runnable, View.OnTouchListener{

    private boolean isRunning = true;
    private final SurfaceHolder holder = getHolder();
    private Passaro passaro;
    private Canos canos;
    private Pontuacao pontuacao;
    private Bitmap background;
    private Tela tela;
    private Som som;

    public Game(Context context){
        super(context);

        this.som = new Som(context);

        inicializaElementos();
        setOnTouchListener(this);

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

            canos.desenhaNo(canvas);
            canos.move();

            pontuacao.desenhaNo(canvas);

            if(new VerificadorDeColisao(passaro, canos).temColisao()){
                new GameOver(tela).desenhaNo(canvas);
                isRunning = false;
            }

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
        this.pontuacao = new Pontuacao(som);
        this.tela = new Tela(getContext());
        this.passaro = new Passaro(tela, getContext(), som);
        this.canos = new Canos(tela, pontuacao, getContext(), som);


        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        passaro.pula();
        return false;
    }
}
