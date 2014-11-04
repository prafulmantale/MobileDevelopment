package prafulmantale.praful.com.semicircleguage.charting;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by prafulmantale on 11/4/14.
 */
public class SemiCirculo {

    private Path circulo;
    private Paint color;
    private float px, py, radio, anguloI, anchoa,offset;
    private int r, g, b;
    private int resolucion;
    private float puntox[],puntoy[];


    public SemiCirculo(int r1, int g1, int b1, int resolucion1) {

        this.offset = 0;
        this.color = new Paint();
        this.r = r1;
        this.g = g1;
        this.b = b1;
        this.color.setColor(Color.rgb(r, g, b));
        color.setAntiAlias(true);
        circulo = new Path();
        this.resolucion = resolucion1;
        this.puntox = new float[this.resolucion];
        this.puntoy = new float[this.resolucion];
        this.anguloI = 0;
        this.anchoa = 1;

    }

    public void SetOffset(float off) {
        this.offset = off;
    }

    public void SetColor(int r1,int g1, int b1){
        this.r=r1;
        this.g=g1;
        this.b=b1;
        this.color.setColor(Color.rgb(r, g, b));
    }

    public void CalcularPuntosPorcentaje(float px1, float py1,
                                         float porcentaje, float radio1) {
        this.anguloI = 0 + this.offset;
        this.px = px1;
        this.py = py1;
        this.radio = radio1;
        this.anguloI = 0;
        this.anchoa = porcentaje / 100 * 360;

        this.CalcularPuntos(px, py, anguloI, anchoa, radio);
    }

    public void CalcularPuntos(float px1, float py1, float anguloI1,
                               float anchoangulo, float radio1) {
        this.anguloI = anguloI1 + this.offset;

        this.anchoa = anchoangulo;
        this.px = px1;
        this.py = py1;
        this.radio = radio1 ;

        float angulo = 360 - this.anguloI - this.anchoa;

        for (int i = 0; i < resolucion; i++) {
            this.puntox[i] = this.px - (float) Math.sin(Math.toRadians(angulo))
                    * this.radio;
            this.puntoy[i] = this.py - (float) Math.cos(Math.toRadians(angulo))
                    * this.radio;
            angulo = (360 - this.anguloI - this.anchoa)
                    + ((this.anchoa / (float) (this.resolucion)) * (i + 2));
        }

        this.circulo.reset();

        this.circulo.moveTo(this.px, this.py);
        for (int i = 0; i < resolucion; i++) {
            this.circulo.lineTo(this.puntox[i], this.puntoy[i]);
        }

    }

    public void ImprimeCirculo(Canvas canvas) {

        canvas.drawPath(this.circulo, this.color);

    }

}
