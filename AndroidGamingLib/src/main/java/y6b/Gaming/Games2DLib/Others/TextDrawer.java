package y6b.Gaming.Games2DLib.Others;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.SurfaceHolder;
import y6b.Gaming.Games2DLib.FreamDrawer;
import y6b.Gaming.Games2DLib.InputObject;

public class TextDrawer extends FreamDrawer{

	private Paint paint;
	private Point point;
	private String text ;
	
	private int canvas_width = 0;
	private int canvas_height = 0;
	
	public TextDrawer(Context context) {
		super(context);

		paint = new Paint();
		
		paint.setColor(Color.BLACK);
		
		point = new Point();
		
		text = "";
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(text, point.x, point.y, paint);
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void sizeCalculated(int game_width, int game_height) {
		canvas_height = game_height;
		canvas_width = game_width;
		
	}

	@Override
	public void processMotionEvent(InputObject input) {
		
		
	}

	@Override
	public void processKeyEvent(InputObject input) {
		
		
	}

	@Override
	public void processOrientationEvent(float[] orientation) {
		
		
	}

	@Override
	public void release() {
		
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		
	}

	@Override
	public void gameStarted() {
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCanvasWidth() {
		return canvas_width;
	}

	public int getCanvasHeight() {
		return canvas_height;
	}
	

}
