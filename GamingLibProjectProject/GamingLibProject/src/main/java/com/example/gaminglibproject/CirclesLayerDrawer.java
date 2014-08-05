package com.example.gaminglibproject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.ArrayList;

import y6b.Gaming.Games2DLib.FreamDrawer;
import y6b.Gaming.Games2DLib.InputObject;
import y6b.Gaming.Games2DLib.Graphics.Circle;
import y6b.Gaming.Games2DLib.Graphics.Line;

/**
 * Created by Yazan on 8/4/14.
 */
public class CirclesLayerDrawer extends FreamDrawer {

    private ArrayList<Circle> circles;

    private Line leftWall;

    public CirclesLayerDrawer(Context context) {
        super(context);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    @Override
    public void sizeCalculated(int game_width, int game_height) {

    }

    @Override
    public void processMotionEvent(InputObject input) {

    }

    @Override
    public void processKeyEvent(InputObject input) {

    }

    @Override
    public void processOrientationEvent(float[] orientation) {

    }

    @Override
    public void release() {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void gameStarted() {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
}
