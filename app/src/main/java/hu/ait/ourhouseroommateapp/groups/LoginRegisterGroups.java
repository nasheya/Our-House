package hu.ait.ourhouseroommateapp.groups;

import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import hu.ait.ourhouseroommateapp.R;

public class LoginRegisterGroups extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register_groups);

        Typeface lemonMilk = Typeface.createFromAsset(getAssets(),  "fonts/LemonMilk.otf");
        name = (TextView) findViewById(R.id.name_groups);
        name.setTypeface(lemonMilk);

        FragmentPagerGroups myFragmentPager =
                new FragmentPagerGroups(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(myFragmentPager);
    }
}
