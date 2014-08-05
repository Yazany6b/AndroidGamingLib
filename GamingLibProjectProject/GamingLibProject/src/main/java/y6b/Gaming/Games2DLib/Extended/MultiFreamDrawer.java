package y6b.Gaming.Games2DLib.Extended;

import java.util.ArrayList;

import y6b.Gaming.Games2DLib.FreamDrawer;
import y6b.Gaming.Games2DLib.InputObject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MultiFreamDrawer extends FreamDrawer {
	
	private ArrayList<FreamDrawer> drawers = new ArrayList<FreamDrawer>();
	
	
	public MultiFreamDrawer(Context context) {
		super(context);
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		for (FreamDrawer drawer : drawers) {
			drawer.draw(canvas);
		}
		
	}

	@Override
	public void update() {
		for (FreamDrawer drawer : drawers) {
			drawer.update();
		}
		
	}

	@Override
	public void sizeCalculated(int game_width, int game_height) {
		for (FreamDrawer drawer : drawers) {
			drawer.sizeCalculated(game_width, game_height);
		}
		
	}

	@Override
	public void processMotionEvent(InputObject input) {
		for (FreamDrawer drawer : drawers) {
			drawer.processMotionEvent(input);
		}
		
	}

	@Override
	public void processKeyEvent(InputObject input) {
		for (FreamDrawer drawer : drawers) {
			drawer.processKeyEvent(input);
		}
		
	}

	@Override
	public void processOrientationEvent(float[] orientation) {
		for (FreamDrawer drawer : drawers) {
			drawer.processOrientationEvent(orientation);
		}
		
		
	}

	@Override
	public void release() {
		for (FreamDrawer drawer : drawers) {
			drawer.release();
		}
		
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		for (FreamDrawer drawer : drawers) {
			drawer.surfaceDestroyed(holder);
		}
		
		
	}

	@Override
	public void gameStarted() {
		for (FreamDrawer drawer : drawers) {
			drawer.gameStarted();
		}
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		for (FreamDrawer drawer : drawers) {
			drawer.surfaceCreated(holder);
		}
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		for (FreamDrawer drawer : drawers) {
			drawer.surfaceChanged(holder, format, width, height);
		}
		
	}

	public boolean addFreamDrawer(FreamDrawer object) {
		return drawers.add(object);
	}

	public void clearFreamDrawers() {
		drawers.clear();
	}

	public boolean contains(FreamDrawer object) {
		return drawers.contains(object);
	}

	public FreamDrawer getFreamDrawer(int index) {
		return drawers.get(index);
	}

	public int indexOf(FreamDrawer object) {
		return drawers.indexOf(object);
	}

	public boolean isEmpty() {
		return drawers.isEmpty();
	}

	public FreamDrawer removeFreamDrawer(int index) {
		return drawers.remove(index);
	}

	public boolean removeFreamDrawer(FreamDrawer object) {
		return drawers.remove(object);
	}
	
	

}
