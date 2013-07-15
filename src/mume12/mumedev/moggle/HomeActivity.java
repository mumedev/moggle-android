package mume12.mumedev.moggle;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * @author 	Joris Schelfaut
 * @since	2012-12-08
 */
public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	public void openQWork(View view) {
		startActivity(new Intent(this, QWorkActivity.class));
	}
	
	public void openDataVis(View view) {
		startActivity(new Intent(this, DataVisActivity.class));
	}
}
