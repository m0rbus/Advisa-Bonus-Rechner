package abr.mc;

import java.util.List;
import java.util.Vector;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


public class ABRMainView extends FragmentActivity 
{
	
	
	private ABRFragmentPagerAdapter FPAdapter;
	private ViewPager mPager;
	public static Context context;
	
	public void onCreate(Bundle SavedInstanceState)
	{
		super.onCreate(SavedInstanceState);
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
		//Make a container to hold all fragments that will be shown in the ViewPager
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Calculate.newInstance("blabla"));
		fragments.add(Forecast.newInstance("blabla"));
		
		
	    mPager = (ViewPager) findViewById(R.id.pager);
	    FPAdapter = new ABRFragmentPagerAdapter(getSupportFragmentManager(), fragments);
	    mPager.setAdapter(FPAdapter);
	    
	}
}
