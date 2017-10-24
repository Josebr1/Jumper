package br.com.imoob.jumper.engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import br.com.imoob.jumper.R;

/**
 * Created by jose on 10/23/17.
 */

public class Som {

    private SoundPool soundPool;
    public static int PULO;
    public static int COLISAO;
    public static int PONTO;

    public Som(Context context){
        soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        PULO = soundPool.load(context, R.raw.pulo, 1);
        COLISAO = soundPool.load(context, R.raw.colisao, 1);
        PONTO = soundPool.load(context, R.raw.pontos, 1);
    }

    public void play(int som){
        soundPool.play(som, 1, 1, 1, 0, 1);
    }

}
