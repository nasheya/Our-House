package hu.ait.ourhouseroommateapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hu.ait.ourhouseroommateapp.main_data.Chore;

/**
 * Created by nasheyarahman on 5/16/17.
 */

public class ChoresAdapter extends RecyclerView.Adapter<ChoresAdapter.ViewHolder> {
    private List<Chore> choreList;

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
    public void onBindViewHolder(ChoresAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return choreList.size();
    }
}
