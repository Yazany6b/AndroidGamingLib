package y6b.Gaming.Games2DLib.CustomSpriteObjects;

import java.util.ArrayList;

import y6b.Gaming.Games2DLib.SpriteObject;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class DockSpriteObject extends AnimatedSpriteObject {

	public enum ViewDirection { Vertical , Horizontal}
	public enum TextLocation { Center , Left , Right , Bottom , Top}
	
	public static interface IDockItemSelectedListener
	{
		void onItemSelected(DockItem sender);
	}
	
	public static class DockItem 
	{
		private Bitmap bitmap;
		private int id;
		private String text = "";
		private Paint paint;
		private TextLocation location;
		public DockItem(Bitmap bitmap, int id) {
			super();
			this.bitmap = bitmap;
			this.id = id;
			paint = new Paint();
			paint.setTextSize(20);
			paint.setColor(Color.RED);
			location = TextLocation.Center;
		}
		
		public DockItem(Bitmap bitmap) {
			super();
			this.bitmap = bitmap;
			id = 0;
			paint = new Paint();
			paint.setTextSize(20);
			paint.setColor(Color.RED);
			location = TextLocation.Center;
		}

		public Bitmap getBitmap() {
			return bitmap;
		}

		public void setBitmap(Bitmap bitmap) {
			this.bitmap = bitmap;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
		
		public DockItemSpriteObject toSpriteObject()
		{
			DockItemSpriteObject item = new DockItemSpriteObject(bitmap , 0 , 0);
			item.setItem(this);
			return item;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Paint getPaint() {
			return paint;
		}

		public void setPaint(Paint paint) {
			this.paint = paint;
		}

		public TextLocation getLocation() {
			return location;
		}

		public void setLocation(TextLocation location) {
			this.location = location;
		}
	}
	
	public static class DockItemSpriteObject extends AnimatedSpriteObject
	{
		private DockItem item;
		
		public DockItemSpriteObject(Bitmap bitmap, int x, int y) {
			super(bitmap, x, y);
		}
		
		@Override
		public void draw(Canvas canvas) {
			super.draw(canvas);
			if(item.getText().trim() != "")
			{
				switch(item.getLocation())
				{
				case Center:
					canvas.drawText(item.getText(),(float) getX() - item.getPaint().measureText(item.getText())/2,(float) getY() - (item.getPaint().getTextSize()/2)  , item.getPaint());
					break;
					
					case Left:
						canvas.drawText(item.getText(),(float) getLeft(),(float) getTop()  , item.getPaint());
						break;
						
					case Top:
						canvas.drawText(item.getText(),(float) getX() - (item.getPaint().measureText(item.getText())/2),(float) getTop() , item.getPaint());
						break;
						
					case Right:
						canvas.drawText(item.getText(),(float) getRight() - item.getPaint().measureText(item.getText()),(float) getY() - (item.getPaint().getTextSize()/2)  , item.getPaint());
						break;
						
					case Bottom:
						canvas.drawText(item.getText(),(float) getX() - item.getPaint().measureText(item.getText())/2,(float) getBottom() - (item.getPaint().getTextSize())  , item.getPaint());
						break;
				}
			}
		}
		
		public DockItemSpriteObject(Bitmap bitmap, int x, int y, int state) {
			super(bitmap, x, y, state);
		}
		public DockItemSpriteObject(SpriteObject scr) {
			super(scr);
		}

		public DockItem getItem() {
			return item;
		}

		public void setItem(DockItem item) {
			this.item = item;
		}
	}
	
	private ViewDirection view_direction = ViewDirection.Vertical;
	private int leftIndent = 10;
	private int topIndent = 0;
	private int rightIndent = 10;
	private int bottomIndent = 0;
	private int spacing = 3;
	
	private boolean selectionExist = false;
	
	private ArrayList<DockItemSpriteObject> items = new ArrayList<DockSpriteObject.DockItemSpriteObject>();
	private ArrayList<IDockItemSelectedListener> listeners = new ArrayList<DockSpriteObject.IDockItemSelectedListener>();
	private DockItemSpriteObject selectedItem = null;
	
	private SpriteObject marker;
	
	public DockSpriteObject(Bitmap bitmap, float x, float y, int state) {
		super(bitmap, x, y, state);
		
		marker = new SpriteObject(null,0,0);
		hideSelection();
		setSelectionColor(Color.BLACK);
	}

	public DockSpriteObject(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);

		marker = new SpriteObject(null,0,0);
		hideSelection();
		setSelectionColor(Color.BLACK);
	}

	public DockSpriteObject(SpriteObject scr) {
		super(scr);

		marker = new SpriteObject(null,0,0);
		hideSelection();
		setSelectionColor(Color.BLACK);
	}	
	
	private void setSelectionAt(DockItemSpriteObject item)
	{
		selectedItem = item;
		selectionExist = true;
		marker.setX((float) item.getX());
		marker.setY((float) item.getY());
		showSelection();
		
		notifyAllListeners(item.getItem());
	}
	
	private void scaleItemsToFit()
	{
		if(view_direction == ViewDirection.Vertical)
		{
			int w = (getBitmap().getWidth() / items.size()) - leftIndent - rightIndent - (spacing * (items.size()-1) );
			int h =  getBitmap().getHeight() - topIndent - bottomIndent ;
			if(items != null)
				for(DockItemSpriteObject x : items)
					x.scale(w, h);
			if(marker != null)
				marker.scale(w, h);
		}else
		{
			int w = getBitmap().getWidth()  - leftIndent - rightIndent;
			int h = (getBitmap().getHeight() / items.size()) - topIndent - bottomIndent - (spacing * (items.size()-1) );
			if(items != null)
				for(DockItemSpriteObject x : items)
					x.scale(w, h);
			if(marker != null)
				marker.scale(w, h);
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		marker.draw(canvas);
		
		for(DockItemSpriteObject item : items){
			item.draw(canvas);
		}
	}
	
	@Override
	public synchronized void update(int adj_mov) {
		super.update(adj_mov);
		
		for(DockItemSpriteObject item : items)
			item.update(adj_mov);
	}
	
	@Override
	public synchronized void scale(int width, int height) {
		super.scale(width, height);
		
		scaleItemsToFit();
	}
	
	public void masture(int game_width , int game_height)
	{
		
	}
	
	@Override
	public void setLocation(Point point) {
		super.setLocation(point);
		invalidate();
	}
	
	@Override
	public void setX(float x) {
		super.setX(x);
		invalidate();
	}
	
	@Override
	public void setY(float y) {
		super.setY(y);
		invalidate();
	}
	
	@Override
	public void setMoveX(float speedx) {
		super.setMoveX(speedx);
		
		//for(DockItemSpriteObject item : items)
		//	item.setMoveX(speedx);
	}
	
	@Override
	public void setMoveY(float speedy) {
		super.setMoveY(speedy);
		
		//for(DockItemSpriteObject item : items)
		//	item.setMoveY(speedy);
	}
	
	public void invalidate()
	{
		if(view_direction == ViewDirection.Vertical)
		{
			int startX = (int) (getLeft() + leftIndent);
			
			for (DockItemSpriteObject item : items) {
				item.setX(startX + (item.getBitmap().getWidth()/2));
				item.setY((getTop()+topIndent) + (item.getBitmap().getHeight()/2));
				
				startX = (int) (item.getRight() + spacing);
			}
		}else
		{
			int startY = (int) (getTop() + topIndent);
			
			for (DockItemSpriteObject item : items) {
				item.setX((getLeft()+leftIndent) + (item.getBitmap().getWidth()/2));
				item.setY(startY + (item.getBitmap().getHeight()/2));
				
				startY = (int) (item.getBottom() + spacing);
			}
		}
	}
	
	public boolean containsDockItem(DockItem item)
	{
		for(DockItemSpriteObject xitem : items)
			if(xitem.getItem().equals(item))
				return true;
		return false;
	}
	
	public boolean addDockItem(DockItem item)
	{
		if(!containsDockItem(item))
		{
			boolean res = items.add(item.toSpriteObject());
			if(res)
			{
				scaleItemsToFit();
				invalidate();
			}
			return res;
		}
		
		return false;
	}
	
	public boolean removeDockItem(DockItem item)
	{
		int index = 0;
		boolean found = false;
		for(DockItemSpriteObject xitem : items)
		{
			if(xitem.getItem().equals(item))
			{
				found = true;
				break;
			}
			index++;
		}
		
		if(found)
		{
			items.remove(index);
			invalidate();
			return true;
		}
		
		return false;
	}
	
	public boolean hasItems()
	{
		return items.isEmpty();
	}
	
	public boolean addItemSelectedListener(IDockItemSelectedListener listener)
	{
		if(!listeners.contains(listener))
		{
			return listeners.add(listener);
		}
		
		return false;
	}
	
	public boolean removeItemSelectedListener(IDockItemSelectedListener listener)
	{
		if(listeners.contains(listener))
		{
			return listeners.remove(listener);
		}
		
		return false;
	}
	
	protected void notifyAllListeners(DockItem sender)
	{
		for ( IDockItemSelectedListener listener : listeners)
			listener.onItemSelected(sender);
	}
	
	public void cursorSelectionChanged(int cursor_x , int cursor_y)
	{
		for (DockItemSpriteObject item : items) {
			if(item.cursor_selection(cursor_x, cursor_y))
			{
				setSelectionAt(item);
			}
		}
	}
	
	public void setSelectionBitmap(Bitmap bitmap)
	{
		marker.setBitmap(bitmap);
	}
	
	public void setSelectionColor(int color)
	{
		Bitmap bitmap = Bitmap.createBitmap((int)(getRight() - getLeft()) - (leftIndent/2),(int)(getBottom() - getTop()) - (topIndent/2), Config.RGB_565);
		
		for (int i = 0; i < bitmap.getWidth(); i++) {
			for (int j = 0; j < bitmap.getHeight(); j++) {
				bitmap.setPixel(i, j, color);
			}
		}
		
		marker.setBitmap(bitmap);
	}
	
	public void hideSelection()
	{
		marker.setState(DEAD_STATE);
	}
	
	public void showSelection() {
		marker.setState(ALIVE_STATE);
	}
	
	public void select(DockItem item)
	{
		for (DockItemSpriteObject xitem : items) {
			if(item.equals(xitem.getItem()))
			{
				setSelectionAt(xitem);
			}
		}
	}
	
	public void select(int index)
	{
		setSelectionAt(items.get(index));
	}

	public int getLeftIndent() {
		return leftIndent;
	}

	public void setLeftIndent(int leftIndent) {
		this.leftIndent = leftIndent;
	}

	public int getTopIndent() {
		return topIndent;
	}

	public void setTopIndent(int topIndent) {
		this.topIndent = topIndent;
	}
	
	public int getRightIndent() {
		return rightIndent;
	}

	public void setRightIndent(int rightIndent) {
		this.rightIndent = rightIndent;
	}

	public int getBottomIndent() {
		return bottomIndent;
	}

	public void setBottomIndent(int bottomIndent) {
		this.bottomIndent = bottomIndent;
	}

	public DockItem getSelectedItem() {
		return selectedItem.getItem();
	}

	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	public boolean isSelectionExist() {
		return selectionExist;
	}

	public void removeSelection()
	{
		selectedItem = null;
		selectionExist = false;
		hideSelection();
	}

	public ViewDirection getViewDirection() {
		return view_direction;
	}

	public void setViewDirection(ViewDirection viewDirection) {
		this.view_direction = viewDirection;
	}
	
	
}
