package y6b.Gaming.Games2DLib;

import android.content.Context; 
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class FreamGameView extends GameView{

	protected FreamDrawer currentDrawer;
	
	public FreamGameView(Context con ,FreamDrawer currentDrawer) {
		super(con);
		
		this.currentDrawer = currentDrawer;
	}
	
	public FreamDrawer getCurrentDrawer() {
		return currentDrawer;
	}



	public void setCurrentDrawer(FreamDrawer currentDrawer) {
		this.currentDrawer = currentDrawer;
	}



	@Override
	public void processMotionEvent(InputObject input) {
		currentDrawer.processMotionEvent(input);
		
	}

	@Override
	public void processKeyEvent(InputObject input) {
		currentDrawer.processKeyEvent(input);
		
	}

	@Override
	public void processOrientationEvent(float[] orientation) {
		currentDrawer.processOrientationEvent(orientation);
		
	}

	@Override
	public void onDraw(Canvas canvas) {
		currentDrawer.draw(canvas);
		
	}

	@Override
	public void update() {
		currentDrawer.update();
		
	}

	@Override
	protected void onSizeCalculated(int game_width, int game_height) {
		currentDrawer.sizeCalculated(game_width, game_height);
		
	}

	@Override
	protected void onSurfaceDestroyed(SurfaceHolder holder) {
		currentDrawer.surfaceDestroyed(holder);
		
	}

	@Override
	protected void onGameStarted() {
		currentDrawer.gameStarted();
		
	}

	@Override
	protected void onSurfaceCreated(SurfaceHolder holder) {
		currentDrawer.surfaceCreated(holder);
		
	}

	@Override
	protected void onSurfaceChanged(SurfaceHolder holder, int format,
			int width, int height) {
		
		currentDrawer.surfaceChanged(holder, format, width, height);
		
	}

}
