package hu.ait.ourhouseroommateapp.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.main_functions.FragmentHome;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigationView();
        setupBottomNavigation();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new FragmentHome());
        getSupportActionBar().setTitle(getString(R.string.house_rules_title));
        fragmentTransaction.commit();
    }
}
