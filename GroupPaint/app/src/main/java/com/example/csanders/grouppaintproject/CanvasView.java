package com.example.csanders.grouppaintproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by csanders on 7/17/2017.
 */

public class CanvasView extends View {

    private Bitmap mBitmap;
    public Canvas mCanvas;
    private Path mPath;
    Context context;
    public Paint mPaint, dPaint;
    private float mX, mY;
    public static String nameOfShape;
    public static boolean makeShapes = false;
    private static final float TOLERANCE = 5;
    Paint myRedPaintFill;
    Paint myGreenPaintFill;
    Paint myBluePaintFill;
    Paint myPurplePaintFill;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        mPaint = new Paint();
        mPath = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(10f);
        dPaint = new Paint(Paint.DITHER_FLAG);
        init();
    }

    private void init(){
        myRedPaintFill = new Paint();
        myRedPaintFill.setColor(Color.RED);
        myRedPaintFill.setStyle(Paint.Style.FILL);

        myGreenPaintFill = new Paint();
        myGreenPaintFill.setColor(Color.GREEN);
        myGreenPaintFill.setStyle(Paint.Style.FILL);

        myBluePaintFill = new Paint();
        myBluePaintFill.setColor(Color.BLUE);
        myBluePaintFill.setStyle(Paint.Style.FILL);

        myPurplePaintFill = new Paint();
        myPurplePaintFill.setColor(Color.parseColor("#FF00FF"));
        myPurplePaintFill.setStyle(Paint.Style.STROKE);
        myPurplePaintFill.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, 0, 0, dPaint);
        canvas.drawPath(mPath, mPaint);
    }

    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void drawTriangle(float x, float y) {
        int width = 100;
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth);
        path.lineTo(x - halfWidth, y + halfWidth);
        path.lineTo(x + halfWidth, y + halfWidth);
        path.lineTo(x, y - halfWidth);
        path.close();

        mCanvas.drawPath(path, myGreenPaintFill);
    }

    public void clearCanvas() {
        mCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    private void upTouch() {
        mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
    }

    private void putShapeDown(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        if (nameOfShape.equals("square")) {
            mCanvas.drawRect(mX, mY, (mX + 200), (mY + 200), myRedPaintFill);
        }
        else if (nameOfShape.equals("circle")) {
            mCanvas.drawCircle(mX, mY, 50, myBluePaintFill);
        }
        else if (nameOfShape.equals("triangle")) {
            drawTriangle(mX, mY);
        }
        else if (nameOfShape.equals("line")) {
            mCanvas.drawLine(mX, mY, (mX+200), (mY+200), myPurplePaintFill);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (makeShapes) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    putShapeDown(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    upTouch();
                    makeShapes = false;
                    break;
            }
        }
        else {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startTouch(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveTouch(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    upTouch();
                    break;
            }
        }
        invalidate();
        return true;
    }
}