package hu.ait.ourhouseroommateapp.groups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.ui.MainActivity;

/**
 * Created by nasheyarahman on 5/12/17.
 */

public class FragmentJoin extends Fragment implements GroupScreen{

    public static final String TAG = "FragmentJoin";

    EditText etID;
    EditText etCode;
    Button btnJoin;

    GroupPresenter groupPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_join_new_group, container, false);

        groupPresenter = new GroupPresenter();
        groupPresenter.attachScreen(this);

        etID = (EditText) rootView.findViewById(R.id.group_ID_join);
        etCode = (EditText) rootView.findViewById(R.id.group_code_join);
        btnJoin = (Button) rootView.findViewById(R.id.join_group_button);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinGroup();
            }
        });

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
    public void highlightUniquenessProblem() {
        etID.setError(getString(R.string.invalid_group_combo_join));
        etID.requestFocus();
    }

    private void joinGroup(){
        String groupID = etID.getText().toString();
        String groupCode = etCode.getText().toString();

        etID.setError(null);
        etCode.setError(null);

        if(formIsNonEmpty()){
            groupPresenter.joinGroup(groupID, groupCode);
        }
    }

    private boolean formIsNonEmpty(){
        boolean cancel = false;
        View focusView = null;

        String groupID = etID.getText().toString();
        String groupCode = etCode.getText().toString();

        if (TextUtils.isEmpty(groupID)) {
            etID.setError(getString(R.string.error_field_required));
            focusView = etID;
            cancel = true;
        }

        if (TextUtils.isEmpty(groupCode)) {
            etCode.setError(getString(R.string.error_field_required));
            focusView = etCode;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void postFailure() {
        Toast.makeText(getContext(), R.string.issue_message, Toast.LENGTH_LONG);
    }
}
