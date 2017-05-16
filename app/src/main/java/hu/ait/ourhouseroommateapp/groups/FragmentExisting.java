package hu.ait.ourhouseroommateapp.groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.ui.MainActivity;

/**
 * Created by nasheyarahman on 5/12/17.
 */

public class FragmentExisting extends Fragment implements GroupScreen{

    public static final String TAG = "FragmentExisting";

    GroupPresenter groupPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_existing_groups, container, false);

        groupPresenter = new GroupPresenter();
        groupPresenter.attachScreen(this);

        return rootView;
    }

    @Override
    public void onDestroy() {
        groupPresenter.detachScreen();
        super.onDestroy();
    }

    @Override
    public void navigateToMainScreen() {
        Intent main = new Intent(getActivity(), MainActivity.class);
        main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(main);
        getActivity().finish();
    }

    @Override
    public void highlightUniquenessProblem() { }

    @Override
    public void postFailure() {
        Toast.makeText(getContext(), R.string.issue_message, Toast.LENGTH_LONG);
    }
}
