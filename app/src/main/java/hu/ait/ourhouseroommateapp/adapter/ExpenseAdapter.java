package hu.ait.ourhouseroommateapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.ait.ourhouseroommateapp.main_data.Expense;
import hu.ait.ourhouseroommateapp.main_functions.MainNetworkFunctionality;
import hu.ait.ourhouseroommateapp.touch.TouchHelperInterface;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> implements TouchHelperInterface{
    private List<Expense> expenseList;
    MainNetworkFunctionality network;

    public ExpenseAdapter(MainNetworkFunctionality network){
        this.network = network;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ExpenseAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    @Override
    public void onItemDismiss(int position) {

    }
}
