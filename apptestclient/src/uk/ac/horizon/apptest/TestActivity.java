package uk.ac.horizon.apptest;

import android.app.Activity;
import android.os.Bundle;
import uk.ac.horizon.apptest.R;

public class TestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}