package y6b.Gaming.Games2DLib.CustomSpriteObjects;

import y6b.Gaming.Games2DLib.SpriteObject;
import y6b.Gaming.Games2DLib.Animation.AnimationFream;
import y6b.Gaming.Games2DLib.Animation.AnimationLoop;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class AnimatedSpriteObject extends AssociatedSpriteObject{
	
	private AnimationLoop animation = null;
	
	public AnimatedSpriteObject(Bitmap bitmap, float x, float y, int state) {
		super(bitmap, x, y, state);
	
		animation = new AnimationLoop(this);
	}

	public AnimatedSpriteObject(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);

		animation = new AnimationLoop(this);
	}

	public AnimatedSpriteObject(SpriteObject scr) {
		super(scr);
		
		animation = new AnimationLoop(this);
	}
	
	public AnimationLoop getAnimationLoop() {
		return animation;
	}

	public void setAnimationLoop(AnimationLoop animation) {
		this.animation = animation;
	}

	public boolean add(AnimationFream object) {
		return animation.add(object);
	}

	public void add(int index, AnimationFream object) {
		animation.add(index, object);
	}

	public void clear() {
		animation.clear();
	}

	public AnimationFream remove(int index) {
		return animation.remove(index);
	}

	public boolean remove(AnimationFream object) {
		return animation.remove(object);
	}

	public void start() {
		animation.start();
	}

	public boolean isCycled() {
		return animation.isCycled();
	}

	public void setCycled(boolean cycled) {
		animation.setCycled(cycled);
	}

	public boolean isStarted() {
		return animation.isStarted();
	}

	public boolean isPaused() {
		return animation.isPaused();
	}

	public boolean isStopped() {
		return animation.isStopped();
	}

	public void pause() {
		animation.pause();
	}

	public void stop() {
		animation.stop();
	}

	@Override
	public void draw(Canvas canvas) {
		animation.doCycle();
		super.draw(canvas);
	}
	
	@Override
	public synchronized void release() {
		try
		{
			animation.release();
			super.release();
		}catch (Exception e) {
		}
	}
}
