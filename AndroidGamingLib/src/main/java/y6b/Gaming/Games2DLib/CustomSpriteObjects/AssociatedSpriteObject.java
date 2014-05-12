package y6b.Gaming.Games2DLib.CustomSpriteObjects;

import y6b.Gaming.Games2DLib.SpriteObject;
import android.graphics.Bitmap;

public class AssociatedSpriteObject extends TimedSpriteObject{

	private SpriteObject associatedObject;
	
	public AssociatedSpriteObject(Bitmap bitmap, float x, float y, int state) {
		super(bitmap, x, y, state);
		// TODO Auto-generated constructor stub
	}

	public AssociatedSpriteObject(Bitmap bitmap, float x, float y) {
		super(bitmap, x, y);
		// TODO Auto-generated constructor stub
	}

	public AssociatedSpriteObject(SpriteObject scr) {
		super(scr);
		// TODO Auto-generated constructor stub
	}

	public SpriteObject getAssociatedObject() {
		return associatedObject;
	}

	public void setAssociatedObject(SpriteObject associatedObject) {
		this.associatedObject = associatedObject;
	}
}
