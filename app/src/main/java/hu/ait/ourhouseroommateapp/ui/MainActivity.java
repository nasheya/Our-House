package hu.ait.ourhouseroommateapp.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.ui.BasicActivity;

public class MainActivity extends BasicActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////
        //getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //getSupportActionBar().setCustomView(R.layout.abs_layout);

        Typeface lemonMilk = Typeface.createFromAsset(getAssets(),  "fonts/LemonMilk.otf");
        //actionBarText.setText("Our House");
        //actionBarText.setTypeface(lemonMilk);
        ////////////////////

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navHome:
                        //fragment = OneFragment.newInstance();
                        break;
                    case R.id.navExpenses:
                        //fragment = TwoFragment.newInstance();
                        break;
                    case R.id.navChores:
                        //fragment = ThreeFragment.newInstance();
                        break;
                    case R.id.navLists:
                        //fragment = ThreeFragment.newInstance();
                        break;
                    case R.id.navBoard:
                        //fragment = ThreeFragment.newInstance();
                        break;

                }
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }
}
