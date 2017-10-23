package br.com.imoob.jumper.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.imoob.jumper.Cano;
import br.com.imoob.jumper.Canos;
import br.com.imoob.jumper.Passaro;
import br.com.imoob.jumper.Pontuacao;
import br.com.imoob.jumper.R;
import br.com.imoob.jumper.Tela;
import br.com.imoob.jumper.VerificadorDeColisao;

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

    public Game(Context context){
        super(context);

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
        this.pontuacao = new Pontuacao();
        this.tela = new Tela(getContext());
        this.passaro = new Passaro(tela);
        this.canos = new Canos(tela, pontuacao);


        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        this.background = Bitmap.createScaledBitmap(back, back.getWidth(), tela.getAltura(), false);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        passaro.pula();
        return false;
    }
}
