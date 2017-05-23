package hu.ait.ourhouseroommateapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.ait.ourhouseroommateapp.R;
import hu.ait.ourhouseroommateapp.main_data.BoardNote;
import hu.ait.ourhouseroommateapp.main_data.HouseRule;
import hu.ait.ourhouseroommateapp.main_data.ShoppingListItem;
import hu.ait.ourhouseroommateapp.main_functions.MainNetworkFunctionality;
import hu.ait.ourhouseroommateapp.touch.TouchHelperInterface;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class HouseRulesAdapter extends RecyclerView.Adapter<HouseRulesAdapter.ViewHolder> implements TouchHelperInterface {
    private List<HouseRule> houseRulesList;
    private MainNetworkFunctionality network;

    public HouseRulesAdapter(MainNetworkFunctionality network){
        this.network = network;
        houseRulesList = new ArrayList<>();
        network.getHouseRules(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView number;
        public TextView rule;
        public ImageView edit;

        public ViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.tvHouseRuleNumber);
            rule = (TextView) itemView.findViewById(R.id.tvHouseRule);
            edit = (ImageView) itemView.findViewById(R.id.editRule);
        }

        public void setNumber(TextView number) {
            this.number = number;
        }

        public void setRule(TextView rule) {
            this.rule = rule;
        }
    }

    @Override
    public HouseRulesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_rules_item, parent, false);
        return new HouseRulesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HouseRulesAdapter.ViewHolder holder, int position) {
        int index = position + 1;
        holder.number.setText(index+". ");
        holder.rule.setText(houseRulesList.get(position).getRule());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showEditDialog();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //supposed to open dialog box with info and maybe delete button
            }
        });
    }

    @Override
    public int getItemCount() {
        return houseRulesList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        HouseRule temp = houseRulesList.remove(position);
        network.deleteHouseRule(temp);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void addToList(HouseRule rule){
        houseRulesList.add(rule);
        notifyItemInserted(houseRulesList.size()-1);
    }

    public void addItem(HouseRule rule){
        network.addHouseRule(rule);
    }
}
