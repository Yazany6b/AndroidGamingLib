package y6b.Gaming.Games2DLib;

import y6b.Gaming.Games2DLib.InputObject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public abstract class FreamDrawer {
	protected Context context;
	public FreamDrawer(Context context) {
		
		this.context = context;
		
	}
	public abstract void draw(Canvas canvas);
	public abstract void update();
	public abstract void sizeCalculated(int game_width , int game_height);
	public abstract void processMotionEvent(InputObject input);
	public abstract void processKeyEvent(InputObject input);
	public abstract void processOrientationEvent(float orientation[]);
	public abstract void release();
	public abstract void surfaceDestroyed(SurfaceHolder holder);
	public abstract void gameStarted();
	public abstract void surfaceCreated(SurfaceHolder holder);
	public abstract void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height);
	
	public static final FreamDrawer NULL = new NullDrawer(null);
	
	
	private static final class NullDrawer extends FreamDrawer{

		private NullDrawer(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void draw(Canvas canvas) {
			// TODO Auto-generated method stub
			
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
	}

}
