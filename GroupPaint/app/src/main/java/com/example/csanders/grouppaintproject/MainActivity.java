package com.example.csanders.grouppaintproject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int brushCount = 1;
    int strokeColor = Color.BLACK;
    float brushSize = 10f;
    int eraseCall = 0;

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View view) {
        customCanvas.clearCanvas();
    }

    public void changeBrushSize(View view) {
        brushCount++;
        if (brushCount == 1) {
            brushSize = 10f;
            Toast.makeText(getApplicationContext(), "You are now using the small brush", Toast.LENGTH_LONG).show();
        } else if (brushCount == 2) {
            brushSize = 20f;
            Toast.makeText(getApplicationContext(), "You are now using the medium brush", Toast.LENGTH_LONG).show();
        } else if (brushCount == 3) {
            brushSize = 30f;
            Toast.makeText(getApplicationContext(), "You are now using the large brush", Toast.LENGTH_LONG).show();
        }
        else if (brushCount == 4) {
            brushSize = 40f;
            Toast.makeText(getApplicationContext(), "You are now using the extra large brush", Toast.LENGTH_LONG).show();
        }
        else if (brushCount == 5) {
            brushSize = 50f;
            brushCount = 0;
            Toast.makeText(getApplicationContext(), "You are now using the jumbo brush", Toast.LENGTH_LONG).show();
        }
        customCanvas.mPaint.setStrokeWidth(brushSize);
    }

    public void eraser(View view) {
        int tempColor = Color.BLACK;
        eraseCall++;
        if (strokeColor != Color.WHITE) {
            tempColor = strokeColor;
        }
        if ((eraseCall % 2) != 0) {
            strokeColor = Color.WHITE;
        }
        else {
            strokeColor = tempColor;
        }
        customCanvas.mPaint.setColor(strokeColor);
    }

    public void changeStrokeColor(View view) {
        String buttonName = getResources().getResourceEntryName(view.getId());
        System.out.print(buttonName);
        if (buttonName.equals("black")) {
            strokeColor = R.color.black;
        } else if (buttonName.equals("gray")) {
            strokeColor = R.color.grey;
        } else if (buttonName.equals("bluedark")) {
            strokeColor = R.color.bluedark;
        } else if (buttonName.equals("bluelight")) {
            strokeColor = R.color.bluelight;
        } else if (buttonName.equals("greendark")) {
            strokeColor = R.color.greendark;
        } else if (buttonName.equals("greenlight")) {
            strokeColor = R.color.greenlight;
        } else if (buttonName.equals("orangedark")) {
            strokeColor = R.color.orangedark;
        } else if (buttonName.equals("orangelight")) {
            strokeColor = R.color.orangelight;
        }
        customCanvas.mPaint.setColor(getResources().getColor(strokeColor));
    }
}