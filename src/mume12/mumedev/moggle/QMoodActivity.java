package mume12.mumedev.moggle;

import java.util.Calendar;

import mume12.mumedev.moggle.db.DatabaseHandler;
import mume12.mumedev.moggle.model.QDat;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * @author 	Joris Schelfaut
 * @since	2012-12-08
 */
public class QMoodActivity extends Activity implements OnSeekBarChangeListener {
	
	private int mood_quantified = 5;
	private SeekBar seekBar;
	private TextView sbLabel;
	private QDat dat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qmood);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setOnSeekBarChangeListener(this);
		sbLabel = (TextView) findViewById(R.id.seekbar_label);

		seekBar.setMax(10);
		seekBar.setProgress(mood_quantified);
		sbLabel.setText(String.format("%d", mood_quantified));
		
		Bundle bundle = this.getIntent().getExtras();
		dat = bundle.getParcelable(QWorkActivity.PARCEL_KEY);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_qmood, menu);
		return true;
	}
	
	public void openHome(View view) {
		
		dat.setMoodQuantified(mood_quantified);
		dat.setDate(Calendar.getInstance());
		
		DatabaseHandler handler = new DatabaseHandler(this);
		handler.getQDatCRUD().insert(dat);
		handler.close();
		
		startActivity(new Intent(this, HomeActivity.class));
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		if (arg2) {
			mood_quantified = arg1;
			sbLabel.setText(String.format("%d", mood_quantified));
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {}

}
