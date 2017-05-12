package hu.ait.ourhouseroommateapp.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.ui.login_register.LoginOrRegisterActivity;
import hu.ait.ourhouseroommateapp.ui.login_register.LoginRegisterGroups;

/**
 * Created by nasheyarahman on 5/8/17.
 */

public class BasicActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            showAboutDialog();
        } else if (id == R.id.action_manage) {
            Intent groups = new Intent(this, LoginRegisterGroups.class);
            startActivity(groups);

        } else if (id == R.id.action_settings) {

        } else if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();

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
}
