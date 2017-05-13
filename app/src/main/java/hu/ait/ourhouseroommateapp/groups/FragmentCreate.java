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

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.ui.MainActivity;

/**
 * Created by nasheyarahman on 5/12/17.
 */

public class FragmentCreate extends Fragment implements GroupScreen{

    public static final String TAG = "FragmentCreate";

    EditText etName;
    EditText etCode;
    EditText etIdentify;
    Button btnCreate;

    GroupPresenter groupPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_new_group, container, false);

        etName = (EditText) rootView.findViewById(R.id.group_name_create);
        etCode = (EditText) rootView.findViewById(R.id.group_code_create);
        etIdentify = (EditText) rootView.findViewById(R.id.group_identifier_create);
        btnCreate = (Button) rootView.findViewById(R.id.create_group_button);

        groupPresenter = new GroupPresenter();
        groupPresenter.attachScreen(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewGroup();
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
    public void highlightUniquenessProblem(){
        etIdentify.setError(getString(R.string.ID_taken));
        etIdentify.requestFocus();
    }

    private void createNewGroup(){
        String groupID = etIdentify.getText().toString();
        String groupName = etName.getText().toString();
        String groupCode = etCode.getText().toString();

        etIdentify.setError(null);
        etName.setError(null);
        etCode.setError(null);

        if(formIsNonEmpty()){
            groupPresenter.addGroupToFirebase(groupID, groupName, groupCode);
        }
    }

    private boolean formIsNonEmpty(){
        boolean cancel = false;
        View focusView = null;

        final String groupID = etIdentify.getText().toString();
        String groupName = etName.getText().toString();
        String groupCode = etCode.getText().toString();

        if (TextUtils.isEmpty(groupName)) {
            etName.setError(getString(R.string.error_field_required));
            focusView = etName;
            cancel = true;
        }

        if (TextUtils.isEmpty(groupCode)) {
            etCode.setError(getString(R.string.error_field_required));
            focusView = etCode;
            cancel = true;
        }

        if (TextUtils.isEmpty(groupID)) {
            etIdentify.setError(getString(R.string.error_field_required));
            focusView = etIdentify;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
