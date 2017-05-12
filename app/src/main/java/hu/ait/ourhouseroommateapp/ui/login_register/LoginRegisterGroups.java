package hu.ait.ourhouseroommateapp.ui.login_register;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.ui.MainActivity;

public class LoginRegisterGroups extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_groups);

        Typeface lemonMilk = Typeface.createFromAsset(getAssets(),  "fonts/LemonMilk.otf");
        name = (TextView) findViewById(R.id.name_groups);
        name.setTypeface(lemonMilk);

        MyFragmentPager myFragmentPager =
                new MyFragmentPager(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(myFragmentPager);
    }

    public void navigateToMainScreen() {
        Intent main = new Intent(this, MainActivity.class);
        main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main);
        finish();
    }
}
