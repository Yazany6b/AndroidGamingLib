package y6b.Gaming.Games2DLib.Animation;

import android.graphics.Bitmap;

public class AnimationFream{

	private Bitmap fream = null;
	private long duration = 0;
	/**
	 * Create a new object of type animation fream
	 * @param fream the bitmap that will represent this fream
	 * @param duration the duration of the fream in the animation loop
	 */
	public AnimationFream(Bitmap fream, long duration) {
		super();
		this.fream = fream;
		this.duration = duration;
	}
	public Bitmap getFream() {
		return fream;
	}
	public void setFream(Bitmap fream) {
		this.fream = fream;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	public void release()
	{
		fream.recycle();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		AnimationFream af = new AnimationFream(Bitmap.createBitmap(fream), duration);
		return af;
	}
	
}
