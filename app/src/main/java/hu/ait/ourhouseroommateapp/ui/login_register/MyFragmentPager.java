package hu.ait.ourhouseroommateapp.ui.login_register;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import hu.ait.ourhouseroommateapp.ui.login_register.FragmentCreate;
import hu.ait.ourhouseroommateapp.ui.login_register.FragmentExisting;
import hu.ait.ourhouseroommateapp.ui.login_register.FragmentJoin;

/**
 * Created by nasheyarahman on 5/12/17.
 */

public class MyFragmentPager extends FragmentPagerAdapter {

    public MyFragmentPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch(position){
            case 0:
                fragment = new FragmentJoin();
                break;
            case 1:
                fragment = new FragmentCreate();
                break;
            case 2:
                fragment = new FragmentExisting();
                break;
            default:
                fragment = new FragmentCreate();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "JOIN";
        } else if(position == 1){
            return "CREATE";
        } else {
            return "EXISTING";
        }
    }
}
