package hu.ait.ourhouseroommateapp.groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.adapter.ExistingGroupAdapter;
import hu.ait.ourhouseroommateapp.ui.MainActivity;

/**
 * Created by nasheyarahman on 5/12/17.
 */

public class FragmentExisting extends Fragment implements GroupScreen{

    public static final String TAG = "FragmentExisting";

    GroupPresenter groupPresenter;

    private ExistingGroupAdapter existingGroupAdapter;

    private RecyclerView recyclerExisting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_existing_groups, container, false);

        groupPresenter = new GroupPresenter();
        groupPresenter.attachScreen(this);

        recyclerExisting = (RecyclerView) rootView.findViewById(R.id.recyclerExistingGroups);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerExisting.setLayoutManager(layoutManager);

        existingGroupAdapter = new ExistingGroupAdapter(this.getContext(), groupPresenter);
        recyclerExisting.setAdapter(existingGroupAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerExisting.getContext(),
                layoutManager.getOrientation());
        recyclerExisting.addItemDecoration(dividerItemDecoration);

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
