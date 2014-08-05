package  y6b.Gaming.Games2DLib;

import java.util.concurrent.ArrayBlockingQueue;
import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class GameView extends SurfaceView implements SurfaceHolder.Callback {
	
	protected GameLogic mGameLogic;
	protected ArrayBlockingQueue<InputObject> inputObjectPool;
	protected Context context;

	private boolean disableTouchListener;
	private boolean disableKeyListener;
	private boolean disableOrientationListener;
	
	public GameView(Context con) {
		super(con);
		context = con;
		
		getHolder().addCallback(this);
		
		mGameLogic = new GameLogic(getHolder(), this);
		createInputObjectPool();
		setFocusable(true);
		disableKeyListener = true;
	}
	
	private void createInputObjectPool() {
		inputObjectPool = new ArrayBlockingQueue<InputObject>(20);
		for (int i = 0; i < 20; i++) {
			inputObjectPool.add(new InputObject(inputObjectPool));
		}
	}

	
	public boolean isTouchListenerDisabled() {
		return disableTouchListener;
	}

	public void setDisableTouchListener(boolean disableTouchListener) {
		this.disableTouchListener = disableTouchListener;
	}

	public boolean isKeyListenerDisabled() {
		return disableKeyListener;
	}

	public void setDisableKeyListener(boolean disableKeyListener) {
		this.disableKeyListener = disableKeyListener;
	}

	public boolean isOrientationListenerDisabled() {
		return disableOrientationListener;
	}

	public void setOrientationSensorListener(boolean disableSensorListener) {
		this.disableOrientationListener = disableSensorListener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(disableTouchListener)
			return false;
		
		try {
			int hist = event.getHistorySize();
			if (hist > 0) {
				for (int i = 0; i < hist; i++) {
					InputObject input = new InputObject(inputObjectPool);
					input.useEventHistory(event, i);
					mGameLogic.feedInput(input);
				}
			}
			
			InputObject input = new InputObject(inputObjectPool);
			input.useEvent(event);
			mGameLogic.feedInput(input);
		} catch (Exception e) {
		}
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
		}
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(disableKeyListener)
			return false;
		
		InputObject input = new InputObject(inputObjectPool);
		
		input.useEvent(event);
		
		mGameLogic.feedInput(input);
		
		return true;
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		onSurfaceChanged(holder, format, width, height);
	}

	public void surfaceCreated(SurfaceHolder holder) {
		onSurfaceCreated(holder);
		Canvas c = holder.lockCanvas();
		int game_width = c.getWidth();
		int game_height = c.getHeight();
		holder.unlockCanvasAndPost(c);
		
		onSizeCalculated(game_width, game_height);
		
		mGameLogic.setGameState(GameLogic.RUNNING_GAME_STATE);
		mGameLogic.start(); 
		onGameStarted();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		onSurfaceDestroyed(holder);
		mGameLogic.setGameState(GameLogic.STOPPED_GAME_STATE);
	}


	@Deprecated
	public void pauseGame() {
		mGameLogic.setGameState(GameLogic.PAUSE_GAME_STATE);
	}
	
	@Deprecated
	public void resumeGame() {
		mGameLogic.setGameState(GameLogic.RUNNING_GAME_STATE);
	}
	
	public void stopGame() {
		mGameLogic.setGameState(GameLogic.STOPPED_GAME_STATE);
	}
	
	public void runGame() {
		mGameLogic.setGameState(GameLogic.RUNNING_GAME_STATE);
	}
	
	public abstract void processMotionEvent(InputObject input);
	public abstract void processKeyEvent(InputObject input);
	public abstract void processOrientationEvent(float orientation[]);
	public abstract void onDraw(Canvas canvas);
	public abstract void update();
	
	protected abstract void onSizeCalculated(int game_width , int game_height);
	protected abstract void onSurfaceDestroyed(SurfaceHolder holder);
	protected abstract void onGameStarted();
	protected abstract void onSurfaceCreated(SurfaceHolder holder);
	protected abstract void onSurfaceChanged(SurfaceHolder holder, int format, int width,
			int height);
		
	
}