package com.andreabonatti92.heartbeat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class HeartBeatCanvas extends View {

    private Paint paint = new Paint();
    private ArrayList<Integer> points;

    public HeartBeatCanvas(Context context, ArrayList<Integer> points) {
        super(context);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.points = points;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = getWidth();
        int canvasHeight = getHeight();
        int centerX = (getWidth() / 2);
        int centerY = (getHeight() / 2);

        // ri-elaboro l'ordine dei valori in modo da porteli rappresentare correttamente
        ArrayList<Integer> yValues = new ArrayList<>();
        for (int j = 0; j <= 49; j++) {
            int tmp;
            try {
                tmp = centerY - points.get(j);
            } catch (IndexOutOfBoundsException e) {
                tmp = centerY;
            }
            yValues.add(tmp);
        }

        int step = (int) Math.ceil(canvasWidth / 50f);
        int yPosition = 0;
        for (int i = 0; i < canvasWidth; i = i + step) {
            if (yPosition == 49) {
                return;
            }
            canvas.drawLine(
                    i,
                    yValues.get(yPosition),
                    i + step,
                    yValues.get(yPosition + 1),
                    paint
            );
            yPosition++;
        }
    }
}
