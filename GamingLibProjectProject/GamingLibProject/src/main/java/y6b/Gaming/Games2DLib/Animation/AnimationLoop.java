package y6b.Gaming.Games2DLib.Animation;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;
import y6b.Gaming.Games2DLib.SpriteObject;

public class AnimationLoop {

	private ArrayList<AnimationFream> freams = new ArrayList<AnimationFream>();
	private int currentFream = 0;
	
	private long timer = 0;
	
	private boolean cycled = false;
	private boolean started = false;
	private boolean paused = false;
	private boolean stopped = true;
	
	private SpriteObject object;
	
	public AnimationLoop(SpriteObject object) {
		cycled = false;
		this.object = object;
	}

	public AnimationLoop(SpriteObject object , boolean cycled) {
		super();
		this.object = object;
		this.cycled = cycled;
	}
	
	private void setupFream()
	{
		AnimationFream f = freams.get(currentFream);
		object.setBitmap(f.getFream());
		timer = System.currentTimeMillis() + f.getDuration();
	}
	
	public boolean add(AnimationFream object) {
		return freams.add(object);
	}

	public void add(int index, AnimationFream object) {
		freams.add(index, object);
	}

	public void clear() {
		freams.clear();
	}

	public boolean isEmpty() {
		return freams.isEmpty();
	}

	public AnimationFream remove(int index) {
		return freams.remove(index);
	}

	public boolean remove(AnimationFream object) {
		return freams.remove(object);
	}

	public void start()
	{
		if(started)
			return;
		
		started = true;
		
		setupFream();
	}


	public boolean isCycled() {
		return cycled;
	}

	public void setCycled(boolean cycled) {
		this.cycled = cycled;
	}

	public boolean isStarted() {
		return started;
	}


	public boolean isPaused() {
		return paused;
	}

	public boolean isStopped() {
		return stopped;
	}

	
	public void pause() {
		this.paused = true;
		started = false;
	}

	public void stop() {
		this.stopped = true;
		started = false;
		paused = false;
		currentFream = 0;
	}
	
	public void doCycle()
	{
		if(!started)
			return;
		
		if(System.currentTimeMillis() - timer > 0)
		{
			if(currentFream + 1 < freams.size())
			{
				currentFream++;
				
				setupFream();
			}
			else
			{
				currentFream = 0;
				started = cycled;
				setupFream();
			}
		}
	}
	
	public void release()
	{
		for(AnimationFream f : freams)
			f.release();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		AnimationLoop loop = new AnimationLoop(null , isCycled());
		loop.started = started;
		loop.stopped = stopped;
		loop.paused = paused;
		loop.timer = timer;
		loop.currentFream = currentFream;
	
		for(AnimationFream f : freams)
			loop.freams.add((AnimationFream) f.clone());
		
		return loop;
	}
	
	public Object clone(SpriteObject c) throws CloneNotSupportedException {
		
		AnimationLoop loop = new AnimationLoop(c , isCycled());
		loop.started = started;
		loop.stopped = stopped;
		loop.paused = paused;
		loop.timer = timer;
		loop.currentFream = currentFream;
	
		for(AnimationFream f : freams)
			loop.freams.add((AnimationFream) f.clone());
		
		return loop;
	}

	public static AnimationLoop fromResources(Resources resources , int freams_id ,int durations_id )
	{
		AnimationLoop loop = new AnimationLoop(null);
		
		int [] freams = resources.getIntArray(freams_id);
		int [] durations = resources.getIntArray(durations_id);
		int lastDuration = 0;
		for(int i = 0 ; i < freams.length ; i++)
		{
			AnimationFream fream = new AnimationFream(BitmapFactory.decodeResource(resources, freams[i]),0);
			if(fream.getFream() == null)
				Log.d("Defend_Hard_Input", "Null Fream " + i + " => " + freams[i]);
			if(durations.length <= 1)
			{
				fream.setDuration(durations[0]);
				lastDuration = durations[0];
			}else if((durations.length-1) > i)
			{
				fream.setDuration(durations[durations.length-1]);
				durations[0] = durations[durations.length-1];
			}else if(durations[i] != -1)
			{
				fream.setDuration(durations[i]);
				lastDuration = durations[i];
			}else
			{
				fream.setDuration(lastDuration);
			}
			
			loop.add(fream);
		}
		
		return loop;
	}
	
}
