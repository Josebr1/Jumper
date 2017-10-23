package br.com.imoob.jumper;

import android.graphics.Paint;

/**
 * Created by jose on 10/22/17.
 */

public class Cores {

    public static Paint getCorDoPassaro(){
        Paint vermelho = new Paint();
        vermelho.setColor(0xFFFF0000);
        return vermelho;
    }

    public static Paint getCorDoCano(){
        Paint verde = new Paint();
        verde.setColor(0xFF00FF00);
        return verde;
    }

}
