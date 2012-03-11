package abr.mc;


import java.util.ArrayList;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Forecast extends Fragment implements OnClickListener
{
	
	static Double salary;
	ArrayList<Double> volume_holder = new ArrayList<Double>();
	Button button_ok;
	EditText field1,field2,field3,field4,volume;
	View view;
	
	
	public static Forecast newInstance( String title )
	{

		Forecast pageFragment = new Forecast();
		Bundle bundle = new Bundle();
		bundle.putString("title", title);
		pageFragment.setArguments(bundle);
		return pageFragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		view = inflater.inflate(R.layout.forecast, container, false);    
        
        setup_refs();
        return view;  
	}
	
	
	private void setup_refs()
	{
		button_ok = (Button)view.findViewById(R.id.button_ok);
        button_ok.setOnClickListener(this);
        
        field1 = (EditText)view.findViewById(R.id.empty_tfield1);
        field2 = (EditText)view.findViewById(R.id.empty_tfield2);
        field3 = (EditText)view.findViewById(R.id.empty_tfield3);
        field4 = (EditText)view.findViewById(R.id.empty_tfield4);
        volume = (EditText)view.findViewById(R.id.salary2);
		
	}

	public void onClick( View v )
	{
		super.getActivity().getBaseContext();
		//The Softkeyboard shall be hidden after pressing the button
		InputMethodManager imm = (InputMethodManager)super.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE); 
		imm.hideSoftInputFromWindow(field1.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(field2.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(field3.getWindowToken(), 0);
		imm.hideSoftInputFromWindow(field4.getWindowToken(), 0);
				
		if(volume.getText().toString().isEmpty())
		{
			Toast toast = Toast.makeText(super.getActivity().getBaseContext(), "Bitte geben Sie ein Brutto Jahresgehalt ein !", Toast.LENGTH_LONG);			
			toast.show();
		}
		else
		{
			get_volumes();
				
			longtoast(calc_forecast());
		}
		volume_holder.clear();
		
	}
	
	public void longtoast(String text)
	{
		Toast toast = Toast.makeText(super.getActivity().getBaseContext(), "Der vorraussichtliche Bonus beträgt: " + text+" €" + " Brutto", Toast.LENGTH_LONG);			
		toast.show();
	}

	private void get_volumes()
	{
			if( !field1.getText().toString().isEmpty())
			{
				volume_holder.add( Double.parseDouble( field1.getText().toString()) );	
			}
			if( !field2.getText().toString().isEmpty())
			{
				volume_holder.add( Double.parseDouble( field2.getText().toString()) );	
			}
			if( !field3.getText().toString().isEmpty())
			{
				volume_holder.add( Double.parseDouble( field3.getText().toString()) );	
			}
			if( !field4.getText().toString().isEmpty())
			{
				volume_holder.add( Double.parseDouble( field4.getText().toString()) );	
			}
			
			salary = Double.parseDouble( volume.getText().toString());
		
	}
	
	private String calc_forecast()
	{
		Double buffer=0.0;  //will have all entered volumes summed up
		Double meridian,bonuscap,transaction;
		Integer divider = volume_holder.size(); //this is to calculate the average
		Integer forecast_result = 0; //here, the final result will be stored and returned
		
		//addup all entered volumes
		for(int i=0; i < volume_holder.size(); i++)
		{
			buffer += volume_holder.get(i);
			
		}		
		
		//meridian will hold the average of the entered volumes
		meridian = buffer / divider;
		
		//transaction is the estimated transaction from 6 month
		transaction = buffer + ( (6.0-divider.doubleValue())*meridian );
		
		//The bonuscap is the actual amount on which the bonus will be applied
		bonuscap = transaction - (salary*2);		
		
		if(bonuscap <= 0)
		{
			Toast nomoney = Toast.makeText(super.getActivity().getBaseContext(), "Leider gibt es keinen Bonus! :(", Toast.LENGTH_LONG);			
			nomoney.show();
		}
		else if(bonuscap > ((salary*4)/2) ||  bonuscap < ((salary*5)/2) )
		{	
			forecast_result = (int) (bonuscap * 0.07);
		}
		else if(bonuscap > ((salary*5)/2) ||  bonuscap < ((salary*6)/2))
		{
			forecast_result = (int) (bonuscap * 0.1);
		}
		else if(bonuscap > ((salary*6)/2) ||  bonuscap < ((salary*7)/2))
		{
			forecast_result = (int) (bonuscap * 0.13);
		}
		else if(bonuscap > ((salary*7)/2) ||  bonuscap < ((salary*8)/2))
		{
			forecast_result = (int) (bonuscap * 0.16);
		}
		else if(bonuscap > ((salary*8)/2) ||  bonuscap < ((salary*9)/2))
		{
			forecast_result = (int) (bonuscap * 0.19);
		}
		
		
		return forecast_result.toString();
		
	}
	
	

}
