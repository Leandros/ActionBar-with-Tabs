package de.arvidg.exampleactionbartabs;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class StartActivity extends Activity {
	public static Context appContext;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        appContext = getApplicationContext();

       //ActionBar
        ActionBar actionbar = getActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.Tab PlayerTab = actionbar.newTab().setText("Fragment A");
        ActionBar.Tab StationsTab = actionbar.newTab().setText("Fragment B");
        
        Fragment PlayerFragment = new AFragment();
        Fragment StationsFragment = new BFragment();

        PlayerTab.setTabListener(new MyTabsListener(PlayerFragment));
        StationsTab.setTabListener(new MyTabsListener(StationsFragment));

        actionbar.addTab(PlayerTab);
        actionbar.addTab(StationsTab);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.menuitem_search:
				Toast.makeText(appContext, "search", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menuitem_add:
				Toast.makeText(appContext, "add", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menuitem_share:
				Toast.makeText(appContext, "share", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menuitem_feedback:
				Toast.makeText(appContext, "feedback", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menuitem_about:
				Toast.makeText(appContext, "about", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.menuitem_quit:
				Toast.makeText(appContext, "quit", Toast.LENGTH_SHORT).show();
				return true;
		}
		return false;
	}
	
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
    
}



class MyTabsListener implements ActionBar.TabListener {
	public Fragment fragment;
	
	public MyTabsListener(Fragment fragment) {
		this.fragment = fragment;
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Toast.makeText(StartActivity.appContext, "Reselected!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
	
}