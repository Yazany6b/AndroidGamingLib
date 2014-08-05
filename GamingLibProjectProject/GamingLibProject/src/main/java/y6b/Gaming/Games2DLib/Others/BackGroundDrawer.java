package y6b.Gaming.Games2DLib.Others;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import y6b.Gaming.Games2DLib.FreamDrawer;
import y6b.Gaming.Games2DLib.InputObject;

public class BackGroundDrawer extends FreamDrawer{

	private int color = Color.GREEN;
	
	public BackGroundDrawer(Context context) {
		super(context);
		
	}

    public BackGroundDrawer(Context context,int backColor) {
        super(context);
        color = backColor;
        
    }

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(color);
		
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
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
