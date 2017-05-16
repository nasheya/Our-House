package hu.ait.ourhouseroommateapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.ait.ourhouseroommateapp.main_data.HouseRule;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class HouseRulesAdapter extends RecyclerView.Adapter<HouseRulesAdapter.ViewHolder> {
    private List<HouseRule> houseRulesList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public HouseRulesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HouseRulesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return houseRulesList.size();
    }
}
