package abr.mc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Information extends Activity implements OnClickListener
{
	View bla;
	ImageView image;
	
	public void onCreate(Bundle SavedInstanceState)
	{
		super.onCreate(SavedInstanceState);
		setContentView(R.layout.info);
		image = (ImageView)findViewById(R.id.infoimageview);
		image.setAlpha(150);
		bla = (View)findViewById(R.id.infoimageview);
		bla.setOnClickListener(this);
	}
	
	public void onClick( View v )
	{
		this.finish();		
	}

}
