package hu.ait.ourhouseroommateapp.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.CurrentGroup;
import hu.ait.ourhouseroommateapp.login.LoginOrRegisterActivity;
import hu.ait.ourhouseroommateapp.groups.LoginRegisterGroups;
import hu.ait.ourhouseroommateapp.main_functions.FragmentChores;
import hu.ait.ourhouseroommateapp.main_functions.FragmentExpenses;
import hu.ait.ourhouseroommateapp.main_functions.FragmentHome;
import hu.ait.ourhouseroommateapp.main_functions.FragmentLists;
import hu.ait.ourhouseroommateapp.main_functions.FragmentWhiteboard;

/**
 * Created by nasheyarahman on 5/8/17.
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    protected BottomNavigationView bottomNavigationView;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            showAboutDialog();
        } else if(id == R.id.action_group_info) {

        } else if (id == R.id.action_manage) {
            Intent groups = new Intent(this, LoginRegisterGroups.class);
            startActivity(groups);

        } else if (id == R.id.action_settings) {

        } else if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();

            CurrentGroup.clearCurrentGroup();

            Intent loginRegister = new Intent(this, LoginOrRegisterActivity.class);
            loginRegister.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginRegister);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void showAboutDialog(){
        Toast.makeText(this, "Hello hello", Toast.LENGTH_LONG).show();
    }

    protected void setupNavigationView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tvEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navHeaderEmail);
        tvEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        TextView name = (TextView) navigationView.getHeaderView(0).findViewById(R.id.navHeaderName);
        name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }

    protected void setupBottomNavigation() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title = "";

                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.navHome:
                        fragment = new FragmentHome();
                        title = getString(R.string.house_rules_title);
                        break;
                    case R.id.navExpenses:
                        fragment = new FragmentExpenses();
                        title = getString(R.string.bottom_nav_expenses);
                        break;
                    case R.id.navChores:
                        fragment = new FragmentChores();
                        title = getString(R.string.bottom_nav_chores);
                        break;
                    case R.id.navLists:
                        fragment = new FragmentLists();
                        title = getString(R.string.bottom_nav_shopping_lists);
                        break;
                    case R.id.navBoard:
                        fragment = new FragmentWhiteboard();
                        title = getString(R.string.bottom_nav_whiteboard);
                        break;

                }
                if (fragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment);
                    getSupportActionBar().setTitle(title);
                    fragmentTransaction.commit();
                }
                return true;
            }
        });
    }
}
