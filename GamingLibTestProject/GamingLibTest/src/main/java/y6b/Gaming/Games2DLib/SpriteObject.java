package y6b.Gaming.Games2DLib;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;

public class SpriteObject {
	/**
	 * The Dead status that make the object not visible.
	 */
	public static final int DEAD_STATE = 0;
	/**
	 * The alive status that make the object visible on the screen. The alive status is the
	 * default status for any object
	 */
	public static final int ALIVE_STATE = 1;

	private Bitmap bitmap;
	private float x;
	private float y;
	private float x_move = 0;
	private float y_move = 0;
	private int state = ALIVE_STATE;

	private int health = 100;
	private int orientation = -1;
	public static final int LEFT_ORIENTATION = -1;
	public static final int RIGHT_ORIENTATION = 1;
	public static final int UP_ORIENTATION = 2;
	public static final int DOWN_ORIENTATION = -2;
	private boolean stack = false;

	/**
	 * 
	 * @param bitmap
	 *            the sprite represent bitmap
	 * @param x
	 *            the initial x location
	 * @param y
	 *            the initial x location
	 */
	public SpriteObject(Bitmap bitmap, float x, float y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * @param bitmap
	 *            the sprite represent bitmap
	 * @param x
	 *            the initial x location
	 * @param y
	 *            the initial x location
	 * @param state
	 *            the initial status of the object
	 */
	public SpriteObject(Bitmap bitmap, float x, float y, int state) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		this.state = state;
	}

	/**
	 * get if the object resources is released from the memory
	 * 
	 * @return boolean
	 */
	public boolean isReleased() {
		return bitmap == null ? true : bitmap.isRecycled();
	}

	/**
	 * 
	 * @return double
	 */
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void setLocation(Point point) {
		x = point.x;
		y = point.y;
	}
	
	public Point getLocation() {
		return new Point((int)x, (int)y);
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setMoveX(float speedx) {
		x_move = speedx;
	}

	public void setMoveY(float speedy) {
		y_move = speedy;
	}

	public void setX(float x2) {
		this.x = x2;
	}

	public void setY(float y) {
		this.y = y;
	}

	/**
	 * gets the value in which the sprite object y location value will be
	 * increased by each time you call the update function
	 * 
	 * @return double
	 */
	public float getMoveY() {
		return y_move;
	}

	/**
	 * gets the value in which the sprite object x location value will be
	 * increased by each time you call the update function
	 * 
	 * @return double
	 */
	public float getMoveX() {
		return x_move;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public int getState() {
		return state;
	}

	public void setState(int s) {
		state = s;
	}

	public synchronized void draw(Canvas canvas) {
		if (isReleased())
			return;
		if (state != ALIVE_STATE)
			return;

		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y
				- (bitmap.getHeight() / 2), null);
	}

	public synchronized void update(int adj_mov) {
		if (isReleased())
			return;

		if (state != ALIVE_STATE)
			return;

		x += x_move;
		y += y_move;

		if (health <= 0) {
			state = DEAD_STATE;
		}
	}

	public int getWidth()
	{
		return bitmap.getWidth();
	}
	
	public int getHeight() {
		return bitmap.getHeight();
	}
	
	public synchronized void scale(int width, int height) {
		Bitmap oldOne = bitmap;
		bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
		oldOne.recycle();
		
		System.gc();
	}

	public synchronized void rotate(float degrees )
	{
		Matrix mtx = new Matrix();
		mtx.postRotate(degrees);
		
		int [] pixels = new int[getWidth() * getHeight()];
		int w = getWidth();
		int h = getHeight();
		
		synchronized (bitmap) {
			Bitmap oldOne = bitmap;
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), mtx, true);
			//bitmap.prepareToDraw();
			
			bitmap.getPixels(pixels, 0, w, (getWidth() - w)/2, (getHeight() - h)/2, w, h);
			
			bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
			
			if(oldOne != bitmap)
			{
				oldOne.recycle();
				oldOne = null;
			}
			
			pixels = null;
		}
		
		System.gc();
	}
	
	public synchronized void flipHorizontal()
	{
		Matrix mtx = new Matrix();
		mtx.setScale(-1,1);
		mtx.postTranslate(bitmap.getWidth(),0);
		
		synchronized (bitmap) {
	
			Bitmap old = bitmap;
			
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), mtx, true);
			 
			if(old != bitmap)
			{
				old.recycle();
			}
		}
		
		System.gc();
			
		
	}
	
	public synchronized void rotateToOrientation(int toOrientation)
	{
		float degrees = 0;
		Matrix mtx = new Matrix();
		
		if(orientation == LEFT_ORIENTATION)
		{
			switch (toOrientation) {
			case RIGHT_ORIENTATION:
				
				break;
			case DOWN_ORIENTATION:
				
				break;
			case UP_ORIENTATION:
				
				break;
			case LEFT_ORIENTATION:
				return;
				

				
			default:
				break;
			}
		}
		
		mtx.postRotate(degrees);
		
		synchronized (bitmap) {
			Bitmap oldOne = bitmap;
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), mtx, true);
			
			oldOne.recycle();
		}
	}
	
	
	public boolean cursor_selection(float cursor_x, float cursor_y) {
		float sprite_right = getRight();
		float sprite_bottom = getBottom();
		float sprite_left = getLeft();
		float sprite_top = getTop();
		
		if ((cursor_x >= sprite_left && cursor_x <= sprite_right) && (cursor_y >= sprite_top
				&& cursor_y <= sprite_bottom )) {
			
			return true;
		} else {
			return false;
		}
	}
	
	public boolean real_cursor_selection(float cursor_x, float cursor_y) {
		float sprite_right = getRight();
		float sprite_bottom = getBottom();
		float sprite_left = getLeft();
		float sprite_top = getTop();
		
		if ((cursor_x >= sprite_left && cursor_x <= sprite_right) && (cursor_y >= sprite_top
				&& cursor_y <= sprite_bottom )) {
			
			return bitmap.getPixel((int)(cursor_x-getLeft()), (int)(cursor_y-getTop())) != Color.TRANSPARENT;
		} else {
			return false;
		}
	}

	public float getLeft()
	{
		return (float) (getX() - (getBitmap().getWidth()/2));
	}
	
	public float getTop()
	{
		return getY() - (getBitmap().getHeight()/2);
	}
	
	public float getRight()
	{
		return getX() + (getBitmap().getWidth()/2);
	}
	
	public float getBottom()
	{
		return getY() + (getBitmap().getHeight()/2);
	}
	
	public void setStacked(boolean s) {
		stack = s;
	}

	public boolean getStacked() {
		return stack;
	}

	public void diminishHealth(int m) {
		health -= m;
	}

	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h) {
		health = h;
	}

	public void setOrientation(int o) {
		orientation = o;
	}

	public int getOrientation() {
		return orientation;
	}

	@Deprecated
	public boolean real_collide(SpriteObject entity)
	{
		if(real_cursor_selection(entity.getLeft(), entity.getTop()))
			return bitmap.getPixel((int)entity.getLeft(), (int) entity.getTop()) != Color.TRANSPARENT;
		
		if(real_cursor_selection(entity.getLeft(), entity.getY()))
			return bitmap.getPixel((int)entity.getLeft(), (int) entity.getY()) != Color.TRANSPARENT;
		
		if(real_cursor_selection(entity.getLeft(), entity.getBottom()))
			return bitmap.getPixel((int)entity.getLeft(), (int) entity.getBottom()) != Color.TRANSPARENT;
		
		
		
		if(real_cursor_selection(entity.getX(), entity.getTop()))
			return bitmap.getPixel((int)entity.getX(), (int) entity.getTop()) != Color.TRANSPARENT;
		
		if(real_cursor_selection(entity.getX(), entity.getY()))
			return bitmap.getPixel((int)entity.getX(), (int) entity.getY()) != Color.TRANSPARENT;
		
		if(real_cursor_selection(entity.getX(), entity.getBottom()))
			return bitmap.getPixel((int)entity.getX(), (int) entity.getBottom()) != Color.TRANSPARENT;
		
		
		if(real_cursor_selection(entity.getRight(), entity.getTop()))
			return bitmap.getPixel((int)entity.getRight(), (int) entity.getTop()) != Color.TRANSPARENT;
		
		if(real_cursor_selection(entity.getLeft(), entity.getY()))
			return bitmap.getPixel((int)entity.getRight(), (int) entity.getY()) != Color.TRANSPARENT;
		
		if(real_cursor_selection(entity.getLeft(), entity.getBottom()))
			return bitmap.getPixel((int)entity.getRight(), (int) entity.getBottom()) != Color.TRANSPARENT;
		
		return false;
	}
	
	public boolean collide(SpriteObject entity) {
		if (isReleased() || entity.isReleased())
			return false;

		if (state != ALIVE_STATE || entity.getState() != ALIVE_STATE)
			return false;

		float left, entity_left;
		float right, entity_right;
		float top, entity_top;
		float bottom, entity_bottom;
		
		/*
		 
  		right = x + bitmap.getWidth();
		entity_right = entity.getX() + entity.getBitmap().getWidth();
		
		top = y;
		entity_top = entity.getY();
		
		bottom = y + bitmap.getHeight();
		entity_bottom = entity.getY() + entity.getBitmap().getHeight();

		 */
		
		left = x;
		entity_left = entity.getX();
		
  		right = x + bitmap.getWidth();
		entity_right = entity.getX() + entity.getBitmap().getWidth();
		
		top = y;
		entity_top = entity.getY();
		
		bottom = y + bitmap.getHeight();
		entity_bottom = entity.getY() + entity.getBitmap().getHeight();
		
		if (bottom < entity_top) {
			return false;
		}
		
		if (top > entity_bottom) {
			return false;
		}
		
		if (right < entity_left) {
			return false;
		}
		
		if (left > entity_right) {
			return false;
		}
		
		return true;
	}

	public synchronized void release() {
		bitmap.recycle();
	}
	
	public static SpriteObject fromResource(Resources res , int resource_id)
	{
		return new SpriteObject(BitmapFactory.decodeResource(res, resource_id), 0, 0);
	}
	
	public static SpriteObject fromResource(Resources res , int resource_id , float x , float y)
	{
		return new SpriteObject(BitmapFactory.decodeResource(res, resource_id), x, y);
	}
	
	public static SpriteObject fromResource(Resources res , int resource_id , float x , float y , int dstWidth , int dstHeight)
	{
		return new SpriteObject(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, resource_id), dstWidth, dstHeight, false), x, y);
	}
	
	public static int getOppositeOrientation(int orientation)
	{
		return orientation * -1;
	}
	
}