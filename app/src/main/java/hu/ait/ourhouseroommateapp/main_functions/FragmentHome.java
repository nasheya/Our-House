package hu.ait.ourhouseroommateapp.main_functions;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.adapter.HouseRulesAdapter;
import hu.ait.ourhouseroommateapp.main_data.BoardNote;
import hu.ait.ourhouseroommateapp.main_data.HouseRule;
import hu.ait.ourhouseroommateapp.touch.TouchHelperCallback;

import static hu.ait.ourhouseroommateapp.R.id.recyclerNotes;

/**
 * Created by nasheyarahman on 5/14/17.
 */

public class FragmentHome extends Fragment implements MainFunctionScreen{
    public static final String TAG = "FragmentHouseRules";

    private HouseRulesAdapter houseRulesAdapter;
    private RecyclerView recyclerRules;
    private MainNetworkFunctionality network;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_house_rules, container, false);

        network = new MainNetworkFunctionality();
        network.attachScreen(this);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabRules);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddRuleDialog();
            }
        });

        recyclerRules = (RecyclerView) rootView.findViewById(R.id.recyclerRules);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerRules.setLayoutManager(layoutManager);

        houseRulesAdapter = new HouseRulesAdapter(network);
        recyclerRules.setAdapter(houseRulesAdapter);

        //adding touch support
        ItemTouchHelper.Callback callback = new TouchHelperCallback(houseRulesAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerRules);

        return rootView;
    }

    @Override
    public void onDestroy() {
        network.detachScreen();
        super.onDestroy();
    }

    private void showAddRuleDialog() {
        AlertDialog.Builder builderRule = new AlertDialog.Builder(this.getContext());

        builderRule.setTitle(R.string.add_house_rule_title);

        final EditText etRule = new EditText(this.getContext());

        View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.house_rules_add, null, false);
        //View view = inflater.inflate(R.layout.house_rules_add, null, false);
        //builderRule.setView(view);
        builderRule.setView(etRule);

        //final EditText etRule = (EditText) view.findViewById(R.id.etRule);

        builderRule.setView(etRule);

        builderRule.setPositiveButton(R.string.add_note_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!TextUtils.isEmpty(etRule.getText())){
                    HouseRule houseRule = new HouseRule(etRule.getText().toString());
                    houseRulesAdapter.addItem(houseRule);
                    recyclerRules.smoothScrollToPosition(houseRulesAdapter.getItemCount());
                }
            }
        });

        builderRule.setNegativeButton(R.string.cancel_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderRule.show();
    }

    @Override
    public void postFailure() {
        Toast.makeText(this.getActivity(), R.string.error_general, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void postSuccess() {

    }
}
