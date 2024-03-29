package abr.view;


import abr.actionmenu.ActionItem;
import abr.actionmenu.QuickAction;
import abr.view.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Calculate extends Fragment implements OnClickListener
{

	public EditText tfield_salary, tfield_volume;
	public static Integer real_bonus;
	public static Double salborder, bonuscap, keeper;
	public Button button_calc, button_test;
	ActionItem ai;
	final QuickAction mQuickAction = new QuickAction(ABRMainView.context);

	public static Calculate newInstance( String title )
	{

		Calculate pageFragment = new Calculate();
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

	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
	{
		super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.calculate, container, false);

		tfield_volume = (EditText) view.findViewById(R.id.volume);
		tfield_salary = (EditText) view.findViewById(R.id.salary);
		button_calc = (Button) view.findViewById(R.id.button_calculate);
		button_calc.setOnClickListener(this);
		button_test = (Button) view.findViewById(R.id.test);
		button_test.setOnClickListener(this);

		ai = new ActionItem(1, "Der Stefan is pl���t", getResources().getDrawable(R.drawable.ic_add));

		mQuickAction.addActionItem(ai);

		//create an onclick listener for the action item menu
		mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener()
		{

			public void onItemClick( QuickAction quickAction, int pos, int actionId )
			{
				ActionItem actionItem = quickAction.getActionItem(pos);

				if (actionId == 1)
				{
					Toast.makeText(ABRMainView.context, "Add item selected", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(ABRMainView.context, actionItem.getTitle() + " selected",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		mQuickAction.setOnDismissListener(new QuickAction.OnDismissListener()
		{
			public void onDismiss()
			{
				//TODO add something that will be done, if the dialogue gets dismissed
			}
		});


		return view;
	}


	public void onClick( View v )
	{

		if (v.getId() == R.id.button_calculate)
		{
			// The Softkeyboard shall be hidden after pressing the button
			InputMethodManager imm = (InputMethodManager) super.getActivity().getSystemService(
					Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(tfield_volume.getWindowToken(), 0);

			// Calculating the bonuscap of the Salary
			calc_bonuscap();
			// Calculating the final Bonus with the given bonuscap
			real_bonus = bonus_calc(bonuscap);
			// Put the result on the screen with a simple toast message
			longtoast(real_bonus.toString());
		}
		else if (v.getId() == R.id.test)
		{
			mQuickAction.show(v);
			mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
		}
	}


	private void calc_bonuscap()
	{
		if (tfield_salary.getText().toString().equals("") || tfield_volume.getText().toString().equals(""))
		{
			Toast inputerror = Toast.makeText(super.getActivity().getBaseContext(), "Falsche Eingabe",
					Toast.LENGTH_LONG);
			inputerror.show();
		}
		else
		{
			keeper = Double.parseDouble(tfield_salary.getText().toString());
			salborder = (Double.parseDouble(tfield_salary.getText().toString()) * 2);
			bonuscap = (Double.parseDouble(tfield_volume.getText().toString())) - salborder;
		}
	}

	private Integer bonus_calc( Double bonusvalue )
	{
		int holder = 0;

		if (tfield_salary.getText().toString().equals("") || tfield_volume.getText().toString().equals(""))
		{
			Toast inputerror = Toast.makeText(super.getActivity().getBaseContext(), "Falsche Eingabe",
					Toast.LENGTH_LONG);
			inputerror.show();
		}
		else
		{
			if (bonuscap <= 0)
			{
				Toast nomoney = Toast.makeText(super.getActivity().getBaseContext(),
						"Leider gibt es keinen Bonus! :(", Toast.LENGTH_LONG);
				nomoney.show();
			}
			else if (bonusvalue > ((keeper * 4) / 2) || bonusvalue < ((keeper * 5) / 2))
			{
				holder = (int) (bonusvalue * 0.07);
			}
			else if (bonusvalue > ((keeper * 5) / 2) || bonusvalue < ((keeper * 6) / 2))
			{
				holder = (int) (bonusvalue * 0.1);
			}
			else if (bonusvalue > ((keeper * 6) / 2) || bonusvalue < ((keeper * 7) / 2))
			{
				holder = (int) (bonusvalue * 0.13);
			}
			else if (bonusvalue > ((keeper * 7) / 2) || bonusvalue < ((keeper * 8) / 2))
			{
				holder = (int) (bonusvalue * 0.16);
			}
			else if (bonusvalue > ((keeper * 8) / 2) || bonusvalue < ((keeper * 9) / 2))
			{
				holder = (int) (bonusvalue * 0.19);
			}
		}
		return holder;
	}

	public void longtoast( String text )
	{

		Toast toast = Toast.makeText(super.getActivity().getBaseContext(), "Der aktuelle Bonus betr�gt: "
				+ text + " �" + " Brutto", Toast.LENGTH_LONG);
		toast.show();
	}
}
