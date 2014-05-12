package y6b.Gaming.Games2DLib;

import java.util.concurrent.ArrayBlockingQueue;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLogic extends Thread {
	private SurfaceHolder surfaceHolder;
	private GameView mGameView;
	private int game_state;
	// private long time_orig;
	public static final int PAUSE_GAME_STATE = 0;
	public static final int READY_GAME_STATE = 1;
	public static final int RUNNING_GAME_STATE = 2;
	public static final int STOPPED_GAME_STATE = 3;
	
	private ArrayBlockingQueue<InputObject> inputQueue = new ArrayBlockingQueue<InputObject>(20);
	private Object inputQueueMutex = new Object();

	public GameLogic(SurfaceHolder surfaceHolder, GameView mGameView) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.mGameView = mGameView;
	}

	public void setGameState(int gamestate) {
		this.game_state = gamestate;
	}

	public int getGameState() {
		return game_state;
	}

	@Override
	public void run() {
//		long time_orig = System.currentTimeMillis();
//		long time_interim;
		Canvas canvas;
		
		while (game_state == RUNNING_GAME_STATE) {
			canvas = null;
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e1) {
					}
//					time_interim = System.currentTimeMillis();
//					int adj_mov = (int) (time_interim - time_orig);
					mGameView.update();
					processInput(); // this is the new way to process input.
//					time_orig = time_interim;
					this.mGameView.onDraw(canvas);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	public void feedInput(InputObject input) {
		synchronized (inputQueueMutex) {
			try {
				inputQueue.put(input);
			} catch (InterruptedException e) {
			}
		}
	}

	private void processInput() {
		synchronized (inputQueueMutex) {
			ArrayBlockingQueue<InputObject> inputQueue = this.inputQueue;
			while (!inputQueue.isEmpty()) {
				try {
					InputObject input = inputQueue.take();
					if (input.eventType == InputObject.EVENT_TYPE_KEY) {
						mGameView.processKeyEvent(input);
					} else if (input.eventType == InputObject.EVENT_TYPE_TOUCH) {
						mGameView.processMotionEvent(input);
					}
					//input.returnToPool();
				} catch (InterruptedException e) {
				}
			}
		}
	}

}