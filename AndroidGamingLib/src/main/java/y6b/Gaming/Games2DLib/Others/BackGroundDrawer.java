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
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(color);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sizeCalculated(int game_width, int game_height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMotionEvent(InputObject input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processKeyEvent(InputObject input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processOrientationEvent(float[] orientation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
