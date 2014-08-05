package com.example.gaminglibproject;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.Window;

import y6b.Gaming.Games2DLib.Extended.MultiFreamDrawer;
import y6b.Gaming.Games2DLib.FreamGameView;
import y6b.Gaming.Games2DLib.GameView;
import y6b.Gaming.Games2DLib.Others.BackGroundDrawer;
import y6b.Gaming.Games2DLib.Others.FreamsPerSecondDrawer;
import y6b.Gaming.Games2DLib.Others.TextDrawer;
import y6b.tests.gaminglibtest.R;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        MultiFreamDrawer drawer = new MultiFreamDrawer(this);

        TextDrawer txtDrawer = new TextDrawer(this);
        txtDrawer.setText("I'm Yazan");
        txtDrawer.setPoint(new Point(100,100));
        txtDrawer.getPaint().setTextSize(20);
        txtDrawer.getPaint().setAntiAlias(true);
        txtDrawer.getPaint().setColor(Color.WHITE);

        drawer.addFreamDrawer(new BackGroundDrawer(this, Color.BLUE));
        drawer.addFreamDrawer(new FreamsPerSecondDrawer(this));
        drawer.addFreamDrawer(txtDrawer);

        setContentView(new FreamGameView(this,drawer));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
