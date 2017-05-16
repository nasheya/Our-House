package hu.ait.ourhouseroommateapp.ui;

import android.os.Bundle;

import hu.ait.ourhouseroommateapp.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigationView();

        setupBottomNavigation();
    }
}
