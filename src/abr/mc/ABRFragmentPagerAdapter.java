package abr.mc;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ABRFragmentPagerAdapter extends FragmentPagerAdapter
{
	
	private List<Fragment> allFrags;
	
	public ABRFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments)
	{
		super(fm);
		this.allFrags = fragments;		
	}

	
	@Override
	public Fragment getItem( int position )
	{
		
		return this.allFrags.get(position);
	}

	@Override
	public int getCount()
	{		
		return this.allFrags.size();
	}
	


}
