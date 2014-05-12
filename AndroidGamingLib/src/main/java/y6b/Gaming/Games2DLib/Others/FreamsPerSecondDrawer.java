package y6b.Gaming.Games2DLib.Others;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

public class FreamsPerSecondDrawer extends TextDrawer{

	private long seconds = 0;
	
	private int freams = 0;
	
	public FreamsPerSecondDrawer(Context context) {
		super(context);
		
		Paint p = getPaint();
		
		p.setTextSize(20f);
		
		p.setColor(Color.RED);
	}
 
	@Override
	public void update() {
	
		if(System.currentTimeMillis() - seconds > 1000)
		{
			setText(freams + "F/S");
			freams = 0;
			seconds = System.currentTimeMillis();
		}else
			freams ++;
		
		super.update();
	}
	
	@Override
	public void gameStarted() {
	
		seconds = System.currentTimeMillis();
		super.gameStarted();
	}
}
