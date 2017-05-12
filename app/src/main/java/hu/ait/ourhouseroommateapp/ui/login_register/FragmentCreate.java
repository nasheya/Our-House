package hu.ait.ourhouseroommateapp.ui.login_register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.ait.ourhouseroommateapp.R;

/**
 * Created by nasheyarahman on 5/12/17.
 */

public class FragmentCreate extends Fragment {

    public static final String TAG = "FragmentCreate";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_new_group, container, false);
        return rootView;
    }
}