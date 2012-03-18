package abr.view;


import abr.actionmenu.ActionItem;
import abr.mc.R;
import abr.model.ABRFragmentPagerAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;


public class ABRMainView extends FragmentActivity
{


	private ABRFragmentPagerAdapter FPAdapter;
	private ViewPager mPager;
	public static Context context;
	TabHost mTabHost;
	ActionItem addAction = new ActionItem();
	
	public void onCreate( Bundle SavedInstanceState )
	{
		super.onCreate(SavedInstanceState);
		context = this;
		setContentView(R.layout.mainview);
		startPaging();

		if (SavedInstanceState != null)
		{
			mTabHost.setCurrentTabByTag(SavedInstanceState.getString("tab"));
		}

		Intent i = new Intent(this, Information.class);
		startActivity(i);
	}

	protected void onPause()
	{
		super.onPause();
	}

	private void startPaging()
	{
		// create a tabhost that will provide the Tab Spec
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		// reference the Viewpager (comes from XML)
		mPager = (ViewPager) findViewById(R.id.pager);

		// create an adapter instance and pass the Tabhost and the Viewpager
		FPAdapter = new ABRFragmentPagerAdapter(this, mTabHost, mPager);

		// now use the function to add Fragments to the Tabs
		FPAdapter.addTab(mTabHost.newTabSpec("Calculate").setIndicator("Bonus"), Calculate.class, null, 50);
		FPAdapter.addTab(mTabHost.newTabSpec("Forecast").setIndicator("Vorhersage"), Forecast.class, null, 50);
	}

	// creates the options menu
	public boolean onCreateOptionsMenu( Menu menu )
	{
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	// take action if an options menu-item is pressed
	public boolean onOptionsItemSelected( MenuItem item )
	{
		if (item.getItemId() == R.id.options)
		{

		}

		else if (item.getItemId() == R.id.about)
		{
			AlertDialog a = new AlertDialog.Builder(this).create();
			a.setTitle(("Info"));
			a.setMessage(getResources().getString(R.string.about));
			a.setButton("zurück", new DialogInterface.OnClickListener()
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
