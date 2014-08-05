package y6b.Gaming.Games2DLib.CustomSpriteObjects;

import y6b.Gaming.Games2DLib.SpriteObject;
import android.graphics.Bitmap;

public class TimedSpriteObject extends SpriteObject {

	private long waitMS = 0;
	private long timer = 0;
	
	private ITimePassedListener listener;
	
	public TimedSpriteObject(Bitmap bitmap, float x, float y, int state) {
		super(bitmap, x, y, state);
		
	}

	public TimedSpriteObject(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
		
	}
	
	public TimedSpriteObject(SpriteObject scr) {
		super(scr.getBitmap(), (int)scr.getX(), (int)scr.getY(),scr.getState());
		setHealth(scr.getHealth());
		setMoveX(scr.getMoveX());
		setMoveY(scr.getMoveY());
		setOrientation(scr.getOrientation());
	}
	
	@Override
	public synchronized void update(int adj_mov) {
		super.update(adj_mov);
		
		if(isTimePassed() && listener != null)
		{
			resetTime(waitMS);
			listener.onTimePassed(this);
		}
	}

	public long getStillMs()
	{
		long still = timer - System.currentTimeMillis();
		return still > 0 ? still : 0;
	}
	
	public long getWaitMS() {
		return waitMS;
	}

	public void setWaitMS(long waitMS) {
		this.waitMS = waitMS;
	}

	public void resetTime(long waitMS)
	{
		this.waitMS = waitMS;
		timer = System.currentTimeMillis() + waitMS;
	}
	
	public void resetTime()
	{
		timer = System.currentTimeMillis() + waitMS;
	}
	
	public  boolean isTimePassed() {
		return System.currentTimeMillis() - timer > 0;
	}
	
	public void setOnTimePassedListener(ITimePassedListener listener)
	{
		this.listener = listener;
	}
	
	public static interface ITimePassedListener {
		void onTimePassed(SpriteObject sender);
	}
	
}
