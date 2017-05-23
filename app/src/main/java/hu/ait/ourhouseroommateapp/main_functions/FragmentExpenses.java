package hu.ait.ourhouseroommateapp.main_functions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.adapter.ExpenseAdapter;
import hu.ait.ourhouseroommateapp.touch.TouchHelperCallback;

/**
 * Created by nasheyarahman on 5/14/17.
 */

public class FragmentExpenses extends Fragment implements MainFunctionScreen {
    public static final String TAG = "FragmentExpenses";

    private ExpenseAdapter expenseAdapter;
    private RecyclerView recyclerExpense;
    private MainNetworkFunctionality network;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expenses, container, false);

        network = new MainNetworkFunctionality();
        network.attachScreen(this);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fabExpenses);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddExpenseDialog();
            }
        });

        recyclerExpense = (RecyclerView) rootView.findViewById(R.id.recyclerExpenses);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerExpense.setLayoutManager(layoutManager);

//        expenseAdapter = new ExpenseAdapter(network);
//        recyclerExpense.setAdapter(expenseAdapter);
//
//        //adding touch support
//        ItemTouchHelper.Callback callback = new TouchHelperCallback(expenseAdapter);
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//        touchHelper.attachToRecyclerView(recyclerExpense);

        return rootView;
    }

    private void showAddExpenseDialog() {
    }

    @Override
    public void onDestroy() {
        network.detachScreen();
        super.onDestroy();
    }

    @Override
    public void postFailure() {
        
    }

    @Override
    public void postSuccess() {

    }
}
