package abr.mc;

import java.util.List;
import java.util.Vector;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



public class ABRMainView extends FragmentActivity
{
	
	
	private ABRFragmentPagerAdapter FPAdapter;
	private ViewPager mPager;
	public static Context context;
	
	public void onCreate(Bundle SavedInstanceState)
	{
		super.onCreate(SavedInstanceState);
		context = this;
		setContentView(R.layout.mainview);  
		startPaging();
		Intent i = new Intent(this, Information.class);
		startActivity(i);
	}
	
	protected void onPause()
	{
		super.onPause();
	}
	
	private void startPaging() 
	{
		
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Calculate.newInstance("blabla"));
		fragments.add(Forecast.newInstance("blabla"));
		
		
	    mPager = (ViewPager) findViewById(R.id.pager);
	    FPAdapter = new ABRFragmentPagerAdapter(getSupportFragmentManager(), fragments);
	    mPager.setAdapter(FPAdapter);	    
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}


	public boolean onOptionsItemSelected(MenuItem item) {

		
		if (item.getItemId() == R.id.options) {
			
		}
		
		else if (item.getItemId() == R.id.about) 
		{
			AlertDialog a = new AlertDialog.Builder(this).create();
			a.setTitle(("Info"));
			a.setMessage(getResources().getString(R.string.about));
			a.setButton("zurück",
					new DialogInterface.OnClickListener()
					{
						public void onClick( DialogInterface dialog, int which )
						{
							return;
						}
					});
			a.show();
		} 
		else if (item.getItemId() == R.id.quit) 
		{
			finish();
		} 
		return true;
	}
}
