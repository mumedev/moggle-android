package mume12.mumedev.moggle;

import mume12.mumedev.moggle.model.QDat;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * @author 	Joris Schelfaut
 * @since	2012-12-08
 */
public class QWorkActivity extends Activity {
	
	private QDat dat;
	public 	static final String PARCEL_KEY = "QDAT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qwork);
		
		dat = new QDat();
		this.dat.setWorkQuality(QDat.WORK_QUALITY_GOOD);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_qwork, menu);
		return true;
	}
	
	public void openQMood(View view) {
		
		EditText editText = (EditText) findViewById(R.id.editText1);
		String txt = editText.getText().toString();
		
		RadioButton rb1 = (RadioButton) findViewById(R.id.radio_work_good);
		RadioButton rb2 = (RadioButton) findViewById(R.id.radio_work_avg);
		RadioButton rb3 = (RadioButton) findViewById(R.id.radio_work_poor);
		boolean checked = rb1.isChecked() || rb2.isChecked() || rb3.isChecked(); 
		
		if (txt == null || txt.equals("") || ! checked) {
			showWarning("Error!", "Please fill in all fields.");
			return;
		}
		int work_quantity = -1;
		try {
			 work_quantity = Integer.parseInt(editText.getText().toString());
		} catch (NumberFormatException exception) {
			showWarning("Error!", "The input weren't numbers!");
			return;
		}
		
		if (work_quantity >= 0 && work_quantity <= 24) {
			
			this.dat.setWorkQuantity(work_quantity);
			
			Intent intent = new Intent(this, QMoodActivity.class);
			Bundle bundle = new Bundle();
			bundle.putParcelable(PARCEL_KEY, dat);

			intent.putExtras(bundle);
			
			startActivity(intent);
		} else {
			showWarning("Error!", "The input must be between 0 and 24!");
		}
	}
	
	private void showWarning(String title, String txt) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(txt);
		alertDialog.show();
	}

	public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.radio_work_good :
	            if (checked)
	            	
	            	this.dat.setWorkQuality(QDat.WORK_QUALITY_GOOD);
	            	
	            break;
	        case R.id.radio_work_avg :
	            if (checked)
	            	
	            	this.dat.setWorkQuality(QDat.WORK_QUALITY_AVG);
	            	
	            break;
	        case R.id.radio_work_poor :
	            if (checked)
	            	
	            	this.dat.setWorkQuality(QDat.WORK_QUALITY_POOR);
	            	
	            break;
	    }
	}
	
}
