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
        float brushSize = 0f;
        if (brushCount == 1) {
            brushSize = 10f;
            Toast.makeText(getApplicationContext(), "You are now using the small brush", Toast.LENGTH_LONG).show();
        } else if (brushCount == 2) {
            brushSize = 20f;
            Toast.makeText(getApplicationContext(), "You are now using the medium brush", Toast.LENGTH_LONG).show();
        } else if (brushCount == 3) {
            brushSize = 30f;
            brushCount = 0;
            Toast.makeText(getApplicationContext(), "You are now using the large brush", Toast.LENGTH_LONG).show();
        }
        customCanvas.mPaint.setStrokeWidth(brushSize);
        customCanvas.mPaint.setColor(strokeColor);
    }

    public void changeStrokeColor(View view) {
        String buttonName = getResources().getResourceEntryName(view.getId());
        if (buttonName == "black") {
            strokeColor = R.color.black;
        } else if (buttonName == "gray") {
            strokeColor = R.color.grey;
        } else if (buttonName == "bluedark") {
            strokeColor = R.color.bluedark;
        } else if (buttonName == "bluelight") {
            strokeColor = R.color.bluelight;
        } else if (buttonName == "greendark") {
            strokeColor = R.color.greendark;
        } else if (buttonName == "greenlight") {
            strokeColor = R.color.greenlight;
        } else if (buttonName == "orangedark") {
            strokeColor = R.color.orangedark;
        } else if (buttonName == "orangelight") {
            strokeColor = R.color.orangelight;
        }
        customCanvas.mPaint.setColor(getResources().getColor(strokeColor));
    }
}