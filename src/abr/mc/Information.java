package abr.mc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Information extends Activity implements OnClickListener
{
	
	LinearLayout mLL = new LinearLayout(ABRMainView.context);
	ImageView image = new ImageView(ABRMainView.context);
	
	public void onCreate(Bundle SavedInstanceState)
	{
		super.onCreate(SavedInstanceState);
		image.setImageResource(R.drawable.infobg);
		image.setClickable(true);
		image.setOnClickListener(this);
		image.setScaleType(ScaleType.FIT_XY);
		//image.setAlpha(150);
		image.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
				  LayoutParams.WRAP_CONTENT));
		mLL.addView(image);	
		setContentView(mLL);		
	}
	
	
	public void onClick( View v )
	{
		this.finish();		
	}

}
