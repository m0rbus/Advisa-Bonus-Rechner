package abr.model;

import java.util.ArrayList;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


public class ABRFragmentPagerAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener,
		ViewPager.OnPageChangeListener
{

	
	private Context mContext;
	public TabHost mTabHost;
	private ViewPager mViewPager;
	private ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
	
			/**
			 	* nested class
			 		* */
			static class TabInfo
			{
					@SuppressWarnings("unused")
					private String tag;
					private Class<?> clss;
					private Bundle args;

					TabInfo(String _tag, Class<?> _class, Bundle _args)
					{
						tag = _tag;
						clss = _class;
						args = _args;
					}
			}

			/**
			 * Dummy Class which is used to fill the tabhost with not visible content
			 * to overcome the 
			 * */
			static class DummyTabFactory implements TabHost.TabContentFactory
			{
				private final Context mContext;

				public DummyTabFactory(Context context)
				{
					mContext = context;
				}

		
				public View createTabContent( String tag )
				{
					View v = new View(mContext);
					v.setMinimumWidth(0);
					v.setMinimumHeight(0);
					return v;
				}
			}
			
	

	/**
	 * constructor to get the needed tabhost and viewpager and pass it into the local values
	 * */
	public ABRFragmentPagerAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager)
			{
				super(activity.getSupportFragmentManager());
				mContext = activity;
	            mTabHost = tabHost;
	            mViewPager = pager;
	            mTabHost.setOnTabChangedListener(this);
	            mViewPager.setAdapter(this);
	            mViewPager.setOnPageChangeListener(this);
			}

	
	/**
	 * Fragments have to be addable to the tabhost, therefore we need this function
	 * */
	public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args, Integer height) 
	{
        tabSpec.setContent(new DummyTabFactory(mContext));
        String tag = tabSpec.getTag();

        TabInfo info = new TabInfo(tag, clss, args);
        mTabs.add(info);
        mTabHost.addTab(tabSpec);
        
        int totalTabs = mTabHost.getTabWidget().getChildCount();        
        //set the height of the tabs
        mTabHost.getTabWidget().getChildAt(totalTabs-1).getLayoutParams().height = height;
        notifyDataSetChanged();
    }

	/**
	 * return the needed item 
	 * */
	@Override
	public Fragment getItem( int position )
	{
		TabInfo info = mTabs.get(position);
        return Fragment.instantiate(mContext, info.clss.getName(), info.args);
	}

	/**
	 * returns the item count (how many fragments does the viewpager-adapter provide)
	 * */
	@Override
	public int getCount()
	{
		return mTabs.size();
	}


	public void onPageScrollStateChanged( int arg0 )
	{
	

	}


	public void onPageScrolled( int arg0, float arg1, int arg2 )
	{
		

	}


	public void onPageSelected( int position )
	{
		TabWidget widget = mTabHost.getTabWidget();
        int oldFocusability = widget.getDescendantFocusability();
        widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        mTabHost.setCurrentTab(position);
        widget.setDescendantFocusability(oldFocusability);

	}


	public void onTabChanged( String arg0 )
	{
		int position = mTabHost.getCurrentTab();
        mViewPager.setCurrentItem(position);
	}


}
